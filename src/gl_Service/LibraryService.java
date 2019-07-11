package gl_Service;

import gl_Object.Customer;
import gl_Object.History;
import gl_Object.Librarian;
import gl_Repository.BookList;
import gl_Repository.CustomerList;
import gl_Repository.HistoryList;
import gl_Repository.LibrarianList;

public class LibraryService {
    private static LibraryService instance;

    private LibrarianList librariansService;
    private CustomerList customersService;
    private BookList booksService;
    private HistoryList historiesService;

    private Librarian librarianDetail;
    private Customer customerDetail;
    private History history;

    private LibraryService() {

    }

    public static LibraryService getInstance() {
        if (instance == null){
            instance = new LibraryService();
        }
        return instance;
    }

    //************************** Getter & Setter **************************//
    public LibrarianList getLibrariansService() {

        return librariansService;
    }

    public void setLibrariansService(LibrarianList librariansService) {

        this.librariansService = librariansService;
    }

    public CustomerList getCustomersService() {

        return customersService;
    }

    public void setCustomersService(CustomerList customersService) {

        this.customersService = customersService;
    }

    public BookList getBooksService() {

        return booksService;
    }

    public void setBooksService(BookList booksService) {

        this.booksService = booksService;
    }

    public HistoryList getHistoriesService() {

        return historiesService;
    }

    public void setHistoriesService(HistoryList historiesService) {

        this.historiesService = historiesService;
    }

    public Librarian getLibrarianDetail() {
        return librarianDetail;
    }

    public void setLibrarianDetail(Librarian librarianDetail) {
        this.librarianDetail = librarianDetail;
    }

    public Customer getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(Customer customerDetail) {
        this.customerDetail = customerDetail;
    }
}
