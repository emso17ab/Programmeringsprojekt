package controllers;
import java.util.Scanner;
import data.Data;
import models.Company;
import models.Contestant;
import models.Team;
import models.User;

//Denne controllerklasse skal styre Hovedmenuen som har alle login funktioner og viderestiller til de andre controllere

public class MainController {
    private User currentUser;
    private Team currentTeam;
    private Data data;
    Scanner input = new Scanner(System.in);

    public MainController() {
        this.data = new Data();
    }

    //METHODS
    public void run() {
        System.out.println("Velkommen til --> VI CYKLER PÅ ARBEJDE <--- programmet!\n");
        System.out.println("1) Log ind");
        System.out.println("2) Jeg er ny deltager");
        System.out.println("Valg: ");

        switch (input.nextInt()){
            case 1:
                authUser();
                openUserMenu(currentUser);
                break;
            case 2: authTeam();
                break;
            default:
        }
    }

    public void authUser() {
        String username;
        String password;
        boolean status = true;

        input.nextLine();

        do { /* HER har jeg lavet et do-while loop, der sørger for at koden kører så længe
                boolean "status" er true.
            */

            System.out.print("\nIndtast Brugernavn: ");
            username = input.nextLine();
            System.out.print("\nIndtast Password: ");
            password = input.nextLine();

            for (User user : data.getAllUsers()) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    currentUser = user;
                    status = false; //Her sættes status til false, således at løkken ikke kører længere
                }
            }
        }while(status);

    }  //Login siden

    public void openUserMenu(User currentUser) {

        if (currentUser instanceof Contestant) {
            System.out.println("Velkommen til programmet " + ((Contestant) currentUser).getContestantName() + "!");
            ContestantController contestantController = new ContestantController(data);
            contestantController.contestantRun((Contestant) currentUser);

        }else if (currentUser instanceof Team){
            System.out.println("Velkommen til programmet hold: " + ((Team) currentUser).getTeamName() + "!");
            ContestantController contestantController = new ContestantController(data);
            contestantController.teamRun((Team)currentUser);

        } else if (currentUser instanceof Company) {
            System.out.println("Velkommen til programmet! Du er logget ind som virksomhed: " + ((Company) currentUser).getCompanyName());
            CompanyController companyController = new CompanyController(data);
            companyController.adminRun((Company)currentUser);
        }
    }  //Sender brugeren videre til den korrekte hovedmenu alt efter logintype

    private void authTeam() {
        String teamId, teamPassword;
        boolean validationStatus;

        input.nextLine();

        System.out.println("For at oprette dig som bruger i systemet kræver det at din organisation/virksomhed har oprettet et hold til dig");
        System.out.println("\nIndtast det 7-cifret holdId: ");
        teamId = input.next();
        System.out.println("Indtast nøgleord: ");
        teamPassword = input.next();
        validationStatus = validateTeam(teamId, teamPassword);

        if (validationStatus) {
            System.out.println("Velkommen til hold " + currentTeam.getTeamName());
            generateUser();

        } else {
            System.out.println("Du har indtastet fokert holdId eller nøgleord, prøv igen!");
        }
    }

    public void generateUser() {
        String username, password;
        String contestantName, contestantEmail, contestantType;
        boolean status = true;

        input.nextLine();

        System.out.println("Du er ved at oprette dig som ny deltager på holdet");
        System.out.println("\nIndtast venligst dit navn: ");
        contestantName = input.nextLine();
        System.out.println("Indtast venligst din email: ");
        contestantEmail = input.nextLine();

        System.out.println("Vælg hvilken cykeltype der passer bedst til dig!");
        System.out.println("1) BEGYNDER");
        System.out.println("2) ØVET");
        System.out.println("3) PROFESSIONEL");

        switch (input.nextInt()) {
            case 1: contestantType = "BEGYNDER";
                break;
            case 2: contestantType = "ØVET";
                break;
            case 3: contestantType = "PROFESSIONEL";
                break;
            default: contestantType = "BEGYNDER";
        }

        do
        {
            System.out.println("Du skal nu vælge et brugernavn\nBrugernavnet skal bestå af mindst 6 karakterer og indeholde bogstaver og tal ");
            System.out.println("Hvis du ønsker at annullere oprettelsen TAST 0 som brugernavn");
            System.out.println("Indtast dit ønsket brugernavn: ");
            username = validateUsername(input.nextLine());

                if(username != null && username.equals("0"))
                    status = false;

                if (username != null && status)
                {
                    System.out.println("Du skal nu vælge et kodeord\nKodeordet skal bestå af mindst 8 karakterer og indeholde bogstaver og tal ");

                        System.out.println("Indtast ønsket kodeord: ");
                        password = validatePassword(input.nextLine());

                        if (password != null) {
                            System.out.println("Indtast ønsket kodeord igen: ");
                            if (password.equals(input.nextLine())) {
                                Contestant contestant = new Contestant(username, password, "00", contestantName, contestantEmail, contestantType);
                                currentTeam.addContestantToTeam(contestant);
                                System.out.println("Din bruger blev oprettet!");
                                status = false;

                            } else System.out.println("Din indtastning matchede ikke prøv igen");

                        } else System.out.println("Fejl i indtastning. Prøv igen");

                } else {
                    if (status)
                    System.out.println("Fejl i indtastning. Prøv igen");
                }

        } while (status);

    }

    private boolean validateTeam(String teamId, String teamPassword) {
        for (User user : data.getAllUsers()){
            if (teamId.equals(user.getUserId()) && teamPassword.equals(user.getPassword())){
                if(user instanceof Team) {
                    currentTeam = (Team) user;
                    return true;
                }
            }
        } return false;
    }

    private String generateUserId() {
        String newUserId;
        int companies = 0, teams = 0, contestants = 0;

        for (User user : data.getAllUsers()) {
            if (user instanceof Company)
                companies++;
            else if (user instanceof Team)
                teams++;
            else if (user instanceof Contestant)
                contestants++;
        }
        newUserId = String.valueOf(String.valueOf(companies) + String.valueOf(teams) + String.valueOf(contestants));
        System.out.println(newUserId);
        return newUserId;
    } //Virker ikke endnu!

    private String validatePassword(String password) {
        if(password.length()>7 && password.matches(".*\\d.*"))
            return password;

        return null;
    }

    public String validateUsername(String username){
        if(username.length()>5 && username.matches(".*\\d.*"))
        return username;

        if(username.equals("0"))
            return username;

        else return null;
    }
}
