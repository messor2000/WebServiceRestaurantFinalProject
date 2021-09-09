package epam.task.finaltaskepam.servlet.command;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.command.customer.Logout;
import epam.task.finaltaskepam.command.customer.MakeAnOrder;
import epam.task.finaltaskepam.command.customer.Purse;
import epam.task.finaltaskepam.command.customer.ToUpAPurse;
import epam.task.finaltaskepam.command.manager.AddDish;
import epam.task.finaltaskepam.command.manager.ReplenishStock;
import epam.task.finaltaskepam.command.manager.ShowAllOrders;
import epam.task.finaltaskepam.command.manager.ShowAllUsers;
import epam.task.finaltaskepam.command.visitor.ShowDish;
import epam.task.finaltaskepam.command.visitor.ShowMenu;
import epam.task.finaltaskepam.command.visitor.Language;
import epam.task.finaltaskepam.command.visitor.Login;
import epam.task.finaltaskepam.command.visitor.Register;
import epam.task.finaltaskepam.command.visitor.ShowMenuBuLowPrice;
import epam.task.finaltaskepam.command.visitor.ShowMenuByCategory;
import epam.task.finaltaskepam.command.visitor.ShowMenuByHighPrice;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Aleksandr Ovcharenko
 */
public class CommandProducer {

    private static final Logger logger = LogManager.getLogger(CommandProducer.class);
    private final Map<CommandPool, Command> guestCommands = new EnumMap<>(CommandPool.class);
    private final Map<CommandPool, Command> customerCommands = new EnumMap<>(CommandPool.class);
    private final Map<CommandPool, Command> managerCommands = new EnumMap<>(CommandPool.class);

    private static final String CUSTOMER = "customer";
    private static final String MANAGER = "manager";

    private static final CommandProducer instance = new CommandProducer();

    public CommandProducer() {
        guestCommands.put(CommandPool.LOGIN, new Login());
        guestCommands.put(CommandPool.REGISTER, new Register());
        guestCommands.put(CommandPool.CHANGE_LANGUAGE, new Language());
        guestCommands.put(CommandPool.SHOW_MENU, new ShowMenu());
        guestCommands.put(CommandPool.SHOW_MENU_BY_LOW_PRICE, new ShowMenuBuLowPrice());
        guestCommands.put(CommandPool.SHOW_MENU_BY_HIGH_PRICE, new ShowMenuByHighPrice());
        guestCommands.put(CommandPool.SHOW_MENU_BY_CATEGORY, new ShowMenuByCategory());
        guestCommands.put(CommandPool.FIND_DISH, new ShowDish());

        customerCommands.putAll(guestCommands);
        customerCommands.put(CommandPool.LOG_OUT, new Logout());
        customerCommands.put(CommandPool.SHOW_PURSE, new Purse());
        customerCommands.put(CommandPool.TOP_UP_PURSE, new ToUpAPurse());
        customerCommands.put(CommandPool.MAKE_AN_ORDER, new MakeAnOrder());

        managerCommands.putAll(customerCommands);
        managerCommands.put(CommandPool.SHOW_ALL_USERS, new ShowAllUsers());
        managerCommands.put(CommandPool.ADD_DISH, new AddDish());
        managerCommands.put(CommandPool.REPLENISH_STOCK, new ReplenishStock());
        managerCommands.put(CommandPool.SHOW_ALL_ORDERS, new ShowAllOrders());
    }

    public Command getCommandForUser(String role, String commandName) {
        String cmd = commandName.replace("-", "_").toUpperCase();
        CommandPool name;
        Command command;
        try {
            name = CommandPool.valueOf(cmd);
            switch (role) {
                case CUSTOMER:
                    return customerCommands.get(name);
                case MANAGER:
                    return managerCommands.get(name);
                default:
                    return guestCommands.get(name);
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "No such command", e);
            command = guestCommands.get(CommandPool.LOGIN);
        }
        return command;
    }

    public static CommandProducer getInstance() {
        return instance;
    }
}
