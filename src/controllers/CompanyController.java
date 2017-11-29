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

    public CompanyController(Data data){
        this.data = data;
    }

    public void adminRun(Company currentUser) {
        boolean status = true;
        this.currentUser = currentUser;

        System.out.println("\nDU ER LOGGET IND SOM ADMINISTRATOR\n");

        do{
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

            switch (input.nextInt()){
                case 0: status = false;
                    break;
                case 1: createTeam();
                    break;
                case 2: approveTeam();
                    break;
                case 3: editTeam();
                    break;
                case 4: displayData();
                    break;
                case 5: deleteContestant();
                    break;
                case 6: goToTeam(currentUser);
                    break;
                default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        }while(status);
    }

    private void createTeam() {
//TODO metode
    }

    private void approveTeam() {
//TODO metode
    }

    private void editTeam() {
//TODO metode
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
                System.out.printf("%-5s %-40s %5s", "Nr", "Navn", "Antal Hold");
                for (User user : data.getAllUsers()) {
                    if (user instanceof Company) {
                        System.out.printf("\n%-5d %-40s %5d", i, ((Company) user).getCompanyName(), ((Company) user).getTeams().size());
                        i++;
                    }
                }
                System.out.println("");
            } break;
            case 2: System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 3: System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 4: System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;
            case 5: System.out.println("Denne funktion er ikke oprette endnu"); //TODO
                break;

        }
    }

    private void deleteContestant() {
//TODO metode
    }

    private void goToTeam(Company currentUser) {
        input.nextLine();
        if (currentUser.getTeams().size() < 1) {
            System.out.println("Du har ingen tilmeldte hold endnu!\nGå tilbage til hovedmenuen og opret dit første hold");
            System.out.println("Tilbage til hovedmenu (TRYK ENTER)");
            input.nextLine(); //Dette input har ingen funktion andet end at gå tilbage til hovedmenuen når brugeren trykker enter
        }
        else {
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

    /*
    1) createTeam (denne metode skal oprette et nyt hold og derefter printe team ID'et som bruges til at logge ind med og tilføje deltagere til holdet
    Eksempel:  "Dit hold er blevet oprettet! [Navn: SuperCyklerne,  HoldKaptajn: Simon Hansen]"
                Holdets ID nummer er: [1030100] og nøgleordet er [hansensis]
                HUSK AT GEMME ID OG NØGLEORD - Det skal bruges til at logge ind på holdets side


    2) approveTeam

TODO editTeam menu med funktioner
    3) editTeam
        3.1 editTeamName
        3.2 editTeamLeader
        3.3 deleteTeam

    5) displayData

    6) deleteContestant


     */


