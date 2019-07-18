package gl_Test;

import gl_Enum.BookCategory;
import gl_Enum.BookStatus;
import gl_Object.Book;
import gl_Service.LibraryService;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LibraryTest {
    //******************************** Add Book ********************************//
    @Test
    public void addBook() {
        //**************** Object ****************//
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        //**************** Add Value to Data ****************//
        book.setUuid(UUID.randomUUID());
        book.setBookName("Sword Art Online");
        book.setBookCategory(BookCategory.valueOf("Novel"));
        book.setBookAuthor("Reki Kawagura");
        book.setBookabstract("Death Game");
        book.setBookStatus(BookStatus.Available);
        //**************** Add Data to List ****************//
        books.add(book);
        //**************** Output ****************//
        Assert.assertEquals(books.get(0).getBookName(),"Sword Art Online");
        assertNull(books.get(0).getBookCode());
//        Assert.assertEquals(books.get(0).getBookCode(), null);
        Assert.assertEquals(books.get(0).getBookCategory(), BookCategory.Novel);
        Assert.assertEquals(books.get(0).getBookStatus(), BookStatus.Available);
        Assert.assertEquals(books.get(0).getBookabstract(), "Death Game");
        assertSame(books.get(0).getUuid(),books.get(0).getUuid());
    }
    //******************************** Remove Book ********************************//
    @Test
    public void removeBook() {
        //**************** Object ****************//
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        //**************** Add Value to Data ****************//
        book.setUuid(UUID.randomUUID());
        book.setBookCode("1");
        //**************** Add Data to List ****************//
        books.add(book);
        //**************** Remove Data from List ****************//
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals("1")) {                                        // Condition check book code if same as input -> continue //
                iterator.remove();
            }
        }
        //**************** Output ****************//
        Assert.assertEquals(books.size(), 0);
    }
    //******************************** Sort Book ********************************//
    @Test
    public void sortBookName(){
        //**************** Object ****************//
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();
        //**************** Add Value to Data ****************//
        book1.setBookName("b");
        book2.setBookName("a");
        book3.setBookName("c");
        //**************** Add Data to List ****************//
        books.add(book1);
        books.add(book2);
        books.add(book3);
        //**************** Sort ****************//
        books.sort(Book.bookNameCompare);
//        Collections.sort(books, Book.bookNameCompare);
        //**************** Output ****************//
        Assert.assertEquals(books.get(0).getBookName(),"a");
        Assert.assertEquals(books.get(1).getBookName(),"b");
        Assert.assertEquals(books.get(2).getBookName(),"c");
    }
    //******************************** Sort Book ********************************//
    @Test
    public void sortBookCategory(){
        //**************** Object ****************//
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();
        //**************** Add Value to Data ****************//
        book1.setBookCategory(BookCategory.Comic);
        book2.setBookCategory(BookCategory.Newspaper);
        book3.setBookCategory(BookCategory.Magazine);
        //**************** Add Data to List ****************//
        books.add(book1);
        books.add(book2);
        books.add(book3);
        //**************** Sort ****************//
//        books.sort(Book.bookCategoryCompare);
        Collections.sort(books, Book.bookCategoryCompare);
        //**************** Output ****************//
        Assert.assertEquals(books.get(0).getBookCategory(),BookCategory.Comic);
        Assert.assertEquals(books.get(1).getBookCategory(),BookCategory.Magazine);
        Assert.assertEquals(books.get(2).getBookCategory(),BookCategory.Newspaper);
    }
    //******************************** Sort Book ********************************//
    @Test
    public void sortBookCode(){
        //**************** Object ****************//
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();
        //**************** Add Value to Data ****************//
        book1.setBookCode("b0001");
        book2.setBookCode("a0001");
        book3.setBookCode("a0002");
        //**************** Add Data to List ****************//
        books.add(book1);
        books.add(book2);
        books.add(book3);
        //**************** Sort ****************//
        books.sort(Book.bookCodeCompare);
//        Collections.sort(books, Book.bookNameCompare);
        //**************** Output ****************//
        Assert.assertEquals(books.get(0).getBookCode(),"a0001");
        Assert.assertEquals(books.get(1).getBookCode(),"a0002");
        Assert.assertEquals(books.get(2).getBookCode(),"b0001");
    }
}