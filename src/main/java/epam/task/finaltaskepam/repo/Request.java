package epam.task.finaltaskepam.repo;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
    public static final String CREATE_USER = "insert into users(user_id, username, email, password, role) values(?, ?, ?, ?, 'customer')";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";
    public static final String ALL_USER = "SELECT * FROM users";
    public static final String CREATE_PURSE_FOR_USER = "insert into purse(user_id, amount) values(?, ?)";
    public static final String TOP_UP_A_PURSE = "update purse set amount=amount+? where user_id=?";

    public static final String CHECK_USER_PURSE = "select exists(select user_id from check where user_id=?)";
    public static final String FIND_USER_PURSE = "select * from purse where user_id=?";
    public static final String MAKE_AN_ORDER = "INSERT INTO dishes_orders VALUES (?, ?)";
    public static final String SHOW_AN_ORDER = "select * from order where user_id=?";
    public static final String SHOW_ALL_ORDERS = "select * from order";

    public static final String SHOW_MENU = "select * from dish";
    public static final String ADD_DISH = "insert into dish(dish_name, price, category, amount) values(?, ?, ?, ?)";
    public static final String SHOW_MENU_BY_CATEGORY = "select * from dish where category=?";
    public static final String SHOW_DISH_BY_NAME = "select * from dish where name=?";
    public static final String SHOW_MENU_SORTING_BY_LOW_PRICE = "select * from dish order by price DESC";
    public static final String SHOW_MENU_SORTING_BY_HIGH_PRICE = "select * from dish order by price ASC";
    public static final String REPLENISH_STOCK = "update dish set amount=amount+? where dish_id=?";

    private Request() {
    }
}
