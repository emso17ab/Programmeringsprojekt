package controllers;

import data.Data;
import models.Master;

import java.util.Scanner;

public class MasterController {
    //Denne controller skal styre funktioner som kun admin login kan udføre
    Data data;
    Scanner input = new Scanner(System.in);

    //CONSTRUCTOR
    public MasterController(Data data) {
        this.data = data;
    }




    public void masterRun(Master master) {
        boolean status = true;

        do {
            System.out.println("\n\tADMINISTRATOR MENU");
            System.out.println("\t1) Tilmeld din organisation");
            System.out.println("\t2) Hjælp");
            System.out.println("\t0) Log af");
            System.out.print("\n\tValg: ");

            switch (input.nextInt()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    createCompany();
                    break;
                case 2:
                    printHelp();
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);

    }

    //METHODS
    public void createCompany(){
        //Metoden opretter en ny virksomhed og tildeler denne et login. Derefter sendes man tilbage til Hovedmenuern og logger ind med de nye oplysninger

    }

    private void printHelp() {

    }


}
