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
                default:
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
//TODO metode
    }

    private void deleteContestant() {
//TODO metode
    }

    private void goToTeam(Company currentUser) {
        int i = 1, c = 1, valg;
        for (Team team : currentUser.getTeams()) {
            System.out.println(i + ")" + team.getTeamName());
            i++;
        }
        System.out.println("\nVælg hold:");
        valg = input.nextInt();
        for (Team team : currentUser.getTeams()) {
            if(valg == c) {
                TeamController teamController = new TeamController(data);
                teamController.teamRun(team);
                break;
            }else c++;
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


