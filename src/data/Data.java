package data;
import models.*;

import java.util.ArrayList;
/*
Ansvarlige: Kesia og Rasmus
 */

public class Data {
    private ArrayList<User> allUsers;
    private ArrayList<Team> teamsToBeApproved;


    public Data() {
        this.allUsers = new ArrayList<>();
        this.teamsToBeApproved = new ArrayList<>();
        generateData();
    }

    public void generateData() {

        //Masteradministratoren oprettes af typen company. Denne er unik og der kan ikke oprettes andre mastere når programmet kører
        Master adminMaster = new Master("admin", "admin", "000000", "VCPA");

        //Testdeltagere bliver oprettet
        Contestant contestant1 = new Contestant("emil", "1234", "010101", "Emil Sørensen", "emil@hotmail.com", "DEBUTANT");
        Contestant contestant2 = new Contestant("rasmus", "1234", "010102", "Rasmus Pold", "rasmus@hotmail.com", "DEBUTANT");
        Contestant contestant3 = new Contestant("william", "1234", "010103", "William Sørensen", "william@hotmail.com", "CRUISER");
        Contestant contestant4 = new Contestant("inge-lise", "1234", "010104", "Inge-Lise Salomon", "inge-lise@hotmail.com", "CRUISER");
        Contestant contestant5 = new Contestant("per", "1234", "010105", "Per Monsen", "per@hotmail.com", "CRUISER");
        Contestant contestant6 = new Contestant("kesia", "1234", "010201", "Kesia Heiberg", "kesia@hotmail.com", "CRUISER");
        Contestant contestant7 = new Contestant("donald", "1234", "010202", "Donald Trump", "donald@duck.com", "DEBUTAT");
        Contestant contestant8 = new Contestant("heino", "1234", "010203", "Heino Hansen", "heino@hotmail.com", "CRUISER");
        Contestant contestant9 = new Contestant("helena", "1234", "010204", "Helene Frank", "helena@hotmail.com", "MESTER");
        Contestant contestant10 = new Contestant("jørgen", "1234", "010205", "Jørgen Theisen", "jørgen@hotmail.com", "DEBUTANT");
        Contestant contestant11 = new Contestant("ola", "1234", "020101", "Ola Nordmann", "nordmann@hotmail.com", "MESTER");
        Contestant contestant12 = new Contestant("micky", "1234", "020102", "Micky Mouse", "micky@hotmail.com", "CRUISER");
        Contestant contestant13 = new Contestant("minnie", "1234", "020103", "Minnie Mouse", "minnie@hotmail.com", "CRUISER");
        Contestant contestant14 = new Contestant("barack", "1234", "020104", "Barack Obamab", "barack@rmvp.com", "MESTER");
        Contestant contestant15 = new Contestant("michelle", "1234", "020105", "Michelle Obamab", "michelle@rmvp.com", "MESTER");

        //Testhold bliver oprettet og der lægges testdeltagere ind på hvert hold
        Team team1 = new Team("team1", "0001", "010100", "SuperCyklerne", "Rasmus Pold");
        Team team2 = new Team("team2", "0002", "010200", "PowerBikers", "Kesia Heiberg");
        Team team3 = new Team("team3", "0003", "020100", "DreamTeamNorway", "Ola Nordmann");

        team1.addContestantToTeam(contestant1);
        team1.addContestantToTeam(contestant2);
        team1.addContestantToTeam(contestant3);
        team1.addContestantToTeam(contestant4);
        team1.addContestantToTeam(contestant5);
        team2.addContestantToTeam(contestant6);
        team2.addContestantToTeam(contestant7);
        team2.addContestantToTeam(contestant8);
        team2.addContestantToTeam(contestant9);
        team2.addContestantToTeam(contestant10);
        team3.addContestantToTeam(contestant11);
        team3.addContestantToTeam(contestant12);
        team3.addContestantToTeam(contestant13);
        team3.addContestantToTeam(contestant14);
        team3.addContestantToTeam(contestant15);

        //Testvirksomheder bliver oprettet og de to hold bliver lagt ind på denne
        Company company1 = new Company("company1", "001", "010000", "Copenhagen Business School");
        Company company2 = new Company("company2", "002", "020000", "DØKDØK17");

        company1.addTeamToCompany(team1);
        company1.addTeamToCompany(team2);
        company2.addTeamToCompany(team3);

        //Til sidst bliver alle deltagerne, hold og virksomhed lagt ind på Masterlisten allUsers som alle har fælles superklasse af typen User.

        allUsers.add(adminMaster);
        allUsers.add(company1);
        allUsers.add(company2);

        allUsers.add(team1);
        allUsers.add(team2);
        allUsers.add(team3);

        allUsers.add(contestant1);
        allUsers.add(contestant2);
        allUsers.add(contestant3);
        allUsers.add(contestant4);
        allUsers.add(contestant5);
        allUsers.add(contestant6);
        allUsers.add(contestant7);
        allUsers.add(contestant8);
        allUsers.add(contestant9);
        allUsers.add(contestant10);
        allUsers.add(contestant11);
        allUsers.add(contestant12);
        allUsers.add(contestant13);
        allUsers.add(contestant14);
        allUsers.add(contestant15);

        //Tre testTeams bliver oprettet under virksomheden "Copenhagen Business School"
        Team testTeam1 = new Team(null, null, "010000x", "Cykkelmyggene", "Louise Nielsen");
        Team testTeam2 = new Team(null, null, "010000x", "Kanldperlerne", "Lone Larsen");
        Team testTeam3 = new Team(null, null, "010000x", "VæltepeterBikers", "Kasper Jørgensen");

        //De tre testTeams bliver lagt på listen over hold der skal godkendes
        addTeamToList(testTeam1);
        addTeamToList(testTeam2);
        addTeamToList(testTeam3);

    }


//Methods

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }
    public void addUserToList(User user) {
        this.allUsers.add(user);
    }

    public ArrayList<Team> getTeamsToBeApproved(){
        return teamsToBeApproved;
    }
    public void addTeamToList(Team team) {
        this.teamsToBeApproved.add(team);
    }
}

