package epam.task.finaltaskepam.servlet.command;

import epam.task.finaltaskepam.command.Command;
import epam.task.finaltaskepam.command.visitor.Login;
import epam.task.finaltaskepam.command.visitor.Register;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandr Ovcharenko
 */
public class CommandProducer {

    private static final Logger logger = LogManager.getLogger(CommandProducer.class);
    private final Map<CommandPool, Command> guestCommands = new HashMap<>();
    private final Map<CommandPool, Command> userCommands = new HashMap<>();
    private final Map<CommandPool, Command> adminCommands = new HashMap<>();

    private static final String VISITOR = "visitor";
    private static final String CUSTOMER = "customer";
    private static final String MANAGER = "manager";

    private static final CommandProducer instance = new CommandProducer();

    public CommandProducer() {
        guestCommands.put(CommandPool.LOGIN, new Login());
        guestCommands.put(CommandPool.REGISTER, new Register());
    }

    public Command getCommandForUser(String type, String commandName) {
        String cmd = commandName.replace("-", "_").toUpperCase();
        CommandPool name;
        Command command = null;
        try {
            name = CommandPool.valueOf(cmd);
            switch (type) {
                case CUSTOMER:
                    return userCommands.get(name);
                case MANAGER:
                    return adminCommands.get(name);
                default:
                    return guestCommands.get(name);
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "No such command", e);
//            command = guestCommands.get(CommandPool.ALL_MOVIES);
        }
        return command;
    }

    public static CommandProducer getInstance() {
        return instance;
    }
}
