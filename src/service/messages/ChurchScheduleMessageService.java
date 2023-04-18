package service.messages;

import model.schedule.ChurchSchedule;
import model.schedule.ChurchScheduleSection;

public interface ChurchScheduleMessageService extends GeneralMessageService{
    void addNewScheduleMessage();
    void welcomeSectionMessage();
    void worshipSectionMessage();
    void prayerSectionMessage();
    void sermonSectionMessage();
    void announcementSectionMessage();
    void editSectionScheduleSuccess(String title);
    void displayChurchSchedule(ChurchSchedule churchSchedule);
    void displayChurchScheduleSection(ChurchScheduleSection churchScheduleSection);
    void noScheduleMessage();
    void editScheduleSectionMessage(ChurchSchedule churchSchedule);
}
