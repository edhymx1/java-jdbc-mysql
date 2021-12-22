package org.edgar.dev.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Mexico_City";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(USERNAME);
            pool.setPassword(PASSWORD);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}
