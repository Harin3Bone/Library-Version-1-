package gl_View;

import gl_Enum.BookCategory;
import gl_Enum.BookSituation;
import gl_Enum.BookStatus;

import gl_Library.InputParser;
import gl_Library.Library;

import gl_Object.Book;
import gl_Object.History;

import gl_Service.LibraryService;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.UUID;

public class LibraryScreen {
    private static LibraryService service = LibraryService.getInstance();
    private static InputParser inputParser = new InputParser();

    //******************************** Add Input ********************************//
    public static String[] AddView() {
        //**************** Input Scanner ****************//
        Scanner scanner = new Scanner(System.in);                                   // Create scanner to get input //
        System.out.print("Please enter book name : ");
        String name = scanner.nextLine();
        System.out.print("Please enter book category : ");
        String category = scanner.nextLine();
        BookCategory CategoryEnum = BookCategory.valueOf(category);                 // Check input is same as enum or not ? //
        System.out.print("Please enter book author : ");
        String author = scanner.nextLine();
        System.out.print("Please enter book abstract : ");
        String abstracts = scanner.nextLine();
        //**************** Generate Book Code ****************//
        String CategoryCode = BookCategory.valueOf(category).getCode();             // Pull code (String) from category enum //
        DecimalFormat decimalFormat = new DecimalFormat("0000");            // DecimalFormat change display value from 1 to 0001 //

//        Random random = new Random();
//        int NumberCode = random.nextInt(1000);                                    // Random number  0 - 1000 //
//        String code = CategoryCode + decimalFormat.format(NumberCode);            // Combine String //

        Integer runningNo = null;

        for (Book b : service.getBooksService().getBooks()) {
            if (CategoryCode.equals(b.getBookCode().substring(0, 1))) {
                if (runningNo == null || runningNo < Integer.parseInt(b.getBookCode().substring(1))) {
                    runningNo = Integer.parseInt(b.getBookCode().substring(1));
                    // if no book in list it will cause runningNo don't have value //
                    // you should create runningNo condition if runningNo == null -> value = 1 //
                }
            }
        }

        String code = CategoryCode + decimalFormat.format(runningNo++);
        return new String[]{name, category, author, abstracts, code};
    }

    //******************************** Sort Menu ********************************//
    public static int SortView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please use sorting function");
        System.out.println("1 - Sort by Name\n2 - Sort by Category\n3 - Sort by Serial\n4 - Sort by Status");
        return scanner.nextInt();
    }

    //******************************** Search Display ********************************//
    public static void SearchDisplay(Book book) {
        System.out.println("==========================");
        System.out.println("Book Name   : " + book.getBookName());
        System.out.println("Book Type   : " + book.getBookCategory());
        System.out.println("Book Code   : " + book.getBookCode());
        System.out.println("Book Status : " + book.getBookStatus());
    }

    //******************************** Search Failed ********************************//
    public static void SearchFailed(Boolean Found){
        if (!Found) {
            System.out.println("Your book doesn't exist");
            System.out.println("==========================");
        }
    }

    //******************************** User Check ********************************//
    public static void SessionCheck(Boolean Found){
        if (Found != null){
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

    //******************************** History Add ********************************//
    public static void HistoryAdd(History historyForeach) {
        History history = new History();
        //**************** History Data Add ****************//
        history.setCustomername(service.getCustomerDetail().getFirstName());
        history.setUuid(UUID.randomUUID());
        history.setBookname(service.getBookDetail().getBookName());
        history.setBookcode(service.getBookDetail().getBookCode());
        history.setBookcategory(service.getBookDetail().getBookCategory());
        history.setBookauthor(service.getBookDetail().getBookAuthor());
        if (historyForeach == null) {    // Can use if (service.getBookDetail().getBookStatus().equals(BookStatus.Wait_Approve)) //
            //**************** History Status ****************//
            history.setBooksituation(BookSituation.Borrow);
            //**************** History List Add ****************//
            service.getHistoriesService().getHistories().add(history);
            //**************** Set book detail = null ****************//
            service.setBookDetail(null);
        } else {                          // Can use if (service.getBookDetail().getBookStatus().equals(BookStatus.Wait_Accept)) //
            //**************** Date Add ****************//
            history.setDayBorrow(historyForeach.getDayBorrow());
            history.setDayReturn(historyForeach.getDayReturn());
            //**************** History Status ****************//
            history.setBooksituation(BookSituation.Return);
            //**************** History List Add ****************//
            service.getHistoriesService().getHistories().add(history);
            //**************** Set book detail = null ****************//
            service.setBookDetail(null);
        }
    }

    //******************************** History Customer Check ********************************//
    public static void HistoryCheck() {
        if (!service.getHistoryDetail().getCustomername().equals(service.getCustomerDetail().getFirstName())) {
            System.out.println("Error, you're not person who borrow the book");
            System.out.println("================================");
            inputParser.User_Login();
            service.setHistoryDetail(null);
        }
    }
}
