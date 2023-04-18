package controller;

import config.ApplicationContext;
import service.SongService;
import service.SongViewService;
import service.messages.GeneralMessageService;

import java.util.Scanner;

public class SongController {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private GeneralMessageService generalMessageService;
    private SongService songService;
    private SongViewService songViewService;

    public SongController() {
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.songService = ApplicationContext.songService();
        this.songViewService = ApplicationContext.songViewService();
    }

    public void songController() {
        isRunning = true;
        while (isRunning) {
            songMenu();
            switch (scanner.nextLine()) {
                case "1":
                    songViewService.showSongs(songService.getSongs());
                    break;
                case "2":
                    songViewService.showSong(songService.getNewSong());
                    break;
                case "3":
                    songViewService.showSongs(songService.getSongs());
                    songService.deleteSong();
                    break;
                case "4":
                    songViewService.showSong(songService.findSongByName());
                    break;
                case "5":
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }

    private void songMenu() {
        System.out.println("1.Lista cantari");
        System.out.println("2.Adauga o cantare");
        System.out.println("3.Stergem o cantare");
        System.out.println("4.Cauta cantare dupa nume");
        System.out.println("5.Inapoi");
    }
}
