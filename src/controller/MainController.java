package controller;

import config.ApplicationContext;

import java.util.Scanner;

public class MainController {


    boolean start = true;

    private ChurchScheduleController churchScheduleController;
    private WorshipController worshipController;
    private MemberController memberController;
    private SmallGroupController smallGroupController;

    public MainController() {
        this.churchScheduleController = ApplicationContext.churchScheduleController();
        this.worshipController = ApplicationContext.worshipController();
        this.memberController = ApplicationContext.memberController();
        this.smallGroupController = ApplicationContext.smallGroupController();
    }

    public void mainController() {


        Scanner scanner = new Scanner(System.in);

        while (this.start) {
            startMenu();
            System.out.println("Bla bla");
            parseInput(scanner.nextLine());

        }
    }

    public void parseInput(String input) {

        switch (input) {
            case "1":
                // Afiseaza programul de biseric
                churchScheduleController.scheduleController();
                break;
            case "2":
                // Inchinarea
                worshipController.worshipController();
                break;
            case "3":
                // Mesaje
                System.out.println("\nNu sunt disponibile acum");
                // titlu
                // descriere
                // autor -> selectezi din colectia de membrii, toti membrii care au slujirea predicare
                // continut
                break;
            case "4":
                // membrii
                memberController.memberController();
                break;
            case "5":
                // grupuri mici
                smallGroupController.smallGroupController();
                break;
            case "6":
                this.start = false;
                break;
            default:
                System.out.println("\nNe pare rau. Selecteaza una din optiunile de mai jos...");
        }
    }

    public void startMenu() {
        System.out.println("\nCe doresti sa faci?");
        System.out.println("1.Programul de biserica (Duminica)");
        System.out.println("2.Inchinare");
        System.out.println("3.Mesaje");
        System.out.println("4.Membrii bisericii");
        System.out.println("5.Grupuri mici");
        System.out.println("6.Iesi din platforma");
    }

    public void greetingMessage(String churchName) {
        System.out.println("Bun venit la Platforma ta de Administrare a bisericii " + churchName + ".");
    }

    public void farewellMessage(String churchName) {
        System.out.println("Multumesc ca ai vizitat platforma de administrare a bisericii: " + churchName + ".");
    }
}
