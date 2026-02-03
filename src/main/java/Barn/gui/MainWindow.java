package Barn.gui;

import Barn.Barn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Barn Barn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image BarnImage = new Image(this.getClass().getResourceAsStream("/images/DaBarn.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Barn instance */
    public void setBarn(Barn b) {
        Barn = b;
        dialogContainer.getChildren().addAll(DialogBox.getBarnDialog(Barn.showWelcome(), BarnImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Barn's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Barn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBarnDialog(response, BarnImage));
        userInput.clear();
    }
}
