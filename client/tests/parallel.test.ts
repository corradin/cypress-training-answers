import { test, expect } from '@playwright/test';

test.describe.configure({mode: 'parallel'});

test('Check Nirvana page contains Nirvana', async ({ page }) => {
  await page.goto('http://localhost:8080/');

  await page.getByText('Nirvana').locator('..').getByRole('button', { name: 'View', exact: true }).click();

  await expect(page).toHaveURL('http://localhost:8080/#/songs/1');
  await page.getByText('Nirvana').click();
});

test('Check Cypress Hill page contains Cypress Hill', async ({ page }) => {
    await page.goto('http://localhost:8080/');

    await page.getByText('Cypress Hill').locator('..').getByRole('button', { name: 'View', exact: true }).click();
    
    await expect(page).toHaveURL('http://localhost:8080/#/songs/4');
    await page.getByText('Cypress Hill').click();
});
