import { test as teardown } from '@playwright/test';

teardown.use({launchOptions: {slowMo: 2000}});

teardown('Navigate to Nevermind by Nirvana and back', async ({ page }) => {
    await page.goto('http://localhost:8081/reset');
});
