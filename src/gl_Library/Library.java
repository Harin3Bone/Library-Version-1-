package gl_Library;

import gl_Object.Customer;
import gl_Object.Librarian;
import gl_Object.Book;
import gl_Object.History;

import gl_Enum.BookCategory;
import gl_Enum.BookStatus;
import gl_Enum.BookSituation;
import gl_Service.LibraryService;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class Library {
    //****************************************** Librarian Function ******************************************//
    //******************************** Add Book ********************************//
    public static void AddBook() {
        //**************** Create Variable ****************//
        LibraryService service = LibraryService.getInstance();              // Create service to use book list //
        Book book = new Book();                                             // Create book object to get detail //
        Scanner scanner = new Scanner(System.in);                           // Create scanner to get input //
        //**************** Input Scanner ****************//
        System.out.print("Please enter book name : ");
        String name = scanner.nextLine();
        System.out.print("Please enter book category : ");
        String category = scanner.nextLine();
        BookCategory CategoryEnum = BookCategory.valueOf(category);         // Check input is same as enum or not ? //
        System.out.print("Please enter book author : ");
        String author = scanner.nextLine();
        System.out.print("Please enter book abstract : ");
        String abstracts = scanner.nextLine();
        //**************** Generate Book Code ****************//
        String CategoryCode = BookCategory.valueOf(category).getCode();     // Pull code (String) from category enum //
        DecimalFormat decimalFormat = new DecimalFormat("0000");     // DecimalFormat change display value from 1 to 0001 //

//        Random random = new Random();
//        int NumberCode = random.nextInt(1000);                                // Random number  0 - 1000 //
//        String code = CategoryCode + decimalFormat.format(NumberCode);        // Combine String //

        Integer runningNo = null;

        for (Book b : service.getBooksService().getBooks()) {
            if (CategoryCode.equals(b.getBookCode().substring(0,1))) {
                if (runningNo == null || runningNo < Integer.parseInt(b.getBookCode().substring(1))) {
                    runningNo = Integer.parseInt(b.getBookCode().substring(1));
                    // if no book in list it will cause runningNo don't have value //
                    // you should create runningNo condition if runningNo == null -> value = 1 //
                }
                if (runningNo == null) {
                    runningNo = 1;
                }
            }

        }

        int number = runningNo + 1;
        String code = CategoryCode + decimalFormat.format(number);
        //**************** Add Data to Variable ****************//
        book.setUuid(UUID.randomUUID());                                    // Random UUID but not show on display //
        book.setBookName(name);                                             // Set value //
        book.setBookCategory(category);
        book.setBookAuthor(author);
        book.setBookabstract(abstracts);
        book.setBookCode(code);
        book.setBookStatus(BookStatus.Available);                           // Set book status //
        //**************** Add Data to List ****************//
        service.getBooksService().getBooks().add(book);
        //**************** Display ****************//
        CheckBook();
    }

    //******************************** Remove Book ********************************//
    public static void RemoveBook() {
        //**************** Create Variable ****************//
        LibraryService service = LibraryService.getInstance();              // Create service to use book list //
        Book book = new Book();                                             // Create book to use book iterator //
        Scanner rem = new Scanner(System.in);                               // Create scanner to get input //
        //**************** Input Scanner ****************//
        System.out.println("Please enter book code to remove : ");
        String remid = rem.nextLine();
        //**************** Remove Component ****************//
        Iterator<Book> iterator = service.getBooksService().getBooks().iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getBookCode().equals(remid)) {                         // Condition check book code if same as input -> continue //
                iterator.remove();                                          // when condition is true -> remove 1 record //
                System.out.println("Book code : " + book.getBookCode() + " remove successful");
                System.out.println("=====================");
                for (int i = 0; i < service.getBooksService().getBooks().size(); i++) {
                    System.out.println(service.getBooksService().getBooks().get(i));
                }
                System.out.println("=====================");
            } else {
                if (book.getBookCode().equalsIgnoreCase(remid)) {
                    System.out.println("Sorry your book code is not exist");
                }
            }
        }
    }

    //******************************** Search Book ********************************//
    //**************** By Name ****************//
    public static void SearchByName() {
        LibraryService service = LibraryService.getInstance();              // Create service to get book list //
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your book name to search : ");
        String name = search.nextLine();
        boolean Found = false;                                              // Boolean it use for check it found book name or not //
        // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookName().equalsIgnoreCase(name)) {
                Found = true;
                SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //**************** By Category ****************//
    public static void SearchByCategory() {
        LibraryService service = LibraryService.getInstance();              // Create service to get book list //
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your category name to search : ");
        String cate = search.nextLine();
        boolean Found = false;                                              // Boolean it use for check it found book name or not //
        // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCategory().equalsIgnoreCase(cate)) {
                Found = true;
                SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //**************** By Code ****************//
    public static void SearchByCode() {
        LibraryService service = LibraryService.getInstance();              // Create service to get book list //
        //**************** Input Scanner ****************//
        Scanner search = new Scanner(System.in);
        System.out.print("Enter your book code to search : ");
        String id = search.nextLine();
        boolean Found = false;                                              // Boolean it use for check it found book name or not //
        // if not use boolean and choose else then will occur some problem //
        //**************** Display Search ****************//
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equalsIgnoreCase(id)) {
                Found = true;
                SearchDisplay(book);
            }
        }
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //******************************** Check Book ********************************//
    public static void CheckBook() {
        LibraryService service = LibraryService.getInstance();
        //**************** Display List Book ****************//
        System.out.println("=====================");
        for (int i = 0; i < service.getBooksService().getBooks().size(); i++) {
            System.out.println("Book Detail " + (i + 1) + " : " + service.getBooksService().getBooks().get(i));
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //******************************** History Book ********************************//
    public static void HistoryBook() {
        LibraryService service = LibraryService.getInstance();
        //**************** Display List Book ****************//
        System.out.println("=====================");
        for (int i = 0; i < service.getHistoriesService().getHistories().size(); i++) {
            System.out.println("History Detail " + (i + 1) + " : " + service.getHistoriesService().getHistories().get(i));
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //******************************** Sort Book ********************************//
    public static void SortBook() {
        LibraryService service = LibraryService.getInstance();
        Scanner sort = new Scanner(System.in);
        System.out.println("Please use sorting function");
        System.out.println("1 - Sort by Name\n2 - Sort by Category\n3 - Sort by Serial\n4 - Sort by Status");
        int ans_sort = sort.nextInt();
        switch (ans_sort) {
            case 1:
                service.getBooksService().getBooks().sort(Book.bookNameCompare);                // Compare book with book name in book method //
//                Collections.sort(books.getBooksService(), Book.bookNameCompare);
                for (Book bnsort : service.getBooksService().getBooks()) {
                    System.out.println(bnsort);                                                 // Display book after sorting //
                }
                break;
            case 2:
                service.getBooksService().getBooks().sort(Book.bookCategoryCompare);            // Compare book with book category in book method //
//                Collections.sort(books.getBooksService(), Book.bookCategoryCompare);
                for (Book bcsort : service.getBooksService().getBooks()) {
                    System.out.println(bcsort);                                                 // Display book after sorting //
                }
                break;
            case 3:
                service.getBooksService().getBooks().sort(Book.bookCodeCompare);                // Compare book with book code in book method //
//                Collections.sort(books.getBooksService(), Book.bookCodeCompare);
                for (Book bssort : service.getBooksService().getBooks()) {
                    System.out.println(bssort);                                                 // Display book after sorting //
                }
                break;
            case 4:
                service.getBooksService().getBooks().sort(Book.bookStatusCompare);              // Compare book with book status in book method //
//                Collections.sort(books.getBooksService(), Book.bookStatusCompare);
                for (Book btsort : service.getBooksService().getBooks()) {
                    System.out.println(btsort);                                                 // Display book after sorting //
                }
                break;
            default:
                System.out.println("Error input doesn't exist");
                System.exit(0);
        }
        InputParser inputParser = new InputParser();
        inputParser.User_Login();
    }

    //******************************** Approve Book ********************************//
    public static void ApproveBook() {
        LibraryService service = LibraryService.getInstance();
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
                    //**************** Status & Day Set ****************//
                    book.setBookStatus(BookStatus.Unvailable);
                    for (History history : service.getHistoriesService().getHistories()) {
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Non_Borrow)) {
                            history.setDayBorrow(LocalDate.now());
                            history.setDayReturn(LocalDate.now().plusDays(7));
                            history.setBooksituation(BookSituation.Borrow);
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
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //******************************** Accept Book ********************************//
    public static void AcceptBook() {
        LibraryService service = LibraryService.getInstance();
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
                        if (history.getBookcode().equals(id) && history.getBooksituation().equals(BookSituation.Non_Return)) {
                            history.setUuid(UUID.randomUUID());
                            history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                            history.setBooksituation(BookSituation.Return);
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
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //******************************** Change Book ********************************//
    public static void ChangeBook_Librarian() {
        LibraryService service = LibraryService.getInstance();
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
                    System.out.print("Enter your number to change return date : ");
                    int x = scanner.nextInt();
                    if (DAYS.between(history.getDayBorrow(), history.getDayReturn().plusDays(x)) >= 15) {
                        System.out.println("Error, your date are invalid");
                        System.out.println("================================");
                        ChangeBook_Librarian();
                    }
                    history.setDayReturn(history.getDayReturn().plusDays(x));
                    history.setLibrarianname(service.getLibrarianDetail().getFirstName());
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        InputParser inputParser = new InputParser();
        inputParser.Admin_Login();
    }

    //****************************************** Customer Function ******************************************//
    //******************************** Borrow Book ********************************//
    public static void BorrowBook() {
        LibraryService service = LibraryService.getInstance();
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
                    history.setCustomername(service.getCustomerDetail().getFirstName());
                    history.setBookname(book.getBookName());
                    history.setBookcode(book.getBookCode());
                    history.setBookcategory(book.getBookCategory());
                    history.setBookauthor(book.getBookAuthor());
                    history.setBooksituation(BookSituation.Non_Borrow);
                    //**************** History List Add ****************//
                    service.getHistoriesService().getHistories().add(history);
                    //**************** Display ****************//
                    System.out.println("User : " + service.getCustomerDetail().getFirstName());
                    SearchDisplay(book);
                    System.out.println("Your work has been successful\n");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist or unvaluable");
        }
        InputParser inputParser = new InputParser();
        inputParser.User_Login();
    }

    //******************************** Return Book ********************************//
    public static void ReturnBook() {
        LibraryService service = LibraryService.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book code to return : ");
        String id = scanner.next();
        //**************** Return Component ****************//
        boolean Found = false;
        for (Book book : service.getBooksService().getBooks()) {
            if (book.getBookCode().equals(id)) {
                Found = true;
                if (book.getBookStatus().equals(BookStatus.Unvailable)) {
                    //**************** Customer Set ****************//
                    for (History history : service.getHistoriesService().getHistories()) {
                        if ((history.getBookcode().equals(id)) && (history.getBooksituation().equals(BookSituation.Borrow))) {
                            if (!history.getCustomername().equals(service.getCustomerDetail().getFirstName())) {
                                System.out.println("Error, you're not person who borrow the book");
                                System.out.println("================================");
                                book.setBookStatus(BookStatus.Unvailable);
                                ReturnBook();
                            }
                            // **************** Date Check **************** //
                            if (!history.getDayReturn().equals(LocalDate.now())) {
                                int x = LocalDate.now().compareTo(history.getDayReturn());
                                if (x < 0) {
                                    System.out.println("" + service.getCustomerDetail().getFirstName() + " You return late " + (x * (-1)) + " day(s)\nThank you");
                                }
                            }
                            System.out.println("User : " + service.getCustomerDetail().getFirstName());
                        }
                        //**************** Status Set ****************//
                        book.setBookStatus(BookStatus.Wait_Accept);
                        history.setBooksituation(BookSituation.Non_Return);
                    }
                    //**************** Display ****************//
                    SearchDisplay(book);
                    System.out.println("Your work has been successful");
                } else {
                    Found = false;
                }
            }
        }
        if (!Found) {
            System.out.println("Your book it doesn't exist");
        }
        InputParser inputParser = new InputParser();
        inputParser.User_Login();
    }

    //******************************** Change Book ********************************//
    public static void ChangeBook_Customer() {
        LibraryService service = LibraryService.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book code : ");
        String id = scanner.next();
        //**************** Change Component ****************//
        boolean Found = false;
        for (History history : service.getHistoriesService().getHistories()) {
            if (history.getBookcode().equalsIgnoreCase(id)) {
                Found = true;
                if (history.getBooksituation().equals(BookSituation.Borrow)) {
                    if (!history.getCustomername().equals(service.getCustomerDetail().getFirstName())) {
                        System.out.println("Error, you're not person who borrow the book");
                        System.out.println("================================");
                        ChangeBook_Customer();
                    }
                    System.out.print("Enter your number to change return date : ");
                    int x = scanner.nextInt();
                    if (DAYS.between(history.getDayBorrow(), history.getDayReturn().plusDays(x)) >= 15) {
                        System.out.println("Error, your date are invalid");
                        System.out.println("================================");
                        ChangeBook_Customer();
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
        InputParser inputParser = new InputParser();
        inputParser.User_Login();
    }

    //****************************************** Register Function ******************************************//
    //******************************** Librarian ********************************//
    public static void Librarian_Register() {
        //**************** Create Variable ****************//
        LibraryService service = LibraryService.getInstance();

        Librarian newLibrarian = new Librarian();
        Scanner add = new Scanner(System.in);

        //**************** Input Scanner ****************//
        System.out.print("Please fill librarian firstname : ");
        String Firstname = add.nextLine();

        System.out.print("Please fill librarian lastname : ");
        String Lastname = add.nextLine();

        System.out.print("Please fill librarian username : ");
        String Identity = add.nextLine();

        System.out.print("Please fill librarian password : ");
        String Password1 = add.nextLine();
        System.out.print("Please re-fill customer password : ");
        String Password2 = add.nextLine();

        try {
            for (Librarian librarian : service.getLibrariansService().getLibrarians()) {
                if ((librarian.getFirstName().equals(Firstname))) {
                    if ((librarian.getLastName().equals(Lastname))) {
                        System.out.println("This account has benn already sign up");
                        System.out.println("=====================");
                        Librarian_Register();                                               // Beware the ConcurrentModificationException //
//                        System.exit(0);
                    }
                }
                if (librarian.getIdentity().equals(Identity)) {
                    System.out.println("Your identity has been already use");
                    System.out.println("=====================");
                    Librarian_Register();                                                   // Beware the ConcurrentModificationException //
//                System.exit(0);
                }
            }

            if (Password1.equals(Password2)) {
                //**************** Add Data to Variable ****************//
                newLibrarian.setFirstName(Firstname);
                newLibrarian.setLastName(Lastname);
                newLibrarian.setIdentity(Identity);
                newLibrarian.setPassword(Password1);
                //**************** Add Data to List ****************//
                service.getLibrariansService().getLibrarians().add(newLibrarian);
            } else {
                System.out.println("Error, Your password don't same\n");
                System.out.println("=====================");
                Librarian_Register();
            }
        } catch (ConcurrentModificationException e) {

        }
        //**************** Display Booklist ****************//
        for (int i = 0; i < service.getLibrariansService().getLibrarians().size(); i++) {
            System.out.println("Librarian Detail " + (i + 1) + " : " + service.getLibrariansService().getLibrarians().get(i));
        }
        System.out.println("=====================");
    }

    //******************************** Customer ********************************//
    public static void Customer_Register() {
        LibraryService service = LibraryService.getInstance();
        //**************** Create Variable ****************//
        Customer newCustomer = new Customer();
        Scanner add = new Scanner(System.in);

        //**************** Input Scanner ****************//
        System.out.print("Please fill customer firstname : ");
        String Firstname = add.nextLine();

        System.out.print("Please fill customer lastname : ");
        String Lastname = add.nextLine();

        System.out.print("Please fill customer username : ");
        String Identity = add.nextLine();

        System.out.print("Please fill customer password : ");
        String Password1 = add.nextLine();
        System.out.print("Please re-fill customer password : ");
        String Password2 = add.nextLine();
        System.out.println("=====================");

        try {
            for (Customer customer : service.getCustomersService().getCustomers()) {
                if ((customer.getFirstName().equals(Firstname))) {
                    if ((customer.getLastName().equals(Lastname))) {
                        System.out.println("This account has benn already sign up");
                        System.out.println("=====================");
                        Customer_Register();                                                // Beware the ConcurrentModificationException //
//                        System.exit(0);
                    }
                }
                if (customer.getIdentity().equals(Identity)) {
                    System.out.println("Your identity has been already use");
                    System.out.println("=====================");
//                Customer_Register(customers);
                    Customer_Register();                                                    // Beware the ConcurrentModificationException //
                }
            }

            if (Password1.equals(Password2)) {
                //**************** Add Data to Variable ****************//
                newCustomer.setFirstName(Firstname);
                newCustomer.setLastName(Lastname);
                newCustomer.setIdentity(Identity);
                newCustomer.setPassword(Password1);
                //**************** Add Data to List ****************//
                service.getCustomersService().getCustomers().add(newCustomer);
            } else {
                System.out.println("Error, Your password don't same\n");
                System.out.println("=====================");
                Customer_Register();
            }
        } catch (ConcurrentModificationException e) {

        }
        //**************** Display Booklist ****************//
        for (int i = 0; i < service.getCustomersService().getCustomers().size(); i++) {
            System.out.println("Customer Detail " + (i + 1) + " : " + service.getCustomersService().getCustomers().get(i));
        }
        System.out.println("=====================");
    }

    //****************************************** Other Function ******************************************//
    //******************************** Search Display ********************************//
    public static void SearchDisplay(Book book) {
        System.out.println("==========================");
        System.out.println("Book Name   : " + book.getBookName());
        System.out.println("Book Type   : " + book.getBookCategory());
        System.out.println("Book Code   : " + book.getBookCode());
        System.out.println("Book Status : " + book.getBookStatus());
    }
}
