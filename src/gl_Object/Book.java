package gl_Object;

import gl_Enum.BookStatus;

import java.util.Comparator;
import java.util.UUID;

public class Book {
    private UUID uuid = UUID.randomUUID();
    private String bookName;
    private String bookCategory;
    private String bookCode;
    private String bookAuthor;
    private String bookabstract;
    private BookStatus bookStatus;

    //************************** Getter & Setter **************************//

    public UUID getUuid() {

        return uuid;
    }

    public void setUuid(UUID uuid) {

        this.uuid = uuid;
    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public String getBookCategory() {

        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {

        this.bookCategory = bookCategory;
    }

    public String getBookCode() {

        return bookCode;
    }

    public void setBookCode(String bookCode) {

        this.bookCode = bookCode;
    }

    public String getBookAuthor() {

        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {

        this.bookAuthor = bookAuthor;
    }

    public String getBookabstract() {

        return bookabstract;
    }

    public void setBookabstract(String bookabstract) {

        this.bookabstract = bookabstract;
    }

    public BookStatus getBookStatus() {

        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {

        this.bookStatus = bookStatus;
    }

    //************************** Constructor **************************//
    public Book(){

    }

    public Book(UUID uuid, String bookName, String bookCategory, String bookCode, String bookAuthor, String bookabstract, BookStatus bookStatus) {
        this.uuid = uuid;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookCode = bookCode;
        this.bookAuthor = bookAuthor;
        this.bookabstract = bookabstract;
        this.bookStatus = bookStatus;
    }

    //************************** toString **************************//

    @Override
    public String toString() {
        return "bookName = " + bookName +
                " | bookCategory = " + bookCategory +
                " | bookCode = " + bookCode +
                " | bookAuthor = " + bookAuthor +
                " | bookStatus = " + bookStatus;
    }


    //************************** Comparator **************************//
    public static Comparator<Book> bookNameCompare = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            String BookName1 = o1.getBookName().toUpperCase();
            String BookName2 = o2.getBookName().toUpperCase();
            return BookName1.compareTo(BookName2);
        }
    };

    public static Comparator<Book> bookCategoryCompare = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            String BookCategory1 = o1.getBookCategory().toUpperCase();
            String BookCategory2 = o2.getBookCategory().toUpperCase();
            return BookCategory1.compareTo(BookCategory2);
        }
    };

    public static Comparator<Book> bookCodeCompare = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            String BookCode1 = o1.getBookCode().toUpperCase();
            String BookCode2 = o2.getBookCode().toUpperCase();
            return BookCode1.compareTo(BookCode2);
        }
    };

    public static Comparator<Book> bookStatusCompare = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            BookStatus BookStat1 = o1.getBookStatus();
            BookStatus BookStat2 = o2.getBookStatus();
            return BookStat1.compareTo(BookStat2);
        }
    };

//    public static Comparator<Book> codeCompare = new Comparator<Book>() {
//        @Override
//        public int compare(Book p1, Book p2) {
//            String Page1 = p1.getBookCode().substring(2);
//            String Page2 = p2.getBookCode().substring(2);
//            return Page1.compareTo(Page2);
//        }
//    };

    //====================== Comparator Interger ========================//
//    public static Comparator<Book> bookCodeCompare = new Comparator<Book>() {
//        @Override
//        public int compare(Book o1, Book o2) {
//            int BookCode1 = o1.getCode();
//            int BookCode2 = o2.getCode();
//            return BookCode1-BookCode2;  -> use when compare int
//        }
//    };

}
