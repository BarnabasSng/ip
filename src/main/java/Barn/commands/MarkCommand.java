package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.Task;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class MarkCommand extends Command {

    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.mark(index);
            storage.save(tasks);
            return ui.showMark(task);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
