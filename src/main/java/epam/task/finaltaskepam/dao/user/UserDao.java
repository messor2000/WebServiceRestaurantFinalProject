package epam.task.finaltaskepam.dao.user;

import epam.task.finaltaskepam.dto.AppUser;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface UserDao {

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
}
