package ru.ibs.glue;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import ru.ibs.pages.GoodsPage;
import ru.ibs.pages.MainPage;
import ru.ibs.tests.BaseTest;

import java.util.Arrays;
import java.util.List;


public class UISteps {
    private final List<String> productList = Arrays.asList("Джекфрут", "Фрукт", "true");

    @Given("Открываем сайт")
    public void init() {
        BaseTest.setUp();
    }

    @Given("На главной странице нажимаем на кнопку Песочница")
    public MainPage clickSandBox() {
        return MainPage.getInstance().switchToSandBox();
    }

    @Given("Во всплывающем меню нажимаем на кнопку Товары")
    public void clickGoodsButton() {
        MainPage.getInstance().goodsButtonClick();
    }

    @Given("На странице с товарами нажимаем кнопку Добавить")
    public GoodsPage addButtonClick() {
        return GoodsPage.getInstance().clickAddButton();
    }

    @Given("Во всплывающем окне Добавления товара вводим в поле Наименование {string}")
    public GoodsPage addProductName(String string) {
        return GoodsPage.getInstance().enterFruitName(string);
    }

    @Given("В поле выбора Тип выбираем Фрукт")
    public GoodsPage clickFruitType() {
        return GoodsPage.getInstance().checkType();
    }

    @Given("В чекбоксе нажимаем галочку Экзотический")
    public GoodsPage clickCheckBoxExotic() {
        return GoodsPage.getInstance().checkBoxClick();
    }

    @Given("Нажимаем кнопку Cохранить")
    public void clickSaveButton() {
        GoodsPage.getInstance().saveButtonClick();
    }

    @Given("Проверяем, что введенные данные появились в таблице")
    public void checkAddProduct() {
        Assertions.assertEquals(productList, GoodsPage.getInstance().getGoodsValueFromTheTable(5));
    }

    @Given("Очищаем введенные данные: нажимаем на кнопку Песочница, в выпадающем меню выбираем кнопку Сброс данных")
    public void deleteFruit() {
        GoodsPage.resetAll();
    }

    @Given("Проверяем, что товар удалился из таблицы")
    public void checkDeleteProduct() {
        Assertions.assertNotEquals(productList, GoodsPage.getInstance().getGoodsValueFromTheTable(4));
    }

    @Given("Постусловие: Закрыть браузер")
    public void tearDown() {
        BaseTest.tearDown();
    }
}
