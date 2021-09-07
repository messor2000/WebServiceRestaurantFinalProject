package epam.task.finaltaskepam.dao.user;

import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.Request;
import epam.task.finaltaskepam.util.Constants;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Aleksandr Ovcharenko
 */
public class AppUserDaoImpl extends AppUser implements AppUserDao {

    private static final Logger logger = LogManager.getLogger(AppUserDaoImpl.class);
    private static final long serialVersionUID = 5566685127381260993L;

    public static final AppUserDao instance = new AppUserDaoImpl();

    public static AppUserDao getInstance() {
        return instance;
    }

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String PASSWORD = "password";
    private static final String REGISTERED = "reg_date";

    private static final String USER_ID = "user_id";
    private static final String AMOUNT = "amount";

    @Override
    public AppUser authorize(String login, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.LOG_IN_STATEMENT, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

//            AppUser user = new AppUser();

            //            user.setIdUser(resultSet.getInt(1));
//            user.setUsername(resultSet.getString(USERNAME));
//            user.setEmail(resultSet.getString(EMAIL));
//            user.setRole(resultSet.getString(ROLE));

//            user.setPurse(createCheckForUser(user));

//            AppUser user = new AppUser.Builder()
//                    .withIdUser(resultSet.getInt(1))
//                    .withUsername(resultSet.getString(USERNAME))
//                    .withEmail(resultSet.getString(EMAIL))
//                    .withRole(resultSet.getString(ROLE))
//                    .withPurse(createCheckForUser(resultSet.getInt(1)))
//                    .build();

            //            createCheckForUser(user);

//            user.setPurse(createCheckForUser(user));

            return new Builder()
                    .withIdUser(resultSet.getInt(1))
                    .withUsername(resultSet.getString(USERNAME))
                    .withEmail(resultSet.getString(EMAIL))
                    .withRole(resultSet.getString(ROLE))
                    .withPurse(createCheckForUser(resultSet.getInt(1)))
                    .build();
        } catch (SQLException e) {
            throw new DaoRuntimeException("Login sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Login pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    @Override
    public AppUser register(String login, String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.CREATE_USER);

            statement.setString(1, login);
            statement.setString(2, email);
            statement.setString(3, password);
            int i = statement.executeUpdate();

            if (i > 0) {
                return authorize(login, password);
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("Register sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Login pool connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.ALL_USER);

            resultSet = statement.executeQuery();
            List<AppUser> users = new ArrayList<>();

            while (resultSet.next()) {
                AppUser user = new AppUser.Builder()
                        .withIdUser(resultSet.getInt(1))
                        .withUsername(resultSet.getString(USERNAME))
                        .withEmail(resultSet.getString(EMAIL))
                        .withRole(resultSet.getString(PASSWORD))
                        .withRole(resultSet.getString(ROLE))
//                        .withPurse(createCheckForUser(resultSet.getInt(1)))
                        .build();;
                users.add(user);


            }
            return users;

        } catch (SQLException e) {
            throw new DaoRuntimeException("Dish sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Dish pool connection error", e);
        } finally {
            Util.closeResource(connection, statement, resultSet);
        }
    }

    public AppUserPurse createCheckForUser(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.CREATE_PURSE_FOR_USER);

            long purseAmount = 0;

            statement.setInt(1, userId);
            statement.setLong(2, purseAmount);

            return new AppUserPurse.Builder()
                    .withIdAppUser(userId)
                    .withAmount(purseAmount)
                    .build();
        } catch (SQLException e) {
            throw new DaoRuntimeException("Create check sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Create check  connection error", e);
        } finally {
            Util.closeResource(connection, statement);
        }
    }


//    public AppUserPurse fillUpAPurse(int userId, int amount) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            connection = ConnectionPoolImpl.getInstance().takeConnection();
//
//            if (checkUserPurse(userId)) {
//
//            }
//
//            statement = connection.prepareStatement(Request.CREATE_PURSE_FOR_USER);
//
//            statement.setInt(1, userId);
//            statement.setLong(2, amount);
//
//            if (checkUserPurse(userId)) {
//
//            }
//
//                AppUserPurse purse = new AppUserPurse.Builder()
//                    .withIdAppUser(userId)
//                    .withAmount(amount)
//                    .build();
//
//            return purse;
//        } catch (SQLException e) {
//            throw new DaoRuntimeException("Create check sql error", e);
//        } catch (ConnectionPoolException e) {
//            throw new DaoRuntimeException("Create check  connection error", e);
//        } finally {
//            Util.closeResource(connection, statement);
//        }
//    }
//
//    private boolean checkUserPurse(int userId) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            connection = ConnectionPoolImpl.getInstance().takeConnection();
//
//            statement = connection.prepareStatement(Request.CHECK_USER_PURSE);
//
//            statement.setInt(1, userId);
//
//            int i = statement.executeUpdate();
//
////            if (i > 0) {
////                return true;
////            }
////
////            return false;
//            return i > 0;
//
//        } catch (SQLException e) {
//            throw new DaoRuntimeException("Create check sql error", e);
//        } catch (ConnectionPoolException e) {
//            throw new DaoRuntimeException("Create check  connection error", e);
//        } finally {
//            Util.closeResource(connection, statement);
//        }
//    }
//
//    private AppUserPurse createPurse(String userId) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            connection = ConnectionPoolImpl.getInstance().takeConnection();
//
//            statement = connection.prepareStatement(Request.FIND_USER_PURSE);
//
//            statement.setInt(1, userId);
//
//            int i = statement.executeUpdate();
//
////            if (i > 0) {
////                return true;
////            }
////
////            return false;
//            return i > 0;
//
//        } catch (SQLException e) {
//            throw new DaoRuntimeException("Create check sql error", e);
//        } catch (ConnectionPoolException e) {
//            throw new DaoRuntimeException("Create check  connection error", e);
//        } finally {
//            Util.closeResource(connection, statement);
//        }
//    }

    private static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

}
