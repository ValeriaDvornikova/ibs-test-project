package ru.ibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Страница с таблицей товаров, на которой происходит добавление нового продукта
 */
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

    @FindBy(xpath = "//*[text()='4']/following-sibling::*")
    private WebElement localValueOfTable;

    private GoodsPage() {
        PageFactory.initElements(driver, this);
    }

    public static GoodsPage getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GoodsPage();
        return INSTANCE;
    }

    //Добавляем новый товар
    public void createNewGoods(String name) {
        // Проверка, что видна кнопка для добавления товара
        if (isElementVisibleAndClickable(addButton))
            addButton.click();
        // Проверка, что при открытии формы видно поле с добавлением наименования
        // (если оно видно, остальные поля тоже есть)
        if (isElementVisibleAndClickable(nameForm))
            nameForm.sendKeys(name);

        type.click();
        fruitType.click();
        checkBox.click();
        // Проверка, что видна кнопка для сохранения введенных данных
        if (isElementVisibleAndClickable(saveButton))
            saveButton.click();

        // Замедляла исключительно с целью наглядности выполнения
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Получаем лист с товарами и сравниваем с тем,
    // что заранее задумывалось: Джекфрут, Фрукт, Экзотический(true)
    public List<String> checkGoods() {
        List<String> result = new ArrayList<>();
        if (isElementVisibleAndClickable(newSandBoxLine)) {
            List<WebElement> newGoodsList = driver.findElements
                    (By.xpath("//*[text()='5']/following-sibling::*"));
            result = Arrays.asList(newGoodsList.get(0).getText(),
                    newGoodsList.get(1).getText(),
                    newGoodsList.get(2).getText());
        }
        return result;
    }

    public List<String> lastValueInTheTable() {
        List<WebElement> goodsList = driver.findElements
                (By.xpath("//*[text()='4']/following-sibling::*"));
        return Arrays.asList(goodsList.get(0).getText(),
                goodsList.get(1).getText(),
                goodsList.get(2).getText());
    }
}
