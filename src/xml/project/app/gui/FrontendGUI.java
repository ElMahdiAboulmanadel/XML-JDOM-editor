/**
 * 
 */
package xml.project.app.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import xml.project.app.AboutDialog;
import xml.project.app.ActionType;
import xml.project.app.ProjectAccessHandler;
import xml.project.app.WorkspaceAccess;

import org.eclipse.swt.widgets.Composite;

import java.nio.file.Path;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class FrontendGUI
{

    public static Shell                shlApplication;
    protected static Display           display;
    public static WorkspaceAccess      workSpaceAccess;
    public static ProjectAccessHandler projectAccessHandler;
    private static Path                currentFilePath;
    public static CTabFolder           tabProjecFolder;

    public static void main(String[] args)
    {
        try
        {
            FrontendGUI window = new FrontendGUI();
            window.open();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open()
    {
        display = Display.getDefault();
        createContents();
        shlApplication.open();
        shlApplication.layout();

        while (!shlApplication.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
    }

    protected void createContents()
    {

        shlApplication = new Shell();
        shlApplication.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/application_edit.png"));
        shlApplication.setSize(818, 617);
        shlApplication.setText("XML-Editor");
        shlApplication.setLayout(new BorderLayout(0, 0));

        workSpaceAccess = new WorkspaceAccess();
        projectAccessHandler = new ProjectAccessHandler(shlApplication,
                workSpaceAccess);

        Composite composite = new Composite(shlApplication, SWT.BORDER);
        composite.setLayoutData(BorderLayout.NORTH);
        composite.setLayout(new GridLayout(2, false));

        ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);

        final ToolItem tltmItemNewFile = new ToolItem(toolBar, SWT.NONE);
        tltmItemNewFile.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/package_add.png"));
        tltmItemNewFile.setText("New");

        final ToolItem tltmItemOpenFile = new ToolItem(toolBar, SWT.NONE);
        tltmItemOpenFile.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/package.png"));
        tltmItemOpenFile.setText("Open");

        ToolBar toolBar_1 = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
        toolBar_1.setLayoutData(
                new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));

        ToolItem tltmAbout = new ToolItem(toolBar_1, SWT.NONE);
        tltmAbout.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                AboutDialog about = new AboutDialog(shlApplication);
                about.open();
            }
        });
        tltmAbout.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/information.png"));
        tltmAbout.setText("About");

        SashForm sashForm = new SashForm(shlApplication, SWT.NONE);
        sashForm.setLayoutData(BorderLayout.CENTER);

        tabProjecFolder = new CTabFolder(sashForm, SWT.BORDER | SWT.FLAT);
        tabProjecFolder.setMRUVisible(true);
        tabProjecFolder.setSelectionBackground(Display.getCurrent()
                .getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        tabProjecFolder.setEnabled(false);

        JAXBMethodBuilder.buildGUI(tabProjecFolder);

        DOMMethodBuilder.buildGUI(tabProjecFolder);

        JDOMMethodBuilder.buildGUI(tabProjecFolder);

        sashForm.setWeights(new int[]
        { 680 });

        ///////////////////////////////////////////////////////////////
        /////
        ///// Actions
        /////
        ///////////////////////////////////////////////////////////////

        tltmItemOpenFile.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                projectAccessHandler.loadFile(ActionType.OPEN_FILE);
            }
        });

        sashForm.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseUp(MouseEvent arg0)
            {
                if (!tabProjecFolder.getEnabled())
                    ProjectAccessHandler.messageBox("XML-Editor",
                            "Please, Select File or" + "\n" + "Create New One");
            }

            @Override
            public void mouseDown(MouseEvent arg0)
            {
                if (!tabProjecFolder.getEnabled())
                    ProjectAccessHandler.messageBox("XML-Editor",
                            "Please, Select File" + "\n" + "Create New One");
            }

            @Override
            public void mouseDoubleClick(MouseEvent arg0)
            {
                if (!tabProjecFolder.getEnabled())
                    ProjectAccessHandler.messageBox("XML-Editor",
                            "Please, Select File" + "\n" + "Create New One");
            }
        });
    }

    /**
     * @return the currentFilePath
     */
    public static Path getCurrentFilePath()
    {
        return currentFilePath;
    }

    /**
     * @param currentFilePath
     *            the currentFilePath to set
     */
    public static void setCurrentFilePath(Path currentFilePath)
    {
        FrontendGUI.currentFilePath = currentFilePath;
    }
}
