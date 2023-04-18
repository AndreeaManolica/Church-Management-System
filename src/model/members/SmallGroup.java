package model.members;

import config.ApplicationContext;
import model.ChurchCentre;
import utils.MinistryConstants;

import java.util.HashMap;
import java.util.Map;

public class SmallGroup {
    private ChurchCentre churchCentre;
    private Map<String, MemberPerson> memberPersonMap = new HashMap<>();
    private MemberPerson smallGroupLeader;

    public SmallGroup() {
        this.churchCentre = ApplicationContext.churchScheduleDao().churchCentre;
    }

    public ChurchCentre getChurchCentre() {
        return churchCentre;
    }

    public Map<String, MemberPerson> getMemberPersonMap() {
        return memberPersonMap;
    }

    public void setMemberPersonMap(Map<String, MemberPerson> memberPersonMap) {
        this.memberPersonMap = memberPersonMap;
    }

    public MemberPerson getSmallGroupLeader() {
        return smallGroupLeader;
    }

    public void setSmallGroupLeader(MemberPerson smallGroupLeader) {
        if (!hasGroupLeaderMinistry(smallGroupLeader)) {
            smallGroupLeader.getMinistryList().add(new Ministry(MinistryConstants.LIDER_GRUP_MIC));
        }
        this.smallGroupLeader = smallGroupLeader;
    }

    private boolean hasGroupLeaderMinistry(MemberPerson memberPerson) {
        return memberPerson.getMinistryList()
                .stream()
                .anyMatch(ministry -> ministry.getName().equals(MinistryConstants.LIDER_GRUP_MIC));
    }
}
