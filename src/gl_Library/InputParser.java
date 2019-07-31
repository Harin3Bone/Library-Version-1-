package gl_Library;

import gl_Object.Customer;
import gl_Object.Librarian;

import gl_Service.LibraryService;

import gl_View.HomeScreen;
import gl_View.MainScreen;
import gl_View.RegisterScreen;

import java.util.InputMismatchException;

public class InputParser {
    private static LibraryService service = LibraryService.getInstance();
    private static MainScreen mainScreen = new MainScreen();

    public void Controller() {
        // Property menu
        LibraryService service = LibraryService.getInstance();
        HomeScreen homeScreen = new HomeScreen();
        // Login section
        switch (homeScreen.homedisplay()) {
            case "1":
                // Librarian login
                String login = mainScreen.loginDisplay();
                if (login.equals("1")) {
                    Librarian loginLibrarian = mainScreen.librarianDisplay();
                    if (loginLibrarian != null) {
                        service.setLibrarianDetail(loginLibrarian);
                        Admin_Login();
                    } else {
                        mainScreen.LoginFailed();
                    }
                } else {
                    // Customer login
                    if (login.equals("2")) {
                        Customer logincustomer = mainScreen.customerDisplay();
                        if (logincustomer != null) {
                            service.setCustomerDetail(logincustomer);
                            User_Login();
                        } else {
                            mainScreen.LoginFailed();
                        }
                    } else {
                        mainScreen.DefaultRework();
                    }
                }
                break;
            case "2":
                // Registeration
                Register();
                break;
            case "3":
                // Exit program
                mainScreen.ExitCommand();

            case "Libra":
                // Extra function show all librarian account detail
                for (int i = 0; i < service.getLibrariansService().getLibrarians().size(); i++) {
                    System.out.println("Librarian Detail " + (i + 1) + " : " + service.getLibrariansService().getLibrarians().get(i));
                }
                System.out.println("=====================");
                Controller();
                break;

            case "Custo":
                // Extra function show all customer account detail
                for (int i = 0; i < service.getCustomersService().getCustomers().size(); i++) {
                    System.out.println("Customer Detail " + (i + 1) + " : " + service.getCustomersService().getCustomers().get(i));
                }
                System.out.println("==========================");
                Controller();
                break;

            default:
                mainScreen.DefaultRework();

        }
    }

    // Librarian login pass
    public void Admin_Login() {
        System.out.println("================================");
        System.out.println("" + service.getLibrarianDetail().getFirstName() + " " + service.getLibrarianDetail().getLastName());
        service.setCustomerDetail(null);
        //******************** Select Section ********************//
        switch (mainScreen.librarianMenu()) {
            case "1":
                Library.AddBook();
                break;
            case "2":
                Library.RemoveBook();
                break;
            case "3":
                Library.SearchBook();
                break;
            case "4":
                Library.CheckBook();
                break;
            case "5":
                Library.HistoryBook();
                break;
            case "6":
                Library.SortBook();
                break;
            case "7":
                Library.ConfirmBook();
                break;
            case "8":
                Library.ChangeBook();
                break;
            case "9":
                service.setLibrarianDetail(null);
                Controller();
                break;
            case "0":
                mainScreen.ExitCommand();
            default:
                mainScreen.DefaultRework();
        }
    }

    // Customer login pass
    public void User_Login() {
        System.out.println("================================");
        System.out.println("" + service.getCustomerDetail().getFirstName() + " " + service.getCustomerDetail().getLastName());
        service.setLibrarianDetail(null);
        switch (mainScreen.customerMenu()) {
            case "1":
                Library.SearchBook();
                break;
            case "2":
                Library.CheckBook();
                break;
            case "3":
                Library.BorrowBook();
                break;
            case "4":
                Library.ReturnBook();
                break;
            case "5":
                Library.ChangeBook();
                break;
            case "6":
                service.setCustomerDetail(null);
                Controller();
                break;
            case "0":
                mainScreen.ExitCommand();
            default:
                mainScreen.DefaultRework();
        }
    }

    // Registeration new account
    public void Register() {
        System.out.println("================================");
        switch (RegisterScreen.registerMenu()) {
            case "1":
                Library.Librarian_Register();
                break;
            case "2":
                Library.Customer_Register();
                break;
            case "3":
                Controller();
                break;
            case "4":
                mainScreen.ExitCommand();
            default:
                mainScreen.DefaultRework();
        }
    }
}

