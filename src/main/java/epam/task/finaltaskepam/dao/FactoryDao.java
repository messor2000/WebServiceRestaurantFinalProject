package epam.task.finaltaskepam.dao;

import epam.task.finaltaskepam.dao.user.MySqlAppUserDao;
import epam.task.finaltaskepam.dao.user.UserDao;
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

    private final UserDao userDao = MySqlAppUserDao.getInstance();
    private final ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();

    public UserDao getUserDao() {
        return userDao;
    }

    public ConnectionPoolImpl getConnectionPool() {
        return connectionPool;
    }
}
