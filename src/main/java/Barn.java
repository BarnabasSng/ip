import java.util.Scanner;

public class Barn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
        while (true) {
            String userString = scanner.nextLine();
            if (userString.equals("bye")) {
                break;
            }
            System.out.println(userString);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}