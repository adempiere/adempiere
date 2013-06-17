package org.adempiere.util.time;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Code taken from the book "Test Driven", Chapter 7 ("Test-driving the
 * unpredictable") by Lasse Koskela.
 * 
 * @author ts
 * 
 */
public final class SystemTime {

	private static final TimeSource defaultTimeSource = new TimeSource() {
		public long millis() {
			return System.currentTimeMillis();
		}

	};

	private static TimeSource timeSource;

	public static long millis() {
		return getTimeSource().millis();
	}

	public static GregorianCalendar asGregorianCalendar() {

		final GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(millis());

		return cal;
	}

	public static Date asDate() {

		return new Date(millis());
	}
	
	public static Timestamp asTimestamp() {

		return new Timestamp(millis());
	}

	/**
	 * Same as {@link #asTimestamp()} but the returned date will be truncated to DAY.
	 * 
	 * @return
	 */
	public static Timestamp asDayTimestamp()
	{
		final GregorianCalendar cal = asGregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}

	private static TimeSource getTimeSource() {
		return timeSource == null ? defaultTimeSource : timeSource;
	}

	/**
	 * After invocation of this method, the time returned will be the system
	 * time again.
	 */
	public static void resetTimeSource() {
		timeSource = null;
	}

	/**
	 * 
	 * @param newTimeSource
	 *            the given TimeSource will be used for the time returned by the
	 *            methods of this class (unless it is null).
	 * 
	 */
	public static void setTimeSource(TimeSource newTimeSource) {
		timeSource = newTimeSource;
	}
}
