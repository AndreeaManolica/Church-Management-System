package model;

import model.members.Ministry;
import model.members.Person;
import model.members.SmallGroup;
import model.schedule.ChurchSchedule;
import model.songs.Song;
import utils.MinistryConstants;

import java.util.ArrayList;
import java.util.List;

public class ChurchCentre {

    private ChurchSchedule churchSchedule;
    private List<SmallGroup> smallGroupList = new ArrayList<>();
    private List<Person> personList = new ArrayList<>();
    private List<Ministry> ministryList = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();

    private ChurchCentre() {
        loadData();
    }

    private void loadData() {
        ministryList.add(new Ministry(MinistryConstants.PREDICARE));
        ministryList.add(new Ministry(MinistryConstants.MIJLOCIRE));
        ministryList.add(new Ministry(MinistryConstants.ASIMILARE));
        ministryList.add(new Ministry(MinistryConstants.WORSHIP_LEADER));
        ministryList.add(new Ministry(MinistryConstants.INSTRUMENTISTI));
        ministryList.add(new Ministry(MinistryConstants.VOCALISTI));
        ministryList.add(new Ministry(MinistryConstants.LUCRAREA_CU_COPII));
        ministryList.add(new Ministry(MinistryConstants.LIDER_GRUP_MIC));
    }

    private static class Singleton {
        private static final ChurchCentre instance = new ChurchCentre();
    }

    public static ChurchCentre getInstance() {
        return Singleton.instance;
    }

    public ChurchSchedule getChurchSchedule() {
        return churchSchedule;
    }

    public void setChurchSchedule(ChurchSchedule churchSchedule) {
        this.churchSchedule = churchSchedule;
    }

    public List<SmallGroup> getSmallGroupList() {
        return smallGroupList;
    }

    public void setSmallGroupList(List<SmallGroup> smallGroupList) {
        this.smallGroupList = smallGroupList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Ministry> getMinistryList() {
        return ministryList;
    }
}
