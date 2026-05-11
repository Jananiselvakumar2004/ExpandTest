package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class FormPage extends BasePage {


    private By inputField = By.cssSelector("input[type='number']");


    private By dropdownSelect = By.cssSelector("select");


    private By checkboxes = By.cssSelector("input[type='checkbox']");


    private By radioButtons = By.cssSelector("input[type='radio']");


    public FormPage(WebDriver driver) {
        super(driver);
    }


    public void navigateToInputs() {
        driver.get("https://practice.expandtesting.com/inputs");
        System.out.println("Navigated to Inputs page");
    }

    public void navigateToDropdown() {
        driver.get("https://practice.expandtesting.com/dropdown");
        System.out.println("Navigated to Dropdown page");
    }

    public void navigateToCheckboxes() {
        driver.get("https://practice.expandtesting.com/checkboxes");
        System.out.println("Navigated to Checkboxes page");
    }

    public void navigateToRadioButtons() {
        driver.get(
                "https://practice.expandtesting.com/radiobuttons");
        System.out.println("Navigated to Radio Buttons page");
    }

    public void enterNumberInput(String value) {
        type(inputField, value);
        System.out.println("Entered input value: " + value);
    }

    public String getInputValue() {
        return waitForElement(inputField)
                .getAttribute("value");
    }

    public void selectDropdownOption(String option) {
        WebElement dropdown = waitForElement(dropdownSelect);
        new Select(dropdown).selectByVisibleText(option);
        System.out.println("Selected dropdown option: " + option);
    }

    public String getSelectedDropdownValue() {
        WebElement dropdown = waitForElement(dropdownSelect);
        return new Select(dropdown)
                .getFirstSelectedOption().getText();
    }

    public void checkFirstCheckbox() {
        List<WebElement> boxes = driver.findElements(checkboxes);
        if (!boxes.get(0).isSelected()) {
            boxes.get(0).click();
        }
        System.out.println("Checked first checkbox");
    }

    public void uncheckFirstCheckbox() {
        List<WebElement> boxes = driver.findElements(checkboxes);
        if (boxes.get(0).isSelected()) {
            boxes.get(0).click();
        }
        System.out.println("Unchecked first checkbox");
    }

    public boolean isFirstCheckboxChecked() {
        return driver.findElements(checkboxes)
                .get(0).isSelected();
    }

    public void selectFirstRadioButton() {
        List<WebElement> radios = driver.findElements(radioButtons);
        radios.get(0).click();
        System.out.println("Selected first radio button");
    }

    public void selectSecondRadioButton() {
        List<WebElement> radios = driver.findElements(radioButtons);
        radios.get(1).click();
        System.out.println("Selected second radio button");
    }

    public boolean isOnlyOneRadioSelected() {
        List<WebElement> radios = driver.findElements(radioButtons);
        int count = 0;
        for (WebElement r : radios) {
            if (r.isSelected()) count++;
        }
        return count == 1;
    }
}