package Barn.tasks.tasklist;

import java.util.ArrayList;

import Barn.tasks.Task;

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

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void add(Task task){
        tasks.add(task);
    }

    public void mark(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void unmark(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getArr() {
        return tasks;
    }
}
