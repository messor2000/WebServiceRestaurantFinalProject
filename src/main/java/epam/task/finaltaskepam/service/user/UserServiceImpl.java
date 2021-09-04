package epam.task.finaltaskepam.service.user;

import com.google.protobuf.ServiceException;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.util.Util;
import epam.task.finaltaskepam.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Aleksandr Ovcharenko
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login    of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public AppUser authorize(String login, byte[] password) throws ServiceException {
        logger.debug("authorize begin");
        if (Validator.validateLogin(login) || !Validator.validatePassword(password)) {
            throw new ServiceRuntimeException("Wrong parameters!");
        }
        String encodedPassword = Util.encodePassword(password);
        FactoryDao factoryDao = FactoryDao.getInstance();
        AppUserDao dao = factoryDao.getUserDao();
        AppUser user;
        try {
            user = dao.authorize(login, encodedPassword);

            if (user == null) {
                throw new ServiceRuntimeException("Wrong login or password!");
            }
        } catch (DaoRuntimeException e) {
            throw new ServiceException("Error in source", e);
        }

        return user;
    }

    /**
     * This method is used to register and give access to the system for some new visitor.
     *
     * @param login       of user
     * @param email       of user
     * @param password    of user
     * @param passwordrepeat of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public AppUser register(String login, String email, byte[] password, byte[] passwordrepeat) throws ServiceException {
        if (!Validator.validate(login, email) || Validator.validateLogin(login)
                || !Validator.validatePassword(password, passwordrepeat) || !Validator.validateEmail(email)) {
            throw new ServiceRuntimeException("Check input parameters");
        }
        String encodedPassword = Util.encodePassword(password);
        FactoryDao factoryDao = FactoryDao.getInstance();
        AppUserDao dao = factoryDao.getUserDao();
        AppUser user;
        try {
            user = dao.register(login, email, encodedPassword);

            if (user == null) {
                throw new ServiceException("Wrong login or password!");
            }

        } catch (DaoRuntimeException | ServiceException e) {
            throw new ServiceException("Error in source!", e);
        }

        return user;
    }
}

