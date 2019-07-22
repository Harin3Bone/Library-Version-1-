package gl_Repository;

import gl_Enum.BookCategory;
import gl_Object.Book;
import gl_Enum.BookStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookList {
    // Create variable
    private List<Book> books = new ArrayList<>();

    // Getter & Setter
    public List<Book> getBooks() {

        return books;
    }

    public void setBooks(List<Book> books) {

        this.books = books;
    }

    // Example book data
    public void DataBookList(BookList books) {
        books.getBooks().add(new Book(UUID.randomUUID(), "Iron Man", BookCategory.Comic, "A0001", "Stan Lee",
                "-", BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(), "Hulk", BookCategory.Novel, "B0001", "Stan Lee",
                "-", BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(), "Hawke Eye", BookCategory.Magazine, "C0001", "Stan Lee",
                "-", BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(), "Thr Avenger", BookCategory.Newspaper, "D0002", "Stan Lee",
                "-", BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(), "Captain America", BookCategory.Comic, "A0002", "Stan Lee",
                "-", BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(), "Black Widow", BookCategory.Novel, "B0002", "Stan Lee",
                "-", BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(), "Thor", BookCategory.Comic, "A0003", "Stan Lee",
                "-", BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(), "Arthur", BookCategory.Magazine, "C0002", "Stan Lee",
                "-", BookStatus.Wait_Approve));
        books.getBooks().add(new Book(UUID.randomUUID(), "Doctor Strange", BookCategory.Newspaper, "D0001", "Stan Lee",
                "-", BookStatus.Unvailable));

    }
}
