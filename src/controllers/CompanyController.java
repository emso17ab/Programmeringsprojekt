package controllers;
import data.Data;

//Admincontrolleren styrer menuer for Virksomhederne, som agerer som administrator for deres hold og holdenes deltagere

public class CompanyController {
    Data data;

    public CompanyController(Data data){
        this.data = data;
    }


    public void adminRun() {
        System.out.println("DU ER LOGGET IND SOM ADMINISTRATOR");
    }

    /*
    1) createTeam (denne metode skal oprette et nyt hold og derefter printe team ID'et som bruges til at logge ind med og tilføje deltagere til holdet
    Eksempel:  "Dit hold er blevet oprettet! [Navn: SuperCyklerne,  HoldKaptajn: Simon Hansen]"
                Holdets ID nummer er: [1030100] og nøgleordet er [hansensis]
                HUSK AT GEMME ID OG NØGLEORD - Det skal bruges til at logge ind på holdets side


    2) approveTeam

    3) editTeam
        3.1 editTeamName
        3.2 editTeamLeader
        3.3 deleteTeam

    5) displayData

    6) deleteContestant


     */

}
