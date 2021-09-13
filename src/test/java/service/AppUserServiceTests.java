package service;

import dao.AppUserDaoTests;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.user.AppUserService;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class AppUserServiceTests {

    private static final Logger logger = LogManager.getLogger(AppUserServiceTests.class);

    @Test
    public void registerUserTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        AppUserService userService;

        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userService = factoryService.getUserService();
            userDao = factoryDao.getUserDao();

            pool.init();
            String username= "testUser";
            String email= "testuser@gmail.com";
            byte[] password = "password".getBytes();
            byte[] password2 = "password".getBytes();

            AppUser user = userService.register(username, email, password, password2);

            userDao.deleteUser(username);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(username, user.getUsername());
            Assert.assertEquals(email, user.getEmail());

        } catch (ConnectionPoolException | ServiceRuntimeException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }

    @Test
    public void authorizeUserTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;

        AppUserService userService;
        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userService = factoryService.getUserService();
            userDao = factoryDao.getUserDao();

            pool.init();
            String username= "testUser";
            String email= "testuser@gmail.com";
            byte[] password = "password".getBytes();

            String encodedPassword = Util.encodePassword(password);

            AppUser user = userService.authorize(username, password);

            AppUser foundedUser = userDao.getUserByNickname(username);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(foundedUser.getUsername(), user.getUsername());
            Assert.assertEquals(foundedUser.getEmail(), user.getEmail());

        } catch (ConnectionPoolException | ServiceRuntimeException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }

    @Test
    public void showAllUsersTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;

        AppUserService userService;
        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userService = factoryService.getUserService();
            userDao = factoryDao.getUserDao();

            pool.init();
            String username= "testUser";
            String email= "testuser@gmail.com";
            byte[] password = "password".getBytes();
            byte[] password2 = "password".getBytes();

            List<AppUser> usersBefore = userService.showAllUsers();

            AppUser user = userService.register(username, email, password, password2);

            List<AppUser> usersAfter = userService.showAllUsers();

            userDao.deleteUser(username);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(usersBefore.size()+1, usersAfter.size());

        } catch (ConnectionPoolException | ServiceRuntimeException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }

    @Test
    public void checkUserPurseAndFillUpTest() {
        FactoryService factoryService;
        FactoryDao factoryDao;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;

        AppUserService userService;
        try{
            factoryService = FactoryService.getInstance();
            factoryDao = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userService = factoryService.getUserService();
            userDao = factoryDao.getUserDao();

            pool.init();
            String username= "testUser";
            String email= "testuser@gmail.com";
            byte[] password = "password".getBytes();
            byte[] password2 = "password".getBytes();

            AppUser user = userService.register(username, email, password, password2);

            AppUserPurse purseBefore = userService.checkPurseAmount(user.getIdUser());
            userService.fillUpAPurse(user.getIdUser(), 100);
            AppUserPurse purseAfter = userService.checkPurseAmount(user.getIdUser());

            userDao.deleteUser(username);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(0, purseBefore.getAmount());
            Assert.assertEquals(100, purseAfter.getAmount());
            Assert.assertEquals(purseBefore.getAmount()+100, purseAfter.getAmount());

        } catch (ConnectionPoolException | ServiceRuntimeException | DaoRuntimeException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                logger.log(Level.WARN, e);
            }
        }
    }
}
