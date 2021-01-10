abstract class Score{

    public static void setScore(FootballClub home, FootballClub away, int hScore, int aScore){

        home.setGoalsScored(home.getGoalsScored() + hScore);
        home.setReceivedGoals(home.getReceivedGoals() + aScore);
        home.setMatchesPlayed(home.getMatchesPlayed() + 1);

        away.setGoalsScored(away.getGoalsScored() + aScore);
        away.setReceivedGoals(away.getReceivedGoals() + hScore);
        away.setMatchesPlayed(away.getMatchesPlayed() + 1);

        if (hScore == aScore) {
            home.setDraws(home.getDraws() + 1);
            home.setPoints(home.getPoints() + 1);

            away.setDraws(away.getDraws() + 1);
            away.setPoints(away.getPoints() + 1);

        } else if (hScore > aScore) {
            home.setWins(home.getWins() + 1);
            home.setPoints(home.getPoints() + 3);
            away.setLosses(away.getLosses() + 1);
        } else {
            away.setWins(away.getWins() + 1);
            away.setPoints(away.getPoints() + 3);
            home.setLosses(home.getLosses() + 1);

        }


    }
}
