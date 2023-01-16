package br.com.divagar.calendar;

import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Test;

public class DayTest {

    @Test
    public void testWeekInTheMonth(){
        int[] day = {5,4,3,1,29,28,27,26,18,19};
        int[] month = {2,2,2,2,1,1,1,1,2,2};
        int[] weekInTheMonth = {2,1,1,1,5,4,4,4,3,4};
        for(int x=0;x<6;x++){
            Day dayObj = new Day(LocalDate.of(2023,month[x],day[x]));
            assertEquals(weekInTheMonth[x], dayObj.calculateWeek());
        }
       
    }
    
}
