package models;

public class Contestant extends User {
    protected String contestantName;
    protected String contestantEmail;
    protected String contestantType;

    public Contestant(String username, String password, String userId, String contestantName, String contestantEmail, String contestantType){
        super (username, password, userId);
        this.contestantName = contestantName;
        this.contestantEmail = contestantEmail;
        this.contestantType = contestantType;
    }


//Methods
    @Override
    public void displayData() {
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
}
