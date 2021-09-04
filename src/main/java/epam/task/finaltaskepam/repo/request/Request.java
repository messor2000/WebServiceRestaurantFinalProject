package epam.task.finaltaskepam.repo.request;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
//    public static final String CREATE_USER = "INSERT INTO users('username', 'email', 'password', 'role') VALUES (?, ?, ?, 'user')";
    public static final String CREATE_USER = "insert into users(username, email, password, role) values(?, ?, ?, 'user')";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";


    private Request(){}
}
