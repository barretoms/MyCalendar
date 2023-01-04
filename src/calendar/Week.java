package calendar;

import java.time.LocalDate;

public class Week {

    int year;
    int month;
    int week;
    LocalDate refDate;
    LocalDate firstOfTheWeek;
    LocalDate lastOfTheWeek;
    Day[] weekDays = new Day[7];
    
    private Week(LocalDate refDate) {
        this.refDate = refDate;
        this.firstOfTheWeek = refDate.minusDays(refDate.getDayOfWeek().getValue()%7);
        this.lastOfTheWeek = this.firstOfTheWeek.plusDays(6);
        this.year = refDate.getYear();
        this.month = refDate.getMonthValue();
        this.week = ((7-refDate.getDayOfMonth()%7)+refDate.getDayOfMonth())/7;
    }

    public Week(int year, int month, int week) {
        this.year = year;
        this.month = month;
        this.week = week;
    }
    
    public void loadDays(){
        LocalDate refDay = firstOfTheWeek;
        for(int x = 0; x<7;x++) weekDays[x] = new Day(refDay.plusDays(x));
    }

    public static Week of(LocalDate refDate){
        return new Week(refDate);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getWeek() {
        return week;
    }

    public Day[] getWeekDays() {
        return weekDays;
    }

    public Week getNext(){
        LocalDate refDate = lastOfTheWeek.plusDays(1);
        Week week = Week.of(refDate);
        return week;
    }


    @Override
    public String toString() {
        String str = "Week "+this.week+": | ";
        for (Day day : weekDays) {
            str += day+" | ";
        }
        return str;
    }
    public static void main(String[] args){
        Week test = new Week(LocalDate.of(2023,1,3));
        test.loadDays();
        System.out.println(test.firstOfTheWeek);
        System.out.println(test.lastOfTheWeek);
        System.out.println(test);
        Week test2 = test.getNext();
        System.out.println(test2.refDate);
        System.out.println(test2.firstOfTheWeek);
        System.out.println(test2.lastOfTheWeek);
        System.out.println(LocalDate.of(2023,1,1).getDayOfWeek().getValue());
        Week test3 = Week.of(LocalDate.now());
        System.out.println("firstOfTheWeek: "+test3.firstOfTheWeek);
        System.out.println("lastOfTheWeek: "+test3.lastOfTheWeek);
        System.out.println("refDate: "+test3.refDate);
        System.out.println("month: "+test3.month);
        System.out.println(test3);
        test3.loadDays();
        System.out.println("firstOfTheWeek: "+test3.firstOfTheWeek);
        System.out.println("lastOfTheWeek: "+test3.lastOfTheWeek);
        System.out.println("refDate: "+test3.refDate);
        System.out.println("month: "+test3.month);
        System.out.println(test3);
    }
}
