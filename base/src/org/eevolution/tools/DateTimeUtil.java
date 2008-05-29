/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.tools;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;

/**
* @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
* @version 1.0, October 14th 2005
*/
public class DateTimeUtil {

 	public static long getTimeDifference(Timestamp time1 , Timestamp time2) {

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTimeInMillis(time1.getTime());
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTimeInMillis(time2.getTime());

		long l1 = gc1.getTime().getTime();
                long l2 = gc2.getTime().getTime();

                return l2 - l1;
    }

 	
	public static Timestamp[] getDayBorders(Timestamp dateTime, Timestamp timeSlotStart, Timestamp timeSlotFinish) {
	
		return new Timestamp[] {
				
				getDayBorder(dateTime, timeSlotStart, false),
				getDayBorder(dateTime, timeSlotFinish, true),
		};
	}
	
	public static Timestamp getDayBorder(Timestamp dateTime, Timestamp timeSlot, boolean end) {
		
		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(dateTime.getTime());
		 dateTime.setNanos(0);
		 
		 if(timeSlot != null) {
			 
			 timeSlot.setNanos(0);

			 GregorianCalendar gcTS = new GregorianCalendar();
			 gcTS.setTimeInMillis(timeSlot.getTime());
			 
			 gc.set(Calendar.HOUR_OF_DAY, gcTS.get(Calendar.HOUR_OF_DAY));
			 gc.set(Calendar.MINUTE, gcTS.get(Calendar.MINUTE));
			 gc.set(Calendar.SECOND, gcTS.get(Calendar.SECOND));
			 gc.set(Calendar.MILLISECOND, gcTS.get(Calendar.MILLISECOND));
		 } 
		 else if(end) {
			 
			 gc.set(Calendar.HOUR_OF_DAY, 23);
			 gc.set(Calendar.MINUTE, 59);
			 gc.set(Calendar.SECOND, 59);
			 gc.set(Calendar.MILLISECOND, 999);
		 }
		 else {
			 
			 gc.set(Calendar.MILLISECOND, 0);
	 		 gc.set(Calendar.SECOND, 0);
	 		 gc.set(Calendar.MINUTE, 0);
	 		 gc.set(Calendar.HOUR_OF_DAY, 0);
		 }
		 return new Timestamp(gc.getTimeInMillis());
	}
	
	public static Timestamp[] getDayBorders(Timestamp dateTime) {
	
		return getDayBorders(dateTime, null, null);
	}
	
	/*public static Timestamp incrementDay(Timestamp dateTime) {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(dateTime.getTime());
		gc.add(Calendar.DAY_OF_MONTH, 1);
	
		return new Timestamp(gc.getTimeInMillis());
	}

	public static Timestamp decrementDay(Timestamp dateTime) {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(dateTime.getTime());
		gc.add(Calendar.DAY_OF_MONTH, -1);
	
		return new Timestamp(gc.getTimeInMillis());
	}*/
}
