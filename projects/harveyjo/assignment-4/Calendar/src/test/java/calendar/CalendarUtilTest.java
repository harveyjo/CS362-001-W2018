package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalendarUtil class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarUtilTest {


	 @Test
	  public void testNumDaysInMonth()  throws Throwable  {
		assertEquals(31, CalendarUtil.NumDaysInMonth(2018, 6));
		assertEquals(29, CalendarUtil.NumDaysInMonth(2016, 1));
	 }

	 @Test
	  public void testIsLeapYear()  throws Throwable  {
		assertFalse(CalendarUtil.IsLeapYear(2018));
		assertTrue(CalendarUtil.IsLeapYear(2016));
		assertTrue(CalendarUtil.IsLeapYear(400));
		assertFalse(CalendarUtil.IsLeapYear(500));
	 }
	 

//add more unit tests as you needed	
}
