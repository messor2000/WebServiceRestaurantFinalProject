package epam.task.finaltaskepam.dao.order;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dao.user.AppUserDaoImpl;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.Request;
import epam.task.finaltaskepam.util.Constants;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderDaoImpl extends Order implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final long serialVersionUID = 5566685127381260993L;

    private static final String ORDER_ID = "order_id";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER_ID = "user_id";
    private static final String CREATION_DATE = "creation_date";
    private static final String UPDATE_DATE = "update_date";

    private static final String DISH_ID = "dish_id";
    private static final String DISH_NAME = "dish_name";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String AMOUNT = "amount";


    public static final OrderDao instance = new OrderDaoImpl();

    public static OrderDao getInstance() {
        return instance;
    }

    @Override
    public Order createAnOrder(int userId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.CREATE_AN_ORDER, Statement.RETURN_GENERATED_KEYS);

            Date dt = new java.util.Date();
            SimpleDateFormat sdf = new java.text.SimpleDateFormat(Constants.DATE_FORMAT);
            String currentTime = sdf.format(dt);

//            statement.setInt(1, userId);
            statement.setString(1, String.valueOf(Status.WAITING_FOR_PAY));
            statement.setInt(2, userId);
//            statement.setDate(3, java.sql.Date.valueOf(currentTime));
//            statement.setDate(4, java.sql.Date.valueOf(currentTime));

            if (statement.executeUpdate() != 1) {
                return null;
            }

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return showUserOrder(userId);
            }
        } catch (SQLException e) {
            throw new DaoRuntimeException("Create order sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Create check  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
        return null;
    }

    @Override
    public void putDishIntoOrder(String dishName, int orderId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(Request.ADD_DISH_INTO_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, dishName);
            statement.setInt(2, orderId);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            rollback(connection);
            throw new DaoRuntimeException("Create check sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Create check  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }

    }

    @Override
    public Order showUserOrder(int userId) throws ServiceRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_USER_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            Order order = new Order.Builder()
                    .withOrderId(resultSet.getInt(ORDER_ID))
                    .withOrderStatus(Status.valueOf(resultSet.getString(ORDER_STATUS)))
                    .withUserId(resultSet.getInt(USER_ID))
//                    .withCreationDate(java.sql.Date.valueOf(resultSet.getString(CREATION_DATE)))
//                    .withUpdateDate(java.sql.Date.valueOf(resultSet.getString(UPDATE_DATE)))
                    .build();

            if (order.getOrderStatus().equals(Status.COMPLETE)) {
                changeOrderStatus(order.getOrderId(), Status.WAITING_FOR_PAY);
            }

            return order;
        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Dish> showDishesInOrder(int orderId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();
            statement = connection.prepareStatement(Request.SHOW_DISHES_IN_ORDER);
            statement.setInt(1, orderId);

            resultSet = statement.executeQuery();

            DishDao dishDao = FactoryDao.getInstance().getDishDao();

            List<String> dishes = new ArrayList<>();

            while (resultSet.next()) {
                dishes.add(resultSet.getString(DISH_NAME));
            }

            List<Dish> allDishes = new ArrayList<>();
            for (String dishName : dishes) {
                allDishes.addAll(dishDao.getDishByName(dishName));
            }

            return allDishes;

        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Order> showAllOrders() throws ServiceRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_ALL_ORDERS);

            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = new Order.Builder()
                        .withOrderId(resultSet.getInt(ORDER_ID))
                        .withOrderStatus(Status.valueOf(resultSet.getString(ORDER_STATUS)))
                        .withUserId(resultSet.getInt(USER_ID))
                        .withCreationDate(resultSet.getDate(CREATION_DATE))
                        .build();

                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public Order findOrderById(int orderId) throws ServiceRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.FIND_ORDER_BY_ID, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orderId);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new Order.Builder()
                    .withOrderId(resultSet.getInt(ORDER_ID))
                    .withOrderStatus(Status.valueOf(resultSet.getString(ORDER_STATUS)))
                    .withUserId(resultSet.getInt(USER_ID))
//                    .withCreationDate(java.sql.Date.valueOf(resultSet.getString(CREATION_DATE)))
//                    .withUpdateDate(java.sql.Date.valueOf(resultSet.getString(UPDATE_DATE)))
                    .build();

        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public void payForOrder(int orderId, int userId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();
            connection.setAutoCommit(false);

            List<Dish> orderList = showDishesInOrder(orderId);
            int totalPrice = 0;
            int dishAmount = 0;
            for (Dish dish : orderList) {
                totalPrice += dish.getPrice();
            }

            AppUserPurse purse = AppUserDaoImpl.getInstance().getPurseAmount(userId);

            if (purse.getAmount() > totalPrice) {
//                connection = ConnectionPoolImpl.getInstance().takeConnection();
                statement = connection.prepareStatement(Request.PAY_FOR_DISH);
                statement.setInt(1, totalPrice);
                statement.setInt(2, userId);
                int i = statement.executeUpdate();

                if (i > 0) {
                    changeOrderStatus(orderId, Status.PAYED);
                }
                connection.commit();
            } else {
                throw new DaoRuntimeException("Dont enough money");
            }

        } catch (SQLException e) {
            rollback(connection);
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public void changeOrderStatus(int orderId, Status status) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(Request.CHANGE_ORDER_STATUS);

            statement.setString(1, String.valueOf(status));
            statement.setInt(2, orderId);
            statement.executeUpdate();

            if (status.equals(Status.COMPLETE)) {
                deleteDishFromOrder(orderId);
            }
            connection.commit();

        } catch (SQLException e) {
            rollback(connection);
            throw new DaoRuntimeException("Create check sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Create check  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    @Override
    public void deleteOrder(int orderId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.DELETE_ORDER_BY_ID);
            statement.setInt(1, orderId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoRuntimeException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("User pool connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    @Override
    public void deleteDishFromOrder(int orderId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();
            statement = connection.prepareStatement(Request.DELETE_DISH_FROM_ORDER);

            List<Dish> dishesInOrder = getInstance().showDishesInOrder(orderId);

            for (Dish dish : dishesInOrder) {
                statement.setInt(1, orderId);
                statement.setString(2, dish.getName());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("User pool connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    private static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }
}
