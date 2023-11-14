package org.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class ExampleTest {

    @Test
    public void exampleTest(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            page.pause();
            page.getByText("Getting Started").click();
        }
    }

    public static void main(String[] args) {
        ExampleTest test = new ExampleTest();
        test.exampleTest();
    }
}