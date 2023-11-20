package org.tests.utils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.tests.POCOs.Song;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SongManager {
    Page page;

    public SongManager(Page page) {
        this.page = page;
    }

    public void createSong(Song song) {
        page.navigate(Constants.CLIENT_URL);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("add")).click();
        page.getByLabel("Title").fill(song.getTitle());
        page.getByLabel("Title").press("Tab");
        page.getByLabel("Artist").fill(song.getArtist());
        page.getByLabel("Artist").press("Tab");
        page.getByLabel("Genre").fill(song.getGenre());
        page.getByLabel("Genre").press("Tab");
        page.getByLabel("Album", new Page.GetByLabelOptions().setExact(true)).fill(song.getAlbum());
        page.getByLabel("Album Image Url").fill(song.getAlbumImageUrl());
        page.getByLabel("YouTube ID").fill(song.getYoutubeId());
        page.getByLabel("Tab").fill(song.getTab());
        page.getByLabel("Lyrics").fill(song.getLyrics());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Song")).click();

        assertThat(page).hasURL(Constants.CLIENT_URL + "/#/songs");
        assertThat(page.getByText(song.getTitle())).isVisible();
    }

    public void editSongTitle(String oldTitle, String newTitle) {
        page.navigate(Constants.CLIENT_URL);
        page.getByText(oldTitle).locator("..")
                .getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("VIEW")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("EDIT")).click();

        page.waitForURL(Constants.CLIENT_URL + "/#/songs/6/edit");
        page.getByLabel("Title").click();
        page.keyboard().type(newTitle, new Keyboard.TypeOptions().setDelay(100));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save Song")).click();
        page.waitForURL(Constants.CLIENT_URL + "/#/songs/6");
        page.getByText("TabTracker").click();

        assertThat(page).hasURL(Constants.CLIENT_URL + "/#/songs");
        assertThat(page.getByText(newTitle)).isVisible();
    }

    public void cleanDatabase() {
        page.navigate(Constants.SERVER_URL + "/reset");
    }
}
