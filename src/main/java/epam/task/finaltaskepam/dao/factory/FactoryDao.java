package epam.task.finaltaskepam.dao.factory;

import epam.task.finaltaskepam.dao.user.MySqlAppUserDao;
import epam.task.finaltaskepam.dao.user.UserDao;
import epam.task.finaltaskepam.dto.AppUserDto;

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

    public UserDao getUserDao() {
        return userDao;
    }
}
