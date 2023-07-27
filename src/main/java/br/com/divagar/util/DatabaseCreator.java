package br.com.divagar.util;

public class DatabaseCreator {

    String database;
    String user;
    String password;
    String host;
    String port;
    
    public DatabaseCreator() {
        
    }

    public static void main (String[] args) {
       System.out.println("Comilation successful!");

    }

    public void createDatabase() {

        String query = "create database calendar;";

    }

    public void createEntryTable () {
        String queryTypes = "create table entry_type ("+
            "id int primary key,"+
            "type varchar(20) not null unique,"+
            "description varchar(255));";
    
        String queryEntries = "create table entries ("+
            "id int primary key auto_increment,"+
            "dt_created datetime default current_timestamp,"+
            "dt_modified datetime default current_timestamp,"+
            "dt_start datetime default current_timestamp,"+
            "dt_finish datetime default current_timestamp,"+
            "type varchar(20) references entry_type(type),"+
            "user_id int default 0);";


    }

    public void createEpisodeTable () {
    
        String query = "create table episodes )"+
            "id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";


    }

    public void createEventTable () {
        String query = "create table events ("+
            "id int primary key auto_increment,"+
            "name varchar(128) not null,"+
            "description varchar(255),"+
            "entry_id int not null,"+
            "foreign key (entry_id) references entries(id) on delete cascade);";

    }

    public void createAgenda () {
    
        String query = "create table agenda ("+
            "id int primary key auto_increment,"+
            "name varchar(128) unique not null,"+
            "description varchar(256),);";

        String queryInsert = "insert into agenda(name) values(\"general\");";
    }

    public void createObjectives () {

        String query = "create table objectives ("+
            "id int primary key auto_increment,"+
            "name varchar(128),"+
            "agenda_id int not null,"+
            "foreign key (agenda_id) references agenda(id) on delete cascade);";

    }

    public void createActivities () {
    
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

    }
}
