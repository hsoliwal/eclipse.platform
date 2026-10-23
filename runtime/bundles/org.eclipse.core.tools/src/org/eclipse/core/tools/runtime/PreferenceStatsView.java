/*******************************************************************************
 * Copyright (c) 2004, 2018 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.core.tools.runtime;

import java.util.*;
import org.eclipse.core.internal.preferences.EclipsePreferences;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceNodeVisitor;
import org.eclipse.core.tools.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Text view that dumps sizeof info and stats about the
 * Eclipse preferences tree.
 *
 * @since 3.0
 */
@SuppressWarnings("restriction")
public class PreferenceStatsView extends SpyView {

	// The JFace widget used for showing the Element Tree info.
	protected TextViewer viewer;

	private IAction updateAction;

	class UpdateAction extends Action {

		// number of nodes in the tree
		int nodeCount;
		// number of key/value pairs in the tree
		int kvCount;
		// number of nodes without key/value pairs
		int emptyNodes;
		// size of the tree
		int treeSize;
		// list of node with key/value pairs
		Set<String> nonEmptyNodes;

		// root node
		IEclipsePreferences rootNode = Platform.getPreferencesService().getRootNode();

		UpdateAction() {
			super("Update view"); //$NON-NLS-1$
			this.setToolTipText("Update"); //$NON-NLS-1$
			this.setImageDescriptor(ImageDescriptor.createFromURLSupplier(true, () -> PreferenceStatsView.class.getResource("/icons/refresh.gif")));
		}

		@Override
		public void run() {
			super.run();
			reset();
			try {
				visitTree();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			updateTextView();
			reset();
		}

		private void reset() {
			nodeCount = 0;
			kvCount = 0;
			emptyNodes = 0;
			treeSize = 0;
			nonEmptyNodes = new TreeSet<>();
		}

		private int basicSizeof(IEclipsePreferences node) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			if (node instanceof EclipsePreferences) {
				return basicSizeof((EclipsePreferences) node);
			}

			// name
			int count = sizeof(node.name());

			// key/value pairs
			try {
				String[] keys = node.keys();
				for (String key : keys) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					count += sizeof(key);
					String value = node.get(key, null);
					count += sizeof(value);
				}
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			return count;
		}

		/*
		 * 12 for the object header
		 * 4 for each slot
		 */
		private int basicSizeof(EclipsePreferences node) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			int count = 12;

			// name
			count += 4;
			count += sizeof(node.name());

			// dirty boolean
			count += 4;

			// removed boolean
			count += 4;

			// loading boolean
			count += 4;

			// slot for the parent pointer
			count += 4;

			// child map
			// TODO this isn't quite right but is ok for now
			count += 4;
			try {
				String[] childrenNames = node.childrenNames();
				for (String childrenName : childrenNames) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					count += sizeof(childrenName);
				}
			} catch (BackingStoreException e) {
				//this is truly exceptional!
			}

			// node change listener list
			// TODO
			count += 4;

			// preference change listener list
			// TODO
			count += 4;

			// cached path
			count += 4;
			count += sizeof(node.absolutePath());

			// key/value pairs
			// TODO this isn't quite right but is ok for now
			count += 4;
			String[] keys = node.keys();
			for (String key : keys) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				count += sizeof(key);
				String value = node.get(key, null);
				count += sizeof(value);
			}

			return count;
		}

		private int basicSizeof(Map<?, ?> map) {
			if (map == null) {
				return 0;
			}

			//formula taken from BundleStats
			int count = (int) Math.round(44 + (16 + (map.size() * 1.25 * 4)) + (24 * map.size()));

			for (Map.Entry<?, ?> entry : map.entrySet()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				count += sizeof(entry.getKey());
				count += sizeof(entry.getValue());
			}
			return count;
		}

		/**
		 * All sizeof tests should go through this central method to weed out
		 * duplicates.
		 */
		private int sizeof(Object object) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			if (object == null) { //|| DeepSize.ignore(object))
				return 0;
			}
			if (object instanceof String) {
				return 44 + 2 * ((String) object).length();
			}
			if (object instanceof byte[]) {
				return 16 + ((byte[]) object).length;
			}
			if (object instanceof Integer) {
				return 16;
			}
			if (object instanceof Map) {
				return basicSizeof((Map<?, ?>) object);
			}
			if (object instanceof IEclipsePreferences) {
				return basicSizeof((IEclipsePreferences) object);
			}
			if (object instanceof QualifiedName name) {
				return 20 + sizeof(name.getQualifier()) + sizeof(name.getLocalName());
			}
			// unknown -- use deep size
			return 0;
		}

		private void visitTree() throws BackingStoreException { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			// count the number of nodes in the preferences tree
			reset();
			IPreferenceNodeVisitor visitor = node -> {
				try {
					treeSize += sizeof(node);
					nodeCount++;
					int keys = node.keys().length;
					kvCount += keys;
					if (keys == 0) {
						emptyNodes++;
					} else {
						nonEmptyNodes.add(node.absolutePath() + " (" + keys + ")"); //$NON-NLS-1$//$NON-NLS-2$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
					}
				} catch (BackingStoreException e) {
					e.printStackTrace();
				}
				return true;
			};
			rootNode.accept(visitor);
		}

		void updateTextView() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			final StringBuilder buffer = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
			buffer.append("Total node count: " + prettyPrint(nodeCount) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append("Nodes without keys: " + prettyPrint(emptyNodes) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append("Key/value pairs: " + prettyPrint(kvCount) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append("Total size of tree: " + prettyPrint(treeSize) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append("Nodes with key/value pairs:\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			for (String string : nonEmptyNodes) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				buffer.append("\t" + string + "\n"); //$NON-NLS-1$//$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}

			//post changes to UI thread
			viewer.getControl().getDisplay().asyncExec(() -> { //FLLDCIM: MARK — T08 UI_THREAD_SYNCHRONIZER CALL
				if (!viewer.getControl().isDisposed()) {
					IDocument doc = viewer.getDocument();
					doc.set(buffer.toString());
					viewer.setDocument(doc);
				}
			});
		}

		private String prettyPrint(int i) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			StringBuilder buf = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
			for (;;) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				if (i < 1000) {
					String val = Integer.toString(i);
					//pad with zeros if necessary
					if (buf.length() > 0) {
						if (val.length() < 2) {
							buf.append('0'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						}
						if (val.length() < 3) {
							buf.append('0'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						}
					}
					buf.append(val); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					return buf.toString();
				}
				if (i < 1000000) {
					String val = Integer.toString(i / 1000);
					//pad with zeros if necessary
					if (buf.length() > 0) {
						if (val.length() < 2) {
							buf.append('0'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						}
						if (val.length() < 3) {
							buf.append('0'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						}
					}
					buf.append(val); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					buf.append(','); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					i = i % 1000;
					continue;
				}
				buf.append(Integer.toString(i / 1000000)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				buf.append(','); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				i = i % 1000000;
			}
		}
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void createPartControl(Composite parent) {

		viewer = new TextViewer(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP | SWT.READ_ONLY);
		viewer.setDocument(new Document());

		IActionBars bars = getViewSite().getActionBars();

		final GlobalAction clearOutputAction = new ClearTextAction(viewer.getDocument());
		clearOutputAction.registerAsGlobalAction(bars);

		final GlobalAction selectAllAction = new SelectAllAction(viewer);
		selectAllAction.registerAsGlobalAction(bars);

		IMenuManager barMenuManager = getViewSite().getActionBars().getMenuManager();
		updateAction = new UpdateAction();
		barMenuManager.add(updateAction);

		// Delete action shortcuts are not captured by the workbench
		// so we need our key binding service to handle Delete keystrokes for us

		this.viewer.getControl().addKeyListener(new KeyAdapter() { //FLLDCIM: MARK — T12 INPUT_EVENT CALL
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.DEL) {
					clearOutputAction.run();
				}
			}
		});

		GlobalAction copyAction = new CopyTextSelectionAction(viewer);
		copyAction.registerAsGlobalAction(bars);

		bars.getToolBarManager().add(updateAction);
		bars.getToolBarManager().add(clearOutputAction);

		bars.updateActionBars();

		// creates a context menu with actions and adds it to the viewer control
		MenuManager menuMgr = new MenuManager();
		menuMgr.add(copyAction);
		menuMgr.add(clearOutputAction);
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		// populate the view with the initial data
		if (updateAction != null) {
			updateAction.run();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		updateAction = null;
	}

}
