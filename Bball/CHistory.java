package Bball;

public class CHistory {
    private int CHistoryID;
    private int CoachID;
    private int TeamID;
    private int year;
    private int won;
    private int lost;

    public CHistory() {
        this.CHistoryID = 0;
        CoachID = 0;
        TeamID = 0;
        this.year = 0;
        this.won = 0;
        this.lost = 0;
    }


    public CHistory(int CHistoryID, int coachID, int teamID, int year, int won, int lost) {
        this.CHistoryID = CHistoryID;
        CoachID = coachID;
        TeamID = teamID;
        this.year = year;
        this.won = won;
        this.lost = lost;
    }

    @Override
    public String toString() {
        String str = " Year: " + Integer.toString(year) + " Won: " + Integer.toString(won) + " Lost: " + Integer.toString(lost);
        return str;
    }

    public int getCHistoryID() {
        return CHistoryID;
    }

    public void setCHistoryID(int CHistoryID) {
        this.CHistoryID = CHistoryID;
    }

    public int getCoachID() {
        return CoachID;
    }

    public void setCoachID(int coachID) {
        CoachID = coachID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }
}
