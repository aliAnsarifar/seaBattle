package shared.model;

public class User {
    private int victory;
    private int losing;
    private String username;
    private String password;
    private int Id;
    private boolean online;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        Id = id;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    public boolean isOnline() {
        return online;
    }

    public int getVictory() {
        return victory;
    }

    public int getLosing() {
        return losing;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return Id;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setLosing(int losing) {
        this.losing = losing;
    }
}
