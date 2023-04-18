package service;

import config.ApplicationContext;
import model.members.Ministry;
import utils.MinistryConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MinistryServiceImpl implements MinistryService{

    private final Scanner scanner = new Scanner(System.in);
    private List<Ministry> ministryList;

    public MinistryServiceImpl() {
        this.ministryList = ApplicationContext.churchScheduleDao().churchCentre.getMinistryList();
    }

    private void showMinistries(List<Ministry> ministries) {
        for (int i = 0; i < ministries.size(); i++) {
            System.out.println(i + 1 + "." + ministries.get(i).getName());
        }
    }

    @Override
    public List<Ministry> getAllMinistries() {
        return ministryList;
    }

    @Override
    public List<Ministry> getMultipleMinistries(List<Ministry> ministryList) {

        List<Ministry> selectedMinistries = new ArrayList<>();

        System.out.println("Selecteaza multiple slujiri sub forma 1,2,3. Ex: 1,2,3");
        showMinistries(ministryList);
        String selectedInput = scanner.nextLine(); // "1,2,3,4,5" -> ["1","2","3","4","5"] -> [1,2,3,4,5]
        String[] data = selectedInput.split(",");
        if (data.length > 0) {
            for (String indexString : data) {
                int index = Integer.parseInt(indexString) - 1;
                Ministry ministry = ministryList.get(index);
                selectedMinistries.add(ministry);
            }
        }

        return selectedMinistries;
    }

    @Override
    public Ministry getOneMinistry(List<Ministry> ministryList) {
        System.out.println("Alege slujirea dupa care vrei sa cauti");
        showMinistries(ministryList);
        return ministryList.get(Integer.parseInt(scanner.nextLine()) - 1);
    }

    @Override
    public List<Ministry> getWorshipMinistries() {
        return ministryList
                .stream()
                .filter(addPredicate(MinistryConstants.INSTRUMENTISTI).or(addPredicate(MinistryConstants.VOCALISTI).or(addPredicate(MinistryConstants.WORSHIP_LEADER))))
                .collect(Collectors.toList());
    }

    private Predicate<Ministry> addPredicate(String name) {
        return ministry -> ministry.getName().equals(name);
    }
}
