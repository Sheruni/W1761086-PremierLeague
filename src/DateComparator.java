import java.util.Comparator;

public class DateComparator implements Comparator<Match> {
    @Override
    public int compare(Match m1, Match m2) {
        return m1.getDate().compareTo(m2.getDate());
    }

}
