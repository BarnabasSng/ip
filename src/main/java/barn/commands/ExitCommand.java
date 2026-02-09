package barn.commands;

import barn.storage.Storage;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Command class that exits the application.
 */
public class ExitCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
