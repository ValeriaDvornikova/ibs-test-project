package ru.ibs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.pages.GoodsPage;
import ru.ibs.pages.MainPage;

import java.util.Arrays;
import java.util.List;

public class LocalHostTest extends BaseTest {
    @Test
    @DisplayName("Проверка добаления и удаления товаров на странице")
    public void checkAddProduct() {
        String fruitName = "Джекфрут";
        MainPage.getInstance()
                .switchToSandBox();

        GoodsPage.getInstance()
                .createNewGoods(fruitName);

        List<String> productList = Arrays.asList("Джекфрут", "Фрукт", "true");

        Assertions.assertAll("Проверки добавления и удаления товара",
                // Проверка добавления строки
                () -> Assertions.assertEquals(productList, GoodsPage.getInstance().checkGoods(),
                        "Полученные данные неодинаковые!"),
                // Проверка удаления строки
                () -> Assertions.assertNotEquals(productList, GoodsPage.getInstance().lastValueInTheTable(),
                        "Полученные данные одинаковые"));
    }
}
