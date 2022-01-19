/**
 * 
 */
package xml.project.app.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import xml.project.app.ProjectAccessHandler;
import xml.project.app.methods.jaxb.JAXBEngine;
import xml.project.app.objects.Book;
import xml.project.app.objects.Catalog;

/**
 * @author El Mahdi Aboulmanadel
 *
 */
public class JAXBMethodBuilder
{

    private static Catalog booksCatalog  = null;
    public static Text     textJAXBFileName;
    private static boolean readFileCheck = false;

    public static void buildGUI(final CTabFolder tabFolderProject)
    {

        CTabItem tbtmMethod = new CTabItem(tabFolderProject, SWT.NONE);
        tbtmMethod.setText("JAXP Method");

        Composite composite_1 = new Composite(tabFolderProject, SWT.NONE);
        tbtmMethod.setControl(composite_1);
        composite_1.setLayout(new BorderLayout(0, 0));

        ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
        toolBar_1.setLayoutData(BorderLayout.NORTH);

        final ToolItem tltmConfigs = new ToolItem(toolBar_1, SWT.NONE);
        tltmConfigs.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/cog.png"));
        tltmConfigs.setText("Configs");

        ToolItem toolItemSeparator = new ToolItem(toolBar_1, SWT.SEPARATOR);
        toolItemSeparator.setText("");

        final ToolItem tltmUpdateCatalog = new ToolItem(toolBar_1, SWT.NONE);
        tltmUpdateCatalog.setImage(SWTResourceManager
                .getImage(FrontendGUI.class, "/icons/arrow_refresh.png"));
        tltmUpdateCatalog.setText("Update");
        tltmUpdateCatalog.setEnabled(false);

        ToolItem toolItem1Separator = new ToolItem(toolBar_1, SWT.SEPARATOR);
        toolItem1Separator.setText("");

        final ToolItem tltmSaveFile = new ToolItem(toolBar_1, SWT.NONE);
        tltmSaveFile.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/disk.png"));
        tltmSaveFile.setText("Save To File");
        tltmSaveFile.setEnabled(false);

        SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
        sashForm_1.setLayoutData(BorderLayout.CENTER);

        Composite composite_2 = new Composite(sashForm_1, SWT.BORDER);
        composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

        Group grpManagment = new Group(composite_2, SWT.NONE);
        grpManagment.setText("Configs");
        grpManagment.setLayout(new GridLayout(1, false));

        Group grpFileManagment = new Group(grpManagment, SWT.NONE);
        grpFileManagment.setText("File Managment");
        grpFileManagment.setLayout(new GridLayout(2, false));
        grpFileManagment.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        final Label lblFileName = new Label(grpFileManagment, SWT.NONE);
        lblFileName.setText("File Name");

        textJAXBFileName = new Text(grpFileManagment, SWT.BORDER);
        textJAXBFileName.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textJAXBFileName.setEditable(false);

        final Button btnRead = new Button(grpFileManagment, SWT.NONE);
        btnRead.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/package_go.png"));
        GridData gd_btnOpen = new GridData(SWT.LEFT, SWT.CENTER, false, false,
                1, 1);
        gd_btnOpen.widthHint = 60;
        btnRead.setLayoutData(gd_btnOpen);
        btnRead.setText("Parse");
        new Label(grpFileManagment, SWT.NONE);

        Group grpCatalogManagement = new Group(grpManagment, SWT.NONE);
        grpCatalogManagement.setText("Catalog Management");
        grpCatalogManagement.setLayout(new GridLayout(2, false));
        grpCatalogManagement.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label lblCatalog = new Label(grpCatalogManagement, SWT.NONE);
        lblCatalog.setText("Options");

        final Combo comboOptions = new Combo(grpCatalogManagement, SWT.NONE);
        comboOptions.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboOptions.setText("Select Option");
        comboOptions.add("New Book");
        comboOptions.add("Edit");
        comboOptions.add("Delete Book");
        comboOptions.setEnabled(true);

        Label lblBooks = new Label(grpCatalogManagement, SWT.NONE);
        lblBooks.setText("Books");

        final Combo comboBooksList = new Combo(grpCatalogManagement, SWT.NONE);
        comboBooksList.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboBooksList.setText("Select Book");
        comboBooksList.setEnabled(false);

        Group grpSelectedBook = new Group(grpManagment, SWT.NONE);
        grpSelectedBook.setLayout(new GridLayout(2, false));
        grpSelectedBook.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        grpSelectedBook.setText("Selected Book");

        Label lblId = new Label(grpSelectedBook, SWT.NONE);
        lblId.setText("Book ID");

        final Text textBookID = new Text(grpSelectedBook, SWT.BORDER);
        textBookID.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textBookID.setEnabled(false);

        Label lblAuthor = new Label(grpSelectedBook, SWT.NONE);
        lblAuthor.setText("Author");

        final Text textAuthor = new Text(grpSelectedBook, SWT.BORDER);
        textAuthor.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textAuthor.setEnabled(false);

        Label lblGenre = new Label(grpSelectedBook, SWT.NONE);
        lblGenre.setText("Genre");

        final Text textGenre = new Text(grpSelectedBook, SWT.BORDER);
        textGenre.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textGenre.setEnabled(false);

        Label lblTitle = new Label(grpSelectedBook, SWT.NONE);
        lblTitle.setText("Title");

        final Text textTitle = new Text(grpSelectedBook, SWT.BORDER);
        textTitle.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textTitle.setEnabled(false);

        Label lblPrice = new Label(grpSelectedBook, SWT.NONE);
        lblPrice.setText("Price");

        final Text textPrice = new Text(grpSelectedBook, SWT.BORDER);
        textPrice.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textPrice.setEnabled(false);

        Label lblPublishDate = new Label(grpSelectedBook, SWT.NONE);
        lblPublishDate.setText("Publish Date");

        final Text textPublishDate = new Text(grpSelectedBook, SWT.BORDER);
        textPublishDate.setLayoutData(
                new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        textPublishDate.setEnabled(false);

        Label lblDiscription = new Label(grpSelectedBook, SWT.NONE);
        lblDiscription.setText("Discription");

        final Text textDisciption = new Text(grpSelectedBook,
                SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        GridData gd_text_8 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
                1);
        gd_text_8.heightHint = 78;
        textDisciption.setLayoutData(gd_text_8);
        textDisciption.setEnabled(false);

        final Button btnEditBook = new Button(grpSelectedBook, SWT.NONE);
        btnEditBook.setText("Update");
        btnEditBook.setEnabled(false);
        btnEditBook.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/arrow_refresh.png"));

        final Button btnAddBook = new Button(grpSelectedBook, SWT.NONE);
        btnAddBook.setText("Add");
        btnAddBook.setEnabled(false);
        btnAddBook.setImage(SWTResourceManager.getImage(FrontendGUI.class,
                "/icons/plugin_add.png"));

        Composite compositeViewOptions = new Composite(sashForm_1, SWT.NONE);
        compositeViewOptions.setLayout(new FillLayout(SWT.HORIZONTAL));

        CTabFolder tabFolderViewOptions = new CTabFolder(compositeViewOptions,
                SWT.BORDER);
        tabFolderViewOptions.setSelectionBackground(Display.getCurrent()
                .getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

        CTabItem tbtmMethodText = new CTabItem(tabFolderViewOptions, SWT.NONE);
        tbtmMethodText.setText("View Text");

        Composite compositeViewText = new Composite(tabFolderViewOptions,
                SWT.NONE);
        tbtmMethodText.setControl(compositeViewText);
        compositeViewText.setLayout(new FillLayout(SWT.HORIZONTAL));

        final Text textAreaView = new Text(compositeViewText,
                SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        textAreaView.setSize(textAreaView.getParent().getSize());
        textAreaView.setEditable(false);

        CTabItem tbtmMethodTree = new CTabItem(tabFolderViewOptions, SWT.NONE);
        tbtmMethodTree.setText("View Tree");

        Composite compositeViewTree = new Composite(tabFolderViewOptions,
                SWT.NONE);
        tbtmMethodTree.setControl(compositeViewTree);
        compositeViewTree.setLayout(new FillLayout(SWT.HORIZONTAL));

        final Tree tree = new Tree(compositeViewTree, SWT.BORDER);
        tree.setVisible(true);
        tree.setLinesVisible(true);
        sashForm_1.setWeights(new int[]
        { 221, 576 });

        ///////////////////////////////////////////////////////////////
        /////
        ///// Actions
        /////
        ///////////////////////////////////////////////////////////////

        btnRead.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                try
                {
                    JAXBEngine jaxbEngine = new JAXBEngine();
                    setBooksCatalog(jaxbEngine
                            .readFile(FrontendGUI.getCurrentFilePath()));
                    genrateCatalogTree(tree, getBooksCatalog());
                    generateBooksCombo(comboBooksList,
                            getBooksCatalog().getBooks());
                    textAreaView.setText("");
                    generateViewTextArea(textAreaView,
                            FrontendGUI.getCurrentFilePath());
                    comboOptions.setEnabled(true);
                    readFileCheck = true;
                } catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found Exception");
                    e.printStackTrace();
                } catch (JAXBException e)
                {
                    System.out.println("JAXBException");
                    e.printStackTrace();
                } catch (IOException e)
                {
                    System.out.println("File Not Found Exception");
                    e.printStackTrace();
                }
            }
        });

        comboOptions.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {

                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("Edit"))
                {
                    textBookID.setText("");
                    textAuthor.setText("");
                    textGenre.setText("");
                    textPrice.setText("");
                    textPublishDate.setText("");
                    textTitle.setText("");
                    textDisciption.setText("");
                    textBookID.setEnabled(true);
                    textAuthor.setEnabled(true);
                    textGenre.setEnabled(true);
                    textPrice.setEnabled(true);
                    textPublishDate.setEnabled(true);
                    textTitle.setEnabled(true);
                    textDisciption.setEnabled(true);
                    btnEditBook.setText("Edit");
                    btnAddBook.setEnabled(false);
                    btnEditBook.setEnabled(false);
                    if (readFileCheck)
                        comboBooksList.setEnabled(true);
                }
                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("New Book"))
                {
                    textBookID.setText("");
                    textAuthor.setText("");
                    textGenre.setText("");
                    textPrice.setText("");
                    textPublishDate.setText("");
                    textTitle.setText("");
                    textDisciption.setText("");
                    textBookID.setEnabled(true);
                    textAuthor.setEnabled(true);
                    textGenre.setEnabled(true);
                    textPrice.setEnabled(true);
                    textPublishDate.setEnabled(true);
                    textTitle.setEnabled(true);
                    textDisciption.setEnabled(true);
                    btnAddBook.setEnabled(true);
                    btnEditBook.setEnabled(false);
                    comboBooksList.setEnabled(false);
                }
                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("Delete Book"))
                {
                    textBookID.setText("");
                    textAuthor.setText("");
                    textGenre.setText("");
                    textPrice.setText("");
                    textPublishDate.setText("");
                    textTitle.setText("");
                    textDisciption.setText("");
                    textBookID.setEnabled(true);
                    textAuthor.setEnabled(true);
                    textGenre.setEnabled(true);
                    textPrice.setEnabled(true);
                    textPublishDate.setEnabled(true);
                    textTitle.setEnabled(true);
                    textDisciption.setEnabled(true);
                    btnEditBook.setText("Delete");
                    btnAddBook.setEnabled(false);
                    btnEditBook.setEnabled(false);
                    if (readFileCheck)
                        comboBooksList.setEnabled(true);
                }
            }
        });

        comboBooksList.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("Edit")
                        || comboOptions
                                .getItem(comboOptions.getSelectionIndex())
                                .equals("Delete Book"))
                {
                    if (!comboBooksList
                            .getItem(comboBooksList.getSelectionIndex())
                            .equals("Select Book"))
                    {
                        List<Book> books = booksCatalog.getBooks();
                        for (int i = 0; i < books.size(); i++)
                        {
                            Book selectedBook = books.get(i);
                            if (comboBooksList
                                    .getItem(comboBooksList.getSelectionIndex())
                                    .equals(selectedBook.getId()))
                            {
                                textBookID.setText(selectedBook.getId());
                                textAuthor.setText(selectedBook.getAuthor());
                                textGenre.setText(selectedBook.getGenre());
                                textPrice.setText(selectedBook.getPrice());
                                textPublishDate.setText(
                                        selectedBook.getPublish_Date());
                                textTitle.setText(selectedBook.getTitle());
                                textDisciption
                                        .setText(selectedBook.getDescription());
                                tltmSaveFile.setEnabled(true);
                                btnEditBook.setEnabled(true);
                                btnAddBook.setEnabled(false);
                                textBookID.setEnabled(true);
                                textAuthor.setEnabled(true);
                                textGenre.setEnabled(true);
                                textPrice.setEnabled(true);
                                textPublishDate.setEnabled(true);
                                textTitle.setEnabled(true);
                                textDisciption.setEnabled(true);
                            }
                        }
                    }
                }
                if (comboBooksList.getItem(comboBooksList.getSelectionIndex())
                        .equals("New Book"))
                {
                    textBookID.setText("");
                    textAuthor.setText("");
                    textGenre.setText("");
                    textPrice.setText("");
                    textPublishDate.setText("");
                    textTitle.setText("");
                    textDisciption.setText("");
                    textBookID.setEnabled(true);
                    textAuthor.setEnabled(true);
                    textGenre.setEnabled(true);
                    textPrice.setEnabled(true);
                    textPublishDate.setEnabled(true);
                    textTitle.setEnabled(true);
                    textDisciption.setEnabled(true);
                }
            }

        });

        tltmUpdateCatalog.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                genrateCatalogTree(tree, getBooksCatalog());
                generateBooksCombo(comboBooksList,
                        getBooksCatalog().getBooks());
                try
                {
                    JAXBEngine jaxbEngine = new JAXBEngine();
                    jaxbEngine
                            .writeFile(booksCatalog,
                                    Paths.get(FrontendGUI.getCurrentFilePath()
                                            .toString()
                                            .substring(0, FrontendGUI
                                                    .getCurrentFilePath()
                                                    .toString().length())));
                    generateViewTextArea(textAreaView,
                            Paths.get(
                                    FrontendGUI.getCurrentFilePath().toString()
                                            .substring(0, FrontendGUI
                                                    .getCurrentFilePath()
                                                    .toString().length())));
                    tltmSaveFile.setEnabled(true);
                    textBookID.setEnabled(false);
                    textAuthor.setEnabled(false);
                    textGenre.setEnabled(false);
                    textPrice.setEnabled(false);
                    textPublishDate.setEnabled(false);
                    textTitle.setEnabled(false);
                    textDisciption.setEnabled(false);
                } catch (JAXBException | IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        tltmSaveFile.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                try
                {
                    JAXBEngine jaxbEngine = new JAXBEngine();
                    jaxbEngine.writeFile(booksCatalog,
                            Paths.get(FrontendGUI.getCurrentFilePath()
                                    .toString().substring(0,
                                            FrontendGUI.getCurrentFilePath()
                                                    .toString().length() - 4)
                                    + "-JAXB-Method.xml"));
                } catch (JAXBException e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnAddBook.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                String id = "" + textBookID.getText();
                String author = "" + textAuthor.getText();
                String title = "" + textTitle.getText();
                String genre = "" + textGenre.getText();
                String date = "" + textPublishDate.getText();
                String price = "" + textPublishDate.getText();
                String description = "" + textDisciption.getText();

                Book newBook = new Book();
                newBook.setId(id);
                newBook.setAuthor(author);
                newBook.setTitle(title);
                newBook.setGenre(genre);
                newBook.setPrice(price);
                newBook.setPublish_Date(date);
                newBook.setDescription(description);

                booksCatalog.addBook(newBook);
                btnAddBook.setEnabled(false);
                btnEditBook.setEnabled(false);
                tltmUpdateCatalog.setEnabled(true);
            }
        });

        btnEditBook.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("Edit"))
                {
                    if (!comboBooksList
                            .getItem(comboBooksList.getSelectionIndex()).equals(
                                    "Select Book")
                            || !comboBooksList
                                    .getItem(comboBooksList.getSelectionIndex())
                                    .equals(""))
                    {
                        Book selectedBook = booksCatalog
                                .getBookbyID(comboBooksList.getItem(
                                        comboBooksList.getSelectionIndex()));

                        String id = "" + textBookID.getText();
                        String author = "" + textAuthor.getText();
                        String title = "" + textTitle.getText();
                        String genre = "" + textGenre.getText();
                        String date = "" + textPublishDate.getText();
                        String price = "" + textPrice.getText();
                        String description = "" + textDisciption.getText();

                        selectedBook.setId(id);
                        selectedBook.setAuthor(author);
                        selectedBook.setTitle(title);
                        selectedBook.setGenre(genre);
                        selectedBook.setPrice(price);
                        selectedBook.setPublish_Date(date);
                        selectedBook.setDescription(description);

                        int index = booksCatalog.getBooks()
                                .indexOf(selectedBook);
                        booksCatalog.getBooks().set(index, selectedBook);
                        btnEditBook.setEnabled(false);
                        tltmUpdateCatalog.setEnabled(true);
                    } else
                    {
                        ProjectAccessHandler.messageBox("XML-Editor",
                                "Please, Select a Book from the List to Edit");
                    }
                }
                if (comboOptions.getItem(comboOptions.getSelectionIndex())
                        .equals("Delete Book"))
                {
                    if (!comboBooksList
                            .getItem(comboBooksList.getSelectionIndex()).equals(
                                    "Select Book")
                            || !comboBooksList
                                    .getItem(comboBooksList.getSelectionIndex())
                                    .equals(""))
                    {
                        Book selectedBook = booksCatalog
                                .getBookbyID(comboBooksList.getItem(
                                        comboBooksList.getSelectionIndex()));
                        booksCatalog.getBooks().remove(selectedBook);
                        textBookID.setText("");
                        textAuthor.setText("");
                        textGenre.setText("");
                        textPrice.setText("");
                        textPublishDate.setText("");
                        textTitle.setText("");
                        textDisciption.setText("");
                        btnEditBook.setEnabled(false);
                        tltmUpdateCatalog.setEnabled(true);
                    } else
                    {
                        ProjectAccessHandler.messageBox("XML-Editor",
                                "Please, Select a Book from the List to Delete");
                    }
                }
            }
        });
    }

    /**
     * @return the booksCatalog
     */
    public static Catalog getBooksCatalog()
    {
        return booksCatalog;
    }

    /**
     * @param booksCatalog
     *            the booksCatalog to set
     */
    public static void setBooksCatalog(Catalog booksCatalog)
    {
        JAXBMethodBuilder.booksCatalog = booksCatalog;
    }

    /**
     * @param tree
     * @param books
     */
    private static void genrateCatalogTree(Tree tree, Catalog catalog)
    {
        tree.removeAll();
        TreeItem trtmNewTreeitem;
        TreeItem trtmNewTreeitemLvl2;
        TreeItem trtmNewTreeitemLvl3;

        List<Book> booksCatlaog = catalog.getBooks();

        trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem.setText("Catalog");

        for (int i = 0; i < booksCatlaog.size(); i++)
        {
            Book currentBook = booksCatlaog.get(i);
            trtmNewTreeitemLvl2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
            trtmNewTreeitemLvl2.setText("BookID: " + currentBook.getId());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3.setText("Author: " + currentBook.getAuthor());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3.setText("Title: " + currentBook.getTitle());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3.setText("Genre: " + currentBook.getGenre());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3.setText("Price: " + currentBook.getPrice());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3
                    .setText("Publish Date: " + currentBook.getPublish_Date());
            trtmNewTreeitemLvl3 = new TreeItem(trtmNewTreeitemLvl2, SWT.NONE);
            trtmNewTreeitemLvl3
                    .setText("Description: " + currentBook.getDescription());
        }
    }

    /**
     * @param combo
     * @param books
     */
    private static void generateBooksCombo(Combo combo, List<Book> books)
    {
        combo.removeAll();
        combo.setText("Select Book");
        for (int i = 0; i < books.size(); i++)
        {
            combo.add(books.get(i).getId());
        }
    }

    public static void generateViewTextArea(Text textarea, Path filePath)
            throws IOException
    {

        textarea.setText("");
        List<String> text = readXMLFile(filePath);

        for (int i = 0; i < text.size(); i++)
        {
            textarea.insert(text.get(i) + "\n");
        }
    }

    /**
     * @param filePath
     * @throws IOException
     */
    private static List<String> readXMLFile(Path filePath) throws IOException
    {
        File xmlFile = new File(filePath.toString());
        FileInputStream fis = new FileInputStream(xmlFile);

        // Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        List<String> lineList = new ArrayList<String>();
        while ((line = br.readLine()) != null)
        {
            lineList.add(line);
        }
        br.close();
        return lineList;
    }
}
