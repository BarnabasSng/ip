package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class FindCommand extends Command{

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = new TaskList(tasks.findTasksWithKeyword(this.keyword));
        ui.showFoundTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }
}
