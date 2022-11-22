package calendar;

import java.time.LocalDate;
import java.util.HashMap;


public class Calendar {

    LocalDate refDate;
    HashMap <String, Page> pages = null;
    HashMap <String, Entry> entries = null;

    public Calendar(){
        refDate = LocalDate.now();
    }

    public Calendar(LocalDate refDate){
        this.refDate = refDate;
    }

    public void loadPage(String page){
        pages.put(page, null);
    }

    @Override
    public String  toString(){
        String str = "";

        // TODO - Implement string representation of a calendar


        return str;
    }
}
