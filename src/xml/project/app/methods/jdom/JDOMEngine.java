/**
 * 
 */
package xml.project.app.methods.jdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import xml.project.app.methods.XMLEngine;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class JDOMEngine implements XMLEngine
{

    public JDOMEngine()
    {
        // Not Used.
    }

    /*
     * (non-Javadoc)
     * 
     * @see xml.project.app.methods.XMLEngine#writeFile(java.lang.Object,
     * java.nio.file.Path)
     */
    @Override
    public void writeFile(Object booksCatalog, Path filePath)
            throws JAXBException
    {
        try
        {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output((Document) booksCatalog,
                    new FileWriter(filePath.toString()));
        } catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see xml.project.app.methods.XMLEngine#readFile(java.nio.file.Path)
     */
    @Override
    public Object readFile(Path filePath)
            throws JAXBException, FileNotFoundException
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(filePath.toString());
        Document document = null;
        try
        {
            document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");
            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                System.out.println(
                        "Book id: " + node.getAttribute("id").getValue());
                System.out
                        .println("Author Name: " + node.getChildText("author"));
                System.out.println("Title: " + node.getChildText("title"));
                System.out.println("genre: " + node.getChildText("genre"));
                System.out.println("price:" + node.getChildText("price"));
                System.out.println(
                        "publish date:" + node.getChildText("publish_date"));
                System.out.println(
                        "Discription:" + node.getChildText("description"));
            }

        } catch (IOException io)
        {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex)
        {
            System.out.println(jdomex.getMessage());
        }
        return document;
    }

    /**
     * @param Doc
     * @param id
     * @return
     */
    public static Element getElementById(Document Doc, String id)
    {
        Element rootNode = Doc.getRootElement();
        List<Element> list = rootNode.getChildren("book");
        for (int i = 0; i < list.size(); i++)
        {
            Element node = (Element) list.get(i);
            if (id.equals(node.getAttribute("id").getValue()))
            {
                return node;
            }
        }
        return null;
    }

    /**
     * @param id
     * @param newfield
     * @param doc
     */
    public static void modifyElement(Document doc, String id, String oldFieldID,
            String newfield)
    {
        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren("book");

        for (int i = 0; i < list.size(); i++)
        {
            Element node = (Element) list.get(i);
            if (id.equals(node.getAttribute("id").getValue()))
            {
                if (oldFieldID.equals("id"))
                {
                    node.getAttribute("id").setValue(newfield);
                } else
                {
                    List<Element> nodeChildren = node.getChildren();
                    for (int j = 0; j < nodeChildren.size(); j++)
                    {
                        if (nodeChildren.get(j).getName().equals(oldFieldID))
                        {
                            nodeChildren.get(j).setText(newfield);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * @param doc
     * @param id
     * @param author
     * @param title
     * @param genre
     * @param price
     * @param date
     * @param discription
     */
    public static void addElement(Document doc, String id, String author,
            String title, String genre, String price, String date,
            String discription)
    {
        Element book = new Element("book");
        book.setAttribute(new Attribute("id", id));
        book.addContent(new Element("author").setText(author));
        book.addContent(new Element("title").setText(title));
        book.addContent(new Element("genre").setText(genre));
        book.addContent(new Element("price").setText(price));
        book.addContent(new Element("publish_date").setText(date));
        book.addContent(new Element("description").setText(discription));
        doc.getRootElement().addContent(book);
    }

    /**
     * @param doc
     * @param id
     */
    public static void deleteElement(Document doc, String id)
    {
        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren("book");
        for (int i = 0; i < list.size(); i++)
        {
            Element node = (Element) list.get(i);
            if (id.equals(node.getAttribute("id").getValue()))
            {
                node.detach();
            }
        }
    }
}
