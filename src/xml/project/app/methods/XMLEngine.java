/**
 * 
 */
package xml.project.app.methods;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public interface XMLEngine
{

    public void writeFile(Object booksCatalog, Path filePath)
            throws JAXBException;

    public Object readFile(Path filePath)
            throws JAXBException, FileNotFoundException;

}
