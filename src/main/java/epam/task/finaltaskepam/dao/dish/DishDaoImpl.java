package epam.task.finaltaskepam.dao.dish;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.Request;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class DishDaoImpl extends Dish implements DishDao {

    private static final Logger logger = LogManager.getLogger(DishDaoImpl.class);
    private static final long serialVersionUID = 5566685127381260994L;

    private static final String DISH_ID = "dish_id";
    private static final String NAME = "dish_name";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String AMOUNT = "amount";

    private static final DishDao instance = new DishDaoImpl();

    private DishDaoImpl() {
    }

    public static DishDao getInstance() {
        return instance;
    }

    @Override
    public List<Dish> getAllDishes() throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_MENU);

            resultSet = statement.executeQuery();
            List<Dish> menu = new ArrayList<>();

            while (resultSet.next()) {
                Dish dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();

                menu.add(dish);
            }
            return menu;

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Dish> getDishesByHighPrice() throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_MENU_SORTING_BY_HIGH_PRICE);

            resultSet = statement.executeQuery();
            List<Dish> menu = new ArrayList<>();

            while (resultSet.next()) {
                Dish dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();

                menu.add(dish);
            }
            return menu;

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Dish> getDishesByLowPrice() throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_MENU_SORTING_BY_LOW_PRICE);

            resultSet = statement.executeQuery();
            List<Dish> menu = new ArrayList<>();

            while (resultSet.next()) {
                Dish dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();

                menu.add(dish);
            }
            return menu;

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }


    @Override
    public List<Dish> getDishesByCategory(String category) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_MENU_BY_CATEGORY);

            statement.setString(1, category);

            resultSet = statement.executeQuery();
            List<Dish> menu = new ArrayList<>();

            while (resultSet.next()) {
                Dish dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();

                menu.add(dish);
            }
            return menu;

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Dish> getDishByName(String name) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_DISH_BY_NAME);

            statement.setString(1, name);

            resultSet = statement.executeQuery();
            List<Dish> dishes = new ArrayList<>();

            while (resultSet.next()) {
                Dish dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();

                dishes.add(dish);
            }
            return dishes;

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public Dish getDishById(int id) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.SHOW_DISH_BY_ID);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            Dish dish = null;

            while (resultSet.next()) {
                 dish = new Dish.Builder()
                        .withDishId(resultSet.getInt(DISH_ID))
                        .withName(resultSet.getString(NAME))
                        .withPrice(resultSet.getLong(PRICE))
                        .withCategory(resultSet.getString(CATEGORY))
                        .withAmount(resultSet.getInt(AMOUNT))
                        .build();
            }

            return dish;
        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public List<Dish> addDish(String name, int price, String category, int amount) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.ADD_DISH);

            statement.setString(1, name);
            statement.setLong(2, price);
            statement.setString(3, category);
            statement.setInt(4, amount);

            int i = statement.executeUpdate();

            if (i > 0) {
                return getAllDishes();
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement);
        }

        return Collections.emptyList();
    }

    @Override
    public void replenishStock(String dishName, int amount) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.REPLENISH_STOCK);

            statement.setInt(1, amount);
            statement.setString(2, dishName);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoRuntimeException("Replenish stock sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Replenish stock  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    @Override
    public void deleteDish(String dishName) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.DELETE_DISH_BY_NAME);
            statement.setString(1, dishName);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoRuntimeException(SQL_ERROR, e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException(CONNECTION_POOL_ERROR, e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }

    private static final String SQL_ERROR = "Dish sql error";
    private static final String CONNECTION_POOL_ERROR = "Dish pool connection error";
}
