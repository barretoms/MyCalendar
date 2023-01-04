package calendar;

import java.time.LocalDate;
import java.util.HashMap;


public class Calendar {

    LocalDate refDate;
    HashMap <String, Month> pages = null;
    HashMap <String, Entry> entries = new HashMap<String, Entry>();
    

    public Calendar(){
        refDate = LocalDate.now();
    }

    public Calendar(LocalDate refDate){
        this.refDate = refDate;
    }


    private void buildDateSlice(LocalDate start, LocalDate finish){

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


}
