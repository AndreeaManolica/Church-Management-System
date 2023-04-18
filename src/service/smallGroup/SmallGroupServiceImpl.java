package service.smallGroup;

import config.ApplicationContext;
import model.members.MemberPerson;
import model.members.SmallGroup;
import service.MemberServiceImpl;
import service.MemberViewService;
import service.messages.GeneralMessageService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SmallGroupServiceImpl extends MemberServiceImpl implements SmallGroupService{

    private List<SmallGroup> smallGroupList;
    private MemberViewService memberViewService;
    private SmallGroupViewService smallGroupViewService;
    private GeneralMessageService generalMessageService;
    private Scanner scanner = new Scanner(System.in);

    public SmallGroupServiceImpl() {
        this.smallGroupList = findSmallGroups();
        this.memberViewService = ApplicationContext.memberViewService();
        this.smallGroupViewService = ApplicationContext.smallGroupViewService();
        this.generalMessageService = ApplicationContext.generalMessageService();
    }

    @Override
    public List<SmallGroup> getAllGroups() {
        return smallGroupList;
    }

    @Override
    public SmallGroup addSmallGroup() {
        // nu putem adauga grup mic daca nu avem membrii in biserica
        if (!super.findTeam().isEmpty()) {
            SmallGroup smallGroup = new SmallGroup();
            // 1) Sa adaugam un lider de grup mic
            addSmallGroupLeader(smallGroup);
            // 2) Sa adaugam membrii (Selectie multipla)
            addMembersToSmallGroup(smallGroup);
            smallGroupList.add(smallGroup);
            return smallGroup;
        } else {
            System.out.println("Imi pare rau, dar nu exista membri in biserica. Ai nevoie de minim un membru pentru a avea grup mic. Adauga te rog unul la " +
                    "sectiunea de membrii");
        }

        return null;
    }

    private void addSmallGroupLeader(SmallGroup smallGroup) {
        // selectam din lista de membrii , un lider
        MemberPerson smallGroupLeader = super.selectPerson("Selecteaza liderul de grup mic");
        if (smallGroupLeader != null) {
            // sa adaugam in obiectul nostru, liderul de grup mic
            smallGroup.setSmallGroupLeader(smallGroupLeader);
            // stergem membru din colectia de membrii daca el este lider de grup mic
            smallGroup.getMemberPersonMap().remove(smallGroupLeader.getFullName());
        }
    }

    private void addMembersToSmallGroup(SmallGroup smallGroup) {

        Map<String, MemberPerson> memberPersonMap = smallGroup.getMemberPersonMap();

        System.out.println("Selecteaza membrii care vrei sa fie in grup mic: (Ex: 1,2,4)");
        // afisam membrii din biserica noastra
        memberViewService.showTeam(super.findTeam());
        String selectedInput = scanner.nextLine();
        String[] data = selectedInput.split(",");
        if (data.length > 0) {
            for (String indexString : data) {
                int index = Integer.parseInt(indexString) - 1;
                MemberPerson memberPerson = super.findTeam().get(index);
                if (memberPersonMap.containsKey(memberPerson.getEmail())) {
                    System.out.println("Membru " + memberPerson.getFullName() + " deja exista in grupul tau mic. Nu a fost adaugat");
                } else {
                    memberPersonMap.put(memberPerson.getEmail(), memberPerson);
                    System.out.println("Membru " + memberPerson.getFullName() + " a fost adaugat cu succes.");
                }
            }
        }
    }

    @Override
    public SmallGroup editSmallGroup() {
        try {
            SmallGroup smallGroup = selectGroup("Selecteaza ce grup vrei sa editezi");
            if (smallGroup != null) {
                boolean isRunning = true;
                while (isRunning) {
                    editMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            addSmallGroupLeader(smallGroup);
                            isRunning = false;
                            break;
                        case "2":
                            addMembersToSmallGroup(smallGroup);
                            isRunning = false;
                            break;
                        default:
                            generalMessageService.generalErrorMessage();
                    }
                }
                return smallGroup;
            }
        } catch (Exception e) {
            System.out.println("A aparut o eroare. Te rugam sa incerci din nou");
        }

        return null;
    }

    protected SmallGroup selectGroup(String action) {
        smallGroupViewService.showSmallGroupsList(findSmallGroups());
        if (smallGroupList != null && !smallGroupList.isEmpty()) {
            System.out.println(action);
            return getAllGroups().get(Integer.parseInt(scanner.nextLine()) - 1);
        }
        return null;
    }

    private void editMenu() {
        System.out.println("Ce sectiune vrei sa schimbi?");
        System.out.println("1.Lider grup mic");
        System.out.println("2.Membrii grup mic");
    }


    @Override
    public void deleteSmallGroup() {
        try {
            SmallGroup smallGroup = selectGroup("Selecteaza ce grup vrei sa stergi");
            if (smallGroup != null) {
                System.out.println("Esti sigur ca vrei sa stergi grupul de casa ( " + smallGroup.getSmallGroupLeader().getFullName() + ") ?");
                if (generalMessageService.generalQuestionYesOrNoMessage()) {
                    smallGroupList.remove(smallGroup);
                    System.out.println("Grupul de casa ("  + smallGroup.getSmallGroupLeader().getFullName() + ") a fost sters cu succes.");
                }
            }
        } catch (Exception e) {
            System.out.println("A aparut o eroare te rugam sa incerci din nou.");
        }
    }

    private List<SmallGroup> findSmallGroups() {
        return ApplicationContext.churchScheduleDao().churchCentre.getSmallGroupList();
    }
}
