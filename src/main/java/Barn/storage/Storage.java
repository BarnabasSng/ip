package Barn.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public enum TaskType {
        T,
        D,
        E
    }

    public static final Pattern TASK_SAVE_FORMAT = Pattern.compile("^(?<taskType>[TDE]) \\| (?<arguments>.*)$");

    public static final Pattern TODO_SAVE_FORMAT = Pattern.compile("(?<doneFlag>[01]) \\| (?<description>.+)");

    public static final Pattern DEADLINE_SAVE_FORMAT = Pattern
            .compile("(?<doneFlag>[01]) \\| (?<description>.+) \\| (?<by>.+)");

    public static final Pattern EVENT_SAVE_FORMAT = Pattern
            .compile("(?<doneFlag>[01]) \\| (?<description>.+) \\| (?<from>.+) \\| (?<to>.+)");

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
            String line = s.nextLine();
            Matcher matcher = TASK_SAVE_FORMAT.matcher(line.trim());
            if (!matcher.matches()) {
                throw new LoadingException();
            }

            String taskLetter = matcher.group("taskType").toUpperCase();
            String arguments = matcher.group("arguments");

            TaskType taskType;
            try {
                taskType = TaskType.valueOf(taskLetter);
            } catch (IllegalArgumentException e) {
                throw new LoadingException();
            }

            Task task;
            switch (taskType) {
                case T:
                    task = getTodoTask(arguments);
                    break;
                case D:
                    task = getDeadlineTask(arguments);
                    break;
                case E:
                    task = getEventTask(arguments);
                    break;
                default:
                    throw new LoadingException();
            }
            tasks.add(task);
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

    private Task getTodoTask(String arguments) throws LoadingException {
        Matcher matcher = TODO_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description");
        Task task = new Todo(description);
        if (matcher.group("doneFlag").equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private Task getDeadlineTask(String arguments) throws LoadingException {
        Matcher matcher = DEADLINE_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description");
        String by = matcher.group("by");
        Task task = new Deadline(description, by);
        if (matcher.group("doneFlag").equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private Task getEventTask(String arguments) throws LoadingException {
        Matcher matcher = EVENT_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        Task task = new Event(description, from, to);
        if (matcher.group("doneFlag").equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
