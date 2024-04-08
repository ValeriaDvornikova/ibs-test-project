package ru.ibs.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ibs.pages.BasePage;
import ru.ibs.pages.ConfProp;

import java.time.Duration;


public abstract class BaseTest {
    protected static WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
