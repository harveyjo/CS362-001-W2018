package calendar;
/**
*  This class provides a basic set of test cases for the
*  TimeTable class.
*/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {
	
	@Test
	public void test01()  throws Throwable  {
		
	}
	@Test
	public void testDeleteAppt()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);

		Appt secondAppt = new Appt(startHour,
		-1 ,
		(startDay + 2),
		startMonth ,
		startYear ,
		title,
		description);
		
		
		TimeTable timeTable = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();

		appts.add(appt);
		appts.add(secondAppt);
		LinkedList<Appt> newAppts = timeTable.deleteAppt(appts, secondAppt);
		
		System.out.println("Appointments size is:");
		System.out.println(appts.size());
		// System.out.println(newAppts.size());

		assertNull(newAppts);
		assertNull(timeTable.deleteAppt(null, null));
		// LinkedList<Appt> afterDeletion = timeTable.deleteAppt(appts, appt);

		// assertEquals(1, afterDeletion.size());

		// assertEquals((appts.size() - 1), newAppts.size());
		
		// assertEquals(0, appts.size());
		// appts.add(appt);
		// assertEquals(1, appts.size());
		// timeTable.deleteAppt(appts, appt);
		// assertEquals(0, appts.size());
		
		
	}
	
	
	@Test
	public void testGetApptRange() throws Throwable {
		Calendar rightnow = Calendar.getInstance();
		//current month/year/date is today
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		TimeTable timeTable=new TimeTable();
		
		int startHour=-1;
		int startMinute=30;
		int startDay=thisDay;  	
		int startMonth=thisMonth; 	
		int startYear=2018; 	
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);
			 
		Appt secondAppt = new Appt(startHour,
		startMinute ,
	 	startDay + 1 ,
		startMonth ,
		startYear ,
		title,
		description);
		
		
		listAppts.add(appt);
		listAppts.add(secondAppt);
		
		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar lastDay = (GregorianCalendar)today.clone();
		lastDay.add(Calendar.DAY_OF_MONTH,2);
		
		LinkedList<CalDay> calDays = timeTable.getApptRange(listAppts, today, lastDay);
		assertEquals(2, calDays.size());
		
		
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPermute() throws Throwable {
		int startHour=-1;
		int startMinute=30;
		int startDay=11;  	
		int startMonth=5; 	
		int startYear=2018; 	
		String title="Birthday Party";
		String description="This is my birthday party.";

		TimeTable timeTable=new TimeTable();
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);
			 
		Appt secondAppt = new Appt(startHour,
		startMinute ,
	 	startDay + 1 ,
		startMonth ,
		startYear ,
		title,
		description);
	
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		
		
		listAppts.add(appt);
		listAppts.add(secondAppt);
		
		int[] pv = {1};

		LinkedList<Appt> newAppts = timeTable.permute(listAppts, pv);

		int[] invalidPv = {};

		assertEquals(listAppts.size(), newAppts.size());

		// Throw the expected excption
		timeTable.permute(listAppts, invalidPv);



	}
	
	
	
	//add more unit tests as you needed
}
