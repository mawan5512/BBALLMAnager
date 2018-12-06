package Bball;

public class CRoster {
    private int CoachID;
    private int TeamID;
    private int salary;

    public CRoster(){
        CoachID = 0;
        this.TeamID = -1;
        this.salary = 0;
    }

    public CRoster(int coachID, int teamID, int salary) {
        CoachID = coachID;
        this.TeamID = teamID;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String str = " Salary: " + Integer.toString(salary);
        return str;
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
        this.TeamID = teamID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
