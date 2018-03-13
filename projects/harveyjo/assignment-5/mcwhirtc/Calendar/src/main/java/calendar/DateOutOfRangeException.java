/*
 * DateOutOfRangeException.java
 *
 */

package calendar;

public class DateOutOfRangeException extends java.lang.Exception {

    /**
     * Creates new <code>DateOutOfRangeException</code> without detail message.
     */
    public DateOutOfRangeException() {
    }


    /**
     * Constructs an <code>DateOutOfRangeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DateOutOfRangeException(String msg) {
        super("Exception in thread \"main\" calendar.InvalidTime: Data in military time. File could not be saved. \n\tat calendar.Appt.setStartHour(Appt.java:134)");
    }
}


