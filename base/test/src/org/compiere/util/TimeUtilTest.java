
/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.compiere.test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;

import test.AdempiereTestCase;

/**
 *	Time Utilities Test
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1873">
 * 		@see FR [ 1873 ] Add hepler method to TimeUtil class</a>
 */
public class TimeUtilTest extends AdempiereTestCase {

	private Properties testProperties = null;

	private Properties m_Ctx = null;
	
	private String fileName_DefaultValue = "/opt/Development/ADempiere_Properties/ADempiere_390_Fork.properties";
	private String fileName_Key = "AdempiereProperties";
	private String fileName_Value = "";
	
	private String isClient_DefaultValue = "Y";
	private String isClient_Key = "isClient";
	private boolean isClient_Value = true;

	private String AD_User_ID_DefaultValue = "0";
	private String AD_User_ID_Key = "AD_User_ID";
	private int AD_User_ID_Value = 0;
	
	@Override
	protected void setUp() throws Exception {
		testProperties = new Properties();
		fileName_Value = testProperties.getProperty(fileName_Key, fileName_DefaultValue);
		isClient_Value = "Y".equals( testProperties.getProperty(isClient_Key, isClient_DefaultValue) );
		AD_User_ID_Value = Integer.parseInt(testProperties.getProperty(AD_User_ID_Key, AD_User_ID_DefaultValue) );
		
		m_Ctx = new Properties();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		log.config("m_Ctx: " + m_Ctx);
		
		if (fileName_Value.length() < 1) {
		    assertEquals("Please specify path to Adempiere.properties file!", true, false);
		}
		
		System.setProperty("PropertyFile", fileName_Value);
		Ini.setClient (isClient_Value);
		org.compiere.Adempiere.startup(isClient_Value);
		CLogMgt.setLevel(Level.CONFIG);
	}
	
	public void testTimeUtil() {
		Timestamp t1 = TimeUtil.getDay(2018, 01, 01);
		Timestamp t2 = TimeUtil.getDay(2019, 05, 31);
		log.config("(t1 - t2)=" + t1 + " - " + t2);
		log.config("getMonthsBetween(t1, t2)=" + TimeUtil.getMonthsBetween(t1, t2));
		
		Timestamp t3 = TimeUtil.getDay(03, 03, 03);
		
		Timestamp t4 = TimeUtil.getDay(01, 01, 01);
		Timestamp t5 = TimeUtil.getDay(02, 02, 02);
		
		log.config(t1 + " - " + t3);
		log.config(t2 + " - " + TimeUtil.isValid (t1,t3, t2));
		log.config(TimeUtil.isSameDay(t1, t4) + " == true" );
		log.config(TimeUtil.isSameDay(t2, t5) + " == true");
		log.config(TimeUtil.isSameDay(t3, t5) + " == false");
		//	All days between
		log.config("getDaysBetween(t1, t2)=" + TimeUtil.getDaysBetween(t1, t2));
		//	For Time
		log.config("getHoursBetween(t1, t2)=" + TimeUtil.getHoursBetween(t1, t2));
		log.config("getMinutesBetween(t1, t2)=" + TimeUtil.getMinutesBetween(t1, t2));
		log.config("getSecondsBetween(t1, t2)=" + TimeUtil.getSecondsBetween(t1, t2));
		log.config("getMillisecondsBetween(t1, t2)=" + TimeUtil.getMillisecondsBetween(t1, t2));
		//	Just Saturday and Sunday between
		log.config("getDaysBetween(t1, t2, Calendar.SATURDAY, Calendar.SUNDAY)=" + TimeUtil.getDaysBetween(t1, t2, Calendar.SATURDAY, Calendar.SUNDAY));
		//	Get Non business days between using calendar match, also include as non business days (Saturday and Sunday)
		log.config("getNonBusinessDaysBetween(t1, t2, Calendar.SATURDAY, Calendar.SUNDAY)=" + TimeUtil.getNonBusinessDaysBetween(t1, t2, Calendar.SATURDAY, Calendar.SUNDAY));
		//	Get business days between using calendar match, also include as non business days (from Monday to Friday)
		log.config("getBusinessDaysBetween(t1, t2, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY)=" + TimeUtil.getBusinessDaysBetween(t1, t2, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY));
		log.config("getYearsBetween(t1, t2)=" + TimeUtil.getYearsBetween(t1, t2));
	}
}
