package br.com.divagar.calendar;

import java.time.LocalDate;

public class Month {

    LocalDate refDate;
    int year;
    int month;
    Week[] weeks = new Week[6];
    LocalDate firstOfTheMonth;
    LocalDate lastOfTheMonth;


    public Month (int year, int month) {
        this.year = year;
        this.month = month;
        this.refDate = LocalDate.of(year, month, 1);
        this.firstOfTheMonth = refDate;
        this.lastOfTheMonth = refDate.plusMonths(1).minusDays(1);
    }

    private Month (LocalDate refDate){
        this.refDate = refDate;
        this.year = refDate.getYear();
        this.month = refDate.getMonthValue();
        this.firstOfTheMonth = refDate.minusDays(refDate.getDayOfMonth()-1);
        this.lastOfTheMonth = firstOfTheMonth.plusMonths(1).minusDays(1);
    }

    public static Month of(LocalDate refDate) {
        Month month = new Month(refDate);
        return month;
    }

    public void loadWeekOf(Day day){
        Week week = new Week(day.getYear(), day.getMonth(), day.getWeek());
        weeks[day.getWeek()-1] = week;
    }

    public void loadWeaks () {
        System.out.println("firstOfTheMonth: "+this.firstOfTheMonth);
        System.out.println("month of the week:"+Week.of(firstOfTheMonth).getMonth());
        System.out.println("this.month:"+ this.month);
        for(Week week = Week.of(firstOfTheMonth); week.getMonth() == this.month; week = week.getNext()){
            week.loadDays();
            // System.out.println(week);
            weeks[week.getWeek()-1] = week;

        }
    }

    public void addDay(Day day) {
        int arrayPosition = day.getWeek()-1;
        if(weeks[arrayPosition] != null) weeks[arrayPosition].LoadDay(day);
        else {
            loadWeekOf(day);
            weeks[arrayPosition].LoadDay(day);
        }
    }

    public Month getNext() {
        Month month = new Month(firstOfTheMonth.plusMonths(1));
        return month;
    }

    public Month getPrevious() {
       Month month = new Month(firstOfTheMonth.minusMonths(1));
        return month;
    }
   
    @Override
    public String toString() {
        String str = "Year-Month: "+year+"-"+month+"\n";
        for (Week week : weeks) {
            if(week!=null) str += week+"\n";
        }

        return str;        
    }

    public static void main (String[] args){
        test();
    }

    public static void test(){
        // DateTimeFormatter formater = DateTimeFormatter.ofPattern("d/MM/yyyy");
        // String stringDate = "12/03/1989";
        // LocalDate localDate = LocalDate.parse(stringDate, formater);
        // System.out.println(localDate);
        Month test = Month.of(LocalDate.of(2023,1,3));
        test.loadWeaks();
        System.out.println(test.weeks);
        System.out.println(test);
        Day day28 = new Day(LocalDate.of(2023,1,28));
        Day day1stOfFeb = new Day(LocalDate.of(2023,2,1));
        
        Month test2 = Month.of(LocalDate.of(2023,1,28));
        test2.addDay(day28);
        test2.addDay(day1stOfFeb);

    }
}


