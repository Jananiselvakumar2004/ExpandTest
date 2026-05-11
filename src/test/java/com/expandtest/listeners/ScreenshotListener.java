package com.expandtest.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    private WebDriver getDriver(ITestResult result) {
        try {
            return (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver == null) return;

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String fileName = "screenshots/"
                + result.getName() + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fileName));
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}