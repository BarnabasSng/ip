package barn.commands;

import barn.storage.Storage;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Abstract class represening a command to be executed.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
