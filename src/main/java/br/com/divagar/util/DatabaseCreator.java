package br.com.divagar.util;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseCreator {

    static String url = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String password = "";
    static String database = "calendar";


    public DatabaseCreator() throws Exception {
    
    }

    private static Connection createConnection() throws SQLException{

        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }

    public static void main (String[] args) throws Exception{
       System.out.println("Compilation successful!");
       Connection con = createConnection();
       createDatabase(con);
       con.setCatalog(database);

       // Call the rest of the functions.

       con.close();


    }

    public static void createDatabase(Connection con) throws Exception{
        String query = "create database calendar;";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");

    }

    public static void createEntryTable (Connection con) throws Exception {
        String queryTypes = "create table entry_type ("+
            "id int primary key,"+
            "type varchar(20) not null unique,"+
            "description varchar(255), "+
            "timestamp datetime default currenttimestamp);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(queryTypes);
        if (status>0) System.out.println("Database created successfully!");
    
        String queryEntries = "create table entries ("+
            "id int primary key auto_increment,"+
            "dt_created datetime default current_timestamp,"+
            "dt_modified datetime default current_timestamp,"+
            "dt_start datetime default current_timestamp,"+
            "dt_finish datetime default current_timestamp,"+
            "type varchar(20) references entry_type(type),"+
            "user_id int default 0);";
        status = smnt.executeUpdate(queryEntries);
        if (status>0) System.out.println("Database created successfully!");


    }

    public static void createEpisodeTable (Connection con) throws Exception {
    
        String query = "create table episodes )"+
            "id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");


    }

    public static void createEventTable (Connection con) throws Exception {
        String query = "create table events ("+
            "id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");

    }

    public static void createAgenda (Connection con) throws Exception {
    
        String query = "create table agenda ("+
            "id int primary key auto_increment,"+
            "name varchar(128) unique not null,"+
            "description varchar(256),);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");

        String queryInsert = "insert into agenda(name) values(\"general\");";
        status = smnt.executeUpdate(queryInsert);
        if (status>0) System.out.println("Database created successfully!");
    }

    public static void createObjectives (Connection con) throws Exception {

        String query = "create table objectives ("+
            "id int primary key auto_increment,"+
            "name varchar(128),"+
            "agenda_id int not null,"+
            "foreign key (agenda_id) references agenda(id) on delete cascade);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");

    }

    public static void createActivities (Connection con) throws Exception {
    
        String query = "create table activities ("+
            "id int primary key not null,"+
            "name varchar(128) not null,"+
            "description varchar(256),"+
            "alignment float not null default 2.5,"+
            "pleasantness float not null default 2.5,"+
            "usefulness float not null default 2.5,"+
            "objectives_id int not null,"+
            "agenda_id int not null,"+
            "foreign key (agenda_id) references agenda(id) on delete cascade,"+
            "foreign key (objectives_id) references objectives(id) on delete cascade);";
        Statement smnt = con.createStatement();
        int status = smnt.executeUpdate(query);
        if (status>0) System.out.println("Database created successfully!");

    }

    public static void test() {
    

    }
}
