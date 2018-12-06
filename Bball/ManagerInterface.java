package Bball;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.*;
import java.util.*;


import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ManagerInterface extends Application {

    public static javadb db = new javadb();
    private Stage stage;

    private String labelStyle = "-fx-font: normal bold 28px 'Calibri'; -fx-text-fill: white";
    private String buttonStyle = "-fx-background-color: FireBrick; -fx-text-fill: white;";
    private String panelStyle = "-fx-font: normal 15px 'Calibri';" +
            "-fx-padding: 5;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 4;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: FireBrick;";
    private String rootStyle = "-fx-background-color: Peru;";

    private void defaultGridSettings(GridPane gridPane){
        int minw = 400;
        int minh = 150;
        Insets insets = new Insets(10, 10, 10, 10);
        int vgap = 5;
        int hgap = 5;
        Pos posvalue = Pos.CENTER;

        gridPane.setMinSize(minw, minh);
        gridPane.setPadding(insets);
        gridPane.setVgap(vgap);
        gridPane.setHgap(hgap);
        gridPane.setAlignment(posvalue);
    }

    //INTERFACE

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        //Setting title to the Stage
        stage.setTitle("Basketball Manager");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(530);
        //Adding scene to the stage
        stage.setScene(mainScene());
        //Displaying the contents of the stage
        stage.show();
    }

    private Scene mainScene(){
        //player
        Label plabel = new Label("PLAYER");
        Text ptext1 = new Text("Player First Name");
        Text ptext2 = new Text("Player Last Name");
        TextField ptextField1 = new TextField();
        TextField ptextField2 = new TextField();
        Button pbutton1 = new Button("Manage");
        pbutton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(multipleFoundScene("player", ptextField1.getText(), ptextField2.getText()));
            }
        });
        //

        //coach
        Label clabel = new Label("COACH");
        Text ctext1 = new Text("Coach First Name");
        Text ctext2 = new Text("Coach Last Name");
        TextField ctextField1 = new TextField();
        TextField ctextField2 = new TextField();
        Button cbutton1 = new Button("Manage");
        cbutton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(multipleFoundScene("coach", ctextField1.getText(), ctextField2.getText()));
            }
        });
        //

        //team
        Label tlabel = new Label("TEAM");
        Text ttext1 = new Text("Team Name");
        TextField ttextField1 = new TextField();
        Button tbutton1 = new Button("Manage");
        tbutton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(multipleFoundScene("team", ttextField1.getText(), ""));
            }
        });
        //

        //new records
        Label nlabel = new Label("NEW RECORDS");
        Button nbutton1 = new Button("Create New Records");
        nbutton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(newRecordScene());
            }
        });
        //

        //setting up gridpanes
        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);
        gridPane1.addRow(0, plabel);
        gridPane1.addRow(1, ptext1, ptextField1);
        gridPane1.addRow(2, ptext2, ptextField2, pbutton1);

        GridPane gridPane2 = new GridPane();
        defaultGridSettings(gridPane2);
        gridPane2.addRow(0, clabel);
        gridPane2.addRow(1, ctext1, ctextField1);
        gridPane2.addRow(2, ctext2, ctextField2, cbutton1);

        GridPane gridPane3 = new GridPane();
        defaultGridSettings(gridPane3);
        gridPane3.addRow(0, tlabel);
        gridPane3.addRow(2, ttext1, ttextField1, tbutton1);

        GridPane gridPane4 = new GridPane();
        defaultGridSettings(gridPane4);
        gridPane4.setMinSize(100, 150);
        gridPane4.addRow(0,nlabel);
        gridPane4.addRow(1,nbutton1);

        //setting up borderpane
        BorderPane root = new BorderPane();
        root.setTop(gridPane1);
        root.setCenter(gridPane2);
        root.setBottom(gridPane3);
        root.setLeft(gridPane4);

        //styling
        //labels
        plabel.setStyle(labelStyle);
        clabel.setStyle(labelStyle);
        tlabel.setStyle(labelStyle);
        nlabel.setStyle(labelStyle);
        //buttons
        pbutton1.setStyle(buttonStyle);
        cbutton1.setStyle(buttonStyle);
        tbutton1.setStyle(buttonStyle);
        nbutton1.setStyle(buttonStyle);
        //panels
        gridPane1.setStyle(panelStyle);
        gridPane2.setStyle(panelStyle);
        gridPane3.setStyle(panelStyle);
        gridPane4.setStyle(panelStyle);
        //background color
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    private Scene playerScene(Player p){
        String playerfname = p.getFirstName();
        String playerlname = p.getLastName();
        Label label = new Label("VIEWING PLAYER: "+playerfname+" "+playerlname);
        Text text1 = new Text("Salary");
        Text text2 = new Text("Team Name");
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Boolean onTeam = false;
        Button button1 = new Button("Change Salary");
        Button button2 = new Button("Hire");
        Button button3 = new Button("See Performance");
        Button button4 = new Button("Suspend");
        Button button5 = new Button("Fire");
        TextArea area = new TextArea();
        try {
            PRoster roster = new PRoster();
            Team team = new Team();
            onTeam = db.shouldHire(p.getPlayerID());
            System.out.println("ON TEAM: " + onTeam);
            if((onTeam)){
                 roster = db.getPlayerRoster(p.getPlayerID());
                 System.out.println("ID:" + roster.getPlayerID());
                 team = db.getTeamByID(roster.getTeamID());
                 textField1.setText(Integer.toString(roster.getSalary()));
                 textField2.setText(team.getTeamName());
                 if(roster.isSuspension()){
                     button4.setText("Unsuspend");
                 }
                 button2.setText("Trade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        area.setPrefHeight(500);
        area.setPrefWidth(1500);
        button1.setOnAction(new EventHandler<ActionEvent>() { //change salary
            @Override
            public void handle(ActionEvent event) {
                int salary = Integer.parseInt(textField1.getText());
                try {
                    db.changePlayerSalary(salary, p.getPlayerID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("Salary updated!!\n");
            }
        });
        Boolean finalOnTeam = onTeam;
        button2.setOnAction(new EventHandler<ActionEvent>() { //hire / trade
            @Override
            public void handle(ActionEvent event) {
                try {
                    Team t = db.getTeam(textField2.getText());
                    if(button2.getText().equals("Hire")){
                        db.hirePlayer(p.getPlayerID(), t.getTeamID());
                        area.appendText("Player hired!!\n");
                        button2.setText("Trade");
                    }else{
                        db.tradePlayer(p.getPlayerID(), t.getTeamID());
                        area.appendText("Player traded!!\n");
                    }
                    PRoster roster = new PRoster();
                    Team team = new Team();
                    roster = db.getPlayerRoster(p.getPlayerID());
                    team = db.getTeamByID(roster.getTeamID());
                    textField1.setText(Integer.toString(roster.getSalary()));
                    textField2.setText(team.getTeamName());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() { //see performance
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<PHistory> history = db.getPlayerHistory(p.getPlayerID());
                    if(!history.isEmpty()) {
                        for (PHistory his : history) {
                            Team t = db.getTeamByID(his.getTeamID());
                            area.appendText(t.getTeamName() + his.toString() + "\n");
                        }
                    }else{
                        area.appendText("No History");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>() { //suspend
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(button4.getText().equals("Suspend")) {
                        db.suspendPlayer(p.getPlayerID());
                        button4.setText("Unsuspend");
                        area.appendText("SUSPENDED!!\n");
                    }else{
                        db.unsuspendPlayer(p.getPlayerID());
                        button4.setText("Suspend");
                        area.appendText("UNSUSPENDED!!\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>() { //fire
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.firePlayer(p.getPlayerID());
                    textField1.clear();
                    textField2.clear();
                    button2.setText("Hire");
                    area.appendText("Player fired!!\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button buttonh = new Button("<");
        buttonh.setOnAction(new EventHandler<ActionEvent>() { //return to main scene
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(mainScene());
            }
        });

        //setting up gridpanes
        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);
        gridPane1.addRow(0, buttonh, label);
        gridPane1.addRow(1, text1, textField1, button1);
        gridPane1.addRow(2, text2, textField2, button2);
        gridPane1.addRow(3, button3, button4, button5);

        GridPane gridPane2 = new GridPane();
        defaultGridSettings(gridPane2);
        gridPane2.addRow(0, area);

        //setting up borderpane
        BorderPane root = new BorderPane();
        root.setTop(gridPane1);
        root.setCenter(gridPane2);

        //styling
        //labels
        label.setStyle(labelStyle);
        //buttons
        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        button4.setStyle(buttonStyle);
        button5.setStyle(buttonStyle);
        buttonh.setStyle(buttonStyle);
        //panels
        gridPane1.setStyle(panelStyle);
        gridPane2.setStyle(panelStyle);
        //background color
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    private Scene coachScene(Coach coach){
        String coachfname = coach.getFirstName();
        String coachlname = coach.getLastName();
        Label label = new Label("VIEWING COACH: "+coachfname+" "+coachlname);
        Text text1 = new Text("Salary");
        Text text2 = new Text("Team Name");
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button button1 = new Button("Change Salary");
        Button button2 = new Button("Hire");
        Button button3 = new Button("Fire");
        Button button4 = new Button("See Performance");
        TextArea area = new TextArea();
        Boolean onTeam = false;
        Team team = new Team();
        try {
            CRoster roster = new CRoster();
            onTeam = db.shouldHireCoach(coach.getCoachID());
            if(!(onTeam)){
                roster = db.getCoachRoster(coach.getCoachID());
                team = db.getTeamByID(roster.getTeamID());
                textField1.setText(Integer.toString(roster.getSalary()));
                textField2.setText(team.getTeamName());
                button2.setText("Trade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        area.setPrefHeight(500);
        area.setPrefWidth(1500);
        button1.setOnAction(new EventHandler<ActionEvent>() { //change salary
            @Override
            public void handle(ActionEvent event) {
                int salary = Integer.parseInt(textField1.getText());
                try {
                    db.changeCoachSalary(salary, coach.getCoachID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("Salary updated!!\n");
            }
        });
        Boolean finalOnTeam = onTeam;
        button2.setOnAction(new EventHandler<ActionEvent>() { //hire
            @Override
            public void handle(ActionEvent event) {
                try {
                    Team t = db.getTeam(textField2.getText());
                    if(button2.getText().equals("Hire")){
                        db.hireCoach(coach.getCoachID(), t.getTeamID());
                        area.appendText("Coach hired!!\n");
                        button2.setText("Trade");
                    }else{
                        db.tradeCoach(coach.getCoachID(), t.getTeamID());
                        area.appendText("Coach traded!!\n");
                    }
                    CRoster roster = new CRoster();
                    t = new Team();
                    roster = db.getCoachRoster(coach.getCoachID());
                    t = db.getTeamByID(roster.getTeamID());
                    textField1.setText(Integer.toString(roster.getSalary()));
                    textField2.setText(t.getTeamName());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() { //fire
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.fireCoach(coach.getCoachID());
                    textField1.clear();
                    textField2.clear();
                    button2.setText("Hire");
                    area.appendText("Coach fired!!\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() { //performance
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<CHistory> history = db.getCoachHistory(coach.getCoachID());
                    if(!history.isEmpty()) {
                        for (CHistory his : history) {
                            Team t = db.getTeamByID(his.getTeamID());
                            area.appendText(t.getTeamName() + his.toString() + "\n");
                        }
                    }else{
                        area.appendText("No History");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button buttonh = new Button("<");
        buttonh.setOnAction(new EventHandler<ActionEvent>() { //return to main scene
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(mainScene());
            }
        });

        //setting up gridpanes
        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);
        gridPane1.addRow(0, buttonh, label);
        gridPane1.addRow(1, text1, textField1, button1);
        gridPane1.addRow(2, text2, textField2, button2);
        gridPane1.add(button3, 2, 3);
        gridPane1.add(button4,0,3);

        GridPane gridPane2 = new GridPane();
        defaultGridSettings(gridPane2);
        gridPane2.addRow(0, area);

        //setting up borderpane
        BorderPane root = new BorderPane();
        root.setTop(gridPane1);
        root.setCenter(gridPane2);

        //styling
        //labels
        label.setStyle(labelStyle);
        //buttons
        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        button4.setStyle(buttonStyle);
        buttonh.setStyle(buttonStyle);
        //panels
        gridPane1.setStyle(panelStyle);
        gridPane2.setStyle(panelStyle);
        //background color
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    private Scene teamScene(Team team){
        String teamname = team.getTeamName();
        Label label = new Label("VIEWING TEAM: "+teamname);
        Button button1 = new Button("Get Roster");
        Button button2 = new Button("Get History ");
        Button button3 = new Button("Disband Team");
        TextArea area = new TextArea();
        area.setPrefHeight(500);
        area.setPrefWidth(1500);
        button1.setOnAction(new EventHandler<ActionEvent>() { //get roster
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<CRoster> crosters = db.getTeamCoaches(team.getTeamID());
                    ArrayList<PRoster> prosters = db.getTeamPlayers(team.getTeamID());
                    if(!crosters.isEmpty()) {
                        area.appendText("Coaches:\n");
                        for (CRoster c : crosters) {
                            Coach coach = db.getCoachByID(c.getCoachID());
                            area.appendText(coach.toString() + " " + c.toString() + "\n");
                        }
                        if(!prosters.isEmpty()) {
                            area.appendText("Players:\n");
                            for (PRoster p : prosters) {
                                Player player = db.getPlayerByID(p.getPlayerID());
                                area.appendText(player.toString() + " " + p.toString() + "\n");
                            }
                        }else{
                            area.appendText("No Players\n");
                        }

                    }else{
                        area.appendText("Team is not Active\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() { //get history
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<THistory> history = db.getTeamHistory(team.getTeamID());
                    if(!history.isEmpty()) {
                        area.appendText("History:\n");
                        for (THistory his : history) {
                            area.appendText(his.toString() + "\n");
                        }
                    }else{
                        area.appendText("No History\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() { //disband
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.disbandTeam(team.getTeamID());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("EVERYONE ON THE TEAM IS NOW MURDERED");
            }
        });

        Button buttonh = new Button("<");
        buttonh.setOnAction(new EventHandler<ActionEvent>() { //return to main scene
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(mainScene());
            }
        });

        //setting up gridpanes
        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);
        gridPane1.addRow(0, buttonh, label);
        gridPane1.addRow(1, button1);
        gridPane1.addRow(2, button2);
        gridPane1.addRow(3, button3);

        GridPane gridPane2 = new GridPane();
        defaultGridSettings(gridPane2);
        gridPane2.addRow(0, area);

        //setting up borderpane
        BorderPane root = new BorderPane();
        root.setTop(gridPane1);
        root.setCenter(gridPane2);

        //styling
        //labels
        label.setStyle(labelStyle);
        //buttons
        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        buttonh.setStyle(buttonStyle);
        //panels
        gridPane1.setStyle(panelStyle);
        gridPane2.setStyle(panelStyle);
        //background color
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    private Scene newRecordScene(){
        TextArea area = new TextArea();
        area.setPrefWidth(1500);

        Text header1 = new Text("DRAFT PLAYER");
        Text text1 = new Text("First Name");
        TextField textField1 = new TextField();
        Text text2 = new Text("Middle Name");
        TextField textField2 = new TextField();
        Text text3 = new Text("Last Name");
        TextField textField3 = new TextField();
        Text text4 = new Text("Position");
        TextField textField4 = new TextField();
        Text text5 = new Text("Height");
        TextField textField5 = new TextField();
        Text text6 = new Text("College");
        TextField textField6 = new TextField();
        Text text7 = new Text("Birth Date");
        TextField textField7 = new TextField();
        Text text8 = new Text("Birth State");
        TextField textField8 = new TextField();
        Button button1 = new Button("Draft");
        button1.setOnAction(new EventHandler<ActionEvent>() { // draft new player
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.draftPlayer(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText()
                            ,Integer.parseInt(textField5.getText()), textField6.getText(), textField7.getText(), textField8.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("New player drafted\n");
                textField1.clear();
                textField2.clear();
                textField3.clear();
                textField4.clear();
                textField5.clear();
                textField6.clear();
                textField7.clear();
                textField8.clear();


            }
        });

        Text header2 = new Text("ADD COACH");
        Text text9 = new Text("First Name");
        TextField textField9 = new TextField();
        Text text10 = new Text("Middle Name");
        TextField textField10 = new TextField();
        Text text11 = new Text("Last Name");
        TextField textField11 = new TextField();
        Text text12 = new Text("Birth Date");
        TextField textField12= new TextField();
        Button button2 = new Button("Add");
        button2.setOnAction(new EventHandler<ActionEvent>() { // add new coach
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.addCoach(textField9.getText(), textField10.getText(), textField11.getText(), textField12.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("New coach added\n");
                textField9.clear();
                textField10.clear();
                textField11.clear();
                textField12.clear();
            }
        });

        Text header3 = new Text("CREATE TEAM");
        Text text13 = new Text("Team Name");
        TextField textField13 = new TextField();
        Button button3 = new Button("Create");
        button3.setOnAction(new EventHandler<ActionEvent>() { // create new team
            @Override
            public void handle(ActionEvent event) {
                try {
                    db.addTeam(textField13.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                area.appendText("New team created\n");
                textField13.clear();
            }
        });

        Button buttonh = new Button("<");
        buttonh.setOnAction(new EventHandler<ActionEvent>() { //return to main scene
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(mainScene());
            }
        });

        //setting up gridpanes
        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);
        gridPane1.setMaxSize(800, 400);
        gridPane1.addRow(0, header1);
        gridPane1.addRow(1, text1, textField1, text2, textField2);
        gridPane1.addRow(2, text3, textField3, text4, textField4);
        gridPane1.addRow(3, text5, textField5, text6, textField6);
        gridPane1.addRow(4, text7, textField7, text8, textField8);
        gridPane1.addRow(5, button1);

        GridPane gridPane2 = new GridPane();
        defaultGridSettings(gridPane2);
        gridPane2.setMaxSize(400, 400);
        gridPane2.addRow(0, header2);
        gridPane2.addRow(1, text9, textField9);
        gridPane2.addRow(2, text10, textField10);
        gridPane2.addRow(3, text11, textField11);
        gridPane2.addRow(4, text12, textField12);
        gridPane2.addRow(5, button2);

        GridPane gridPane3 = new GridPane();
        gridPane3.setMaxSize(400, 400);
        gridPane3.addRow(1, buttonh, header3);
        gridPane3.addRow(2, text13, textField13);
        gridPane3.addRow(3, button3);

        GridPane gridPane4 = new GridPane();
        defaultGridSettings(gridPane4);
        gridPane4.addRow(0, area);

        //setting up borderpane
        BorderPane root = new BorderPane();
        root.setCenter(gridPane1);
        root.setRight(gridPane2);
        root.setLeft(gridPane3);
        root.setBottom(gridPane4);

        //styling
        header1.setStyle("-fx-font: normal bold 20px 'Calibri'; ");
        header2.setStyle("-fx-font: normal bold 20px 'Calibri'; ");
        header3.setStyle("-fx-font: normal bold 20px 'Calibri'; ");
        //buttons
        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        buttonh.setStyle(buttonStyle);
        //panels
        gridPane1.setStyle(panelStyle);
        gridPane2.setStyle(panelStyle);
        gridPane3.setStyle(panelStyle);
        gridPane4.setStyle(panelStyle);
        //background color
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    private Scene multipleFoundScene(String searchType, String name1, String name2) {
        ListView listView = new ListView();
        switch (searchType) {
            case "player":
                ArrayList<Player> plist = null;
                try {
                    plist = db.searchPlayer(name1, name2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for(Player p : plist){
                    listView.getItems().add(p);
                }
                break;
            case "coach":
                ArrayList<Coach> clist = null;
                try {
                    clist = db.searchCoach(name1, name2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for(Coach c : clist){
                    listView.getItems().add(c);
                }
                break;
            case "team":
                ArrayList<Team> tlist = null;
                try {
                    tlist = db.searchTeam(name1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for(Team t : tlist){
                    listView.getItems().add(t);
                }
                break;
        }

        Text text1 = new Text("Multiple matching records found, please select one");
        Button button1 = new Button("Manage Selected");
        button1.setOnAction(new EventHandler<ActionEvent>() { // add new coach
            @Override
            public void handle(ActionEvent event) {
                Object o = listView.getSelectionModel().getSelectedItem();


                System.out.println("o = " + o + " (" + o.getClass() + ")");
                System.out.println(o);


                switch (searchType) {
                    case "player":
                        Player p = (Player)o;
                        stage.setScene(playerScene(p));
                        break;
                    case "coach":
                        Coach c = (Coach)o;
                        stage.setScene(coachScene(c));
                        break;
                    case "team":
                        Team t = (Team)o;
                        stage.setScene(teamScene(t));
                        break;
                }
            }
        });
        Button buttonh = new Button("<");
        buttonh.setOnAction(new EventHandler<ActionEvent>() { //return to main scene
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(mainScene());
            }
        });


        GridPane gridPane1 = new GridPane();
        defaultGridSettings(gridPane1);

        gridPane1.addRow(0, buttonh);
        gridPane1.addRow(1, text1);
        gridPane1.addRow(2, listView);
        gridPane1.addRow(3, button1);

        BorderPane root = new BorderPane();
        root.setCenter(gridPane1);

        text1.setStyle("-fx-font: normal bold 20px 'Calibri'; ");
        button1.setStyle(buttonStyle);
        buttonh.setStyle(buttonStyle);
        gridPane1.setStyle(panelStyle);
        root.setStyle(rootStyle);

        return new Scene(root);
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        db.setConnections();
        Application.launch(args);
    }

    private class Thingy{
        private String name;

        private Thingy(String n){
            name = n;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
