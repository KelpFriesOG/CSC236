package labs.lab4;

public class QueueUnderflowException extends RuntimeException {
    public QueueUnderflowException() {
        super();
    }

    public QueueUnderflowException(String message) {
        super(message);
    }
}
