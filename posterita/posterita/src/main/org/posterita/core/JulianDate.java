/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 29-Jul-2005 by alok
 *
 */
 
package org.posterita.core;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * A light weight date class stored in julian format.
 */
public class JulianDate implements Comparable
{
   /**
	* The end of time.
	*/
	public static final JulianDate MAX_VALUE = new JulianDate(Integer.MAX_VALUE);

	/**
	 * Beginning of AD
	 */
	public static final int INT_REAL_MIN_VALUE = 1721424;
	public static final JulianDate MIN_VALUE = new JulianDate(0);

	/**
	 * Calculator used to convert java.util.Dates.
	 */
	protected static final GregorianCalendar gc = new GregorianCalendar();

	/**
	 * Julian date, defaults to January 1'st, 1970
	 */
	protected int julian = 2440588;

	/**
	 * No argument constructor.
	 */
	public JulianDate() {}

	/**
	 * Constructs a date according to the specified year, month, and day.
	 * <p>
	 * Entering an illegal value (e.g., a 14 for a month, or 31 for the number
	 * of days in February) results in a date of unpredictable value.
	 *
	 * @param year  The year expressed as a 4-digit number. For example, 2002
	 * @param month The month expressed as an integer between 1 and 12
	 * @param day   The day of the month, expressed as a number between 1 up to 31
	 *
	 */
	public JulianDate(int year, int month, int day)
	{
		YMD ymd = new YMD(year, month, day);

		julian = ymd.julian();
	}
	
		
	/**
	 * Constructs a julian date from a julian date value.
	 */
	public JulianDate(int julian)
	{
		this.julian = julian;
	}

	/**
	 * Constructs a julian date from another julian date.
	 */
	public JulianDate(JulianDate d)
	{
		julian = d.julian;
	}

	/**
	 * Constructs a julian date from a java date.
	 */
	public JulianDate(Date date)
	{
		julian = toJulian(date);
	}

	/**
	 * Compares the current object and the passed-in object for equality.
	 *
	 * @param obj the object to compare with the current object.
	 * @return true if the two objects are equal, false otherwise.
	 */
	public boolean equals(Object obj)
	{
		try
		{
			JulianDate d = (JulianDate)obj;

			return (julian == d.julian);
		}
		catch (Exception ignore) {}

		return false;
	}

	/**
	 * Returns the hash code of this object.
	 *
	 * @return the hash code of this object.
	 */
	public int hashCode()
	{
		return julian;
	}

	/**
	 *
	 * Checks if the specified date is eual to this date.
	 *
	 * @return <em>true</em> if the specified object equals this object,
	 * otherwise returns <em>false</em>.
	 */
	public boolean equals(JulianDate d)
	{
		return (julian == d.julian);
	}

	/**
	 * Returns the int difference between two julian dates.
	 *
	 * @param d the julian date whose value will be subtracted from this date.
	 *
	 * @return the difference betwween the julian value of this date and the
	 * julian value of the specified date.
	 */
	public int difference(JulianDate d)
	{
		return (julian - d.julian);
	}

	/**
	 * Returns the julian value of this date.
	 *
	 * @return the julian value of this date.
	 */
	public int getJulian()
	{
		return julian;
	}
	
	public Integer getJulianInt()
	{
		return Integer.valueOf(julian);
	}

	/**
	 * Method used to create a timestamp. must have nonjulian time.
	 *
	 * @return the mseconds that are in the date(non Julian).
	 */
	public long getStandardTime()
	{
		return getJavaDate().getTime();
	}

	/**
	 * Returns a Long that is the last MSecond Of the Date Object.
	 *
	 * @return the last MSecond Of the Date Object.
	 */
	public long getLastMSDate()
	{
		JulianDate startOfDayTime = new JulianDate(this.getYear(), this.getMonth(), this.getDay());
		return (startOfDayTime.getStandardTime() + (24 * 60 * 60 * 1000 - 1));
	}

	/**
	 * Returns the day of the present year in which this date falls.
	 * <p>
	 * The 1'st of January is day 0.
	 *
	 * @return the day of the present year in which this date falls.
	 */
	public int getDay()
	{
		YMD ymd = new YMD(julian);

		return ymd.day;
	}

	/**
	 * Returns the month of the present year in which this date falls.
	 * <p>
	 * January is month 0.
	 *
	 * @return the month of the present year in which this date falls.
	 */
	public int getMonth()
	{
		YMD ymd = new YMD(julian);

		return ymd.month;
	}

	/**
	 * Returns the year in which this date falls.
	 *
	 * @return the year in which this date falls.
	 */
	public int getYear()
	{
		YMD ymd = new YMD(julian);

		return ymd.year;
	}

	/**
	 * Checks if this date is on or before the input date.
	 *
	 * @return <em>true</em> if this date is on or before the input date,
	 * <em>false</em> otherwise.
	 */
	public boolean isOnOrBefore(JulianDate d)
	{
		return (julian < d.julian || julian == d.julian);
	}


	/**
	 * Checks if this date is on or after the input date.
	 *
	 * @return <em>true</em> if this date is on or after the input date,
	 * <em>false</em> otherwise.
	 */
	public boolean isOnOrAfter(JulianDate d)
	{
		return (julian > d.julian || julian == d.julian);
	}

	/**
	 * Checks if this date is before the input date.
	 *
	 * @return <em>true</em> if this date is before the input date,
	 * <em>false</em> otherwise.
	 */
	public boolean isBefore(JulianDate d)
	{
		return (julian < d.julian);
	}

	/**
	 * Checks if this date is after the input date.
	 *
	 * @return <em>true</em> if this date is before the input date,
	 * <em>false</em> otherwise.
	 */
	public boolean isAfter(JulianDate d)
	{
		return (julian > d.julian);
	}

	/**
	 * Shifts the adjusted version by the <code>offset</code>, interpreted
	 * as a number of calendar dates, and sets the unadjusted version to be
	 * equal to the adjusted version.
	 * For example, if initially the unadjusted date is December 25, and the
	 * adjusted date is December 26, then shifting by an <code>shift</code> of 2
	 * would cause the adjusted and unadjusted version to be December 28.
	 *
	 * @return the shifted date.
	 */
	public JulianDate shift(int shift)
	{
		JulianDate date = new JulianDate(this);

		date.julian += shift;

		return date;
	}

	/**
	 * Returns a <code>java.util.Date</code> representation of the adjusted
	 * version of <code>this</code>.
	 *
	 * return a <code>java.util.Date</code> representation of the adjusted
	 * version of <code>this</code>.
	 */
	public java.util.Date getJavaDate()
	{
		return fromJulian(julian);
	}

	/**
	 * Returns a <code>java.sql.Date</code> representation of the unadjusted
	 * version of <code>this</code>.
	 *
	 * @return a <code>java.sql.Date</code> representation of the unadjusted
	 * version of <code>this</code>.
	 */
	public java.sql.Date getSqlDate()
	{
		return new java.sql.Date(getJavaDate().getTime());
	}

	/**
	 * Returns a <code>java.sql.Date</code> representation of the adjusted
	 * version of <code>this</code>.
	 *
	 * @return a <code>java.sql.Date</code> representation of the adjusted
	 * version of <code>this</code>.
	 */
	public java.sql.Date getAdjSqlDate()
	{
		return new java.sql.Date(getJavaDate().getTime());
	}

	/**
	 * Add the given number of period units to this date.
	 */
	public JulianDate add(int units, int amount)
	{
		JulianDate date = null;

		synchronized (gc)
		{
			gc.setTime(getJavaDate());
			gc.add(units, amount);

			date = new JulianDate(gc.getTime());
		}

		return date;
	}

	/**
	 * Convert a Java Date to it's Julian equivalent.
	 *
	 * @param the specified java date
	 * @return the corresponding julian value.
	 */
	protected int toJulian(java.util.Date date)
	{
		YMD ymd = null;

		synchronized (gc)
		{
			gc.setTime(date);

			ymd = new YMD(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH) + 1,    // GC month starts at 0
			gc.get(GregorianCalendar.DAY_OF_MONTH));
		}

		return ymd.julian();
	}

	/**
	 * Convert a Julian to it's Java Date equivalent.
	 *
	 * @param the specified julian value.
	 * @return the corresponding java date.
	 */
	protected java.util.Date fromJulian(int julian)
	{
		YMD               ymd = new YMD(julian);
		GregorianCalendar cal = new GregorianCalendar(ymd.year, ymd.month - 1, ymd.day);

		return cal.getTime();
	}

	/**
	 * Returns the day of the week of this date.
	 *
	 * @return the day of the week of this date.
	 */
	public int toDayOfWeek()
	{
		return new YMD(julian).toDayOfWeek(this);
	}

	/**
	 * Returns a string representation of this object.
	 *
	 * @return a string representation of this object.
	 */
	public String toString()
	{
		DateFormat shortDF = DateFormat.getDateInstance(DateFormat.SHORT);

		return shortDF.format(fromJulian(julian));
	}

	/**
	 * Convert between Greogorian dates and Julian dates.
	 * <p>
	 * Algorithm adapted from <em>Press et al., Numerical Recipes in C, 2nd ed.,
	 * Cambridge University Press 1992</em>
	 */
	protected class YMD
	{

		/**
		 * Gregorian day of month (1-31).
		 */
		int day = 0;

		/**
		 * Gregorian month of year (1-12).
		 */
		int month = 0;

		/**
		 * Gregorian year.
		 */
		int year = 0;

		YMD(int year, int month, int day)
		{
			this.year = year;
			this.month = month;
			this.day = day;
		}

		YMD(int julian)
		{
			if (julian < 0)
			{
				julian = 0;

			}

			int a = julian;

			if (julian >= 2299161)
			{
				int jadj = (int)(((float)(julian - 1867216) - 0.25) / 36524.25);

				a += 1 + jadj - (int)(0.25 * jadj);
			}

			int b = a + 1524;
			int c = (int)(6680.0 + ((float)(b - 2439870) - 122.1) / 365.25);
			int d = (int)(365 * c + (0.25 * c));
			int e = (int)((b - d) / 30.6001);

			day = b - d - (int)(30.6001 * e);
			month = e - 1;

			if (month > 12)
			{
				month -= 12;

			}

			year = c - 4715;

			if (month > 2)
			{
				--year;

			}

			if (year <= 0)
			{
				--year;
			}
		}

		int julian()
		{
			int y = year;

			if (year < 0)
			{
				y++;

			}

			int m = month;

			if (month > 2)
			{
				m++;
			}
			else
			{
				y--;
				m += 13;
			}

			int julian = (int)(java.lang.Math.floor(365.25 * y) + java.lang.Math.floor(30.6001 * m) + day + 1720995.0);

			int yearZero = 15 + 31 * (10 + 12 * 1582);

			if (day + 31 * (month + 12 * year) >= yearZero)
			{
				int jadj = (int)(0.01 * y);

				julian += 2 - jadj + (int)(0.25 * jadj);
			}

			return julian;
		}

		public final int toDayOfWeek(JulianDate julDate)
		{

			long julian = julDate.getDay();
			// If julian is negative, then julian%7 will be negative, so we adjust
			// accordingly.  We add 1 because Julian day 0 is Monday.
			int  dayOfWeek = (int)((julian + 1) % 7);

			return dayOfWeek + ((dayOfWeek < 0) ? (7 + Calendar.SUNDAY) : Calendar.SUNDAY);
		}

	}

	/**
	 * Gets the day of week of the julian day, following the <code>Calendar</code>
	 * convention:
	 * <li> Calendar.SUNDAY = 1
	 * <li> Calendar.MONDAY = 2
	 * <li> ...
	 * <li> Calendar.SATURDAY = 7
	 *
	 * @return gets the day of week of the julian day
	 */
	public static int getDayOfWeek(int julian)
	{
		// If julian is negative, then julian%DAYS_IN_WEEK will be negative, so we adjust
		// accordingly.  We add 1 because Julian day 0 is Monday.
		int dayOfWeek = (int)((julian + 1) % DAYS_IN_WEEK);

		return dayOfWeek + ((dayOfWeek < 0) ? (DAYS_IN_WEEK + Calendar.SUNDAY) : Calendar.SUNDAY);
	}

	/**
	 * Number of days in a week.
	 */
	public static final int DAYS_IN_WEEK = 7;

	/**
	 * Checks if a year is a leap year.
	 *
	 * @param aYear the year to check
	 * @eturn true if the specified year is a leap year, false otherwise.
	 */
	public static final boolean isLeapYear(int aYear)
	{
		return ((aYear % 4 == 0) && ((aYear % 100 != 0) || (aYear % 400 == 0)));
	}

	/**
	 * Checks if the period contains the 29th of February.
	 *
	 * @param start the start date of the period
	 * @param end the end date of the period
	 * Returns true if the period contains the 29th of February, false otherwise.
	 */
	public static final boolean containsFebruary29AsAdj(JulianDate start, JulianDate end)
	{
		int startYear = start.getYear();
		int endYear = end.getYear();

		if (isLeapYear(startYear))
		{
			if (start.isOnOrBefore(new JulianDate(startYear, 2, 29)))
			{
				if (end.isOnOrAfter(new JulianDate(startYear, 2, 29)))
				{
					return true;
				}
			}
		}
		else if (isLeapYear(endYear))
		{
			if (end.isOnOrAfter(new JulianDate(endYear, 2, 29)))
			{
				if (start.isOnOrBefore(new JulianDate(endYear, 2, 29)))
				{
					return true;
				}
			}
		}

		return false;
	}


	/**
	 * Checks if this date is at the end of the month.
	 *
	 * Returns true if the date is on the last day of the month, false otherwise.
	 */
	public boolean isEndOfMonth()
	{
		return equals(getLastDayOfMonth());
	}

	/**
	 * Returns the last day of the specified month.
	 *
	 * @param aMonth the specified month
	 * @param aYear the specified year
	 * @return the last <code>JulianDate</code> of this month.
	 */
	public static final JulianDate getLastDayOfMonth(int aMonth, int aYear)
	{
		return getFirstDayOfNextMonth(aMonth, aYear).shift(-1);
	}

	/**
	 * Returns the last day of the month in which this date falls.
	 *
	 * @return the last day of the month in which this date falls.
	 */
	public JulianDate getLastDayOfMonth()
	{
		return getLastDayOfMonth(this.getMonth(), this.getYear());
	}

	/**
	 * Returns the day following the specified month.
	 *
	 * @param aMonth the soecified month.
	 * @param aYear the specified year.
	 * @return the first <code>JulianDate</code> of the next month.
	 */
	public static final JulianDate getFirstDayOfNextMonth(int aMonth, int aYear)
	{
		if (aMonth == Calendar.DECEMBER + 1)    // Calendar month starts at 0
		{
			return new JulianDate(aYear + 1, 1, 1);
		}
		else
		{
			return new JulianDate(aYear, aMonth + 1, 1);
		}
	}

	/**
	 * Get today as a <code>JulianDate</code>.
	 *
	 * @return today as a <code>JulianDate</code>..
	 */
	public static final JulianDate getToday()
	{
		Calendar rightNow = Calendar.getInstance();

		// Calendar.MONTH starts at 0!!
		return new JulianDate(rightNow.get(Calendar.YEAR),
							  rightNow.get(Calendar.MONTH) + 1,
							  rightNow.get(Calendar.DAY_OF_MONTH));
	}

	public int compareTo(Object o)
	{
		JulianDate date = (JulianDate) o;
		return this.difference(date);
	
	}
	
	
	public static Timestamp getTodayDateOnly()
	{
	    Calendar cal = Calendar.getInstance();
	    
	    cal.set(Calendar.HOUR_OF_DAY,0);
	    cal.set(Calendar.MINUTE,0);
	    cal.set(Calendar.SECOND,0);
	    cal.set(Calendar.MILLISECOND,0);
	    
	    Timestamp today = new Timestamp(cal.getTimeInMillis());
	    
	    return today;
	}
	

}