package com.expandtest.tests;

import com.expandtest.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {



    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {

        return new Object[][]{

                {
                        "practice",
                        "SuperSecretPassword!",
                        true
                },

                {
                        "wronguser",
                        "wrongpassword123",
                        false
                }
        };
    }



    @Test(dataProvider = "loginCredentials", priority = 1)

    public void testLogin(String username,
                          String password,
                          boolean shouldPass) {

        System.out.println(
                " Login test for: " + username);

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.enterUsername(username);

        loginPage.enterPassword(password);

        loginPage.clickLogin();

        if (shouldPass) {

            Assert.assertTrue(
                    loginPage.isLogoutButtonVisible(),
                    "Logout button not visible after valid login"
            );

            System.out.println(
                    "[PASS] Valid login passed");

        } else {

            Assert.assertTrue(
                    loginPage.isErrorMessageDisplayed(),
                    "Error message not displayed for invalid login"
            );

            System.out.println(
                    "[PASS] Invalid login validation passed");
        }
    }



    @Test(priority = 2)

    public void testLogout() {

        System.out.println(
                " Logout functionality test");

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.enterUsername("practice");

        loginPage.enterPassword("SuperSecretPassword!");

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLogoutButtonVisible(),
                "Login failed — cannot test logout"
        );

        loginPage.clickLogout();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed()
                        || driver.getCurrentUrl().contains("login"),
                "Login page not displayed after logout"
        );

        System.out.println(
                " Logout redirected successfully");
    }



    @Test(priority = 3)

    public void testInvalidLoginErrorMessage() {

        System.out.println(
                "[TEST] Invalid login error message test");

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.enterUsername("wronguser");

        loginPage.enterPassword("wrongpassword");

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message not displayed"
        );

        System.out.println(
                "[PASS] Invalid login error displayed");
    }
}