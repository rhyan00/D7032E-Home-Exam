package exception;

/**
 * Thrown when a user provides input that cannot be parsed as a valid number.
 */
public class NumberFormatInputException extends GameplayInputException {
    private static final long serialVersionUID = 1L;  // Unique identifier for serialization

    public NumberFormatInputException(String message) {
        super(message);
    }

    public NumberFormatInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
