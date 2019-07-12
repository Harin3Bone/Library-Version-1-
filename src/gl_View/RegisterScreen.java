package gl_View;

import java.util.Scanner;

public class RegisterScreen {
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
}
