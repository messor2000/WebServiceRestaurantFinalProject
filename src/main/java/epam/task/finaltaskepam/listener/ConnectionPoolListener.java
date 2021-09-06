package epam.task.finaltaskepam.listener;

import epam.task.finaltaskepam.error.ServiceRuntimeException;
import epam.task.finaltaskepam.repo.ConnectionPool;
import epam.task.finaltaskepam.error.ConnectionPoolException;
import epam.task.finaltaskepam.service.FactoryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Aleksandr Ovcharenko
 */
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

    public ConnectionPoolListener() {
        // Public constructor is required by servlet spec
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context isно
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        try {
        FactoryService factoryService = FactoryService.getInstance();
        ConnectionPool connectionPool = factoryService.getConnectionPool();
        connectionPool.init();
        logger.log(Level.INFO, "Pool successfully initialized");

         } catch (ConnectionPoolException e) {
            throw new ServiceRuntimeException("Cannot init the pool", e);
         }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
        try {
            FactoryService factoryService = FactoryService.getInstance();
            ConnectionPool connectionPool = factoryService.getConnectionPool();
            connectionPool.destroy();
            logger.log(Level.INFO, "Pool successfully destroyed");
        } catch (ConnectionPoolException e) {
            throw new ServiceRuntimeException("Cannot init the pool", e);
        }
    }
}