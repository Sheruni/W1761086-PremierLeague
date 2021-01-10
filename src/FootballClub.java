import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable {
    private int wins;
    private int draws;
    private int losses;
    private int receivedGoals;
    private int goalsScored;
    private int points;
    private int matchesPlayed;

    public FootballClub(String clubName, String location) {
        super(clubName, location);
    }

    public FootballClub(String clubName, String location, int wins, int draws, int losses, int receivedGoals, int goalsScored, int points, int matchesPlayed) {
        super(clubName, location);
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.receivedGoals = receivedGoals;
        this.goalsScored = goalsScored;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public void setReceivedGoals(int receivedGoals) {
        this.receivedGoals = receivedGoals;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }


    @Override
    public String toString() {
        return "FootballClub : name="+super.getClubName()+
                ", location="+ super.getLocation()+
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", receivedGoals=" + receivedGoals +
                ", goalsScored=" + goalsScored +
                ", points=" + points +
                ", matchesPlayed=" + matchesPlayed;
    }
}
