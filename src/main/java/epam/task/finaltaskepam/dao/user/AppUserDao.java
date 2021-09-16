package epam.task.finaltaskepam.dao.user;

import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.error.DaoRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface AppUserDao {

    /**
     * This method is used to authorize user in the system using data source.
     *
     * @param login    of user
     * @param password of user
     * @return filled User bean or null
     */
    AppUser authorize(String login, String password);

    /**
     * This method is used to add new user to the system and data source.
     *
     * @param login    of user
     * @param email    of user
     * @param password of user
     * @return filled User bean or null
     */
    AppUser register(String login, String email, String password);

    /**
     * This method is used to get all existing users from data source.
     *
     * @return list of filled User beans
     */
    List<AppUser> getAllUsers();

    AppUserPurse fillUpAPurse(int userId, int amount);

    AppUserPurse getPurseAmount(int userId);

    /**
     * This method is used to get detailed information about some user from data source.
     *
     * @param username of user
     * @return filled User bean.
     * @throws DaoRuntimeException if some error occurred while processing data.
     */
    AppUser getUserByNickname(String username) throws DaoRuntimeException;

    /**
     * This method is used to delete some user from the system and used only for tests!!!
     *
     * @param username of user
     * @throws DaoRuntimeException if some error occurred while processing data.
     */
    void deleteUser(String username) throws DaoRuntimeException;
}
