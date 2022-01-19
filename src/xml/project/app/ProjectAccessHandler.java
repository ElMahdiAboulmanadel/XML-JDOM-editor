/**
 * 
 */
package xml.project.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Ahmed Badr
 *
 */
public class ProjectAccessHandler
{

    private static Shell    parentShell;
    private WorkspaceAccess projectWorkspace;

    public ProjectAccessHandler(Shell parentShell,
            WorkspaceAccess projectWorkspace)
    {
        ProjectAccessHandler.parentShell = parentShell;
        this.projectWorkspace = projectWorkspace;
    }

    public void loadFile(ActionType actiontype)
    {

        openProjectAccessDialog(parentShell, projectWorkspace, actiontype);
    }

    public void createNewFile()
    {

    }

    /**
     * @param parentShell
     * @param pronetWorkspace
     * @param actionName
     */
    private int openProjectAccessDialog(Shell parentShell,
            WorkspaceAccess pronetWorkspace, ActionType actiontype)
    {

        ProjectAccessDialog projectAction = new ProjectAccessDialog(parentShell,
                pronetWorkspace, actiontype);

        return projectAction.open();
    }

    /////////////////////////////////////////////////////////////////////////
    //
    // Notification METHODs
    //
    /////////////////////////////////////////////////////////////////////////

    /**
     * @param reason
     * @param message
     * @return buttonID
     */
    public static int messageBox(String reason, String message)
    {
        MessageBox messageBox = new MessageBox(parentShell,
                SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);

        messageBox.setText(reason);

        messageBox.setMessage(message);
        int buttonID = messageBox.open();

        return buttonID;
    }
}
