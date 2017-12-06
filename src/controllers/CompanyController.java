package controllers;
import data.Data;
import models.Company;
import models.Contestant;
import models.Team;
import models.User;

import java.util.Scanner;

//Admincontrolleren styrer menuer for Virksomhederne, som agerer som administrator for deres hold og holdenes deltagere

public class CompanyController {
    Data data;
    Company currentUser;
    Scanner input = new Scanner(System.in);

    public CompanyController(Data data) {
        this.data = data;
    }

    public void adminRun(Company currentUser) {
        boolean status = true;
        this.currentUser = currentUser;

        System.out.println("\nDU ER LOGGET IND SOM ADMINISTRATOR\n");

        do {
            System.out.println("\n\tVelkommen til menuen for: " + currentUser.getCompanyName());
            System.out.println("ORGANISATIONENS MENU");
            System.out.println("1) Opret Hold");
            System.out.println("2) Godkend Hold");
            System.out.println("3) Ændr Hold");
            System.out.println("4) Vis Data");
            System.out.println("5) Afmeld en deltager");
            System.out.println("6) Gå til Holdmenu");
            System.out.println("0) Log af ");
            System.out.println("Valg: ");

            switch (input.nextInt()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    createTeam();
                    break;
                case 2:
                    approveTeam();
                    break;
                case 3:
                    editTeam();
                    break;
                case 4:
                    displayData();
                    break;
                case 5:
                    deleteContestant();
                    break;
                case 6:
                    goToTeam(currentUser);
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    private void createTeam() {
        String teamName, teamLeader, username, userId, password;
        boolean status = true;
        input.nextLine();

        System.out.println("Du har valgt at opprette ett hold");
        System.out.println("Indtast ønsket navn på holdet: "); //TODO Der bør checkes for ledighed så der ikke kan oprettes to hold med samme navn!
        teamName = input.nextLine();
        System.out.println("Indtast navn på holdkaptejn: ");
        teamLeader = input.nextLine();
        username = teamName + "123";
        userId = generateTeamID();


        System.out.println("Dit nye hold er oprettet med holdID: " + userId);
        System.out.println("HUSK at gemme dit holdID!! Nye deltager til holdet skal bruge det til at registrere sig i systemet, første gang de logger ind");
        System.out.println("\n-med holdnavn: " + teamName);
        System.out.println("-med holdkaptajn: " + teamLeader);
        System.out.println("-med brugernavn: " + username);
        System.out.println("\nNu mangler du bare at vælge et kodeord der passer med brugernavnet, når du skal logge på med dit nye hold");
        System.out.println("Indtast et ønsket kodeord på mindst 6 karakterer (bogstaver og tal skal indgå)");
        System.out.println("Nyt kodeord: ");
        password = validatePassword(input.nextLine());

        if (password != null) {
            System.out.println("Bekræft kodeord: ");
            if (password.equals(input.nextLine())) {
                Team team = new Team(username, password,userId, teamName, teamLeader);
                data.addUserToList(team);
                currentUser.addTeamToCompany(team);
                System.out.println("Din bruger blev oprettet!\n");
            } else System.out.println("Din indtastning matchede ikke prøv igen");
        } else System.out.println("Fejl i indtastning. Prøv igen");
    }
    private String validatePassword(String password) {
        if(password.length()>7 && password.matches(".*\\d.*"))
            return password;
        return null;
    }
    private String generateTeamID() {
        String companyId, userIdToString;
        int userId;

        companyId = currentUser.getUserId().substring(0,2);
        userId = currentUser.getTeams().size() + 1;
        userIdToString = String.valueOf(userId);

        if(userId < 10)
            return companyId + "0" + userIdToString + "00";
        else if(userId < 100)
            return companyId + userIdToString + "00";

        return null;
    }

    private void editTeam() {
        System.out.println("Du har valgt at endre på dit hold");
        /*
        Switch()
        3.1 editTeamName
        3.2 editTeamLeader
        3.3 deleteTeam
         */
//TODO metode
    }

    private void approveTeam() { //TODO Man skal kunne vælge hvilke hold der skal godkendes
        int k = 0;
        boolean status = true;

        //De første 3 linjer kode (137-139) i denne metode checker først om der er nogle hold på listen som afventer godkendelse
        for (Team team : data.getTeamsToBeApproved())
            if (team.getUserId().equals(currentUser.getUserId() + "x"))
                k++;

        //Hvis der blev fundet hold på listen kører metoden videre nedenfor, hvis ikke afsluttes den med en besked (linje 156)
        if(k > 0) {
            do {
                System.out.println("**********************************************************************************");
                System.out.println("Liste over hold der afventer godkendelse af dig");
                System.out.println("-----------------------------------------------------------------------");
                System.out.printf("%-12s %-40s %-40s", "Index", "Holdnavn", "Holdkaptajn");

                for (Team team : data.getTeamsToBeApproved())
                    if (team.getUserId().equals(currentUser.getUserId() + "x"))  //Dette checker for hold der skal godkendes men kun dem som tilhører den current organisation
                        System.out.printf("\n%-12s %-40s %-40s", data.getTeamsToBeApproved().indexOf(team), team.getTeamName(), team.getTeamLeader());

                System.out.println("\n**********************************************************************************");
                System.out.println("");

                System.out.println("Hvad vil du foretage dig?\n");
                System.out.println("1) GODKEND et hold via index nr.       3) AFVIS et hold via index nr.");
                System.out.println("2) GODKEND alle hold på listen         4) AFVIS alle hold på listen");
                System.out.println("\n0) Tilbage til hovedmenuen");

                switch (input.nextInt()) {
                    case 0:
                        status = false;
                        break;
                    case 1:
                        approveTeamFromIndex();
                        break;
                    case 2:
                        approveTeamAll();
                        break;
                    case 3:
                        rejectTeamFromIndex();
                        break;
                    case 4:
                        rejectTeamAll();
                        break;
                    default:
                }
            } while (status);
        }
        else {
            System.out.println("Du har ingen hold der afventer godkendelse");
        }

    }

    private void rejectTeamAll() {
    }

    private void rejectTeamFromIndex() {
    }

    private void approveTeamAll() {
    }

    private void approveTeamFromIndex() {

    }

    private void displayData() {

        System.out.println("\nHvad vil du have vist?");
        System.out.println("1) Oversigt over alle deltagende virksomheder");
        System.out.println("2) Information om et hold (Kræver holdID)");
        System.out.println("3) Information om en deltager (Kræver deltagerID)");
        System.out.println("4) Vis deltagere og hold");

        switch (input.nextInt()) { //TODO DENNE SWITCH SKAL KALDE METODER I CASE'NE ISTEDET FOR AT INDEHOLDE METODEN

            case 1:
                printAllCompanies();
            break;
            case 2:
                printTeamByID();
                break;
            case 3:
                printContestantByID();
                break;
            case 4:
                printAllContestants();
                break;

        }
    }


    private void deleteContestant() {


    }

    private void goToTeam(Company currentUser) {
        input.nextLine();
        if (currentUser.getTeams().size() < 1) {
            System.out.println("Du har ingen tilmeldte hold endnu!\nGå tilbage til hovedmenuen og opret dit første hold");
            System.out.println("Tilbage til hovedmenu (TRYK ENTER)");
            input.nextLine(); //Dette input har ingen funktion andet end at gå tilbage til hovedmenuen når brugeren trykker enter
        } else {
            int i = 1, c = 1, valg;
            for (Team team : currentUser.getTeams()) {
                System.out.println(i + ")" + team.getTeamName());
                i++;
            }
            System.out.println("Gå tilbage (TAST 0)");
            System.out.println("\nVælg hold:");
            valg = input.nextInt();
            for (Team team : currentUser.getTeams()) {
                if (valg == c) {
                    TeamController teamController = new TeamController(data);
                    teamController.teamRun(team);
                    break;
                } else c++;
            }
        }
    }

    private String findTeamOfContestant(String contestantID){
        String teamId = contestantID.substring(0,4) + "00";
        for (User user : data.getAllUsers())
            if(teamId.equals(user.getUserId()) && user instanceof Team)
                return ((Team) user).getTeamName();
        return null;
    }

    private void printAllCompanies(){
        int i = 0;
        System.out.println("Liste over alle virksomheder der deltager i kampagnen");
        System.out.printf("%-5s %-40s %-6s %15s", "Nr", "Navn", "VirksomhedsID", "Antal Hold");
        for (User user : data.getAllUsers()) {
            if (user instanceof Company) {
                System.out.printf("\n%-5d %-40s %-6s %15d", i, ((Company) user).getCompanyName(), user.getUserId(), ((Company) user).getTeams().size());
                i++;
            }
        }
        System.out.println("");
    }

    private void printContestantByID() {
        User activeUser = null;
        String contestantId, contestantPassword;
        input.nextLine();

        System.out.println("Indtast deltagerens ID: ");
        contestantId = input.nextLine();
        System.out.println("Indtast kodeord: ");
        contestantPassword = input.nextLine();

        for(User user : data.getAllUsers()) {
            if (contestantId.equals(user.getUserId()) && contestantPassword.equals(user.getPassword()) && user instanceof Contestant) {
                System.out.println("*****************************************************************************");
                System.out.println("Viser information om bruger " + user.getUserId() + ": ");
                System.out.println("------------------------------------------------");
                System.out.println("Navn: " + ((Contestant) user).getContestantName());
                System.out.println("Email: " + ((Contestant) user).getContestantEmail());
                System.out.println("Type: " + ((Contestant) user).getContestantType());
                System.out.println("Brugernavn: " + user.getUsername());
                System.out.println("Kodeord: " + user.getPassword());
                System.out.println("*****************************************************************************");
                activeUser = user;
            }
        }
        if(activeUser == null)
            System.out.println("Fejl i indtastet ID eller kodeord!");
    }

    private void printTeamByID() {
    }

    private void printAllContestants(){
        String companyID = currentUser.getUserId().substring(0,2);
        System.out.println("**********************************************************************************");
        System.out.println("Liste over alle deltagere med hold i din virksomhed");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-12s %-20s %-25s %-30s %-15s", "DeltagerID", "Hold", "Navn", "Email", "Type");

        for (User user : data.getAllUsers())
            if(companyID.equals(user.getUserId().substring(0,2)) && user instanceof Contestant){
                System.out.printf("\n%-12s %-20s %-25s %-30s %-15s", user.getUserId(), findTeamOfContestant(user.getUserId()),
                        ((Contestant) user).getContestantName(), ((Contestant) user).getContestantEmail(),
                        ((Contestant) user).getContestantType());
            }
        System.out.println("\n**********************************************************************************");
        System.out.println("");
    }


}