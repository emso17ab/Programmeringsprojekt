package models;
import data.Data;

import java.util.ArrayList;

public class Team extends User {
    protected String teamName;
    protected String teamLeader;
    protected ArrayList<Contestant> contestants;
    protected String teamCompanyId;

    public Team(String username, String password, String userId, String teamName, String teamLeader){
        super (username, password, userId);
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.contestants = new ArrayList<>();
        this.teamCompanyId = userId.substring(0,2) + "0000";
    }

//Abstract Method
    @Override
    public void displayData() {
        System.out.println("#" + userId + "_************************************************************************");
        System.out.println("<---------------HOLD--------------->");
        System.out.println("Holdnavn: " + teamName);
        System.out.println("Holdkaptajn: " + teamLeader);
        System.out.println("Antal deltagere: " + contestants.size() + "\n");
        System.out.println("");
    }

//Methods
    public void addContestantToTeam(Contestant newContestant){
        this.contestants.add(newContestant);
    }
    public void removeContestantFromTeam(Contestant contestant){
        this.contestants.remove(contestant);
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
    public String getTeamCompanyId() {
        return teamCompanyId;
    }
    public void setTeamCompanyId(String teamCompanyId) {
        this.teamCompanyId = teamCompanyId;
    }
}
