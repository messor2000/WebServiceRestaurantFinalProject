package epam.task.finaltaskepam.service;

import epam.task.finaltaskepam.service.user.UserServiceImpl;

/**
 * @author Aleksandr Ovcharenko
 */
public class FactoryService {
    private static final FactoryService INSTANCE = new FactoryService();

    public static FactoryService getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl userService = new UserServiceImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }
}
