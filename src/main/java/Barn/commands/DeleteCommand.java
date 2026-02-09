package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

/**
 * Command class that deletes a task in the tasklist, then saves it in the
 * storage.
 */
public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.delete(index);
            storage.save(tasks);
            return ui.showDeleteTask(task) + System.lineSeparator() + ui.showTaskCount(tasks);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
