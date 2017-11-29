package models;

public class Master extends User {
    protected String masterName;

    public Master(String username, String password, String userId, String masterName){
        super (username, password, userId);
        this.masterName = masterName;
    }

//Abstract Method
    @Override
    public void displayData() {
    }

//Getter and Setter Methods
    public String getMasterName() {
        return masterName;
    }
}


