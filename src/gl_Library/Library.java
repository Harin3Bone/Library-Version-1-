package gl_Library;

import gl_Enum.BookCategory;
import gl_Object.Book;
import gl_Object.History;

import gl_Enum.BookStatus;
import gl_Enum.BookSituation;

import gl_Service.LibraryService;

import gl_View.LibraryScreen;
import gl_View.RegisterScreen;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class Library {
    private static InputParser inputParser = new InputParser();                         // Create input parser to throw back input function //
    private static LibraryService service = LibraryService.getInstance();               // Create service to use all of list //

    //****************************************** Librarian Function ******************************************//
    //******************************** Add Book ********************************//
    public static void AddBook() {
        //**************** Create Variable ****************//
        Book book = new Book();                                                         // Create book object to get detail //
        String[] value = LibraryScreen.AddView();
        try {
            String code = LibraryScreen.GenerateCode(value[1]);
            book.setBookCode(code);
        } catch (IllegalArgumentException ignored) {
            System.out.println("Error, your category doesn't exist");
            LibraryScreen.SessionCheck(null);
        }
        //**************** Add Data to Variable ****************//
        book.setUuid(UUID.randomUUID());                                                // Random UUID but not show on display //
        book.setBookName(value[0]);                                                     // Set value //
        book.setBookCategory(BookCategory.valueOf(value[1]));
        book.setBookAuthor(value[2]);
        book.setBookabstract(value[3]);
        book.setBookStatus(BookStatus.Available);                                       // Set book status //
        //**************** Add Data to List ****************//
        service.getBooksService().getBooks().add(book);
        //**************** Display ****************//
        CheckBook();
    }

    //******************************** Remove Book ********************************//
    public static void RemoveBook() {
        //**************** Create Variable ****************//
        Book book = new Book();                                                         // Create book to use book iterator //
        String id = LibraryScreen.RemoveView();
        //**************** Remove Component ****************//
        Iterator<Book> iterator = service.getBooksService().getBooks().iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals(id)) {                                        // Condition check book code if same as input -> continue //
                iterator.remove();                                                      // when condition is true -> remove 1 record //
                System.out.println("Book code : " + book.getBookCode() + " remove successful");
                CheckBook();
            } else {
                if (book.getBookCode().equalsIgnoreCase(id)) {
                    System.out.println("Sorry your book code is not exist");
                    LibraryScreen.SessionCheck(null);
                }
            }
        }
    }

    //******************************** Search Book ********************************//
    public static void SearchBook() {
        boolean Found = false;
        // Boolean it use for check it found book name or not. If not use boolean and choose else then will occur some problem //
        switch (LibraryScreen.SearchExtension()) {
            case "1":
                Found = SearchByName(false);
                break;
            case "2":
                Found = SearchByCategory(false);
                break;
            case "3":
                Found = SearchByCode(false);
                break;
        }
        LibraryScreen.SessionCheck(Found);
    }

    //**************** By Name ****************//
    private static Boolean SearchByName(Boolean Found) {
        String name = LibraryScreen.SearchBookName();
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookName().equalsIgnoreCase(name)) {
                Found = true;
                service.setBookDetail(book);
                LibraryScreen.SearchDisplay();
            }
        }
        return Found;
    }

    //**************** By Category ****************//
    private static Boolean SearchByCategory(Boolean Found) {
        String category = LibraryScreen.SearchBookCategory();
        //**************** Display Search ****************//
        try {
            for (Book book : service.getBooksService().getBooks()) {
                if (book.getBookCategory().equals(BookCategory.valueOf(category))) {
                    Found = true;
                    service.setBookDetail(book);
                    LibraryScreen.SearchDisplay();
                }
            }
        } catch (IllegalArgumentException ignored) {

        }
        return Found;
    }

    //**************** By Code ****************//
    private static Boolean SearchByCode(Boolean Found) {
        String code = LibraryScreen.SearchBookCode();
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(code)) {
                Found = true;
                service.setBookDetail(book);
                LibraryScreen.SearchDisplay();
            }
        }
        return Found;
    }

    //******************************** Check Book ********************************//
    public static void CheckBook() {
        //**************** Display List Book ****************//
        System.out.println("================================");
        for (int i = 0; i < service.getBooksService().getBooks().size(); i++) {
            System.out.println("Book Detail " + (i + 1) + " : " + service.getBooksService().getBooks().get(i));
        }
        LibraryScreen.SessionCheck(null);
    }

    //******************************** History Book ********************************//
    public static void HistoryBook() {
        //**************** Display List Book ****************//
        System.out.println("================================");
        for (int i = 0; i < service.getHistoriesService().getHistories().size(); i++) {
            System.out.println("History Detail " + (i + 1) + " : " + service.getHistoriesService().getHistories().get(i));
        }
        LibraryScreen.SessionCheck(null);
    }

    //******************************** Sort Book ********************************//
    public static void SortBook() {
        int ans = LibraryScreen.SortView();
        switch (ans) {
            case 1:
                service.getBooksService().getBooks().sort(Book.bookNameCompare);                    // Compare book with book name in book method //
                LibraryScreen.SortDisplay();
                break;
            case 2:
                service.getBooksService().getBooks().sort(Book.bookCategoryCompare);                // Compare book with book category in book method //
                LibraryScreen.SortDisplay();
                break;
            case 3:
                service.getBooksService().getBooks().sort(Book.bookCodeCompare);                    // Compare book with book code in book method //
                LibraryScreen.SortDisplay();
                break;
            case 4:
                service.getBooksService().getBooks().sort(Book.bookStatusCompare);                  // Compare book with book status in book method //
                LibraryScreen.SortDisplay();
                break;
            default:
        }
        // Can use collection replace sort //
//        Collections.sort(service.getBooksService().getBooks(), Book.bookNameCompare);
//        Collections.sort(service.getBooksService().getBooks(), Book.bookCategoryCompare);
//        Collections.sort(service.getBooksService().getBooks(), Book.bookCodeCompare);
//        Collections.sort(service.getBooksService().getBooks(), Book.bookStatusCompare);

        LibraryScreen.SessionCheck(null);
    }

    //****************************************** Confirm Book ******************************************//
    public static void ConfirmBook() {
        if (LibraryScreen.ConfirmView().equals("1")) {
            ApproveBook();
        } else {
            if (LibraryScreen.ConfirmView().equals("2")) {
                AcceptBook();
            }
        }
    }

    //******************************** Approve Book ********************************//
    private static void ApproveBook() {
        //**************** Scanner Input ****************//
        String id = LibraryScreen.ConfirmInput();
        //**************** Approve Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                if (book.getBookStatus().equals(BookStatus.Wait_Approve)) {
                    Found = true;
                    //**************** Status Change ****************//
                    book.setBookStatus(BookStatus.Unvailable);
                    for (History history : service.getHistoriesService().getHistories()) {
                        //**************** Value Set ****************//
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Borrow)) {
                            history.setDayBorrow(LocalDate.now());
                            history.setDayReturn(LocalDate.now().plusDays(7));
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                        }
                    }
                    //**************** Display ****************//
                    System.out.println("\nYour work has been successful");
                    HistoryBook();
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    //******************************** Accept Book ********************************//
    private static void AcceptBook() {
        String id = LibraryScreen.ConfirmInput();
        //**************** Accept Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Wait_Accept)) {
                    book.setBookStatus(BookStatus.Available);
                    for (History history : service.getHistoriesService().getHistories()) {
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Return)) {
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                        }
                    }
                    //**************** Display ****************//
                    System.out.println("\nYour work has been successful");
                    HistoryBook();
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    //******************************** Change Book ********************************//
    public static void ChangeBook() {
        String id = LibraryScreen.ChangeView();
        //**************** Change Component ****************//
        boolean Found = false;
        for (History history : service.getHistoriesService().getHistories()) {
            if (history.getBookcode().equalsIgnoreCase(id)) {
                Found = true;
                if (history.getBooksituation().equals(BookSituation.Borrow)) {
                    service.setHistoryDetail(history);
                    if (service.getCustomerDetail() != null) {
                        LibraryScreen.HistoryCheck();
                    }
                    if (history.getDayBorrow() == null || history.getDayReturn() == null) {
                        Found = false;
                    } else {
                        int day = LibraryScreen.ChangeDate();
                        if (DAYS.between(history.getDayBorrow(), history.getDayReturn().plusDays(day)) > 15) {
                            System.out.println("Error, your date are invalid");
                            System.out.println("================================");
                            ChangeBook();
                        }
                        history.setDayReturn(history.getDayReturn().plusDays(day));
                        System.out.println("Your work has been successful");
                    }
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    //****************************************** Customer Function ******************************************//
    //******************************** Borrow Book ********************************//
    public static void BorrowBook() {
        String id = LibraryScreen.ConfirmInput();
        boolean Found = false;
        //**************** Borrow Component ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Available)) {
                    //**************** Status Set ****************//
                    book.setBookStatus(BookStatus.Wait_Approve);
                    //**************** Customer Set ****************//
                    service.setBookDetail(book);
                    LibraryScreen.HistoryAdd(null);
                    //**************** Display ****************//
                    System.out.println("User : " + service.getCustomerDetail().getFirstName());
                    LibraryScreen.SearchDisplay();
                    System.out.println("Your work has been successful\n");
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    //******************************** Return Book ********************************//
    public static void ReturnBook() {
        String id = LibraryScreen.ConfirmInput();
        //**************** Return Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    service.setBookDetail(book);
                    //**************** Customer Check ****************//
                    try {
                        for (History history : service.getHistoriesService().getHistories()) {
                            service.setHistoryDetail(history);
                            if (history.getBookcode().equals(id)) {
                                if (history.getBooksituation().equals(BookSituation.Borrow)) {
                                    LibraryScreen.HistoryCheck();
                                    // **************** Date Check **************** //
                                    System.out.println("\nUser : " + service.getCustomerDetail().getFirstName());
                                    int x = (int) DAYS.between(history.getDayReturn(), LocalDate.now());
                                    if (x > 0) {
                                        System.out.println("" + service.getCustomerDetail().getFirstName() + ", You return book late " + x + " day(s)");
                                    }
                                    //**************** Add Data to history ****************//
                                    book.setBookStatus(BookStatus.Wait_Accept);
                                    LibraryScreen.HistoryAdd(history);
                                }
                            }

                        }
                    } catch (ConcurrentModificationException ignored) {

                    }
                    //**************** Display ****************//
                    LibraryScreen.SearchDisplay();
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }

        LibraryScreen.SessionCheck(Found);
    }

    //****************************************** Register Function ******************************************//
    //******************************** Librarian ********************************//
    public static void Librarian_Register() {
        //**************** Create Variable ****************//
        String[] account = RegisterScreen.RegisterInput();
        try {
            RegisterScreen.DataCheck(account, Boolean.TRUE);

        } catch (ConcurrentModificationException ignored) {

        }
        RegisterScreen.DataAdd(account, Boolean.TRUE);

//        //**************** Display ****************//
//        for (int i = 0; i < service.getLibrariansService().getLibrarians().size(); i++) {
//            System.out.println("Librarian Detail " + (i + 1) + " : " + service.getLibrariansService().getLibrarians().get(i));
//        }
//        System.out.println("=====================");

        inputParser.Register();
    }

    //******************************** Customer ********************************//
    public static void Customer_Register() {
        //**************** Create Variable ****************//
        String[] account = RegisterScreen.RegisterInput();
        try {
            RegisterScreen.DataCheck(account, Boolean.FALSE);

        } catch (ConcurrentModificationException ignored) {

        }
        RegisterScreen.DataAdd(account, Boolean.FALSE);

//        //**************** Display ****************//
//        for (int i = 0; i < service.getCustomersService().getCustomers().size(); i++) {
//            System.out.println("Customer Detail " + (i + 1) + " : " + service.getCustomersService().getCustomers().get(i));
//        }
//        System.out.println("==========================");

        inputParser.Register();
    }
}
