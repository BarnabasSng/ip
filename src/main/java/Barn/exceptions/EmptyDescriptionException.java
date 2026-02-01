package Barn.exceptions;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("Description for task cannot be empty");
    }
}
