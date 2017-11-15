package models;

public abstract class User {
    protected String username;
    protected String password;
    protected String userId; //Et userId består af syv cifre. ####### Det første indikerer user typen. De to næste representerer Virksomheden, De to næste, Holdet, og de to sidste, deltageren.


    public User(String username, String password, String userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    //Methods


    //Getter and Setter Methods
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
