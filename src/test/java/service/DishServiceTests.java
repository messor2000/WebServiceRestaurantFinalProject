package service;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.menu.MenuService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class DishServiceTests {

    private static final Logger logger = LogManager.getLogger(DishServiceTests.class);

    @Test
    public void addDishTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            factoryService = FactoryService.getInstance();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 10;
            String dishCategory = "desert";
            int amount = 10;

            menuService.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = dishDao.getDishByName(dishName);

            Dish dish = dishes.get(0);

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dish, null);
            Assert.assertEquals(dishName, dish.getName());
            Assert.assertEquals(dishCategory, dish.getCategory());
            Assert.assertEquals(price, dish.getPrice());
            Assert.assertEquals(amount, dish.getAmount());

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
    public void getMenuTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 10;
            String dishCategory = "desert";
            int amount = 10;

            List<Dish> dishesBefore = menuService.getMenu();

            menuService.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishesAfter = menuService.getMenu();

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dishesAfter, null);
            Assert.assertEquals(dishesBefore.size()+1, dishesAfter.size());

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
    public void getDishesByHighPriceTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 10000;
            String dishCategory = "desert";
            int amount = 10;

            menuService.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = menuService.getDishesByHighPrice();

            Dish dish = dishes.get(0);

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dish, null);
            Assert.assertEquals(10000, dish.getPrice());

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
    public void getDishesByLowPriceTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 1;
            String dishCategory = "desert";
            int amount = 10;

            menuService.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = menuService.getDishesByLowPrice();

            Dish dish = dishes.get(0);

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dish, null);
            Assert.assertEquals(1, dish.getPrice());

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
    public void getDishesCategoryTest() {
        FactoryService factoryService;
        ConnectionPoolImpl pool = null;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishCategory = "desert";

            List<Dish> dishes = menuService.getDishesByCategory(dishCategory);

            Assert.assertNotEquals(dishes, null);
            for (Dish dish: dishes) {
                Assert.assertEquals(dishCategory, dish.getCategory());
            }
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
    public void replenishStockAmountDishTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 1;
            String dishCategory = "desert";
            int amount = 0;

            menuService.addDish(dishName, price, dishCategory, amount);

            menuService.replenishStock(dishName, 10);

            List<Dish> dishes = menuService.getDish(dishName);

            Dish dish = dishes.get(0);

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dish, null);
            Assert.assertEquals(amount+10, dish.getAmount());

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
    public void getDishByIdTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        MenuService menuService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factoryDao.getDishDao();
            menuService = factoryService.getMenuService();

            pool.init();
            String dishName = "testDish";
            int price = 10;
            String dishCategory = "desert";
            int amount = 10;

            menuService.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = dishDao.getDishByName(dishName);

            Dish dish = dishes.get(0);

            Dish dish1 = menuService.getDishById(dish.getDishId());

            dishDao.deleteDish(dishName);

            Assert.assertNotEquals(dish1, null);
            Assert.assertEquals(dish.getDishId(), dish1.getDishId());

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
