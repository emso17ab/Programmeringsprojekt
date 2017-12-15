package models;
/*
Ansvarlig: Rasmus og Kesia
 */

public class Master extends User {
    protected String masterName;

    public Master(String username, String password, String userId, String masterName){
        super (username, password, userId);
        this.masterName = masterName;
    }

//Abstract Method
    @Override
    public void displayData() {
        //Metoden er nærmere beskrevet i rapporten
        System.out.println("\n");
        System.out.println("#" + userId + "_************************************************************************");
        System.out.println("<---------------MASTER ADMIN--------------->");
        System.out.println("PROGRAMMETS UDVIKLERE: Team XB22 Ft. Kesia Heiberg, Rasmus Pold, William Kaspersen & Emil Sørensen");
        System.out.println("\n\n\n");
    }

//Getter and Setter Methods
    public String getMasterName() {
        return masterName;
    }
}


