package epam.task.finaltaskepam.repo;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
    public static final String CREATE_USER = "insert into users(username, email, password, role) values(?, ?, ?, 'customer')";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";

    public static final String SHOW_MENU = "select * from dish";
    public static final String SHOW_MENU_BY_CATEGORY = "select * from dish where category=?";
    public static final String SHOW_MENU_SORTING_BY_LOW_PRICE = "select * from dish order by price DESC";
    public static final String SHOW_MENU_SORTING_BY_HIGH_PRICE = "select * from dish order by price ASC";

    private Request() {
    }
}
