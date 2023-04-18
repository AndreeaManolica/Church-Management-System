package service;

import config.ApplicationContext;
import model.members.MemberPerson;
import model.members.Ministry;
import model.members.Person;
import service.messages.GeneralMessageService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MemberServiceImpl implements MemberService {

    private List<Person> team;
    private final Scanner scanner = new Scanner(System.in);
    private final GeneralMessageService generalMessageService;
    private final MemberViewService memberTeamViewService;
    private final MinistryService ministryService;
    private List<Ministry> ministryList;

    public MemberServiceImpl() {
        this.team = ApplicationContext.churchScheduleDao().churchCentre.getPersonList();
        this.generalMessageService = ApplicationContext.generalMessageService();
        this.memberTeamViewService = ApplicationContext.memberViewService();
        this.ministryService = ApplicationContext.ministryService();
        this.ministryList = ministryService.getAllMinistries();
    }

    @Override
    public List<MemberPerson> getMembers() {
        return findTeam();
    }


    @Override
    public MemberPerson addNewMember() {
        try {
            MemberPerson memberPerson = new MemberPerson();
            addPersonalData(memberPerson);
            addMinistry(memberPerson);
            team.add(memberPerson);
            return memberPerson;
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.out.println("Te rugam sa pui un numar, nu litere. Incearca din nou");
            } else {
                System.out.println("A aparut o eroare. Te rugam sa incerci din nou");
            }
        }
        return null;
    }

    private void addMinistry(MemberPerson memberPerson) {
        boolean isRunning = true;
        while (isRunning) {
            List<Ministry> selectedMinistries = ministryService.getMultipleMinistries(this.ministryList);
            if (!selectedMinistries.isEmpty()) {
                isRunning = false;
                memberPerson.setMinistryList(selectedMinistries);
            } else {
                generalMessageService.generalErrorMessage();
            }
        }
    }


    @Override
    public MemberPerson editMember() {
        try {
            MemberPerson memberPerson = selectPerson("Selecteaza ce persoana sa editezi");
            if (memberPerson != null) {
                boolean isRunning = true;
                while (isRunning) {
                    editMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            addPersonalData(memberPerson);
                            isRunning = false;
                            break;
                        case "2":
                            addMinistry(memberPerson);
                            isRunning = false;
                            break;
                        default:
                            generalMessageService.generalErrorMessage();
                    }
                }
                return memberPerson;
            }
        } catch (Exception e) {
            System.out.println("A aparut o eroare. Te rugam sa incerci din nou");
        }

        return null;
    }

    @Override
    public void deleteMember() {
        try {
            MemberPerson memberPerson = selectPerson("Selecteaza ce persoana vrei sa stergi");
            if (memberPerson != null) {
                System.out.println("Esti sigur ca vrei sa stergi membru " + memberPerson.getFullName() + " ?");
                if (generalMessageService.generalQuestionYesOrNoMessage()) {
                    team.remove(memberPerson);
                    System.out.println(memberPerson.getFullName() + " a fost sters cu succes.");
                }
            }
        } catch (Exception e) {
            System.out.println("A aparut o eroare te rugam sa incerci din nou.");
        }
    }

    private void addPersonalData(Person person) {
        System.out.println("Scrie numele complet te rog:");
        person.setFullName(scanner.nextLine());
        System.out.println("Scrie varsta te rog:");
        person.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.println("Scrie adresa de mail te rog:");
        person.setEmail(scanner.nextLine());
        addGender(person);
    }

    private void addGender(Person person) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Alege te rog genul. (Credem doar in 2 genuri )");
            System.out.println("1.Barbat");
            System.out.println("2.Femeie");
            switch (scanner.nextLine()) {
                case "1":
                    person.setMale(true);
                    isRunning = false;
                    break;
                case "2":
                    person.setMale(false);
                    isRunning = false;
                    break;
                default:
                    generalMessageService.generalErrorMessage();
            }
        }
    }


    protected MemberPerson selectPerson(String action) {
        memberTeamViewService.showTeam(findTeam());
        if (team != null && !team.isEmpty()) {
            System.out.println(action);
            return getMembers().get(Integer.parseInt(scanner.nextLine()) - 1);
        }
        return null;
    }

    private void editMenu() {
        System.out.println("Ce sectiune vrei sa schimbi?");
        System.out.println("1.Date personale");
        System.out.println("2.Slujirea");
    }

    @Override
    public MemberPerson findMemberByName() {
        System.out.println("Cauta dupa nume:");
        String name = scanner.nextLine().toLowerCase();
        Optional<MemberPerson> memberPersonOptional = findTeam()
                .stream()
                .filter(memberPerson -> memberPerson.getFullName().toLowerCase().contains(name))
                .findFirst();

        if (memberPersonOptional.isEmpty()) {
            System.out.println("Ne pare rau, dar nu am gasit membru care sa contina numele " + name + ".");
            return null;
        }
        return memberPersonOptional.get();
    }

    @Override
    public List<MemberPerson> findMembersByMinistry() {
        Ministry ministryFilter = ministryService.getOneMinistry(ministryList);
        if (ministryFilter != null) {
            return findTeam()
                    .stream()
                    .filter(memberPerson ->
                            memberPerson.getMinistryList()
                                    .stream()
                                    .anyMatch(ministry -> ministry.getName().equals(ministryFilter.getName())))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Ne pare rau, dar nu exista categoria respectiva.");
            return findTeam();
        }
    }


    public void setMinistryList(List<Ministry> ministryList) {
        this.ministryList = ministryList;
    }


    public List<MemberPerson> findTeam() {
        return ApplicationContext.churchScheduleDao().churchCentre.getPersonList()
                .stream()
                .filter(person -> person instanceof MemberPerson)
                .map(person -> (MemberPerson) person)
                .collect(Collectors.toList());
    }
}
