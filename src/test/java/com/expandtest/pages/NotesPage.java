package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class NotesPage extends BasePage {

    private final By addNoteButton = By.xpath("//a[contains(text(),'Add Note') or contains(text(),'+')]");
    private final By noteTitleField = By.cssSelector("input#title");
    private final By noteDescField = By.cssSelector("textarea#description");
    private final By categoryDropdown = By.cssSelector("select#category");
    private final By saveNoteButton = By.cssSelector("button[type='submit']");
    private final By noteCards = By.cssSelector(".card");
    private final By noteTitle = By.cssSelector(".card-title");
    private final By editButton = By.xpath("//a[contains(text(),'Edit')]");
    private final By deleteButton = By.xpath("//button[contains(text(),'Delete')]");
    private final By confirmDeleteButton = By.xpath("//button[contains(text(),'Delete') and @class[contains(.,'danger')]]");
    private final By successMessage = By.xpath("//div[contains(@class,'alert-success') or contains(@class,'success')]");
    private final By filterWork = By.xpath("//a[contains(text(),'Work')]");
    private final By filterPersonal = By.xpath("//a[contains(text(),'Personal')]");

    public NotesPage(WebDriver driver) {
        super(driver);
    }
    // ... rest of methods


    public void navigateToNotes() {
        driver.get("https://practice.expandtesting.com/notes/app");
        System.out.println("Navigated to Notes page");
    }

    public void clickAddNote() {
        click(addNoteButton);
        System.out.println("Clicked Add Note button");
    }

    public void enterTitle(String title) {
        type(noteTitleField, title);
        System.out.println("Entered note title: " + title);
    }

    public void enterDescription(String desc) {
        type(noteDescField, desc);
        System.out.println("Entered note description");
    }

    public void selectCategory(String category) {
        WebElement dropdown = waitForElement(categoryDropdown);
        new Select(dropdown).selectByVisibleText(category);
        System.out.println("Selected category: " + category);
    }

    public void clickSave() {
        click(saveNoteButton);
        System.out.println("Clicked Save button");
    }

    public void createNote(String title, String desc, String category) {
        clickAddNote();
        enterTitle(title);
        enterDescription(desc);
        selectCategory(category);
        clickSave();
    }

    public void clickEditOnFirstNote() {
        click(editButton);
        System.out.println("Clicked Edit on first note");
    }

    public void clickDeleteOnFirstNote() {
        click(deleteButton);
        System.out.println("Clicked Delete on first note");
    }

    public void confirmDelete() {
        click(confirmDeleteButton);
        System.out.println("Confirmed delete");
    }

    public void filterByWork() {
        click(filterWork);
        System.out.println("Filtered by Work");
    }

    public void filterByPersonal() {
        click(filterPersonal);
        System.out.println("Filtered by Personal");
    }


    public boolean isNoteVisible(String title) {
        try {
            List<WebElement> titles = driver.findElements(noteTitle);
            for (WebElement t : titles) {
                if (t.getText().contains(title)) {
                    System.out.println("Note found: " + title);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean isSuccessMessageVisible() {
        return isVisible(successMessage);
    }

    public boolean isNoteDeleted(String title) {
        return !isNoteVisible(title);
    }

    public int getNoteCount() {
        return driver.findElements(noteCards).size();
    }
}