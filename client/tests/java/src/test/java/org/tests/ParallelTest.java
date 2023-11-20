package org.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;
import org.tests.utils.Constants;

public class ParallelTest {

    @Test
    public void nirvanaNameTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(Constants.CLIENT_URL);
            page.getByText("Nirvana").locator("..")
                    .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("VIEW")).click();

            assertThat(page.getByText("Nirvana")).isVisible();
        }
    }

    @Test
    public void cypressHillNameTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(Constants.CLIENT_URL);
            page.getByText("Cypress Hill").locator("..")
                    .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("VIEW")).click();

            assertThat(page.getByText("Cypress Hill")).isVisible();
        }
    }
}