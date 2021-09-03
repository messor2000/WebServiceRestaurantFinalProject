package epam.task.finaltaskepam.service;

import epam.task.finaltaskepam.repo.pool.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.user.UserServiceImpl;

/**
 * @author Aleksandr Ovcharenko
 */
public class FactoryService {
    private static final FactoryService INSTANCE = new FactoryService();

    public static FactoryService getInstance() {
        return INSTANCE;
    }

    private final UserServiceImpl userService = new UserServiceImpl();
    private final ConnectionPoolImpl connectionPool = new ConnectionPoolImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }

    public ConnectionPoolImpl getConnectionPool() {
        return connectionPool;
    }
}
