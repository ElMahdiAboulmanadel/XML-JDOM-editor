/**
 * 
 */
package xml.project.app.methods.dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import xml.project.app.methods.XMLEngine;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class DOMEngine implements XMLEngine
{

    public DOMEngine()
    {
        // Not Used.
    }

    /* (non-Javadoc)
     * @see xml.project.app.methods.XMLEngine#writeFile(java.lang.Object, java.nio.file.Path)
     */
    @Override
    public void writeFile(Object booksCatalog, Path filePath)
            throws JAXBException
    {
        try
        {
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource((Document) booksCatalog);
            StreamResult result = new StreamResult(
                    new File(filePath.toString()));
            transformer.transform(source, result);

        } catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see xml.project.app.methods.XMLEngine#readFile(java.nio.file.Path)
     */
    @Override
    public Document readFile(Path filePath)
            throws JAXBException, FileNotFoundException
    {
        File fXmlFile = new File(filePath.toString());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * @param doc
     * @param id
     * @return
     */
    public static Node getElementByID(Document doc, String id)
    {

        NodeList nList = doc.getElementsByTagName("book");
        for (int i = 0; i < nList.getLength(); i++)
        {
            Node book = doc.getElementsByTagName("book").item(i);
            NamedNodeMap attr = book.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            if (nodeAttr.getNodeValue().equals(id))
            {
                System.out.println(nodeAttr.getNodeValue());
                return book;
            }
        }
        return null;
    }

    /**
     * @param node
     * @param type
     * @param name
     */
    public static void modifyElement(Node node, String type, String name)
    {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {

            Node nNode = list.item(i);

            if(type.equals("id")){
                node.getAttributes().item(0).setNodeValue(name);
            }

            if ("author".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }

            if ("title".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }

            if ("genre".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }

            if ("price".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }

            if ("publish_date".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }

            if ("description".equals(nNode.getNodeName())
                    && type.equals(nNode.getNodeName()))
            {
                nNode.setTextContent(name);
                break;
            }
        }
    }

}
