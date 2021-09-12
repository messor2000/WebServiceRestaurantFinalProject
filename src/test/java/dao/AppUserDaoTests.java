package dao;

import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.user.AppUserDao;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class AppUserDaoTests {

    @Test
    public void registerUserTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userDao = factory.getUserDao();

            pool.init();
            String userNickname= "testUser";
            String userEmail= "testuser@gmail.com";
            String userPass= "testUserPass";

            userDao.register(userNickname, userEmail, userPass);
            AppUser user = userDao.getUserByNickname(userNickname);

            userDao.deleteUser(userNickname);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(userNickname, user.getUsername());
            Assert.assertEquals(userEmail, user.getEmail());

        } catch (ConnectionPoolException | DaoRuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void authorizeUserTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userDao = factory.getUserDao();

            pool.init();
            String username= "testUser";
            String userEmail= "testuser@gmail.com";
            String password= "testUserPass";

            userDao.register(username, userEmail, password);

            AppUser user = userDao.authorize(username, password);

            userDao.deleteUser(username);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(username, user.getUsername());
            Assert.assertEquals(userEmail, user.getEmail());

        } catch (ConnectionPoolException | DaoRuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getAllUsers() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userDao = factory.getUserDao();

            pool.init();
            String userNickname= "testUser";
            String userEmail= "testuser@gmail.com";
            String userPass= "testUserPass";

            List<AppUser> usersBefore = userDao.getAllUsers();

            userDao.register(userNickname, userEmail, userPass);

            List<AppUser> usersAfter = userDao.getAllUsers();

            userDao.deleteUser(userNickname);

            Assert.assertNotEquals(usersAfter, null);
            Assert.assertEquals(usersBefore.size()+1, usersAfter.size());

        } catch (ConnectionPoolException | DaoRuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getUserPurseAmountTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userDao = factory.getUserDao();

            pool.init();
            String userNickname= "testUser";
            String userEmail= "testuser@gmail.com";
            String userPass= "testUserPass";

            userDao.register(userNickname, userEmail, userPass);

            AppUser user = userDao.getUserByNickname(userNickname);

            AppUserPurse purse = userDao.getPurseAmount(user.getIdUser());

            userDao.deleteUser(userNickname);

            Assert.assertNotEquals(purse, null);
            Assert.assertEquals(0, purse.getAmount());

        } catch (ConnectionPoolException | DaoRuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void fillUpUserPurseAmountTest() {
        FactoryDao factory;
        ConnectionPoolImpl pool = null;
        AppUserDao userDao;
        try{
            factory = FactoryDao.getInstance();
            pool = ConnectionPoolImpl.getInstance();
            userDao = factory.getUserDao();

            pool.init();
            String userNickname= "testUser";
            String userEmail= "testuser@gmail.com";
            String userPass= "testUserPass";

            userDao.register(userNickname, userEmail, userPass);

            AppUser user = userDao.getUserByNickname(userNickname);

            AppUserPurse purseBefore = userDao.getPurseAmount(user.getIdUser());
            AppUserPurse purseAfter = userDao.fillUpAPurse(user.getIdUser(), 50);

            userDao.deleteUser(userNickname);

            Assert.assertNotEquals(purseBefore, null);
            Assert.assertNotEquals(purseAfter, null);
            Assert.assertEquals(purseBefore.getAmount()+50, purseAfter.getAmount());

        } catch (ConnectionPoolException | DaoRuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }
}
