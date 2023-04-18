package service;

import model.members.MemberPerson;

import java.util.List;

public interface MemberService {
    List<MemberPerson> getMembers();
    MemberPerson addNewMember();
    MemberPerson editMember();
    void deleteMember();
    MemberPerson findMemberByName();
    List<MemberPerson> findMembersByMinistry();
}
