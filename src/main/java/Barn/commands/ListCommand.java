package Barn.commands;

import Barn.exceptions.OutOfBoundsException;
import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

/**
 * Command class that lists all tasks in the tasklist.
 */
public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfBoundsException {
        return ui.showTasks(tasks);
    }
}
