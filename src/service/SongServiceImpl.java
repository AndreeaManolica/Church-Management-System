package service;

import model.ChurchCentre;
import model.songs.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SongServiceImpl implements SongService{

    private List<Song> songs;
    private Scanner scanner = new Scanner(System.in);

    public SongServiceImpl() {
        this.songs = ChurchCentre.getInstance().getSongs();
    }

    @Override
    public List<Song> getSongs() {
        return this.songs;
    }

    @Override
    public Song getNewSong() {
        try {
            System.out.println("Te rog scrie titlul noii cantari:");
            String title = scanner.nextLine();
            System.out.println("Te rog scrie autorul:");
            String autor = scanner.nextLine();
            List<String> verses = addVerses();
            Song song = new Song(title, autor, verses);
            songs.add(song);
            return song;
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.out.println("Te rugam sa pui un numar la strofe, nu litere.");
            } else {
                System.out.println("A aparut o eroare. Te rugam sa incerci din nou");
            }
        }

        return null;
    }

    private List<String> addVerses() {
        List<String> verses = new ArrayList<>();
        System.out.println("Cate strofe are cantarea ta?");
        int numberOfVerses = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfVerses; i++) {
            System.out.println("Pune continutul strofei. Scrie gata atunci cand ai terminat");
            verses.add(getVerseDescription());
        }

        return verses;
    }

    @Override
    public void deleteSong() {
        try {
            if (songs != null && !songs.isEmpty()) {
                System.out.println("Selecteaza te rog ce cantare vrei sa stergi");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.println("Esti sigur ca vrei sa stergi cantarea: " + songs.get(index - 1).getTitle() + " ? Scrie da, daca vrei.");
                if (scanner.nextLine().equalsIgnoreCase("Da")) {
                    songs.remove(index - 1);
                    System.out.println("Cantarea a fost stearsa cu succes.");
                }
            }
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
                System.out.println("Acest element nu exista. Incearca din nou.");
            } else {
                System.out.println("A aparut o eroare, te rog sa incerci din nou.");
            }
        }

    }

    @Override
    public Song findSongByName() {
        if (songs != null && !songs.isEmpty()) {
            System.out.println("Scrie numele cantarii pe care vrei sa o cauti (Titlu)");
            String title = scanner.nextLine();
            Optional<Song> songOptional = songs
                    .stream()
                    .filter(song -> song.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .findFirst();
            if (songOptional.isEmpty()) {
                System.out.println("Imi pare rau, dar nu am gasit cantecul " + title + ". Incearca te rog un filtru mai corect :)");
            } else {
                return songOptional.get();
            }
        } else {
            System.out.println("Nu ai nici o cantare adaugata. Adauga una :)");
        }

        return null;
    }

    private String getVerseDescription() {
        String input = "";
        String worshipSongsDescription = "";

        while (!input.equalsIgnoreCase("gata")) {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("gata")) {
                worshipSongsDescription += input + "\n";
            }
        }

        return worshipSongsDescription;
    }
}
