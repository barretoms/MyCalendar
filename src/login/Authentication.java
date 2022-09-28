package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.General;
import java.util.Scanner;
// import login.DBManager;


public class Authentication {
 
    public static void createUser(Connection conn, String username, String password){
        try {
            PreparedStatement ps = conn.prepareStatement("insert into user(username, salt, pass_hash) values (?, ?, ?);");
            ps.setString(1, username);
            String salt = generateSalt(12);
            ps.setString(2, salt);
            String pass_hash = General.getSha256FromString(username+salt+password);
            ps.setString(3, pass_hash);
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       

    }

    public void updatePassword(Connection conn, String username, String newPassword){
        try {
            PreparedStatement ps = conn.prepareStatement("update user set salt = ?, pass_hash = ? where username = ?;");
            ps.setString(3, username);
            String salt = generateSalt(12);
            ps.setString(1, salt);
            String pass_hash = General.getSha256FromString(username+salt+newPassword);
            ps.setString(2, pass_hash);
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
    }

    public static Boolean existsUser (Connection conn, String username){
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from user where username = ? ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void validateCredentials(Connection conn, Credential credential){
        if (existsUser(conn, credential.getUsername())){
            try {
                
                PreparedStatement ps = conn.prepareStatement("select id from user where username = ? and pass_hash = ?");
                ps.setString(1, credential.getUsername());
                String passHash = " ";  // TODO 
                ps.setString(2, passHash);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
    }

    public static String generateSalt(Integer length){
        String salt = General.getRandomAlphaNumericString(length);
        return salt;
    }

    public static String getSalt(Credential credential){
        String salt = null;


        // TODO implement a function to retrieve the salt, given a credential

        return salt;
    }

    public static Connection getConnection() throws SQLException {
        
        //  Connects to login database
        // FIXME use DBManager to get the connection
        String url = "jdbc:mysql://localhost:3306/authentication";
        String username = "root";
        String password = "";

        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void test(){

        System.out.println(Authentication.generateSalt(15)); //salt test

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter a Password: ");
        String password = scanner.nextLine();

        String salt = generateSalt(10);

        System.out.println("\n...\n");
        System.out.println(String.format("Username: %s\nPassword: %s\nSalt: %s\n", username, password, salt));

        scanner.close();

        try {
            Connection conn = getConnection();
            System.out.println(conn);

            createUser(conn, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public static void test2(){

        try {
            Connection conn = getConnection();
            System.out.println(conn);
            
            System.out.println("Case: User exists -> ");
            System.out.println(existsUser(conn, "marcelo"));
            System.out.println("Case: User doesn't exists -> ");
            System.out.println(existsUser(conn, "fabio"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // test(); // tests generateSalt() and include user createUser()
        test2(); // tests existsUser()
    }

}

