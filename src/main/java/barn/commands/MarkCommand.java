package barn.commands;

import barn.storage.Storage;
import barn.tasks.Task;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Command class that marks a task as done, then saves it in the storage.
 */
public class MarkCommand extends Command {

    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Mark task as done
     *
     * @return String containing the successful message or an error message
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.mark(index);
            storage.save(tasks);
            return ui.showMark(task);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
