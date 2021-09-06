package epam.task.finaltaskepam.util;

import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Aleksandr Ovcharenko
 */
public class Util {
    private static final Logger logger = LogManager.getLogger(Util.class);
    private static final ConnectionPoolImpl CONNECTION_POOL = ConnectionPoolImpl.getInstance();

    /**
     * This method is used to close opened resources such as PreparedStatement, ResultSet and
     * return Connection.
     * @param con Connection
     * @param st PreparedStatement
     * @param rs ResultSet
     */
    public static void closeResource(Connection con, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing result set");
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing statement");
            }
        }
        try {
            CONNECTION_POOL.returnConnection(con);
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e + "Exception while returning connection");
        }
    }

    /**
     * This method is used to close opened resource PreparedStatement and
     * return Connection.
     * @param con Connection
     * @param st PreparedStatement
     */
    public static void closeResource(Connection con, PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing statement");
            }
        }
        try {
            CONNECTION_POOL.returnConnection(con);
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e + "Exception while returning connection");
        }
    }

    public static String encodePassword(byte[] password) {
        return DigestUtils.md5Hex(password);
    }

    public static String encodePassword(String password) {
        return DigestUtils.md5Hex(password);
    }

    /**
     * This method is used to get previous query string if it exists,
     * in other case it returns default welcome page.
     * @param request
     * @return String previous query String
     */
    public static String getPreviousQuery(HttpServletRequest request) {
        String previousQuery = (String) request.getSession(false).getAttribute(Constants.PREVIOUS_QUERY);
        if (previousQuery == null) {
            previousQuery = Constants.WELCOME_PAGE;
        }
        return previousQuery;
    }

    /**
     * This method creates session for client if it does not exist
     * and sets query string attribute to this session object.
     * @param request
     */
    public static void saveCurrentQueryToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if (queryString == null) {
            session.setAttribute(Constants.SESSION_PREV_QUERY, requestURI);
        } else {
            session.setAttribute(Constants.SESSION_PREV_QUERY,
                    requestURI + Constants.SESSION_PREV_QUERY + queryString);
        }
    }

    /**
     * This method is used to get session language, if session exists
     * in other case returns russian language by default.
     * @param request
     * @return String language
     */
    public static Object getLanguage(HttpServletRequest request) {
        Object lang = request.getSession(false).getAttribute(Constants.LANGUAGE);
        if (lang == null) {
            return Constants.ENGLISH;
        }
        return lang;
    }
}
