package me.tanshul.csvreader.model;

/**
 * Created by tansdeva on 28/12/17.
 */

public class MediaItem {
    private String name;
    private String artist;
    private String album;

    public MediaItem(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}
