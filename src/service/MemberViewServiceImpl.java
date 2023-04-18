package service;

import model.members.MemberPerson;
import model.members.Ministry;

import java.util.List;
import java.util.stream.Collectors;

public class MemberViewServiceImpl implements MemberViewService {
    @Override
    public void showTeam(List<MemberPerson> personList) {
        System.out.println();
        if (personList != null && !personList.isEmpty()) {
            for (int i = 0; i < personList.size(); i++) {
                MemberPerson person = personList.get(i);
                System.out.println(i + 1 + "." + showMemberPerson(person));
            }
        } else {
            System.out.println("Ne pare rau, dar nu ai persoane in church center. Adauga persoane te rog :)");
        }
        System.out.println();
    }

    @Override
    public void showMember(MemberPerson memberPerson) {
        if (memberPerson != null) {
            System.out.println("Nume: " + memberPerson.getFullName());
            System.out.println("Email: " + memberPerson.getEmail());
            System.out.println("Varsta: " + memberPerson.getAge());
            System.out.println("Gen: " + (memberPerson.isMale() ? "Barbat" : "Femeie"));
            System.out.println("Slujire: " + getDisplayMinistriesForMemberPerson(memberPerson));
        }
    }

    protected String showMemberPerson(MemberPerson memberPerson) {
        return memberPerson.getFullName() + " - " + memberPerson.getEmail() + "(" +  getDisplayMinistriesForMemberPerson(memberPerson) + ")";
    }

    // [Ministry@3124, Ministry@231214] -> ["Lauda si inchinare", "Instrumentisti"] -> "Lauda si inchinare, Instrumentisti";
    private String getDisplayMinistriesForMemberPerson(MemberPerson memberPerson) {
        return memberPerson
                .getMinistryList()
                .stream()
                .map(Ministry::getName)
                .collect(Collectors.joining(","));
    }
}
