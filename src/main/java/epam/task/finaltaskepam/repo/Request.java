package epam.task.finaltaskepam.repo;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
    public static final String CREATE_USER = "insert into users(username, email, password, role) values(?, ?, ?, 'customer')";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";

    public static final String SHOW_MENU = "select * from dish";

    private Request(){}
}
