package gl_Library;

import gl_Enum.BookCategory;
import gl_Enum.BookSituation;
import gl_Enum.BookStatus;
import gl_Object.Book;
import gl_Object.History;
import gl_Service.LibraryService;
import gl_View.LibraryScreen;
import gl_View.RegisterScreen;

import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class Library {
    private static InputParser inputParser = new InputParser();                         // Create input parser to throw back input function //
    private static LibraryService service = LibraryService.getInstance();               // Create service to use all of list //

    // Librarian Function
    // Add book
    public static void addBook() {
        // Create variable
        Book book = new Book();

        // Get data from LibraryScreen,addDisplay() function
        String[] value = LibraryScreen.addDisplay();

        // Automatic generate book code
        try {
            String code = LibraryScreen.GenerateCode(value[1]);
            book.setBookCode(code);
        } catch (IllegalArgumentException ignored) {
            System.out.println("Error, your category doesn't exist");
            LibraryScreen.SessionCheck(null);
        }

        // Add data to book
        book.setUuid(UUID.randomUUID());
        book.setBookName(value[0]);
        book.setBookCategory(BookCategory.valueOf(value[1]));
        book.setBookAuthor(value[2]);
        book.setBookabstract(value[3]);
        book.setBookStatus(BookStatus.Available);

        // Add book to book list
        service.getBooksService().getBooks().add(book);

        // Show all book in book list
        checkBook();
    }

    // Remove book
    public static void removeBook() {
        // Create variable
        Book book = new Book();

        // Get data from LibrarianScreen.removeDisplay() function
        String id = LibraryScreen.removeDisplay();

        // Remove function
        Iterator<Book> iterator = service.getBooksService().getBooks().iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals(id)) {
                // Condition check book code if same as input -> continue //
                // when condition is true -> remove 1 record //
                iterator.remove();
                System.out.println("Book code : " + book.getBookCode() + " remove successful");
                checkBook();

            } else {
                if (book.getBookCode().equalsIgnoreCase(id)) {
                    System.out.println("Sorry your book code is not exist");
                    LibraryScreen.SessionCheck(null);
                }
            }
        }
    }

    // Search book menu display
    public static void searchBook() {
        // Boolean it use for check it found book name or not. If not use boolean and choose else then will occur some problem
        boolean Found = false;

        switch (LibraryScreen.searchDisplay()) {
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

    // Search book name
    private static Boolean SearchByName(Boolean Found) {
        // Get data from LibraryScreen.searchNameDisplay()
        String name = LibraryScreen.searchNameDisplay();

        // Display book
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookName().equalsIgnoreCase(name)) {
                Found = true;
                service.setBookDetail(book);
                LibraryScreen.searchShow();
            }
        }
        return Found;
    }

    // Search book category
    private static Boolean SearchByCategory(Boolean Found) {
        // Get data from LibraryScreen.searchCategoryDisplay();
        String category = LibraryScreen.searchCategoryDisplay();

        // Display book
        try {
            for (Book book : service.getBooksService().getBooks()) {
                if (book.getBookCategory().equals(BookCategory.valueOf(category))) {
                    Found = true;
                    service.setBookDetail(book);
                    LibraryScreen.searchShow();
                }
            }
        } catch (IllegalArgumentException ignored) {

        }
        return Found;
    }

    // Search book code
    private static Boolean SearchByCode(Boolean Found) {
        // Get data from LibraryScreen.searchCodeDisplay()
        String code = LibraryScreen.searchCodeDisplay();

        // Display book
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(code)) {
                Found = true;
                service.setBookDetail(book);
                LibraryScreen.searchShow();
            }
        }
        return Found;
    }

    // Display all book in Library
    public static void checkBook() {
        // Display book
        System.out.println("================================");
        for (int i = 0; i < service.getBooksService().getBooks().size(); i++) {
            System.out.println("Book Detail " + (i + 1) + " : " + service.getBooksService().getBooks().get(i));
        }
        LibraryScreen.SessionCheck(null);
    }

    // Display all book history in Library
    public static void historyBook() {
        // Display history
        System.out.println("================================");
        for (int i = 0; i < service.getHistoriesService().getHistories().size(); i++) {
            System.out.println("History Detail " + (i + 1) + " : " + service.getHistoriesService().getHistories().get(i));
        }
        LibraryScreen.SessionCheck(null);
    }

    // Sort book menu display
    public static void sortBook() {
        int ans = LibraryScreen.sortDisplay();
        switch (ans) {
            case 1:
                // Sort by name
                service.getBooksService().getBooks().sort(Book.bookNameCompare);                    // Compare book with book name in book method //
                LibraryScreen.sortShow();
                break;
            case 2:
                // Sort by category
                service.getBooksService().getBooks().sort(Book.bookCategoryCompare);                // Compare book with book category in book method //
                LibraryScreen.sortShow();
                break;
            case 3:
                // Sort by code
                service.getBooksService().getBooks().sort(Book.bookCodeCompare);                    // Compare book with book code in book method //
                LibraryScreen.sortShow();
                break;
            case 4:
                // Sort by status
                service.getBooksService().getBooks().sort(Book.bookStatusCompare);                  // Compare book with book status in book method //
                LibraryScreen.sortShow();
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

    // Confirm book -> select between approve or accept
    public static void confirmBook() {
        String x = LibraryScreen.ConfirmView();
        if (x.equals("1")) {
            ApproveBook();
        } else {
            if (x.equals("2")) {
                AcceptBook();
            }
        }
    }

    // Approve book
    private static void ApproveBook() {
        // Get data form LibraryScreen.ConfirmInput()
        String id = LibraryScreen.ConfirmInput();
        // Approve component
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    Found = true;
                    for (History history : service.getHistoriesService().getHistories()) {
                        // Value set
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Wait_Approve)) {
                            history.setBooksituation(BookSituation.Borrow);
                            history.setDayBorrow(LocalDate.now());
                            history.setDayReturn(LocalDate.now().plusDays(7));
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                        }
                    }
                    // Display book history
                    System.out.println("\nYour work has been successful");
                    historyBook();
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    // Accept book
    private static void AcceptBook() {
        // Get data form LibraryScreen.ConfirmInput()
        String id = LibraryScreen.ConfirmInput();

        // Accept component
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    // Status change
                    book.setBookStatus(BookStatus.Available);
                    for (History history : service.getHistoriesService().getHistories()) {
                        // Value set
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Wait_Accept)) {
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                            history.setBooksituation(BookSituation.Return);
                        }
                    }
                    // Display book history
                    System.out.println("\nYour work has been successful");
                    historyBook();
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    // Change book return date
    public static void changeBook() {
        // Get data form LibraryScreen.ChangeView()
        String id = LibraryScreen.ChangeView();

        // Change book date component
        boolean Found = false;
        for (History history : service.getHistoriesService().getHistories()) {
            if (history.getBookcode().equalsIgnoreCase(id)) {
                Found = true;
                if (history.getBooksituation().equals(BookSituation.Borrow)) {
                    service.setHistoryDetail(history);

                    // When book borrow but not approve -> can't change date return
                    if (history.getDayBorrow() == null || history.getDayReturn() == null) {
                        Found = false;
                    } else {
                        int day = LibraryScreen.ChangeDate();
                        if (DAYS.between(history.getDayBorrow(), history.getDayReturn().plusDays(day)) > 15) {
                            System.out.println("Error, your date are invalid");
                            System.out.println("================================");
                            changeBook();
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

    // Customer Function
    // Borrow book
    public static void borrowBook() {
        // Get data from LibraryScreen.ConfirmInput()
        String id = LibraryScreen.ConfirmInput();
        // Boolean it use for check it found book name or not. If not use boolean and choose else then will occur some problem
        boolean Found = false;
        // Borrow component
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Available)) {
                    // Status set
                    book.setBookStatus(BookStatus.Unvailable);
                    // Customer set
                    service.setBookDetail(book);
                    // History add -> historyForeach use for return book
                    LibraryScreen.HistoryAdd(null);
                    // Display book borrow detail
                    System.out.println("User : " + service.getCustomerDetail().getFirstName());
                    LibraryScreen.searchShow();
                    System.out.println("Your work has been successful\n");
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    // Return book
    public static void returnBook() {
        // Get data from LibraryScreen.ConfirmInput()
        String id = LibraryScreen.ConfirmInput();
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    service.setBookDetail(book);
                    try {
                        for (History history : service.getHistoriesService().getHistories()) {
                            service.setHistoryDetail(history);
                            if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Borrow)) {
                                // Return date check late or early
                                System.out.println("\nUser : " + service.getCustomerDetail().getFirstName());
                                LibraryScreen.DayLateCheck();
                                // History add
                                LibraryScreen.HistoryAdd(history);
                            }
                        }
                    } catch (ConcurrentModificationException ignored) {
                    }
                    // Display book return detail
                    LibraryScreen.searchShow();
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        LibraryScreen.SessionCheck(Found);
    }

    // register function
    // Librarian register section
    public static void librarianRegister() {
        // Get data from RegisterScreen.RegisterInput()
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

        inputParser.register();
    }

    // Customer register section
    public static void customerRegister() {
        // Get data from RegisterScreen.RegisterInput()
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

        inputParser.register();
    }
}
