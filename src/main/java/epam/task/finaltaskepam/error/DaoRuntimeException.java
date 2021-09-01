package epam.task.finaltaskepam.error;

public class DaoRuntimeException extends RuntimeException {
    public DaoRuntimeException(){}

    public DaoRuntimeException(String massage){
        super(massage);
    }

    public DaoRuntimeException(Throwable cause){
        super(cause);
    }

    public DaoRuntimeException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
