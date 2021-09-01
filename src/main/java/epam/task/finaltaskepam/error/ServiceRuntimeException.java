package epam.task.finaltaskepam.error;

/**
 * @author Aleksandr Ovcharenko
 */
public class ServiceRuntimeException extends RuntimeException {
    public ServiceRuntimeException(){}

    public ServiceRuntimeException(String massage){
        super(massage);
    }

    public ServiceRuntimeException(Throwable cause){
        super(cause);
    }

    public ServiceRuntimeException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
