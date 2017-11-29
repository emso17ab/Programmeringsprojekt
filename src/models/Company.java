package models;
import java.util.ArrayList;

public class Company extends User {
    protected String companyName;
    protected int numberOfTeams;
    protected ArrayList<Team> teams;

    public Company(String username, String password, String userId, String companyName){
        super (username, password, userId);
        this.companyName = companyName;
        this.teams = new ArrayList<>();
    }

//Abstract Method
    @Override
    public void displayData() {
        System.out.println("#" + userId + "_************************************************************************");
        System.out.println("<---------------VIRKSOMHED--------------->");
        System.out.println("Navn: " + companyName);
        System.out.println("Antal hold: " + teams.size() + "\n");
        System.out.println("");
    }

//Methods
    public void addTeamToCompany(Team newTeam){
        this.teams.add(newTeam);
    }

//Getter and Setter Methods
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public int getNumberOfTeams() {
        return numberOfTeams;
    }
    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
