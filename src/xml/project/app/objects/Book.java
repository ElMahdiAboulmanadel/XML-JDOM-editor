/**
 * 
 */
package xml.project.app.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
@XmlRootElement(name = "book")
@XmlType(propOrder =
{ "id", "author", "title", "genre", "price", "publish_Date", "description" })
public class Book
{

    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publish_date;
    private String description;

    /**
     * Empty Constructor.
     */
    public Book()
    {
    }

    /**
     * Constructor Create New Book.
     * 
     * @param authorID
     * @param title
     * @param genre
     * @param price
     * @param date
     */
    public Book(String id, String authorID, String title, String genre,
            String price, String date)
    {
        this.id = id;
        this.author = authorID;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = date;
    }

    // ////////////////////////////////////////////////
    // // Getters
    // ///////////////////////////////////////////////

    /**
     * @return the book id.
     */
    @XmlAttribute
    public String getId()
    {
        return id;
    }

    /**
     * @return Author.
     */
    @XmlElement(name = "author")
    public String getAuthor()
    {
        return author;
    }

    /**
     * @return Book Title.
     */
    @XmlElement(name = "title")
    public String getTitle()
    {
        return title;
    }

    /**
     * @return Book Genre.
     */
    @XmlElement(name = "genre")
    public String getGenre()
    {
        return genre;
    }

    /**
     * @return Book Price.
     */
    @XmlElement(name = "price")
    public String getPrice()
    {
        return price;
    }

    /**
     * @return Publish Date.
     */
    @XmlElement(name = "publish_date")
    public String getPublish_Date()
    {
        return publish_date;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    // ////////////////////////////////////////////////
    // // Setters
    // ///////////////////////////////////////////////

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param authorID
     *            to set
     */
    public void setAuthor(String autherID)
    {
        this.author = autherID;
    }

    /**
     * @param title
     *            to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @param genre
     *            to set
     */
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    /**
     * @param price
     *            to set
     */
    public void setPrice(String price)
    {
        this.price = price;
    }

    /**
     * @param publish_date
     *            to set
     */
    public void setPublish_Date(String publisDate)
    {
        this.publish_date = publisDate;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {

        return "Book ID: " + id + ", Author: " + author + ", Title: " + title
                + ", Genre: " + genre + ", Publish Date: " + publish_date
                + ", Price: " + price;
    }

}
