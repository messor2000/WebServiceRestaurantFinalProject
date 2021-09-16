package epam.task.finaltaskepam.service.user;

import com.google.protobuf.ServiceException;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.util.Util;
import epam.task.finaltaskepam.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class AppUserServiceImpl implements AppUserService {

    private static final Logger logger = LogManager.getLogger();

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login    of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceRuntimeException if any error occurred while processing method.
     */
    @Override
    public AppUser authorize(String login, byte[] password) throws ServiceRuntimeException {
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
            throw new ServiceRuntimeException(ERROR_IN_SOURCE, e);
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
     * @throws ServiceRuntimeException if any error occurred while processing method.
     */
    @Override
    public AppUser register(String login, String email, byte[] password, byte[] passwordrepeat) throws ServiceRuntimeException {
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
            throw new ServiceRuntimeException(ERROR_IN_SOURCE, e);
        }

        return user;
    }

    @Override
    public List<AppUser> showAllUsers() throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        AppUserDao dao = factoryDao.getUserDao();

        List<AppUser> users;

        try {
            users = dao.getAllUsers();
        } catch (DaoRuntimeException | ServiceRuntimeException e) {
            throw new ServiceRuntimeException(ERROR_IN_SOURCE, e);
        }


        return users;
    }

    @Override
    public AppUserPurse fillUpAPurse(int userId, int amount) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        AppUserDao dao = factoryDao.getUserDao();
        AppUserPurse purse;

        try {
            purse = dao.fillUpAPurse(userId, amount);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException(ERROR_IN_SOURCE, e);
        }

        return purse;
    }

    @Override
    public AppUserPurse checkPurseAmount(int userId) throws ServiceRuntimeException {
        FactoryDao factoryDao = FactoryDao.getInstance();
        AppUserDao dao = factoryDao.getUserDao();
        AppUserPurse purse;

        try {
            purse = dao.getPurseAmount(userId);
        } catch (DaoRuntimeException e) {
            throw new ServiceRuntimeException(ERROR_IN_SOURCE, e);
        }

        return purse;
    }

    private static final String ERROR_IN_SOURCE = "Error in source";
}

