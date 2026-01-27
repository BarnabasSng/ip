package commands;

import storage.Storage;
import tasks.Task;
import tasks.tasklist.TaskList;
import ui.Ui;

public class AddCommand extends Command{

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(task);
            ui.showAddTask(task);
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
