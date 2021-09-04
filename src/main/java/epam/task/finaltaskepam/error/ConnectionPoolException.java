package epam.task.finaltaskepam.error;

/**
 * @author Aleksandr Ovcharenko
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Exception e) {
        super(s, e);
    }
}
