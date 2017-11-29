package data;
import models.*;

import java.util.ArrayList;

public class Data {
    private ArrayList<User> allUsers;
    private ArrayList<Team> teamsToBeApproved;


    public Data(){
        this.allUsers = new ArrayList<>();
        this.teamsToBeApproved = new ArrayList<>();
        generateData();
    }

    public void generateData(){

        //Masteradministratoren oprettes af typen company. Denne er unik og der kan ikke oprettes andre mastere når programmet kører
        Master adminMaster = new Master("admin","admin","000000","VCPA");

        //Seks testdeltagere bliver oprettet
        Contestant contestant1 = new Contestant("emil", "1234", "010101", "Emil Sørensen", "emil.soerensen@hotmail.com", "BEGYNDER");
        Contestant contestant2 = new Contestant("rasmus", "1234", "010102", "Rasmus Pold", "emil.soerensen@hotmail.com", "BEGYNDER");
        Contestant contestant3 = new Contestant("william", "1234", "010103", "William Sørensen", "emil.soerensen@hotmail.com", "ØVET");
        Contestant contestant4 = new Contestant("kesia", "1234", "010201", "Kesia Heiberg", "emil.soerensen@hotmail.com", "ØVET");
        Contestant contestant5 = new Contestant("donald", "1234", "010202", "Donald Trump", "emil.soerensen@hotmail.com", "PROFESSIONEL");
        Contestant contestant6 = new Contestant("heino", "1234", "010203", "Heino Hansen", "emil.soerensen@hotmail.com", "PROFESSIONEL");

        //To testhold bliver oprettet og der lægges tre testdeltagere ind på hvert hold
        Team team1 = new Team("team1", "0001", "010100","SuperCyklerne", "Rasmus Pold");
        Team team2 = new Team("team2", "0002", "010200", "PowerBikers", "Kesia Heiberg");

        team1.addContestantToTeam(contestant1);
        team1.addContestantToTeam(contestant2);
        team1.addContestantToTeam(contestant3);
        team2.addContestantToTeam(contestant4);
        team2.addContestantToTeam(contestant5);
        team2.addContestantToTeam(contestant6);

        //En testvirksomhed bliver oprettet og de to hold bliver lagt ind på denne
        Company company1 = new Company("company1", "001", "010000", "Copenhagen Business School");

        company1.addTeamToCompany(team1);
        company1.addTeamToCompany(team2);

        //Til sidst bliver alle deltagerne, hold og virksomhed lagt ind på Masterlisten allUsers som alle har fælles superklasse af typen User.

        allUsers.add(adminMaster);
        allUsers.add(company1);

        allUsers.add(team1);
        allUsers.add(team2);

        allUsers.add(contestant1);
        allUsers.add(contestant2);
        allUsers.add(contestant3);
        allUsers.add(contestant4);
        allUsers.add(contestant5);
        allUsers.add(contestant6);

    }


//Methods

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void addUserToList(User user){
        this.allUsers.add(user);
    }

    public void addTeamToList (Team team){
        this.teamsToBeApproved.add(team);
    }

}



