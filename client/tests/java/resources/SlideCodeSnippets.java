import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class SlideCodeSnippets {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        songManager = new SongManager(page);
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    void selectors() {
        // Text
        page.getByText("Log Out").click();

        // Role
        page.getByRole(AriaRole.BUTTON).click();
        page.getByRole(AriaRole.BUTTON,
                new GetByRoleOptions().setName("Log in")).click();

        // CSS
        page.locator("button").click();
        page.locator("#nav-bar .contact-us-item").click();

        // Layout
        // Fill an input to the right of "Username".
        page.locator("input:right-of(:text(\"Username\"))").fill("value");

        // Click a button near the promo card.
        page.locator("button:near(.promo-card)").click();

        // Click the radio input in the list closest to the "Label 3".
        page.locator("[type=radio]:left-of(:text(\"Label 3\"))").first().click();

        // Chaining selectors
        page.locator("#nav-bar >> div:has-text('Contact Us')").click();

        // Chaining locators (Recommended)
        page.locator("#nav-bar").getByText("Contact us").click();

        // Test Id
        page.getByTestId("button").click();

        // Don'ts
        // Avoid long CSS or XPath chains
        page.locator(
                "#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input").click();
        page.locator("//*[@id='tsf']/div[2]/div[1]/div[1]/div/div[2]/input").click();
    }

    void testAnatomy() {
        // Web first assertion
        assertThat(page.getByText("Playwright")).isVisible();

        // Locator
        page.locator("text=Get Started");

        // Element selectors
        page.getByText("Log Out");
    }
}

// Test parameters
public class MyDataProvider {
    @DataProvider(name = "my-data-provider")
    public Object[][] dpMethod() {
        return new Object[][] { { "First-Value" }, { "Second-Value" } };
    }

    @Test(dataProvider = "my-data-provider")
    public void myTest(String val) {
        System.out.println("Passed Parameter Is : " + val);
    }
}
