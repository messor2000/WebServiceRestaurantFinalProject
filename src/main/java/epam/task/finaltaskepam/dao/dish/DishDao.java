package epam.task.finaltaskepam.dao.dish;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.DaoRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface DishDao {

    List<Dish> getAllDishes() throws DaoRuntimeException;

    List<Dish> getDishesByName(String name) throws DaoRuntimeException;

    List<Dish> getDishesByPrice(Long price) throws DaoRuntimeException;

    List<Dish> getDishesByNCategory(String category) throws DaoRuntimeException;

    Dish getDishById(int id) throws DaoRuntimeException;

    void addDish(int id, String name, long price, String category) throws DaoRuntimeException;
}
