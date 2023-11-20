package org.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.tests.utils.Constants;

public class End2EndTest {

    // Ensures the database is always left empty between tests, even when a test fails.
    @AfterTest(alwaysRun = true)
    void cleanDatabase() {
         try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(Constants.SERVER_URL + "/reset");
         }
    }

    @Test
    public void addSongTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(Constants.CLIENT_URL);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("add")).click();
            page.getByLabel("Title").fill("Everlong");
            page.getByLabel("Title").press("Tab");
            page.getByLabel("Artist").fill("Foo Fighters");
            page.getByLabel("Artist").press("Tab");
            page.getByLabel("Genre").fill("Rock");
            page.getByLabel("Genre").press("Tab");
            page.getByLabel("Album", new Page.GetByLabelOptions().setExact(true)).fill("The Color and the Shape");
            page.getByLabel("Album Image Url").fill(
                    "https://en.wikipedia.org/wiki/The_Colour_and_the_Shape#/media/File:FooFighters-TheColourAndTheShape.jpg");
            page.getByLabel("YouTube ID").fill("https://www.youtube.com/watch?v=eBG7P-K-r1Y");
            page.getByLabel("Tab").fill("dsfa");
            page.getByLabel("Lyrics").fill("fdas");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Song")).click();
            
            assertThat(page).hasURL(Constants.CLIENT_URL + "/#/songs");
            assertThat(page.getByText("Everlong")).isVisible();
        }
    }

    @Test
    public void editSongTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            // Create song
            page.navigate(Constants.CLIENT_URL);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("add")).click();
            page.getByLabel("Title").fill("Everlong");
            page.getByLabel("Title").press("Tab");
            page.getByLabel("Artist").fill("Foo Fighters");
            page.getByLabel("Artist").press("Tab");
            page.getByLabel("Genre").fill("Rock");
            page.getByLabel("Genre").press("Tab");
            page.getByLabel("Album", new Page.GetByLabelOptions().setExact(true)).fill("The Color and the Shape");
            page.getByLabel("Album Image Url").fill(
                    "https://en.wikipedia.org/wiki/The_Colour_and_the_Shape#/media/File:FooFighters-TheColourAndTheShape.jpg");
            page.getByLabel("YouTube ID").fill("https://www.youtube.com/watch?v=eBG7P-K-r1Y");
            page.getByLabel("Tab").fill("dsfa");
            page.getByLabel("Lyrics").fill("fdas");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Song")).click();
            
            assertThat(page).hasURL(Constants.CLIENT_URL + "/#/songs");
            assertThat(page.getByText("Everlong")).isVisible();

            //Edit song
            page.navigate(Constants.CLIENT_URL);
            page.getByText("Everlong").locator("..").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("VIEW")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("EDIT")).click();

            page.waitForURL(Constants.CLIENT_URL + "/#/songs/6/edit");
            page.getByLabel("Title").click();
            page.keyboard().type("The Pretender", new Keyboard.TypeOptions().setDelay(100));
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save Song")).click();
            page.waitForURL(Constants.CLIENT_URL + "/#/songs/6");
            page.getByText("TabTracker").click();

            assertThat(page).hasURL(Constants.CLIENT_URL + "/#/songs");
            assertThat(page.getByText("The Pretender")).isVisible();
        }
    }

    @Test
    public void searchTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(Constants.CLIENT_URL);

            Locator searchInput = page.getByPlaceholder("Search by song title, artist, album, or genre");

            assertThat(searchInput).not().isFocused();
            searchInput.click();
            assertThat(searchInput).isFocused();
        }
    }
}