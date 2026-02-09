package barn.commands;

import barn.storage.Storage;
import barn.tasks.Task;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Command class that adds a task to the tasklist, then saves it in the storage.
 */
public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Add task to tasklist and save
     *
     * @return String containing either the successful text or an error
     */
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
