package controllers;
import data.Data;
import models.Company;
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


    public void contestantRun(Contestant currentUser) {
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
//TODO metode
    }

        private void editCurrentContestantEmail() {
//TODO metode
    }

        private void editCurrentContestantName() {
//TODO metode
    }

        private void editCurrentContestantPassword() {
//TODO metode
    }

        private void editCurrentContestantUsername() {
//TODO metode
    }
}


