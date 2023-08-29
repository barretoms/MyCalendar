package br.com.divagar.calendar;

import java.sql.*;
import java.time.LocalDate;

public class test {

    static String url = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String password = "";

    public static void main (String[] args){
        getWeekOfTheMonth(LocalDate.now());
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,1,15));
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,1,25));
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,2,1));
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,2,15));
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,2,5));
        System.out.println("-------------");
        getWeekOfTheMonth(LocalDate.of(2023,2,4));
    
        System.out.println("testeMySql");

        try {
            testConnection();
        }
        catch (Exception e) {
        System.out.println(e);
        }
    }

    public static void testConnection () throws Exception{

        Connection con = DriverManager.getConnection(url,user,password);

    }
    public static int getWeekOfTheMonth (LocalDate day){
        double dayOfTheMonth = day.getDayOfMonth();
        double dayOfTheWeek = day.getDayOfWeek().getValue()%7+1;
        System.out.println(String.format("Day of the week of '%s' is %s", day, dayOfTheWeek));
        double dayOfTheWeekOfThe1st = day.minusDays(day.getDayOfMonth()-1).getDayOfWeek().getValue()%7+1;
        System.out.println(String.format("The day of the week of the 1st of the month is %s, by using LocalDate", dayOfTheWeekOfThe1st));
        // double dayOfTheWeekOfThe1stnld =7+1);
        double dayOfTheWeekOfThe1stnld = (double)((dayOfTheMonth + dayOfTheWeek+1)%7);
        // double dayOfTheWeekOfThe1stnld = (dayOfTheMonth + dayOfTheWeek%7))%7;
        System.out.println(String.format("The dayOfTheWeek using math operations of the 1st of the month is %s", dayOfTheWeekOfThe1stnld));
        int week = (int)Math.ceil((((7-dayOfTheWeekOfThe1st%7))+ dayOfTheMonth)/7);
        System.out.println(week);
        return week;
    }
}
