package Barn.commands;

import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
