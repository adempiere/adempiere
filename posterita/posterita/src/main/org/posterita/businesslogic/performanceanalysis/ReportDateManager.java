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
 * Created on 19-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic.performanceanalysis;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.jfree.date.SerialDate;

import org.posterita.beans.ReportBean;
import org.posterita.core.TimestampConvertor;
import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.InvalidMonthException;
import org.posterita.exceptions.OperationException;

/**
 * @author Praveen
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportDateManager 
{
	
	public static String getEquivalentMonth(String month) throws InvalidMonthException
	{
	    int intMonth = Integer.parseInt(month);
	    
	    switch(intMonth)
	    {
	        case 1:
	            return "Jan";
	        case 2:
	            return "Feb";
	        case 3:
	            return "Mar";
	        case 4:
	            return "Apr";
	        case 5:
	            return "May";
	        case 6:
	            return "Jun";
	        case 7:
	            return "Jul";
	        case 8:
	            return "Aug";
	        case 9:
	            return "Sep";
	        case 10:
	            return "Oct";
	        case 11:
	            return "Nov";
	        case 12:
	            return "Dec";
	            
	            default:
	                throw new InvalidMonthException("Invalid Month");
	            		
	    }
    
	}
	
	
	public static ReportBean formatDate(ReportBean bean) throws InvalidMonthException
	{
	    String fromDate = bean.getFromDate();
	    String toDate = bean.getToDate();
	    String delimiter = "/";
	   	    
	    String newFromDate = format(fromDate, delimiter);
	    String newToDate = format(toDate, delimiter);
	    bean.setFromDate(newFromDate);
	    bean.setToDate(newToDate);
	    
	    return bean;
    
	}
	
	
	public static String format(String date, String delimiter) throws InvalidMonthException
	{
	    String newDateFormatDelimiter = "-";
    	StringTokenizer dateTokenizer = new StringTokenizer(date, delimiter);
	   
	    String day = dateTokenizer.nextToken();
	    String month = dateTokenizer.nextToken();
	    String year = dateTokenizer.nextToken();
	    
	    String equiMonth = getEquivalentMonth(month);
	    
	    ///dd/mm/yyyy
	    String newDateFormat = day
    							+ newDateFormatDelimiter
    							+ equiMonth
    							+ newDateFormatDelimiter
    							+ year;
	    
	    return newDateFormat;
	}
	
	public static Timestamp getStartDateForPeriod(String period) throws OperationException
	{
		Calendar cal = Calendar.getInstance();
		SerialDate serialDate = null;
		
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);		
		
		if(period.equalsIgnoreCase(TODAY))
		{
			//return default
		}
		else if(period.equalsIgnoreCase(CURRENT_WEEK))
		{
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			int d = 1-dayOfWeek;
			
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addDays(d,serialDate);
			cal.setTime(s.toDate());
			
		}
		else if(period.equalsIgnoreCase(LAST_2WEEKS))
		{
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			int d = 1-dayOfWeek-7;
			
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addDays(d,serialDate);
			cal.setTime(s.toDate());
		}
		else if(period.equalsIgnoreCase(LAST_3WEEKS))
		{
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			int d = 1-dayOfWeek-14;
			
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addDays(d,serialDate);
			cal.setTime(s.toDate());
		}
		else if(period.equalsIgnoreCase(CURRENT_MONTH))
		{
			cal.set(Calendar.DATE,1);			
		}
		else if(period.equalsIgnoreCase(LAST_3MONTHS))
		{
			cal.set(Calendar.DATE,1);
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addMonths(-2,serialDate);
			cal.setTime(s.toDate());
		}
		else if(period.equalsIgnoreCase(LAST_2MONTHS))
		{
			cal.set(Calendar.DATE,1);
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addMonths(-1,serialDate);
			cal.setTime(s.toDate());
		}
		else if(period.equalsIgnoreCase(LAST_6MONTHS))
		{
			cal.set(Calendar.DATE,1);
			serialDate = SerialDate.createInstance(cal.getTime());
			SerialDate s = SerialDate.addMonths(-5,serialDate);
			cal.setTime(s.toDate());
		}
		else if(period.equalsIgnoreCase(CURRENT_YEAR))
		{		
			cal.set(Calendar.DATE,1);
			cal.set(Calendar.MONTH,0);
			
		}
		else
		{
			throw new OperationException("Invalid Period Format - " + period);
		}
		
		return  new Timestamp(cal.getTimeInMillis());
	}
	
	public static Timestamp getEndDateForPeriod(String period)
	{
		Calendar cal = Calendar.getInstance();		
		return new Timestamp(cal.getTimeInMillis());
	}

	public static final String TODAY = "Today";	
	
	public static final String CURRENT_WEEK = "Current Week";
	public static final String LAST_2WEEKS = "Last 2 Weeks";
	public static final String LAST_3WEEKS = "Last 3 Weeks";
	
	public static final String CURRENT_MONTH = "Current Month";
	public static final String LAST_2MONTHS = "Last 2 months";
	public static final String LAST_3MONTHS = "Last 3 Months";
	public static final String LAST_6MONTHS = "Last 6 Months";
	
	public static final String CURRENT_YEAR = "Current Year";

	
	public static Timestamp getFromDate(ReportBean reportBean) throws InvalidMonthException, InvalidDateTimeException
    {
        //String delimiter = "/";
		//String dateFrom = ReportDateManager.format(reportBean.getFromDate(),delimiter);
		
		String dateFrom = reportBean.getFromDate();
		String timeFrom = dateFrom + " " + reportBean.getStartHour() + ":" + reportBean.getStartMinute() + ":00";
		return TimestampConvertor.getTimestamp(timeFrom, TimestampConvertor.DEFAULT_DATE_PATTERN1);
    }
    
    public static Timestamp getToDate(ReportBean reportBean) throws InvalidMonthException, InvalidDateTimeException
    {
        //String delimiter = "/";
		//String dateTo = ReportDateManager.format(reportBean.getToDate(),delimiter);
		
    	String dateTo = reportBean.getToDate();
    	String timeTo = dateTo + " " + reportBean.getEndHour() + ":" + reportBean.getEndMinute() + ":00";
		return TimestampConvertor.getTimestamp(timeTo, TimestampConvertor.DEFAULT_DATE_PATTERN1);        
    }
    
    public static Timestamp getFromDateAsDate(ReportBean bean) throws InvalidMonthException, ParseException, InvalidDateTimeException
    {
    	Timestamp date = getFromDate(bean);
    	
    	return date;
    }
    
    public static Timestamp getEndDateAsDate(ReportBean bean) throws InvalidMonthException, ParseException, InvalidDateTimeException
    {
    	Timestamp date = getToDate(bean);
    	
    	return date;
    }
	

}
