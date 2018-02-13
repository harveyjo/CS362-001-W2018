package calendar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
*  This class provides a basic set of test cases for the
*  TimeTable class.
*/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.*;


import org.junit.Test;
import org.omg.CosNaming._BindingIteratorImplBase;

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
		startMinute,
		(startDay + 2),
		startMonth ,
		startYear ,
		title,
		description);

		Appt thirdAppt = new Appt(startHour,
		startMinute + 5,
		(startDay + 2),
		startMonth ,
		startYear ,
		title,
		description);

		Appt invalidAppt = new Appt(-1,
		startMinute,
		(startDay + 2),
		startMonth ,
		startYear ,
		title,
		description);
		
		
		TimeTable timeTable = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		assertNull("Timetable should be null", timeTable.deleteAppt((LinkedList<Appt>)null, appt));
		assertNull("Timetable should be null", timeTable.deleteAppt(appts, appt));
		assertNull("Timetable should be null", timeTable.deleteAppt(appts, invalidAppt));
		

		appts.add(appt);
		appts.add(secondAppt);
		appts.add(thirdAppt);
		LinkedList<Appt> newAppts = timeTable.deleteAppt(appts, secondAppt);
		assertNotNull(newAppts);
		assertEquals(2, newAppts.size());

		newAppts = timeTable.deleteAppt(appts, thirdAppt);
		assertNull(newAppts);
		
		System.out.println("Appointments size is:");
		System.out.println(appts.size());
		// System.out.println(newAppts.size());
		// assertNotNull("Timetable should not be null", timeTable.deleteAppt(appts, appt));
		
		// assertNull(timeTable.deleteAppt(null, null));
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
		LinkedList<Appt> nullAppts = new LinkedList<Appt>();
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
	 	thisDay,
		thisMonth ,
		thisYear ,
		title,
		description);

		int[] recurDaysArr={2,3,4};
		secondAppt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

		Appt invalidAppt = new Appt(-1,
		-90 ,
	 	startDay + 1 ,
		startMonth ,
		startYear ,
		title,
		description);
		
		listAppts.add(invalidAppt);

		
		
		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar lastDay = (GregorianCalendar)today.clone();
		lastDay.add(Calendar.DAY_OF_MONTH,7);
		LinkedList<CalDay> calDays = timeTable.getApptRange(nullAppts, today, lastDay);

		assertEquals(0, calDays.get(0).getAppts().size());
		listAppts.add(appt);
		listAppts.add(secondAppt);

		calDays = timeTable.getApptRange(listAppts, today, lastDay);
		assertEquals(7, calDays.size());

		
		
		
		
		
		
	}

	@Test
	public void testGetNextApptOccurence() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		TimeTable timeTable = new TimeTable();

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

		Appt recurAppt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);

		Appt recurMonthly = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);

		Appt recurDaysNullAppt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);


		Method method = TimeTable.class.getDeclaredMethod("getNextApptOccurrence", Appt.class, GregorianCalendar.class);
		method.setAccessible(true);
		CalDay calDay = (CalDay) method.invoke(timeTable, appt, today);
		assertNull(calDay);


		int[] recurDaysArr={2,3,4};
		recurAppt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		GregorianCalendar returnedDay = (GregorianCalendar) method.invoke(timeTable, recurAppt, today);
		assertNotNull(returnedDay);

		int[] recurDaysNew={2,3,4,1};
		int[] recurDaysNull={};
		recurAppt.setRecurrence( recurDaysNew, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		GregorianCalendar returnedDayNew = (GregorianCalendar) method.invoke(timeTable, recurAppt, today);
		assertEquals(returnedDay, returnedDayNew);

		recurAppt.setRecurrence( recurDaysNew, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
		GregorianCalendar returnedDayMonthly = (GregorianCalendar) method.invoke(timeTable, recurMonthly, today);

		assertNotEquals(returnedDayMonthly, returnedDayNew);
		assertNotEquals(returnedDay, returnedDayMonthly);

		// GregorianCalendar shouldBeNull =

		recurDaysNullAppt.setRecurrence( recurDaysNull, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
		GregorianCalendar returnedDayNull = (GregorianCalendar) method.invoke(timeTable, recurMonthly, today);
		assertNull(returnedDayNull);
		// GregorianCalendar lastDay = (GregorianCalendar)returnedDayNull.clone();
		// lastDay.add(lastDay.DAY_OF_MONTH, 1);
		// lastDay.add(Calendar.DAY_OF_MONTH,7);
		// assertEquals(returnedDayNull,  lastDay);

	}

	@Test
	public void testGetApptOccurences() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Calendar rightnow = Calendar.getInstance();
		//current month/year/date is today
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);

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
		
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();

				//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar nextDay = (GregorianCalendar)today.clone();
		GregorianCalendar lastDay = (GregorianCalendar)today.clone();
		lastDay.add(Calendar.DAY_OF_MONTH,4);

		TimeTable timeTable = new TimeTable();


		Method method = TimeTable.class.getDeclaredMethod("getApptOccurences", Appt.class, GregorianCalendar.class, GregorianCalendar.class);
		method.setAccessible(true);
		LinkedList<CalDay> calDays = (LinkedList<CalDay>) method.invoke(timeTable, appt, today, nextDay);
		assertEquals(0, calDays.size());

		calDays = (LinkedList<CalDay>) method.invoke(timeTable, appt, today, lastDay);
		assertEquals(1, calDays.size());
		// method.invoke(calDay, appts);
		// assertEquals(1, calDay.getAppts().size());


		

		// Method method = TimeTable.class.getDeclaredMethod("getApptOccurences", Appt.class, GregorianCalendar.class, GregorianCalendar.class);
		// method.setAccessible(true);

		
		// method.invoke(timeTable, appt, today, lastDay);
		// method.invoke(calDay, new LinkedList<Appt>());
		// assertEquals(0, calDay.getAppts().size());
		// method.invoke(calDay, appts);
		// assertEquals(1, calDay.getAppts().size());
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

		Appt thirdAppt = new Appt(startHour,
		startMinute + 2 ,
	 	startDay + 1 ,
		startMonth ,
		startYear ,
		title,
		description);
	
	
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		
		
		listAppts.add(appt);
		listAppts.add(secondAppt);
		listAppts.add(thirdAppt);

		int[] validPv = {2, 0, 1};

		System.out.println("&&^^&%&^$%^$^%#^$#$%%#PV LENGTH:");
		System.out.println(validPv.length);
		System.out.println(listAppts.size());
		LinkedList<Appt> newAppts = new LinkedList<Appt>();

		assertEquals(0, newAppts.size());
		assertNotEquals(listAppts, newAppts);
		assertNotEquals(listAppts.get(0), listAppts.get(1));
		assertEquals(listAppts.size(), validPv.length);
		newAppts = timeTable.permute(listAppts, validPv);	
		assertEquals(listAppts.size(), newAppts.size());
		assertEquals(listAppts, newAppts);
		assertNotEquals(newAppts.get(0), newAppts.get(1));

	
		// assertNotEquals(listAppts.get(0), newAppts.get(0)); // Some permutation happened
		
		int[] invalidPv = {1, 2};
		// Should raise an exception
		assertNotEquals(listAppts.size(), invalidPv.length);
		newAppts = timeTable.permute(listAppts, invalidPv);




		// listAppts.add(secondAppt); 
		// newAppts = timeTable.permute(listAppts, pv);
		// assertNotNull(newAppts);

		// // Throw the expected excption
		// timeTable.permute(listAppts, invalidPv);



	}
	
	
	
	//add more unit tests as you needed
}
