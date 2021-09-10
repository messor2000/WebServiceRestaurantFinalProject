package epam.task.finaltaskepam.dao.order;

import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.Request;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final long serialVersionUID = 5566685127381260993L;

    private static final String ORDER_ID = "order_id";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER_ID = "user_id";


    public static final OrderDao instance = new OrderDaoImpl();

    public static OrderDao getInstance() {
        return instance;
    }

    @Override
    public void makeAnOrder(int orderId, int dishId) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.MAKE_AN_ORDER);

            statement.setInt(1, orderId);
            statement.setInt(2, dishId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoRuntimeException("Register sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Login pool connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    @Override
    public Order showUserOrder(int userId) throws ServiceRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> orders;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_AN_ORDER);

            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

//            List<Order> orders = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Order order = new Order.Builder()
//                        .withOrderId(resultSet.getInt(ORDER_ID))
//                        .withOrderStatus(Status.valueOf(resultSet.getString(ORDER_STATUS)))
//                        .withUserId(resultSet.getInt(USER_ID))
//                        .build();
//
//                orders.add(order);
//            }
//            return orders;

            if (resultSet.next()) {

                return new Order.Builder()
                        .withOrderId(resultSet.getInt(ORDER_ID))
                        .withOrderStatus(Status.valueOf(resultSet.getString(ORDER_STATUS)))
                        .withUserId(resultSet.getInt(USER_ID))
                        .build();
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }

        return null;
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
}
