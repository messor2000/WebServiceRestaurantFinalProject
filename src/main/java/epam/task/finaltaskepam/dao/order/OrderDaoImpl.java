package epam.task.finaltaskepam.dao.order;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
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
import java.util.List;

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

            statement.setInt(1, userId);
//            statement.setString(2, String.valueOf(Status.WAITING_FOR_ACCEPT));
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

//            Order order = null;
//
//            while (resultSet.next()) {
////                int orderId = resultSet.getInt(1);
//                order = new Order.Builder()
//                        .withOrderId(userId)
//                        .withUserId(userId)
//                        .withOrderStatus(Status.WAITING_FOR_ACCEPT)
//                        .withCreationDate(java.sql.Date.valueOf(CREATION_DATE))
//                        .withUpdateDate(java.sql.Date.valueOf(UPDATE_DATE))
//                        .build();
//            }
//
//            return order;
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

            statement = connection.prepareStatement(Request.ADD_DISH_INTO_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, dishName);
            statement.setInt(2, orderId);
            statement.executeUpdate();

//            if (i < 0) {
//
//                return getPurseAmount(userId);userId
//            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("Create check sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Create check  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }

//        return null;
    }

    @Override
    public Order showUserOrder(int userId) throws ServiceRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_USER_ORDER);

            statement.setInt(1, userId);

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
            for(String dishName: dishes) {
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
        return null;
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
}
