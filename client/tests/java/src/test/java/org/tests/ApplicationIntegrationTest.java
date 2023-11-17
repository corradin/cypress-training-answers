package org.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;
import org.tests.utils.Constants;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class ApplicationIntegrationTest {
    @Test
    void applicationIntegrationTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();
            page.navigate(Constants.CLIENT_URL + "/#/songs");
            page.getByPlaceholder("Search by song title, artist, album, or genre").fill("neverm");
            page.getByPlaceholder("Search by song title, artist, album, or genre").click();
            page.getByPlaceholder("Search by song title, artist, album, or genre").fill("nevermind");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View")).first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit")).click();
            page.getByLabel("Tab").fill("I like this music");
            page.getByLabel("Lyrics").fill("lalalala");
            page.getByLabel("Lyrics").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save Song")).click();
            page.getByRole(AriaRole.TEXTBOX).first().click();
            page.getByRole(AriaRole.TEXTBOX).nth(1).click();
        }
    }
}