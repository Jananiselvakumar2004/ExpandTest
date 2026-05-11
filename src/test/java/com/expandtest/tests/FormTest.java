package com.expandtest.tests;

import com.expandtest.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormTest extends BaseTest {

    FormPage fp;

    @Test(priority = 1)
    public void testInputField() {
        fp = new FormPage(driver);
        fp.navigateToInputs();
        fp.enterNumberInput("42");
        Assert.assertEquals(fp.getInputValue(), "42",
                "Input value mismatch");
        System.out.println("Input field PASSED");
    }

    @Test(priority = 2)
    public void testDropdown() {
        fp = new FormPage(driver);
        fp.navigateToDropdown();
        fp.selectDropdownOption("Option 1");
        Assert.assertEquals(fp.getSelectedDropdownValue(),
                "Option 1", "Dropdown mismatch");
        System.out.println("Dropdown PASSED");
    }

    @Test(priority = 3)
    public void testCheckbox() {
        fp = new FormPage(driver);
        fp.navigateToCheckboxes();
        fp.checkFirstCheckbox();
        Assert.assertTrue(fp.isFirstCheckboxChecked(),
                "Checkbox not checked");
        fp.uncheckFirstCheckbox();
        Assert.assertFalse(fp.isFirstCheckboxChecked(),
                "Checkbox not unchecked");
        System.out.println("Checkbox PASSED");
    }

    @Test(priority = 4)
    public void testRadioButton() {
        fp = new FormPage(driver);
        fp.navigateToRadioButtons();
        fp.selectFirstRadioButton();
        fp.selectSecondRadioButton();
        Assert.assertTrue(fp.isOnlyOneRadioSelected(),
                "Multiple radios selected");
        System.out.println("Radio button PASSED");
    }
}