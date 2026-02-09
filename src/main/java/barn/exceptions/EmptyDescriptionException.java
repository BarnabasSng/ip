package barn.exceptions;

/**
 * Exception for a task without a description
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("Description for task cannot be empty");
    }
}
