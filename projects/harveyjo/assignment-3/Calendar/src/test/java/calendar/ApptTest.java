package calendar;
/**
*  This class provides a basic set of test cases for the
*  Appt class.
*/
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
	/**
	* Test that the gets methods work as expected.
	*/
	@Test
	public void test01()  throws Throwable  {
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
		// assertions
		assertTrue(appt.getValid());
		assertEquals(21, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(2, appt.getRecurBy());
		assertEquals(15, appt.getStartDay());
		assertEquals(01, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription());         		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetters() throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		
		
		int newStartHour = 22;
		int newStartMinute = 31;
		int newStartDay = 16;
		int newStartMonth = 02;
		int newStartYear = 2019;
		
		String newTitle = "Heavy Night of Drinking";
		String newDescription = "You know what it is.";
		
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);


		Appt invalidHour = new Appt(-1,
		startMinute ,
		startDay ,
		startMonth ,
		startYear ,
		title,
		description);

		assertFalse(invalidHour.getValid());
		invalidHour.setStartHour(startHour);
		assertTrue(invalidHour.getValid());
		invalidHour.setStartMinute(-1);
		assertFalse(invalidHour.getValid());
		invalidHour.setStartMinute(startMinute);
		assertTrue(invalidHour.getValid());
		appt.setStartMonth(12);
		assertTrue("The appointment month cause invalidation", invalidHour.getValid());
		appt.setStartMonth(13);
		assertFalse(invalidHour.getValid());
		appt.setStartMonth(12);
		assertTrue(invalidHour.getValid());
		appt.setStartYear(-1);
		assertFalse(invalidHour.getValid());
		appt.setStartYear(1);
		assertTrue(invalidHour.getValid());
		appt.setStartDay(-1);
		assertFalse(invalidHour.getValid());
		appt.setStartDay(1);
		assertTrue(invalidHour.getValid());

		assertTrue(appt.getValid());
		appt.setStartDay(42);
		assertFalse(appt.getValid());
		appt.setStartHour(25);
		appt.setStartMinute(67);
		appt.setStartMonth(13);
		assertFalse(appt.getValid());
		
		// appt.setStartYear(startYear);
		
		appt.setStartHour(newStartHour);
		appt.setStartMinute(newStartMinute);
		appt.setStartDay(newStartDay);
		appt.setStartMonth(newStartMonth);
		appt.setStartYear(newStartYear);
		appt.setTitle(newTitle);
		appt.setDescription(newDescription);
		
		assertEquals(newStartHour, appt.getStartHour());
		assertEquals(newStartMinute, appt.getStartMinute());
		assertEquals(newStartDay, appt.getStartDay());
		assertEquals(newStartMonth, appt.getStartMonth());
		assertEquals(newStartYear, appt.getStartYear());
		assertEquals(newTitle, appt.getTitle());
		assertEquals(newDescription, appt.getDescription());  

		assertNotEquals("", appt.getTitle());
		assertNotEquals("", appt.getDescription());
		appt.setTitle(null);
		appt.setDescription(null);
		assertEquals("", appt.getTitle());
		assertEquals("", appt.getDescription());
		

		
	}
	
	// @Test
	// public void testIsValid() {
	// 	int startHour=21;
	// 	int startMinute=30;
	// 	int startDay=15;
	// 	int startMonth=01;
	// 	int startYear=2018;
	// 	String title="Birthday Party";
	// 	String description="This is my birthday party.";
		
	// 	Appt invalidHour01 = new Appt(-1,
	// 	startMinute ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	Appt invalidHour02 = new Appt(25,
	// 	startMinute ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	assertFalse(invalidHour01.getValid());
	// 	assertFalse(invalidHour02.getValid());

	// 	Appt invalidMinute01 = new Appt(startHour,
	// 	-1 ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	Appt invalidMinute02 = new Appt(startHour,
	// 	60 ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	assertFalse(invalidMinute01.getValid());
	// 	assertFalse(invalidMinute02.getValid());

	// 	Appt invalidDay01  = new Appt(startHour,
	// 	startMinute ,
	// 	-1,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	Appt invalidDay02  = new Appt(startHour,
	// 	startMinute ,
	// 	40,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	assertFalse(invalidDay01.getValid());
	// 	assertFalse(invalidDay02.getValid());

	// 	Appt invalidMonth01  = new Appt(startHour,
	// 	startMinute ,
	// 	startDay ,
	// 	-1 ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	Appt invalidMonth02  = new Appt(startHour,
	// 	startMinute ,
	// 	startDay ,
	// 	52 ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	assertFalse(invalidMonth01.getValid());
	// 	assertFalse(invalidMonth02.getValid());

	// 	Appt invalidYear01  = new Appt(startHour,
	// 	startMinute ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	Appt invalidYear02  = new Appt(startHour,
	// 	startMinute ,
	// 	startDay ,
	// 	startMonth ,
	// 	startYear ,
	// 	title,
	// 	description);

	// 	assertFalse(invalidYear01.getValid());
	// 	assertFalse(invalidYear02.getValid());



	// 	// //Construct a new Appointment object with the initial data	 
	// 	// Appt appt = new Appt(startHour,
	// 	// startMinute ,
	// 	// startDay ,
	// 	// startMonth ,
	// 	// startYear ,
	// 	// title,
	// 	// description);
		
		
		
	// }
	
	// @Test 
	// public void testRecurrenceMethods() {
		// 	int startHour=21;
		// 	int startMinute=30;
		// 	int startDay=15;
		// 	int startMonth=01;
		// 	int startYear=2018;
		// 	String title="Birthday Party";
		// 	String description="This is my birthday party.";
		
		// 	Appt appt = new Appt(startHour,
		// 	startMinute ,
		// 	startDay ,
		// 	startMonth ,
		// 	startYear ,
		// 	title,
		//    description);
		
		// 	appt.setRecurBy();
		
		
		
		
		// }


		@Test 
		public void testCompareTo() {
			int startHour=0;
			int startMinute=30;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Birthday Party";
			String description="This is my birthday party.";
			
			Appt appt = new Appt(startHour,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			Appt invalidHour = new Appt(-1,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			Appt secondAppt = new Appt(2,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			assertTrue((appt.compareTo(invalidHour) > 0));
			assertEquals(1, appt.compareTo(invalidHour));
			assertEquals(-2, appt.compareTo(secondAppt));
		}

		@Test 
		public void testToString() {
			int startHour=0;
			int startMinute=30;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Birthday Party";
			String description="This is my birthday party.";
			
			Appt appt = new Appt(startHour,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			assertTrue((appt.toString().length() > 0));
			assertEquals(0, appt.getRecurNumber());

			// assertEquals("1/15/2018 at 12:30am ,Birthday Party, This is my birthday party..", appt.toString().trim());

			Appt invalidHour = new Appt(-1,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			// Check that the string has some length greater than 0

			// Check that the string is not null

			Appt noonAppt = new Appt(12,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			assertEquals("1/15/2018 at 12:30am ,Birthday Party, This is my birthday party.", appt.toString().trim().replace("\n", "").replace("\r", ""));
			assertEquals("1/15/2018 at 12:30pm ,Birthday Party, This is my birthday party.", noonAppt.toString().trim().replace("\n", "").replace("\r", ""));			
			assertEquals(null, invalidHour.toString());
		}
		
		@Test
		public void testRecurrence()  throws Throwable  {
			int startHour=21;
			int startMinute=30;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Birthday Party";
			String description="This is my birthday party.";
			
			Appt testAppt = new Appt(startHour,
			startMinute ,
			startDay ,
			startMonth ,
			startYear ,
			title,
			description);

			testAppt.setRecurrence(null, 7, 5, 2);

			assertEquals(2, testAppt.getRecurNumber());
			assertEquals(7, testAppt.getRecurBy());
			assertEquals(0, testAppt.getRecurDays().length);
			assertEquals(5, testAppt.getRecurIncrement());
			assertEquals(true, testAppt.isRecurring());

			testAppt.setRecurrence(null, 7, 5, 0);
			assertEquals(false, testAppt.isRecurring());

		}
		//add more unit tests as you needed
		
	}
	