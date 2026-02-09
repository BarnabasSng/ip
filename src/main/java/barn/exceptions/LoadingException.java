package barn.exceptions;

/**
 * Exception when there is an error while loading txt files
 */
public class LoadingException extends Exception {
    public LoadingException() {
        super("Error when loading Barn.txt file");
    }
}
