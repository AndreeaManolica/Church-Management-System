package service.messages;

import model.schedule.ChurchSchedule;
import model.schedule.ChurchScheduleSection;

import java.util.Map;

public class ChurchScheduleMessageServiceImpl extends GeneralMessageServiceImpl implements ChurchScheduleMessageService {


    @Override
    public void addNewScheduleMessage() {
        System.out.println("Imediat adaugam programul tau. Raspunde te rog la urmatoarele intrebari.");
    }

    @Override
    public void welcomeSectionMessage() {
        System.out.println("Cine o sa fie responsabil de welcome?");
    }

    @Override
    public void worshipSectionMessage() {
        System.out.println("Te rog adauga titlul cantarilor care vrei sa fie. Scrie textul gata cand ai terminat de adaugat.");
    }

    @Override
    public void prayerSectionMessage() {
        System.out.println("Cine o sa fie responsabil cu rugaciunea (indemn la rugaciune)?");
    }

    @Override
    public void sermonSectionMessage() {
        System.out.println("Cine o sa fie responsabil cu cuvantul?");
    }

    @Override
    public void announcementSectionMessage() {
        System.out.println("Cine o sa fie responsabil de anunturi?");
    }

    @Override
    public void editSectionScheduleSuccess(String title) {
        System.out.println("S-a modificat sectiunea " + title + " cu succes din programul tau.\n");
    }

    @Override
    public void displayChurchSchedule(ChurchSchedule churchSchedule) {
        System.out.println("\nSERVICIU VINERI\n");

        churchSchedule.getChurchScheduleSectionMap().forEach((key, value) -> {
            displayChurchScheduleSection(value);
        });
        System.out.println();

    }

    @Override
    public void displayChurchScheduleSection(ChurchScheduleSection churchScheduleSection) {
        System.out.println(churchScheduleSection.getDisplayTitle());
        System.out.println(churchScheduleSection.getDescription());
        System.out.println();
    }

    @Override
    public void noScheduleMessage() {
        System.out.println("Imi pare rau, nu este nici un program adaugat. Doresti sa adaugi unul?");
    }

    @Override
    public void editScheduleSectionMessage(ChurchSchedule churchSchedule) {
        System.out.println("Ce sectiune vrei sa modifici?");

        int counter = 1;

        for (Map.Entry<String, ChurchScheduleSection> churchScheduleSectionEntry : churchSchedule.getChurchScheduleSectionMap().entrySet()) {
            System.out.println(counter + "." + churchScheduleSectionEntry.getKey());
            counter++;
        }

        System.out.println("6.Afiseaza programul");
        System.out.println("7.Inapoi");
    }

}
