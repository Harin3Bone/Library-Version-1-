package gl_Library;

import gl_Object.Customer;
import gl_Object.Librarian;

import gl_Repository.BookList;
import gl_Repository.CustomerList;
import gl_Repository.HistoryList;
import gl_Repository.LibrarianList;

import gl_Service.LibraryService;

import gl_View.HomeScreen;
import gl_View.MainScreen;
import gl_View.RegisterScreen;

import java.util.Scanner;

public class InputParser {
    private static LibraryService service = LibraryService.getInstance();
    public void Controller() {
        //******************** Property Section ********************//
        LibraryService service = LibraryService.getInstance();
        HomeScreen homeScreen = new HomeScreen();
        int ans_property = homeScreen.homedisplay();
        //******************** Login Section ********************//
        switch (ans_property) {
            case 1:
                MainScreen mainScreen = new MainScreen();
                int ans_login = mainScreen.loginDisplay();
                if (ans_login == 1) {
                    Librarian loginLibrarian = mainScreen.librarianDisplay();
                    if (loginLibrarian != null) {
                        service.setLibrarianDetail(loginLibrarian);
                        Admin_Login();
                    } else {
                        mainScreen.LoginFailed();
                    }
                } else {
                    if (ans_login == 2) {
                        MainScreen customerScreen = new MainScreen();
                        Customer logincustomer = mainScreen.customerDisplay();
                        if (logincustomer != null) {
                            service.setCustomerDetail(logincustomer);
                            User_Login();
                        } else {
                            mainScreen.LoginFailed();
                        }
                    }
                }
                break;
            case 2:
                Register();
                break;
            case 3:
                System.out.println("Thank you");
                System.exit(0);
            default:
                System.out.println("Error, your input doesn't exist");
                System.exit(0);
        }
    }

    //****************************************** Admin Login ******************************************//
    public void Admin_Login() {
        System.out.println("================================");
        System.out.println(""+service.getLibrarianDetail().getFirstName()+" "+service.getLibrarianDetail().getLastName());
        service.setCustomerDetail(null);
        MainScreen librarianMenu = new MainScreen();
        //******************** Select Section ********************//
        int choices = librarianMenu.librarianMenu();
        BookList books = new BookList();
        HistoryList histories = new HistoryList();
        switch (choices) {
            case 1:
                Library.AddBook();
                break;
            case 2:
                Library.RemoveBook();
                break;
            case 3:
                SearchExtend();
                break;
            case 4:
                Library.CheckBook();
                break;
            case 5:
                Library.HistoryBook();
                break;
            case 6:
                Library.SortBook();
                break;
            case 7:
                ConfirmExtend();
                break;
            case 8:
                Library.ChangeBook();
                break;
            case 9:
                Controller();
                break;
            case 0:
                System.out.println("Thank you");
                System.out.println("================================");
                System.exit(0);
            default:
                System.out.println("Error, your input doesn't exist");
                System.exit(0);
                break;
        }
    }

    //****************************************** User Login ******************************************//
    public void User_Login() {
        System.out.println("================================");
        System.out.println(""+service.getCustomerDetail().getFirstName()+" "+service.getCustomerDetail().getLastName());
        service.setLibrarianDetail(null);
        MainScreen customerMenu = new MainScreen();
        int choices = customerMenu.customerMenu();
        switch (choices) {
            case 1:
                SearchExtend();
                break;
            case 2:
                Library.CheckBook();
                break;
            case 3:
                Library.BorrowBook();
                break;
            case 4:
                Library.ReturnBook();
                break;
            case 5:
                Library.ChangeBook();
                break;
            case 6:
                Controller();
                break;
            case 0:
                System.out.println("Thank you");
                System.out.println("================================");
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }

    //****************************************** Registeration ******************************************//
    public void Register() {
        RegisterScreen registerMenu = new RegisterScreen();
        int choice = registerMenu.registerMenu();
        switch (choice) {
            case 1:
                Library.Librarian_Register();
                break;
            case 2:
                Library.Customer_Register();
                break;
            case 3:
                Controller();
                break;
            case 4:
                System.out.println("Thank you");
                System.out.println("================================");
                System.exit(0);
        }
    }

    //****************************************** Search Extension ******************************************//
    private void SearchExtend() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your search type");
        System.out.println("1 - Search by name\n2 - Search by Category\n3 - Search by Code");
        int ans = scanner.nextInt();
        switch (ans) {
            case 1:
                Library.SearchByName();
                break;
            case 2:
                Library.SearchByCategory();
                break;
            case 3:
                Library.SearchByCode();
                break;
            default:
                System.out.println("Error your input doesn't exist");
                System.exit(0);
        }
    }

    //****************************************** Confirm Extension ******************************************//
    private void ConfirmExtend() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your property");
        System.out.println("1 - Approve\t2 - Accept");
        int ans = scanner.nextInt();
        if (ans == 1) {
            Library.ApproveBook();
        } else {
            if (ans == 2) {
                Library.AcceptBook();
            }
        }
    }
}

