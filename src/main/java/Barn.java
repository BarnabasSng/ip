import java.util.ArrayList;
import java.util.Scanner;

public class Barn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
        while (true) {
            String userString = scanner.nextLine();
            if (userString.equals("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(String.valueOf(i+1)+ ". " + arr.get(i));
                };
                continue;
            }
            if (userString.equals("bye")) {
                break;
            }
            arr.add(userString);
            System.out.println("added: "+ userString);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}