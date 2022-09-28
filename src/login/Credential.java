package login;

public class Credential {
    
    private String username;
    private String password;
    private Boolean validated = false;
    private Integer id = null;

    public Credential (String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void validate(){
        validated = true;
    }

    public Boolean isValidated(){
        return validated;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
       
}