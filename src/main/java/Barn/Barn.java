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

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String showWelcome() {
        return ui.showWelcome();
    }
}
