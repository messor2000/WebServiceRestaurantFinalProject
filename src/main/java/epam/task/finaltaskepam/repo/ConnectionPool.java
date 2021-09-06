package epam.task.finaltaskepam.repo;

import epam.task.finaltaskepam.error.ConnectionPoolException;

/**
 * @author Aleksandr Ovcharenko
 */
public interface ConnectionPool {
    void init() throws ConnectionPoolException;

    /**
     * This method is used to destroy the pool of connections with data source.
     *
     * @throws ConnectionPoolException if some error occurred while initializing ConnectionPool.
     */
    void destroy() throws ConnectionPoolException;
}
