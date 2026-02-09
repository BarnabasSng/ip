package barn.commands;

import barn.exceptions.OutOfBoundsException;
import barn.storage.Storage;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Command class that finds tasks with description containing the keyword.
 */
public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Show tasks with description containing the keyword
     *
     * @return String containing the matching tasks or an error message
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList foundTasks = new TaskList(tasks.findTasksWithKeyword(this.keyword));
            return ui.showFoundTasks(foundTasks);
        } catch (OutOfBoundsException e) {
            return ui.showError(e.getMessage());
        }
    }
}
