package org.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class Debugging {

    @Test
    void shouldNavigateToPlaywrightHomepage(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            page.pause();
            page.getByText("Getting Started").click();
        }
    }
}