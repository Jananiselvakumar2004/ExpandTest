package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class FormPage extends BasePage {

    private final By inputField = By.cssSelector("input[type='number']");
    private final By dropdownSelect = By.cssSelector("select");
    private final By checkboxes = By.cssSelector("input[type='checkbox']");
    private final By radioButtons = By.cssSelector("input[type='radio']");

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
        driver.get("https://practice.expandtesting.com/radio-buttons");
        System.out.println("Navigated to Radio Buttons page");
    }

    public void enterNumberInput(String value) {
        type(inputField, value);
        System.out.println("Entered: " + value);
    }

    public String getInputValue() {
        return waitForElement(inputField).getAttribute("value");
    }

    public void selectDropdownOption(String option) {
        new Select(waitForElement(dropdownSelect))
                .selectByVisibleText(option);
        System.out.println("Selected: " + option);
    }

    public String getSelectedDropdownValue() {
        return new Select(waitForElement(dropdownSelect))
                .getFirstSelectedOption().getText();
    }

    public void checkFirstCheckbox() {
        List<WebElement> boxes = driver.findElements(checkboxes);
        if (!boxes.getFirst().isSelected())
            boxes.getFirst().click();
        System.out.println("Checked first checkbox");
    }

    public void uncheckFirstCheckbox() {
        List<WebElement> boxes = driver.findElements(checkboxes);
        if (boxes.getFirst().isSelected())
            boxes.getFirst().click();
        System.out.println("Unchecked first checkbox");
    }

    public boolean isFirstCheckboxChecked() {
        return driver.findElements(checkboxes)
                .getFirst().isSelected();
    }

    public void selectFirstRadioButton() {
        waitForElement(radioButtons);
        List<WebElement> radios = driver.findElements(radioButtons);
        if (!radios.isEmpty()) {
            radios.getFirst().click();
            System.out.println("Selected first radio");
        }
    }

    public void selectSecondRadioButton() {
        List<WebElement> radios = driver.findElements(radioButtons);
        if (radios.size() > 1) {
            radios.get(1).click();
            System.out.println("Selected second radio");
        }
    }

    public boolean isOnlyOneRadioSelected() {
        int count = 0;
        for (WebElement r : driver.findElements(radioButtons))
            if (r.isSelected()) count++;
        return count == 1;
    }
}