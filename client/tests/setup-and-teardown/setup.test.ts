import { test as setup } from '@playwright/test';

// setup.use({launchOptions: {slowMo: 2000}});

setup('Navigate to Nevermind by Nirvana and back', async ({ page }) => {
    await page.goto('https://playwright.dev/');
});
