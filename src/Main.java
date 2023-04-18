import config.ApplicationContext;
import controller.MainController;

public class Main {

    /*
    2) Inchinare -> Membrii, Cantece
    3) Mesaje -> Membrii, Mesaj
    4) Membrii -> Membrii / Vizitatori
    5) Grupuri mici -> Membrii / Lideri grupuri mici
     */
    
    public static String churchName = "Vertical Bucuresti";

    public static void main(String[] args) {
        start();
    }

    public static void start() {


        MainController mainController = ApplicationContext.mainController();

        mainController.greetingMessage(churchName);
        mainController.mainController();
        mainController.farewellMessage(churchName);
    }



}
