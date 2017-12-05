package controllers;
import data.Data;
import models.Company;
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

        System.out.println("Følgende hold afventer godkendelse:");

        System.out.println("**********************************************************************************");
        System.out.println("Liste over hold der afventer godkendelse af dig");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-12s %-40s %-40s", "Index", "Holdnavn", "Holdkaptajn");
        int i = 1;
        for (Team team : data.getTeamsToBeApproved())
            if(team.getUserId().equals(currentUser.getUserId()+"x")) //Dette checker for hold der skal godkendes men kun dem som tilhører den current organisation
            System.out.printf("\n%-12s %-40s %-40s", i++, team.getTeamName(), team.getTeamLeader());
        System.out.println("\n**********************************************************************************");
        System.out.println("");

    }

    private void displayData() {

        System.out.println("\nHvad vil du have vist?");
        System.out.println("1) Oversigt over alle deltagende virksomheder");
        System.out.println("2) Dine hold");
        System.out.println("3) Information om et hold (Kræver holdID)");
        System.out.println("4) Information om en deltager (Kræver deltagerID)");
        System.out.println("5) Udskriv alle deltagere");

        switch (input.nextInt()) { //TODO DENNE SWITCH SKAL KALDE METODER I CASE'NE ISTEDET FOR AT INDEHOLDE METODEN

            case 1: {
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
            break;
            case 2:
                System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 3:
                System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 4:
                System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 5:
                System.out.println("Denne funktion er ikke oprette endnu"); //TODO
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
}