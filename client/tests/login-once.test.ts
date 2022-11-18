import { test, expect } from '@playwright/test';

test.use({storageState: 'tests/storage-state/storageState.json'});

test('Should contain Bookmarks component on homepage', async ({ page }) => {
  await page.goto('http://localhost:8080/');
  await expect(page.getByText('Bookmarks')).toBeVisible();
});

test('Should contain Recently Viewed Songs component on homepage', async ({ page }) => {
  await page.goto('http://localhost:8080/');
  await expect(page.getByText('Recently Viewed Songs')).toBeVisible();
});
