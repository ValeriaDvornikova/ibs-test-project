package ru.ibs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage {
    protected static WebDriver driver;
    @FindBy(xpath = "//*[@data-toggle = 'dropdown']")
    private static WebElement sandBox;
    @FindBy(xpath = "//*[@id = 'reset']")
    private static WebElement resetData;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void resetAll() {
        sandBox.click();
        resetData.click();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
