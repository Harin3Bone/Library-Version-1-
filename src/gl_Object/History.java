package gl_Object;

import gl_Enum.BookSituation;
import java.time.LocalDate;
import java.util.UUID;

public class History {
    //************************** Attribute Variable **************************//
    private UUID uuid = UUID.randomUUID();
    private String Librarianname;
    private String Customername;
    private String Bookname;
    private String Bookcategory;
    private String Bookcode;
    private String Bookauthor;
    private BookSituation Booksituation;
    private LocalDate DayBorrow;
    private LocalDate DayReturn;

    //************************** Getter & Setter **************************//

    public UUID getUuid() {

        return uuid;
    }

    public void setUuid(UUID uuid) {

        this.uuid = uuid;
    }

    public String getLibrarianname() {

        return Librarianname;
    }

    public void setLibrarianname(String librarianname) {

        Librarianname = librarianname;
    }

    public String getCustomername() {

        return Customername;
    }

    public void setCustomername(String customername) {

        Customername = customername;
    }

    public String getBookname() {

        return Bookname;
    }

    public void setBookname(String bookname) {

        Bookname = bookname;
    }

    public String getBookcategory() {

        return Bookcategory;
    }

    public void setBookcategory(String bookcategory) {

        Bookcategory = bookcategory;
    }

    public String getBookcode() {

        return Bookcode;
    }

    public void setBookcode(String bookcode) {

        Bookcode = bookcode;
    }

    public String getBookauthor() {

        return Bookauthor;
    }

    public void setBookauthor(String bookauthor) {

        Bookauthor = bookauthor;
    }

    public BookSituation getBooksituation() {

        return Booksituation;
    }

    public void setBooksituation(BookSituation booksituation) {

        Booksituation = booksituation;
    }

    public LocalDate getDayBorrow() {

        return DayBorrow;
    }

    public void setDayBorrow(LocalDate dayBorrow) {

        DayBorrow = dayBorrow;
    }

    public LocalDate getDayReturn() {

        return DayReturn;
    }

    public void setDayReturn(LocalDate dayReturn) {

        DayReturn = dayReturn;
    }

    //************************** Constructor **************************//
    public History(){

    }

    public History(UUID uuid, String librarianname, String customername, String bookname, String bookcategory,
                   String bookcode, String bookauthor, BookSituation booksituation, LocalDate dayBorrow, LocalDate dayReturn) {
        this.uuid = uuid;
        Librarianname = librarianname;
        Customername = customername;
        Bookname = bookname;
        Bookcategory = bookcategory;
        Bookcode = bookcode;
        Bookauthor = bookauthor;
        Booksituation = booksituation;
        DayBorrow = dayBorrow;
        DayReturn = dayReturn;
    }
    //************************** toString **************************//

    @Override
    public String toString() {
        return "Librarian = " + Librarianname +
                " | Customer = " + Customername +
                " | Book = " + Bookname +
                " | Category = " + Bookcategory +
                " | Code = " + Bookcode +
                " | Author = " + Bookauthor +
                " | Status = " + Booksituation +
                " | Borrow = " + DayBorrow +
                " | Return = " + DayReturn;
    }
}
