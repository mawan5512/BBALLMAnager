package Bball;
import java.sql.*;
import java.io.*;
import java.util.*;

public class javadb{
    Connection conn;
    Statement stmt;

    public void setConnections() throws SQLException, ClassNotFoundException, IOException{
        conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bmanager", "bmanager", "bmanager");
        stmt =conn.createStatement();
    }


    //Player SQL Statements
    public ArrayList<Player> searchPlayer(String firstName, String lastName) throws SQLException{
        ArrayList<Player> playerlist = new ArrayList<>();
        String sql ="SELECT * FROM PLAYER ";
        if(firstName != null){
            sql += "WHERE firstName like '%" + firstName + "%'";
            if(lastName != null){
                sql += "AND lastName like '%" + lastName + "%'";
            }
        }
        else{
            sql += "WHERE lastName like '%" + lastName + "%'";
        }

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Player player = new Player(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9));
            playerlist.add(player);
        }
        return playerlist;
    }

    public Player getPlayerByID(int pid)throws SQLException{
        Player player = new Player();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER WHERE PlayerID = " + pid);
        while(rs.next()){
            player = new Player(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9));
        }
        return player;
    }

    public PRoster getPlayerRoster(int pid) throws SQLException{
        PRoster player = new PRoster();
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM PLAYER_ROSTER WHERE PlayerID = '" + pid + "'");
        while(rs1.next()){
            player = new PRoster(rs1.getInt(1),rs1.getInt(2), rs1.getInt(3), rs1.getBoolean(4));
            System.out.println(player.toString());
        }
        return player;
    }


    public ArrayList<PHistory> getPlayerHistory(int pid) throws SQLException{
        ArrayList<PHistory> historyList = new ArrayList<>();
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM PLAYER_HISTORY WHERE playerID = '" + pid + "'");
        while(rs2.next()){
            PHistory history = new PHistory(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),rs2.getInt(6),rs2.getInt(7),rs2.getInt(8),rs2.getInt(9),rs2.getInt(10),rs2.getInt(11),rs2.getInt(12));
            historyList.add(history);
        }
        return historyList;
    }

    public boolean shouldHire(int pid) throws SQLException{
        PRoster player = new PRoster();
        Boolean empty = true;
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM PLAYER_ROSTER WHERE PlayerID = '" + pid + "'");
        while(rs1.next()){
            empty = false;
        }
        if (empty) {
            return false;
        }
        return true;
    }

    public void hirePlayer(int pid, int tid) throws SQLException{
        stmt.execute("INSERT INTO PLAYER_ROSTER VALUES(" + pid + "," + tid +",100,false)");
    }

    public void tradePlayer(int pid, int tid) throws SQLException{
        stmt.execute("UPDATE PLAYER_ROSTER SET tmID =" + tid + " WHERE PlayerID = "+ pid);
    }

    public void firePlayer(int pid) throws SQLException{
        stmt.execute("DELETE FROM PLAYER_ROSTER WHERE PlayerID = " + pid);
    }

    public void suspendPlayer(int pid)throws SQLException{
        stmt.execute("UPDATE PLAYER_ROSTER SET suspension =" + true + " WHERE PlayerID = "+ pid);
    }
    public void unsuspendPlayer(int pid)throws SQLException{
        stmt.execute("UPDATE PLAYER_ROSTER SET suspension =" + false + " WHERE PlayerID = "+ pid);
    }

    //Team SQL statements
    public ArrayList<Team> searchTeam(String teamName) throws SQLException{
        ArrayList<Team> teamlist = new ArrayList<>();
        String sql ="SELECT * FROM TEAM ";
        if(teamName != null){
            sql += "WHERE teamName like '%" + teamName + "%'";
        }

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Team team = new Team(rs.getInt(1),rs.getString(2));
            teamlist.add(team);
        }
        return teamlist;
    }

    public Team getTeam(String teamName) throws SQLException{
        Team team = new Team();
        String sql ="SELECT * FROM TEAM ";
        if(teamName != null){
            sql += "WHERE teamName like '%" + teamName + "%'";
        }

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            team = new Team(rs.getInt(1),rs.getString(2));
        }
        return team;
    }

    public Team getTeamByID(int tid) throws SQLException{
        Team team = new Team();
        String sql ="SELECT * FROM TEAM ";
        sql += "WHERE tmID =" + tid;

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            team = new Team(rs.getInt(1),rs.getString(2));
        }
        return team;
    }

    public ArrayList<THistory> getTeamHistory(int tid) throws SQLException{
        ArrayList<THistory> historyList = new ArrayList<>();
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM TEAM_HISTORY WHERE tmID = '" + tid + "'");
        while(rs2.next()){
            THistory history = new THistory(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),rs2.getInt(6),rs2.getInt(7),rs2.getInt(8),rs2.getInt(9));
            historyList.add(history);
        }
        return historyList;
    }

    public ArrayList<PRoster> getTeamPlayers(int tmid)throws SQLException{
        ArrayList<PRoster> playerlist = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_ROSTER WHERE tmID = " + tmid);
        while(rs.next()){
            PRoster player = new PRoster(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getBoolean(4));
            playerlist.add(player);
        }
        return playerlist;

    }

    public ArrayList<CRoster> getTeamCoaches(int tmid)throws SQLException{
        ArrayList<CRoster> coachlist = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM COACH_ROSTER WHERE tmID = " + tmid);
        while(rs.next()){
            CRoster coach = new CRoster(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            coachlist.add(coach);
        }
        return coachlist;

    }


    //Coach SQL statements
    public ArrayList<Coach> searchCoach(String firstName, String lastName) throws SQLException{
        ArrayList<Coach> coachlist = new ArrayList<>();
        String sql ="SELECT * FROM COACH ";
        if(firstName != null){
            sql += "WHERE firstName like '%" + firstName + "%'";
            if(lastName != null){
                sql += "AND lastName like '%" + lastName + "%'";
            }
        }
        else{
            sql += "WHERE lastName like '%" + lastName + "%'";
        }

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Coach coach = new Coach(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
            coachlist.add(coach);
        }
        return coachlist;
    }

    public Coach getCoachByID(int cid)throws SQLException{
        Coach coach = new Coach();
        ResultSet rs = stmt.executeQuery("SELECT * FROM COACH WHERE coachID = " + cid);
        while(rs.next()){
            coach = new Coach(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
        }
        return coach;
    }

    public CRoster getCoachRoster(int cid) throws SQLException{
        CRoster coach = new CRoster();
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM COACH_ROSTER WHERE coachID = '" + cid + "'");
        while(rs1.next()){
            coach = new CRoster(rs1.getInt(1),rs1.getInt(2), rs1.getInt(3));
        }
        return coach;
    }

    public boolean shouldHireCoach(int cid) throws SQLException{
        CRoster r = getCoachRoster(cid);
        if(r.getTeamID() == -1){
            return true;
        }
        return false;
    }

    public ArrayList<CHistory> getCoachHistory(int cid) throws SQLException{
        ArrayList<CHistory> historyList = new ArrayList<>();
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM COACH_HISTORY WHERE coachID = '" + cid + "'");
        while(rs2.next()){
            CHistory history = new CHistory(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),rs2.getInt(6));
            historyList.add(history);
        }
        return historyList;
    }

    public void hireCoach(int cid, int tid) throws SQLException{
        stmt.execute("INSERT INTO COACH_ROSTER VALUES(" + cid + "," + tid +",100)");
    }

    public void tradeCoach(int cid, int tid) throws SQLException{
        stmt.execute("UPDATE COACH_ROSTER SET tmID =" + tid + " WHERE coachID = "+ cid);
    }

    public void fireCoach(int cid) throws SQLException{
        stmt.execute("DELETE FROM COACH_ROSTER WHERE coachID = " + cid);
    }

    public void changePlayerSalary(int salary, int playerID) throws SQLException{
        String sql = "UPDATE PLAYER_ROSTER " +
                "SET salary = "+salary+" " +
                "WHERE PlayerID = "+playerID+"; ";

        stmt.execute(sql);
    }

    public void changeCoachSalary(int salary, int coachID) throws SQLException{
        String sql = "UPDATE COACH_ROSTER " +
                "SET salary = "+salary+" " +
                "WHERE coachID = "+coachID+"; ";

        stmt.execute(sql);
    }

    public void disbandTeam (int tmID) throws SQLException{
        String sql ="DELETE FROM PLAYER_ROSTER WHERE tmID = " + tmID;
        String sql2 ="DELETE FROM COACH_ROSTER WHERE tmID = " + tmID;
        stmt.execute(sql);
        stmt.execute(sql2);
    }

    public void draftPlayer(String firstname, String middleName, String lastName, String pos, int height,
                            String college, String birthDate, String birthState) throws SQLException{
        String sql = "INSERT INTO PLAYER(firstName, middleName, lastName, pos, height, college, birthDate, birthState) "
                + "VALUES('"+firstname+"','"+middleName+"','"+lastName+"','"+pos+"',"+height
                +",'"+college+"','"+birthDate+"','"+birthState+"');";
        stmt.execute(sql);
    }

    public void addCoach (String fname, String mname, String lname, String date) throws SQLException{
        String sql = "INSERT INTO COACH (firstName, middleName, lastName, birthDate) VALUES ('" + fname + "', '" + mname + "', '" + lname + "', '" + date + "')";
        stmt.execute(sql);
    }

    public void addTeam (String name) throws SQLException{
        String sql = "INSERT INTO TEAM (teamName) VALUES ('" + name  + "')";
        stmt.execute(sql);
    }

}