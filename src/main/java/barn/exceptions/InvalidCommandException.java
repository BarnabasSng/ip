package barn.exceptions;

/**
 * Exception for a command that is not part of the set of standard commands
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command");
    }

    public InvalidCommandException(String message) {
        super("Invalid command: " + message);
    }
}
