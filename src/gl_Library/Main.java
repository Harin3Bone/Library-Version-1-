package gl_Library;

import gl_Repository.BookList;
import gl_Repository.CustomerList;
import gl_Repository.HistoryList;
import gl_Repository.LibrarianList;
import gl_Service.LibraryService;

public class Main {
    public static void main(String[] args) {

        LibraryService service = LibraryService.getInstance();

        LibrarianList librarians = new LibrarianList();     //create librarian list gl_Object//
        librarians.DataLibrarianList(librarians);           //add data to librarian list//
        service.setLibrariansService(librarians);           //add list to service//

        CustomerList customers = new CustomerList();        //create customer list gl_Object//
        customers.DataCustomerList(customers);              //add data to customer list//
        service.setCustomersService(customers);             //add list to service//

        BookList books = new BookList();                    //create book list gl_Object//
        books.DataBookList(books);                          //add data to book list//
        service.setBooksService(books);                     //add list to service//

        HistoryList histories = new HistoryList();          //create history list gl_Object//
        histories.DataHistoryList(histories);               //add data to history list//
        service.setHistoriesService(histories);             //add list to service//

        InputParser inputParser = new InputParser();
        inputParser.Controller();
    }
}
