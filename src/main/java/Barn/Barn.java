package Barn;
import Barn.commands.Command;
import Barn.parser.Parser;
import Barn.storage.Storage;
import Barn.tasks.tasklist.TaskList;
import Barn.ui.Ui;

public class Barn {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Barn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
    }
}
    public static void main(String[] args) {
        new Barn("data/tasks.txt").run();
    }
}