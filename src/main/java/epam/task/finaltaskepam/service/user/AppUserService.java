package epam.task.finaltaskepam.service.user;

import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.AppUserPurse;
import epam.task.finaltaskepam.error.ServiceRuntimeException;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public interface AppUserService {

    /**
     * This method is used to register and give access to the system for
     * some new visitor.
     *
     * @param login of user
     * @param email of user
     * @param password of user
     * @param passwordrep of user
     * @return User bean with filled in fields.
     * @throws ServiceRuntimeException if any error occurred while processing method.
     */
    AppUser register(String login, String email, byte[] password, byte[] passwordrep) throws ServiceRuntimeException;

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceRuntimeException if any error occurred while processing method.
     */
    AppUser authorize(String login, byte[] password) throws ServiceRuntimeException;

    List<AppUser> showAllUsers() throws ServiceRuntimeException;

    AppUserPurse fillUpAPurse(int userId, int amount) throws ServiceRuntimeException;

    AppUserPurse checkPurseAmount(int userId) throws ServiceRuntimeException;
}
