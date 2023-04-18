package dao;

import model.ChurchCentre;
import model.schedule.ChurchSchedule;
import model.schedule.ChurchScheduleSection;

public class ChurchScheduleDao {

    public ChurchCentre churchCentre;

    public ChurchScheduleDao() {
        this.churchCentre = ChurchCentre.getInstance();
    }

    public ChurchSchedule getChurchSchedule() {
        return churchCentre.getChurchSchedule();
    }

    public void addNewChurchScheduleSection(ChurchScheduleSection churchScheduleSection) {
        if (churchCentre.getChurchSchedule() == null) {
            churchCentre.setChurchSchedule(new ChurchSchedule());
        }

        this.churchCentre.getChurchSchedule().addNewChurchScheduleSection(churchScheduleSection);
    }

    public void deleteChurchSchedule() {
        this.churchCentre.setChurchSchedule(null);
    }


    public void editChurchScheduleSection(ChurchScheduleSection churchScheduleSection) {
        ChurchSchedule churchSchedule = churchCentre.getChurchSchedule();

        churchSchedule.getChurchScheduleSectionMap().put(churchScheduleSection.getTitle(), churchScheduleSection);
    }

}
