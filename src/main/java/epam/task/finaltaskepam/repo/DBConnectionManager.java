package epam.task.finaltaskepam.repo;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionManager {
    private static BasicDataSource basicDataSource;

    private DBConnectionManager(){}

    public static BasicDataSource getDataSource() {
        if (basicDataSource == null) {
            BasicDataSource dataSource = new BasicDataSource();

            String dbURL = "http://localhost:3038/final_task_restaurant";
            String user = "root";
            String password = "password";
            dataSource.setUrl(dbURL);
            dataSource.setUsername(user);
            dataSource.setPassword(password);

            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxOpenPreparedStatements(100);

            basicDataSource = dataSource;
        }
        return basicDataSource;
    }
}
