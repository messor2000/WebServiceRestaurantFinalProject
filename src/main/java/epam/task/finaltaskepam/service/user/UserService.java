package epam.task.finaltaskepam.service.user;

import com.google.protobuf.ServiceException;
import epam.task.finaltaskepam.dto.AppUser;

/**
 * @author Aleksandr Ovcharenko
 */
public interface UserService {

    /**
     * This method is used to register and give access to the system for
     * some new visitor.
     *
     * @param login of user
     * @param email of user
     * @param password of user
     * @param passwordrep of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    AppUser register(String login, String email, byte[] password, byte[] passwordrep) throws ServiceException;

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    AppUser authorize(String login, byte[] password) throws ServiceException;

}