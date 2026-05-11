package com.expandtest.tests;

import com.expandtest.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up browser ");
        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            try {
                WebDriverManager.chromedriver().setup();
            } catch (Exception e) {
                System.setProperty("webdriver.chrome.driver",
                        "/opt/homebrew/bin/chromedriver");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("remote-allow-origins");
            driver = new ChromeDriver(options);
        }

        driver.get(ConfigReader.get("baseUrl"));
        System.out.println(" Browser launched: "
                + ConfigReader.get("baseUrl") + " ");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }
}