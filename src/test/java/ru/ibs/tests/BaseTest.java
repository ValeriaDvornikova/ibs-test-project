package ru.ibs.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.ibs.pages.BasePage;
import ru.ibs.pages.ConfProp;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;


public abstract class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        if ("remote".equalsIgnoreCase(ConfProp.getProperty("type_driver"))) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(ConfProp.getProperty("type_browser"));
            capabilities.setVersion("109.0");
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", false
            ));
            try {
                driver = new RemoteWebDriver(URI.create(ConfProp.getProperty("selenoid_url")).toURL(),
                        capabilities, true);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            BasePage.setDriver(driver);
            driver.get(ConfProp.getProperty("remote_url"));

        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            BasePage.setDriver(driver);
            driver.get(ConfProp.getProperty("base_url"));
        }
    }


    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}

