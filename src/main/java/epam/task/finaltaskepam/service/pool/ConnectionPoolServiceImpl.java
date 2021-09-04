package epam.task.finaltaskepam.service.pool;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.pool.ConnectionPool;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.repo.pool.ConnectionPoolImpl;

/**
 * @author Aleksandr Ovcharenko
 */
public class ConnectionPoolServiceImpl implements ConnectionPool {
    public ConnectionPoolServiceImpl() {
    }

    @Override
    public void init() {
        try {
            FactoryDao factoryDao = FactoryDao.getInstance();
            ConnectionPoolImpl poolDAO = factoryDao.getConnectionPool();
            poolDAO.init();
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Cannot init the pool", e);
        }
    }

    @Override
    public void destroy() {
        try {
            FactoryDao factoryDao = FactoryDao.getInstance();
            ConnectionPoolImpl poolDAO = factoryDao.getConnectionPool();
            poolDAO.destroy();
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Cannot init the pool", e);
        }
    }
}
