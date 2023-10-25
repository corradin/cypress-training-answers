package org.example;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.*;

class FirstPlaywrightTest {

    @Test
    void navigatePlaywrightSite() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
        }
    }

}