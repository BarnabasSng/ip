package barn.commands;

import barn.exceptions.OutOfBoundsException;
import barn.storage.Storage;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Command class that lists all tasks in the tasklist.
 */
public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfBoundsException {
        return ui.showTasks(tasks);
    }
}
