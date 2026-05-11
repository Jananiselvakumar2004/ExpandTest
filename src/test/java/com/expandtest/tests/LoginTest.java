package com.expandtest.tests;

import com.expandtest.pages.LoginPage;
import com.expandtest.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage lp;

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
                { ConfigReader.get("email"), ConfigReader.get("password"), true },
                { "wrong@email.com", "wrongpass", false }
        };
    }

    @Test(dataProvider = "loginData", priority = 1)
    public void testLogin(String email, String pass, boolean shouldPass) {
        lp = new LoginPage(driver);
        lp.navigateToLogin();
        lp.login(email, pass);

        if (shouldPass) {
            Assert.assertTrue(lp.isLogoutButtonVisible(),
                    "Valid login failed");
            System.out.println("Valid login PASSED");
        } else {
            Assert.assertTrue(lp.isErrorMessageVisible(),
                    "Error not shown for invalid login");
            System.out.println("Invalid login PASSED");
        }
    }

    @Test(priority = 2)
    public void testLogout() {
        lp = new LoginPage(driver);
        lp.navigateToLogin();
        lp.login(ConfigReader.get("email"), ConfigReader.get("password"));
        lp.clickLogout();
        Assert.assertTrue(lp.isLoginPageVisible(), "Logout failed");
        System.out.println("Logout PASSED");
    }

    @Test(priority = 3)
    public void testEmptyLogin() {
        lp = new LoginPage(driver);
        lp.navigateToLogin();
        lp.submitEmptyForm();
        Assert.assertTrue(lp.isValidationMessageVisible(),
                "Validation not shown");
        System.out.println("Empty login validation PASSED");
    }
}