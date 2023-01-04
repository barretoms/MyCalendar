package calendar;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Day {

    int year;
    int month;
    int dayOfTheMonth;
    LocalDateTime dtStart;
    LocalDateTime dtFinish;
    String[] entriesIds;

    public Day(LocalDate current) {
        this.year = current.getYear();
        this.month = current.getMonthValue();
        this.dayOfTheMonth = current.getDayOfMonth();
        this.dtStart = LocalDateTime.of(year, month, dayOfTheMonth, 0, 0);
        this.dtFinish = LocalDateTime.of(year, month, dayOfTheMonth, 23, 59, 59);
    }
        
    @Override
    public String toString() {
        String str = year+"-"+month+"-"+dayOfTheMonth;
        // TODO - Implement string representation of a day
        return str;
    }
    
    
}
