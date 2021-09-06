package epam.task.finaltaskepam.service;

import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.menu.MenuServiceImpl;
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
    private final MenuServiceImpl menuService = new MenuServiceImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }

    public ConnectionPoolImpl getConnectionPool() {
        return connectionPool;
    }

    public MenuServiceImpl getMenuService() {
        return menuService;
    }
}
