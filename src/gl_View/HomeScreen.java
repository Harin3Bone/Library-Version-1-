package gl_View;

import java.util.Scanner;

public class HomeScreen {
    public int homedisplay(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your property");
        System.out.println("1 - Login\t2 - Register\t3 - ExitCommand");

        int ans_property = scanner.nextInt();
        return ans_property;
    }
}
