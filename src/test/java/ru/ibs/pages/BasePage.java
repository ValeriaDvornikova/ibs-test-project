package ru.ibs.pages;

import lombok.Setter;
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
public class BasePage {
    @Setter
    protected static WebDriver driver;
    @FindBy(xpath = "//*[@data-toggle = 'dropdown']")
    private static WebElement sandBox;
    @FindBy(xpath = "//*[@id = 'reset']")
    private static WebElement resetData;

    //Проверка, что элемент кликабелен и виден
    public static boolean isElementVisibleAndClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void resetAll() {
        if (isElementVisibleAndClickable(sandBox))
            sandBox.click();
        if (isElementVisibleAndClickable(resetData))
            resetData.click();
        // Замедляла исключительно с целью наглядности выполнения
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
