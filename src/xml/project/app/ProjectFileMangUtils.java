/**
 * 
 */
package xml.project.app;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Ahmed Badr
 *
 */
public class ProjectFileMangUtils
{

    private ProjectFileMangUtils()
    {
    }

    /**
     * @return the user home path, which depends on the used operating system
     *         (OS).
     */
    public static String getUserHomePath()
    {
        return System.getProperty("user.home");// specified by Java, valid for
                                               // all OSs
    }

    /**
     * List all directories & files in the given path.
     * 
     * @param dirPath
     * @return all files & directories into the given dirPath
     */
    public static DirectoryStream<Path> listAll(final Path dirPath)
    {
        try
        {
            return Files.newDirectoryStream(dirPath);
        } catch (IOException e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * @param folderPathName
     */
    public static void creatFolder(final Path folderPathName)
    {
        if (!Files.exists(folderPathName))
        {
            try
            {
                System.out.println("Folder does not exist. The Folder ("
                        + folderPathName + ") will be created.");

                Files.createDirectory(folderPathName);
            } catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }
        System.out
                .println("Folder (" + folderPathName + ") does already exist.");
    }
}
