package calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Month {

    LocalDate refDate;
    int year;
    int month;
    ArrayList<Week> weeks = new ArrayList<Week>();
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

    public void loadWeaks () {
        System.out.println("firstOfTheMonth: "+this.firstOfTheMonth);
        System.out.println("month of the week:"+Week.of(firstOfTheMonth).getMonth());
        System.out.println("this.month:"+ this.month);
        for(Week week = Week.of(firstOfTheMonth); week.getMonth() == this.month; week = week.getNext()){
            week.loadDays();
            // System.out.println(week);
            weeks.add(week);

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
            str += week+"\n";
        }

        return str;        
    }

    public static void main (String[] args){
        test();
    }

    public static void test(){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String stringDate = "12/03/1989";
        LocalDate localDate = LocalDate.parse(stringDate, formater);
        System.out.println(localDate);
        Month test = Month.of(LocalDate.of(2023,1,3));
        test.loadWeaks();
        System.out.println(test.weeks.size());
        System.out.println(test);

    }
}


