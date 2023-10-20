package org.example;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.*;

class FirstPlaywrightTest {

    @Test
    void navigatePlaywrightSite() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            System.out.println(page.title());
        }
    }

}