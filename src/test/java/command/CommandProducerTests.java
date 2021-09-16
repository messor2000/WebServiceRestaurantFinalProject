package command;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.dao.FactoryDao;
import epam.task.finaltaskepam.dao.dish.DishDao;
import epam.task.finaltaskepam.dto.Dish;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.error.DaoRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPoolImpl;
import epam.task.finaltaskepam.service.FactoryService;
import epam.task.finaltaskepam.service.menu.MenuService;
import epam.task.finaltaskepam.servlet.command.CommandPool;
import epam.task.finaltaskepam.servlet.command.CommandProducer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import service.DishServiceTests;

import java.util.List;

/**
 * @author Aleksandr Ovcharenko
 */
public class CommandProducerTests {

    private static final Logger logger = LogManager.getLogger(DishServiceTests.class);


    @Test
    public void getCommandForUserTest() {


        CommandProducer commandProducer = CommandProducer.getInstance();

        String role = "visitor";

//        Command command = commandProducer.getCommandForUser(role, CommandPool.MAKE_AN_ORDER);

    }
}
