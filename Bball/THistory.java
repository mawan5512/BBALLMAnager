package Bball;

public class THistory {
    private int THistoryID;
    private int TeamID;
    private int year;
    private int opts;
    private int dpts;
    private int won;
    private int lost;
    private int games;
    private int min;

    public THistory() {
        this.THistoryID = 0;
        TeamID = 0;
        this.year = 0;
        this.opts = 0;
        this.dpts = 0;
        this.won = 0;
        this.lost = 0;
        this.games = 0;
        this.min = 0;
    }

    public THistory(int THistoryID, int teamID, int year, int opts, int dpts, int won, int lost, int games, int min) {
        this.THistoryID = THistoryID;
        TeamID = teamID;
        this.year = year;
        this.opts = opts;
        this.dpts = dpts;
        this.won = won;
        this.lost = lost;
        this.games = games;
        this.min = min;
    }

    @Override
    public String toString() {
        String str = " Year: " + Integer.toString(year) + " Won: " + Integer.toString(won) + " Lost: " + Integer.toString(lost);
        return str;
    }

    public int getTHistoryID() {
        return THistoryID;
    }

    public void setTHistoryID(int THistoryID) {
        this.THistoryID = THistoryID;
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

    public int getOpts() {
        return opts;
    }

    public void setOpts(int opts) {
        this.opts = opts;
    }

    public int getDpts() {
        return dpts;
    }

    public void setDpts(int dpts) {
        this.dpts = dpts;
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

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
