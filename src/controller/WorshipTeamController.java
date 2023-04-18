package controller;

import config.ApplicationContext;
import service.MemberViewService;
import service.WorshipTeamService;
import service.messages.GeneralMessageService;

import java.util.Scanner;

public class WorshipTeamController {


    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private final GeneralMessageService generalMessageService;
    private final WorshipTeamService worshipTeamService;
    private final MemberViewService worshipTeamViewService;

    public WorshipTeamController() {
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.worshipTeamService = ApplicationContext.worshipTeamService();
        this.worshipTeamViewService = ApplicationContext.memberViewService();
    }

    public void worshipTeamController() {
        isRunning = true;
        while (isRunning) {
            worshipTeamMenu();
            switch (scanner.nextLine()) {
                case "1":
                    worshipTeamViewService.showTeam(worshipTeamService.getMembers());
                    break;
                case "2":
                    worshipTeamViewService.showMember(worshipTeamService.addNewMember());
                    break;
                case "3":
                    worshipTeamViewService.showMember(worshipTeamService.editMember());
                    break;
                case "4":
                    worshipTeamService.deleteMember();
                    break;
                case "5":
                    worshipTeamViewService.showMember(worshipTeamService.findMemberByName());
                    break;
                case "6":
                    worshipTeamViewService.showTeam(worshipTeamService.findMembersByMinistry());
                    break;
                case "7":
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }

    private void worshipTeamMenu() {
        System.out.println("1.Lista membrii inchinare");
        System.out.println("2.Adauga un membru");
        System.out.println("3.Editeaza un membru");
        System.out.println("4.Sterge un membru");
        System.out.println("5.Cauta membru dupa nume");
        System.out.println("6.Cauta membru dupa slujire");
        System.out.println("7.Inapoi");
    }
}
