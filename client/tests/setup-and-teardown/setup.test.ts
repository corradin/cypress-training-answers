import { test as setup } from '@playwright/test';
import dotenv from 'dotenv';

dotenv.config();

const username = process.env.TEST_USERNAME ?? '';
const password = process.env.TEST_PASSWORD ?? '';
const storageState = 'tests/storage-state/storageState.json';

// setup.use({launchOptions: {slowMo: 2000}});

setup('Navigate to Nevermind by Nirvana and back', async ({ page }) => {
    await page.goto('https://playwright.dev/');
});

setup('Login a user', async ({ page }) => {
  await page.goto('http://localhost:8080/');
  await page.getByRole('link', { name: 'Login' }).click();
  await page.getByRole('textbox', { name: 'Email' }).click();
  await page.getByRole('textbox', { name: 'Email' }).fill(username);
  await page.getByRole('textbox', { name: 'Password' }).click();
  await page.getByRole('textbox', { name: 'Password' }).fill(password);
  await page.getByRole('button', { name: 'Login' }).click({force: true});
  await page.waitForURL('http://localhost:8080/#/songs');
  await page.context().storageState({ path: storageState as string });
});
