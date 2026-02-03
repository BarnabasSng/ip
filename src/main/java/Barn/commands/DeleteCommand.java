package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.delete(index);
            storage.save(tasks);
            return ui.showDeleteTask(task) + System.lineSeparator() + ui.showTaskCount(tasks);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
