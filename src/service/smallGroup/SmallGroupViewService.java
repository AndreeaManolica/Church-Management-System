package service.smallGroup;

import model.members.SmallGroup;

import java.util.List;

public interface SmallGroupViewService {
    void showSmallGroups(List<SmallGroup> smallGroupList);
    void showSmallGroupsList(List<SmallGroup> smallGroupList);
    void showSmallGroup(SmallGroup smallGroup);
}
