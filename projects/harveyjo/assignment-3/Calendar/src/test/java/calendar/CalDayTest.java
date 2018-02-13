package calendar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {


	//  @Test
	//   public void testCalDay()  throws Throwable  {
	// 	CalDay calDay = new CalDay();
	// 	GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
	// 	CalDay calDayValid = new CalDay(testCalendar);
	// 	assertFalse(calDay.isValid());
	// 	assertFalse(calDayValid.isValid());
	//  }
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
	 public void testSetAppts() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);
		LinkedList<Appt> appts = new LinkedList<Appt>();
		appts.add(testAppt);

	
		Method method = CalDay.class.getDeclaredMethod("setAppts", LinkedList.class);
		method.setAccessible(true);

		method.invoke(calDay, new LinkedList<Appt>());
		assertEquals(0, calDay.getAppts().size());
		method.invoke(calDay, appts);
		assertEquals(1, calDay.getAppts().size());


	 }

	//  @Test
	//  public void testToString() {
	// 	GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
	// 	Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
	// 	CalDay calDay = new CalDay(testCalendar);
	// 	int sizeBefore = calDay.getAppts().size();
		
	// 	// Check that the size increments after adding appt
	// 	calDay.addAppt(testAppt);
	// 	assertEquals(sizeBefore + 1, calDay.getAppts().size());

	//  }

	 @Test
	 public void testGetters() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		CalDay calDay = new CalDay(testCalendar);

		assertEquals(calDay.getDay(), 1);
		assertEquals(calDay.getMonth(), 1);
		assertEquals(calDay.getYear(), 2018);
		assertEquals(calDay.getSizeAppts(), 0);
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

		assertNotNull(testAppt);
		assertTrue((testAppt.toString().length() > 1));
		assertEquals("--- 1/1/2018 ---  --- -------- Appointments ------------ ---", calDay.toString().trim().replace("\n", "").replace("\r", ""));
		// assertEquals("1/1/2018 at 1:30am ,Appointment to test my program., Test description", testAppt.toString().trim());


	 }


	 @Test 
	 public void testIterator() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testInvalidAppt = new Appt(-1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testApptTwo = new Appt(2, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testApptThree = new Appt(3, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);
		CalDay calDayInvalid = new CalDay();

		assertNotNull(calDay.iterator());
		assertNull(calDayInvalid.iterator());

		calDay.addAppt(testAppt);

		calDay.iterator();
	 }

	 @Test
	 public void testAddAppt() {
		GregorianCalendar testCalendar = new GregorianCalendar(2018, 1, 1);
		Appt testInvalidAppt = new Appt(-1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testAppt = new Appt(1, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testApptTwo = new Appt(2, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		Appt testApptThree = new Appt(12, 30, 1, 1, 2018, "Appointment to test my program.", "Test description");
		CalDay calDay = new CalDay(testCalendar);

		calDay.addAppt(testApptTwo);
		calDay.addAppt(testAppt);
		calDay.addAppt(testApptThree);
		calDay.addAppt(testApptTwo);
		calDay.addAppt(testInvalidAppt);

		assertEquals(calDay.getAppts().get(0), testAppt);
		assertEquals(calDay.getAppts().get(3), testApptThree);
		assertEquals(4, calDay.getAppts().size());
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
