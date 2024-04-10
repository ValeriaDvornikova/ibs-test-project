package ru.ibs.tests;

import org.junit.jupiter.api.*;
import ru.ibs.pages.GoodsPage;
import ru.ibs.pages.MainPage;

import java.util.Arrays;
import java.util.List;

public class LocalHostTest extends BaseTest {
    private final List<String> productList = Arrays.asList("Джекфрут", "Фрукт", "true");
    @Test
    @DisplayName("Проверка добаления и удаления товаров на странице")
    public void checkAddProduct() {
        String fruitName = "Джекфрут";
        MainPage.getInstance()
                .switchToSandBox()
                .goodsButtonClick();
        GoodsPage.getInstance()
                .clickAddButton()
                .enterFruitName(fruitName)
                .checkType()
                .checkBoxClick()
                .saveButtonClick();
        Assertions.assertEquals(productList, GoodsPage.getInstance().checkGoods(),
                "Полученные данные неодинаковые: товар не добавился");
        GoodsPage.resetAll();
        Assertions.assertNotEquals(productList, GoodsPage.getInstance().getGoodsValueFromTheTable(4),
                "Полученные данные одинаковые: товар не удален");
    }
}