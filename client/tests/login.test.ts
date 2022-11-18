import { test, expect } from '@playwright/test';
import dotenv from 'dotenv';

dotenv.config();

const username = process.env.TEST_USERNAME ?? '';
const password = process.env.TEST_PASSWORD ?? '';

test('Log in - should contain Bookmarks component on homepage', async ({ page }) => {
  await page.goto('http://localhost:8080/');

  await page.getByRole('link', { name: 'Login' }).click();
  await expect(page).toHaveURL('http://localhost:8080/#/login');

  await page.getByRole('textbox', { name: 'Email' }).click();

  await page.getByRole('textbox', { name: 'Email' }).fill(username);

  await page.getByRole('textbox', { name: 'Password' }).click();

  await page.getByRole('textbox', { name: 'Password' }).fill(password);

  await page.getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText('Bookmarks')).toBeVisible();
});

test('Log in - should contain Recently Viewed Songs component on homepage', async ({ page }) => {
  await page.goto('http://localhost:8080/');

  await page.getByRole('link', { name: 'Login' }).click();
  await expect(page).toHaveURL('http://localhost:8080/#/login');

  await page.getByRole('textbox', { name: 'Email' }).click();

  await page.getByRole('textbox', { name: 'Email' }).fill(username);

  await page.getByRole('textbox', { name: 'Password' }).click();

  await page.getByRole('textbox', { name: 'Password' }).fill(password);

  await page.getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText('Recently Viewed Songs')).toBeVisible();
});
