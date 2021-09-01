package epam.task.finaltaskepam.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dto.AppUser;
import epam.task.finaltaskepam.servlet.command.CommandProducer;
import epam.task.finaltaskepam.util.Constants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Aleksandr Ovcharenko
 */
//@WebServlet(name = "FrontController")
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(FrontController.class);


    public FrontController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");

        logger.log(Level.INFO, "Controller processRequest() - commandName = {}", commandName);
        if (commandName != null && !commandName.isEmpty()) {
            try {
                AppUser user = (AppUser) request.getSession(false).getAttribute("user");
                String type;
                if (user != null) {
                    type = user.getRole();
                } else {
                    type = "visitor";
                }
                Command command = CommandProducer.getInstance().getCommandForUser(type, commandName);
                if (command == null) {
                    logger.log(Level.ERROR, "Access without permission from client");
                    request.setAttribute(Constants.getERROR(), "You don't have permission to do that.");
                    request.getRequestDispatcher(Constants.getErrorPage()).forward(request, response);
                } else {
                    command.execute(request, response);
                }
            } catch (IllegalArgumentException ex) {
                logger.log(Level.ERROR, "404 error, client requests a nonexistent command", ex);
                request.setAttribute(Constants.getERROR(), "Ooops something went wrong");
                request.getRequestDispatcher(Constants.getErrorPage()).forward(request, response);
            }
        } else {
            logger.log(Level.ERROR, "No such command");
            request.setAttribute(Constants.getERROR(), "Ooops something went wrong");
            request.getRequestDispatcher(Constants.getErrorPage()).forward(request, response);
        }
    }
}
