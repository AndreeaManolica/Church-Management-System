package controller;

import config.ApplicationContext;
import service.messages.GeneralMessageService;

import java.util.Scanner;

public class WorshipController {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private GeneralMessageService generalMessageService;
    private SongController songController;
    private WorshipTeamController worshipTeamController;

    public WorshipController() {
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.songController = ApplicationContext.songController();
        this.worshipTeamController = ApplicationContext.worshipTeamController();
    }


    public void worshipController() {
        isRunning = true;
        while (isRunning) {
            worshipMenu();
            switch (scanner.nextLine()) {
                case "1":
                    songController.songController();
                    break;
                case "2":
                    worshipTeamController.worshipTeamController();
                    break;
                case "3":
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }

    private void worshipMenu() {
        System.out.println("1.Sectiunea cantari");
        System.out.println("2.Echipa de inchinare");
        System.out.println("3.Inapoi");
    }
}
