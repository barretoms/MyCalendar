package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Authentication {

    private String username;
    private String password;

    public static void create(){

    }

    public void update(){

    }

    public static void validate(){
        
    }

    public static String generateSatlt(){
        String salt = new String();

            // TODO Implemment function to generate random salt

        return salt;
    }

    public static Connection getConnection() throws SQLException {
        
        // Connects to login database
        String url = "";
        String username = "";
        String password = "";

        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


}
