package exception;

/**
 * Custom exception for handling errors in the CardManager.
 */
public class CardManagerException extends Exception {
    private static final long serialVersionUID = 1L;

    public CardManagerException(String message) {
        super(message);
    }

    public CardManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
