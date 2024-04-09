package ru.ibs.glue.ui_steps;

import io.cucumber.java.en.Given;
import ru.ibs.pages.GoodsPage;
import ru.ibs.pages.MainPage;
import ru.ibs.tests.BaseTest;

public class UISteps {
    @Given("Открываем сайт")
    public void init() {
        BaseTest.setUp();
    }
    @Given("На главной странице нажимаем на кнопку Песочница и во всплювающем меню нажимаем на кнопку Товары")
    public void goProductsPage(){
        MainPage.getInstance().switchToSandBox();
    }
    @Given("На странице с товарами нажмаем на кнопку Добавить")
    public void addNewGood(){
    }
}
