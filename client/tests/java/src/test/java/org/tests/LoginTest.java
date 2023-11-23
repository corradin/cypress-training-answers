package org.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import io.github.cdimascio.dotenv.Dotenv;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tests.utils.Constants;
import org.tests.utils.SongManager;

public class LoginTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    SongManager songManager;

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

    @Test
    public void loginTest() {
        Dotenv dotenv = Dotenv.load();

        page.navigate(Constants.CLIENT_URL);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"))
                .fill(dotenv.get("TEST_USERNAME"));
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"))
                .fill(dotenv.get("TEST_PASSWORD"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        //If we leave this out, it will be too fast to create a logged in state.
        page.waitForURL(Constants.CLIENT_URL + "/#/songs");
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(Constants.AUTH_STATE_PATH)));
        assertThat(page.getByText("Bookmarks")).isVisible();
    }

    // @Test
    // public void loginAnotherTest() {
    //     Dotenv dotenv = Dotenv.load();

    //     page.navigate(Constants.CLIENT_URL);
    //     page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
    //     page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).click();
    //     page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"))
    //             .fill(dotenv.get("TEST_USERNAME"));
    //     page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
    //     page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"))
    //             .fill(dotenv.get("TEST_PASSWORD"));
    //     page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    //     assertThat(page.getByText("Recently Viewed Songs")).isVisible();
    // }
}