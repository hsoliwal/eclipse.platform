/*******************************************************************************
 * Copyright (c) 2024, 2026 Hannes Wellmann and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Hannes Wellmann - initial API and implementation
 *     Hannes Wellmann - Migrate from JNA to FFM API
 *     Hitesh Soliwal (Synexia) - JNA restored for Java 21 (M3JDK21), later fixes ported
 *******************************************************************************/
package org.eclipse.core.internal.filesystem.local;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.provider.FileInfo;

/**
 * Native handler for Windows through JNA, the Java 21 (M3JDK21) form of the handler. The FFM API form of master
 * (JEP 454, final in Java 22) is kept in history; this file carries the same behaviour: the raw-path handling of
 * DOS device paths, the {@link EFS#IGNORE_NAME_CASE} fast path, the directory listing through a directory
 * stream, the name of a file that does not exist, and the two-argument {@link #putFileInfo(String, IFileInfo)}.
 */
public class Win32Handler extends NativeHandler {

	private static final String WIN32_RAW_PATH_PREFIX = "\\\\?\\"; //$NON-NLS-1$
	private static final String WIN32_UNC_RAW_PATH_PREFIX = "\\\\?\\UNC"; //$NON-NLS-1$

	private static final int ATTRIBUTES = EFS.ATTRIBUTE_SYMLINK | EFS.ATTRIBUTE_LINK_TARGET // symbolic link support
			| EFS.ATTRIBUTE_ARCHIVE | EFS.ATTRIBUTE_READ_ONLY | EFS.ATTRIBUTE_HIDDEN; // standard DOS attributes

	private static final IFileInfo[] EMPTY_FILEINFO_ARRAY = {};

	@Override
	public int getSupportedAttributes() {
		return ATTRIBUTES;
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public FileInfo fetchFileInfo(String fileName, int options) {
		String target = toLongWindowsPath(fileName);
		Path file = Path.of(target);
		// If the file exists, the name will be replaced by its real casing.
		FileInfo fileInfo = createFileInfo(file);

		if (target.length() == 7 && target.startsWith(WIN32_RAW_PATH_PREFIX) && target.endsWith(":\\")) { //$NON-NLS-1$
			// FindFirstFile does not work at the root level. However, we don't need it because the root will never change time-stamp.
			// A root path is for example: \\?\c:\
			fileInfo.setDirectory(true);
			fileInfo.setExists(Files.exists(file));
			return fileInfo;
		}
		if ((options & EFS.IGNORE_NAME_CASE) != 0) {
			// Use faster method to read file attributes without determining the real casing of its name
			readDosFileAttributesIntoFileInfo(fileInfo, file);
			return fileInfo;
		}
		try (Memory mem = new Memory(WIN32_FIND_DATA_SIZE)) {
			// Allocating (uninitialized) memory explicitly in advance is faster than using
			// the slightly more convenient direct instantiation of a JNA WinBase.WIN32_FIND_DATA structure,
			// probably because the latter perform also an initial autowrite and computes the size each time.
			// For the same reason it is again faster to read the data-structure 'manually'.
			// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-findfirstfilew
			long handle = FileAPIh.FindFirstFileW(new WString(target), mem);
			if (handle == FileAPIh.INVALID_HANDLE_VALUE) {
				int error = Native.getLastError();
				if (!(error == WinError.ERROR_FILE_NOT_FOUND // file not found in existing parent directory
						|| error == WinError.ERROR_PATH_NOT_FOUND)) { // Not even the parent directory exists
					fileInfo.setError(IFileInfo.IO_ERROR);
				}
				return fileInfo;
			}
			FileAPIh.FindClose(handle);

			convertFindDataWToFileInfo(mem, fileInfo, file);
		} catch (IOException e) {
			// Leave alone and continue. The name is set before an IOException can be thrown
			fileInfo.setError(IFileInfo.IO_ERROR);
		}
		return fileInfo;
	}

	@Override
	public IFileInfo[] listDirectoryAndGetFileInfos(String fileName) {
		Path file = Path.of(fileName);
		List<IFileInfo> children = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		try (DirectoryStream<Path> directoryContent = Files.newDirectoryStream(file);) {
			for (Path child : directoryContent) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				// The directory stream delivers names with real casing and therefore the faster method can be used
				FileInfo fileInfo = createFileInfo(child);
				readDosFileAttributesIntoFileInfo(fileInfo, child);
				children.add(fileInfo);
			}
		} catch (IOException e) {
			return EMPTY_FILEINFO_ARRAY;
		}
		return children.toArray(IFileInfo[]::new);
	}

	/**
	 * Sets the given {@link IFileInfo} to the given file.
	 *
	 * @implNote
	 * This implementation invokes the native Windows API methods {@code GetFileAttributesW} and {@code SetFileAttributesW} to read and write the file attributes.
	 * It allows to set the updated file attributes only once, after all modifications are applied and only if there is any overall change.
	 * Through {@link java.nio.file.attribute.DosFileAttributeView} each file attribute can only be set individually,
	 * which leads to one native file-attributes get and (up to) one set invocation per attribute.
	 * The NIO API does not provide means for batch updates.
	 * Since there are currently three file attributes considered, using the Java NIO API would consequently be up to three times slower.
	 */
	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public boolean putFileInfo(String fileName, IFileInfo info) {
		WString lpFileName = new WString(toLongWindowsPath(fileName));
		// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-getfileattributesw
		long dwFileAttributes = FileAPIh.GetFileAttributesW(lpFileName);
		if (dwFileAttributes == FileAPIh.INVALID_FILE_ATTRIBUTES) {
			return false;
		}
		if (dwFileAttributes == WinNT.FILE_ATTRIBUTE_NORMAL) {
			// Assume nothing is set, as the documentation of FILE_ATTRIBUTE_NORMAL states:
			// "A file that does not have other attributes set. This attribute is valid only when used alone."
			dwFileAttributes = 0;
		}
		long fileAttributes = dwFileAttributes;

		boolean archive = info.getAttribute(EFS.ATTRIBUTE_ARCHIVE);
		boolean readOnly = info.getAttribute(EFS.ATTRIBUTE_READ_ONLY);
		boolean hidden = info.getAttribute(EFS.ATTRIBUTE_HIDDEN);
		fileAttributes = set(fileAttributes, WinNT.FILE_ATTRIBUTE_ARCHIVE, archive);
		fileAttributes = set(fileAttributes, WinNT.FILE_ATTRIBUTE_READONLY, readOnly);
		fileAttributes = set(fileAttributes, WinNT.FILE_ATTRIBUTE_HIDDEN, hidden);

		if (dwFileAttributes == fileAttributes) {
			return true; // Everything is already up to date -> nothing to do
		}
		// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-setfileattributesw
		return FileAPIh.SetFileAttributesW(lpFileName, fileAttributes);
	}

	public static String getShortPathName(String longPath) {
		longPath = toLongWindowsPath(longPath);
		char[] buffer = new char[longPath.length()];
		// https://learn.microsoft.com/de-de/windows/win32/api/fileapi/nf-fileapi-getshortpathnamew
		int newLength = com.sun.jna.platform.win32.Kernel32.INSTANCE.GetShortPathName(longPath, buffer, buffer.length);
		if (0 < newLength && newLength < buffer.length) { // zero means error and if not shorter it's not useful
			int offset = longPath.startsWith(WIN32_UNC_RAW_PATH_PREFIX) ? WIN32_UNC_RAW_PATH_PREFIX.length() : WIN32_RAW_PATH_PREFIX.length();
			return new String(buffer, offset, newLength - offset);
		}
		return null;
	}

	private static String toLongWindowsPath(String fileName) {
		// See https://learn.microsoft.com/en-us/windows/win32/fileio/naming-a-file
		// https://learn.microsoft.com/en-us/dotnet/standard/io/file-path-formats#dos-device-paths
		if (fileName.startsWith(WIN32_RAW_PATH_PREFIX)) {
			return fileName;
		} else if (fileName.startsWith("\\\\")) { //$NON-NLS-1$
			//convert UNC path of form \\server\path to long/unicode form \\?\UNC\server\path
			return WIN32_UNC_RAW_PATH_PREFIX + fileName.substring(1); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		} else {
			//convert simple path of form C:\path to long/unicode form \\?\C:\path
			return WIN32_RAW_PATH_PREFIX + fileName; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
	}

	private static FileInfo createFileInfo(Path file) {
		FileInfo fileInfo = new FileInfo();
		if (file.getFileName() instanceof Path name) {
			// Behind null check because roots don't have names, e.g.,
			// - network shares '\\?\UNC\server\share'
			// - drives '\\?\c:\'
			fileInfo.setName(name.toString());
		}
		return fileInfo;
	}

	/**
	 * Reads the DOS file attributes of the file without determining its real name
	 * (which may have different letter case, because the Window file system is case-insensitive).
	 *
	 * This is significantly faster than using {@code FindFirstFileW()}, which effectively searches a directory
	 * and requires two native calls.
	 */
	private static void readDosFileAttributesIntoFileInfo(FileInfo fileInfo, Path path) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		try {
			DosFileAttributes attributes = Files.readAttributes(path, DosFileAttributes.class, LinkOption.NOFOLLOW_LINKS);

			fileInfo.setExists(true);
			fileInfo.setLastModified(attributes.lastModifiedTime().toMillis());
			fileInfo.setLength(attributes.size());
			fileInfo.setDirectory(attributes.isDirectory());
			fileInfo.setAttribute(EFS.ATTRIBUTE_ARCHIVE, attributes.isArchive());
			fileInfo.setAttribute(EFS.ATTRIBUTE_READ_ONLY, attributes.isReadOnly());
			fileInfo.setAttribute(EFS.ATTRIBUTE_HIDDEN, attributes.isHidden());
			if (attributes.isSymbolicLink()) {
				setSymLink(path, fileInfo);
				// For sym-links, DosFileAttributes.isDirectory() always returns false.
				// Determine the real type of the link target and follow-links to the eventual target
				fileInfo.setDirectory(Files.isDirectory(path));
			}
		} catch (NoSuchFileException unused) { // file just does not exist
		} catch (IOException unused) {
			// Leave alone and continue.
			fileInfo.setError(IFileInfo.IO_ERROR);
		}
	}

	private static void setSymLink(Path path, FileInfo info) throws IOException {
		Path linkTarget = Files.readSymbolicLink(path);
		info.setAttribute(EFS.ATTRIBUTE_SYMLINK, true);
		info.setStringAttribute(EFS.ATTRIBUTE_LINK_TARGET, linkTarget.toString());
	}

	static class FileAPIh {
		static {
			Native.register(NativeLibrary.getInstance("Kernel32" /* , W32APIOptions.DEFAULT_OPTIONS */ )); //$NON-NLS-1$
			// Not using W32APIOptions.DEFAULT_OPTIONS requires the usage of a few special types (e.g. WString) but improves performance.
		}
		private static final long INVALID_HANDLE_VALUE = Pointer.nativeValue(WinBase.INVALID_HANDLE_VALUE.getPointer());

		// winnt.h HANDLE can be expressed as java long

		// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-findfirstfilew
		static native long FindFirstFileW(WString lpFileName, Pointer lpFindFileData);

		// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-getfileattributesw
		static native long GetFileAttributesW(WString lpFileName);

		// https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-setfileattributesw
		static native boolean SetFileAttributesW(WString lpFileName, long dwFileAttributes);

		static final long INVALID_FILE_ATTRIBUTES = new WinBase.DWORD(-1).longValue();

		static native boolean FindClose(long handle);
	}

	private static final int DWORD_SIZE = WinBase.DWORD.SIZE;
	private static final int FILETIME_SIZE = 2 * DWORD_SIZE;
	private static final int WCHAR_SIZE = 2;

	/**
	 * Read the of the native memory data-structure
	 * <a href="https://learn.microsoft.com/en-us/windows/win32/api/minwinbase/ns-minwinbase-win32_find_dataw">{@code WIN32_FIND_DATAW}</a>.
	 * <pre>
	 *	typedef struct _WIN32_FIND_DATAW {
	 *		DWORD dwFileAttributes;
	 *		FILETIME ftCreationTime;
	 *		FILETIME ftLastAccessTime;
	 *		FILETIME ftLastWriteTime;
	 *		DWORD nFileSizeHigh;
	 *		DWORD nFileSizeLow;
	 *		DWORD dwReserved0;
	 *		DWORD dwReserved1;
	 *		_Field_z_ WCHAR  cFileName[ MAX_PATH ];
	 *		_Field_z_ WCHAR  cAlternateFileName[ 14 ];
	 *	} WIN32_FIND_DATAW
	 * </pre>
	 */
	private static final int DW_FILE_ATTRIBUTES = 0;
	private static final int FT_CREATION_TIME = DW_FILE_ATTRIBUTES + DWORD_SIZE;
	private static final int FT_LAST_ACCESS_TIME = FT_CREATION_TIME + FILETIME_SIZE;
	private static final int FT_LAST_WRITE_TIME = FT_LAST_ACCESS_TIME + FILETIME_SIZE;
	private static final int N_FILE_SIZE_HIGH = FT_LAST_WRITE_TIME + FILETIME_SIZE;
	private static final int N_FILE_SIZE_LOW = N_FILE_SIZE_HIGH + DWORD_SIZE;
	private static final int DW_RESERVED_0 = N_FILE_SIZE_LOW + DWORD_SIZE;
	private static final int DW_RESERVED_1 = DW_RESERVED_0 + DWORD_SIZE;
	private static final int C_FILE_NAME = DW_RESERVED_1 + DWORD_SIZE;
	private static final int C_ALTERNATE_FILE_NAME = C_FILE_NAME + WinDef.MAX_PATH * WCHAR_SIZE;

	private static final int WIN32_FIND_DATA_SIZE = C_ALTERNATE_FILE_NAME + 14 * WCHAR_SIZE;
	static {
		if (WIN32_FIND_DATA_SIZE != WinBase.WIN32_FIND_DATA.sizeOf()) {
			throw new IllegalStateException("Struct 'WIN32_FIND_DATAW' has unexpected size"); //$NON-NLS-1$
		}
	}
	private static final long MAXDWORD = 0xFFFFFFFFL; // unsigned long from winnt.h. On Windows a C long usually has only 32bit

	private static void convertFindDataWToFileInfo(Memory mem, FileInfo info, Path file) throws IOException { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		/**
		 * For possible values of dwFileAttributes and their descriptions,
		 * see <a href="https://learn.microsoft.com/en-us/windows/win32/fileio/file-attribute-constants">File Attribute Constants</a>.
		 */
		int dwFileAttributes = readDWORDAsSignedInt(mem, DW_FILE_ATTRIBUTES);
		Date ftLastWriteTime = readFILETIME(mem, FT_LAST_WRITE_TIME);
		long nFileSizeHigh = readDWORD(mem, N_FILE_SIZE_HIGH);
		long nFileSizeLow = readDWORD(mem, N_FILE_SIZE_LOW);
		int dwReserved0 = readDWORDAsSignedInt(mem, DW_RESERVED_0);
		String cFileName = mem.getWideString(C_FILE_NAME);

		long fileLength = (nFileSizeHigh * (MAXDWORD + 1)) + nFileSizeLow;

		info.setName(cFileName);
		info.setExists(true);
		info.setLastModified(ftLastWriteTime.getTime());
		info.setLength(fileLength);
		info.setDirectory(isSet(dwFileAttributes, WinNT.FILE_ATTRIBUTE_DIRECTORY));
		info.setAttribute(EFS.ATTRIBUTE_ARCHIVE, isSet(dwFileAttributes, WinNT.FILE_ATTRIBUTE_ARCHIVE));
		info.setAttribute(EFS.ATTRIBUTE_READ_ONLY, isSet(dwFileAttributes, WinNT.FILE_ATTRIBUTE_READONLY));
		info.setAttribute(EFS.ATTRIBUTE_HIDDEN, isSet(dwFileAttributes, WinNT.FILE_ATTRIBUTE_HIDDEN));

		boolean isReparsePoint = isSet(dwFileAttributes, WinNT.FILE_ATTRIBUTE_REPARSE_POINT);
		if (isReparsePoint && dwReserved0 == WinNT.IO_REPARSE_TAG_SYMLINK) {
			setSymLink(file, info);
		}
	}

	private static int readDWORDAsSignedInt(Memory memory, int offset) {
		return memory.getInt(offset); // int is signed
	}

	private static long readDWORD(Memory memory, int offset) {
		// From https://learn.microsoft.com/en-us/windows/win32/winprog/windows-data-types
		// "DWORD - A 32-bit unsigned integer. The range is 0 through 4294967295 decimal. This type is declared in IntSafe.h as follows: typedef unsigned long DWORD;"
		return readDWORDAsSignedInt(memory, offset) & MAXDWORD;
	}

	private static Date readFILETIME(Memory memory, int offset) {
		int low = readDWORDAsSignedInt(memory, offset);
		int high = readDWORDAsSignedInt(memory, offset + DWORD_SIZE);
		return WinBase.FILETIME.filetimeToDate(high, low);
	}

	private static boolean isSet(long field, int bit) {
		return (field & bit) != 0;
	}

	private long set(long field, int bit, boolean isSet) {
		return isSet ? (field | bit) : (field & ~bit);
	}

}
