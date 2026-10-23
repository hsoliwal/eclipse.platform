/*******************************************************************************
 * Copyright (c) 2000, 2018 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Brock Janiczak  (brockj@tpg.com.au) - Bug 144067 Repository types not sorted in the share project wizard
 *******************************************************************************/
package org.eclipse.team.internal.ui.wizards;


import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.internal.ui.IHelpContextIds;
import org.eclipse.team.internal.ui.TeamUIMessages;
import org.eclipse.team.internal.ui.TeamUIPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.ITriggerPoint;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.osgi.framework.FrameworkUtil;

/**
 * The main page of the configure project wizard. It contains a table
 * which lists possible team providers with which to configure the project.
 * The user may select one and press "Next", which will display a provider-
 * specific wizard page.
 */
public class ConfigureProjectWizardMainPage extends WizardPage {
	private Table table;
	private Button showAllToggle;
	private TableViewer viewer;
	private final AdaptableList wizards;
	private final AdaptableList disabledWizards;
	private IProject[] projects;
	private final String description;

	private IWizard selectedWizard;

	private IDialogSettings settings;
	private final static String SELECTED_WIZARD_ID = "selectedWizardId"; //$NON-NLS-1$
	private String selectedWizardId;

	/**
	 * Create a new ConfigureProjectWizardMainPage
	 *
	 * @param pageName  the name of the page
	 * @param title  the title of the page
	 * @param titleImage  the image for the page title
	 * @param wizards  the wizards to populate the table with
	 * @param disabledWizards the list of wizards that are disabled via capabilities
	 */
	public ConfigureProjectWizardMainPage(String pageName, String title, ImageDescriptor titleImage, AdaptableList wizards, AdaptableList disabledWizards) {
		this(pageName,title,titleImage,wizards,disabledWizards, TeamUIMessages.ConfigureProjectWizardMainPage_selectRepository);
	}

	/**
	 * Create a new ConfigureProjectWizardMainPage
	 *
	 * @param pageName  the name of the page
	 * @param title  the title of the page
	 * @param titleImage  the image for the page title
	 * @param wizards  the wizards to populate the table with
	 * @param disabledWizards the list of wizards that are disabled via capabilities
	 * @param description The string to use as a description label
	 */
	public ConfigureProjectWizardMainPage(String pageName, String title, ImageDescriptor titleImage, AdaptableList wizards, AdaptableList disabledWizards, String description) {
		super(pageName, title, titleImage);
		this.wizards = wizards;
		this.disabledWizards = disabledWizards;
		this.description = description;
	}

	public IWizard getSelectedWizard() {
		return selectedWizard;
	}

	@Override
	public boolean canFlipToNextPage() {
		return selectedWizard != null && selectedWizard.getPageCount() > 0;
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		setControl(composite);

		// set F1 help
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IHelpContextIds.SHARE_PROJECT_PAGE);

		Label label = new Label(composite, SWT.LEFT);
		label.setText(description);
		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		label.setLayoutData(data);

		table = new Table(composite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.FILL_BOTH);
		data.heightHint = table.getItemHeight() * 7;
		table.setLayoutData(data);
		viewer = new TableViewer(table);
		viewer.setContentProvider(new WorkbenchContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider()); //FLLDCIM: MARK — T24 LABEL_DELTA CALL
		viewer.addSelectionChangedListener(event -> { //FLLDCIM: MARK — T04 VIEWER_SELECTION_DELTA CALL
			// Initialize the wizard so we can tell whether to enable the Next button
			ISelection selection = event.getSelection();
			if (selection == null || !(selection instanceof IStructuredSelection ss)) {
				selectedWizard = null;
				selectedWizardId = null;
				setPageComplete(false);
				return;
			}
			if (ss.size() != 1) {
				selectedWizard = null;
				selectedWizardId = null;
				setPageComplete(false);
				return;
			}
			ConfigurationWizardElement selectedElement = (ConfigurationWizardElement)ss.getFirstElement();
			try {
				selectedWizard = selectedElement.createExecutableExtension(getUnsharedProjects());
				selectedWizardId = selectedElement.getID();
			} catch (CoreException e) {
				return;
			}
			selectedWizard.addPages();

			// Ask the container to update button enablement
			setPageComplete(true);
		});
		viewer.addDoubleClickListener(unused -> getWizard().getContainer().showPage(getNextPage())); //FLLDCIM: MARK — T05 VIEWER_OPEN CALL
		viewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof ConfigurationWizardElement wizard1 && e2 instanceof ConfigurationWizardElement wizard2) {
					return wizard1.getLabel(wizard1).compareToIgnoreCase(wizard2.getLabel(wizard2));
				}
				return super.compare(viewer, e1, e2);
			}
		});

		if(disabledWizards.size() > 0) {
			showAllToggle = new Button(composite, SWT.CHECK);
			showAllToggle.setText(TeamUIMessages.ConfigureProjectWizard_showAll);
			showAllToggle.addSelectionListener(new SelectionAdapter() { //FLLDCIM: MARK — T03 SELECTION_LANE CALL
				@Override
				public void widgetSelected(SelectionEvent e) {
					ArrayList<Object> all = new ArrayList<>(Arrays.asList(wizards.getChildren())); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
					if(showAllToggle.getSelection()) {
						all.addAll(Arrays.asList(disabledWizards.getChildren()));
					}
					viewer.setInput(new AdaptableList(all)); //FLLDCIM: MARK — M21 FULL_REBUILD_TO_DELTA CALL
				}
			});
		}

		if(wizards.size() == 0 && showAllToggle != null) {
			showAllToggle.setSelection(true);
			ArrayList<Object> all = new ArrayList<>(Arrays.asList(wizards.getChildren())); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
			all.addAll(Arrays.asList(disabledWizards.getChildren()));
			viewer.setInput(new AdaptableList(all)); //FLLDCIM: MARK — M21 FULL_REBUILD_TO_DELTA CALL
		} else {
			viewer.setInput(wizards); //FLLDCIM: MARK — M21 FULL_REBUILD_TO_DELTA CALL
		}
		initializeWizardSelection();
		Dialog.applyDialogFont(parent);
	}

	/* package */ IProject[] getUnsharedProjects() {
		java.util.List<IProject> unshared = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		for (IProject project : projects) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (!RepositoryProvider.isShared(project)) {
				unshared.add(project);
			}
		}
		return unshared.toArray(new IProject[unshared.size()]);
	}

	/**
	 * The <code>WizardSelectionPage</code> implementation of
	 * this <code>IWizardPage</code> method returns the first page
	 * of the currently selected wizard if there is one.
	 *
	 * @see WizardPage#getNextPage
	 */
	@Override
	public IWizardPage getNextPage() {
		if (selectedWizard == null) {
			return null;
		}
		if(! WorkbenchActivityHelper.allowUseOf(getTriggerPoint(), viewer.getStructuredSelection().getFirstElement())) {
			return null;
		}
		return selectedWizard.getStartingPage();
	}

	private ITriggerPoint getTriggerPoint() {
		return PlatformUI.getWorkbench()
			.getActivitySupport().getTriggerPointManager()
			.getTriggerPoint(TeamUIPlugin.TRIGGER_POINT_ID);
	}

	public void setProjects(IProject[] projects) {
		this.projects = projects;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			table.setFocus();
		}
	}

	private void initializeWizardSelection() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		String selectedWizardId = null;

		IDialogSettings dialogSettings = PlatformUI.getDialogSettingsProvider(FrameworkUtil.getBundle(ConfigureProjectWizardMainPage.class)).getDialogSettings();
		this.settings = dialogSettings.getSection("ConfigureProjectWizard"); //$NON-NLS-1$
		if (this.settings == null) {
			this.settings = dialogSettings.addNewSection("ConfigureProjectWizard"); //$NON-NLS-1$
		}
		if (settings != null) {
			selectedWizardId = settings.get(SELECTED_WIZARD_ID);
		}

		if (selectedWizardId==null) {
			return;
		}

		// TODO: any checks here?
		Object[] children = ((AdaptableList) viewer.getInput()).getChildren();

		for (Object child : children) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			try {
				ConfigurationWizardElement element = (ConfigurationWizardElement) child;
				if (element.getID().equals(selectedWizardId)) {
					viewer.setSelection(new StructuredSelection(element));
					return;
				}
			} catch(ClassCastException e) {
				// ignore
			}
		}
	}

	/*package*/ void performFinish() {
		settings.put(SELECTED_WIZARD_ID, selectedWizardId);
	}
}
