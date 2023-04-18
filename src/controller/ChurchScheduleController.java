package controller;

import config.ApplicationContext;
import model.schedule.ChurchSchedule;
import service.ChurchScheduleService;
import service.messages.ChurchScheduleMessageService;

import java.util.Scanner;

public class ChurchScheduleController {

    public ChurchScheduleService churchScheduleService;
    public ChurchScheduleMessageService churchScheduleMessageService;
    public boolean isRunning = true;

    public ChurchScheduleController() {
        this.churchScheduleService = ApplicationContext.churchScheduleService();
        this.churchScheduleMessageService = ApplicationContext.churchScheduleMessageService();
    }

    public void scheduleController() {
        this.isRunning = true;
        Scanner scanner = new Scanner(System.in);
        ChurchSchedule churchSchedule = churchScheduleService.getChurchSchedule();

        if (churchSchedule != null) {
            churchScheduleMessageService.displayChurchSchedule(churchSchedule);
        }

        while (this.isRunning) {
            if (churchSchedule != null) {
                System.out.println("Ce vrei sa faci acum?");
                System.out.println("1.Sterge programul");
                System.out.println("2.Modifica programul");
                System.out.println("3.Inapoi");

                switch (scanner.nextLine()) {
                    case "1":
                        churchScheduleService.deleteChurchSchedule();
                        System.out.println("Programul de biserica a fost sters cu succes.");
                        this.isRunning = false;
                        break;
                    case "2":
                        churchSchedule = churchScheduleService.editChurchSchedule(churchSchedule);
                        break;
                    case "3":
                        this.isRunning = false;
                        break;
                    default:
                        churchScheduleMessageService.generalErrorMessage();
                }


            } else {
                churchScheduleMessageService.noScheduleMessage();

                if (churchScheduleMessageService.generalQuestionYesOrNoMessage()) {
                    churchSchedule = churchScheduleService.addNewChurchSchedule();
                    churchScheduleMessageService.displayChurchSchedule(churchSchedule);
                } else {
                    this.isRunning = false;
                }
            }
        }


    }
}
