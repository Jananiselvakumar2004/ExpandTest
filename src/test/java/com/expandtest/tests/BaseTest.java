package com.expandtest.tests;

import com.aventstack.extentreports.Status;
import com.expandtest.listeners.ScreenshotListener;
import com.expandtest.utils.ConfigReader;
import com.expandtest.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void startReport() {
        ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        System.out.println("=== Setting up: " + method.getName() + " ===");
        try {
            WebDriverManager.chromedriver().setup();
        } catch (Exception e) {
            System.setProperty("webdriver.chrome.driver",
                    "/opt/homebrew/bin/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(ConfigReader.get("baseUrl"));

        ExtentReportManager.setTest(
                ExtentReportManager.getInstance()
                        .createTest(method.getName()));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.getTest()
                    .log(Status.FAIL, result.getThrowable());
            new ScreenshotListener().onTestFailure(result);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().log(Status.PASS, "Test passed");
        }
        if (driver != null) driver.quit();
        System.out.println("=== Browser closed ===");
    }

    @AfterSuite
    public void endReport() {
        ExtentReportManager.flush();
        System.out.println("=== Extent Report generated ===");
    }
}