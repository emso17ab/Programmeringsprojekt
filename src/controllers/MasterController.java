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
                default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
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

    private void printHelp(){
        System.out.println("***********************************************************************************************************");
        System.out.println("-----------------------------------------------    HJÆLP    -----------------------------------------------");
        System.out.println("***********************************************************************************************************");
            System.out.println("Du er logget ind som 'master'. Det er her du kan tilmelde din organisation til programmet" +
                    "\nStart med at vælge 'Tilmeld din organisation' fra hovedmenuen. " +
                    "\n\nNår du har tilmeldt din organisation er det tid til at oprette dit første hold. \nStart " +
                    "med at logge ud af 'masteren' som du er inde på nu. \nHerefter logger du ind igen, men denne gang med " +
                    "dit nye virksomhedslogin. \n\nDu kommer nu til en menu hvor du kan vælge at oprette nye hold." +
                    "\nNår du har oprettet de hold dine medarbejdere har ønsket at deltage med og gemt de tilhørende holdID-numre " +
                    "\nkan dine medarbejdere begynde at oprette sig selv som deltager på deres respektive hold. \n\nDette gør de " +
                    "fra hovedmenuen ved at vælge 'Ny deltager' hvorefter de skal indtaste det holdId \nderes hold har fået tildelt." +
                    " Derefter er det nemt at oprette sig som deltager, og dine medarbejdere er nu \nmed i kampagnen!" +
                    "\n\nDer er også mulighed for at logge ind som et helt hold. Dette giver adgang til de samme ting som " +
                    "\nhvis man loggede ind som deltager, på nær menupunktet 'Min Side' som er personligt for hver deltager." +
                    "\n\nPå 'Min Side' kan man ændre i sine personlige oplysninger samt vise forskellige data over andre hold " +
                    "\neller deltagere der er med i kampagnen. \n\nRigtig god fornøjelse! ");
        System.out.println("\n************************************************************************************************************");
    }


}
