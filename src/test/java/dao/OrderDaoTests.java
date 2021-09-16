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

            Order order = orderDao.createAnOrder(userId);

            List<Order> ordersAfter = orderDao.showAllOrders();

            orderDao.deleteOrder(order.getOrderId());

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
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        OrderDao orderDao;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            orderDao = factory.getOrderDao();
            dishDao = factory.getDishDao();

            pool.init();
            int userId = 10;

            List<Dish> dishes = dishDao.getDishByName("Ice Cake");
            Dish dish = dishes.get(0);

            Order order = orderDao.createAnOrder(userId);

            orderDao.putDishIntoOrder(dish.getName(), order.getOrderId());

            List<Dish> dishesFromOrder = orderDao.showDishesInOrder(userId);

            Dish dishFromOrder = dishesFromOrder.get(0);

            orderDao.deleteOrder(userId);

            Assert.assertNotEquals(dishesFromOrder, null);
            Assert.assertEquals(dish.getName(), dishFromOrder.getName());
            Assert.assertEquals(dish.getCategory(), dishFromOrder.getCategory());
            Assert.assertEquals(dish.getPrice(), dishFromOrder.getPrice());

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
    public void showUserOrderTest() {
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

            Order orderFounded = orderDao.showUserOrder(userId);

            orderDao.deleteOrder(order.getOrderId());

            Assert.assertNotEquals(order, null);
            Assert.assertNotEquals(orderFounded, null);
            Assert.assertEquals(order.getOrderId(), orderFounded.getOrderId());
            Assert.assertEquals(order.getUserId(), orderFounded.getUserId());
            Assert.assertEquals(order.getOrderStatus(), orderFounded.getOrderStatus());
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
    public void showFindOrderByIdTest() {
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

            Order orderFounded = orderDao.findOrderById(order.getOrderId());

            orderDao.deleteOrder(userId);

            Assert.assertNotEquals(order, null);
            Assert.assertNotEquals(orderFounded, null);
            Assert.assertEquals(order.getOrderId(), orderFounded.getOrderId());
            Assert.assertEquals(order.getUserId(), orderFounded.getUserId());
            Assert.assertEquals(order.getOrderStatus(), orderFounded.getOrderStatus());
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
    public void changeOrderStatusTest() {
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

            Assert.assertEquals( Status.WAITING_FOR_PAY, order.getOrderStatus());

            orderDao.changeOrderStatus(order.getOrderId(), Status.PAYED);

            Assert.assertEquals( Status.PAYED, order.getOrderStatus());

            orderDao.changeOrderStatus(order.getOrderId(), Status.COMPLETE);

            Assert.assertEquals( Status.COMPLETE, order.getOrderStatus());

            orderDao.deleteOrder(userId);
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
    public void deleteDishFromOrderTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        OrderDao orderDao;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            orderDao = factory.getOrderDao();
            dishDao = factory.getDishDao();

            pool.init();
            int userId = 10;

            List<Dish> dishes = dishDao.getDishByName("Ice Cake");
            Dish dish = dishes.get(0);

            Order order = orderDao.createAnOrder(userId);

            orderDao.putDishIntoOrder(dish.getName(), order.getOrderId());

            List<Dish> dishesFromOrder = orderDao.showDishesInOrder(userId);

            Assert.assertNotNull(dishesFromOrder);

            orderDao.deleteDishFromOrder(order.getOrderId());

            Assert.assertNull(dishesFromOrder);

            orderDao.deleteOrder(userId);

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
}
