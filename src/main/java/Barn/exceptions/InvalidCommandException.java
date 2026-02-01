package Barn.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command");
    }

    public InvalidCommandException(String message) {
        super("Invalid command: " + message);
    }
}
