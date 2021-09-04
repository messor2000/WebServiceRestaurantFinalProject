package epam.task.finaltaskepam.dao;

import epam.task.finaltaskepam.dao.user.AppUserDaoImpl;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.repo.pool.ConnectionPoolImpl;

/**
 * @author Aleksandr Ovcharenko
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

    public AppUserDao getUserDao() {
        return appUserDao;
    }

    public ConnectionPoolImpl getConnectionPool() {
        return connectionPool;
    }
}
