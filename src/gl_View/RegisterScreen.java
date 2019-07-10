package gl_View;

import java.util.Scanner;

public class RegisterScreen {
    public int registerMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select level to registeration");
        System.out.println("1 - Librarian\t2 - Customer\n3 - Back\t\t4 - Exit");
        int choice = scanner.nextInt();
        return choice;
    }
}
