package service.smallGroup;

import model.members.SmallGroup;
import service.MemberViewServiceImpl;

import java.util.List;

public class SmallGroupViewServiceImpl extends MemberViewServiceImpl implements SmallGroupViewService {


    @Override
    public void showSmallGroups(List<SmallGroup> smallGroupList) {
        if (!smallGroupList.isEmpty()) {
            smallGroupList.forEach(this::showSmallGroup);
        } else {
            System.out.println("Nu ai nici un grup mic in church center. Adauga unul te rog...:)");
        }
    }

    @Override
    public void showSmallGroup(SmallGroup smallGroup) {
        if (smallGroup != null) {
            System.out.printf("Grup de casa (%s)", smallGroup.getSmallGroupLeader().getFullName());
            System.out.println();
            System.out.println("========= Membri grup mic ===========");
            smallGroup.getMemberPersonMap().forEach((email, memberPerson) -> System.out.println(showMemberPerson(memberPerson)));
        }
    }

    @Override
    public void showSmallGroupsList(List<SmallGroup> smallGroupList) {
        if (!smallGroupList.isEmpty()) {
            for (int i = 0; i < smallGroupList.size(); i++) {
                System.out.println(i + 1 + ". Grup de casa (" + smallGroupList.get(i).getSmallGroupLeader().getFullName() + ")");
            }
        } else {
            System.out.println("Nu ai nici un grup mic in church center. Adauga unul te rog...:)");
        }
    }
}
