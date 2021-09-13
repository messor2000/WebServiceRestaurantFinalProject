package epam.task.finaltaskepam.service.order;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderService {
    public Order createAnOrder(int userId) throws ServiceRuntimeException;

    void makeAnOrder(String dishName, int orderId) throws ServiceRuntimeException;

    Order showOrder(int userId) throws ServiceRuntimeException;

    List<Order> showAllOrders() throws ServiceRuntimeException;

    List<Dish> showDishesInOrder(int orderId) throws ServiceRuntimeException;
}
