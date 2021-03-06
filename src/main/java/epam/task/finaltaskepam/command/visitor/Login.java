package epam.task.finaltaskepam.command.visitor;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.user.AppUserService;
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
 * Login class is used to handle sign in request from client.
 */
public class Login implements Command {


    private static final Logger logger = LogManager.getLogger(Login.class);

    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";

    public static final String JSP_LOGIN_PAGE_PATH = "WEB-INF/jsp/loginPage.jsp";
    private static final String MESSAGE_OF_ERROR_1 = "Wrong login or pass";
    private static final String MESSAGE_OF_ERROR_2 = "Sorry something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(LOGIN);
        byte[] password = request.getParameter(PASSWORD).getBytes();
        String previousQuery = Util.getPreviousQuery(request);

        AppUserService appUserService = FactoryService.getInstance().getUserService();
        HttpSession session = request.getSession(true);

        if (login != null && password.length>5) {
            try {
                AppUser user = appUserService.authorize(login, password);
                Arrays.fill(password, (byte) 0);
                session.setAttribute(Constants.USER_REQUEST_ATTRIBUTE, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceRuntimeException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_LOGIN_PAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(JSP_LOGIN_PAGE_PATH).forward(request, response);
        }
    }
}