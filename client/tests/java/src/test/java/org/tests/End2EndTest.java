package org.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import org.testng.annotations.Test;

public class End2EndTest {
    @Test
    public void testAddingSong() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("http://localhost:8080/");
            page.navigate("http://localhost:8080/#/");
            page.navigate("http://localhost:8080/#/songs");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("add")).click();
            page.getByLabel("Title").fill("Everlong");
            page.getByLabel("Title").press("Tab");
            page.getByLabel("Artist").fill("Foo Fighters");
            page.getByLabel("Artist").press("Tab");
            page.getByLabel("Genre").fill("Rock");
            page.getByLabel("Genre").press("Tab");
            page.getByLabel("Album", new Page.GetByLabelOptions().setExact(true)).fill("The Color and the Shape");
            page.getByLabel("Album Image Url").fill(
                    "https://en.wikipedia.org/wiki/The_Colour_and_the_Shape#/media/File:FooFighters-TheColourAndTheShape.jpg");
            page.getByLabel("YouTube ID").fill("https://www.youtube.com/watch?v=eBG7P-K-r1Y");
            page.getByLabel("Tab").fill("dsfa");
            page.getByLabel("Lyrics").fill("fdas");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Song")).click();
        }
    }
}