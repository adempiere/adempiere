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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.OperationException;

public class TimestampConvertor 
{
	
	public static final String XML_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
	public static final String DEFAULT_DATE_PATTERN1 = "dd/MM/yyyy HH:mm:ss";
	public static final String DEFAULT_DATE_PATTERN2 = "dd-MMM-yyyy HH:mm:ss";
	public static final String FYRACLE_DATETIME_PATTERN = "MM-dd-yyyy HH:mm:ss";
	public static final String FYRACLE_DATE_PATTERN = "MM-dd-yyyy";
	public static final String FYRACLEDB_DATETIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
	public static final String NORM_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String FLAT_DATE_PATTERN = "ddMMyyyy";
	public static final String REPORTS_DATE_PATTERN = "dd-MMM-yyyy HH:mm:ss";
	public static final String SYNC_DATE_TIME = "yyyy-MM-dd HH:mm";
	public static final String BIRTH_DATE = "dd/MM/yyyy";
	
	public static Timestamp getTimestamp(String timeStr) throws OperationException 
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(XML_DATE_PATTERN);
		
		Date date = null;
		try 
		{ 
			date = sFormat.parse(timeStr);
		} 
		catch (ParseException e) 
		{
			throw new OperationException("Cannot convert to timestamp!" + timeStr);
		}
		
		Timestamp stamp = new Timestamp(date.getTime());
		
		return stamp;
		
	}
	
    public static Timestamp getTimestamp(String timeStr, String format) throws InvalidDateTimeException
    {
        SimpleDateFormat sFormat = new SimpleDateFormat(format);
        
        if(timeStr == null || timeStr.length() == 0)
            throw new InvalidDateTimeException("String length is invalid");
        
        Date date = null;
        try 
        {
            date = sFormat.parse(timeStr);
        } 
        catch (ParseException e) 
        {
            throw new InvalidDateTimeException("Cannot convert to timestamp!" + timeStr);
        }
        
        Timestamp stamp = new Timestamp(date.getTime());
        
        return stamp;   
    }
	public static Timestamp getTimestampForSync(String timeStr) throws InvalidDateTimeException
	{
	   return getTimestamp(timeStr, SYNC_DATE_TIME);
	}
	
	public static Timestamp getTimestampFromFlatString(String timeStr) throws OperationException, InvalidDateTimeException
	{
        return getTimestamp(timeStr, FLAT_DATE_PATTERN);
    }
	
	public static String getCurrentDateTime()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(NORM_DATE_TIME_PATTERN);
		
		Date now = new Date();
		
		String strDate = sFormat.format(now);

		return strDate;
	}
	
	/**
	 * @return Current date and time
	 */
	public static Timestamp getCurrentDateTimeTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * @return Current Date only
	 * @throws OperationException
	 */
	public static Timestamp getCurrentDateTimestamp() throws OperationException
	{
		// Does not clear hour
		/*Calendar cal = Calendar.getInstance();
		cal.clear(Calendar.MILLISECOND);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.HOUR);*/
		
		try
		{
			Date now = new Date();
			SimpleDateFormat sFormat = new SimpleDateFormat(FLAT_DATE_PATTERN);
			
			String d = sFormat.format(now);
			
			Date nDate = sFormat.parse(d);
		
			return new Timestamp(nDate.getTime());
		}
		catch(ParseException ex)
		{
			throw new OperationException("Could not retrieve current date");
		}
	}
	
	
	
	public static String convertTimeStampToFyracle(String timeStr) throws OperationException
	{
		if(timeStr == null || timeStr.trim().length() == 0)
			throw new OperationException("Time string cannot be null");
		
		if(timeStr.length() < 8)
			throw new OperationException("Time string is invalid, Time String: " + timeStr);
		
		int ind = timeStr.indexOf("-");
		if(ind != 2)
			throw new OperationException("Time string is not good, Time String: " + timeStr);
		
		String timeStrSub = timeStr.substring(ind + 1);
		
		int ind2 = timeStrSub.indexOf("-");
		
		if(ind2 == 2)
			return convertTimeStampToFyracle(timeStr, DEFAULT_DATE_PATTERN1);
		else if(ind2 == 3)
			return convertTimeStampToFyracle(timeStr, DEFAULT_DATE_PATTERN2);
		else
			throw new OperationException("Time string is not valid, Time String: " + timeStr);
	}
	
	public static String convertTimeStampToFyracle(String timeStr, String pattern) throws OperationException
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat tFormat = new SimpleDateFormat(FYRACLE_DATETIME_PATTERN);
		
		Date date = null;
		
		try
		{
			date = sFormat.parse(timeStr);
			String retVal = tFormat.format(date);
			return retVal;
		}
		catch(ParseException ex)
		{
			throw new OperationException("Cannot convert to timestamp!" + timeStr);
		}
	}
	
	public static String changeTimestampPattern(String timeStr, String fromPattern, String toPattern) throws OperationException
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(fromPattern);
		SimpleDateFormat tFormat = new SimpleDateFormat(toPattern);
		
		Date date = null;
		
		try
		{
			date = sFormat.parse(timeStr);
			String retVal = tFormat.format(date);
			return retVal;
		}
		catch(ParseException ex)
		{
			throw new OperationException("Cannot convert to timestamp!" + timeStr);
		}
	}
	
	public static String convertTimeStampToFyracleDate(Timestamp tStamp) throws OperationException
	{
		SimpleDateFormat tFormat = new SimpleDateFormat(FYRACLE_DATE_PATTERN);
		
		try
		{
			String retVal = tFormat.format(tStamp);
			return retVal;
		}
		catch(Exception ex)
		{
			throw new OperationException("Cannot convert to timestamp!" + tStamp.toString());
		}
	}
	
	public static String convertTimeStamp(Timestamp time, String format) throws OperationException
	{
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
			String formatted = dateFormat.format(time);
			return formatted;
		}
		catch (Exception e)
		{
			throw new OperationException("Cannot convert to timestamp!" + time.toString());
		}
	}
	
	public static String reportTimeStampToFyracle(String timeStr) throws OperationException
	{
		SimpleDateFormat sFormat = new SimpleDateFormat(REPORTS_DATE_PATTERN);
		SimpleDateFormat tFormat = new SimpleDateFormat(FYRACLEDB_DATETIME_PATTERN);
		
		Date date = null;
		
		try
		{
			date = sFormat.parse(timeStr);
			String retVal = tFormat.format(date);
			return retVal;
		}
		catch(ParseException ex)
		{
			throw new OperationException("Cannot convert to timestamp!" + timeStr);
		}
	}
	
	/**
	 * Format Timestamp according to default pattern 
	 * @see DEFAULT_DATE_PATTERN1
	 * @param timestamp
	 * @return formatted timestamp
	 */
	public static String format(Timestamp timestamp)
	{
		return format(timestamp, DEFAULT_DATE_PATTERN1);
	}
	
	/**
	 * Format a Timestamp according to the pattern provided
	 * @param timestamp
	 * @param pattern
	 * @return Formated string for the Timestamp
	 */
	public static String format(Timestamp timestamp, String pattern)
	{
		if (timestamp == null)
		{
			throw new IllegalArgumentException("Timestamp cannot be null");
		}
		
		if (pattern == null)
		{
			throw new IllegalArgumentException("Pattern cannot be null");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(timestamp);
	}
}
 