package model.schedule;

import utils.ChurchScheduleConstants;

public class ChurchScheduleSection {

    private String title;
    private String description;
    private final String displayTitle;

    public ChurchScheduleSection(String title, String description) {
        this.title = title;
        this.description = description;
        this.displayTitle = getEmojiBasedOnTitle() + " " + title + " " + getEmojiBasedOnTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    private String getEmojiBasedOnTitle() {
        switch (title) {
            case ChurchScheduleConstants.WELCOME: return "\uD83D\uDD1B";
            case ChurchScheduleConstants.PRAYER: return "\uD83D\uDCE2";
            case ChurchScheduleConstants.WORSHIP: return "\uD83D\uDE4F";
            case ChurchScheduleConstants.WORD_OF_GOD: return "\uD83D\uDCD3";
            case ChurchScheduleConstants.ANNOUNCEMENT: return "\uD83C\uDFBA";
            default: return "";
        }
    }


}
