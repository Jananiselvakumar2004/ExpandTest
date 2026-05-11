package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // ===== LOCATORS =====
    private By emailField = By.cssSelector("input#email");
    private By passwordField = By.cssSelector("input#password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector("div.alert-danger");
    private By successMessage = By.cssSelector("div.alert-success");
    private By logoutButton = By.cssSelector("a[href='/notes/app/logout']");
    private By loginPageHeader = By.cssSelector("h1.title");
    private By emailValidation = By.cssSelector("div.invalid-feedback");

    // ===== CONSTRUCTOR =====
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ===== ACTIONS =====
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

    // ===== VERIFICATIONS =====
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