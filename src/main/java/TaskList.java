import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private void printTaskSize() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it, I've added this task: \n"+ task);
        printTaskSize();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.valueOf(i+1)+ "." + tasks.get(i));
        };
    }

    public void mark(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmark(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printTaskSize();
    }
}
