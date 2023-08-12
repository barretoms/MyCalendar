package br.com.divagar.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MysqlDatabaseCreator {

    private String url;
    private String user;
    private String password;
    private String database;

    public MysqlDatabaseCreator() throws  FileNotFoundException, IOException {

        Properties properties = General.loadProperties("config.properties");

        this.url = properties.getProperty("connector")+"://"+properties.getProperty("server")+":"+properties.getProperty("port")+"/";
        this.user = properties.getProperty("dbUsername");
        this.password = properties.getProperty("dbPassword");
        this.database = properties.getProperty("dbName");
    }

    public static void main (String[] args) throws Exception {
        
        MysqlDatabaseCreator mdbc = new MysqlDatabaseCreator();
        mdbc.execute();

    }

    private Connection createConnection() throws SQLException{

        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }

    public void createDatabase(Connection con) throws Exception {

        String query = "create database if not exists calendar;";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);

    }

    public void createEntryTypeTable(Connection con) throws SQLException {

        String query = "create table if not exists entry_type ("+
            "id int primary key,"+
            "type varchar(20) not null unique,"+
            "description varchar(255));";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);

        Integer[] ids = {1, 2, 3, 4, 5};
        String[] types = {"Event","Episode","Reminder","Activity","Log"};
        String[] descriptions = {
            "Activity that will happen in the future that has a start an a finish.",
            "Activity that will happen in the future at a specific time (there's no dimension of time).",
            "Reminder to keep in mind during a specific time.",
            "Ongoing activity that has a start time (will become a log, once it's finished).",
            ""
        };
        
        PreparedStatement ps = con.prepareStatement("insert ignore into entry_type(id, type, description) values (?, ?, ?)");
        for(int x = 0; x<ids.length; x++) {
            ps.setInt(1, ids[x]);
            ps.setString(2, types[x]);
            ps.setString(3, descriptions[x]);
            ps.execute();
        }

    }

    public void createEntriesTable(Connection con) throws Exception {

        Statement smnt = con.createStatement();    
        String query = "create table if not exists entries ("+
            "id int primary key auto_increment,"+
            "dt_created datetime default current_timestamp,"+
            "dt_modified datetime default current_timestamp,"+
            "dt_start datetime default current_timestamp,"+
            "dt_finish datetime default current_timestamp,"+
            "type varchar(20) references entry_type(type),"+
            "user_id int default 0);";
        smnt.executeUpdate(query);

    }

    public void createEpisodeTable(Connection con) throws Exception {
    
        String query = "create table if not exists episodes"+
            "(id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);

    }

    public void createEventTable(Connection con) throws Exception {

        String query = "create table if not exists events ("+
            "id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);


    }

    public void createAgenda(Connection con) throws Exception {
    
        String query = "create table if not exists agenda ("+
            "id int primary key auto_increment,"+
            "name varchar(128) unique not null,"+
            "description varchar(256));";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);

        String queryInsert = "insert ignore into agenda(name) values(\"general\");";
        smnt.executeUpdate(queryInsert);

    }

    public void createObjectives(Connection con) throws Exception {

        String query = "create table if not exists objectives ("+
            "id int primary key auto_increment,"+
            "name varchar(128),"+
            "agenda_id int not null,"+
            "foreign key (agenda_id) references agenda(id) on delete cascade);";
        Statement smnt = con.createStatement();
        smnt.executeUpdate(query);

    }

    public void createActivities(Connection con) throws Exception {
    
        String query = "create table if not exists activities ("+
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

    public void execute() throws Exception {

        Connection con = createConnection();
        createDatabase(con);
        con.setCatalog(database);
        createEntryTypeTable(con);
        createEntriesTable(con);
        createEpisodeTable(con);
        createEventTable(con);
        createAgenda(con);
        createObjectives(con);
        createActivities(con);

        con.close();

    }
}
