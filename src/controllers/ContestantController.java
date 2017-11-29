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
        System.out.println("6) Afmeld din bruger");
        System.out.println("0) Tilbage til Holdmenu ");
        System.out.println("Valg: ");

        switch (input.nextInt()){
            case 0: status = false;
                break;
            case 1: editCurrentContestantUsername(currentUser);
                    printContestantInfo(currentUser);
                break;
            case 2: editCurrentContestantPassword(currentUser);
                    printContestantInfo(currentUser);
                break;
            case 3: editCurrentContestantName(currentUser);
                    printContestantInfo(currentUser);
                break;
            case 4: editCurrentContestantEmail(currentUser);
                    printContestantInfo(currentUser);
                break;
            case 5: editCurrentContestantType(currentUser);
                    printContestantInfo(currentUser);
                break;
            case 6: deleteCurrentContestant(currentUser);
                    printContestantInfo(currentUser);
                break;
            default:
            }
        }while(status);
    }

    private void deleteCurrentContestant(Contestant contestant) {
    }

    private void editCurrentContestantType(Contestant contestant) {
        String newType;
        System.out.println("Du har valgt at ænder deltagetype");
        System.out.println("Din nuværende type er: " + contestant.getContestantType());
        System.out.println("Vælg ny type fra listen:\n");
        System.out.println("1) BEGYNDER");
        System.out.println("2) ØVET");
        System.out.println("3) PROFESSIONEL");
        switch (input.nextInt()) {
            case 1:
                 newType = "BEGYNDER";
                break;
            case 2:
                newType = "ØVET";
                break;
            case 3:
                newType = "PROFESSIONEL";
                break;
            default: newType = contestant.getContestantType();
        }
        contestant.setContestantType(newType);
        System.out.println("Din type er blevet ændret!\nNy type: " + contestant.getContestantType());
        System.out.println("Tilbage til Min Side (TAST ENTER)");
        input.nextLine();
        input.nextLine(); //Denne linje kode er så ovenstående besked passer og brugeren skal trykke ENTER
    }

        private void editCurrentContestantEmail(Contestant contestant) {
//TODO metode
    }

        private void editCurrentContestantName(Contestant contestant) {
//TODO metode
    }

        private void editCurrentContestantPassword(Contestant contestant) {
//TODO metode
    }

        private void editCurrentContestantUsername(Contestant contestant) {
//TODO metode
    }

    private void printContestantInfo(Contestant contestant){
        System.out.println("*****************************************************************************");
        System.out.println("Viser information om bruger " + contestant.getUserId() + ": ");
        System.out.println("------------------------------------------------");
        System.out.println("Navn: " + contestant.getContestantName());
        System.out.println("Email: " + contestant.getContestantEmail());
        System.out.println("Type: " + contestant.getContestantType());
        System.out.println("Brugernavn: " + contestant.getUsername());
        System.out.println("Kodeord: " + contestant.getPassword());
        System.out.println("*****************************************************************************");
    }
}