import java.util.Scanner;

public class Barn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
        mainLoop: while (true) {
            String userString = scanner.nextLine();
            Parser processor = new Parser(userString);
            String command = processor.getCommand();
            switch (command) {
                case "list": {
                    tasks.printList();
                    break;
                }

                case "bye": {
                    break mainLoop;
                }

                case "mark": {
                    int index = processor.getIndex();
                    tasks.mark(index);
                    break;
                }

                case "unmark": {
                    int index = processor.getIndex();
                    tasks.unmark(index);
                    break;
                }

                case "delete": {
                    int index = processor.getIndex();
                    tasks.delete(index);
                    break;
                }

                case "todo": {
                    String description;
                    try {
                        description = processor.getTodoInfo();
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Todo task = new Todo(description);
                    tasks.add(task);
                    break;
                }

                case "deadline": {
                    String[] deadlineInfo = processor.getDeadlineInfo();
                    String description = deadlineInfo[0];
                    String by = deadlineInfo[1];
                    Deadline task = new Deadline(description, by);
                    tasks.add(task);
                    break;
                }

                case "event": {
                    String[] eventInfo = processor.getEventInfo();
                    String description = eventInfo[0];
                    String from = eventInfo[1];
                    String to = eventInfo[2];
                    Event task = new Event(description, from, to);
                    tasks.add(task);
                    break;
                }

                default: {
                    InvalidCommandException e = new InvalidCommandException();
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}