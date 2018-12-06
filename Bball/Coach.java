package Bball;

public class Coach {
    private int CoachID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;

    public Coach() {
        CoachID = 0;
        firstName = "";
        middleName = "";
        lastName = "";
        birthDate = "";
    }

    public Coach(int coachID, String firstName, String middleName, String lastName, String birthDateName) {
        CoachID = coachID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDateName;
    }

    @Override
    public String toString() {
        String str = firstName + " " + middleName + " " + lastName;
        return str;
    }

    public int getCoachID() {
        return CoachID;
    }

    public void setCoachID(int coachID) {
        CoachID = coachID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
