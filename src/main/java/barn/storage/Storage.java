package barn.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import barn.exceptions.LoadingException;
import barn.tasks.Deadline;
import barn.tasks.Event;
import barn.tasks.Task;
import barn.tasks.Todo;
import barn.tasks.tasklist.TaskList;

/**
 * Manages loading and writing tasks into the txt file in hard drive.
 */
public class Storage {

    public static final Pattern TASK_SAVE_FORMAT = Pattern.compile("^(?<taskType>[TDE]) \\| (?<arguments>.*)");
    public static final Pattern TODO_SAVE_FORMAT = Pattern.compile("(?<doneFlag>[01]) \\| (?<description>[^|]+)"
        + "(?: \\| (?<tags>(?:#\\w+\\s*)+))?");
    public static final Pattern DEADLINE_SAVE_FORMAT = Pattern
            .compile("(?<doneFlag>[01]) \\| (?<description>[^|]+) \\| (?<by>[^|]+)"
                    + "(?: \\| (?<tags>(?:#\\w+\\s*)+))?");
    public static final Pattern EVENT_SAVE_FORMAT = Pattern
            .compile("(?<doneFlag>[01]) \\| (?<description>[^|]+) \\| (?<from>[^|]+) \\| (?<to>[^|]+)"
                    + "(?: \\| (?<tags>(?:#\\w+\\s*)+))?");
    private static final String DONE = "1";
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Enum for the letters representing different task types
     */
    public enum TaskType {
        T,
        D,
        E
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
        File f = createFile();
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

            Task task = switch (taskType) {
            case T -> getTodoTask(arguments);
            case D -> getDeadlineTask(arguments);
            case E -> getEventTask(arguments);
            default -> throw new LoadingException();
            };
            tasks.add(task);
        }
        s.close();
        return tasks;
    }

    /**
     * Write the tasks into the given txt file.
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

    /**
     * Creates a Todo task from the given arguments.
     *
     * @param arguments String containing Todo description
     * @return new Todo task
     * @throws LoadingException If arguments is empty
     */
    private Task getTodoTask(String arguments) throws LoadingException {
        Matcher matcher = TODO_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description").trim();
        String tagString = matcher.group("tags");
        ArrayList<String> tags = new ArrayList<>();
        if (tagString != null && !tagString.isBlank()) {
            Collections.addAll(tags, tagString.trim().split("\\s+"));
        }
        Task task = new Todo(description, tags);
        if (matcher.group("doneFlag").equals(DONE)) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a Deadline task from the given arguments
     *
     * @param arguments String containing description and deadline date (indicated
     *                  by "/by" string)
     * @return new Deadline task
     * @throws LoadingException If description is empty or arguments does not
     *                          contain "/by" string
     */
    private Task getDeadlineTask(String arguments) throws LoadingException {
        Matcher matcher = DEADLINE_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description").trim();
        String by = matcher.group("by").trim();
        String tagString = matcher.group("tags");
        ArrayList<String> tags = new ArrayList<>();
        if (tagString != null && !tagString.isBlank()) {
            Collections.addAll(tags, tagString.trim().split("\\s+"));
        }
        Task task = new Deadline(description, by, tags);
        if (matcher.group("doneFlag").equals(DONE)) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates an Event task from the given arguments
     *
     * @param arguments String containing description, and time of event (indicated
     *                  by "/from" and "/to" strings)
     * @return new Event task
     * @throws LoadingException If description is empty or arguments does not
     *                          contain "/from" or "/to" strings
     */
    private Task getEventTask(String arguments) throws LoadingException {
        Matcher matcher = EVENT_SAVE_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new LoadingException();
        }
        String description = matcher.group("description").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();
        String tagString = matcher.group("tags");
        ArrayList<String> tags = new ArrayList<>();
        if (tagString != null && !tagString.isBlank()) {
            Collections.addAll(tags, tagString.trim().split("\\s+"));
        }
        Task task = new Event(description, from, to, tags);
        if (matcher.group("doneFlag").equals(DONE)) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a new txt file if file does not exist.
     *
     * @return Txt file of the todo list.
     * @throws IOException If error while creating file
     */
    private File createFile() throws IOException {
        File f = new File(this.filePath);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }
}
