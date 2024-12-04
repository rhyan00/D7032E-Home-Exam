package exception;

/**
 * Thrown when a user provides invalid input during gameplay.
 */
public class GameplayInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;  // Unique identifier for serialization

    public GameplayInputException(String message) {
        super(message);
    }

    public GameplayInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
