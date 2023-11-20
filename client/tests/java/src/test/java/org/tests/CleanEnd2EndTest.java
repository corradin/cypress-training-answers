package org.tests;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tests.POCOs.Song;
import org.tests.utils.Constants;
import org.tests.utils.SongManager;

public class CleanEnd2EndTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    SongManager songManager;

    String title = "Everlong";
    String artist = "Foo Fighters";
    String genre = "Rock";
    String album = "The Color and the Shape";
    String albumImageUrl = "https://en.wikipedia.org/wiki/The_Colour_and_the_Shape#/media/File:FooFighters-TheColourAndTheShape.jpg";
    String youtubeId = "https://www.youtube.com/watch?v=eBG7P-K-r1Y";
    String tab = "dsfa";
    String lyrics = "fdas";

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        songManager = new SongManager(page);
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    @AfterMethod(alwaysRun = true)
    void cleanDatabase() {
        songManager.cleanDatabase();
    }

    @Test
    public void addSongTest() {
        songManager.createSong(new Song(title, artist, genre, album, albumImageUrl, youtubeId, tab, lyrics));
    }

    @Test
    public void editSongTest() {
        songManager.createSong(new Song(title, artist, genre, album, albumImageUrl, youtubeId, tab, lyrics));

        // Edit song
        songManager.editSongTitle(title, "The Pretenders");
    }

    @Test
    public void searchTest() {
            page.navigate(Constants.CLIENT_URL);

            Locator searchInput = page.getByPlaceholder("Search by song title, artist, album, or genre");

            assertThat(searchInput).not().isFocused();
            searchInput.click();
            assertThat(searchInput).isFocused();
    }
}