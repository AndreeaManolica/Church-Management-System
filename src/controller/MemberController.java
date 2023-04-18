package controller;

import config.ApplicationContext;
import service.MemberService;
import service.MemberViewService;
import service.messages.GeneralMessageService;

import java.util.Scanner;

public class MemberController {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private final GeneralMessageService generalMessageService;
    private final MemberService memberService;
    private final MemberViewService memberViewService;

    public MemberController() {
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.memberService = ApplicationContext.memberTeamService();
        this.memberViewService = ApplicationContext.memberViewService();
    }


    public void memberController() {
        isRunning = true;
        while (isRunning) {
            memberMenu();
            switch (scanner.nextLine()) {
                case "1":
                    memberViewService.showTeam(memberService.getMembers());
                    break;
                case "2":
                    memberViewService.showMember(memberService.addNewMember());
                    break;
                case "3":
                    memberViewService.showMember(memberService.editMember());
                    break;
                case "4":
                    memberViewService.showMember(memberService.findMemberByName());
                    break;
                case "5":
                    memberViewService.showTeam(memberService.findMembersByMinistry());
                    break;
                case "6":
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }

    private void memberMenu() {
        System.out.println("1.Afisare membrii");
        System.out.println("2.Adaugare un membru");
        System.out.println("3.Editare informatii membru");
        System.out.println("4.Cauta membru dupa nume");
        System.out.println("5.Cauta membru dupa slujire");
        System.out.println("6.Inapoi");
    }
}
