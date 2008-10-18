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

/*
 * This class is used for formatting purposes only
 * @author vishee
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SimpleCalendarDate
{
	private String year;
	private String month;
	private String day;
	
	
	public SimpleCalendarDate(String day, String month, String year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	


	/**
	 * @return Returns the day.
	 */
	public String getDay()
	{
		return day;
	}
	/**
	 * @param day The day to set.
	 */
	public void setDay(String day)
	{
		this.day = day;
	}
	/**
	 * @return Returns the month.
	 */
	public String getMonth()
	{
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month)
	{
		this.month = month;
	}
	/**
	 * @return Returns the year.
	 */
	public String getYear()
	{
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year)
	{
		this.year = year;
	}
}
