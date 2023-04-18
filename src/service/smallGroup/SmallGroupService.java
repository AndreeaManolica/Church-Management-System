package service.smallGroup;

import model.members.SmallGroup;

import java.util.List;

public interface SmallGroupService {
    List<SmallGroup> getAllGroups();
    SmallGroup addSmallGroup();
    SmallGroup editSmallGroup();
    void deleteSmallGroup();
}
