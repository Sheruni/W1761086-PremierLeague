import java.util.Objects;

public class UniversityFootballClub extends FootballClub{
    private String uniName;

    public UniversityFootballClub(String clubName, String location,String uniName) {
        super(clubName, location);
        this.uniName= uniName;
    }

    public UniversityFootballClub(String clubName, String location, int wins, int draws, int losses, int receivedGoals, int goalsScored, int points, int matchesPlayed, String uniName) {
        super(clubName, location, wins, draws, losses, receivedGoals, goalsScored, points, matchesPlayed);
        this.uniName=uniName;
    }


    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(uniName, that.uniName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniName);
    }

    @Override
    public String toString() {
        return "UniversityFootballClub : uniName= " + uniName;
    }
}
