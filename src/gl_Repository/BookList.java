package gl_Repository;

import gl_Object.Book;
import gl_Enum.BookStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookList {
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {

        return books;
    }

    public void setBooks(List<Book> books) {

        this.books = books;
    }

    //************************** Book List **************************//
    public void DataBookList(BookList books){
        books.getBooks().add(new Book(UUID.randomUUID(),"Iron Man","Comic","A0001","Stan Lee",
                "-",BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(),"Hulk","Novel","B0001","Stan Lee",
                "-",BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(),"Hawke Eye","Magazine","C0001","Stan Lee",
                "-",BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(),"Thr Avenger","Newspaper","D0002","Stan Lee",
                "-",BookStatus.Available));
        books.getBooks().add(new Book(UUID.randomUUID(),"Captain America","Comic","A0002","Stan Lee",
                "-",BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(),"Black Widow","Novel","B0002","Stan Lee",
                "-",BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(),"Thor","Comic","A0003","Stan Lee",
                "-",BookStatus.Unvailable));
        books.getBooks().add(new Book(UUID.randomUUID(),"Arthur","Magazine","C0002","Stan Lee",
                "-",BookStatus.Wait_Approve));
        books.getBooks().add(new Book(UUID.randomUUID(),"Doctor Strange","Newspaper","D0001","Stan Lee",
                "-",BookStatus.Unvailable));

    }
}
