package gl_View;

import gl_Library.InputParser;
import gl_Library.Library;

import gl_Object.Customer;
import gl_Object.Librarian;

import gl_Repository.BookList;
import gl_Repository.CustomerList;
import gl_Repository.HistoryList;
import gl_Repository.LibrarianList;

import gl_Service.LibraryService;

import java.util.Scanner;

public class MainScreen {
    // Login menu display
    public String loginDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("Choose  your  level to login");
        System.out.println("1 - Librarian\t2 - Customer\t3 - Back");
        return scanner.nextLine();
    }

    // Login failed
    public void LoginFailed() {
        System.out.println("Your identity or password incorrect");
        System.out.println("================================");
        InputParser inputParser = new InputParser();
        inputParser.Controller();
    }

    // Exit
    public void ExitCommand() {
        System.out.println("Thank you");
        System.out.println("================================");
        System.exit(0);
    }

    // Default case
    public void DefaultRework() {
        InputParser inputParser = new InputParser();
        System.out.println("Error, your input doesn't exist");
        inputParser.Controller();
    }

    // Librarian login
    public Librarian librarianDisplay() {
        String[] account = loginTemplate();

        LibraryService service = LibraryService.getInstance();

        Librarian loginLibrarian = null;

        // id & password check
        for (Librarian librarian : service.getLibrariansService().getLibrarians()) {
            if (librarian.getIdentity().equals(account[0])) {
                if (librarian.getPassword().equals(account[1])) {
                    loginLibrarian = librarian;
                }
            }
        }
        return loginLibrarian;
    }

    // Librarian menu display
    public String librarianMenu() {
        Scanner scanner = new Scanner(System.in);
        LibraryService service = LibraryService.getInstance();
        System.out.println("================================");
        System.out.println("What do you want to do ?");
        System.out.println("1 - Add Book\t2 - Delete Book\t3 - Search\n4 - Check\t\t" +
                "5 - History \t6 - Sort\n7 - Confirm\t\t8 - Change\t\t9 - Back\n0 - Exit");
        return scanner.nextLine();
    }

    // Customer login
    public Customer customerDisplay() {
        String[] account = loginTemplate();
        LibraryService service = LibraryService.getInstance();
        Customer loginCustomer = null;
        //***************************** Id & Ps Check *****************************//
        for (Customer customer : service.getCustomersService().getCustomers()) {
            if (customer.getIdentity().equals(account[0])) {
                if (customer.getPassword().equals(account[1])) {
                    loginCustomer = customer;
                }
            }
        }
        return loginCustomer;
    }

    // Customer menu display
    public String customerMenu() {
        Scanner scanner = new Scanner(System.in);
        LibraryService service = LibraryService.getInstance();
        System.out.println("================================");
        Scanner choice2 = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("1 - Search\t2 - Check\t3 - Borrow\n4 - Return\t5 - Change\t6 - Back" +
                "\n0 - Exit");
        return scanner.nextLine();
    }

    // login input
    private static String[] loginTemplate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("ID : ");
        String id = scanner.next();
        System.out.println("Password : ");
        String ps = scanner.next();
        return new String[]{id, ps};
    }
}
