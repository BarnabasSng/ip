package exceptions;

public class LoadingException extends Exception{
    public LoadingException() {
        super("Error when loading Barn.txt file");
    }
}
