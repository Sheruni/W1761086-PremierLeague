import java.io.*;
import java.util.*;


public class PremierLeagueManager extends Score implements LeagueManager{
    private Scanner sc = new Scanner(System.in);
    public static List<FootballClub> leagueList = new ArrayList<>();
    public static List<Match> matchList = new ArrayList<>();
    private int maxTeams = 20;



    @Override
    public void addClub(FootballClub club) {
        if (leagueList.contains(club)) {
            System.out.println("This club is already added to the league");
        } else if (leagueList.size() == maxTeams) {   // Validation of the maximum number of clubs to be added to the premier league
            System.out.println("Maximum number of clubs have been reached for the Premier League !");
        } else {
            leagueList.add(club);
        }
    }

    @Override
    public void deleteClub(String club) {
        boolean found = false;
        if (leagueList.isEmpty()) {     //Display message if leagueList is empty
            System.out.println("There are no clubs in the Premier League !");
        } else {
            for (FootballClub fclub : leagueList) {
                if (fclub.getClubName().toLowerCase().equals(club.toLowerCase())) {
                    found = true;
                    leagueList.remove(fclub);
                    break;
                }
            }
            if (!found) {
                System.out.println("This club is not in the Premier League");
            }
        }

    }

    @Override
    public void displayStats(String name) {
        boolean found = false;
        if (leagueList.isEmpty()) {    //Validation if the entered club is existing
            System.out.println("There are no clubs in the Premier League !");
        } else {
            for (FootballClub club : leagueList) {
                if ((club.getClubName().toLowerCase()).equals(name)) {
                    found = true;
                    System.out.println("WINS : " + club.getWins());
                    System.out.println("DRAWS : " + club.getDraws());
                    System.out.println("LOSSES : " + club.getLosses());
                    System.out.println("GOALS SCORED : " + club.getGoalsScored());
                    System.out.println("GOALS AGAINST : " + club.getReceivedGoals());
                    System.out.println("POINTS : " + club.getPoints());
                    System.out.println("MATCHES PLAYED : " + club.getMatchesPlayed());
                }
            }
            if (!found) {
                System.out.println("This club is not in the Premier League");
            }
        }

    }

    @Override
    public void displayTable() {


        if (leagueList.isEmpty()) {
            System.out.println("There are no clubs in the Premier League !");
        } else {
            Collections.sort(leagueList, new TeamComparator().reversed());
            String repeated = new String(new char[120]).replace("\0", "-");  //Horizontal line of repetitive dashes
            System.out.println("|" + repeated + "|");

            System.out.format("|%16s %10s %10s %10s %18s %18s %10s %18s %3s",
                    "Name", "Wins", "Draws", "Losses", "Goals Scored", "Goals Received", "Points", "Matches Played", "|");
            System.out.print("\n|" + repeated + "|");
            for (FootballClub club : leagueList) {
                System.out.printf("\n|%16s %8d %10d %9d %15d %17d %14d %14d %10s",
                        club.getClubName(),
                        club.getWins(),
                        club.getDraws(),
                        club.getLosses(),
                        club.getGoalsScored(),
                        club.getReceivedGoals(),
                        club.getPoints(),
                        club.getMatchesPlayed(), "|");
            }
            System.out.println("\n|" + repeated + "|");

        }

    }

    @Override
    public void addMatch() {

        System.out.print("Enter Home Team : ");
        String hteam = sc.nextLine();
        FootballClub home = null;
        boolean hfound = false;
        for (FootballClub club : leagueList) {
            if ((club.getClubName().toLowerCase()).equals(hteam)) {
                hfound = true;
                home = club;
            }
        }
        if (!hfound) {
            System.out.println("Club is not in the Premier League");
            return;
        }

        System.out.print("Enter Away Team : ");
        FootballClub away = null;
        String ateam = sc.nextLine();
        boolean afound = false;
        for (FootballClub club : leagueList) {
            if ((club.getClubName().toLowerCase()).equals(ateam)) {
                afound = true;
                away = club;
            }
        }
        if (!afound) {
            System.out.println("Club is not in the Premier League");
            return;
        }

        System.out.println("Enter home team score : ");
        int hScore;
        while (true) {
            try {
                hScore = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid score");    //Validation for input type
            }
        }

        System.out.println("Enter away team score : ");
        int aScore;
        while (true) {
            try {
                aScore = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid score");   //Validation for input type
            }
        }


        System.out.print("Enter date (dd-mm-yy) : ");

        String date = sc.nextLine();

        String[] datepart = date.split("-");   //Split the entered date to acquire the day, month and year separately

        int day = Integer.parseInt(datepart[0]);
        int month = Integer.parseInt(datepart[1]);
        int year = Integer.parseInt(datepart[2]);

        Date mdate = new Date(day, month, year);

        while (!Date.isDateValid(date)) {
            System.out.println("Invalid Date! Please try again");
            date = sc.next();
        }

        Match match = new Match(home, away, hScore, aScore, mdate);   //Create  new Match object
        matchList.add(match);   //Add the new match to the match list
        setScore(home, away, hScore, aScore);    //Update the score board based on the match details

        Collections.sort(matchList,new DateComparator());   //Sort the match list according to the date played
    }

    @Override
    public void saveData(String fileName) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("PremierLeague");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        boolean clubsExists = new File("PremierLeague").exists();   //Writing the clubs into the PremierLeague text file
        ObjectOutputStream oos = clubsExists ?
                new ObjectOutputStream(fileOutputStream) {    //If the file exists the data will be written to the existing file
                    public void writeStreamHeader() throws IOException {
                        reset();
                        for (FootballClub club : leagueList) {
                            objectOutputStream.writeObject(club);
                        }
                    }
                } : new ObjectOutputStream(fileOutputStream);  //If the file doesn't exist, a new file will be created

        objectOutputStream.flush();
        fileOutputStream.close();
        objectOutputStream.close();

        if (!matchList.isEmpty()) {
            FileOutputStream fos = new FileOutputStream("Matches");  //Writing the matches into the Matches text file
            ObjectOutputStream oos1 = new ObjectOutputStream(fos);

            boolean matchesExists = new File("Matches").exists();
            ObjectOutputStream oos2 = matchesExists ?
                    new ObjectOutputStream(fos) {
                        public void writeStreamHeader() throws IOException {   //If the file exists the data will be written to the existing file
                            reset();
                            for (Match match : matchList) {
                                oos1.writeObject(match);
                            }
                        }
                    } : new ObjectOutputStream(fos);  //If the file doesn't exist, a new file will be created

            System.out.println("Data have been saved successfully");
            oos1.flush();
            fos.close();
            oos1.close();
        }

    }

    @Override
    public void retrieveData() throws IOException {

        boolean premierLeagueExists = new File("PremierLeague").exists();
        if (!premierLeagueExists) {    //If the text file doesn't exist, it will return no values
            return;
        }
        FileInputStream fis1 = new FileInputStream("PremierLeague");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        while (fis1.available() > 0) {  //while there are objects in the text file, each of them will be added to the leaguelist
            try {
                FootballClub club = (FootballClub) ois1.readObject();
                leagueList.add(club);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ois1.close();
        fis1.close();

        boolean matchesExists = new File("Matches").exists();
        if (!matchesExists) {    //If the text file doesn't exist, it will return no values
            return;
        }
        FileInputStream fis2 = new FileInputStream("Matches");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        while (fis2.available() > 0) {   //while there are objects in the text file, each of them will be added to the matchlist
            try {
                Match match = (Match) ois2.readObject();
                matchList.add(match);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ois2.close();
        fis2.close();

    }


}
