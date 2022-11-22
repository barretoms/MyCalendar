package calendar;

import java.time.LocalDate;

public class Week {

    LocalDate firstOfTheWeek;
    Day[] weekDays = null;
    
    public Week(LocalDate firstOfTheWeek) {
        this.firstOfTheWeek = firstOfTheWeek;
    }

    @Override
    public String toString() {
        return "Week []";
    }
    
    
    
}
