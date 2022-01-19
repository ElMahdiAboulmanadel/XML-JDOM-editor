/**
 * 
 */
package xml.project.app.objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
@XmlRootElement(name = "catalog")
public class Catalog
{

    private List<Book> books;

    /**
     * Constructor Create New Books Catalog.
     */
    public Catalog()
    {
        this.books = new ArrayList<Book>();
    }

    /**
     * @return the books
     */
    @XmlElement(name = "book")
    public List<Book> getBooks()
    {
        return books;
    }

    /**
     * @param books
     *            the books to set
     */
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    /**
     * Add New Book to the Catalog.
     * 
     * @param book
     */
    public void addBook(Book book)
    {
        this.books.add(book);
    }

    /**
     * Search For Book by ID.
     * 
     * @param bookID
     * @return
     */
    public Book getBookbyID(String bookID)
    {
        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).getId().equals(bookID))
                return books.get(i);
        }
        return null;
    }

    /**
     * Search For Book Index by ID.
     * 
     * @param bookID
     * @return
     */
    public int getBookIndexbyID(String bookID)
    {
        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).getId().equals(bookID))
                return i;
        }
        return 0;
    }

}
