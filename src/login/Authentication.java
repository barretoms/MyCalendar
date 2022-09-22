package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;


public class Authentication {

    private String username;
    private String password;

    

    public static void create(){

    }

    public void update(){

    }

    public static void validate(){
        
    }

    public static String generateSalt(Integer length){

        String salt = new String();

            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numbers = "0123456789";

            String characters = numbers + lowerCaseLetters + upperCaseLetters;

            Random rand = new Random();

            for(int i = 0; i < length; i++){
                salt += characters.charAt(rand.nextInt(characters.length()));
            }

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

