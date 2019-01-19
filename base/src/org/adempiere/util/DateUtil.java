package org.adempiere.util;

import java.util.Calendar;

public class DateUtil {
	
	public static String getDateString(){
		String dateString = "";
		
		Calendar cal = Calendar.getInstance();
		
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String month = Integer.toString(cal.get(Calendar.MONTH)+1);
		String day = Integer.toString(cal.get(Calendar.DATE));
		
		if (year.length()>=4){
			year = year.substring(2,4);
		}else if (year.length()<2)
			year = "0"+year;
		
		if (month.length()<2)
			month = "0"+month;
		
		if (day.length()<2)
			day = "0"+day;
		
		dateString = year+month+day;
		
		return dateString;
	}
}
