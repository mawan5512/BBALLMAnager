package Bball;

public class Team {
    private int TeamID;
    private String teamName;

    public Team() {
        TeamID = 0;
        this.teamName = "";     
    }

    public Team(int teamID, String teamName) {
        TeamID = teamID;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        String str = teamName;
        return str;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}