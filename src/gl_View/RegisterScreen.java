package gl_View;

import gl_Library.Library;
import gl_Object.Customer;
import gl_Object.Librarian;
import gl_Service.LibraryService;

import java.util.Scanner;
import java.util.UUID;

public class RegisterScreen {
    private static LibraryService service = LibraryService.getInstance();
    public static int registerMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select level to registeration");
        System.out.println("1 - Librarian\t2 - Customer\n3 - Back\t\t4 - Exit");
        int choice = scanner.nextInt();
        return choice;
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
    public static void DataCheck(String[] account){
        Librarian newLibrarian = new Librarian();
        Customer newCustomer = new Customer();
        //******************************** Librarian ********************************//
        for (Librarian librarian : service.getLibrariansService().getLibrarians()) {
            if ((librarian.getFirstName().equals(account[0]))) {
                if ((librarian.getLastName().equals(account[1]))) {
                    System.out.println("This account has benn already sign up");
                    System.out.println("=====================");
                    Library.Librarian_Register();                                      // Beware the ConcurrentModificationException //
                }
            }
            if (librarian.getIdentity().equals(account[2])) {
                System.out.println("Your identity has been already use");
                System.out.println("=====================");
                Library.Librarian_Register();                                          // Beware the ConcurrentModificationException //
            }
        }
        if (account[3].equals(account[4])) {
            //**************** Add Data to Variable ****************//
            newLibrarian.setUuid(UUID.randomUUID());
            newLibrarian.setFirstName(account[0]);
            newLibrarian.setLastName(account[1]);
            newLibrarian.setIdentity(account[2]);
            newLibrarian.setPassword(account[3]);
            //**************** Add Data to List ****************//
            service.getLibrariansService().getLibrarians().add(newLibrarian);
        } else {
            System.out.println("Error, Your password don't same\n");
            System.out.println("=====================");
            Library.Librarian_Register();
        }
        //******************************** Customer ********************************//
        for (Customer customer : service.getCustomersService().getCustomers()) {
            if ((customer.getFirstName().equals(account[0]))) {
                if ((customer.getLastName().equals(account[1]))) {
                    System.out.println("This account has benn already sign up");
                    System.out.println("==========================");
                    Library.Customer_Register();                                       // Beware the ConcurrentModificationException //
                }
            }
            if (customer.getIdentity().equals(account[2])) {
                System.out.println("Your identity has been already use");
                System.out.println("==========================");
                Library.Customer_Register();                                           // Beware the ConcurrentModificationException //
            }
        }
        if (account[3].equals(account[4])) {
            //**************** Add Data to Variable ****************//
            newCustomer.setUuid(UUID.randomUUID());
            newCustomer.setFirstName(account[0]);
            newCustomer.setLastName(account[1]);
            newCustomer.setIdentity(account[2]);
            newCustomer.setPassword(account[3]);
            //**************** Add Data to List ****************//
            service.getCustomersService().getCustomers().add(newCustomer);
        } else {
            System.out.println("Error, Your password don't same\n");
            System.out.println("==========================");
            Library.Customer_Register();
        }
    }
}
