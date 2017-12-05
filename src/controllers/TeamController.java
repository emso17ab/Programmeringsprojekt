package controllers;
import data.Data;
import models.Company;
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
        for (User user : data.getAllUsers()) {
            String userTest = (user.getUserId());
            if(userTest.equals(contestant.getContestantTeamId()))
               teamRun((Team)user);
        }
    }

    public void teamRun(Team currentTeam) {
        boolean status = true;

        do {
            System.out.println("\n\tVELKOMMEN TIL HOLDSIDEN FOR: " + currentTeam.getTeamName());
            System.out.println("\t1) Nyt Hold");
            System.out.println("\t2) Vis Data");
            if(currentContestant != null)
            System.out.println("\t3) Min side");
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
                    displayData(currentTeam);
                    break;
                case 3:
                    if(currentContestant != null) {
                        ContestantController contestantController = new ContestantController(data);
                        contestantController.contestantRun(currentContestant);
                    } else System.out.println("\nFejl i indtastningen, prøv igen!\n");
                    break;
                default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    private void displayData(Team team) {
        System.out.println("\nHvad vil du have vist?");
        System.out.println("1) Oversigt over alle hold i din organisation");
        System.out.println("2) Udskriv alle deltagere på dit hold");
        switch (input.nextInt()) {
            case 1: displayAllTeams(team);
                break;
            case 2: displayAllContestants(team);
                break;
            default:
        }
    }

    private void displayAllTeams(Team team){
        System.out.println("**********************************************************************************");
        System.out.println("Liste over alle hold der er oprettet under samme organisation som dig");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-12s %-40s %-40s %5s", "HoldID", "Holdnavn", "Holdkaptajn", "Antal Deltagere");
        for (User user : data.getAllUsers())
            if (user.getUserId().equals(team.getTeamCompanyId()) && user instanceof Company)
                for (Team team1 : ((Company) user).getTeams())
                    System.out.printf("\n%-12s %-40s %-40s %5d", team1.getUserId(), team1.getTeamName(), team1.getTeamLeader(), team1.getContestants().size());
        System.out.println("\n**********************************************************************************");
        System.out.println("");
    }

    private void displayAllContestants(Team team){
        System.out.println("**********************************************************************************");
        System.out.println("Liste over alle deltagere på dit hold");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-30s %-15s", "DeltagerID", "Navn", "Email", "Type");
        for (Contestant contestant : team.getContestants())
            System.out.printf("\n%-12s %-25s %-30s %-15s", contestant.getUserId(), contestant.getContestantName(), contestant.getContestantEmail(), contestant.getContestantType());
        System.out.println("\n**********************************************************************************");
        System.out.println("");
    }

    private void createTeamToBeApproved() {
        String teamName, teamLeader;
        input.nextLine();

        System.out.println("Indtast navnet på det nye hold: ");
        teamName = input.nextLine();
        System.out.println("Indtast navnet på holdkaptajnen på det nye hold: ");
        teamLeader = input.nextLine();

        Team team = new Team(null, null, "NONE", teamName, teamLeader);
        data.addTeamToList(team);

        System.out.println("Tillykke! Dit hold er oprettet og afventer godkendelse af administrator!");
    }
}
