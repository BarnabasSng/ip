import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LoadingException, IOException{
        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(this.filePath);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] splitLine = s.nextLine().split("\\s*\\|\\s*");
            String taskType = splitLine[0];
            int done = Integer.parseInt(splitLine[1]);
            String description = splitLine[2];
            Task task;
            switch (taskType) {
                case "T":
                    task = new Todo(description, done);
                    break;

                case "D":
                    String by = splitLine[3];
                    task = new Deadline(description, done, by);
                    break;

                case "E":
                    String from = splitLine[3];
                    String to = splitLine[4];
                    task = new Event(description, done, from, to);
                    break;

                default:
                    throw new LoadingException();
            }
            tasks.add(task);
        }
        s.close();
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task: tasks.getArr()) {
            fw.write(task.getFormattedString() + "\n");
        }
        fw.close();
    }
}
