package models;
/*
Ansvarlig: Emil
 */

public abstract class User {
    protected String username;
    protected String password;
    protected String userId;
    /*
    Et userId består af seks cifre. ###### De to første representerer Virksomheden, De to næste, Holdet, og de to sidste, deltageren.
    Dette userId er nærmere beskrevet i rapporten.
    */

    public User(String username, String password, String userId){
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

//Abstract Method
    public abstract void displayData();
    /*
    Det er User-klassens krav at denne metode kaldes i alle sub-klasserne som arver fra denne superklasse.
    Metoden er nærmere beskrevet i rapporten
    */

//Methods
    public Team findTeamFromUser(User user){
        return null;
    }

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
