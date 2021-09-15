package epam.task.finaltaskepam.command.manager;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.menu.MenuService;
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
public class ReplenishStock implements Command {

    private static final Logger logger = LogManager.getLogger(ReplenishStock.class);

    private static final String NAME = "dishName";
    private static final String AMOUNT = "amount";

    private static final String MESSAGE_OF_ERROR = "Something wrong with replenish stock, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String amount = request.getParameter(AMOUNT);
        String dishName = request.getParameter(NAME);
        String previousQuery = Util.getPreviousQuery(request);

        MenuService menuService = FactoryService.getInstance().getMenuService();

        try {
            menuService.replenishStock(dishName, Integer.parseInt(amount));

            response.sendRedirect(previousQuery);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}
