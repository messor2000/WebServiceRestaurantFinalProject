package epam.task.finaltaskepam.command.customer;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.dto.order.Order;
import epam.task.finaltaskepam.dto.order.Status;
import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.menu.MenuService;
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
import java.util.Set;

/**
 * @author Aleksandr Ovcharenko
 */
public class MakeAnOrder implements Command {

    private static final Logger logger = LogManager.getLogger(MakeAnOrder.class);

    private static final String DISH_NAME = "dishName";

    public static final String JSP_MENU_PAGE_PATH = "WEB-INF/jsp/menu.jsp";
    private static final String MESSAGE_OF_ERROR = "Something wrong with menu, pls try later";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String previousQuery = Util.getPreviousQuery(request);

        String dishName = request.getParameter(DISH_NAME);

        int userId = 0;

        Object object = request.getSession(false).getAttribute(Constants.USER_REQUEST_ATTRIBUTE);

        if (object.getClass().equals(AppUser.class)) {
            AppUser user = (AppUser) object;
            userId = user.getIdUser();
        }

        OrderService orderService = FactoryService.getInstance().getOrderService();

        Order order = orderService.showOrder(userId);
        List<Dish> dishList = orderService.showDishesInOrder(order.getOrderId());

//        if (dishList.isEmpty()) {
//            order.setOrderStatus(Status.WAITING_FOR_PAY);
//        }



//        if (order.getOrderStatus().equals(Status.COMPLETE) || order == null) {
//            order = orderService.createAnOrder(userId);
//        }

        try {
            orderService.makeAnOrder(dishName, order.getOrderId());

            response.sendRedirect(previousQuery);
        } catch (ServiceRuntimeException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(Constants.ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(JSP_MENU_PAGE_PATH).forward(request, response);
        }
    }
}
