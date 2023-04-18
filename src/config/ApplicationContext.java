package config;

import controller.*;
import dao.ChurchScheduleDao;
import service.*;
import service.messages.ChurchScheduleMessageService;
import service.messages.ChurchScheduleMessageServiceImpl;
import service.messages.GeneralMessageService;
import service.messages.GeneralMessageServiceImpl;
import service.smallGroup.SmallGroupService;
import service.smallGroup.SmallGroupServiceImpl;
import service.smallGroup.SmallGroupViewService;
import service.smallGroup.SmallGroupViewServiceImpl;

public class ApplicationContext {

    public static SongService songService() {
        return new SongServiceImpl();
    }

    public static SongViewService songViewService() {
        return new SongViewServiceImpl();
    }

    public static GeneralMessageService generalMessageService() {
        return new GeneralMessageServiceImpl();
    }

    public static ChurchScheduleMessageService churchScheduleMessageService() {
        return new ChurchScheduleMessageServiceImpl();
    }

    public static ChurchScheduleService churchScheduleService() {
        return new ChurchScheduleServiceImpl();
    }

    public static ChurchScheduleDao churchScheduleDao() {
        return new ChurchScheduleDao();
    }

    public static ChurchScheduleController churchScheduleController() {
        return new ChurchScheduleController();
    }

    public static WorshipController worshipController() {
        return new WorshipController();
    }

    public static SongController songController() {
        return new SongController();
    }

    public static MainController mainController() {
        return new MainController();
    }

    public static WorshipTeamController worshipTeamController() {
        return new WorshipTeamController();
    }

    public static MemberService memberTeamService() {
        return new MemberServiceImpl();
    }

    public static MemberViewService memberViewService() {
        return new MemberViewServiceImpl();
    }

    public static WorshipTeamService worshipTeamService() {
        return new WorshipTeamService();
    }

    public static MemberController memberController() {
        return new MemberController();
    }

    public static MinistryService ministryService() {
        return new MinistryServiceImpl();
    }

    public static SmallGroupService smallGroupService() {
        return new SmallGroupServiceImpl();
    }

    public static SmallGroupController smallGroupController() {
        return new SmallGroupController();
    }

    public static SmallGroupViewService smallGroupViewService() {
        return new SmallGroupViewServiceImpl();
    }
}
