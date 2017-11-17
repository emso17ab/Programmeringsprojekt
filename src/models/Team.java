package models;
import java.util.ArrayList;

public class Team extends User {
    protected String teamName;
    protected String teamLeader;
    protected ArrayList<Contestant> contestants;

    public Team(String username, String password, String userId, String teamName, String teamLeader){
        super (username, password, userId);
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.contestants = new ArrayList<>();
    }

//Abstract methods
    @Override
    public void displayData() {
    }

    //Methods
    public void addContestantToTeam(Contestant newContestant){
        this.contestants.add(newContestant);
    }


//Getter and Setter Methods
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamLeader() {
        return teamLeader;
    }
    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }
    public ArrayList<Contestant> getContestants() {
        return contestants;
    }
    public void setContestants(ArrayList<Contestant> contestants) {
        this.contestants = contestants;
    }
}
