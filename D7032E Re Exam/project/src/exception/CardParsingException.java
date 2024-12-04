package exception;

public class CardParsingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardParsingException(String message) {
        super(message);
    }

    public CardParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
