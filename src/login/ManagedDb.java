package login;

public class ManagedDb {

    private String name;
    private String connector = "jdbc:mysql";
    private String address;
    private String username;
    private String password;
    

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConnector(){
        return connector;
    }

    public void setConnector(String connector){
        this.connector = connector;
    }

    public String connectorString(){
        return connector+":"+address;
    }
    
    public String toString(){
        return name + " -> " + this.connectorString();
    }
    
}
