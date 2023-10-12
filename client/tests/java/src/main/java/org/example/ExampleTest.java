package org.example;

import com.microsoft.playwright.*;


public class ExampleTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            System.out.println(page.title());
        }
        // try (Playwright playwright = Playwright.create()) {
        //     BrowserTypeLaunchOptions options = new BrowserTypeLaunchOptions().setHeadless(true);
        //     Browser browser = playwright.firefox().launch(options);
        //     Page page = browser.newPage();
        //     page.navigate("https://playwright.dev/");

        //     // Expect a title "to contain" a substring.
        //     assertTrue(page.title().contains("Playwright"));

        //     // create a locator
        //     String getStarted = "Get Started";

        //     // Expect an attribute "to be strictly equal" to the value.
        //     assertTrue(page.locator("text=" + getStarted).getAttribute("href").equals("/docs/intro"));

        //     // Click the get started link.
        //     page.locator("text=" + getStarted).click();

        //     // Expects the URL to contain intro.
        //     assertTrue(page.url().contains("intro"));

        //     browser.close();
        // }
    }
}