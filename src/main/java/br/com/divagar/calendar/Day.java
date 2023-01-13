 package br.com.divagar.calendar;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.Math;

public class Day {

    int year;
    int month;
    int week;
    int dayOfTheMonth;
    int dayOfTheWeek;
    LocalDate refDate;
    LocalDateTime dtStart;
    LocalDateTime dtFinish;
    ArrayList<Integer> entrieIds = new ArrayList<Integer>();

    public Day(LocalDate refDate) {
        this.refDate = refDate;
        this.year = refDate.getYear();
        this.month = refDate.getMonthValue();   
        this.dayOfTheMonth = refDate.getDayOfMonth();
        this.dtStart = LocalDateTime.of(year, month, dayOfTheMonth, 0, 0);
        this.dtFinish = LocalDateTime.of(year, month, dayOfTheMonth, 23, 59, 59);
        this.dayOfTheWeek = refDate.getDayOfWeek().getValue()%7+1;
        this.week = calculateWeek();
    }
        
    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    private int calculateWeek(){ // 
        double dayOfTheMonth = (double)this.dayOfTheMonth;
        LocalDate firstOfTheMonth = LocalDate.of(year, month, 1);
        double weekDayOfTheFirst = (double)(firstOfTheMonth.getDayOfWeek().getValue()%7+1);
        // double weekDayOfTheFirst2 = (double)((dayOfTheMonth + dayOfTheWeek%7+1)%7);

        System.out.println(String.format("Day of the week of the 1st: %s", weekDayOfTheFirst));
        int week = (int)Math.ceil((((weekDayOfTheFirst-1))+ dayOfTheMonth)/7);
        return week;
    }

    public int getWeek() {
        return this.week;
    }

    public int getDayOfTheMonth() {
        return dayOfTheMonth;
    }

    public LocalDateTime getDtStart() {
        return dtStart;
    }

    public LocalDateTime getDtFinish() {
        return dtFinish;
    }

    public ArrayList<Integer> getEntrieIds() {
        return entrieIds;
    }

    public LocalDate getLocalDate() {
        return this.refDate;
    }

    public boolean intersects(Entry entry) {
        if (entry.getStarLocalDateTime().isAfter(dtStart)&&entry.getStarLocalDateTime().isBefore(dtFinish)||
        entry.getFinishLocalDateTime().isAfter(dtStart)&&entry.getFinishLocalDateTime().isBefore(dtFinish)||
        dtStart.isAfter(entry.getStarLocalDateTime())&&dtStart.isBefore(entry.getFinishLocalDateTime())||
        dtFinish.isAfter(entry.getStarLocalDateTime())&&dtFinish.isBefore(entry.getFinishLocalDateTime())) return true;
        return false;
    }

    public boolean intersects(LocalDateTime eStart, LocalDateTime eFinish) {
        if (eStart.isAfter(dtStart)&&eStart.isBefore(dtFinish)||
        eFinish.isAfter(dtStart)&&eFinish.isBefore(dtFinish)||
        dtStart.isAfter(eStart)&&dtStart.isBefore(eFinish)||
        dtFinish.isAfter(eStart)&&dtFinish.isAfter(eFinish)) return true;
        return false;
    }

    public Day getPrevious(){
        Day day = new Day(refDate.minusDays(1));
        return day;
    }

    public Day getNext(){
        Day day = new Day(refDate.plusDays(1));
        return day;
    }

    public void includeEntryId(int id){
        this.entrieIds.add(id);
    }

    public boolean isEmpty(){
        return entrieIds.isEmpty();
    }

    @Override
    public String toString() {
        String str = year+"-"+month+"-"+dayOfTheMonth;
        return str;
    }
    
    private static void test(){
        System.out.println("Testing Day Class: ");
        Day test = new Day(LocalDate.now());
        System.out.println(test);
        System.out.println("dayOfTheMonth: "+test.dayOfTheMonth);
        System.out.println("month: "+ test.month);
        System.out.println("year: "+ test.year);
        System.out.println("week: "+ test.week);
        System.out.println();
        Day test2 = test.getNext();
        System.out.println("Testing getNext(): ");
        System.out.println("dayOfTheMonth: "+test2.dayOfTheMonth);
        System.out.println("month: "+ test2.month);
        System.out.println("year: "+ test2.year);
        System.out.println("week: "+ test2.week);
        System.out.println();
        Day test3 = test.getPrevious();
        System.out.println("Testing getPrevious(): ");
        System.out.println("dayOfTheMonth: "+test3.dayOfTheMonth);
        System.out.println("month: "+ test3.month);
        System.out.println("year: "+ test3.year);
        System.out.println("week: "+ test3.week);
        
    }

    private static void test2(){
        System.out.println("Testing Days of The Week: getDayOfTheWeek()");
        System.out.println("Expected: sunday | 1 ");
        System.out.println(new Day(LocalDate.of(2023,1,1)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: monday | 2");
        System.out.println(new Day(LocalDate.of(2023,1,2)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: tuesday | 3");
        System.out.println(new Day(LocalDate.of(2023,1,3)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: wednsday | 4");
        System.out.println(new Day(LocalDate.of(2023,1,4)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: thursday | 5");
        System.out.println(new Day(LocalDate.of(2023,1,5)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: friday | 6");
        System.out.println(new Day(LocalDate.of(2023,1,6)).getDayOfTheWeek());
        System.out.println("--------------------");
        System.out.println("Expected: saturday | 7");
        System.out.println(new Day(LocalDate.of(2023,1,7)).getDayOfTheWeek());
        System.out.println("--------------------");
        
    }

    public static void test3(){

        System.out.println("\n ------------------ \n*Testando Semana no mes");
        Day dia26 = new Day(LocalDate.of(2023,1,26));
        System.out.println("Dia 26/1/2023, Expected: 4, Gotten: "+dia26.week);
        System.out.println("----------------");
        Day dia27 = new Day(LocalDate.of(2023,1,27));
        System.out.println("Dia 27/1/2023, Expected: 4, Gotten: "+dia27.week);
        System.out.println("----------------");
        Day dia28 = new Day(LocalDate.of(2023,1,28));
        System.out.println("Dia 28/1/2023, Expected: 4, Gotten: "+dia28.week);
        System.out.println("----------------");
        Day dia1 = new Day(LocalDate.of(2023,1,1));
        System.out.println("Dia 1/1/2023, Expected: 1, Gotten: "+dia1.week);
        System.out.println("----------------");
        Day dia29 = new Day(LocalDate.of(2023,1,29));
        System.out.println("Dia 29/1/2023, Expected: 5, Gotten: "+dia29.week);
        System.out.println("----------------");
        Day dia1Feb = new Day(LocalDate.of(2023,2,1));
        System.out.println("Dia 1/2/2023, Expected: 1, Gotten: "+dia1Feb.week);
        System.out.println("----------------");
        Day dia2Feb = new Day(LocalDate.of(2023,2,2));
        System.out.println("Dia 2/2/2023, Expected: 1, Gotten: "+dia2Feb.week);
        System.out.println("----------------");
        Day dia3Feb = new Day(LocalDate.of(2023,2,3));
        System.out.println("Dia 3/2/2023, Expected: 1, Gotten: "+dia3Feb.week);
        System.out.println("----------------");
        Day dia4Feb = new Day(LocalDate.of(2023,2,4));
        System.out.println("Dia 4/2/2023, Expected: 1, Gotten: "+dia4Feb.week);
        System.out.println("----------------");
        Day dia5Feb = new Day(LocalDate.of(2023,2,5));
        // dia5Feb.
        System.out.println("Dia 5/2/2023, Expected: 2, Gotten: "+dia5Feb.week);
        System.out.println("----------------");
    }

    public static void test4(){

        System.out.println("\n ------------------ \n*Testando getNext()");
        Day day = new Day(LocalDate.of(2023,1,31));
        System.out.println("Dia 31/1/2023: "+day);
        System.out.println("----------------");
        Day nextDay = day.getNext();
        System.out.println("day.getNext(): "+nextDay);
        System.out.println("----------------");
        
    }

    public static void main(String[] args){
        test();
        test2();
        test3();
        test4();
    }
    
}
