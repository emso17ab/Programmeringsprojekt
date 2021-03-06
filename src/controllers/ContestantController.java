package controllers;
import data.Data;
import models.Contestant;
import models.Team;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;
/*
Ansvarlige: Rasmus og Emil
Contestantcontrolleren styrer menuerne for deltageren
*/
public class ContestantController {
    Data data;
    Scanner input = new Scanner(System.in);
    Contestant currentUser;

    public ContestantController(Data data) {
        this.data = data;
    }

    //METHODS
    public void contestantRun(Contestant currentUser) {
        this.currentUser = currentUser;
        boolean status = true;

        do{
        System.out.println("\n\tVelkommen til menuen for cykelrytter: " + currentUser.getContestantName());
        System.out.println("DELTAGER MENU");
        System.out.println("1) Ændr Brugernavn");
        System.out.println("2) Ændr Kodeord");
        System.out.println("3) Ændr Navn");
        System.out.println("4) Ændr Email");   // menu
        System.out.println("5) Skift Cyklist type");
        System.out.println("6) Afmeld dig fra holdet");
        System.out.println("7) Se Fuld Profil");
        System.out.println("0) Tilbage til Holdmenu ");
        System.out.println("Valg: ");
try{
    switch (input.nextInt()) {
        case 0:
            status = false;
            currentUser = null; //programmet køres så længe status er true
            break;
        case 1:
            editCurrentContestantUsername(currentUser);
            break;
        case 2:
            editCurrentContestantPassword(currentUser);
            break;
        case 3:
            editCurrentContestantName(currentUser);
            break;
        case 4:
            editCurrentContestantEmail(currentUser);      // metoder kaldes
            break;
        case 5:
            editCurrentContestantType(currentUser);
            break;
        case 6:
            status = deleteCurrentContestant(currentUser);
            break;
        case 7:
            printContestantInfo(currentUser);
            break;
        default:
            System.out.println("\nFejl i indtastningen, prøv igen!\n");
    }
}
    catch(Exception oObject){input.nextLine(); //sikrer fejl i input
        System.out.println("Fejl i Indtastningen!");}
        }while(status);
    }

    private boolean deleteCurrentContestant(User contestant) {
        /*
        Metoden er udarbejdet af Emil.
        Metoden sletter den indloggede bruger fra systemet
         */
        int choice;
        String teamId = contestant.getUserId().substring(0,4) + "00";

        System.out.println("OBS! Du er ved at slette din profil");
        System.out.println("Alt data vil gå tabt\nEr du sikker på du vil fortsætte? ");
        System.out.println("1) JA,  fortsæt og slet min profil");
        System.out.println("2) NEJ, tilbage til Min Side");
        choice = input.nextInt();
        if (choice == 1) {
            data.getAllUsers().remove(contestant); //Først slettes deltageren fra hovedlisten...
            for (User user : data.getAllUsers())
                if(teamId.equals(user.getUserId()) && user instanceof Team)
                    ((Team) user).removeContestantFromTeam((Contestant) contestant); //-herefter bliver deltageren slettet fra Holdlisten
            currentUser = null;
            return false;
        }
        else if (choice == 2){
            System.out.println("Går tilbage til Min side...");
        }
        return true;
    }

    private void editCurrentContestantType(Contestant contestant) {
        //Metoden er udarbejdet af Rasmus.
        String newType;
        System.out.println("Du har valgt at ændre deltagertype");
        System.out.println("Din nuværende type er: " + contestant.getContestantType());
        System.out.println("Vælg ny type fra listen:\n");
        System.out.println("1) DEBUTANT");
        System.out.println("2) CRUISER");
        System.out.println("3) MESTER");
        System.out.println("4) ENTUSIAST");
        switch (input.nextInt()) {
            case 1:
                 newType = "DEBUTANT";
                break;
            case 2:
                newType = "CRUISER"; // ny type vælges
                break;
            case 3:
                newType = "MESTER";
                break;
            case 4: newType = "ENTUSIAST";
                break;
            default: newType = contestant.getContestantType();
        }
        contestant.setContestantType(newType);
        System.out.println("Din type er blevet ændret!\nNy type: " + contestant.getContestantType());
        System.out.println("Tilbage til Min Side (TAST ENTER)"); //anvender set metode til at ændre Type.
        input.nextLine();
        input.nextLine(); //Denne linje kode er så ovenstående besked passer og brugeren skal trykke ENTER
    }

    private void editCurrentContestantEmail(Contestant contestant) {
        //Metoden er udarbejdet af Rasmus.
            System.out.println("du har valgt at ændre email");
            System.out.println("Skriv ny email:");
            contestant.setContestantEmail(input.nextLine()); // anvender set-metode til at ændre variablen.
            System.out.println("du har ændret din mail til: "+contestant.getContestantEmail());
    }

    private void editCurrentContestantName(Contestant contestant) {
        //Metoden er udarbejdet af Rasmus.
        input.nextLine();
        System.out.println("Skriv det nye navn: ");
        contestant.setContestantName(input.nextLine()); //anvender set metode til at ændre navn.
        System.out.println("Du har ændret dit navn til : " + contestant.getContestantName());
    }

    private void editCurrentContestantPassword(User contestant) {
        //Metoden er udarbejdet af Rasmus.
            boolean status;
            String password;
            String check;
            input.nextLine();
            do{
            System.out.println("Du har valgt at ændre password");
            System.out.println("Skriv nyt password:");

            password = input.nextLine(); //anvender set metode til at ændre password.
            System.out.println("Skriv nyt password igen");
            check = input.nextLine();


                if (password.equals(check)) { //her er et fejlcheck, der sørger for nye password trykkes korrekt.
                    contestant.setPassword(password);
                    System.out.println("du har ændret dit password til: " + contestant.getPassword());
                    status = false;
                } else {
                    System.out.println("Fejl i indtastning");
                    status = true;
                }
            } while (status);
        }

    private void editCurrentContestantUsername(User contestant) {
        //Metoden er udarbejdet af Rasmus.
        input.nextLine();
        System.out.println("Du er ved at ændre brugernavn:");
        System.out.println("Skriv dit nye brugernavn"); // anvender set metode til at ændre brugernavn.
        contestant.setUsername(input.nextLine());
        System.out.println("Du har ændret brugernavn til: " + contestant.getUsername());
    }

    private void printContestantInfo(Contestant contestant) {
        /*
        Metoden er udarbejdet af Rasmus.
        Metoden printer information om den indloggede bruger
         */
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

    public boolean checkIfDeleted() {
        /*
        Metoden bruges af MainControlleren til at stoppe do-while lykken og dermed til at logge ud a programmet
        Metoden kaldes kun hvis den indloggede bruger har valgt at afmelde sig kampagnen og dermed slette sin profil fra systemet
         */
        return currentUser != null;
    }
}
