package models;
/*
Ansvarlig: William
 */

public class Contestant extends User {
    protected String contestantName;
    protected String contestantEmail; // Opretter attributtor for "Contestant"
    protected String contestantType;
    protected String contestantTeamId;

    public Contestant(String username, String password, String userId, String contestantName, String contestantEmail, String contestantType){
        super (username, password, userId);
        this.contestantName = contestantName;
        this.contestantEmail = contestantEmail;    //kalder attributter i constructoren.
        this.contestantType = contestantType;
        this.contestantTeamId = userId.substring(0,4) + "00";
    }

//Abstract Method
    @Override
    public void displayData() {
        System.out.println("#" + userId + "_************************************************************************");
        System.out.println("<---------------DELTAGER--------------->");
        System.out.println("Navn: " + contestantName);
        System.out.println("Email: " + contestantEmail);
        System.out.println("CyklistType: " + contestantType);
        System.out.println("\n");
    }

//Getter and Setter Methods
    public String getContestantName() {
        return contestantName;
    }
    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }
    public String getContestantEmail() {
        return contestantEmail;
    }
    public void setContestantEmail(String contestantEmail) {
        this.contestantEmail = contestantEmail;
    }
    public String getContestantType() {
        return contestantType;
    }
    public void setContestantType(String contestantType) {
        this.contestantType = contestantType;
    }
    public String getContestantTeamId() {
        return contestantTeamId;
    }
    public void setContestantTeamId(String contestantTeamId) {
        this.contestantTeamId = contestantTeamId;
    }
}
