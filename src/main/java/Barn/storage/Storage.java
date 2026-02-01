package Barn.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Barn.exceptions.LoadingException;
import Barn.tasks.Deadline;
import Barn.tasks.Event;
import Barn.tasks.Task;
import Barn.tasks.Todo;
import Barn.tasks.tasklist.TaskList;

/**
 * Manages loading and writing tasks into the txt file in hard drive.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new txt file if file does not exist. Otherwise, scan
     * the txt file and load the given tasks into an ArrayList.
     * 
     * @return ArrayList of tasks in the given txt file
     * @throws LoadingException If txt file is not in a valid format
     * @throws IOException      If error while creating file
     */
    public ArrayList<Task> load() throws LoadingException, IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(this.filePath);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            try {
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
            } catch (Exception e) {
                throw new LoadingException();
            }
        }
        s.close();
        return tasks;
    }

    /**
     * Write the tasks into the given txt file
     * 
     * @param tasks TaskList containing tasks to be written
     * @throws IOException If error while writing tasks to txt file
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : tasks.getArr()) {
            fw.write(task.getFormattedString() + "\n");
        }
        fw.close();
    }
}
