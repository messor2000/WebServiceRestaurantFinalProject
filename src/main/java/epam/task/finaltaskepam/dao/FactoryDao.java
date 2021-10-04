package epam.task.finaltaskepam.dao;

import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dao.dish.DishDaoImpl;
import epam.task.finaltaskepam.dao.order.OrderDao;
import epam.task.finaltaskepam.dao.order.OrderDaoImpl;
import epam.task.finaltaskepam.dao.user.AppUserDaoImpl;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;

/**
 * @author Aleksandr Ovcharenko
 *
 * FactoryDao represents the factory for obtaining DAO objects.
 *
 */
public class FactoryDao {

    private static final FactoryDao INSTANCE = new FactoryDao();

    private FactoryDao() {
    }

    public static FactoryDao getInstance() {
        return INSTANCE;
    }

    private final AppUserDao appUserDao = AppUserDaoImpl.getInstance();
    private final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();
    private final DishDao dishDao = DishDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    public AppUserDao getUserDao() {
        return appUserDao;
    }

    public ConnectionPoolImpl getConnectionPool() {
        return connectionPool;
    }

    public DishDao getDishDao() {
        return dishDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
