package calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;

import static org.junit.Assert.*;


/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	 @Test(expected = DateOutOfRangeException.class)
	  public void radnomtest()  throws Throwable  {
        TimeTable testTimeTable = new TimeTable();



        long randomseed = System.currentTimeMillis(); //10
		Random random = new Random(randomseed);
		// Construct a random number of test appointments between 500 and 1000
		int numTests = ValuesGenerator.getRandomIntBetween(random, 500, 25000);
		LinkedList<Appt> testAppts = new LinkedList<Appt>();
		ArrayList<int[]> recurDaysList = new ArrayList<int[]>();
		

		for(int i = 0; i < numTests; i++) {
			Boolean isValidTest = true;
			int startHour = ValuesGenerator.getRandomIntBetween(random, -100, 100);
			int startMinute = ValuesGenerator.getRandomIntBetween(random, -100, 100);
			int startDay = ValuesGenerator.getRandomIntBetween(random, -100, 100);
			int startYear = ValuesGenerator.getRandomIntBetween(random, -100, 100);
			int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
            int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
            GregorianCalendar firstDay =  new GregorianCalendar(startYear, startMonth, 5);
            GregorianCalendar secondDay =  new GregorianCalendar(startYear, startMonth + 1, 9);

			String title = ValuesGenerator.getString(random);
			String description = ValuesGenerator.getString(random);

			if((startHour < 0 || startHour > 23) ||
			   (startMinute < 0 || startMinute > 59) ||
			   (startDay < 1 || startDay >NumDaysInMonth) ||
			   (startMonth<1 || startMonth > 12)
			) {
				isValidTest = false;
			} 

            Appt testAppointment = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
            Appt testAppointment2 = new Appt(5, startMinute, startDay, startMonth, startYear, title, description);
            testAppts.add(testAppointment);
            testAppts.add(testAppointment2);
			
			// if the startMonth is wrong, expect an 
			// new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
			if(isValidTest) {
                assertNotNull(testTimeTable.getApptRange(testAppts, firstDay, secondDay));
                assertNull(testTimeTable.getApptRange(testAppts, secondDay, firstDay));
                assertNotNull(testTimeTable.deleteAppt(testAppts, testAppointment));
			} else {
                assertNull(testTimeTable.deleteAppt(testAppts, testAppointment));
                assertNull(testTimeTable.deleteAppt(testAppts, null));
                assertNull(testTimeTable.deleteAppt(null, testAppointment));
            }
          
		}

	 }


	
}
