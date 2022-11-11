import { expect, test } from '@playwright/test';

test('Log all requests and responses', async ({ page }) => {
  page.on('request', request => console.log('>>', request.method(), request.url()));
  page.on('response', response => console.log('<<', response.status(), response.url()));

  await page.goto('http://localhost:8080');
});

test('Log song request and response', async ({ page }) => {
  page.on('request', request => {
    if (request.url().includes('songs')) {
      console.log('>>', request.method(), request.url());
    }
  });
  page.on('response', response => {
    if (response.url().includes('songs')) {
      console.log('<<', response.status(), response.url());
    }
  });

  await page.goto('http://localhost:8080');
});

test('Log image related request and response', async ({ page }) => {
  page.on('request', request => {
    if (request.url().endsWith('.jpg')) {
      console.log('>>', request.method(), request.url());
    }
  });
  page.on('response', response => {
    if (response.url().endsWith('.jpg')) {
      console.log('<<', response.status(), response.url());
    }
  });

  await page.goto('http://localhost:8080');
});

test('Return something', async ({ page }) => {
  const testData = [
    {
      id: 1000,
      title: 'My own random song',
      artist: 'Me',
      genre: 'Random',
      album: 'Random album',
      albumImageUrl: 'https://api.lorem.space/image/album?w=268',
      youtubeId: '',
      lyrics: '',
      tab: '',
    },
  ];

  page.route('**/songs', async route => {
    // Fetch original response.
    const response = await page.request.fetch(route.request());
    
    route.fulfill({
      // Pass all fields from the response.
      response,
      body: JSON.stringify(testData),
    });
  });

  await page.goto('http://localhost:8080');
});
