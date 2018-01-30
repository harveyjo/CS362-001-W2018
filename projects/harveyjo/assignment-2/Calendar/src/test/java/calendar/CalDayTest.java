package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {


	 @Test
	  public void testCalDay()  throws Throwable  {
		CalDay calDay = new CalDay();
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		CalDay calDayValid = new CalDay(testCalendar);
		assertFalse(calDay.isValid());
		assertFalse(calDayValid.isValid());
	 }
	 @Test
	  public void test02()  throws Throwable  {
		 
	 }
	 
	 @Test
	 public void testaddAppt() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);
		int sizeBefore = calDay.getAppts().size();
		
		// Check that the size increments after adding appt
		calDay.addAppt(testAppt);
		assertEquals(sizeBefore + 1, calDay.getAppts().size());

	 }

	 @Test
	 public void testGetters() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		CalDay calDay = new CalDay(testCalendar);

		assertEquals(calDay.getDay(), 1);
		assertEquals(calDay.getMonth(), 1);
		assertEquals(calDay.getYear(), 2018);
	 }


	 @Test 
	 public void testToString() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testInvalidAppt = new Appt(-1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);
		int sizeBefore = calDay.getAppts().size();
		
		// Check that the size increments after adding appt
		// calDay.addAppt(testAppt);

		testInvalidAppt.toString();


	 }


	 @Test 
	 public void testIterator() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);

		assertNull(calDay.iterator());

		calDay.addAppt(testAppt);

		calDay.iterator();
	 }

	 @Test
	 public void testAddAppt() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testInvalidAppt = new Appt(-1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testApptTwo = new Appt(2, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);

		calDay.addAppt(testAppt);
		calDay.addAppt(testApptTwo);
		calDay.addAppt(testInvalidAppt);
	 }

	 @Test 
	 public void testIsValid() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		CalDay calDay = new CalDay(testCalendar);
		CalDay invalidCalDay = new CalDay();

		assertTrue(calDay.isValid());
		assertFalse(invalidCalDay.isValid());
	 }

//add more unit tests as you needed	
}
