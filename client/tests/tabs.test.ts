import { test, expect } from '@playwright/test';

test('Tabs', async ({ context }) => {
    const page1 = await context.newPage();
    const page2 = await context.newPage();

 
  await page1.goto('http://localhost:8080/');

  await page2.goto('http://localhost:8080/');

  const searchInput = await page1.getByPlaceholder('Search by song title, artist, album, or genre');

  await expect(searchInput).not.toBeFocused();
  await searchInput.click();
  await searchInput.type('test');

  //TODO: https://playwright.dev/docs/next/pages#handling-new-pages
  await expect(searchInput).toBeFocused()
});