package br.com.divagar.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import br.com.divagar.util.General;

class SqlTaskManager {

    private String url;
    private String user;
    private String password;


    

    public SqlTaskManager () throws  FileNotFoundException, IOException {
        
        Properties properties = General.loadProperties("config.properties");
        this.url = properties.getProperty("connector")+"://"+properties.getProperty("server")+":"
            +properties.getProperty("port")+"/"+properties.getProperty("dbName");
        this.user = properties.getProperty("dbUsername");
        this.password = properties.getProperty("dbPassword");

    }

    private Connection createConnection() throws SQLException{

        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }
    
    public void insertAgenda (Connection con, Agenda agenda) throws SQLException{

        String query = "INSERT into agenda (name, description) values (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, agenda.getName());
        ps.setString(2, agenda.getDescription());
        ps.executeUpdate();
        int id = getLastInsertId(con);
        agenda.setId(id);

    } 
    
    public void insertObjective (Connection con, Objective objective) throws SQLException{

        String query = "INSERT into objective (name, description) values (?, ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, objective.getName());
        ps.setString(2, objective.getDescription());
        ps.executeUpdate();
        int id = getLastInsertId(con);
        objective.setId(id);

    }

    public void insertActivity (Connection con, Activity activity) throws SQLException{

        String query = "INSERT into activity (name, description, alignment, pleasantness," +
            "usefulness, objectiveId, agendaId) values (? ?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, activity.getName());
        ps.setString(2, activity.getDescription());
        ps.setFloat(3, activity.getAlignment());
        ps.setFloat(4, activity.getPleasantness());
        ps.setFloat(5, activity.getUsefulness());
        ps.setInt(6, activity.getObjectiveId());
        ps.setInt(7, activity.getAgendaId());
        ps.executeUpdate();
        int id = getLastInsertId(con);
        activity.setId(id);

    } 
    
    // * condition is the condition part of the sql statemente ('where' included).
    public static Agenda[] getAgenda(Connection con, String condition) throws SQLException {
    String query = "select id, name, description, timestamp from agenda" + condition;
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    ArrayList<Agenda> list = new ArrayList<Agenda>();
    rs.next();
    while (rs.next()) {
        list.add(new Agenda(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
            rs.getTimestamp("timestamp").toLocalDateTime()));

    }
    Agenda[] agenda = new Agenda[list.size()]; 
    return list.toArray(agenda);
    }

    // * condition is the condition part of the sql statemente ('where' included).
    public static Objective[] getObjective (Connection con, String condition) throws SQLException {
    String query = "select id, name, description, agendaId  from objective" + condition;
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    ArrayList<Objective> list = new ArrayList<Objective>();
    rs.next();
    while (rs.next()) {
        list.add(new Objective(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("agendaID")));
    }
    Objective[] objective = new Objective[list.size()]; 
    return list.toArray(objective);
    }
    
    // * condition is the condition part of the sql statemente ('where' included).
    public static Activity[] getActivity (Connection con, String condition) throws SQLException {
    String query = "select id, name, description, alignment, usefulness, pleasantness," +
        "objectiveId,  agendaId  from objective" + condition;
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    ArrayList<Activity> list = new ArrayList<Activity>();
    rs.next();
    while (rs.next()) {
        list.add(new Activity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
            rs.getFloat("alignment"), rs.getFloat("pleasantness"), rs.getFloat("usefulness"),
            rs.getInt("objectiveId"), rs.getInt("agendaId")));
    }
    Activity[] activity = new Activity[list.size()]; 
    return list.toArray(activity);
    }

    private int getLastInsertId(Connection con) throws SQLException{
        PreparedStatement ps = con.prepareStatement("Select LAST_INSERT_ID();");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void main (String[] args) {}

    public static void test () {
    
        Agenda testAgenda = new Agenda( );


    }

}
