package ru.ibs.tests.db_tests;


import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class DBTest extends BaseDbTest {
    /**
     * Проверка, что соединение с БД установлено
     */
    @Test
    @DisplayName("Проврека подключения к БД")
    public void shouldGetJdbcConnection() throws SQLException {
        assertTrue(connection.isValid(1), "Подключения нет");
        assertFalse(connection.isClosed(), "Подключение открыто");
    }

    /**
     * Проверка добавления позиции в таблицу
     */
    @Test
    @DisplayName("Проверка добавления и удаления товара в БД")

    public void dbTest() throws SQLException {
        String query = "SELECT * FROM FOOD;";
        String addRequest = "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) " +
                "VALUES (?, ?, ?);";
        String deleteRequest = "DELETE FROM FOOD WHERE FOOD_NAME = 'Ананас'";

        // Добавление записи в БД
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        PreparedStatement add = connection.prepareStatement(addRequest);
        add.setString(1, "Ананас");
        add.setString(2, "FRUIT");
        add.setInt(3, 1);
        add.executeUpdate();

        //Проверка наличия записи в БД
        ResultSet afterUpdSelectResult = statement.executeQuery(query);
        afterUpdSelectResult.last();
        Assertions.assertAll("Сравнение последней строки с введенным значением",
                () -> assertEquals(afterUpdSelectResult.getString("FOOD_NAME"), "Ананас"),
                () -> assertEquals(afterUpdSelectResult.getString("FOOD_TYPE"), "FRUIT"),
                () -> assertEquals(afterUpdSelectResult.getInt("FOOD_EXOTIC"), 1)
        );

        // Удаление записи из БД
        statement.execute(deleteRequest);
        ResultSet afterDeleteResult = statement.executeQuery(query);
        afterDeleteResult.last();

        // Проверка, что запись отсутствует в БД
        Assertions.assertFalse("Ананас".equals(afterDeleteResult.getString("FOOD_NAME"))
                        && ("FRUIT".equals(afterDeleteResult.getString("FOOD_TYPE")))
                        && (Integer.valueOf(1).equals(afterDeleteResult.getInt("FOOD_EXOTIC"))),
                "Товары совпадают, строка не удалилась");
    }
}
