package controller;

import config.ApplicationContext;
import service.messages.GeneralMessageService;
import service.smallGroup.SmallGroupService;
import service.smallGroup.SmallGroupViewService;

import java.util.Scanner;

public class SmallGroupController {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private GeneralMessageService generalMessageService;
    private SmallGroupService smallGroupService;
    private SmallGroupViewService smallGroupViewService;

    public SmallGroupController() {
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.smallGroupService = ApplicationContext.smallGroupService();
        this.smallGroupViewService = ApplicationContext.smallGroupViewService();
    }

    public void smallGroupController() {
        isRunning = true;
        while (isRunning) {
            smallGroupMenu();
            switch (scanner.nextLine()) {
                case "1":
                    smallGroupViewService.showSmallGroups(smallGroupService.getAllGroups());
                    break;
                case "2":
                    smallGroupViewService.showSmallGroup(smallGroupService.addSmallGroup());
                    break;
                case "3":
                    smallGroupViewService.showSmallGroup(smallGroupService.editSmallGroup());
                    break;
                case "4":
                    smallGroupService.deleteSmallGroup();
                    break;
                case "5":
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }

    private void smallGroupMenu() {
        System.out.println("1.Afisare grupuri");
        System.out.println("2.Adauga grup nou");
        System.out.println("3.Editare grup");
        System.out.println("4.Sterge grup");
        System.out.println("5.Inapoi");
    }
}
