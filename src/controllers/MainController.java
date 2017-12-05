package controllers;
import java.util.Scanner;
import data.Data;
import models.*;

//Denne controllerklasse skal styre Hovedmenuen som har alle login funktioner og viderestiller til de andre controllere

public class MainController {
    private User currentUser;
    public Team currentTeam;
    private Data data;
    Scanner input = new Scanner(System.in);

    public MainController() {
        this.data = new Data();
    }

    //METHODS
    public void run() {
        boolean runStatus = true;
        System.out.println("Velkommen til --> VI CYKLER PÅ ARBEJDE <--- programmet!\n");
        do {
            System.out.println("1) Log ind");
            System.out.println("2) Jeg er ny deltager");
            System.out.println("3) PRINT DATABASE");
            System.out.println("0) Luk program");
            System.out.println("Valg: ");

            switch (input.nextInt()) {
                case 0:
                    System.out.println("Programmet lukker ned...");
                    runStatus = false;
                    break;
                case 1:
                    authUser();
                    openUserMenu(currentUser);
                    break;
                case 2:
                    authTeam();
                    break;
                case 3:
                    printDatabase();
                    break;
                default: System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        }while(runStatus);
    }

    public void authUser() {
        String username;
        String password;
        boolean status = true;
        int i = 3;

        input.nextLine();

        do { /* HER har jeg lavet et do-while loop, der sørger for at koden kører så længe
                boolean "status" er true.
            */
            if(i > 0) {
                System.out.print("\nIndtast Brugernavn: ");
                username = input.nextLine();
                System.out.print("\nIndtast Password: ");
                password = input.nextLine();

                currentUser = null;

                for (User user : data.getAllUsers()) {
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        currentUser = user;
                        status = false; //Her sættes status til false, således at løkken ikke kører længere
                    }
                }
                if (currentUser == null && i > 1)
                    System.out.println("\nForkert indtastet brugernavn eller adgangskode!\n" + --i + " Forsøg tilbage - Prøv igen");
                else if(i == 1) {
                    System.out.println("\nForkert indtastet brugernavn eller adgangskode!\nDu har ikke flere forsøg!\n");
                    status = false;
                }
            }
            else status = false;
        }while(status);

    }  //Login siden

    public void openUserMenu(User currentUser) {

        if (currentUser instanceof Contestant) {
            System.out.println("Velkommen til programmet " + ((Contestant) currentUser).getContestantName() + "!");
            TeamController teamController = new TeamController(data);
            teamController.contestantRun((Contestant)currentUser);

        }else if (currentUser instanceof Team){
            System.out.println("Velkommen til programmet hold: " + ((Team) currentUser).getTeamName() + "!");
            TeamController teamController = new TeamController(data);
            teamController.teamRun((Team)currentUser);

        } else if (currentUser instanceof Company) {
            System.out.println("Velkommen til programmet! Du er logget ind som virksomhed: " + ((Company) currentUser).getCompanyName());
            CompanyController companyController = new CompanyController(data);
            companyController.adminRun((Company)currentUser);

        } else if (currentUser instanceof Master) {
            MasterController masterController = new MasterController(data);
            masterController.masterRun((Master) currentUser);
        }
    }  //Sender brugeren videre til den korrekte hovedmenu alt efter logintype

    private void authTeam() {
        String teamId, teamPassword;
        boolean validationStatus;
        boolean authTeamStatus = true;

        input.nextLine();

        System.out.println("For at oprette dig som bruger i systemet kræver det at din organisation/virksomhed har oprettet et hold til dig");
        do {
            System.out.println("\nIndtast det 6-cifret holdId: ");
            teamId = input.next();
            System.out.println("Indtast nøgleord: ");
            teamPassword = input.next();
            validationStatus = validateTeam(teamId, teamPassword);

            if (validationStatus) {
                System.out.println("Velkommen til hold " + currentTeam.getTeamName());
                generateUser();
                authTeamStatus = false;

            } else {
                System.out.println("Du har indtastet fokert holdId eller nøgleord, prøv igen!");
            }
        }while(authTeamStatus);
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
            case 1:
                contestantType = "BEGYNDER";
                break;
            case 2:
                contestantType = "ØVET";
                break;
            case 3:
                contestantType = "PROFESSIONEL";
                break;
            default:
                contestantType = "BEGYNDER";
        }
        input.nextLine();

            System.out.println("Du skal nu vælge et brugernavn");
        do
        {
            System.out.println("\nBrugernavnet skal bestå af mindst 6 karakterer og indeholde bogstaver og tal ");
            System.out.println("Hvis du ønsker at annullere oprettelsen TAST 0 som brugernavn");
            System.out.println("\nIndtast dit ønsket brugernavn: ");
            username = validateUsername(input.nextLine());

                if(username != null && username.equals("0"))
                    status = false;

                if (username != null && status)
                {
                    System.out.println("Du skal nu vælge et kodeord\nKodeordet skal bestå af mindst 8 karakterer og indeholde bogstaver og tal ");

                        System.out.println("\nIndtast ønsket kodeord: ");
                        password = validatePassword(input.nextLine());

                        if (password != null) {
                            System.out.println("Bekræft kodeord: ");
                            if (password.equals(input.nextLine())) {
                                Contestant contestant = new Contestant(username, password, generateUserId(currentTeam), contestantName, contestantEmail, contestantType);
                                currentTeam.addContestantToTeam(contestant);
                                data.addUserToList(contestant);
                                System.out.println("Din bruger blev oprettet! - Dit personlige ID-nummer er: " + contestant.getUserId() + "\n");
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

    private String generateUserId(Team currentTeam) {
        int newContestant;
        String contestantNo = "##";

        newContestant = currentTeam.getContestants().size() + 1;

        //Konverterer integer til String og holder det i korrekt format for et userId på 6 cifre
        if(newContestant < 10)
            contestantNo = "0" + String.valueOf(newContestant);
        else if(newContestant < 100)
            contestantNo = String.valueOf(newContestant);

        //Genererer userID'et, printer og returnerer.
        return currentTeam.getUserId().substring(0,4) + contestantNo;


    } //Genererer et unikt UserId til brugeren

    public String validatePassword(String password) {
        if(password.length()>7 && password.matches(".*\\d.*"))
            return password;

        return null;
    }

    public String validateUsername(String username) {
        boolean status = true;

        for (User user : data.getAllUsers()) {
            if (username.equals(user.getUsername()))
                status = false;
        }
            if (status && username.length() > 5 && username.matches(".*\\d.*") || status && username.equals("0"))
                return username;
            else System.out.println("Brugernavn er ikke ledigt, prøv igen!");

        return null;
    }

    private void printDatabase() {
        for (User user : data.getAllUsers()) {
            user.displayData();
        }
    }
}
