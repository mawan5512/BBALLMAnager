package Bball;

public class PHistory {
    private int PHistoryID;
    private int PlayerID;
    private int TeamID;
    private int salary;
    private int year;
    private int GP;
    private int points;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int turnovers;

    public PHistory() {
        this.PHistoryID = 0;
        PlayerID = 0;
        TeamID = 0;
        this.salary = 0;
        this.year = 0;
        this.GP = 0;
        this.points = 0;
        this.rebounds = 0;
        this.assists = 0;
        this.steals = 0;
        this.blocks = 0;
        this.turnovers = 0;
    }

    public PHistory(int PHistoryID, int playerID, int teamID, int salary, int year, int GP, int points, int rebounds, int assists, int steals, int blocks, int turnovers) {
        this.PHistoryID = PHistoryID;
        PlayerID = playerID;
        TeamID = teamID;
        this.salary = salary;
        this.year = year;
        this.GP = GP;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
    }

    @Override
    public String toString() {
        String str = " Year: " + Integer.toString(year) + " GP: " + Integer.toString(GP) + " PTS: " + Integer.toString(points);
        return str;
    }

    public int getPHistoryID() {
        return PHistoryID;
    }

    public void setPHistoryID(int PHistoryID) {
        this.PHistoryID = PHistoryID;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGP() {
        return GP;
    }

    public void setGP(int GP) {
        this.GP = GP;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }
}
