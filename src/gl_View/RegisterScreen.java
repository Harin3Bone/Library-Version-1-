package gl_View;

import gl_Library.InputParser;
import gl_Library.Library;
import gl_Object.Customer;
import gl_Object.Librarian;
import gl_Object.Person;
import gl_Service.LibraryService;

import java.util.Scanner;
import java.util.UUID;

public class RegisterScreen {
    private static LibraryService service = LibraryService.getInstance();
    private static InputParser inputParser = new InputParser();

    public static int registerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select level to registeration");
        System.out.println("1 - Librarian\t2 - Customer\n3 - Back\t\t4 - ExitCommand");
        return scanner.nextInt();
    }

    //******************************** Register Input ********************************//
    public static String[] RegisterInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please fill your firstname : ");
        String Firstname = scanner.nextLine();

        System.out.print("Please fill your lastname : ");
        String Lastname = scanner.nextLine();

        System.out.print("Please fill your username : ");
        String Identity = scanner.nextLine();

        System.out.print("Please fill your password : ");
        String Password1 = scanner.nextLine();
        System.out.print("Please re-fill your password : ");
        String Password2 = scanner.nextLine();
        System.out.println("==========================");

        return new String[]{Firstname, Lastname, Identity, Password1, Password2};
    }

    //******************************** Librarian & Customer Check ********************************//
    public static void DataCheck(String[] account, Boolean bool) {
        //******************************** Librarian ********************************//
        if (bool) {
            for (Librarian librarian : service.getLibrariansService().getLibrarians()) {
                if ((librarian.getFirstName().equals(account[0]) && librarian.getLastName().equals(account[1]))
                        || librarian.getIdentity().equals(account[2])) {
                    System.out.println("This account has been already sign up");
                    System.out.println("=====================");
                    Library.Librarian_Register();
                    // Beware the ConcurrentModificationException //
                }
            }
        } else {
            //******************************** Customer ********************************//
            for (Customer customer : service.getCustomersService().getCustomers()) {
                if ((customer.getFirstName().equals(account[0]) && customer.getLastName().equals(account[1]))
                        || (customer.getIdentity().equals(account[2])) ) {
                    System.out.println("This account has been already sign up");
                    System.out.println("==========================");
                    Library.Customer_Register();
                    // Beware the ConcurrentModificationException //
                }
            }
        }
    }

    //******************************** Librarian & Customer Add ********************************//
    public static void DataAdd(String[] account, Boolean bool) {
        Person person = new Person();
        Librarian librarian = new Librarian();
        Customer customer  = new Customer();
        if (!account[3].equals(account[4])) {
            System.out.println("Error, Your password don't same\n");
            System.out.println("==========================");
            inputParser.Register();

        } else {
            if (bool){
                librarian.setUuid(UUID.randomUUID());
                librarian.setFirstName(account[0]);
                librarian.setLastName(account[1]);
                librarian.setIdentity(account[2]);
                librarian.setPassword(account[3]);
                service.getLibrariansService().getLibrarians().add(librarian);
            }
            else {
                customer.setUuid(UUID.randomUUID());
                customer.setFirstName(account[0]);
                customer.setLastName(account[1]);
                customer.setIdentity(account[2]);
                customer.setPassword(account[3]);
                service.getCustomersService().getCustomers().add(customer);
            }
        }
    }
}
