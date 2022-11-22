package calendar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class MySqlEntryManager implements EntryManager {

    private Properties properties = new Properties();

    public MySqlEntryManager() throws FileNotFoundException, IOException{
        loadProperties();
    }

    @Override
    public Entry getEvents(LocalDateTime date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean intersects(LocalDateTime start, LocalDateTime finish) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void includeEntry(Entry entry) {
        try {
            Connection con = createConnection();
            String queryString = "insert into entries(dt_created, dt_modified, dt_start, dt_finish, name, type) values (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setString(1, dateTimeToString(entry.getCreationLocalDateTime()));
            ps.setString(2, dateTimeToString(entry.getModificationLocalDateTime()));
            ps.setString(3, dateTimeToString(entry.getStarLocalDateTime()));
            ps.setString(4, dateTimeToString(entry.getFinishLocalDateTime()));
            ps.setString(5, entry.getName());
            ps.setString(6, entry.getType());
            ps.executeUpdate();
            entry.setId(getLastInsertId(con));
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public int getLastInsertId(Connection con) throws SQLException{
        PreparedStatement ps = con.prepareStatement("Select LAST_INSERT_ID();");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int[] getEntryIdsByPeriod(LocalDateTime dtStart, LocalDateTime dtFinish, String type){
        int[] idList = null;

        return idList;
    }

    public ArrayList<Integer> getEntryIdsByPeriod(LocalDateTime dtStart, LocalDateTime dtFinish) throws SQLException{

        ArrayList<Integer> idList = new ArrayList<Integer>();
        Connection con = createConnection();
        String queryString = 
        "select id, name from entries where "+
        "(? between dt_start and dt_finish) or "+
        "(? between dt_start and dt_finish) or "+
        "(dt_start between ? and ?) or "+
        "(dt_finish between ? and ?);"; 
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setString(1, dateTimeToString(dtStart));
        ps.setString(2, dateTimeToString(dtFinish));
        ps.setString(3, dateTimeToString(dtStart));
        ps.setString(4, dateTimeToString(dtFinish));
        ps.setString(5, dateTimeToString(dtStart));
        ps.setString(6, dateTimeToString(dtFinish));
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            // System.out.println(rs.getInt(1)); // Test code
            idList.add(rs.getInt(1));
        }
        con.close();
        return idList;
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

    public LocalDateTime stringToDateTime(String dt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dt, formatter);
        return ldt;
    }

    public String dateTimeToString(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldtString = ldt.format(formatter);
        return ldtString;
    }

    public static void main (String[] args) throws FileNotFoundException, IOException, SQLException{
        MySqlEntryManager obj = new MySqlEntryManager();
        // Testing getProperty function
        System.out.println(obj.properties.getProperty("connectionType"));
        System.out.println(obj.properties.getProperty("dbPassword"));
        obj.getEntryIdsByPeriod(LocalDateTime.parse("2022-11-21T00:00:00"), LocalDateTime.parse("2022-11-21T23:59:59"));
        Entry entry = new Event("Test insert java 1", LocalDateTime.parse("2022-11-21T00:00:00"), LocalDateTime.parse("2022-11-21T23:59:59"));
        obj.includeEntry(entry);
        System.out.println(entry.getId());
    }   
}
