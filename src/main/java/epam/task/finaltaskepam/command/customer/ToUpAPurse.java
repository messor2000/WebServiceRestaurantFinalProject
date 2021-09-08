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
public class ToUpAPurse implements Command {

    private static final Logger logger = LogManager.getLogger(ToUpAPurse.class);

    private static final String AMOUNT = "amount";
    private static final String USER_ID = "user_id";

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/purse.jsp";
    private static final String MESSAGE_OF_ERROR = "Something wrong with show all menu, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String amount = request.getParameter(AMOUNT);
        String userId = request.getParameter(USER_ID);
        Util.saveCurrentQueryToSession(request);

        AppUserService appUserService = FactoryService.getInstance().getUserService();
        try {

            AppUserPurse purse;

            purse = appUserService.fillUpAPurse(Integer.parseInt(userId), Integer.parseInt(amount));

            request.setAttribute(Constants.USER_REQUEST_ATTRIBUTE, purse);

            request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}

