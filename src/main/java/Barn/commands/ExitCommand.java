package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class ExitCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
