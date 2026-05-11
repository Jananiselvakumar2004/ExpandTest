package com.expandtest.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.expandtest.utils.ExtentManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    protected static ExtentReports extent;

    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {

        extent = ExtentManager.getInstance();

        System.out.println(
                "[INFO] Extent Report initialized"
        );
    }

    @BeforeMethod
    public void setup(java.lang.reflect.Method method) {

        System.out.println(
                "=== Setting up: "
                        + method.getName()
                        + " ==="
        );

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // IMPLICIT WAIT
        driver.manage().timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(5)
                );

        driver.manage().timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(20)
                );

        System.out.println(
                "[INFO] Browser launched successfully"
        );

        test = extent.createTest(
                method.getName()
        );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus()
                == ITestResult.FAILURE) {

            test.fail(
                    result.getThrowable()
            );
        }

        else if (result.getStatus()
                == ITestResult.SUCCESS) {

            test.pass("Test Passed");
        }

        else {

            test.skip("Test Skipped");
        }

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "=== Browser closed ==="
            );
        }
    }

    @AfterSuite
    public void flushReport() {

        extent.flush();

        System.out.println(
                "=== Extent Report generated ==="
        );
    }
}