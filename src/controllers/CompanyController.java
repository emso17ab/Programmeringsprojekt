package controllers;
import data.Data;
import models.*;

import java.util.Scanner;
/*
Ansvarlige: Emil og Kesia
CompanyControlleren styrer menuer for Virksomhederne, som agerer som administrator for deres hold og holdenes deltagere
*/
public class CompanyController {
    Data data;
    Company currentUser;
    Team currentTeam;
    Scanner input = new Scanner(System.in);

    public CompanyController(Data data) {
        this.data = data;
    }

    //METHODS
    public void adminRun(Company currentUser) {
        boolean status = true;
        this.currentUser = currentUser;

        System.out.println("\nDU ER LOGGET IND SOM ADMINISTRATOR\n");

        do {
            System.out.println("\n\tVelkommen til menuen for: " + currentUser.getCompanyName());
            System.out.println("ORGANISATIONENS MENU");
            System.out.println("1) Opret Hold");
            System.out.println("2) Godkend Hold");
            System.out.println("3) Ændr Hold");
            System.out.println("4) Vis Data");
            System.out.println("5) Afmeld en deltager");
            System.out.println("6) Gå til Holdmenu");
            System.out.println("0) Log af ");
            System.out.println("Valg: ");

            switch (input.nextInt()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    createTeam();
                    break;
                case 2:
                    approveTeam();
                    break;
                case 3:
                    editTeam();
                    break;
                case 4:
                    displayData();
                    break;
                case 5:
                    deleteContestant();
                    break;
                case 6:
                    goToTeam(currentUser);
                    break;
                default:
                    System.out.println("\nFejl i indtastningen, prøv igen!\n");
            }
        } while (status);
    }

    private void createTeam() {
        /*
        Metoden er udarbejdet af Kesia.
         */
        String teamName, teamLeader, username, userId, password;
        boolean status = true;
        input.nextLine();

        System.out.println("Du har valgt at opprette ett hold");
        System.out.println("Indtast ønsket navn på holdet: "); //TODO Der bør checkes for ledighed så der ikke kan oprettes to hold med samme navn!
        teamName = input.nextLine();
        System.out.println("Indtast navn på holdkaptejn: ");
        teamLeader = input.nextLine();
        username = teamName + "123";
        userId = generateTeamID();


        System.out.println("Dit nye hold er oprettet med holdID: " + userId);
        System.out.println("HUSK at gemme dit holdID!! Nye deltagere til holdet skal bruge det til at registrere sig i systemet, første gang de logger ind");
        System.out.println("\n-med holdnavn: " + teamName);
        System.out.println("-med holdkaptajn: " + teamLeader);
        System.out.println("-med brugernavn: " + username);
        System.out.println("\nNu mangler du bare at vælge et kodeord der passer med brugernavnet, når du skal logge på med dit nye hold");
        System.out.println("Indtast et ønsket kodeord på mindst 6 karakterer (bogstaver og tal skal indgå)");
        System.out.println("Nyt kodeord: ");
        password = validatePassword(input.nextLine());

        if (password != null) {
            System.out.println("Bekræft kodeord: ");
            if (password.equals(input.nextLine())) {
                Team team = new Team(username, password,userId, teamName, teamLeader);
                data.addUserToList(team);
                currentUser.addTeamToCompany(team);
                System.out.println("Din bruger blev oprettet!\n");
            } else System.out.println("Din indtastning matchede ikke prøv igen");
        } else System.out.println("Fejl i indtastning. Prøv igen");
    }

    private String validatePassword(String password) {
        //Metoden returnerer brugerens indtastede ønsket kodeord såfremt kriterierne er opfyldt.
        if(password.length()>7 && password.matches(".*\\d.*"))
            return password;
        return null; //Hvis kriteriet ikke er opfyldt returnerer metoden null.
    }

    private String generateTeamID() {
        /*
        Metoden er udarbejdet af Emil.
        Metoden kaldes af "createTeam" og "approveTeamFromIndex" og genererer et unikt holdId.
        Metodens design er nærmere beskrevet i rapporten.
         */
        String companyId, userIdToString;
        int userId;

        companyId = currentUser.getUserId().substring(0,2);
        userId = currentUser.getTeams().size() + 1;
        userIdToString = String.valueOf(userId);

        if(userId < 10)
            return companyId + "0" + userIdToString + "00";
        else if(userId < 100)
            return companyId + userIdToString + "00";

        return null;
    }

    private void editTeam() {
        /*
        Metoden anvender først en anden metode (selectTeam) til at gemme et holdobjektet af det hold brugeren ønsker at ændre.
        Herefter vises en menu over forskellige funktioner til at ændre eller vise data om det valgte hold.
         */
        boolean status = true;
        System.out.println("Vælg et hold du ønsker at ændre");
        currentTeam = selectTeam(); //Brugeren vælger et hold i "selectTeam" som bliver gemt som en attribut af klassen.
        if (currentTeam != null) {
            do {
                System.out.println("\n\tÆNDR HOLD  >>" + currentTeam.getTeamName() + "<<"); //Brugeren for vist hvilket hold han/hun er igang med at ændre.
                System.out.println("1) Ændr Holdnavn");
                System.out.println("2) Ændre Holdkaptajn");
                System.out.println("3) Vis Holdprofil");
                System.out.println("4) Slette Holdet");
                System.out.println("0) Tilbage til Hovedmenu");

                switch (input.nextInt()) {
                    case 0:
                        status = false;
                        break;
                    case 1:
                        editTeamName();
                        break;
                    case 2:
                        editTeamLeader();
                        break;
                    case 3:
                        printTeamInfo(currentTeam);
                        break;
                    case 4:
                        deleteTeam();
                        break;
                    default:
                        System.out.println("\nFejl i indtastningen, prøv igen!\n");
                }
            } while (status && currentTeam != null);
        }
    }

    private void deleteTeam() {
        //Metoden sletter et hold fra virksomheden
        int indexChoice = currentUser.getTeams().indexOf(currentTeam); //Fanger indexnummeret af det valgte hold
        System.out.println("Er du sikker på du vil slette hold: " + currentTeam.getTeamName() + " ?");
        System.out.println("1) JA                 2) NEJ");
        if(input.nextInt() == 1) {
            data.getAllUsers().remove(data.getAllUsers().indexOf(currentTeam));
            currentUser.getTeams().remove(indexChoice);
            System.out.println("Hold '" + currentTeam.getTeamName() + "' er nu slettet fra programmet");
            currentTeam = null;
        }
    }

    private void editTeamName() {
        //Metoden ændrer det af brugeren valgte holds holdnavn
        input.nextLine();
        System.out.println("\nDet nuværende holdnavn er : " + currentTeam.getTeamName());
        System.out.println("\nSkriv jeres nye holdnavn: ");
        currentTeam.setTeamName(input.nextLine());
        System.out.println("\nDu har ændret holdnavnet til: " + currentTeam.getTeamName());
        System.out.println("");
    }

    private void editTeamLeader() {
        //Metoden ændrer det af brugeren valgte holds holdleder
        input.nextLine();
        System.out.println("\nDu er ved at ændre jeres Holdkaptajn: " + currentTeam.getTeamLeader());
        System.out.println("Hvem er jeres nye Holdkaptajn?");
        currentTeam.setTeamLeader(input.nextLine());
        System.out.println("Du har ændret jeres holdkaptajn til:" + currentTeam.getTeamLeader());
        System.out.println("");
    }

    private void printTeamInfo(Team team){
        //Metoden printer informationer om det valgte hold. Denne funktion kan bruges af brugeren til fx. at se de ændrede hold oplysninger.
        System.out.println("*****************************************************************************");
        System.out.println("Viser information om hold " + team.getUserId() + ": ");
        System.out.println("------------------------------------------------");
        System.out.println("Holdnavn: " + team.getTeamName());
        System.out.println("Holdkaptajn: " + team.getTeamLeader());
        System.out.println("Brugernavn: " + team.getUsername());
        System.out.println("Kodeord: " + team.getPassword());
        System.out.println("*****************************************************************************");
    }

    private void approveTeam() {
        /*
        Metoden er udarbejdet af Emil.
        Metoden tager brugeren igennem processen for godkendelse af hold der afventer på listen TeamsToBeApproved.
        Metoden er nærmere beskrevet i rapporten.
         */
        int k = 0;
        boolean status = true;

        do {
            //De første 3 linjer kode i denne metode checker først om der er nogle hold på listen som afventer godkendelse
            for (Team team : data.getTeamsToBeApproved())
                if (team.getUserId().equals(currentUser.getUserId() + "x"))
                    k++;

            //Hvis der blev fundet hold på listen kører metoden videre nedenfor, hvis ikke afsluttes den med en besked (linje 156)
            if(k > 0) {
                k = 0; //Dette nulstiller tælleren
                System.out.println("**********************************************************************************");
                System.out.println("Liste over hold der afventer godkendelse af dig");
                System.out.println("-----------------------------------------------------------------------");
                System.out.printf("%-12s %-40s %-40s", "Index", "Holdnavn", "Holdkaptajn");

                for (Team team : data.getTeamsToBeApproved())
                    if (team.getUserId().equals(currentUser.getUserId() + "x"))  //Dette checker for hold der skal godkendes men kun dem som tilhører den pågældende organisation
                        System.out.printf("\n%-12s %-40s %-40s", data.getTeamsToBeApproved().indexOf(team), team.getTeamName(), team.getTeamLeader());

                System.out.println("\n**********************************************************************************");
                System.out.println("");

                System.out.println("Hvad vil du foretage dig?\n");
                System.out.println("1) GODKEND et hold via index nr.");
                System.out.println("2) AFVIS et hold via index nr.");
                System.out.println("3) AFVIS alle hold på listen");
                System.out.println("\n0) Tilbage til hovedmenuen");

                switch (input.nextInt()) {
                    case 0:
                        status = false;
                        break;
                    case 1:
                        input.nextLine();
                        System.out.println("Indtast index nr.: ");
                        approveTeamFromIndex(input.nextInt()); //Dette gør at metoden kan genbruges af approveTeamAll metoden
                        break;
                    case 2:
                        input.nextLine();
                        System.out.println("Indtast index nr.: ");
                        rejectTeamFromIndex(input.nextInt()); //Dette gør at metoden kan genbruges af approveTeamFromIndex metoden
                        break;
                    case 3:
                        rejectTeamAll();
                        break;
                    default:
                }
            } else {
                System.out.println("**********************************************************************************");
                System.out.println("Du har ingen hold der afventer godkendelse");
                System.out.println("**********************************************************************************");
                status = false;
            }
        } while (status);
    }

    private void rejectTeamAll() {
        /*
        Metoden er tænkt til at skulle slette/afvise alle hold på den pågældende virksomheds liste over "Hold der afventer godkendelse"
        Metoden er ikke ordentligt implementeret i programmet. Dette er nærmere beskrevet i rapporten.
         */
        int index;

        for (Team team : data.getTeamsToBeApproved()) {
            if (team.getUserId().equals(currentUser.getUserId() + "x")) {
                data.getTeamsToBeApproved().remove(team);
            }
        }//TODO Denne metode skal rettes så det kører ordentligt!
    }

    private void rejectTeamFromIndex(int index) {
        //En simpel metode der sletter et hold fra TeamsToBeApproved-listen via et indeks indtastet af brugeren i "approveTeam" metoden.
        data.getTeamsToBeApproved().remove(index);
    }

    private void approveTeamFromIndex(int indexChoice) {
        /*
        Metoden er udarbejdet af Emil.
        Metoden godkender et hold fra TeamsToBeApproved-listen via et indeks indtastet af brugeren i "approveTeam" metoden.
        Metoden opretter holdet med det allerede valgte holdnavn og holdkaptajn og lader brugeren vælge et brugernavn samt kodeord,
        før holdet efterfølgende bliver gemt på både AllUsers-listen og virksomhedens liste over aktive hold.
         */
        Team teamToBeApproved;
        String username, password, teamId;

        teamToBeApproved = data.getTeamsToBeApproved().get(indexChoice);

        //OBS! Denne holdoprettelsesmetode tager ikke højde for kriterier for brugernavn og kodeord pga tidsbegrænsning!
        input.nextLine();
        System.out.println("Du har valgt at godkende hold: " + teamToBeApproved.getTeamName());
        System.out.println("\nDu skal vælge et brugernavn og et kodeord til holdet");
        System.out.println("\nVælg et brugernavn: ");
        username = input.nextLine();
        System.out.println("Vælg et kodeord: ");
        password = input.nextLine();
        teamId = generateTeamID();
        Team team = new Team(username, password, teamId, teamToBeApproved.getTeamName(), teamToBeApproved.getTeamLeader());
        data.addUserToList(team); //Holdet gemmes på listen over alle brugere.
        currentUser.addTeamToCompany(team); //Holdet gemmes på virksomhedens liste over aktive hold.
        System.out.println("Holdet blev oprettet med holdID: " + teamId);
        System.out.println("HUSK at gemme dit holdID!! " +
                "Nye deltagere til holdet skal bruge det til at registrere sig i systemet, første gang de logger ind");

        //Efter holdet er blevet godkendt og tilføjet til virksomhedens holdliste fjernes holdet fra teamsToBeApproved-listen
        rejectTeamFromIndex(indexChoice);
    }

    private void displayData() {
        /*
        Metoden giver brugeren en menu over forskellige typer data der kan vises.
        Herfra kaldes alle printmetoderne.
         */
        System.out.println("\nHvad vil du have vist?");
        System.out.println("1) Oversigt over alle deltagende virksomheder");
        System.out.println("2) Information om en deltager (Kræver deltagerID)");
        System.out.println("3) Vis deltagere og hold");

        switch (input.nextInt()) {

            case 1:
                printAllCompanies();
                break;
            case 2:
                printContestantByID();
                break;
            case 3:
                printAllContestants();
                break;
            default:
                System.out.println("Fejl i indtastning");
        }
    }

    private Team selectTeam() {
        /*
        Metoden er udarbejdet af Emil.
        Der checkes først om der er nogle hold på listen som afventer godkendelse
        Hvis der ikke findes nogle hold for virksomheden returnerer metoden null og en besked til brugeren om dette.
        Metodens design er yderligere beskrevet i rapporten.
        */
        boolean status;
        int choice;
        int k = currentUser.getTeams().size();

            //Printer en liste over alle hold Virksomheden har og returnerer det valgte hold
            if (k > 0) {
                System.out.printf("\n%-10s %-12s %-40s %-40s %5s\n", "Index", "HoldID", "Holdnavn", "Holdkaptajn", "Antal Deltagere");
                for (Team team : currentUser.getTeams())
                    System.out.printf("%-10s %-12s %-40s %-40s %5s\n", currentUser.getTeams().indexOf(team), team.getUserId(), team.getTeamName(), team.getTeamLeader(), currentUser.getTeams().size());

                do {
                    System.out.print("\nVælg hold ved at indtaste holdets index nr.: ");
                    choice = input.nextInt();

                    //Checker om brugeren har tastet et korrekt index ind, hvis ikke får brugeren en fejlmeddelelse
                    if (choice > k-1 || choice < 0) {
                        System.out.println("Et hold med index " + choice + " eksisterer ikke, prøv igen");
                        status = true;
                    } else {
                        status = false;
                    }
                }while(status);
                return currentUser.getTeams().get(choice);
            } else {
                System.out.println("**********************************************************************************");
                System.out.println("Du har ingen hold endnu!");
                System.out.println("**********************************************************************************");
                return null;
            }
    }

    private void deleteContestant() {
        /*
        Metoden er udarbejdet af Emil og William
        Metoden lader brugeren (virksomheden) slette/afmelde en deltager fra kampagnen.
        Metodens design er nærmere beskrevet i rapporten.
         */
        boolean status = false;
        Contestant selectedContestant = null;
        String contestantId;
        int choice;
        input.nextLine();

        printAllContestants();
        do {
            System.out.println("Vælg deltager som skal afmeldes kampagnen  < ANNULLER - TAST 0 >");
            System.out.println("Indtat deltagerID: ");
            contestantId = input.nextLine();
            for (User user : data.getAllUsers()) {
                if (user.getUserId().equals(contestantId) && user instanceof Contestant) {
                    selectedContestant = (Contestant) user;
                    currentTeam = findTeamOfContestant(contestantId);
                }
            }
            if (selectedContestant != null) {
                System.out.println("Er du sikker på, at du vil afmelde " + selectedContestant.getContestantName() + " permanent fra kampagnen?");
                System.out.println("1) JA       2)ANNULLER");
                choice = input.nextInt();
                if (choice == 1) {
                    currentTeam.getContestants().remove(currentTeam.getContestants().indexOf(selectedContestant)); //Sletter den valgte deltager fra Hold-listen
                    data.getAllUsers().remove(data.getAllUsers().indexOf(selectedContestant)); // Sletter den valgte deltager fra Master-listen
                    System.out.println(selectedContestant.getContestantName() + " er afmeldt 'Vi Cykler Til Arbejde' kampagnen");
                }
                else if (choice == 2)
                    System.out.println("Afmelding annulleret...");
                else {
                    System.out.println("Fejl i indtastningen, prøv igen");
                    status = true;
                }
            }
            else if(contestantId.equals("0")) {
                status = false;
            }
            else {
                System.out.println("Fejl i indtastningen, prøv igen");
                status = true;
            }
        }while(status);
    }

    private void goToTeam(Company currentUser) {
        /*
        Metoden er udarbejdet af Kesia
         */
        input.nextLine();
        if (currentUser.getTeams().size() < 1) {
            System.out.println("Du har ingen tilmeldte hold endnu!\nGå tilbage til hovedmenuen og opret dit første hold");
            System.out.println("Tilbage til hovedmenu (TRYK ENTER)");
            input.nextLine(); //Dette input har ingen funktion andet end at gå tilbage til hovedmenuen når brugeren trykker enter
        } else {
            int i = 1, c = 1, valg;
            for (Team team : currentUser.getTeams()) {
                System.out.println(i + ")" + team.getTeamName());
                i++;
            }
            System.out.println("Gå tilbage (TAST 0)");
            System.out.println("\nVælg hold:");
            valg = input.nextInt();
            for (Team team : currentUser.getTeams()) {
                if (valg == c) {
                    TeamController teamController = new TeamController(data);
                    teamController.teamRun(team);
                    break;
                } else c++;
            }
        }
    }

    private String findTeamNameOfContestant(String contestantId){
        //Metoden returnerer et holdnavn ud fra modtaget deltagerId
        String teamId = contestantId.substring(0,4) + "00";
        for (User user : data.getAllUsers())
            if(teamId.equals(user.getUserId()) && user instanceof Team)
                return ((Team) user).getTeamName();
        return null;
    }

    private Team findTeamOfContestant(String contestantId){
        //Metoden returnerer et holdobjekt ud fra modtaget deltagerId
        String teamId = contestantId.substring(0,4) + "00";
        for (User user : data.getAllUsers())
            if(teamId.equals(user.getUserId()) && user instanceof Team)
                return (Team) user;
        return null;
    }

    private void printAllCompanies(){
        //Metoden printer en liste over alle virksomheder der deltager i kampagnen og viser samtidig en holdfordeling ud på alle virksomhederne.
        int i = 0, teamTotal = 0;
        System.out.println("\n******************************************************************************");
        System.out.println("HOLDFORDELING  /  Liste over alle virksomheder der deltager i kampagnen");
        System.out.println("******************************************************************************\n");
        System.out.printf("%-5s %-40s %-6s %15s", "Nr", "Navn", "VirksomhedsID", "Antal Hold");
        for (User user : data.getAllUsers()) {
            if (user instanceof Company) {
                System.out.printf("\n%-5d %-40s %-6s %15d", i, ((Company) user).getCompanyName(), user.getUserId(), ((Company) user).getTeams().size());
                i++;
                teamTotal = teamTotal + ((Company) user).getTeams().size(); //Totale antal hold udregnes
            }
        }
        System.out.println("\n------------------------------------------------------------------------------");
        System.out.println("TOTAL                                                               " + teamTotal);
        System.out.println("******************************************************************************\n");
    }

    private void printContestantByID() {
        //Metoden udskriver oplysninger på specifik deltager såfremt brugeren kender deltagerens id og kodeord
        User activeUser = null;
        String contestantId, contestantPassword;
        input.nextLine();

        System.out.println("Indtast deltagerens ID: ");
        contestantId = input.nextLine();
        System.out.println("Indtast kodeord: ");
        contestantPassword = input.nextLine();

        for(User user : data.getAllUsers()) {
            if (contestantId.equals(user.getUserId()) && contestantPassword.equals(user.getPassword()) && user instanceof Contestant) {
                //"instance of.." kunne egentlig udelades, men er med alligevel for ekstra kontrol.
                System.out.println("*****************************************************************************");
                System.out.println("Viser information om bruger " + user.getUserId() + ": ");
                System.out.println("------------------------------------------------");
                System.out.println("Navn: " + ((Contestant) user).getContestantName());
                System.out.println("Email: " + ((Contestant) user).getContestantEmail());
                System.out.println("Type: " + ((Contestant) user).getContestantType());
                System.out.println("Brugernavn: " + user.getUsername());
                System.out.println("Kodeord: " + user.getPassword());
                System.out.println("*****************************************************************************");
                activeUser = user; //Den valgte deltager gemmes i et Userobjekt for at omgå fejlbesked til brugeren længere nede i koden.
            }
        }
        //Her bruges værdien i Userobjektet "activeUser" som condition til at udskrive fejlmeddelelse i tilfældet at de indtastede oplysninger ikke matchede.
        if(activeUser == null)
            System.out.println("Fejl i indtastet ID eller kodeord!");
    }

    private void printAllContestants(){
        //Metoden udskriver en liste over alle deltagere og hold for den indloggede bruger (virksomhed).
        String companyID = currentUser.getUserId().substring(0,2);
        System.out.println("**********************************************************************************");
        System.out.println("Liste over alle deltagere med hold i din virksomhed");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-12s %-20s %-25s %-30s %-15s", "DeltagerID", "Hold", "Navn", "Email", "Type");

        for (User user : data.getAllUsers())
            if(companyID.equals(user.getUserId().substring(0,2)) && user instanceof Contestant){
                System.out.printf("\n%-12s %-20s %-25s %-30s %-15s", user.getUserId(), findTeamNameOfContestant(user.getUserId()),
                        ((Contestant) user).getContestantName(), ((Contestant) user).getContestantEmail(),
                        ((Contestant) user).getContestantType());
            }
        System.out.println("\n**********************************************************************************");
        System.out.println("");
    }
}