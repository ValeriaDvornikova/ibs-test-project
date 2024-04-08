package ru.ibs.tests.db_tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.ibs.pages.ConfProp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDbTest {
    protected static Connection connection;

    @BeforeAll
    public static void init() throws SQLException {
        connection = getNewConnection();
    }

    @AfterAll
    public static void close() throws SQLException {
        connection.close();
    }

    protected static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(ConfProp.getProperty("db_url"),
                ConfProp.getProperty("db_login"), ConfProp.getProperty("db_password"));
    }
}
