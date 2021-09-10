package epam.task.finaltaskepam.command.visitor;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.Dish;
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
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class ShowDish implements Command {
    private static final Logger logger = LogManager.getLogger(ShowDish.class);

    private static final String NAME = "name";

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/menu.jsp";
    private static final String MESSAGE_OF_ERROR = "No such dish in menu";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(NAME);
        Util.saveCurrentQueryToSession(request);

        MenuService menuService = FactoryService.getInstance().getMenuService();
        try {
            List<Dish> dish = menuService.getDish(name);

            request.setAttribute(Constants.MENU_REQUEST_ATTRIBUTE, dish);

            request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}

