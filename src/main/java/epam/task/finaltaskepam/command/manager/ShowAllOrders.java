package epam.task.finaltaskepam.command.manager;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.order.OrderService;
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
public class ShowAllOrders implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllOrders.class);

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/orders.jsp";
    private static final String MESSAGE_OF_ERROR = "Something wrong with this page, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Util.saveCurrentQueryToSession(request);

        List<Order> orders;

        OrderService orderService = FactoryService.getInstance().getOrderService();
        try {

            orders = orderService.showAllOrders();

            request.setAttribute(Constants.USER_ORDER_REQUEST_ATTRIBUTE, orders);

            request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}
