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
                throw new ServiceRuntimeException("No movies matching your query");
            }
//            Y.fillRatingsForMovie(ratingDAO, movies);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException("Error in source!", e);
        }
        return menu;
    }
}
