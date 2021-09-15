package dao;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dao.order.OrderDao;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderDaoTests {

    private static final Logger logger = LogManager.getLogger(DishDaoTests.class);

    @Test
    public void addOrderTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        OrderDao orderDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            orderDao = factory.getOrderDao();

            pool.init();
            int userId = 10;

            Order order = orderDao.createAnOrder(userId);

            orderDao.deleteOrder(userId);

            Assert.assertNotEquals(order, null);
            Assert.assertEquals(userId, order.getOrderId());
            Assert.assertEquals(userId, order.getUserId());
            Assert.assertEquals(Status.WAITING_FOR_PAY, order.getOrderStatus());
        } catch (ConnectionPoolException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }

    @Test
    public void showAllOrdersTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        OrderDao orderDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            orderDao = factory.getOrderDao();

            pool.init();
            int userId = 10;

            List<Order> ordersBefore = orderDao.showAllOrders();

            orderDao.createAnOrder(userId);

            List<Order> ordersAfter = orderDao.showAllOrders();

            orderDao.deleteOrder(userId);

            Assert.assertNotEquals(ordersAfter, null);
            Assert.assertEquals(ordersBefore.size()+1, ordersAfter.size());
        } catch (ConnectionPoolException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }

    @Test
    public void showDishesInOrderTest() {
//        FactoryDao factory;
//        ConnectionPoolImpl pool = null;
//        OrderDao orderDao;
//        DishDao dishDao;
//        try{
//            factory = FactoryDao.getInstance();
//            pool = ConnectionPoolImpl.getInstance();
//            orderDao = factory.getOrderDao();
//            dishDao = factory.getDishDao();
//
//            pool.init();
//            int userId = 10;
//
//            List<Dish> dishes = dishDao.getDishByName("Ice Cake");
//            Dish dish = dishes.get(0);
//
//            Order order = orderDao.createAnOrder(userId);
//
//            orderDao.putDishIntoOrder(dish.getName(), order.getOrderId());
//
//            Set<Dish> dishesFromOrder = orderDao.showDishesInOrder(userId);
//
//            Dish dishFromOrder = dishesFromOrder.stream().toArray().g;
//
//            orderDao.deleteOrder(userId);
//
//            Assert.assertNotEquals(dishesFromOrder, null);
//            Assert.assertEquals(dish.getName(), dishFromOrder.getName());
//            Assert.assertEquals(dish.getCategory(), dishFromOrder.getCategory());
//            Assert.assertEquals(dish.getPrice(), dishFromOrder.getPrice());
//
//        } catch (ConnectionPoolException | DaoRuntimeException e) {
//            logger.log(Level.WARN, e);
//        } finally {
//            try {
//                assert pool != null;
//                pool.destroy();
//            } catch (ConnectionPoolException e) {
//                logger.log(Level.WARN, e);
//            }
//        }
    }
}
