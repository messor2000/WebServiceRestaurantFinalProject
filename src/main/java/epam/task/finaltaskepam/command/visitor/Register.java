package epam.task.finaltaskepam.command.visitor;

import com.google.protobuf.ServiceException;
import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.user.UserServiceImpl;
import epam.task.finaltaskepam.util.Constants;
import epam.task.finaltaskepam.util.Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Aleksandr Ovcharenko
 */
public class Register implements Command {

    private static final Logger logger = LogManager.getLogger(Register.class);

    private static final String USER = "user";

    private static final String LOGIN = "nickname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String PASSWORD_2 = "pass2";

    private static final String MESSAGE_OF_ERROR_1 = "All fields should be filled";
    private static final String MESSAGE_OF_ERROR_2 = "Such with such email or login is already exist";
    private static final String MESSAGE_OF_ERROR_3 = "Login and password should be at least 6 characters";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        byte[] password = request.getParameter(PASSWORD).getBytes();
        byte[] password2 = request.getParameter(PASSWORD_2).getBytes();

        UserServiceImpl userService = FactoryService.getInstance().getUserService();
        String previousQuery = Util.getPreviousQuery(request);
        HttpSession session = request.getSession(true);

        if (login != null && email != null && password.length > 0 && password2.length > 0) {
            try {
                AppUser user = userService.register(login, email, password, password2);
                Arrays.fill(password, (byte) 0);
                Arrays.fill(password2, (byte) 0);

                session.setAttribute(USER, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceRuntimeException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.getERROR(), MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(Constants.getJspPagePath()).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.getERROR(), MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(Constants.getJspPagePath()).forward(request, response);
            }
        } else {
            request.setAttribute(Constants.getERROR(), MESSAGE_OF_ERROR_3);
            request.getRequestDispatcher(Constants.getJspPagePath()).forward(request, response);
        }
    }
}
