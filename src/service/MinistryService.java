package service;

import model.members.Ministry;

import java.util.List;

public interface MinistryService {

    List<Ministry> getAllMinistries();
    List<Ministry> getMultipleMinistries(List<Ministry> ministryList);
    Ministry getOneMinistry(List<Ministry> ministryList);
    List<Ministry> getWorshipMinistries();
}
