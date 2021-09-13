package epam.task.finaltaskepam.service.order;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.order.OrderDao;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createAnOrder(int userId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();
        Order order;

        try {
            order = orderDao.createAnOrder(userId);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source", e);
        }

        return order;
    }

    @Override
    public void makeAnOrder(String dishName, int orderId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();

        try {
            orderDao.putDishIntoOrder(dishName, orderId);
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

    public List<Dish> showDishesInOrder(int orderId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        OrderDao orderDao = factoryDao.getOrderDao();
        List<Dish> dishes;

        try {
            dishes = orderDao.showDishesInOrder(orderId);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source", e);
        }

        return dishes;
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
