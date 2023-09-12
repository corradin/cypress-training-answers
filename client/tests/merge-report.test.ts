import { test } from '@playwright/test';

for (let i = 1; i < 3; i++) {
  test.use({ headless: true });
  test(`test search Playwright version 1.${i}`, async ({ page }) => {
    await page.goto('https://playwright.dev/');
    await page.getByRole('button', { name: 'Search' }).click();
    await page.getByPlaceholder('Search docs').fill(`${i}`);
    await page.getByPlaceholder('Search docs').press('Enter');
  });
}
