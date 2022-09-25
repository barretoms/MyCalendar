package login;

public class Credential {
    
    private String username;
    private String password;

    public Credential (String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
       
}
