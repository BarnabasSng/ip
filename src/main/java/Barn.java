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
            if (firstWord.equals("todo")) {
                String description = userString.split(" ", 2)[1];
                Todo task = new Todo(description);
                tasks.add(task);
            }
            if (firstWord.equals("deadline")) {
                String descriptionAndBy = userString.split(" ", 2)[1];
                String description = descriptionAndBy.split(" /by ")[0];
                String by = descriptionAndBy.split(" /by ")[1];
                Deadline task = new Deadline(description, by);
                tasks.add(task);
            }
            if (firstWord.equals("event")) {
                String descriptionAndFromTo = userString.split(" ", 2)[1];
                String description = descriptionAndFromTo.split(" /from | /to ")[0];
                String from = descriptionAndFromTo.split(" /from | /to ")[1];
                String to = descriptionAndFromTo.split(" /from | /to ")[2];
                Event task = new Event(description, from, to);
                tasks.add(task);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}