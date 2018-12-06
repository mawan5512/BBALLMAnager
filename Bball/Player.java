package Bball;

public class Player {
    private int PlayerID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String pos;
    private double height;
    private String college;
    private String birthDate;
    private String birthState;

    //Default Constructor
    Player()
    {
        PlayerID = 0;
        firstName = "";
        lastName = "";
        middleName = "";
        pos = "";
        height = 0;
        college = "";
        birthDate = "";
        birthState = "";
    }

    //Actual Constructor
    Player(int playerid, String first_name, String middle_name, String last_name, String pos, double height, String college, String birth_date, String birth_state)
    {
        PlayerID = playerid;
        firstName = first_name;
        middleName = middle_name;
        lastName = last_name;
        pos = pos;
        height = height;
        college = college;
        birthDate = birth_date;
        birthState = birth_state;

    }

    @Override
    public String toString() {
        String str = firstName + " " + middleName + " " + lastName;
        return str;
    }

    public int getPlayerID()
    {
        return PlayerID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPos()
    {
        return pos;
    }

    public double getHeight()
    {
        return height;
    }

    public String getCollege()
    {
        return college;
    }

    public String getBirthDate()
    {
        return birthDate;
    }

    public String getBirthState()
    {
        return birthState;
    }

    public void setPlayerID(int new_playerID)
    {
        PlayerID = new_playerID;
    }

    public void setFirstName(String new_first_name)
    {
        firstName = new_first_name;
    }

    public void setMiddleName(String new_middle_name)
    {
        middleName = new_middle_name;
    }

    public void setLastName(String new_last_name)
    {
        lastName = new_last_name;
    }

    public void setPos(String new_pos)
    {
        pos = new_pos;
    }

    public void setHeight(double new_height)
    {
        height = new_height;
    }

    public void setCollege(String new_college)
    {
        college = new_college;
    }

    public void setBirthDate(String new_birth_date)
    {
        birthDate = new_birth_date;
    }

    public void setBirthState(String new_birth_state)
    {
        birthState = new_birth_state;
    }

}