package com.expandtest.pages;

import com.expandtest.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class NotesPage extends BasePage {



    private final By addNoteButton      = By.xpath("//a[contains(text(),'Add Note') or contains(text(),'+')]");
    private final By noteTitleField     = By.cssSelector("input#title");
    private final By noteDescField      = By.cssSelector("textarea#description");
    private final By categoryDropdown   = By.cssSelector("select#category");
    private final By saveNoteButton     = By.cssSelector("button[type='submit']");
    private final By noteCards          = By.cssSelector(".card");
    private final By noteTitleText      = By.cssSelector(".card-title");
    private final By editNoteButton     = By.xpath("//a[contains(text(),'Edit')]");
    private final By deleteNoteButton   = By.xpath("//button[contains(text(),'Delete')]");
    private final By confirmDeleteBtn   = By.xpath("//button[contains(text(),'Delete') and contains(@class,'danger')]");
    private final By successAlert       = By.xpath("//div[contains(@class,'alert-success') or contains(@class,'success')]");
    private final By filterWorkLink     = By.xpath("//a[contains(text(),'Work')]");
    private final By filterPersonalLink = By.xpath("//a[contains(text(),'Personal')]");


    public NotesPage(WebDriver driver) {
        super(driver);
    }


    public void navigateToNotes() {
        driver.get(ConfigReader.get("notesUrl"));
        System.out.println("[INFO] Navigated to Notes page: "
                + ConfigReader.get("notesUrl"));
    }


    public void clickAddNote() {
        click(addNoteButton);
        System.out.println("[INFO] Clicked Add Note button");
    }


    public void enterTitle(String title) {
        type(noteTitleField, title);
        System.out.println("[INFO] Note title entered: " + title);
    }


    public void enterDescription(String description) {
        type(noteDescField, description);
        System.out.println("[INFO] Note description entered");
    }


    public void selectCategory(String category) {
        WebElement dropdown = waitForElement(categoryDropdown);
        new Select(dropdown).selectByVisibleText(category);
        System.out.println("[INFO] Category selected: " + category);
    }


    public void clickSave() {
        click(saveNoteButton);
        System.out.println("[INFO] Save button clicked");
    }


    public void createNote(String title, String description,
                           String category) {
        clickAddNote();
        enterTitle(title);
        enterDescription(description);
        selectCategory(category);
        clickSave();
        System.out.println("[INFO] Note created: " + title);
    }

    public void clickEditOnFirstNote() {
        click(editNoteButton);
        System.out.println("[INFO] Edit button clicked on first note");
    }


    public void clickDeleteOnFirstNote() {
        click(deleteNoteButton);
        System.out.println("[INFO] Delete button clicked on first note");
    }


    public void confirmDelete() {
        click(confirmDeleteBtn);
        System.out.println("[INFO] Delete confirmed");
    }


    public void filterByWork() {
        click(filterWorkLink);
        System.out.println("[INFO] Filtered by Work category");
    }

    public void filterByPersonal() {
        click(filterPersonalLink);
        System.out.println("[INFO] Filtered by Personal category");
    }


    public boolean isNoteVisible(String title) {
        try {
            List<WebElement> titles = driver.findElements(noteTitleText);
            for (WebElement t : titles) {
                if (t.getText().contains(title)) {
                    System.out.println("[INFO] Note found: " + title);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("[INFO] Note not found: " + title);
        }
        return false;
    }


    public boolean isSuccessMessageVisible() {
        boolean result = isVisible(successAlert);
        System.out.println("[INFO] Success message visible: " + result);
        return result;
    }


    public boolean isNoteDeleted(String title) {
        boolean deleted = !isNoteVisible(title);
        System.out.println("[INFO] Note deleted: " + deleted);
        return deleted;
    }


    public int getNoteCount() {
        int count = driver.findElements(noteCards).size();
        System.out.println("[INFO] Note count: " + count);
        return count;
    }
}