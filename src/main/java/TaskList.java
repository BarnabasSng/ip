import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected String filePathString;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.filePathString = "./data/Barn.txt";
        this.createFile();
    }

    private void printTaskSize() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void add(Task task){
        this.tasks.add(task);
        System.out.println("Got it, I've added this task: \n"+ task);
        printTaskSize();
        try {
            this.updateFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
        try {
            updateFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmark(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        try {
            updateFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printTaskSize();
        try {
            updateFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getArr() {
        return this.tasks;
    }

    public void createFile() {
        Path filePath = Paths.get(this.filePathString);
        try {
            Files.createDirectories(filePath.getParent());
            File file = new File("./data/Barn.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFile() throws IOException{
        FileWriter fw = new FileWriter(this.filePathString);
        for (Task task : this.tasks) {
            fw.write(task.toString() + "/n");
        }
        fw.close();
    }
}
