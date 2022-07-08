package calendar;

import java.time.LocalDate;


public class Calendar {

    LocalDate refDate;


    public Calendar(){
        refDate = LocalDate.now();
    }

    public void loadMonth(String month){
        // TODO - Implement a function to load a Month of events
    }

    @Override
    public String  toString(){
        String str = "";

        // TODO - Implement string representation of a calendar


        return str;
    }
}
