package org.tests;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tests.utils.Constants;
import org.tests.utils.SongManager;

public class LoginRequiredTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    SongManager songManager;

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(Constants.AUTH_STATE_PATH)));
        page = context.newPage();
        songManager = new SongManager(page);
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    @Test
    public void recentlyViewedSongsTest() {
        page.navigate(Constants.CLIENT_URL);
        assertThat(page.getByText("Recently Viewed Songs")).isVisible();
    }
}