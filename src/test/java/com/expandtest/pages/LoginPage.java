package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        );
    }


    By usernameField =
            By.id("username");

    By passwordField =
            By.id("password");

    By loginButton =
            By.xpath("//button[@type='submit']");

    By logoutButton =
            By.linkText("Logout");

    By errorMessage =
            By.id("flash");


    public void navigateToLoginPage() {

        driver.get(
                "https://practice.expandtesting.com/login"
        );

        System.out.println(" Navigated to Login page");
    }


    public void enterUsername(String username) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField
                )
        ).sendKeys(username);

        System.out.println(" Username entered");
    }


    public void enterPassword(String password) {

        driver.findElement(passwordField)
                .sendKeys(password);

        System.out.println(" Password entered");
    }


    public void clickLogin() {

        WebElement loginBtn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                loginButton
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        loginBtn
                );

        System.out.println(" Login button clicked");
    }


    public void login(String username,
                      String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }


    public void clickLogout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        logoutButton
                )
        ).click();

        System.out.println(" Logout button clicked");
    }


    public boolean isLogoutButtonVisible() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            logoutButton
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }


    public boolean isErrorMessageDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            errorMessage
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }


    public String getErrorMessageText() {

        try {

            return driver.findElement(errorMessage)
                    .getText();

        } catch (Exception e) {

            return "";
        }
    }


    public boolean isLoginPageDisplayed() {

        return driver.getCurrentUrl()
                .contains("/login");
    }


    public void submitEmptyLoginForm() {

        clickLogin();
    }


    public boolean isValidationMessageDisplayed() {

        WebElement username =
                driver.findElement(usernameField);

        String message =
                username.getAttribute(
                        "validationMessage"
                );

        return !message.isEmpty();
    }
}