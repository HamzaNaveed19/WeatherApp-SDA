import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        AbstractUI ui;
        AbstractDB db;

        UIFactory uifactory = new UIFactory();
        DBFactory dbfactory = new DBFactory();

        int uiInput;
        int dbInput;
        String uiString = "";
        String dbString = "";

        Scanner input = new Scanner(System.in);

        // Console UI Design
        System.out.println("###########################################");
        System.out.println("#        Welcome to Weather App           #");
        System.out.println("###########################################");
        System.out.println("#                                         #");
        System.out.println("#          Select UI Interface:           #");
        System.out.println("#           1. Graphical UI               #");
        System.out.println("#           2. Command Line UI            #");
        System.out.println("#                                         #");
        System.out.println("###########################################");

        uiInput = input.nextInt();
        input.nextLine(); // Consume the newline character

        if (uiInput == 1) {
            uiString = "Java";
        } else if (uiInput == 2) {
            uiString = "Terminal";
        }

        System.out.println("###########################################");
        System.out.println("#                                         #");
        System.out.println("#           Select Database:              #");
        System.out.println("#           1. SQL Database               #");
        System.out.println("#           2. TXT Database               #");
        System.out.println("#                                         #");
        System.out.println("###########################################");

        dbInput = input.nextInt();
        input.nextLine(); // Consume the newline character

        if (dbInput == 1) {
            dbString = "Database";
        } else if (dbInput == 2) {
            dbString = "FileHandler";
        }

        ui = uifactory.createUI(uiString);
        db = dbfactory.createHandler(dbString);

        WeatherApp app = new WeatherApp(ui, db);

        app.display();
    }
}
