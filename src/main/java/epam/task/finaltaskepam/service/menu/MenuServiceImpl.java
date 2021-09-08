package epam.task.finaltaskepam.service.menu;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class MenuServiceImpl implements MenuService {

    @Override
    public List<Dish> getMenu() throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.getAllDishes();
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }

    @Override
    public List<Dish> getDishesByHighPrice() throws DaoRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.getDishesByHighPrice();
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }

    @Override
    public List<Dish> getDishesByLowPrice() throws DaoRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.getDishesByLowPrice();
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }

    @Override
    public List<Dish> getDishesByCategory(String category) throws DaoRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.getDishesByCategory(category);
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }

    @Override
    public List<Dish> getDish(String name) throws DaoRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.getDishByName(name);
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }

    @Override
    public List<Dish> addDish(String name, int price, String category, int amount) throws DaoRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        DishDao dishDao = factoryDao.getDishDao();

        List<Dish> menu;

        try {
            menu = dishDao.addDish(name, price, category, amount);
            if (menu == null || menu.isEmpty()) {
                throw new ServiceRuntimeException("No dishes matching your query");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }

        return menu;
    }
}
