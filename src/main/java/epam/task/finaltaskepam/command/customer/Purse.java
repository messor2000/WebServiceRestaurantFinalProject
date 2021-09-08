package epam.task.finaltaskepam.command.customer;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUserPurse;
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
import java.io.IOException;

/**
 * @author Aleksandr Ovcharenko
 */
public class Purse implements Command {

    private static final Logger logger = LogManager.getLogger(Purse.class);

    private static final String USER_ID = "appUserId";

    public static final String JSP_PURSE_PAGE_PATH = "WEB-INF/jsp/purse.jsp";
    private static final String MESSAGE_OF_ERROR = "Something wrong with show your purse, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter(USER_ID);
        Util.saveCurrentQueryToSession(request);
        AppUserPurse purse;

        AppUserService appUserService = FactoryService.getInstance().getUserService();
        try {
            purse = appUserService.checkPurseAmount(Integer.parseInt(userId));

            request.setAttribute(Constants.USER_PURSE_REQUEST_ATTRIBUTE, purse);

            request.getRequestDispatcher(JSP_PURSE_PAGE_PATH).forward(request, response);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}
