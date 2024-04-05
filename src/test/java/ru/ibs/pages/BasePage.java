package ru.ibs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Базовая страница, инициализирующая для остальных работу с веб-драйвером;
 * Также реализует постусловие в resetAll(): происходит удаление введенных данных
 */
public abstract class BasePage {
    protected static WebDriver driver;
    @FindBy(xpath = "//*[@data-toggle = 'dropdown']")
    private static WebElement sandBox;
    @FindBy(xpath = "//*[@id = 'reset']")
    private static WebElement resetData;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    public static boolean isElementVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void resetAll() {
        sandBox.click();
        resetData.click();
        // Замедляла исключительно с целью наглядности выполнения
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
