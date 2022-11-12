package calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Page {

    LocalDate refDate;
    int year;
    int month;
    HashMap<Integer, Week> weeks = null;
    LocalDate firstOfTheMonth;

    public static void main (String[] args){
        test();
    }

    public Page (LocalDate date){
        this.refDate = date;
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.firstOfTheMonth = date.minusDays(date.getDayOfMonth()-1);
        System.out.println(firstOfTheMonth);
    }

    public Page (int year, int month) {
        this.year = year;
        this.month = month;
        this.refDate = LocalDate.parse(year+"-"+month+"-01");
        this.firstOfTheMonth = this.refDate;
    }


    public Page getNext() {
        return new Page(refDate.plusMonths(1));
    }

    public Page getPrevious() {
        return new Page(refDate.plusMonths(1));
    }
    
    public static void test(){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String stringDate = "12/03/1989";
        LocalDate localDate = LocalDate.parse(stringDate, formater);
        System.out.println(localDate);

        Page page = new Page(localDate);
    }
}


