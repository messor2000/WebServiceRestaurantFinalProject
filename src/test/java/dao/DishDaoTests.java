package dao;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dao.dish.DishDaoImpl;
import epam.task.finaltaskepam.dto.Dish;
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
public class DishDaoTests {

    private static final Logger logger = LogManager.getLogger(DishDaoTests.class);

    @Test
    public void addNewDishTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factory.getDishDao();

            pool.init();
            String dishName = "testDish";
            int price = 10;
            String dishCategory = "desert";
            int amount = 10;

            dishDao.addDish(dishName, price, dishCategory, amount);

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
    public void findDishByCategoryTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factory.getDishDao();

            pool.init();
            String dishCategory = "desert";

            List<Dish> dishes = dishDao.getDishesByCategory(dishCategory);

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
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factory.getDishDao();

            pool.init();
            String dishName = "testDish";
            int price = 10;
            String dishCategory = "desert";
            int amount = 0;

            dishDao.addDish(dishName, price, dishCategory, amount);

            dishDao.replenishStock(dishName, 10);

            List<Dish> dishes = dishDao.getDishByName(dishName);

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
    public void getDishByHighPriceTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factory.getDishDao();

            pool.init();
            String dishName = "testDish";
            int price = 10000;
            String dishCategory = "desert";
            int amount = 0;

            dishDao.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = dishDao.getDishesByHighPrice();

            Dish dish = dishes.get(0);
            System.out.println(dish.getPrice());

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
    public void getDishByLowPriceTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        DishDao dishDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            dishDao = factory.getDishDao();

            pool.init();
            String dishName = "testDish";
            int price = 1;
            String dishCategory = "desert";
            int amount = 0;

            dishDao.addDish(dishName, price, dishCategory, amount);

            List<Dish> dishes = dishDao.getDishesByLowPrice();

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
}
