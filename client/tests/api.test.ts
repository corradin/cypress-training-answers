import { expect, test } from '@playwright/test';

type Song = {
  id: number;
  title: string;
  artist: string;
  genre: string;
  album: string;
  albumImageUrl: string;
  youtubeId: string;
  lyrics: string;
  tab: string;
  createdAt: string;
  updatedAt: string;
};

test('Retrieve the songs from the API', async ({ request }) => {
  // ARRANGE
  const expectedSong: Song = {
    id: 1,
    title: 'Nevermind',
    artist: 'Nirvana',
    genre: 'Alternative Rock',
    album: 'Nevermind',
    albumImageUrl: 'https://is3-ssl.mzstatic.com/image/thumb/Features/d0/cc/62/dj.nanioukp.jpg/268x0w.jpg',
    youtubeId: 'm-ofL_3EZyE',
    lyrics: '',
    tab: '',
    createdAt: '2018-02-13T12:56:24.432Z',
    updatedAt: '',
  };

  const response = await request.get('http://localhost:8081/songs');
  expect(response.ok()).toBeTruthy();
  const responseArray: Song[] = await response.json();
  const actualSong = responseArray.find(item => item.id === expectedSong.id);
  expect(actualSong?.id).toBe(expectedSong.id);
  expect(actualSong?.title).toBe(expectedSong.title);
  expect(actualSong?.artist).toBe(expectedSong.artist);
  expect(actualSong?.genre).toBe(expectedSong.genre);
  expect(actualSong?.album).toBe(expectedSong.album);
  expect(actualSong?.albumImageUrl).toBe(expectedSong.albumImageUrl);
  expect(actualSong?.youtubeId).toBe(expectedSong.youtubeId);
  expect(actualSong?.lyrics).toBe(expectedSong.lyrics);
  expect(actualSong?.tab).toBe(expectedSong.tab);
  expect(actualSong?.createdAt).toBe(expectedSong.createdAt);
});
