public class ShowTasksCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
