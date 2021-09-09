package epam.task.finaltaskepam.dao.order;

import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface OrderDao {

    void makeAnOrder(int orderId, int dishId) throws DaoRuntimeException;

    Order showOrder(int userId) throws ServiceRuntimeException;

    List<Order> showAllOrders() throws ServiceRuntimeException;
}
