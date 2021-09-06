package epam.task.finaltaskepam.dao.user;

import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.Request;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public AppUser authorize(String login, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().takeConnection();

            statement = connection.prepareStatement(Request.LOG_IN_STATEMENT);

            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            AppUser user = new AppUser();

            user.setUsername(resultSet.getString(USERNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setRole(resultSet.getString(ROLE));

            return user;
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

            if (connection != null) {
                connection.setAutoCommit(false);

                statement = connection.prepareStatement(Request.CREATE_USER);
                statement.setString(1, login);
                statement.setString(2, email);
                statement.setString(3, password);
                int i = statement.executeUpdate();

                if (i > 0) {
                    return authorize(login, password);
                }
                connection.commit();
            }

        } catch (SQLException e) {
            rollback(connection);
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
        return null;
    }

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
