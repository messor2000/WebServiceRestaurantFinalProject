package epam.task.finaltaskepam.service.menu;

import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface MenuService {

    List<Dish> getMenu() throws ServiceRuntimeException;

    List<Dish> getDishesByHighPrice() throws DaoRuntimeException;

    List<Dish> getDishesByLowPrice() throws DaoRuntimeException;

    List<Dish> getDishesByCategory(String category) throws DaoRuntimeException;

    List<Dish> getDish(String name) throws DaoRuntimeException;

    List<Dish> addDish(String name, int price, String category, int amount) throws DaoRuntimeException;
}
