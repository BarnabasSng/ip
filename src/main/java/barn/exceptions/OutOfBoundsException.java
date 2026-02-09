package barn.exceptions;

/**
 * Exception where invalid index is given for tasklist
 */
public class OutOfBoundsException extends Exception {
    public OutOfBoundsException() {
        super("Index is out of bounds");
    }
}
