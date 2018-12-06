package Bball;

public class PRoster {
    private int PlayerID;
    private int TeamID;
    private int salary;
    private boolean suspension;

    public PRoster(){
        PlayerID = 0;
        this.TeamID = -1;
        this.salary = 0;
        this.suspension = true;
    }

    public PRoster(int playerID, int teamID, int salary, boolean suspension) {
        PlayerID = playerID;
        this.TeamID = teamID;
        this.salary = salary;
        this.suspension = suspension;
    }

    @Override
    public String toString() {
        String str = " Salary: " + Integer.toString(salary) + " Suspension: " + suspension;
        return str;
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
        this.TeamID = teamID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isSuspension() {
        return suspension;
    }

    public void setSuspension(boolean suspension) {
        this.suspension = suspension;
    }
}
