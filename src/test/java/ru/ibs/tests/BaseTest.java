package ru.ibs.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ibs.pages.BasePage;
import ru.ibs.pages.ConfProp;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected static WebDriver driver;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.setDriver(driver);
        driver.get(ConfProp.getProperty("base_url"));
    }

    @AfterEach
    public void tearDown() {
        BasePage.resetAll();
        driver.close();
        driver.quit();
    }
}
