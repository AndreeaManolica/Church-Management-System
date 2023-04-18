package service;

import model.songs.Song;

import java.util.List;

public interface SongViewService {
    public void showSongs(List<Song> songs);
    public void showSong(Song song);
}
