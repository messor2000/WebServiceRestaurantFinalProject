package epam.task.finaltaskepam.service.order;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.order.OrderDao;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public void makeAnOrder(int orderId, int dishId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();

        try {
            orderDao.makeAnOrder(orderId, dishId);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source", e);
        }
    }

    @Override
    public Order showOrder(int userId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();
        Order order;

        try {
            order = orderDao.showUserOrder(userId);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source", e);
        }

        return order;
    }

    @Override
    public List<Order> showAllOrders() throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();
        List<Order> orders;

        try {
            orders = orderDao.showAllOrders();
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source", e);
        }

        return orders;
    }

}
