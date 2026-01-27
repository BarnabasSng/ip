package commands;

import storage.Storage;
import tasks.Task;
import tasks.tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.delete(index);
            ui.showDeleteTask(task);
            ui.showTaskCount(tasks);
            storage.save(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());;
        }
    }

    public boolean isExit() {
        return false;
    }
}
