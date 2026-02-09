package Barn.tasks.tasklist;

import java.util.ArrayList;

import Barn.exceptions.OutOfBoundsException;
import Barn.tasks.Task;

/**
 * TaskList represents a collection of tasks and allows for functionality
 * such as adding, marking, and deleting tasks
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) throws OutOfBoundsException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsException();
        }
    }

    public void add(Task task) {
        tasks.add(task);
        assert this.getTaskCount() > 0;
    }

    public void mark(int index) throws OutOfBoundsException {
        Task task = getTask(index);
        task.markAsDone();
    }

    public void unmark(int index) throws OutOfBoundsException {
        Task task = getTask(index);
        task.markAsNotDone();
    }

    public void delete(int index) throws OutOfBoundsException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsException();
        }
    }

    public ArrayList<Task> getArr() {
        return tasks;
    }

    /**
     * Search all tasks and find those with descriptions containing the keyword
     * 
     * @param keyword String of keyword to be searched
     * @return ArrayList containg tasks with matching descriptions
     */
    public ArrayList<Task> findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }
}
