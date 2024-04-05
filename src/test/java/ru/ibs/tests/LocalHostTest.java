package ru.ibs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ibs.pages.GoodsPage;
import ru.ibs.pages.MainPage;

public class LocalHostTest extends BaseTest {
    @Test
    public void checkPage() {
        String fruitName = "Джекфрут";
        MainPage.getInstance()
                .switchToSandBox();

        GoodsPage.getInstance()
                .createNewGoods(fruitName);
        Assertions.assertTrue(GoodsPage.getInstance().checkGoods(), "Полученные данные неодинаковые!");
    }
}
