package barn;

import barn.commands.Command;
import barn.parser.Parser;
import barn.storage.Storage;
import barn.tasks.tasklist.TaskList;
import barn.ui.Ui;

/**
 * Class representing chatbot that handles a Todo list of different tasks
 */
public class Barn {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /** Creates a Barn chatbot with the TaskList saved at specified file path */
    public Barn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        assert tasks != null;
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
