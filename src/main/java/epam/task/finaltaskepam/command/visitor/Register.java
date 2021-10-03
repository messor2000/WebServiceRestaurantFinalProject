package epam.task.finaltaskepam.command.visitor;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.user.AppUserServiceImpl;
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
 *
 * Register is used to handle register request from client.
 */
public class Register implements Command {

    private static final Logger logger = LogManager.getLogger(Register.class);

    private static final String LOGIN = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_2 = "password2";

    public static final String JSP_REGISTER_PAGE_PATH = "WEB-INF/jsp/registerPage.jsp";
    private static final String MESSAGE_OF_ERROR_1 = "Such user already exist";
    private static final String MESSAGE_OF_ERROR_2 = "Error in server, please try late";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        byte[] password = request.getParameter(PASSWORD).getBytes();
        byte[] password2 = request.getParameter(PASSWORD_2).getBytes();

        AppUserServiceImpl userService = FactoryService.getInstance().getUserService();
        String previousQuery = Util.getPreviousQuery(request);
        HttpSession session = request.getSession(true);

        if (login != null && email != null && password.length > 0 && password2.length> 0) {
            try {
                AppUser user = userService.register(login, email, password, password2);
                Arrays.fill(password, (byte) 0);
                Arrays.fill(password2, (byte) 0);

                session.setAttribute(Constants.USER_REQUEST_ATTRIBUTE, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceRuntimeException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_REGISTER_PAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(JSP_REGISTER_PAGE_PATH).forward(request, response);
        }
    }
}
