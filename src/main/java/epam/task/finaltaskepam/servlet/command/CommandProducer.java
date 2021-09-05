package epam.task.finaltaskepam.servlet.command;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.command.customer.Logout;
import epam.task.finaltaskepam.command.visitor.Language;
import epam.task.finaltaskepam.command.visitor.Login;
import epam.task.finaltaskepam.command.visitor.Register;
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

        customerCommands.putAll(guestCommands);
        customerCommands.put(CommandPool.LOG_OUT, new Logout());

        managerCommands.putAll(customerCommands);
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
