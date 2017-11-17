package controllers;
import data.Data;
import models.User;

import java.util.Scanner;

//Contestantcontrolleren styrer menuerne for deltageren,

/*
VELKOMMEN TIL HOLDSIDEN FOR "Team Superyklerne"
HOVEDMENU

1) createTeamToBeApproved

2) createContestant

3) displayData

4) goToCurrentContestant (Denne funktion går til den aktuelle deltagers egen menu)

    Velkommen til menuen for cykelrytter: "Hans Hansen"
    DELTAGER MENU
    1) editCurrentContestantUsername
    2) editCurrentContestantPassword
    3) editCurrentContestantName
    4) editCurrentContestantEmail
    5) editCurrentContestantType

    OBS! Hvis der logges ind med et holdID og ikke et deltagerId skal menuen goToCurrentContestant IKKE vises!
    Istedet skal man lave en ny deltager (createContestant) og derigennem få oprettet et personlig deltagerId og login


 */

public class ContestantController {
    Data data;
    Scanner input = new Scanner(System.in);

    public ContestantController(Data data){
        this.data = data;
    }


    public void contestantRun(User currentUser){
    int choice;
    boolean status = true;

        do {
        System.out.println("\n\tDELTAGER MENU");
        System.out.println("\t1) Ændr Hold");
        System.out.println("\t2) Ændr Deltager");
        System.out.println("\t3) Vis Deltageroplysninger");
        System.out.println("\t4) Vis Holdoplysninger");
        System.out.println("\t0) Log af");
        System.out.print("\n\tIndtast valg: ");
        choice = input.nextInt();

        switch (choice) {
            case 0:
                status = false;
                break;
            case 1: changeTeam();
                break;
            case 2: changeContestant();
                break;
            case 3: printContestantData();
                break;
            case 4: printTeamData();
                break;
            default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        }while(status);
    }
    private void printTeamData() {

    }
    private void printContestantData() {

    }
    private void changeContestant() {

    }
    private void changeTeam() {

    }
}
