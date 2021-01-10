import java.util.Comparator;

public class TeamComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t1, FootballClub t2) {
        if(!(t1.getPoints()==t2.getPoints())) {
            return (t1.getPoints()- t2.getPoints());
        }else{
            int t1goalDifference = t1.getGoalsScored()-t1.getReceivedGoals();
            int t2goalDifference = t2.getGoalsScored()-t2.getReceivedGoals();
            return (t1goalDifference-t2goalDifference);
        }
    }
}
