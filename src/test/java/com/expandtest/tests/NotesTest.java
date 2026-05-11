package com.expandtest.tests;

import com.expandtest.pages.LoginPage;
import com.expandtest.pages.NotesPage;
import com.expandtest.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotesTest extends BaseTest {

    LoginPage loginPage;
    NotesPage notesPage;

    @BeforeMethod
    public void loginFirst() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(
                ConfigReader.get("email"),
                ConfigReader.get("password"));
        notesPage = new NotesPage(driver);
        notesPage.navigateToNotes();
    }

    @Test(priority = 1)
    public void testCreateNote() {
        System.out.println("[TEST] Create note test");
        notesPage.createNote("Hackathon Note", "Selenium Practice", "Work");
        Assert.assertTrue(notesPage.isNoteVisible("Hackathon Note"),
                "Note not created");
        System.out.println("[PASS] Create note passed");
    }

    @Test(priority = 2)
    public void testEditNote() {
        System.out.println("[TEST] Edit note test");
        notesPage.clickEditOnFirstNote();
        notesPage.enterTitle("Updated Note");
        notesPage.clickSave();
        Assert.assertTrue(notesPage.isSuccessMessageVisible(),
                "Edit failed");
        System.out.println("[PASS] Edit note passed");
    }

    @Test(priority = 3)
    public void testDeleteNote() {
        System.out.println("[TEST] Delete note test");
        notesPage.clickDeleteOnFirstNote();
        notesPage.confirmDelete();
        Assert.assertTrue(notesPage.isSuccessMessageVisible(),
                "Delete failed");
        System.out.println("[PASS] Delete note passed");
    }

    @Test(priority = 4)
    public void testFilterNotes() {
        System.out.println("[TEST] Filter notes test");
        notesPage.createNote("Work Note", "Filter test", "Work");
        notesPage.filterByWork();
        Assert.assertTrue(notesPage.getNoteCount() > 0,
                "No notes after filter");
        System.out.println("[PASS] Filter notes passed");
    }
}