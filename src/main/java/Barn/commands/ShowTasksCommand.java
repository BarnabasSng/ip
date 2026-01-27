package commands;

import storage.Storage;
import tasks.tasklist.TaskList;
import ui.Ui;

public class ShowTasksCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
