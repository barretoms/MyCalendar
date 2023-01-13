package br.com.divagar.calendar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import br.com.divagar.util.General;

public class SqlEntryManager implements EntryManager {

    private Properties properties = new Properties();

    public SqlEntryManager() throws FileNotFoundException, IOException{
        loadProperties();
    }

    @Override
    public Entry getEntries(LocalDateTime date) {
        // TODO Auto-generated method stub
        return null;
    }

    private void includeEntry(Entry entry, Connection con) throws SQLException {
            String queryString = "insert into entries(dt_created, dt_modified, dt_start, dt_finish, type) values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setString(1, General.dateTimeToString(entry.getCreationLocalDateTime()));
            ps.setString(2, General.dateTimeToString(entry.getModificationLocalDateTime()));
            ps.setString(3, General.dateTimeToString(entry.getStarLocalDateTime()));
            ps.setString(4, General.dateTimeToString(entry.getFinishLocalDateTime()));
            ps.setString(5, entry.getType());
            ps.executeUpdate();
            entry.setEntryId(getLastInsertId(con));
    }

    @Override
    public void includeEvent(Event event) throws SQLException {
        Connection con = createConnection();
        includeEntry(event, con);
        String queryString = "insert into events (entry_id, name, description) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setInt(1, event.getEntryId());
        ps.setString(2, event.getName());
        ps.setString(3, event.getDescription());
        ps.executeUpdate();
        event.setEventId(getLastInsertId(con));
        con.close();
        
    }

    private int getLastInsertId(Connection con) throws SQLException{
        PreparedStatement ps = con.prepareStatement("Select LAST_INSERT_ID();");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public ArrayList<Event> getEventByPeriod(LocalDateTime dtStart, LocalDateTime dtFinish) throws SQLException {
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = createConnection();
        String queryString = 
        "select name, a.id as entry_id, dt_start, dt_finish, dt_created, dt_modified, type, description, b.id as event_id "+ 
        "from entries as a join events as b on a.id = b.entry_id "+
        "where type = 'Event' and "+
        "(? between dt_start and dt_finish) or "+
        "(? between dt_start and dt_finish) or "+
        "(dt_start between ? and ?) or "+
        "(dt_finish between ? and ?);";
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setString(1, General.dateTimeToString(dtStart));
        ps.setString(2, General.dateTimeToString(dtFinish));
        ps.setString(3, General.dateTimeToString(dtStart));
        ps.setString(4, General.dateTimeToString(dtFinish));
        ps.setString(5, General.dateTimeToString(dtStart));
        ps.setString(6, General.dateTimeToString(dtFinish));
        ps.execute();
        ResultSet rs = ps.getResultSet();
            while(rs.next()){

                events.add(new Event(
                    rs.getString("name"),
                    General.stringToDateTime(rs.getString("dt_start")),
                    General.stringToDateTime(rs.getString("dt_finish")),
                    General.stringToDateTime(rs.getString("dt_created")),
                    General.stringToDateTime(rs.getString("dt_modified")),
                    rs.getString("description"),
                    rs.getInt("entry_id"),
                    rs.getInt("event_id")));
                }
        con.close();

        return events;
    }

    public ArrayList<Entry> getEntriesByPeriod(LocalDateTime dtStart, LocalDateTime dtFinish) throws SQLException{

        ArrayList<Entry> entries = new ArrayList<Entry>();
        entries.addAll(getEventByPeriod(dtStart, dtFinish));

        return entries;
    }  

    private void loadProperties() throws FileNotFoundException, IOException{
        String path = ".\\resources\\config.properties";
            properties.load(new FileInputStream(path));
    }

    private Connection createConnection() throws SQLException{
        String url = properties.getProperty("connector")+"://"+properties.getProperty("server")+":"
        +properties.getProperty("port")+"/"+properties.getProperty("dbName");
        String user = properties.getProperty("dbUsername");
        String password = properties.getProperty("dbPassword");
        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }


    public static void main (String[] args) throws FileNotFoundException, IOException, SQLException{
        SqlEntryManager obj = new SqlEntryManager();
        // Testing getProperty function
        System.out.println(obj.properties.getProperty("connectionType"));
        System.out.println(obj.properties.getProperty("dbPassword"));
        System.out.println(obj.getEntriesByPeriod(LocalDateTime.parse("2022-11-21T00:00:00"), LocalDateTime.parse("2022-11-21T23:59:59")));
        Event event = new Event("Test insert java 1", LocalDateTime.parse("2022-11-21T00:00:00"), LocalDateTime.parse("2022-11-21T23:59:59"));
        obj.includeEvent(event);
        System.out.println(event.getEntryId());
        System.out.println(event.getEventId());
    }
}
