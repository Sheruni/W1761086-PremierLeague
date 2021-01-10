import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private Scanner sc = new Scanner(System.in);
    private static LeagueManager manager = new PremierLeagueManager();
    private Stage mainStage;


    public static void main(String[] args) throws Exception {
        manager.retrieveData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Timer timer = new Timer();
        timer.schedule(new runConsole(), 0, 5_000);

    }


    public class runConsole extends TimerTask{

        @Override
        public void run() {
            menuLoop:
            while (true) {
                displayMenu();

                while (!sc.hasNext("[12345678]")) {   // Checks for input validation
                    System.out.println("That's not a valid input!");
                    sc.next();
                }
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addClub();
                        break;
                    case 2:
                        removeClub();
                        break;
                    case 3:
                        displayStats();
                        break;
                    case 4:
                        manager.displayTable();
                        break;
                    case 5:
                        manager.addMatch();
                        break;
                    case 6:
                        try {
                            manager.saveData("PremierLeague");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        Platform.runLater(() -> {        //Opening the GUI multiple times when this option is selected
                            mainStage= new GUI().getStage();   //Create new Stage
                            mainStage.show();
                        });
                        break menuLoop;
                    case 8:
                        System.out.println("Thank you for using the system!");
                        System.exit(1);
                    default:
                        System.out.println("Invalid response!");
                }
            }

        }
    }


    public void displayStats() {
        System.out.println("Enter club name to display statistics: ");
        String clubName = sc.nextLine().toLowerCase();   //get input as lowercase
        manager.displayStats(clubName);
    }

    public void removeClub() {
        System.out.println("Enter club to remove :");
        String clubName = sc.nextLine().toLowerCase();

        manager.deleteClub(clubName);


    }

    public void addClub() {
        System.out.print("Enter club name : ");
        String clubName = sc.nextLine().toLowerCase();

        System.out.print("Enter club location : ");
        String location = sc.nextLine().toLowerCase();

        FootballClub fclub = new FootballClub((String) toCamelCase(clubName), (String) toCamelCase(location));
        manager.addClub(fclub);

        //Using toCamelCase method the names and locations of clubs are converted to camel case

    }


    public void displayMenu() {
        System.out.println("\nWelcome to the Premier League Manager !");
        System.out.println("To add new club to Premier League, press 1");
        System.out.println("To remove a club from Premier League, press 2");
        System.out.println("To print statistics of a club, press 3");
        System.out.println("To display the Premier League Table, press 4");
        System.out.println("To add a played match, press 5");
        System.out.println("To save the data, press 6");
        System.out.println("To open GUI, press 7");
        System.out.println("To exit press 8");
    }


    public Object toCamelCase(String key){
        //This is a method to convert string(name) into uppercamel case before inserting into Database

        StringBuilder name = new StringBuilder(key.length());
        for (final String word : key.split(" ")) {
            //the name is split by whitespace

            if (!word.isEmpty()) {
                //if the split part is not empty the first letter is set to capital and the rest to simple

                name.append(Character.toUpperCase(word.charAt(0)));
                name.append(word.substring(1).toLowerCase());
            }

            if (!(name.length() == key.length()))
                //if the name length is not equal to the key length a space is added to the name
                name.append(" ");
        }
        return name.toString();

    }

}
