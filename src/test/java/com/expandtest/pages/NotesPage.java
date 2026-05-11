package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class NotesPage extends BasePage {


    private By addNoteButton = By.cssSelector("a.btn-primary[href='/notes/app/notes/add']");
    private By noteTitleField = By.cssSelector("input#title");
    private By noteDescField = By.cssSelector("textarea#description");
    private By categoryDropdown = By.cssSelector("select#category");
    private By saveNoteButton = By.cssSelector("button[type='submit']");
    private By noteCards = By.cssSelector("div.card-body");
    private By noteTitle = By.cssSelector("div.card-body a.card-title");
    private By editButton = By.cssSelector("a.btn-warning");
    private By deleteButton = By.cssSelector("button.btn-danger");
    private By confirmDeleteButton = By.cssSelector("button#confirmDeleteNote");
    private By successMessage = By.cssSelector("div.alert-success");
    private By filterHome = By.cssSelector("a[href='/notes/app']");
    private By filterWork = By.cssSelector("a[data-filter='Work']");
    private By filterPersonal = By.cssSelector("a[data-filter='Personal']");


    public NotesPage(WebDriver driver) {
        super(driver);
    }


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