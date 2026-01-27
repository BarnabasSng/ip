import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Barn\nWhat can I do for you?");
    }

    public  void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddTask(Task task) {
        System.out.println("Got it, I've added this task:");
        System.out.println(task);
    }

    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showTaskCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTaskCount() + " tasks in the list.");
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.println(String.valueOf(i+1)+ "." + tasks.getTask(i));
        };
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }
}