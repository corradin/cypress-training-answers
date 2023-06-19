import { chromium, FullConfig } from '@playwright/test';
import dotenv from 'dotenv';

dotenv.config();

const username = process.env.TEST_USERNAME ?? '';
const password = process.env.TEST_PASSWORD ?? '';

async function globalSetup(config: FullConfig) {
  const { storageState } = config.projects[0].use;
  const browser = await chromium.launch();
  const page = await browser.newPage();
  // await page.context().tracing.start({ screenshots: true, snapshots: true });
  // await page.goto('http://localhost:8080/');
  // await page.getByRole('link', { name: 'Login' }).click();
  // await page.getByRole('textbox', { name: 'Email' }).click();
  // await page.getByRole('textbox', { name: 'Email' }).fill(username);
  // await page.getByRole('textbox', { name: 'Password' }).click();
  // await page.getByRole('textbox', { name: 'Password' }).fill(password);
  // await page.getByRole('button', { name: 'Login' }).click({force: true});
  // await page.waitForURL('http://localhost:8080/#/songs');
  await page.context().storageState({ path: storageState as string });
  // await page.context().tracing.stop({
  //   path: './test-results-setup/failed-setup-trace.zip',
  // });
  await browser.close();
}

export default globalSetup;
