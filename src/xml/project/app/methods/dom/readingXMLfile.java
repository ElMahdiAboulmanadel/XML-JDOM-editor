package xml.project.app.methods.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class readingXMLfile
{

    private static Document doc;

    private static void readFile()
    {
        File fXmlFile = new File("/XML-Editor/sample.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void writeFile()
    {
        try
        {

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(
                    new File("/XML-Editor/sample.xml"));
            transformer.transform(source, result);

        } catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }

    ///////////////////////////////////////////////
    ///////
    /////// get Node
    ///////
    //////////////////////////////////////////////

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unused")
    private static Node getElementByID(String id)
    {
        NodeList nList = doc.getElementsByTagName("book");
        for (int i = 0; i < nList.getLength(); i++)
        {
            Node book = doc.getElementsByTagName("book").item(i);
            NamedNodeMap attr = book.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            if (nodeAttr.getNodeValue().equals(id))
            {
                // Element eElement = (Element) book;
                return book;
            }
        }
        return null;

    }

    /**
     * @param title
     * @return
     */
    @SuppressWarnings("unused")
    private static Node getElementByTitle(String title)
    {

        NodeList bookList = doc.getElementsByTagName("book");
        for (int j = 0; j < bookList.getLength(); j++)
        {
            Node book = bookList.item(j);
            NodeList nList = book.getChildNodes();
            for (int i = 0; i < nList.getLength(); i++)
            {
                Node node = nList.item(i);
                if (node.getNodeName().equals("title"))
                {
                    if (node.getTextContent().equals(title))
                    {
                        // Element eElement = (Element) book;
                        return book;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param author
     * @return
     */
    private static Node getElementByAuthor(String author)
    {

        NodeList bookList = doc.getElementsByTagName("book");
        for (int j = 0; j < bookList.getLength(); j++)
        {
            Node book = bookList.item(j);
            NodeList nList = book.getChildNodes();
            for (int i = 0; i < nList.getLength(); i++)
            {
                Node node = nList.item(i);
                if (node.getNodeName().equals("author"))
                {
                    if (node.getTextContent().equals(author))
                    {
                        // Element eElement = (Element) book;
                        return book;
                    }
                }
            }
        }
        return null;
    }

    ///////////////////////////////////////////////
    ///////
    /////// modify elements
    ///////
    //////////////////////////////////////////////

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

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        try
        {

            readFile();
            Node n = getElementByAuthor("Thurman, Paula");
            modifyElement(n, "price", "20");
            writeFile();
            readFile();

            System.out.println(
                    "Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("book");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++)
            {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element eElement = (Element) nNode;

                    System.out.println("ID : " + eElement.getAttribute("id"));
                    System.out.println("author : "
                            + eElement.getElementsByTagName("author"));
                    System.out.println(
                            "title : " + eElement.getElementsByTagName("title")
                                    .item(0).getTextContent());
                    System.out.println(
                            "genre : " + eElement.getElementsByTagName("genre")
                                    .item(0).getTextContent());
                    System.out.println(
                            "price : " + eElement.getElementsByTagName("price")
                                    .item(0).getTextContent());
                    System.out.println("publish date : "
                            + eElement.getElementsByTagName("publish_date")
                                    .item(0).getTextContent());
                    System.out.println("description : "
                            + eElement.getElementsByTagName("description")
                                    .item(0).getTextContent());
                    System.out.println("----------------------------");

                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Node nNode = getElementByAuthor("Thurman, Paula");
        // Element eElement = (Element) nNode;
        // System.out.println("price : " +
        // eElement.getElementsByTagName("price").item(0).getTextContent());

    }

}
