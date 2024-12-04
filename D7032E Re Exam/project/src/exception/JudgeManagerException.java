package exception;

/**
 * Custom exception for handling errors related to the JudgeManager.
 */
public class JudgeManagerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JudgeManagerException(String message) {
        super(message);
    }

    public JudgeManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
