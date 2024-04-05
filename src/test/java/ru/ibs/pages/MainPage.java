package ru.ibs.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Главная страница сайта, с которой происходит переход на страницу с товарами
 */

public class MainPage extends BasePage {
    private static MainPage INSTANCE;
    @FindBy(xpath = "//*[@data-toggle = 'dropdown']")
    private WebElement sandBox;

    @FindBy(xpath = "//*[text()='Товары']")
    private WebElement goods;

    private MainPage() {
        PageFactory.initElements(driver, this);
    }

    public static MainPage getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MainPage();
        return INSTANCE;
    }
    // Действия по переходу с главной страницы на страницу с товарами
    public void switchToSandBox() {
        if (isElementVisible(sandBox))
            sandBox.click();
        if (isElementVisible(goods))
            goods.click();
    }
}
