import { test, expect } from '@playwright/test';

test('Navigate to homepage and compare visual', async ({ page }) => {
  await page.goto('http://localhost:8080');
  await expect(page).toHaveScreenshot({ mask: [page.getByAltText('random-image')], maskColor: '#009863'});
});
