package su.rj._3;

public class ServerError extends RuntimeException {
    public ServerError(String message) {
        super(message);
    }

    public ServerError(Throwable cause) {
        super(cause);
    }

    public ServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerError() {
        super();
    }
}
