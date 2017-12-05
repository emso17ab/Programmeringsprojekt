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
        System.out.println("6) Afmeld dig fra holdet");
        System.out.println("7) Se Fuld Profil");
        System.out.println("0) Tilbage til Holdmenu ");
        System.out.println("Valg: ");

        switch (input.nextInt()){
            case 0: status = false;
                break;
            case 1: editCurrentContestantUsername(currentUser);
                break;
            case 2: editCurrentContestantPassword(currentUser);
                break;
            case 3: editCurrentContestantName(currentUser);
                break;
            case 4: editCurrentContestantEmail(currentUser);
                break;
            case 5: editCurrentContestantType(currentUser);
                break;
            case 6: deleteCurrentContestant(currentUser);
                break;
            case 7:printContestantInfo(currentUser);
                break;
            default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        }while(status);
    }

    private void deleteCurrentContestant(Contestant contestant) {
        System.out.println("OBS! Du er ved at slette din profil");
        System.out.println("Alt data vil gå tabt\nEr du sikker på du vil fortsætte? ");
        System.out.println("1) JA,  fortsæt og slet min profil");
        System.out.println("2) NEJ, tilbage til Min Side");
        switch (input.nextInt()){
            case 1:
                //for (User userToDelete : data.getAllUsers()) {
                    //if(userToDelete==);                }
                break;
            case 2:
                break;
                default:
        }
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

            System.out.println("du har valgt at ændre email");
            System.out.println("Skriv ny email:");
            contestant.setContestantEmail(input.nextLine());
            System.out.println("du har ændret din mail til:"+contestant.getContestantEmail());
    }

    private void editCurrentContestantName(Contestant contestant) {

            System.out.println("Du har valgt at ændre dit navn:");
            System.out.println("Skriv dit nye navn:");
            input.nextLine();
            contestant.setContestantName(input.nextLine());
            System.out.println("du har ændret dit navn til:" + contestant.getContestantName());
    }

    private void editCurrentContestantPassword(User contestant) {

            boolean status;
            String password;
            String check;
            input.nextLine();
            do{
            System.out.println("Du har valgt at ændre password");
            System.out.println("Skriv nyt password:");

            password = input.nextLine();
            System.out.println("Skriv nyt password igen");
            check = input.nextLine();


                if (password.equals(check)) {
                    contestant.setPassword(password);
                    System.out.println("du har ændret dit password til" + contestant.getPassword());
                    status = false;
                } else {
                    System.out.println("Fejl i indtastning");
                    status = true;
                }
            } while (status);
        }

    private void editCurrentContestantUsername(User contestant) {
            String newUsername;
            System.out.println("Du er ved at ændre brugernavn:");
            System.out.println("Skriv dit nye brugernavn");
            input.nextLine();
            newUsername=input.nextLine();
            contestant.setUsername(input.nextLine());
            System.out.println("Du har ændret brugernavn til:" +contestant.getUsername());

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
