package ru.ibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class GoodsPage extends BasePage {
    private static GoodsPage INSTANCE;
    @FindBy(xpath = "//*[@type = 'button' and text() = 'Добавить']")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id = 'name']")
    private WebElement nameForm;

    @FindBy(xpath = "//*[@id = 'type']")
    private WebElement type;

    @FindBy(xpath = "//*[@value = 'FRUIT']")
    private WebElement fruitType;

    @FindBy(xpath = "//*[@type= 'checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "//*[@id= 'save']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[text()='5']/following-sibling::*")
    private WebElement newSandBoxLine;


    private GoodsPage() {
        PageFactory.initElements(driver, this);
    }

    public static GoodsPage getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GoodsPage();
        return INSTANCE;
    }

    public GoodsPage createNewGoods(String name) {
        addButton.click();
        nameForm.sendKeys(name);
        type.click();
        fruitType.click();
        checkBox.click();
        saveButton.click();
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public String checkGoods() {
        List <WebElement> newGoodsList = driver.findElements(By.xpath("//*[text()='5']/following-sibling::*"));
        return newGoodsList.get(0).getText();
    }
}
