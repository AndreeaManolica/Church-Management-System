package service;

import model.songs.Song;

import java.util.List;

public class SongViewServiceImpl implements SongViewService{

    @Override
    public void showSongs(List<Song> songs) {
        System.out.println();
        if (songs != null && !songs.isEmpty()) {
            for (int i = 0; i < songs.size(); i++) {
                Song song = songs.get(i);
                System.out.println(i + 1 + "." + song.getTitle());
            }
        } else {
            System.out.println("Ne pare rau, dar nu ai cantace in church center. Adauga cantec te rog :)");
        }
        System.out.println();
    }

    @Override
    public void showSong(Song song) {
        if (song != null) {
            System.out.println("Titlu: " + song.getTitle());
            System.out.println("Autor: " + song.getAuthor());
            System.out.println();
            song.getStrofe().forEach(strofa -> {
                System.out.println(strofa);
                System.out.println();
            });
        }
    }
}
