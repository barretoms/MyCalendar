package calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DateSlice {
    
    LocalDate dsStart;
    LocalDate dsFinish;
    ArrayList<Month> dateSlice = new ArrayList<Month>();
    HashMap<Integer, Entry> entries = new HashMap<Integer, Entry>();

    public DateSlice(LocalDate dsStart, LocalDate dsFinish) {
        this.dsStart = dsStart;
        this. dsFinish = dsFinish;
    }

    public void populate(){
        int previousMonthValue = dsStart.getMonthValue();
        int currentMonthValue = dsStart.getMonthValue();
        Month month = Month.of(dsStart);   
        dateSlice.add(month);  
        for(Day day = new Day(dsStart); day.getLocalDate().isBefore(dsFinish.plusDays(1)); day = day.getNext()){
            currentMonthValue = day.getMonth();
            if(previousMonthValue != currentMonthValue){
                month = Month.of(day.getLocalDate());
                System.out.println("Month added...");
                dateSlice.add(month);
            }
            System.out.println("day added: "+day);
            month.addDay(day);
            previousMonthValue = currentMonthValue;
        }
    }

    public static void test() {
        DateSlice ds = new DateSlice(LocalDate.of(2023,1,1), LocalDate.of(2023, 2, 15));
        ds.populate();
        for (Month month : ds.dateSlice) {
            System.out.println(month);
        }
    }

    public static void main (String[] args) {
        System.out.println(LocalDate.of(2023,1,28).plusDays(6));
        
        test();
    }
}
