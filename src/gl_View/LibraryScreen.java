package gl_View;

import gl_Enum.BookCategory;
import gl_Enum.BookSituation;
import gl_Library.InputParser;
import gl_Object.Book;
import gl_Object.History;
import gl_Service.LibraryService;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class LibraryScreen {
    private static LibraryService service = LibraryService.getInstance();
    private static InputParser inputParser = new InputParser();

    // Add book input
    public static String[] AddView() {
        Scanner scanner = new Scanner(System.in);                                   // Create scanner to get input //
        System.out.print("Please enter book name : ");
        String name = scanner.nextLine();
        System.out.print("Please enter book category : ");
        String category = scanner.nextLine();
        System.out.print("Please enter book author : ");
        String author = scanner.nextLine();
        System.out.print("Please enter book abstract : ");
        String abstracts = scanner.nextLine();
        return new String[]{name, category, author, abstracts};
    }

    // Remove book input
    public static String RemoveView() {
        Scanner scanner = new Scanner(System.in);                                       // Create scanner to get input //
        System.out.println("Please enter book code to remove : ");
        return scanner.nextLine();
    }

    // Sort menu
    public static int SortView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please use sorting function");
        System.out.println("1 - Sort by Name\n2 - Sort by Category\n3 - Sort by Serial\n4 - Sort by Status");
        return scanner.nextInt();
    }

    // Sort display
    public static void SortDisplay() {
        for (Book sort : service.getBooksService().getBooks()) {
            System.out.println(sort);                                                   // Display book after sorting //
        }
    }

    // Search menu
    public static String SearchExtension() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your search type");
        System.out.println("1 - Search by name\n2 - Search by Category\n3 - Search by Code");
        return scanner.nextLine();
    }

    // Search by name
    public static String SearchBookName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book name to search : ");
        return scanner.nextLine();
    }

    // Search by category
    public static String SearchBookCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book category to search : ");
        return scanner.nextLine();
    }

    // Search by code
    public static String SearchBookCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book code to search : ");
        return scanner.nextLine();
    }

    // Search display
    public static void SearchDisplay() {
        System.out.println("==========================");
        System.out.println("Book Name   : " + service.getBookDetail().getBookName());
        System.out.println("Book Type   : " + service.getBookDetail().getBookCategory());
        System.out.println("Book Code   : " + service.getBookDetail().getBookCode());
        System.out.println("Book Status : " + service.getBookDetail().getBookStatus());
        service.setBookDetail(null);
    }

    // Confirm menu
    public static String ConfirmView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your property");
        System.out.println("1 - Approve\t2 - Accept");
        return scanner.nextLine();
    }

    // Book Code Input
    public static String ChangeView() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book code : ");
        return scanner.nextLine();
    }

    // Change date Input
    public static int ChangeDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your number to change return date : ");
        return scanner.nextInt();
    }

    // Borrow & Return Input
    public static String ConfirmInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your book code : ");
        return scanner.nextLine();
    }

    // Session Check
    public static void SessionCheck(Boolean Found) {
        if (Found != null) {
            if (!Found) {
                System.out.println("Your book it doesn't exist");
            }
        }
        if (service.getLibrarianDetail() == null) {
            inputParser.User_Login();
        } else {
            inputParser.Admin_Login();
        }
    }

    public static void DayLateCheck() {
        int x = (int) DAYS.between(service.getHistoryDetail().getDayReturn(), LocalDate.now());
        if (x > 0) {
            System.out.println("" + service.getHistoryDetail().getCustomername() + ", You return book late " + x + " day(s)");
        }
    }


    // Generate Book Code
    public static String GenerateCode(String value) {
        String CategoryCode = BookCategory.valueOf(value).getCode();                    // Pull code (String) from category enum
        DecimalFormat decimalFormat = new DecimalFormat("0000");                // DecimalFormat change display value from 1 to 0001

//        Random random = new Random();
//        int NumberCode = random.nextInt(1000);                                    // Random number  0 - 1000
//        String code = CategoryCode + decimalFormat.format(NumberCode);            // Combine String

        Integer runningNo = null;
        if (service.getBooksService().getBooks().size() != 0) {
            for (Book b : service.getBooksService().getBooks()) {
                if (CategoryCode.equals(b.getBookCode().substring(0, 1))) {
                    if (runningNo == null || runningNo < Integer.parseInt(b.getBookCode().substring(1))) {
                        runningNo = Integer.parseInt(b.getBookCode().substring(1));
                    }
                } else {
                    if (runningNo == null) {
                        runningNo = 0;
                    }
                }
            }
        } else {
            runningNo = 0;
        }
        runningNo = runningNo + 1;
        String code = CategoryCode + decimalFormat.format(runningNo);
        return code;
    }

    // History Add
    public static void HistoryAdd(History historyForeach) {
        History history = new History();

        // Add data to history object
        history.setUuid(UUID.randomUUID());
        history.setBookname(service.getBookDetail().getBookName());
        history.setBookcode(service.getBookDetail().getBookCode());
        history.setBookcategory(service.getBookDetail().getBookCategory());
        history.setBookauthor(service.getBookDetail().getBookAuthor());

        // Add history data to history list
        if (historyForeach == null) {
            // Can use if (service.getBookDetail().getBookStatus().equals(BookStatus.Wait_Approve))
            history.setCustomername(service.getCustomerDetail().getFirstName());
            history.setBooksituation(BookSituation.Borrow);
            service.getHistoriesService().getHistories().add(history);
        } else {
            // Can use if (service.getBookDetail().getBookStatus().equals(BookStatus.Wait_Accept))
            history.setCustomername(historyForeach.getCustomername());
            history.setDayBorrow(historyForeach.getDayBorrow());
            history.setDayReturn(historyForeach.getDayReturn());
            history.setBooksituation(BookSituation.Return);
            service.getHistoriesService().getHistories().add(history);
//
        }
    }

    // History Customer Check
//    public static void HistoryCheck() {
//        if (!service.getHistoryDetail().getCustomername().equals(service.getCustomerDetail().getFirstName())) {
//            System.out.println("Error, you're not person who borrow the book");
//            service.setHistoryDetail(null);
//            inputParser.User_Login();
//        }
//    }
}
