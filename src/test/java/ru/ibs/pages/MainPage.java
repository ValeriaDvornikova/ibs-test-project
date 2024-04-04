package ru.ibs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void switchToSandBox() {
        sandBox.click();
        goods.click();
    }
}
