package epam.task.finaltaskepam.command.manager;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.command.customer.MakeAnOrder;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
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

/**
 * @author Aleksandr Ovcharenko
 */
public class ApproveOrder implements Command {

    private static final Logger logger = LogManager.getLogger(ApproveOrder.class);

    private static final String ORDER_ID = "orderId";

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/menu.jsp";
    private static final String MESSAGE_OF_ERROR = "Something wrong with menu, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String previousQuery = Util.getPreviousQuery(request);

        String orderId = request.getParameter(ORDER_ID);

        OrderService orderService = FactoryService.getInstance().getOrderService();

        try {
            orderService.approveOrder(Integer.parseInt(orderId), Status.COMPLETE);

            response.sendRedirect(previousQuery);

        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
        }
    }
}
