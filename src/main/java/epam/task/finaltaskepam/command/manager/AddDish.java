package epam.task.finaltaskepam.command.manager;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class AddDish implements Command {

    private static final Logger logger = LogManager.getLogger(AddDish.class);

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String AMOUNT = "amount";

    public static final String JSP_ADD_DISH_PAGE_PATH = "WEB-INF/jsp/addDish.jsp";
    private static final String MESSAGE_OF_ERROR_1 = "Error in add new dish";
    private static final String MESSAGE_OF_ERROR_2 = "Error in server, please try late";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(NAME);
        String price = request.getParameter(PRICE);
        String category = request.getParameter(CATEGORY);
        String amount = request.getParameter(AMOUNT);

        MenuService menuService = FactoryService.getInstance().getMenuService();

        Util.saveCurrentQueryToSession(request);
        String previousQuery = Util.getPreviousQuery(request);
        HttpSession session = request.getSession(true);

        if (name != null && price != null && category != null && amount != null) {
            try {
                List<Dish> menu;

                menu = menuService.addDish(name, Integer.parseInt(price), category, Integer.parseInt(price));

//                session.setAttribute(Constants.MENU_REQUEST_ATTRIBUTE, menu);
                request.setAttribute(Constants.USER_PURSE_REQUEST_ATTRIBUTE, menu);


//                response.sendRedirect(previousQuery);
                request.getRequestDispatcher(JSP_ADD_DISH_PAGE_PATH).forward(request, response);
            } catch (ServiceRuntimeException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_ADD_DISH_PAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(JSP_ADD_DISH_PAGE_PATH).forward(request, response);
        }
    }
}
