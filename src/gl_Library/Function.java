package gl_Library;

import gl_Enum.BookSituation;
import gl_Object.Book;
import gl_Object.History;
import gl_Service.LibraryService;

import java.util.UUID;

public class Function {
    private static LibraryService service = LibraryService.getInstance();
    private static InputParser inputParser = new InputParser();
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
