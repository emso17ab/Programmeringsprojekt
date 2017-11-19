package models;

public class Master extends User {
    protected String masterName;

    public Master(String username, String password, String userId, String masterName){
        super (username, password, userId);
        this.masterName = masterName;
    }

//Abstract methods
    @Override
    public void displayData() {
    }

    public String getMasterName() {
        return masterName;
    }
}


