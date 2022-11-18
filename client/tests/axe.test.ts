import { test, expect } from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';

test('should not violate accessibility standards', async ({ page }) => {
  await page.goto('http://localhost:8080');

  const axeScanResults = await new AxeBuilder({ page }).withTags('wcag2a').analyze();

  expect(axeScanResults.violations).toEqual([]);
});
