package com.expandtest.tests;

import com.expandtest.pages.LoginPage;
import com.expandtest.pages.NotesPage;
import com.expandtest.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotesTest extends BaseTest {

    LoginPage lp;
    NotesPage np;

    @BeforeMethod
    public void loginFirst() {
        lp = new LoginPage(driver);
        lp.navigateToLogin();
        lp.login(ConfigReader.get("email"), ConfigReader.get("password"));
        np = new NotesPage(driver);
        np.navigateToNotes();
    }

    @Test(priority = 1)
    public void testCreateNote() {
        np.createNote("Hackathon Note", "Selenium Practice", "Work");
        Assert.assertTrue(np.isNoteVisible("Hackathon Note"),
                "Note not created");
        System.out.println("Create Note PASSED");
    }

    @Test(priority = 2)
    public void testEditNote() {
        np.clickEditOnFirstNote();
        np.enterTitle("Updated Note");
        np.clickSave();
        Assert.assertTrue(np.isSuccessMessageVisible(),
                "Edit failed");
        System.out.println("Edit Note PASSED");
    }

    @Test(priority = 3)
    public void testDeleteNote() {
        np.clickDeleteOnFirstNote();
        np.confirmDelete();
        Assert.assertTrue(np.isSuccessMessageVisible(),
                "Delete failed");
        System.out.println("Delete Note PASSED");
    }

    @Test(priority = 4)
    public void testFilterNotes() {
        np.createNote("Work Note", "Filter test", "Work");
        np.filterByWork();
        Assert.assertTrue(np.getNoteCount() > 0,
                "Filter returned no notes");
        System.out.println("Filter Notes PASSED");
    }
}