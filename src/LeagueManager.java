import java.io.IOException;

public interface LeagueManager {
    void addClub(FootballClub club);
    void deleteClub(String club);
    void displayStats(String name);
    void displayTable();
    void addMatch();
    void saveData(String fileName) throws IOException;
    void retrieveData() throws IOException, ClassNotFoundException;
}

