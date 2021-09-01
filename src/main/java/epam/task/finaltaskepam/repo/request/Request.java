package epam.task.finaltaskepam.repo.request;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
    public static final String CREATE_USER = "INSERT INTO users VALUES (?, ?, ?, 2)";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";



}
