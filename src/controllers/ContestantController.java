package controllers;
import data.Data;
import models.Contestant;
import models.Team;
import models.User;

import java.util.Scanner;

//Contestantcontrolleren styrer menuerne for deltageren

public class ContestantController {
    Data data;
    Scanner input = new Scanner(System.in);

    public ContestantController(Data data) {
        this.data = data;
    }




    public void teamRun(Team currentUser) {
        boolean status = true;

        do {
            System.out.println("\n\tVELKOMMEN TIL HOLDSIDEN FOR: " + currentUser.getTeamName());
            System.out.println("\t1) Nyt Hold");
            System.out.println("\t2) Ny Deltager");
            System.out.println("\t3) Vis Data");
            System.out.println("\t0) Log af");
            System.out.print("\n\tIndtast valg: ");

            switch (input.nextInt()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    createTeamToBeApproved();
                    break;
                case 2:
                    createContestant();
                    break;
                case 3:
                    displayData();
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    public void contestantRun(Contestant currentUser) {
        boolean status = true;

        do {
            System.out.println("\n\tVELKOMMEN TIL HOLDSIDEN FOR: " + currentUser.getContestantName()); //TODO getTeamName i stedet for Contestant!
            System.out.println("\t1) Nyt Hold");
            System.out.println("\t2) Ny Deltager");
            System.out.println("\t3) Vis Data");
            System.out.println("\t4) Min side");
            System.out.println("\t0) Log af");
            System.out.print("\n\tIndtast valg: ");

            switch (input.nextInt()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    createTeamToBeApproved();
                    break;
                case 2:
                    createContestant();
                    break;
                case 3:
                    displayData();
                    break;
                case 4:
                    goToCurrentContestant(currentUser);
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    private void createTeamToBeApproved() {
    }

    private void createContestant() {
    }

    private void displayData() { //Abstrakt metode fra superklassen User
    }

    private void goToCurrentContestant(Contestant currentUser) {
        boolean status = true;

        do{
        System.out.println("\n\tVelkommen til menuen for cykelrytter: " + currentUser.getContestantName());
        System.out.println("DELTAGER MENU");
        System.out.println("1) Ændr Brugernavn");
        System.out.println("2) Ændr Kodeord");
        System.out.println("3) Ændr Navn");
        System.out.println("4) Ændr Email");
        System.out.println("5) Skift Cyklist type");
        System.out.println("0) Tilbage til Holdmenu ");

        switch (input.nextInt()){
            case 0: status = false;
                break;
            case 1: editCurrentContestantUsername();
                break;
            case 2: editCurrentContestantPassword();
                break;
            case 3: editCurrentContestantName();
                break;
            case 4: editCurrentContestantEmail();
                break;
            case 5: editCurrentContestantType();
                break;
            default:
            }
        }while(status);
    }

        private void editCurrentContestantType() {

    }

        private void editCurrentContestantEmail() {

    }

        private void editCurrentContestantName() {

    }

        private void editCurrentContestantPassword() {

    }

        private void editCurrentContestantUsername() {

    }
}


