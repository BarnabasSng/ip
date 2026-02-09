package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

/**
 * Command class that marks a task as not done, then saves it in the storage.
 */
public class UnmarkCommand extends Command {

    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.unmark(index);
            storage.save(tasks);
            return ui.showUnmark(task);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
