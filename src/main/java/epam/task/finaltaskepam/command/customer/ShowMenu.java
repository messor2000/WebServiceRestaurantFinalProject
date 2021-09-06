package epam.task.finaltaskepam.command.customer;

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
public class ShowMenu implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/menu.jsp";
//    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ShowMenu.class);


    private static final String PAGE = "page";
    private static final String AMOUNT_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 10;

    private static final String REQUEST_ATTRIBUTE = "menu";

//    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Something wrong with menu, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Util.saveCurrentQueryToSession(request);

        List<Dish> menu;
        MenuService menuService = FactoryService.getInstance().getMenuService();
        try {
//            int page = 1;
//            if (request.getParameter(PAGE) != null) {
//                page = Integer.parseInt(request.getParameter(PAGE));
//            }

            menu = menuService.getMenu();

//            int numberOfMovies = movieService.countAllMoviesAmount();
//            int noOfPages = (int) Math.ceil(numberOfMovies * 1.0 / RECORDS_PER_PAGE);

//            request.setAttribute(AMOUNT_OF_PAGES, noOfPages);
//            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(REQUEST_ATTRIBUTE, menu);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.getERROR(), MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.getErrorPage()).forward(request, response);
        }
    }
}
