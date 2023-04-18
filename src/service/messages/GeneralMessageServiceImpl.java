package service.messages;

import java.util.Scanner;

public class GeneralMessageServiceImpl implements GeneralMessageService{

    @Override
    public void generalErrorMessage() {
        System.out.println("Ne pare rau. Selecteaza una din optiunile de mai jos...");
    }

    @Override
    public boolean generalQuestionYesOrNoMessage() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (true) {
            System.out.println("1.Da\n2.Nu");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("1")) {
                return true;
            } else if (input.equalsIgnoreCase("2")) {
                return false;
            } else {
                generalErrorMessage();
            }
        }

    }
}
