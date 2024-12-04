package exception;

import java.io.IOException;

/**
 * Exception that should be thrown when the client can't connect to the server.
 */
public class ClientConnectionException extends Exception {
    private static final long serialVersionUID = 1L; // Explicit declaration of serialVersionUID

    public ClientConnectionException(String message, IOException e) {
        super(message, e);
    }
}
