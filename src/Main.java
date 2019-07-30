import gl_Library.InputParser;
import gl_Repository.BookList;
import gl_Repository.CustomerList;
import gl_Repository.HistoryList;
import gl_Repository.LibrarianList;
import gl_Service.LibraryService;

public class Main {
    public static void main(String[] args) {
        LibraryService service = LibraryService.getInstance();  // Create service to save all list in service -> Singleton

        LibrarianList librarians = new LibrarianList();         // Create librarian list gl_Object
        librarians.DataLibrarianList(librarians);               // Add data to librarian list
        service.setLibrariansService(librarians);               // Add list to service

        CustomerList customers = new CustomerList();            // Create customer list gl_Object
        customers.DataCustomerList(customers);                  // Add data to customer list
        service.setCustomersService(customers);                 // Add list to service

        BookList books = new BookList();                        // Create book list gl_Object
        books.DataBookList(books);                              // Add data to book list
        service.setBooksService(books);                         // Add list to service

        HistoryList histories = new HistoryList();              // Create history list gl_Object
        histories.DataHistoryList(histories);                   // Add data to history list
        service.setHistoriesService(histories);                 // Add list to service

        InputParser inputParser = new InputParser();
        inputParser.controller();
    }
}
