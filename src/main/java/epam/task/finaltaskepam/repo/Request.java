package epam.task.finaltaskepam.repo;

/**
 * @author Aleksandr Ovcharenko
 */
public class Request {
    public static final String CREATE_USER = "insert into users(username, email, password, role) values(?, ?, ?, 'customer')";
    public static final String LOG_IN_STATEMENT = "SELECT * FROM users WHERE username=? and password=?";
    public static final String ALL_USER = "SELECT * FROM users";
    public static final String CREATE_PURSE_FOR_USER = "insert into purse(user_id, amount) values(?, ?)";
    public static final String TOP_UP_A_PURSE = "update purse set amount=amount+? where user_id=?";
    public static final String FIND_USER_PURSE = "select * from purse where user_id=?";
    public static final String FIND_USER_BY_USERNAME = "select * from users where username=?";
    public static final String DELETE_USER_BY_USERNAME = "delete from users where username=?";

//    public static final String CREATE_AN_ORDER = "insert into orders(order_id, order_status, user_id, creation_date, update_date) values(?, 'WAITING_FOR_ACCEPT', ?, ?, ?)";
//    public static final String CREATE_AN_ORDER = "insert into orders(order_id, order_status, user_id) values(?, 'WAITING_FOR_ACCEPT', ?)";
    public static final String CREATE_AN_ORDER = "insert into orders(order_status, user_id) values(?, ?)";
    public static final String ADD_DISH_INTO_ORDER = "insert into dishes_orders(dish_name, order_id) values(?, ?)";
    public static final String SHOW_DISHES_IN_ORDER = "select dish_name from dishes_orders where order_id=?";
//    public static final String SHOW_USER_ORDER = "select * from orders where user_id=?";
    public static final String SHOW_USER_ORDER = "SELECT * FROM final_task_restaurant.orders where user_id=?";
    public static final String SHOW_ALL_ORDERS = "select * from orders";
    public static final String DELETE_ORDER_BY_ID = "delete from orders where order_id=?";
    public static final String FIND_ORDER_BY_ID = "select * from orders where order_id=?";
    public static final String CHANGE_ORDER_STATUS = "update orders set order_status=? where order_id=?";
    public static final String PAY_FOR_DISH = "update purse set amount=amount-? where user_id=?";
    public static final String DELETE_DISH_FROM_ORDER = "delete from dishes_orders where (order_id=?) and (dish_name=?)";

//(`order_id` = '6') and (`dish_name` = 'Big Mac')

    public static final String SHOW_MENU = "select * from dish";
    public static final String ADD_DISH = "insert into dish(dish_name, price, category, amount) values(?, ?, ?, ?)";
    public static final String SHOW_MENU_BY_CATEGORY = "select * from dish where category=?";
    public static final String SHOW_DISH_BY_NAME = "select * from dish where dish_name=?";
    public static final String SHOW_DISH_BY_ID = "select * from dish where dish_id=?";
    public static final String SHOW_MENU_SORTING_BY_HIGH_PRICE = "select * from dish order by price DESC";
    public static final String SHOW_MENU_SORTING_BY_LOW_PRICE = "select * from dish order by price ASC";
    public static final String REPLENISH_STOCK = "update dish set amount=amount+? where dish_name=?";
    public static final String DELETE_DISH_BY_NAME = "delete from dish where dish_name=?";

    private Request() {
    }
}
