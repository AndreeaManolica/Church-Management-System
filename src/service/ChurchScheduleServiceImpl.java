package service;

import config.ApplicationContext;
import dao.ChurchScheduleDao;
import model.schedule.ChurchSchedule;
import model.schedule.ChurchScheduleSection;
import service.messages.ChurchScheduleMessageService;
import utils.ChurchScheduleConstants;

import java.util.Scanner;

public class ChurchScheduleServiceImpl implements ChurchScheduleService{

    public ChurchScheduleDao churchScheduleDao;
    public ChurchScheduleMessageService churchScheduleMessageService;

    public ChurchScheduleServiceImpl() {
        this.churchScheduleDao = ApplicationContext.churchScheduleDao();
        this.churchScheduleMessageService = ApplicationContext.churchScheduleMessageService();
    }

    // get current schedule
    @Override
    public ChurchSchedule getChurchSchedule() {
        return churchScheduleDao.getChurchSchedule();
    }

    // add new schedule
    @Override
    public ChurchSchedule addNewChurchSchedule() {
        Scanner scanner = new Scanner(System.in);

        churchScheduleMessageService.addNewScheduleMessage();

        // welcome section
        churchScheduleMessageService.welcomeSectionMessage();
        ChurchScheduleSection welcomeSection = new ChurchScheduleSection(ChurchScheduleConstants.WELCOME, scanner.nextLine());
        churchScheduleDao.addNewChurchScheduleSection(welcomeSection);

        // worship section
        churchScheduleMessageService.worshipSectionMessage();
        ChurchScheduleSection worshipSection = new ChurchScheduleSection(ChurchScheduleConstants.WORSHIP, getWorshipSectionDescription(scanner));
        churchScheduleDao.addNewChurchScheduleSection(worshipSection);

        // prayer section
        churchScheduleMessageService.prayerSectionMessage();
        ChurchScheduleSection prayerSection = new ChurchScheduleSection(ChurchScheduleConstants.PRAYER, scanner.nextLine());
        churchScheduleDao.addNewChurchScheduleSection(prayerSection);

        // sermon section
        churchScheduleMessageService.sermonSectionMessage();
        ChurchScheduleSection sermonSection = new ChurchScheduleSection(ChurchScheduleConstants.WORD_OF_GOD, scanner.nextLine());
        churchScheduleDao.addNewChurchScheduleSection(sermonSection);

        // announcement section
        churchScheduleMessageService.announcementSectionMessage();
        ChurchScheduleSection announcementSection = new ChurchScheduleSection(ChurchScheduleConstants.ANNOUNCEMENT, scanner.nextLine());
        churchScheduleDao.addNewChurchScheduleSection(announcementSection);

        return churchScheduleDao.getChurchSchedule();

    }

    @Override
    public ChurchSchedule editChurchSchedule(ChurchSchedule churchSchedule) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            churchScheduleMessageService.editScheduleSectionMessage(churchSchedule);

            switch (scanner.nextLine()) {
                case "1":
                    churchScheduleMessageService.welcomeSectionMessage();
                    ChurchScheduleSection welcomeSection = new ChurchScheduleSection(ChurchScheduleConstants.WELCOME, scanner.nextLine());
                    churchScheduleDao.editChurchScheduleSection(welcomeSection);
                    churchScheduleMessageService.editSectionScheduleSuccess(welcomeSection.getTitle());
                    churchScheduleMessageService.displayChurchScheduleSection(welcomeSection);
                    break;
                case "2":
                    churchScheduleMessageService.worshipSectionMessage();
                    ChurchScheduleSection worshipSection = new ChurchScheduleSection(ChurchScheduleConstants.WORSHIP, getWorshipSectionDescription(scanner));
                    churchScheduleDao.editChurchScheduleSection(worshipSection);
                    churchScheduleMessageService.editSectionScheduleSuccess(worshipSection.getTitle());
                    churchScheduleMessageService.displayChurchScheduleSection(worshipSection);
                    break;
                case "3":
                    churchScheduleMessageService.prayerSectionMessage();
                    ChurchScheduleSection prayerSection = new ChurchScheduleSection(ChurchScheduleConstants.PRAYER, scanner.nextLine());
                    churchScheduleDao.editChurchScheduleSection(prayerSection);
                    churchScheduleMessageService.editSectionScheduleSuccess(prayerSection.getTitle());
                    churchScheduleMessageService.displayChurchScheduleSection(prayerSection);
                    break;
                case "4":
                    churchScheduleMessageService.sermonSectionMessage();
                    ChurchScheduleSection sermonSection = new ChurchScheduleSection(ChurchScheduleConstants.WORD_OF_GOD, scanner.nextLine());
                    churchScheduleDao.editChurchScheduleSection(sermonSection);
                    churchScheduleMessageService.editSectionScheduleSuccess(sermonSection.getTitle());
                    churchScheduleMessageService.displayChurchScheduleSection(sermonSection);
                    break;
                case "5":
                    churchScheduleMessageService.announcementSectionMessage();
                    ChurchScheduleSection announcementSection = new ChurchScheduleSection(ChurchScheduleConstants.ANNOUNCEMENT, scanner.nextLine());
                    churchScheduleDao.editChurchScheduleSection(announcementSection);
                    churchScheduleMessageService.editSectionScheduleSuccess(announcementSection.getTitle());
                    churchScheduleMessageService.displayChurchScheduleSection(announcementSection);
                    break;
                case "6":
                    churchScheduleMessageService.displayChurchSchedule(churchSchedule);
                case "7":
                    isRunning = false;
                    break;
                default:
                    churchScheduleMessageService.generalErrorMessage();
            }
        }

        return churchSchedule;
    }


    @Override
    public void deleteChurchSchedule() {
        churchScheduleDao.deleteChurchSchedule();
    }

    private String getWorshipSectionDescription(Scanner scanner) {
        String input = "";
        String worshipSongsDescription = "";

        for (int i = 1; !input.equalsIgnoreCase("gata"); i++) {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("gata")) {
                worshipSongsDescription += i + "." + input + "\n";
            }
        }

        return worshipSongsDescription;
    }
}
