package service;

import dao.DishDaoTests;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dao.order.OrderDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.order.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class OrderServiceTests {

    private static final Logger logger = LogManager.getLogger(DishDaoTests.class);

    @Test
    public void addOrderTest() {
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        OrderDao orderDao;
        FactoryDao factory;
        AppUserDao appUserDao;
        try{
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            factory = FactoryDao.getInstance();
            orderDao = factory.getOrderDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);

            Order order = orderService.createAnOrder(user.getIdUser());

            int userId = user.getIdUser();

            orderDao.deleteOrder(userId);
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        OrderDao orderDao;
        FactoryDao factory;
        AppUserDao appUserDao;

        try{
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            factory = FactoryDao.getInstance();
            orderDao = factory.getOrderDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);

            List<Order> ordersBefore = orderService.showAllOrders();

            Order order = orderService.createAnOrder(user.getIdUser());

            List<Order> ordersAfter = orderService.showAllOrders();

            orderDao.deleteOrder(order.getOrderId());
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        FactoryDao factory;
        OrderDao orderDao;
        DishDao dishDao;
        AppUserDao appUserDao;

        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            orderDao = factory.getOrderDao();
            dishDao = factory.getDishDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);

            int userId = user.getIdUser();

            List<Dish> dishes = dishDao.getDishByName("Ice Cake");
            Dish dish = dishes.get(0);

            Order order = orderService.createAnOrder(userId);

            orderService.makeAnOrder(dish.getName(), order.getOrderId());

            List<Dish> dishesFromOrder = orderService.showDishesInOrder(userId);

            Dish dishFromOrder = dishesFromOrder.get(0);

            orderDao.deleteOrder(userId);
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        FactoryDao factory;
        OrderDao orderDao;
        AppUserDao appUserDao;

        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            orderDao = factory.getOrderDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);
            int userId = user.getIdUser();

            Order order = orderService.createAnOrder(userId);

            Order orderFounded = orderService.showOrder(userId);

            orderDao.deleteOrder(order.getOrderId());
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        FactoryDao factory;
        OrderDao orderDao;
        AppUserDao appUserDao;

        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            orderDao = factory.getOrderDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);
            int userId = user.getIdUser();

            Order order = orderService.createAnOrder(userId);

            Order orderFounded = orderService.showOrderById(order.getOrderId());

            orderDao.deleteOrder(userId);
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        FactoryDao factory;
        OrderDao orderDao;
        AppUserDao appUserDao;

        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            orderDao = factory.getOrderDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);
            int userId = user.getIdUser();

            Order order = orderService.createAnOrder(userId);

            Assert.assertEquals(Status.WAITING_FOR_PAY, order.getOrderStatus());

            orderService.approveOrder(order.getOrderId(), Status.PAYED);

            Assert.assertEquals(Status.PAYED, order.getOrderStatus());

            orderService.approveOrder(order.getOrderId(), Status.COMPLETE);

            Assert.assertEquals(Status.COMPLETE, order.getOrderStatus());

            orderDao.deleteOrder(userId);
            appUserDao.deleteUser(user.getUsername());

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
        ConnectionPoolImpl pool = null;
        FactoryService factoryService;
        OrderServiceImpl orderService;
        FactoryDao factory;
        OrderDao orderDao;
        DishDao dishDao;
        AppUserDao appUserDao;

        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            factoryService = FactoryService.getInstance();
            orderService = factoryService.getOrderService();
            orderDao = factory.getOrderDao();
            dishDao = factory.getDishDao();
            appUserDao = factory.getUserDao();

            pool.init();
            String userNickname= "userForTests";
            String userEmail= "userForTests@gmail.com";
            String userPass= "testUserPass";
            AppUser user = appUserDao.register(userNickname, userEmail, userPass);
            int userId = user.getIdUser();

            List<Dish> dishes = dishDao.getDishByName("Ice Cake");
            Dish dish = dishes.get(0);

            Order order = orderService.createAnOrder(userId);

            orderService.makeAnOrder(dish.getName(), order.getOrderId());

            List<Dish> dishesFromOrder = orderService.showDishesInOrder(userId);

            Assert.assertNotEquals(dishesFromOrder, null);

            orderDao.deleteDishFromOrder(order.getOrderId());

            Assert.assertNull(dishesFromOrder);

            orderDao.deleteOrder(userId);
            appUserDao.deleteUser(user.getUsername());

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
