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

    // LOCATORS
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

    // OPEN LOGIN PAGE
    public void navigateToLoginPage() {

        driver.get(
                "https://practice.expandtesting.com/login"
        );

        System.out.println(" Navigated to Login page");
    }

    // ENTER USERNAME
    public void enterUsername(String username) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField
                )
        ).sendKeys(username);

        System.out.println(" Username entered");
    }

    // ENTER PASSWORD
    public void enterPassword(String password) {

        driver.findElement(passwordField)
                .sendKeys(password);

        System.out.println(" Password entered");
    }

    // CLICK LOGIN
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

    // LOGIN METHOD
    public void login(String username,
                      String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }

    // LOGOUT
    public void clickLogout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        logoutButton
                )
        ).click();

        System.out.println(" Logout button clicked");
    }

    // LOGOUT BUTTON CHECK
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

    // ERROR MESSAGE CHECK
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

    // ERROR TEXT
    public String getErrorMessageText() {

        try {

            return driver.findElement(errorMessage)
                    .getText();

        } catch (Exception e) {

            return "";
        }
    }

    // LOGIN PAGE CHECK
    public boolean isLoginPageDisplayed() {

        return driver.getCurrentUrl()
                .contains("/login");
    }

    // EMPTY LOGIN
    public void submitEmptyLoginForm() {

        clickLogin();
    }

    // VALIDATION MESSAGE
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