package epam.task.finaltaskepam.dao.dish;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.DaoRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface DishDao {

    List<Dish> getAllDishes() throws DaoRuntimeException;

    List<Dish> getDishesByHighPrice() throws DaoRuntimeException;

    List<Dish> getDishesByLowPrice() throws DaoRuntimeException;

    List<Dish> getDishesByCategory(String category) throws DaoRuntimeException;

    List<Dish> getDishByName(String name) throws DaoRuntimeException;

    Dish getDishById(int id) throws DaoRuntimeException;

    List<Dish> addDish(String name, int price, String category, int amount) throws DaoRuntimeException;

    List<Dish> replenishStock(int dishId, int amount) throws DaoRuntimeException;
}
