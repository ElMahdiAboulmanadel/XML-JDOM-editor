/*
 * PRONET_GUI Project - Planning Platform for Roadside Networks
 * Field of Research: Intelligent Transportation Systems (ITS)
 * 
 * Supervisor: Robil Daher
 *             Assoc. Prof. Dr.-Ing.
 * Institution:  ITS-WG | Networks Department | German University in Cairo (GUC)
 *
 * Date/Time: 28.05.2014 - 20:51:06
 * Version 0.0.1
 *
 * (Planned) to be released under the GPLv2 open source license
 *-------------------------------------------------------------------
 */

package xml.project.app;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.wb.swt.SWTResourceManager;

import xml.project.app.gui.DOMMethodBuilder;
import xml.project.app.gui.FrontendGUI;
import xml.project.app.gui.JAXBMethodBuilder;
import xml.project.app.gui.JDOMMethodBuilder;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * @author Ahmed Badr
 * 
 */
class ProjectAccessDialog extends TitleAreaDialog
{

    private Combo combo;
    private Text  txtWorkspacename;

    private WorkspaceAccess pronetWorkspace;

    private String selectedFileName;

    private Composite  area;
    private Composite  container;
    private ActionType actionName;

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public ProjectAccessDialog(Shell parentShell,
            WorkspaceAccess pronetWorkspace, ActionType actionName)
    {
        super(parentShell);

        this.pronetWorkspace = pronetWorkspace;
        this.actionName = actionName;
        this.selectedFileName = pronetWorkspace.currentFileName();
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent)
    {

        if (actionName.equals(ActionType.OPEN_FILE))
        {
            area = (Composite) super.createDialogArea(parent);
            container = new Composite(area, SWT.NONE);
            container.setLayout(new GridLayout(2, false));
            container.setLayoutData(new GridData(GridData.FILL_BOTH));
            this.setTitle("XML-Editor");

            this.setMessage("Load XML File");

            {
                final Label lblProject = new Label(container, SWT.NONE);
                lblProject.setText("File Name");
                ComboViewer comboViewer = new ComboViewer(container,
                        SWT.READ_ONLY);
                combo = comboViewer.getCombo();
                combo.setLayoutData(
                        new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                loadFilesList(combo);
            }

            {
                final Label lblWorkspace = new Label(container, SWT.NONE);
                lblWorkspace.setText("Workspace");
                txtWorkspacename = new Text(container, SWT.BORDER);
                txtWorkspacename.setBackground(SWTResourceManager
                        .getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                txtWorkspacename.setEditable(false);
                txtWorkspacename.setLayoutData(
                        new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                txtWorkspacename
                        .setText(pronetWorkspace.projectWorkspace().toString());
            }
        }
        return area;
    }

    /**
     * Create contentopenProjectAccessDialogs of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
        Button buttonOK = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, false);
        buttonOK.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {

                switch (actionName)
                {
                    case NEW_FILE:
                    case OPEN_FILE:
                        if (!selectedFileName.isEmpty())
                        {
                            pronetWorkspace
                                    .setCurrentFileName(selectedFileName);
                            FrontendGUI.shlApplication.setText("XML-Editor"
                                    + " | " + selectedFileName.substring(0,
                                            selectedFileName.length() - 4));
                            FrontendGUI
                                    .setCurrentFilePath(Paths.get(
                                            pronetWorkspace.projectWorkspace()
                                                    .toString(),
                                            selectedFileName));
                            JAXBMethodBuilder.textJAXBFileName
                                    .setText(selectedFileName);
                            DOMMethodBuilder.textDOMFileName
                                    .setText(selectedFileName);
                            JDOMMethodBuilder.textJDOMFileName
                                    .setText(selectedFileName);
                            FrontendGUI.tabProjecFolder.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }

            }
        });

        Button buttonCancel = createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, true);
        buttonCancel.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
            }
        });
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize()
    {
        return new Point(450, 300);
    }

    @Override
    protected void okPressed()
    {
        getInput();

        switch (actionName)
        {

            case OPEN_FILE:
            {
                if (!selectedFileName.isEmpty())
                {
                    if (pronetWorkspace.currentFileName()
                            .equals(selectedFileName))
                    {
                        ProjectAccessHandler.messageBox("File Management",
                                "You Can't Open the Same File");
                    } else
                    {
                        super.okPressed();
                    }
                } else
                {
                    ProjectAccessHandler.messageBox("File Management",
                            "No File Selected");
                }
                break;
            }
            case NEW_FILE:
            {
                break;
            }
            default:
                break;
        }
    }

    /**
     * Get the input from Text/Combo.
     */
    private void getInput()
    {

        switch (actionName)
        {
            case NEW_FILE:
                break;
            case OPEN_FILE:
            {
                selectedFileName = combo.getText();
                break;
            }
        }

    }

    /**
     * @param targetCombo
     */
    private void loadFilesList(Combo targetCombo)
    {
        DirectoryStream<Path> projectsList = pronetWorkspace
                .getFileList(pronetWorkspace.projectWorkspace());
        Path filePath = Paths
                .get(FrontendGUI.workSpaceAccess.projectWorkspace().toString());

        projectsList = pronetWorkspace.getFileList(filePath);

        for (Path path : projectsList)
            targetCombo.add(path.getFileName().toString());
    }
}
