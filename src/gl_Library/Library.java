package gl_Library;

import gl_Object.Customer;
import gl_Object.Librarian;
import gl_Object.Book;
import gl_Object.History;

import gl_Enum.BookCategory;
import gl_Enum.BookStatus;
import gl_Enum.BookSituation;
import gl_Service.LibraryService;
import gl_View.LibraryScreen;
import gl_View.RegisterScreen;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Library {
    private static InputParser inputParser = new InputParser();                     // Create input parser to throw back input function //
    private static LibraryService service = LibraryService.getInstance();           // Create service to use all of list //

    //****************************************** Librarian Function ******************************************//
    //******************************** Add Book ********************************//
    public static void AddBook() {
        //**************** Create Variable ****************//
        Book book = new Book();                                                     // Create book object to get detail //
        String[] value = LibraryScreen.AddView();
        //**************** Add Data to Variable ****************//
        book.setUuid(UUID.randomUUID());                                            // Random UUID but not show on display //
        book.setBookName(value[0]);                                                 // Set value //
        book.setBookCategory(value[1]);
        book.setBookAuthor(value[2]);
        book.setBookabstract(value[3]);
        book.setBookCode(value[4]);
        book.setBookStatus(BookStatus.Available);                                   // Set book status //
        //**************** Add Data to List ****************//
        service.getBooksService().getBooks().add(book);
        //**************** Display ****************//
        CheckBook();
    }

    //******************************** Remove Book ********************************//
    public static void RemoveBook() {
        //**************** Create Variable ****************//
        Book book = new Book();                                                     // Create book to use book iterator //
        Scanner rem = new Scanner(System.in);                                       // Create scanner to get input //
        //**************** Input Scanner ****************//
        System.out.println("Please enter book code to remove : ");
        String remid = rem.nextLine();
        //**************** Remove Component ****************//
        Iterator<Book> iterator = service.getBooksService().getBooks().iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals(remid)) {                                 // Condition check book code if same as input -> continue //
                iterator.remove();                                                  // when condition is true -> remove 1 record //
                System.out.println("Book code : " + book.getBookCode() + " remove successful");
                CheckBook();
            } else {
                if (book.getBookCode().equalsIgnoreCase(remid)) {
                    System.out.println("Sorry your book code is not exist");
                    inputParser.Admin_Login();
                }
            }
        }
    }

    //******************************** Search Book ********************************//
    //**************** By Name ****************//
    public static void SearchByName() {
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your book name to search : ");
        String name = search.nextLine();                                            // Boolean it use for check it found book name or not //
        boolean Found = false;                                                      // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookName().equalsIgnoreCase(name)) {
                Found = true;
                LibraryScreen.SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        inputParser.Admin_Login();
    }

    //**************** By Category ****************//
    public static void SearchByCategory() {
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your category name to search : ");
        String cate = search.nextLine();                                            // Boolean it use for check it found book name or not //
        boolean Found = false;                                                      // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCategory().equalsIgnoreCase(cate)) {
                Found = true;
                LibraryScreen.SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        inputParser.Admin_Login();
    }

    //**************** By Code ****************//
    public static void SearchByCode() {
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your book code to search : ");
        String id = search.nextLine();                                              // Boolean it use for check it found book name or not //
        boolean Found = false;                                                      // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                LibraryScreen.SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        inputParser.Admin_Login();
    }

    //******************************** Check Book ********************************//
    public static void CheckBook() {
        //**************** Display List Book ****************//
        System.out.println("=====================");
        for (int i = 0; i < service.getBooksService().getBooks().size(); i++) {
            System.out.println("Book Detail " + (i + 1) + " : " + service.getBooksService().getBooks().get(i));
        }
        if (service.getCustomerDetail() == null) {
            inputParser.Admin_Login();
        } else {
            inputParser.User_Login();
        }
    }

    //******************************** History Book ********************************//
    public static void HistoryBook() {
        //**************** Display List Book ****************//
        System.out.println("=====================");
        for (int i = 0; i < service.getHistoriesService().getHistories().size(); i++) {
            System.out.println("History Detail " + (i + 1) + " : " + service.getHistoriesService().getHistories().get(i));
        }
        inputParser.Admin_Login();
    }

    //******************************** Sort Book ********************************//
    public static void SortBook() {
        int ans_sort = LibraryScreen.SortView();
        switch (ans_sort) {
            case 1:
                service.getBooksService().getBooks().sort(Book.bookNameCompare);                    // Compare book with book name in book method //
//                Collections.sort(service.getBooksService().getBooks(), Book.bookNameCompare);     // Can use collection replace sort //
                for (Book bnsort : service.getBooksService().getBooks()) {
                    System.out.println(bnsort);                                                     // Display book after sorting //
                }
                break;
            case 2:
                service.getBooksService().getBooks().sort(Book.bookCategoryCompare);                // Compare book with book category in book method //
//                Collections.sort(service.getBooksService().getBooks(), Book.bookCategoryCompare); // Can use collection replace sort //
                for (Book bcsort : service.getBooksService().getBooks()) {
                    System.out.println(bcsort);                                                     // Display book after sorting //
                }
                break;
            case 3:
                service.getBooksService().getBooks().sort(Book.bookCodeCompare);                    // Compare book with book code in book method //
//                Collections.sort(service.getBooksService().getBooks(), Book.bookCodeCompare);     // Can use collection replace sort //
                for (Book bssort : service.getBooksService().getBooks()) {
                    System.out.println(bssort);                                                     // Display book after sorting //
                }
                break;
            case 4:
                service.getBooksService().getBooks().sort(Book.bookStatusCompare);                  // Compare book with book status in book method //
//                Collections.sort(service.getBooksService().getBooks(), Book.bookStatusCompare);   // Can use collection replace sort //
                for (Book btsort : service.getBooksService().getBooks()) {
                    System.out.println(btsort);                                                     // Display book after sorting //
                }
                break;
            default:
                System.out.println("Error input doesn't exist");
                System.exit(0);
        }
        inputParser.Admin_Login();
    }

    //******************************** Approve Book ********************************//
    public static void ApproveBook() {
        //**************** Scanner Input ****************//
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book code to approve : ");
        String id = scanner.next();
        //**************** Approve Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Wait_Approve)) {
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
                    for (int i = 0; i < service.getHistoriesService().getHistories().size(); i++) {
                        System.out.println(service.getHistoriesService().getHistories().get(i));
                    }
                    System.out.println("\nYour work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        inputParser.Admin_Login();
    }

    //******************************** Accept Book ********************************//
    public static void AcceptBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book code to accept : ");
        String id = scanner.next();
        //**************** Accept Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Wait_Accept)) {
                    book.setBookStatus(BookStatus.Available);
                    for (History history : service.getHistoriesService().getHistories()) {
                        if (history.getBooksituation().equals(BookSituation.Return)) {
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                        }
                    }
                    //**************** Display ****************//
                    HistoryBook();
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        inputParser.Admin_Login();
    }

    //******************************** Change Book ********************************//
    public static void ChangeBook() {
        //**************** Input Scanner ****************//
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book code : ");
        String id = scanner.next();
        //**************** Change Component ****************//
        boolean Found = false;
        for (History history : service.getHistoriesService().getHistories()) {
            if (history.getBookcode().equalsIgnoreCase(id)) {
                Found = true;
                if (history.getBooksituation().equals(BookSituation.Borrow)) {
                    if (service.getCustomerDetail() != null) {
                        if (!history.getCustomername().equals(service.getCustomerDetail().getFirstName())) {
                            System.out.println("Error, you're not person who borrow the book");
                            System.out.println("================================");
                            inputParser.User_Login();
                        }
                    }
                    System.out.print("Enter your number to change return date : ");
                    int x = scanner.nextInt();
                    if (DAYS.between(history.getDayBorrow(), history.getDayReturn().plusDays(x)) > 15) {
                        System.out.println("Error, your date are invalid");
                        System.out.println("================================");
                        ChangeBook();
                    }
                    history.setDayReturn(history.getDayReturn().plusDays(x));
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        if (service.getLibrarianDetail() == null) {
            inputParser.User_Login();
        } else {
            inputParser.Admin_Login();
        }

    }

    //****************************************** Customer Function ******************************************//
    //******************************** Borrow Book ********************************//
    public static void BorrowBook() {
        History history = new History();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book code to borrow : ");
        String id = scanner.next();
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
                    LibraryScreen.SearchDisplay(book);
                    System.out.println("Your work has been successful\n");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist or unvaluable");
        }
        inputParser.User_Login();
    }

    //******************************** Return Book ********************************//
    public static void ReturnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book code to return : ");
        String id = scanner.next();
        //**************** Return Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    service.setBookDetail(book);
                    //**************** Customer Set ****************//
                    try {
                        for (History history : service.getHistoriesService().getHistories()) {
                            if ((history.getBookcode().equals(id)) && (history.getBooksituation().equals(BookSituation.Borrow))) {
                                if (!history.getCustomername().equals(service.getCustomerDetail().getFirstName())) {
                                    System.out.println("Error, you're not person who borrow the book");
                                    System.out.println("================================");
                                    book.setBookStatus(BookStatus.Unvailable);
                                    ReturnBook();
                                }
                                // **************** Date Check **************** //
                                System.out.println("\nUser : " + service.getCustomerDetail().getFirstName());
                                int x = (int) DAYS.between(history.getDayReturn(), LocalDate.now());
                                if (x > 0) {
                                    System.out.println("" + service.getCustomerDetail().getFirstName() + ", You return book late " + x + " day(s)");
                                }
                            }
                            //**************** Add Data to history ****************//
                            LibraryScreen.HistoryAdd(history);
                            book.setBookStatus(BookStatus.Wait_Accept);
                        }
                    } catch (ConcurrentModificationException ignored) {

                    }
                    //**************** Display ****************//
                    LibraryScreen.SearchDisplay(book);
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        inputParser.User_Login();
    }

    //****************************************** Register Function ******************************************//
    //******************************** Librarian ********************************//
    public static void Librarian_Register() {
        //**************** Create Variable ****************//
        String[] account = RegisterScreen.RegisterInput();
        try {
            RegisterScreen.DataCheck(account);

        } catch (ConcurrentModificationException ignored) {

        }
        //**************** Display Booklist ****************//
        for (int i = 0; i < service.getLibrariansService().getLibrarians().size(); i++) {
            System.out.println("Librarian Detail " + (i + 1) + " : " + service.getLibrariansService().getLibrarians().get(i));
        }
        System.out.println("=====================");
        inputParser.Register();
    }

    //******************************** Customer ********************************//
    public static void Customer_Register() {
        //**************** Create Variable ****************//
        String[] account = RegisterScreen.RegisterInput();
        try {
            RegisterScreen.DataCheck(account);

        } catch (ConcurrentModificationException ignored) {

        }
        //**************** Display Booklist ****************//
        for (int i = 0; i < service.getCustomersService().getCustomers().size(); i++) {
            System.out.println("Customer Detail " + (i + 1) + " : " + service.getCustomersService().getCustomers().get(i));
        }
        System.out.println("==========================");
        inputParser.Register();
    }
}
