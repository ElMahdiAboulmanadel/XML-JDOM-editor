package xml.project.app.methods.jdom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class ReadXmlFiles
{

    public static void readData(Attribute id)
    {
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File("/XML-Editor/sample.xml");

        try
        {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");
            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                // if (id.getValue().equals(node.getAttribute("id").getValue()))
                // {

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
                System.out.println("--------------------------");
            }

            // }

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void readData()
    {
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File("/XML-Editor/sample.xml");

        try
        {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");
            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                // if (id.getValue().equals(node.getAttribute("id").getValue()))
                // {

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
                System.out.println("--------------------------");
            }

            // }

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void writeAndModifyID(Attribute id, String newID)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getAttribute("id").setValue(newID);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void writeAndModifyAuthor(Attribute id, String newAuthor)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("author").setText(newAuthor);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void writeAndModifyTitle(String id, String newTitle)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("title").setText(newTitle);
                }
            }
            //
            // XMLOutputter xmlOutput = new XMLOutputter();
            // xmlOutput.setFormat(Format.getPrettyFormat());
            // xmlOutput.output(document, new FileWriter(
            // "/home/maria/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void writeAndModifyGenre(Attribute id, String newGenre)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("genre").setText(newGenre);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void writeAndModifyPrice(Attribute id, String newPrice)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("price").setText(newPrice);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void writeAndModifyPublishDate(Attribute id, String newdate)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("publish_date").setText(newdate);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void writeAndModifyDescription(Attribute id,
            String newDescription)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.getChild("description").setText(newDescription);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void addExtraField(Attribute id)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    Element rate = new Element("rate").setText("5");
                    node.addContent(rate);
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void addElement(String id, String author, String title,
            String genre, String price, String date, String discription)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            // Element rootNode = document.getRootElement();

            Element book = new Element("book");
            book.setAttribute(new Attribute("id", id));
            book.addContent(new Element("author").setText(author));
            book.addContent(new Element("title").setText(title));
            book.addContent(new Element("genre").setText(genre));
            book.addContent(new Element("price").setText(price));
            book.addContent(new Element("publish_date").setText(date));
            book.addContent(new Element("description").setText(discription));
            document.getRootElement().addContent(book);

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void deleteAuthor(Attribute id)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("author");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void deleteTitle(Attribute id)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("title");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void deleteGenre(Attribute id)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("genre");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void deletePrice(Attribute id)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("price");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void deletePublishDate(Attribute id)
    {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("publish_date");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }

    }

    public static void deleteDescription(Attribute id)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("/Downloads/sample2.xml");

        try
        {

            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("book");

            for (int i = 0; i < list.size(); i++)
            {
                Element node = (Element) list.get(i);
                if (id.getValue().equals(node.getAttribute("id").getValue()))
                {
                    node.removeChild("description");
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document,
                    new FileWriter("/Downloads/sample2.xml"));

        } catch (IOException io)
        {

            System.out.println(io.getMessage());

        } catch (JDOMException jdomex)
        {

            System.out.println(jdomex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        // try {

        // SAXBuilder builder = new SAXBuilder();
        //
        // File xmlFile = new File("/XML-Editor/sample.xml");
        //
        // Document document = (Document) builder.build(xmlFile);
        // Element rootNode = document.getRootElement();
        // List<Element> list = rootNode.getChildren("book");
        // Element inputNode = (Element) list.get(1);
        // writeAndModifyAuthor(inputNode.getAttribute("id"), "yala yad");
        // writeAndModifyID(inputNode.getAttribute("id"), "book88");
        // writeAndModifyDescription(inputNode.getAttribute("id"), "yala yad");
        // writeAndModifyGenre(inputNode.getAttribute("id"), "yala yad");
        // writeAndModifyPrice(inputNode.getAttribute("id"), "yala yad");
        // writeAndModifyPublishDate(inputNode.getAttribute("id"), "yala yad");
        // writeAndModifyTitle(inputNode.getAttribute("id"), "yala yad");
        // addExtraField(inputNode.getAttribute("id"));
        readData();
        // deleteAuthor(inputNode.getAttribute("id"));

        // String Id="bk10002";
        // String author="J.K Rolling";
        // String title= "Harry potter";
        // String genre="fiction";
        // String price="800";
        // String publishDate="2000";
        // String Discription="wizards";
        // addElement(Id,author,title,genre,price,publishDate,Discription);

        // }
        //
        // catch (IOException io) {
        // System.out.println(io.getMessage());
        // }
        //
        // catch (JDOMException jdomex) {
        // System.out.println(jdomex.getMessage());
        // }

    }

}
