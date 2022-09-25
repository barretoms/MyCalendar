package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.General;


public class Authentication {
 
    public static void createUser(){

    }

    public void updateUser(){

    }

    public static void validateCredentials(Credential credential){
        
    }

    public static String generateSalt(Integer length){
        String salt = General.getRandomAlphaNumericString(length);
        return salt;
    }

    public static Connection getConnection() throws SQLException {
        
        // Connects to login database
        String url = "jdbc:mysql://localhost:3306/authentication";
        String username = "root";
        String password = "";

        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void main(String[] args) {

        System.out.println(Authentication.generateSalt(15)); //salt test

        try {
            Connection con = getConnection();
            System.out.println(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

