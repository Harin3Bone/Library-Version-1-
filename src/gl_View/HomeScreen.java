package gl_View;

import java.util.Scanner;

public class HomeScreen {
    public int homedisplay(){
        System.out.println("================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your property");
        System.out.println("1 - Login\t2 - Register\t3 - Exit");

        int ans_property = scanner.nextInt();
        return ans_property;
    }
}
