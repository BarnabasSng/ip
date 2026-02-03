package Barn.commands;

import Barn.exceptions.OutOfBoundsException;
import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfBoundsException {
        TaskList foundTasks = new TaskList(tasks.findTasksWithKeyword(this.keyword));
        return ui.showFoundTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }
}
