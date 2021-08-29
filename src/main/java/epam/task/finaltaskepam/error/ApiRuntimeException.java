package epam.task.finaltaskepam.error;

public class ApiRuntimeException extends RuntimeException {
    public ApiRuntimeException(){}

    public ApiRuntimeException(String massage){
        super(massage);
    }

    public ApiRuntimeException(Throwable cause){
        super(cause);
    }

    public ApiRuntimeException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
