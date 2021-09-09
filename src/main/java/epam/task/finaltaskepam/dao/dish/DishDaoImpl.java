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
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class DishDaoImpl implements DishDao {
    private static final Logger logger = LogManager.getLogger(DishDaoImpl.class);

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
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
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
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
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
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
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
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
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
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public Dish getDishById(int id) throws DaoRuntimeException {
        return null;
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
            throw new DaoRuntimeException("Add dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }

        return null;
    }

    @Override
    public List<Dish> replenishStock(int dishId, int amount) throws DaoRuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.REPLENISH_STOCK);

            statement.setInt(1, amount);
            statement.setLong(2, dishId);
            int i = statement.executeUpdate();

            if (i > 0) {
                return getAllDishes();
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("Replenish stock sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Replenish stock  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }

        return null;
    }
}
