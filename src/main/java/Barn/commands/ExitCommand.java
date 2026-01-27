package commands;

import storage.Storage;
import tasks.tasklist.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
