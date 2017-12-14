package controllers;
import java.util.Scanner;
import data.Data;
import models.*;

/*
Ansvarlige: William og Emil
Denne controllerklasse styrer Hovedmenuen som har alle login funktioner og viderestiller til de andre controllere
 */

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
        /*
        Denne metode kører hovedmenuen i programmet, hvorfra brugeren kan logge ind, oprette sig som ny bruger,
        printe alt data om alle brugere i programmet eller printe en kort hjælpetekst om programmet.
         */
        boolean runStatus = true;
        System.out.println("Velkommen til --> VI CYKLER PÅ ARBEJDE <--- programmet!\n");
        do {

            System.out.println("1) Log ind");
            System.out.println("2) Jeg er ny deltager");
            System.out.println("3) PRINT DATABASE");
            System.out.println("4) HJÆLP");
            System.out.println("0) Luk program");
            System.out.println("Valg: ");

            try { //En Try-Catch metode bliver brugt til at "fange" et muligt ugyldigt input fra brugeren, så programmet ikke lukker uventet.
                switch (input.nextInt()) {
                    case 0:
                        runStatus = runShutDown();
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
                    case 4:
                        printHelp();
                        break;
                    default:
                        System.out.println("\nFejl i indtastningen, prøv igen!\n");
                }
            }
            catch (Exception eObjekt){
                input.nextLine();
                System.out.println("\nFejl i Indtastningen");
                runStatus = true;
            }
        }while(runStatus);
    }

    public void authUser() {
        /*
        Metoden authUser er udarbejdet af Emil.
        Denne metode kører login-siden for brugeren.
         */
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

    }

    public void openUserMenu(User currentUser) {
        /*
        Metoden openUserMenu er udarbejdet af Emil.
        Metoden sender brugeren videre til den korrekte brugermenu alt efter logintype.
        Dette bestemmes ved hjælp af IF statements som checker currentUser-objektets instans af superklassen User.
         */

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
    }

    private void authTeam() {
        /*
        Metoden authTeam er udarbejdet af Emil.
        Metoden validerer via metoden (validateTeam) om brugerens input stemmer med et hold der eksisterer i systemet.
        Herefter sendes brugeren videre til generateUser eller hvis holdet ikke eksisterede får brugeren en fejlmeddelese.
         */
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
            validationStatus = validateTeam(teamId, teamPassword); //Valideringen bliver gemt som en boolean

            if (validationStatus) { //Her testes for valideringens resultat (True/false)
                System.out.println("Velkommen til hold " + currentTeam.getTeamName());
                generateUser();
                authTeamStatus = false;

            } else {
                System.out.println("Du har indtastet fokert holdId eller nøgleord, prøv igen!");
            }
        }while(authTeamStatus);
    }

    public void generateUser() {
        /*
        Denne metode tager brugeren igennem hele registreringsprocessen og opretter brugeren i systemet,
        såfremt alt indtata fra brugeren er blevet valideret og godkendt af systemet.
         */
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
        System.out.println("1) DEBUTANT");
        System.out.println("2) CRUISER");
        System.out.println("3) MESTER");
        System.out.println("4) ENTUSIAST");


        switch (input.nextInt()) {
            case 1:
                contestantType = "DEBUTANT";
                break;
            case 2:
                contestantType = "CRUISER";
                break;
            case 3:
                contestantType = "MESTER";
                break;
            case 4:
                contestantType = "ENTUSIAST";
                break;
            default:
                contestantType = "DEBUTANT";
        }
        input.nextLine();

            System.out.println("Du skal nu vælge et brugernavn");
        do
        {
            System.out.println("\nBrugernavnet skal bestå af mindst 6 karakterer og indeholde bogstaver og tal ");
            System.out.println("Hvis du ønsker at annullere oprettelsen TAST 0 som brugernavn");
            System.out.println("\nIndtast dit ønsket brugernavn: ");
            username = validateUsername(input.nextLine()); //validateUsername metoden benyttes til validering af det indtastet brugernavn.

                if(username != null && username.equals("0"))//Brugeren kan annullere oprettelsen ved at taste 0
                    status = false;

                if (username != null && status)
                {
                    System.out.println("Du skal nu vælge et kodeord\nKodeordet skal bestå af mindst 8 karakterer og indeholde bogstaver og tal ");

                        System.out.println("\nIndtast ønsket kodeord: ");
                        password = validatePassword(input.nextLine()); //validatePassword metoden benyttes til validering af det indtastet kodeord.

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
        /*
        Metoden validateTeam er udarbejdet af Emil.
        Metoden bruges af authTeam til at validere om et hold eksisterer i systemet.
        Konkret tager denne metode imod et holdId og et kodeord indtastet af brugeren i authTeam metoden
        og tester det med de eksisterende hold via et enhanced for-loop.
         */
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
        /*
        Metoden generateUserId er udarbejdet af Emil.
        Metoden genererer et unikt UserId til brugeren.
        Designet af denne metode er nærmere beskrevet i rapporten.
         */
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
    }

    public String validatePassword(String password) {
        //Metoden returnerer brugerens indtastede ønsket kodeord såfremt kriterierne er opfyldt.
        if(password.length()>7 && password.matches(".*\\d.*")) //Kodeordet skal indeholde bogstaver og bestå af mindst 8 tegn.
            return password;

        return null; //Hvis kriteriet ikke er opfyldt returnerer metoden null.
    }

    public String validateUsername(String username) {
        //Metoden returnerer brugerens indtastede ønsket brugernavn såfremt kriterierne er opfyldt.
        boolean status = true;

        for (User user : data.getAllUsers()) { //Der testes om brugernavnet eksisterer i systemet i forvejen
            if (username.equals(user.getUsername()))
                status = false;
        }
            if (status && username.length() > 5 && username.matches(".*\\d.*") || status && username.equals("0"))
                return username; //Brugernavnet "0" godkendes som undtagelse, da det betyder at brugeren annullerer sin oprettelse
            else System.out.println("Brugernavn er ikke ledigt, prøv igen!");

        return null;
    }

    private void printDatabase() {
        //Dette er en metode der kalder abstraktmetoden "displayData" for alle Userobjekter
        for (User user : data.getAllUsers()) {
            user.displayData();
        }
    }

    private void printHelp(){
        //Metoden printer en kort hjælpetekst til brug af programmet
        System.out.println("***********************************************************************************************************");
        System.out.println("-----------------------------------------------    HJÆLP    -----------------------------------------------");
        System.out.println("***********************************************************************************************************");
        System.out.println("Du starter med at logge ind som 'master'. Det er her du kan tilmelde din organisation til programmet" +
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

    private boolean runShutDown(){
        //Metoden kører nedlukningsprocessen hvor brugeren skal svare om han/hun er sikker på at programmet skal lukkes
        int choice;
        input.nextLine();
        System.out.println("************************************************");
        System.out.println("|                                              |");
        System.out.println("|   Er du sikker på, du vil lukke programmet?  |");
        System.out.println("|   ----------------     --------------        |");
        System.out.println("|     (1)OK               (2)ANNULLER          |");
        System.out.println("|   ----------------     --------------        |");
        System.out.println("|                                              |");
        System.out.println("************************************************");
        System.out.print("   Valg: ");
        choice = input.nextInt();

        if(choice == 1) {
            System.out.println("Programmet lukker ned...");
            return false;
        }
        else if (choice == 2){
            System.out.println("Går tilbage til hovedmenuen...");
            return true;
        }
        return true;
    }
}
