/**
 * 
 */
package xml.project.app;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

/**
 * @author Ahmed Badr
 *
 */
public class WorkspaceAccess
{

    private ProjectFileManager projectFileManager;

    private String currentFileName;

    // ---Constructors ----------------------
    /**
     * @param pronetSpaceName
     */
    public WorkspaceAccess()
    {
        this.projectFileManager = ProjectFileManager.init("XML-Editor");

        this.currentFileName = "";
    }

    /**
     * @param pronetSpaceName
     */
    public WorkspaceAccess(final String pronetSpaceName)
    {
        this.projectFileManager = ProjectFileManager.init(pronetSpaceName);
    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Basic Getters
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @return the current workspacePathName
     */
    public Path projectWorkspace()
    {
        return projectFileManager.workspacePathName();
    }

    /**
     * @return the currentProjectName
     */
    public String currentFileName()
    {
        return currentFileName;
    }

    /**
     * @return true if a project is already opened; otherwise, false
     */
    public boolean hasOpenedFile()
    {
        if (!this.currentFileName.isEmpty())
            return true;

        return false;
    }

    /**
     * @param projectName
     *            (incl. path)
     * @return true if project found, otherwise false
     */
    public boolean foundProject(final Path projectName)
    {
        DirectoryStream<Path> projectsList = getFileList(projectWorkspace());

        for (Path path : projectsList)
        {
            if (path.equals(projectName))
                return true;
        }

        return false;
    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Basic Setters
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @param currentFileName
     */
    public void setCurrentFileName(final String currentFileName)
    {
        this.currentFileName = currentFileName;
    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Basic Actions
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @param fileName
     */
    public void openFile(final String fileName)
    {

        this.currentFileName = fileName;
    }

    /**
     * Obtain the files and folders as a list of Paths.
     * 
     * @param dirPath
     * @return all directories/files located into this path
     */
    public DirectoryStream<Path> getFileList(final Path dirPath)
    {
        return ProjectFileMangUtils.listAll(dirPath);
    }

}
