package br.com.divagar.calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import br.com.divagar.util.General;

public class Calendar {

    LocalDate refDate;
    HashMap <String, Month> pages = null;
    HashMap <String, Entry> entries = new HashMap<String, Entry>();
    DateSlice dateSlice;
    private EntryManager manager;
    private static Properties properties;
    
    
    public Calendar() throws FileNotFoundException, IOException{
        refDate = LocalDate.now();
        System.out.println("Loading Calendar Properties...");
        properties = General.loadProperties("config.properties");
        switch(properties.getProperty("connectionType")){
            case "database":
                manager = new SqlEntryManager();
        }        
    }

    public Calendar(LocalDate refDate){
        this.refDate = refDate;
    }

    private DateSlice buildDateSlice(LocalDate start, LocalDate finish) {
        DateSlice dateSlice = new DateSlice(start, finish);
        dateSlice.populate();
        return dateSlice;
    }

    public void loadMonthPage(int year, int month){
        Month monthObject = new Month(year, month);
        monthObject.loadWeaks();
        pages.put(new String(year+"-"+month), monthObject);
    }

    @Override
    public String  toString(){
        String str = "";

        // TODO - Implement string representation of a calendar


        return str;
    }
    public static void test(){
        // Test goes Here...
    }

    public static void main(String[] args){
        test();
    }

}
