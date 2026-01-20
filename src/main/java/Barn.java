import java.util.Scanner;

public class Barn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
        while (true) {
            String userString = scanner.nextLine();
            String firstWord = userString.split(" ")[0];
            if (userString.equals("list")) {
                tasks.printList();
                continue;
            }
            if (userString.equals("bye")) {
                break;
            }
            if (firstWord.equals("mark")) {
                String[] splitString = userString.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                tasks.mark(index);
                continue;
            }
            if (firstWord.equals("unmark")) {
                String[] splitString = userString.split(" ");
                int index = Integer.parseInt(splitString[1]) - 1;
                tasks.unmark(index);
                continue;
            }
            else {
                Task task = new Task(userString);
                tasks.add(task);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}