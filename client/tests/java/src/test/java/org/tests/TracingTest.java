package org.tests;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import org.testng.annotations.Test;
// import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TracingTest {

    @Test
    public void tracingTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();

            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();
            page.navigate("http://playwright.dev");
            page.getByText("Getting Started").click();

            assertThat(page).hasURL("https://playwright.dev/docs/intro");

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }
    }
}