/**
 * 
 */
package xml.project.app;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ahmed Badr
 *
 */
public class ProjectFileManager
{

    private Path workspacePathName;// = path + Workspace Name

    /**
     * Constructor.
     * 
     * @param workspaceName,
     *            DEFAULT NAME = "Projects"
     */
    private ProjectFileManager(final String workspaceName)
    {
        this.workspacePathName = Paths
                .get(ProjectFileMangUtils.getUserHomePath(), workspaceName);

        createWorkspaceFolder();
    }

    /**
     * Factory methods.
     * 
     * @param spaceName
     * @return FileManager specified for the Workspace
     */
    public static ProjectFileManager init(final String spaceName)
    {
        return new ProjectFileManager(spaceName);
    }

    // --- initiation methods----------
    /**
     * Initiate the Workspace.
     */
    private void createWorkspaceFolder()
    {
        ProjectFileMangUtils.creatFolder(workspacePathName);
    }

    /**
     * @return the spaceRootPath (path + Workspace Name)
     */
    public Path workspacePathName()
    {
        return workspacePathName;
    }

}
