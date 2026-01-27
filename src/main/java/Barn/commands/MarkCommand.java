package commands;

import storage.Storage;
import tasks.Task;
import tasks.tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command{

    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.mark(index);
            ui.showMark(task);
            storage.save(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
