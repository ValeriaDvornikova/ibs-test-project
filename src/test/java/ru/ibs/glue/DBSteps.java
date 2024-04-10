package ru.ibs.glue;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import ru.ibs.pages.ConfProp;

import java.sql.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class DBSteps {

    private static final String QUERY = "SELECT * FROM FOOD;";
    private static Connection connection;
    private static final String ADD_REQUEST = "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) " +
            "VALUES (?, ?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM FOOD WHERE FOOD_NAME = 'Ананас'";

    @Given("Создаем подключение к БД")
    public void init() throws SQLException {
        connection = DriverManager.getConnection(ConfProp.getProperty("db_url"),
                ConfProp.getProperty("db_login"), ConfProp.getProperty("db_password"));
    }

    @Given("Проверяем, что подключение в БД создано")
    public void checkConnection() throws SQLException {
        assertTrue(connection.isValid(1), "Подключения нет");
    }

    @Given("Проверяем, что добавляем в БД уникальный новый товар")
    public void checkNewGood() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(QUERY);
        set.first();
        do {
            assertNotEquals("Ананас", set.getString("FOOD_NAME"));
        } while (set.next());
    }

    @Given("Добавляем в БД новый товар, заполнив поля:")
    public void addNewGood(Map<String, String> requestParams) throws SQLException {
        PreparedStatement add = connection.prepareStatement(ADD_REQUEST);
        add.setString(1, requestParams.get("FOOD_NAME"));
        add.setString(2, requestParams.get("FOOD_TYPE"));
        add.setInt(3, Integer.parseInt(requestParams.get("FOOD_EXOTIC")));
        add.executeUpdate();
    }

    @Given("Проверяем, что в таблицу добавился введенный товар")
    public void checkAddNewGood() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet afterUpdSelectResult = statement.executeQuery(QUERY);
        afterUpdSelectResult.last();
        Assertions.assertAll("Сравнение последней строки с введенным значением",
                () -> assertEquals(afterUpdSelectResult.getString("FOOD_NAME"), "Ананас"),
                () -> assertEquals(afterUpdSelectResult.getString("FOOD_TYPE"), "FRUIT"),
                () -> assertEquals(afterUpdSelectResult.getInt("FOOD_EXOTIC"), 1)
        );
    }

    @Given("Удаляем введенный товар из таблицы")
    public void deleteNewGood() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST);
        statement.execute();
    }

    @Given("Проверяем, что в таблице отсутствует удаленный товар")
    public void checkDeleteNewGood() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet afterDeleteResult = statement.executeQuery(QUERY);
        afterDeleteResult.last();

        Assertions.assertFalse("Ананас".equals(afterDeleteResult.getString("FOOD_NAME"))
                        && ("FRUIT".equals(afterDeleteResult.getString("FOOD_TYPE")))
                        && (Integer.valueOf(1).equals(afterDeleteResult.getInt("FOOD_EXOTIC"))),
                "Товары совпадают, строка не удалилась");

    }

    @Given("Постусловие: Закрыть соединение")
    public static void close() throws SQLException {
        connection.close();
    }
}
