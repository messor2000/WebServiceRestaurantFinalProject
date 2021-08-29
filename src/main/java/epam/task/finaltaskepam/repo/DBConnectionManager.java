package epam.task.finaltaskepam.repo;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionManager {
    private static BasicDataSource basicDataSource;

    public static BasicDataSource getDataSource() {
        if (basicDataSource == null) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql//localhost:3036/web_service_restaurant?useUnicode=yes&characterEncoding=UTF8MB4");
            dataSource.setUsername("root");
            dataSource.setPassword("password");

            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxOpenPreparedStatements(100);

            basicDataSource = dataSource;
        }
        return basicDataSource;
    }
}
