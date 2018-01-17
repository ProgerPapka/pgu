package exception;

public class ElkServiceException extends Exception{

    public ElkServiceException() {
    }

    public ElkServiceException(String message) {
        super(message);
    }

    public ElkServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElkServiceException(Throwable cause) {
        super(cause);
    }
}
