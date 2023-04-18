package service;

import model.schedule.ChurchSchedule;

public interface ChurchScheduleService {
    ChurchSchedule getChurchSchedule();
    ChurchSchedule addNewChurchSchedule();
    ChurchSchedule editChurchSchedule(ChurchSchedule churchSchedule);
    void deleteChurchSchedule();
}
