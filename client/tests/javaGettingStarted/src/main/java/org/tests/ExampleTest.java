package org.tests;

import com.microsoft.playwright.*;

public class ExampleTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            page.getByText("Getting Started").click();
        }
    }
}