import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('http://localhost:8080/');

  await page.getByRole('textbox', { name: 'Search by song title, artist, album, or genre' }).fill('nirvana');

  await expect(page).toHaveURL('http://localhost:8080/#/songs?search=nirvana');
});
