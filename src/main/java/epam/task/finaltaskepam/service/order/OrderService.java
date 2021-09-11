package epam.task.finaltaskepam.service.order;

import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderService {
    void makeAnOrder(int orderId, int dishName) throws ServiceRuntimeException;

    Order showOrder(int userId) throws ServiceRuntimeException;

    List<Order> showAllOrders() throws ServiceRuntimeException;
}
