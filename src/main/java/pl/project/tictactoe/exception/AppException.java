package pl.project.tictactoe.exception;

public class AppException extends RuntimeException {
    private final int statusCode;

    public AppException(String message) {
        super(message);
        this.statusCode = 400;  // Default to 400 Bad Request
    }

    public AppException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
