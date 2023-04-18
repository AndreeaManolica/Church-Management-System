package service;

import config.ApplicationContext;
import model.members.MemberPerson;
import model.members.Ministry;

import java.util.List;
import java.util.stream.Collectors;


public class WorshipTeamService extends MemberServiceImpl{

    private MinistryService ministryService;
    private List<Ministry> worshipMinistries;

    public WorshipTeamService() {
        this.ministryService = ApplicationContext.ministryService();
        this.worshipMinistries = ministryService.getWorshipMinistries();
        setMinistryList(worshipMinistries); // 3 slujiri
    }


    private boolean isFromWorshipTeam(MemberPerson memberPerson) {
        return memberPerson
                .getMinistryList()
                .stream()
                .anyMatch(ministry -> worshipMinistries.contains(ministry));
    }

    @Override
    public List<MemberPerson> findTeam() {
        return ApplicationContext.churchScheduleDao().churchCentre.getPersonList()
                .stream()
                .filter(person -> person instanceof MemberPerson)
                .map(person -> (MemberPerson) person)
                .filter(this::isFromWorshipTeam)
                .collect(Collectors.toList());
    }
}
