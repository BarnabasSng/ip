package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            storage.save(tasks);
            return ui.showAddTask(task) + System.lineSeparator() + ui.showTaskCount(tasks);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
