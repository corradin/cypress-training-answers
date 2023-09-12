import { expect, Page } from '@playwright/test';

export const createSong = async (page: Page, title: string) => {
  await page.goto('http://localhost:8080/');

  await page.getByRole('link', { name: 'add' }).click();
  await expect(page).toHaveURL('http://localhost:8080/#/songs/create');

  await page.getByLabel('Title').click();

  await page.getByLabel('Title').fill(title);

  await page.getByLabel('Title').press('Tab');

  await page.getByLabel('Artist').fill('Sample Artist');

  await page.getByLabel('Artist').press('Tab');

  await page.getByLabel('Genre').fill('Sample Genre');

  await page.getByLabel('Genre').press('Tab');

  await page.getByRole('textbox', { name: 'Album*' }).fill('Sample Album');

  await page.getByRole('textbox', { name: 'Album*' }).press('Tab');

  await page.getByLabel('Album Image Url').fill('localhost:8080');

  await page.getByLabel('Album Image Url').press('Tab');

  await page.getByLabel('YouTube ID').fill('SampleYouTubeID');

  await page.getByLabel('YouTube ID').press('Tab');

  await page.getByLabel('Tab').fill('Sample Tab');

  await page.getByLabel('Tab').press('Tab');

  await page.getByLabel('Lyrics').fill('Sample Lyrics');

  await page.getByRole('button', { name: 'Create Song' }).click();
  await expect(page).toHaveURL('http://localhost:8080/#/songs');

  await page.getByText(title).click();
};

export const clean = async (page: Page) => {
  await page.goto('http://localhost:8081/reset');
}