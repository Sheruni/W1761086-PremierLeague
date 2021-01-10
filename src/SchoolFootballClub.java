import java.util.Objects;

public class SchoolFootballClub extends FootballClub  {
    private String schoolName;


    public SchoolFootballClub(String clubName, String location, String schoolName) {
        super(clubName, location);
        this.schoolName = schoolName;

    }

    public SchoolFootballClub(String clubName, String location, int wins, int draws, int losses, int receivedGoals, int goalsScored, int points, int matchesPlayed, String schoolName, String location1) {
        super(clubName, location, wins, draws, losses, receivedGoals, goalsScored, points, matchesPlayed);
        this.schoolName = schoolName;

    }



    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName);
    }

    @Override
    public String toString() {
        return "SchoolFootballClub : schoolName= " + schoolName;
    }
}
