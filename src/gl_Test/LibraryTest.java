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
    // Add book unit test
    @Test
    public void addBook() {
        // Create variable
        Book book = new Book();
        List<Book> books = new ArrayList<>();

        // Add data to book
        book.setUuid(UUID.randomUUID());
        book.setBookName("Sword Art Online");
        book.setBookCategory(BookCategory.valueOf("Novel"));
        book.setBookAuthor("Reki Kawagura");
        book.setBookabstract("Death Game");
        book.setBookStatus(BookStatus.Available);

        // Add book to book list
        books.add(book);

        // Assert check
        Assert.assertEquals(books.get(0).getBookName(), "Sword Art Online");
        assertNull(books.get(0).getBookCode());
//        Assert.assertEquals(books.get(0).getBookCode(), null);
        Assert.assertEquals(books.get(0).getBookCategory(), BookCategory.Novel);
        Assert.assertEquals(books.get(0).getBookStatus(), BookStatus.Available);
        Assert.assertEquals(books.get(0).getBookabstract(), "Death Game");
        assertSame(books.get(0).getUuid(), books.get(0).getUuid());
    }

    // Remove book unit test
    @Test
    public void removeBook() {
        // Create variable
        Book book = new Book();
        List<Book> books = new ArrayList<>();

        // Add data to book
        book.setUuid(UUID.randomUUID());
        book.setBookCode("1");

        // Add book to book list
        books.add(book);

        // Remove data from book list
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals("1")) {
                iterator.remove();
                // Condition check book code if same as input -> continue //
            }
        }

        // Assert check
        Assert.assertEquals(books.size(), 0);
    }

    // Sort book name
    @Test
    public void sortBookName() {
        // Create variable
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();

        // Add data to book
        book1.setBookName("b");
        book2.setBookName("a");
        book3.setBookName("c");

        // Add book to book list
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // Sort data
        books.sort(Book.bookNameCompare);
//        Collections.sort(books, Book.bookNameCompare);

        // Assert check
        Assert.assertEquals(books.get(0).getBookName(), "a");
        Assert.assertEquals(books.get(1).getBookName(), "b");
        Assert.assertEquals(books.get(2).getBookName(), "c");
    }

    // Sort book category
    @Test
    public void sortBookCategory() {
        // Create variable
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();

        // Add data to book
        book1.setBookCategory(BookCategory.Comic);
        book2.setBookCategory(BookCategory.Newspaper);
        book3.setBookCategory(BookCategory.Magazine);

        // Add book to book list
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // Sort data
//        books.sort(Book.bookCategoryCompare);
        Collections.sort(books, Book.bookCategoryCompare);

        // Assert check
        Assert.assertEquals(books.get(0).getBookCategory(), BookCategory.Comic);
        Assert.assertEquals(books.get(1).getBookCategory(), BookCategory.Magazine);
        Assert.assertEquals(books.get(2).getBookCategory(), BookCategory.Newspaper);
    }

    // Sort book code
    @Test
    public void sortBookCode() {
        // Create variable
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        List<Book> books = new ArrayList<>();

        // Add data to book
        book1.setBookCode("b0001");
        book2.setBookCode("a0001");
        book3.setBookCode("a0002");

        // Add book to book list
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // Sort data
        books.sort(Book.bookCodeCompare);
//        Collections.sort(books, Book.bookNameCompare);

        // Assert check
        Assert.assertEquals(books.get(0).getBookCode(), "a0001");
        Assert.assertEquals(books.get(1).getBookCode(), "a0002");
        Assert.assertEquals(books.get(2).getBookCode(), "b0001");
    }

    // Sort book status
    @Test
    public void sortBookStatus() {
        // Create variable
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        // Add data to book
        book1.setBookStatus(BookStatus.Available);
        book2.setBookStatus(BookStatus.Unvailable);
        book3.setBookStatus(BookStatus.Available);

        // Add book to book list
        List<Book> books = Arrays.asList(book1, book2, book3);

        // Sort data
        books.sort(Book.bookStatusCompare);
//        Collections.sort(books, Book.bookNameCompare);

        // Assert check
        Assert.assertEquals(books.get(0).getBookStatus(), BookStatus.Available);
        Assert.assertEquals(books.get(1).getBookStatus(), BookStatus.Unvailable);
        Assert.assertEquals(books.get(2).getBookStatus(), BookStatus.Available);
    }
}