package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

/**
 * Command class that adds a task to the tasklist, then saves it in the storage.
 */
public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            storage.save(tasks);
            return ui.showAddTask(task) + System.lineSeparator() + ui.showTaskCount(tasks);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
