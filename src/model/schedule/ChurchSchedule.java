package model.schedule;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChurchSchedule {


    private Map<String,ChurchScheduleSection> churchScheduleSectionMap = new LinkedHashMap<>();

    public ChurchSchedule() {
    }

    public void addNewChurchScheduleSection(ChurchScheduleSection churchScheduleSection) {
        this.churchScheduleSectionMap.put(churchScheduleSection.getTitle(),churchScheduleSection);
    }

    public Map<String, ChurchScheduleSection> getChurchScheduleSectionMap() {
        return churchScheduleSectionMap;
    }
}
