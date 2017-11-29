package controllers;
import data.Data;
import models.*;

import java.util.Scanner;

public class MasterController {
    //Denne controller skal styre funktioner som kun admin login kan udføre
    Data data;
    Scanner input = new Scanner(System.in);
    MainController mainController;

    //CONSTRUCTOR
    public MasterController(Data data) {
        this.data = data;
        this.mainController = new MainController();
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
        String companyName, username, password;
        boolean status = true;

        input.nextLine();

        System.out.println("Du er ved at tilmelde din organisation/virksomhed til kampagnen Vi Cykler På Arbejde!");
        System.out.println("Indtast venligst navnet på din organisation/virksomhed: ");
        companyName = input.nextLine();

        System.out.println("Du skal nu vælge et brugernavn");
        do
        {
            System.out.println("\nBrugernavnet skal bestå af mindst 6 karakterer og indeholde bogstaver og tal ");
            System.out.println("Hvis du ønsker at annullere oprettelsen TAST 0 som brugernavn");
            System.out.println("\nIndtast dit ønsket brugernavn: ");
            username = mainController.validateUsername(input.nextLine());

            if(username != null && username.equals("0"))
                status = false;

            if (username != null && status)
            {
                System.out.println("Du skal nu vælge et kodeord\nKodeordet skal bestå af mindst 8 karakterer og indeholde bogstaver og tal ");

                System.out.println("\nIndtast ønsket kodeord: ");
                password = mainController.validatePassword(input.nextLine());

                if (password != null) {
                    System.out.println("Bekræft kodeord: ");
                    if (password.equals(input.nextLine())) {
                     Company company = new Company(username, password, generateCompanyId(), companyName);
                        data.addUserToList(company);
                        System.out.println("Din bruger blev oprettet!\n");
                        status = false;

                    } else System.out.println("Din indtastning matchede ikke prøv igen");

                } else System.out.println("Fejl i indtastning. Prøv igen");

            } else {
                if (status)
                    System.out.println("Fejl i indtastning. Prøv igen");
            }

        } while (status);

    }

    private String generateCompanyId() {
        int i = 0;
        for (User user : data.getAllUsers())
            if (user instanceof Company)
                i++;
        if (i < 10)
            return String.valueOf(i + "0000");
        else if (i < 100)
            return String.valueOf("0" + i + "0000");

        return "##0000";
    }

    private void printHelp(){
            System.out.println("Hello HELP");
    }


}
