package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailField = By.cssSelector("input#email");
    private final By passwordField = By.cssSelector("input#password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.xpath("//div[contains(@class,'alert')]");
    private final By successMessage = By.xpath("//div[contains(@class,'alert-success')]");
    private final By logoutButton = By.xpath("//a[text()='Logout']");
    private final By loginPageHeader = By.cssSelector("h1");
    private final By emailValidation = By.cssSelector(":invalid");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        driver.get("https://practice.expandtesting.com/notes/app/login");
        System.out.println("Navigated to login page");
    }

    public void enterEmail(String email) {
        type(emailField, email);
        System.out.println("Entered email: " + email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
        System.out.println("Entered password");
    }

    public void clickLogin() {
        click(loginButton);
        System.out.println("Clicked login button");
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void clickLogout() {
        click(logoutButton);
        System.out.println("Clicked logout");
    }

    public void submitEmptyForm() {
        click(loginButton);
        System.out.println("Submitted empty form");
    }

    public boolean isErrorMessageVisible() {
        return isVisible(errorMessage);
    }

    public boolean isSuccessMessageVisible() {
        return isVisible(successMessage);
    }

    public boolean isLogoutButtonVisible() {
        return isVisible(logoutButton);
    }

    public boolean isLoginPageVisible() {
        return isVisible(loginPageHeader);
    }

    public boolean isValidationMessageVisible() {
        return isVisible(emailValidation);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}