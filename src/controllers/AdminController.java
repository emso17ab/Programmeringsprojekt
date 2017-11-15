package controllers;
import data.Data;

//Admincontrolleren styrer menuer for Virksomhederne, som agerer som administrator for deres hold og holdenes deltagere

public class AdminController {
    Data data;

    public AdminController(Data data){
        this.data = data;
    }


    public void adminRun() {
        System.out.println("DU ER LOGGET IND SOM ADMINISTRATOR");
    }

}
