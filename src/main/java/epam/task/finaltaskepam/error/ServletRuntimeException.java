package epam.task.finaltaskepam.error;

/**
 * @author Aleksandr Ovcharenko
 */
public class ServletRuntimeException extends RuntimeException {
    public ServletRuntimeException(){}

    public ServletRuntimeException(String massage){
        super(massage);
    }

    public ServletRuntimeException(Throwable cause){
        super(cause);
    }

    public ServletRuntimeException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
