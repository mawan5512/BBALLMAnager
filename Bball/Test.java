package Bball;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        javadb db = new javadb();

        db.setConnections();

        ArrayList<Player> playerlist = db.searchPlayer("Leb", "Ja");
        Player player = playerlist.get(0);
        PRoster roster = db.getPlayerRoster(player.getPlayerID());
        ArrayList<PHistory> historyList = db.getPlayerHistory(player.getPlayerID());

        System.out.println(player.getFirstName());
        System.out.println(player.getLastName());
        System.out.println(roster.getSalary());
        for(PHistory history : historyList) {
            System.out.print(history.getYear() + " ");
            System.out.println(history.getSalary());
        }
    }
}
