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
*
* Created on Sep 11, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.beans;

public class DateFilterBean extends UDIBean
{
	public String getFromDate()
    {
        return fromDate;
    }
    
    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }
    
    public String getToDate()
    {
        return toDate;
    }
    
    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }
    
    public String getStartDay()
    {
        return startDay;
    }
    
    public void setStartDay(String startDay)
    {
        this.startDay = startDay;
    }
    
    public String getStartMonth()
    {
        return startMonth;
    }
    
    public void setStartMonth(String startMonth)
    {
        this.startMonth = startMonth;
    }

    public String getStartYear()
    {
        return startYear;
    }
    
    public void setStartYear(String startYear)
    {
        this.startYear = startYear;
    }
    
    public String getEndDay()
    {
        return endDay;
    }
    
    public void setEndDay(String endDay)
    {
        this.endDay = endDay;
    }
    
    public String getEndMonth()
    {
        return endMonth;
    }
    
    public void setEndMonth(String endMonth)
    {
        this.endMonth = endMonth;
    }
    
    public String getEndYear()
    {
        return endYear;
    }
    
    public void setEndYear(String endYear)
    {
        this.endYear = endYear;
    }
    
    public String getStartHour()
    {
        return startHour;
    }
    
    public void setStartHour(String startHour)
    {
        this.startHour = startHour;
    }
    
    public String getStartMinute()
    {
        return startMinute;
    }
    
    public void setStartMinute(String startMinute)
    {
        this.startMinute = startMinute;
    }
        
    public String getEndHour()
    {
        return endHour;
    }
    
    public void setEndHour(String endHour)
    {
        this.endHour = endHour;
    }
    
    public String getEndMinute()
    {
        return endMinute;
    }
    
    public void setEndMinute(String endMinute)
    {
        this.endMinute = endMinute;
    }
}
