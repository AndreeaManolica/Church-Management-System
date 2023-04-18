package service;

import model.members.MemberPerson;

import java.util.List;

public interface MemberViewService {
    void showTeam(List<MemberPerson> personList);
    void showMember(MemberPerson memberPerson);
}
