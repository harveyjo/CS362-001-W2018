package calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
        public void radnomtest()  throws Throwable  {
            GregorianCalendar occurrenceDay =  new GregorianCalendar(2017, 1, 5);
            CalDay testCalDay = new CalDay(occurrenceDay);

            long randomseed = System.currentTimeMillis(); //10
            Random random = new Random(randomseed);
            // Construct a random number of test appointments between 500 and 1000
            int numTests = ValuesGenerator.getRandomIntBetween(random, 500, 25000);
            ArrayList<Appt> testAppts = new ArrayList<Appt>();
            ArrayList<int[]> recurDaysList = new ArrayList<int[]>();
            int numAdded = 0;
            ArrayList<Appt> apptsToAdd = new ArrayList<Appt>();
            

            for(int i = 0; i < numTests; i++) {
                Boolean isValidTest = true;
                int startHour = ValuesGenerator.getRandomIntBetween(random, -100, 100);
                int startMinute = ValuesGenerator.getRandomIntBetween(random, -100, 100);
                int startDay = ValuesGenerator.getRandomIntBetween(random, -100, 100);
                int startYear = ValuesGenerator.getRandomIntBetween(random, -100, 100);
                int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
                int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);

                String title = ValuesGenerator.getString(random);
                String description = ValuesGenerator.getString(random);

            Appt testApptToAdd  = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);

                if((startHour < 0 || startHour > 23) ||
                (startMinute < 0 || startMinute > 59) ||
                (startDay < 1 || startDay >NumDaysInMonth) ||
                (startMonth<1 || startMonth > 12)
                ) {
                    isValidTest = false;
                    // apptsToAdd.add();
                    testCalDay.addAppt(testApptToAdd); // should fail
                } else {
                    numAdded++;
                    testCalDay.addAppt(testApptToAdd);
                }

                // Appt testAppointment = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
                // testAppts.add(testAppointment);

                // // generate random recur days
                // int numRecurDays = ValuesGenerator.getRandomIntBetween(random, 1, 7);
                // int[] thisRecurDays = new int[numRecurDays];

                // for(int j = 0; j < numRecurDays; j++) {
                // 	thisRecurDays[j] = ValuesGenerator.getRandomIntBetween(random, 1, 7);
                // }
                



                // // recurDaysList.add(thisRecurDays);

                // testAppointment.setRecurrence(thisRecurDays, 1, 0, 0);
                // thisRecurDays = null;
                // testAppointment.setRecurrence(thisRecurDays, 1, 0, 0);
        }
        assertEquals(numAdded, testCalDay.getSizeAppts());
    }

}