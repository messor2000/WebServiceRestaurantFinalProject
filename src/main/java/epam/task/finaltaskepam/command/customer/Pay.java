package epam.task.finaltaskepam.command.customer;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.Dish;
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
import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class Pay implements Command {

    private static final Logger logger = LogManager.getLogger(Pay.class);

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/menu.jsp";
    private static final String MESSAGE_OF_ERROR = "You dont have enough money to pay";
    private static final String MESSAGE_OF_ERROR_2 = "You have already paid, wait managers approve";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Util.saveCurrentQueryToSession(request);

        int userId = 0;

        OrderService orderService = FactoryService.getInstance().getOrderService();
        Object object = request.getSession(false).getAttribute(Constants.USER_REQUEST_ATTRIBUTE);

        if (object.getClass().equals(AppUser.class)) {
            AppUser user = (AppUser) object;
            userId = user.getIdUser();
        }

        Order order = orderService.showOrder(userId);

        List<Dish> dishes = orderService.showDishesInOrder(order.getOrderId());

        if (order.getOrderStatus().equals(Status.WAITING_FOR_PAY)) {
            try {
                orderService.payForOrder(order.getOrderId(), userId);

                request.setAttribute(Constants.MENU_REQUEST_ATTRIBUTE, dishes);

                request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
            } catch (ServiceRuntimeException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
        }
    }
}
