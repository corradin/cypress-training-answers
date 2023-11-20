package org.tests.POCOs;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private String album;
    private String albumImageUrl;
    private String youtubeId;
    private String tab;
    private String lyrics;

    // Constructor
    public Song(String title, String artist, String genre, String album, String albumImageUrl, String youtubeId, String tab, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.albumImageUrl = albumImageUrl;
        this.youtubeId = youtubeId;
        this.tab = tab;
        this.lyrics = lyrics;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
