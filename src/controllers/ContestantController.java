package controllers;
import data.Data;
import java.util.Scanner;

//Contestantcontrolleren styrer menuerne for deltageren,

public class ContestantController {
    Data data;
    Scanner input = new Scanner(System.in);

    public ContestantController(Data data){
        this.data = data;
    }


    public void contestantRun() {
        System.out.println("DU ER LOGGET IND SOM DELTAGER");
        printContestantMenu();
    }

    public void printContestantMenu(){
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
