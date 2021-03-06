package epam.task.finaltaskepam.dao.order;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderDao {

    Order createAnOrder(int userId) throws DaoRuntimeException;

    void putDishIntoOrder(String dishName, int orderId) throws DaoRuntimeException;

    Order showUserOrder(int userId) throws DaoRuntimeException;

    List<Order> showAllOrders() throws DaoRuntimeException;

    List<Dish> showDishesInOrder(int orderId) throws DaoRuntimeException;

    Order findOrderById(int orderId) throws DaoRuntimeException;

    void payForOrder(int orderId, int userId) throws DaoRuntimeException;

    void changeOrderStatus(int orderId, Status status) throws DaoRuntimeException;

    void deleteOrder(int orderId) throws DaoRuntimeException;

    void deleteDishFromOrder(int orderId) throws DaoRuntimeException;
}
