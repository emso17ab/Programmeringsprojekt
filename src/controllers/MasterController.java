package controllers;
import data.Data;
import models.*;

import java.util.Scanner;
/*
Ansvarlig: William og Rasmus
Denne controller styrer funktioner som kun masteren med adminlogin kan udføre.
 */
public class MasterController {
    //D
    Data data;
    Scanner input = new Scanner(System.in);
    MainController mainController;

    //CONSTRUCTOR
    public MasterController(Data data) {
        this.data = data;
        this.mainController = new MainController();
    }

    //METHODS
    public void masterRun(Master master) {
        boolean status = true;
//mastermenu
        do {
            System.out.println("\n\tADMINISTRATOR MENU");
            System.out.println("\t1) Tilmeld din organisation");
            System.out.println("\t0) Log af");
            System.out.print("\n\tValg: ");
try {
    switch (input.nextInt()) {
        case 0:
            status = false;
            break;
        case 1:
            createCompany(); // kalder createCompany metoden
            break;
        default:
            System.out.println("\nFejl i indtastningen, prøv igen!\n");
    }
}catch(Exception eObject){input.nextLine(); // fanger fejl i input
    System.out.println("Fejl i indtastningen");}


        } while (status);

    }

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

            if (username != null && status) //fortsætter hvis brugeren ikke vil lukke program
            {
                System.out.println("Du skal nu vælge et kodeord\nKodeordet skal bestå af mindst 8 karakterer og indeholde bogstaver og tal ");

                System.out.println("\nIndtast ønsket kodeord: ");
                password = mainController.validatePassword(input.nextLine());

                if (password != null) {
                    System.out.println("Bekræft kodeord: "); //tjekker password
                    if (password.equals(input.nextLine())) {
                     Company company = new Company(username, password, generateUserId(), companyName);
                        data.addUserToList(company);
                        System.out.println("Din bruger blev oprettet!\n");
                        status = false;

                    } else System.out.println("Din indtastning matchede ikke prøv igen"); //fejlmelding hvis ustemmighed

                } else System.out.println("Fejl i indtastning. Prøv igen");

            } else {
                if (status)
                    System.out.println("Fejl i indtastning. Prøv igen");
            }

        } while (status); //præmissen for at programmet bliver ved med at køre

    }

    private String generateUserId() {
        //Metoden genererer et unikt virksomhedsID.
        int i = 1;
        for (User user : data.getAllUsers())
            if (user instanceof Company)
                i++;
        if (i < 10)
            return String.valueOf("0" + i + "0000");
        else if (i < 100)
            return String.valueOf(i + "0000");

        return "##0000";
    }
}