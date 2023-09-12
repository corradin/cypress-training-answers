import { test as teardown } from '@playwright/test';

// teardown.use({launchOptions: {slowMo: 2000}});
const storageState = 'tests/storage-state/storageState.json';

teardown.use({storageState: storageState});

teardown('Log out', async ({ page }) => {
    await page.goto('http://localhost:8080/#/songs');
    await page.getByText('Log Out').click();
    await page.context().storageState({ path: storageState as string });
});
