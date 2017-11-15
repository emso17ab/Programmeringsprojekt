package controllers;
import data.Data;
import models.Company;

import java.util.Scanner;

public class GuestController {
    Data data;
    Scanner input = new Scanner(System.in);

    public GuestController(Data data){
        this.data = data;
    }


    public void run() {
        System.out.println("DU ER LOGGET IND SOM GÆST");
    }

    /*
    public void printGuestMenu() {
        int choice;
        boolean status = true;

        do {
            System.out.println("\n\tGUEST MENU");
            System.out.println("\t1) Opret Hold");
            System.out.println("\t2) Tilmeld Deltager");
            System.out.println("\t0) Log af");
            System.out.print("\tIndtast valg: ");
            choice = input.nextInt();

            switch(choice){
                case 0: status = false;
                    break;
                case 1: createTeam();
                    break;
                case 2: createContestant();
                    break;
                default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }


        }while(status);
    }
    */

    private void createContestant() {

    }

    /*
    private void createTeam() {
        int scenario = 0;
        boolean status = true;
        String teamName, teamLeader, teamId, companyName, companyId;
        Company currentCompany;

        do {

            if(scenario == 0) {
                input.nextLine();
                System.out.println("Vil du oprette et nyt hold til en...");
                System.out.println("1) ny Virksomhed");
                System.out.println("2) eksisterende Virksomed");
                System.out.print("\nIndtast valg: ");

                switch (input.nextInt()) {
                    case 1:
                        scenario = 1;
                        break;
                    case 2:
                        scenario = 2;
                        break;
                    default:
                }
            }

            if(scenario ==1){
                System.out.println("\nIndtast navnet på den virksomhed du vil oprette et hold under: ");
                companyName = input.nextLine();
            }

            if(scenario == 2) {

                data.printAlleHold();

                System.out.print("\nIndtast holdNr.: ");
                currentCompany = data.getSpecificCompany(input.nextInt());

                System.out.println("Indtast holdnavn: ");
                teamName = input.nextLine();
                System.out.println("Indtast holdkaptajn: ");
                teamCaptain = input.nextLine();

                teamId = generateTeamId(currentCompany);

                Hold hold = new Hold(teamName, teamCaptain, teamId);
                data.addTeam(hold);

                companyId = generateCompanyId();
                Virksomhed virksomhed = new Virksomhed(currentCompany.getName(), teamId, hold);

                for (Hold hold1 : data.getAlleHold()) {
                    System.out.println("holdnavn: " + hold1.getHoldNavn() + "Holdkaptajn: " + hold1.getHoldKaptajn());
                }
            }

        }while(status);


    }
    */

}

