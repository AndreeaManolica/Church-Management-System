package service;

import model.songs.Song;

import java.util.List;

public interface SongService {

    List<Song> getSongs();
    Song getNewSong();
    void deleteSong();
    Song findSongByName();
}
