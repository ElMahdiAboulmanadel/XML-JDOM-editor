/**
 * 
 */
package xml.project.app.methods.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import xml.project.app.methods.XMLEngine;
import xml.project.app.objects.Book;
import xml.project.app.objects.Catalog;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class JAXBEngine implements XMLEngine
{

    public JAXBEngine()
    {
        // Not Used.
    }

    /**
     * @param booksCatalog
     * @param filePath
     * @throws JAXBException
     */
    @Override
    public void writeFile(Object booksCatalog, Path filePath)
            throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(Catalog.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal((Catalog) booksCatalog, System.out);
        m.marshal((Catalog) booksCatalog, new File(filePath.toString()));
    }

    /**
     * @param filePath
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    @Override
    public Catalog readFile(Path filePath)
            throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(Catalog.class);
        Unmarshaller um = context.createUnmarshaller();
        Catalog booksCatalog = (Catalog) um
                .unmarshal(new FileReader(filePath.toString()));
        if (booksCatalog != null)
            if (booksCatalog.getBooks() != null)
            {
                List<Book> booksCatlaog = booksCatalog.getBooks();
                for (int i = 0; i < booksCatlaog.size(); i++)
                {
                    System.out.println(booksCatlaog.get(i));
                }
            }
        return booksCatalog;
    }
}
