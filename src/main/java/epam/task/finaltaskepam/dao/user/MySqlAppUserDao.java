package epam.task.finaltaskepam.dao.user;

import epam.task.finaltaskepam.dto.AppUserDto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.DBConnectionManager;
import epam.task.finaltaskepam.repo.pool.ConnectionPoolException;
import epam.task.finaltaskepam.repo.pool.ConnectionPoolImpl;
import epam.task.finaltaskepam.repo.request.Request;
import epam.task.finaltaskepam.util.Util;
import org.apache.log4j.Logger;


/**
 * @author Aleksandr Ovcharenko
 */
public class MySqlAppUserDao extends AppUserDto implements UserDao {

    public static final Logger logger = Logger.getLogger(MySqlAppUserDao.class);
    private static final long serialVersionUID = 5566685127381260993L;

    public static final MySqlAppUserDao instance = new MySqlAppUserDao();

    public static MySqlAppUserDao getInstance() {
        return instance;
    }

    @Override
    public AppUserDto authorize(String login, String password) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolImpl.getInstance().takeConnection();

            st = con.prepareStatement(Request.LOG_IN_STATEMENT);

            st.setString(1, login);
            st.setString(2, password);

            rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            }

            AppUserDto user = new AppUserDto();
            user.setUsername(rs.getString(1));
            user.setEmail(rs.getString(2));
            user.setRole(rs.getString(3));
            return user;

        } catch (SQLException e) {
            throw new DaoRuntimeException("Login sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Login pool connection error", e);
        } finally {
            Util.closeResource(con, st, rs);
        }
    }

    @Override
    public AppUserDto register(String login, String email, String password) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolImpl.getInstance().takeConnection();

            st = con.prepareStatement(Request.CREATE_USER);
            st.setString(1, login);
            st.setString(2, email);
            st.setString(3, password);
            int i = st.executeUpdate();

            if (i > 0) {
                return authorize(login, password);
            }

        } catch (SQLException e) {
            throw new DaoRuntimeException("Register sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DaoRuntimeException("Login pool connection error", e);
        } finally {
            Util.closeResource(con, st);
        }
        return null;
    }

    @Override
    public List<AppUserDto> getAllUsers() {
        return null;
    }

}
