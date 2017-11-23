package controllers;
import data.Data;
import models.Contestant;
import models.Team;
import models.User;

import java.util.Scanner;

//Team controlleren styrer menuerne for holdene

public class TeamController {
    Data data;
    Scanner input = new Scanner(System.in);
    Contestant currentContestant = null;

    public TeamController(Data data){
        this.data = data;

    }

    public void contestantRun(Contestant contestant){
        currentContestant = contestant;
        String testString = currentContestant.getUserId().substring(0,4) + "00";
        for (User user : data.getAllUsers()) {
            String test = (user.getUserId());
            if(test.equals(testString))
               teamRun((Team)user);
        }
    } //Denne metode

    public void teamRun(Team currentTeam)
    {
        boolean status = true;

        do {
            System.out.println("\n\tVELKOMMEN TIL HOLDSIDEN FOR: " + currentTeam.getTeamName());
            System.out.println("\t1) Nyt Hold");
            System.out.println("\t2) Ny Deltager");
            System.out.println("\t3) Vis Data");
            if(currentContestant != null)
            System.out.println("\t4) Min side");
            System.out.println("\t0) Log af");
            System.out.print("\n\tValg: ");

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
                    if(currentContestant != null)
                    goToCurrentContestant(currentContestant);
                    else System.out.println("\nFejl i indtastningen, prøv igen!\n");
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    private void goToCurrentContestant(Contestant currentContestant) {
        ContestantController contestantController = new ContestantController(data);
        contestantController.contestantRun(currentContestant);
    }

    private void displayData() {
//TODO metode
    }

    private void createContestant() {
//TODO metode
    }

    private void createTeamToBeApproved() {
//TODO metode
    }
}
