import java.util.Scanner;

public class Barn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
        while (true) {
            String userString = scanner.nextLine();
            UserInputProcessor processor = new UserInputProcessor(userString);
            String command = processor.getCommand();
            if (command.equals("list")) {
                tasks.printList();
            }
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("mark")) {
                int index = processor.getIndex();
                tasks.mark(index);
            }
            if (command.equals("unmark")) {
                int index = processor.getIndex();
                tasks.unmark(index);
            }
            if (command.equals("todo")) {
                String description = processor.getTodoInfo();
                Todo task = new Todo(description);
                tasks.add(task);
            }
            if (command.equals("deadline")) {
                String[] deadlineInfo = processor.getDeadlineInfo();
                String description = deadlineInfo[0];
                String by = deadlineInfo[1];
                Deadline task = new Deadline(description, by);
                tasks.add(task);
            }
            if (command.equals("event")) {
                String[] eventInfo = processor.getEventInfo();
                String description = eventInfo[0];
                String from = eventInfo[1];
                String to = eventInfo[2];
                Event task = new Event(description, from, to);
                tasks.add(task);
            }
            // else {
            //     throw new InvalidCommandException();
            // }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}