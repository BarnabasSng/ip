package Barn.commands;

import Barn.exceptions.OutOfBoundsException;
import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfBoundsException {
        return ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
