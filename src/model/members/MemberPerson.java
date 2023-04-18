package model.members;

import config.ApplicationContext;
import model.ChurchCentre;

import java.util.ArrayList;
import java.util.List;

public class MemberPerson extends Person {

    private ChurchCentre churchCentre;
    private SmallGroup smallGroup;
    private List<Ministry> ministryList = new ArrayList<>();

    public MemberPerson() {
    }

    public MemberPerson(SmallGroup smallGroup) {
        this.churchCentre = ApplicationContext.churchScheduleDao().churchCentre;
        this.smallGroup = smallGroup;
    }

    public SmallGroup getSmallGroup() {
        return smallGroup;
    }

    public void setSmallGroup(SmallGroup smallGroup) {
        this.smallGroup = smallGroup;
    }

    public List<Ministry> getMinistryList() {
        return ministryList;
    }

    public void setMinistryList(List<Ministry> ministryList) {
        this.ministryList = ministryList;
    }
}
