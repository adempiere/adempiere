/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Calendar Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCalendar.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/763">
 *		@see FR [ 763 ] Helper method for get non business day from calendar</a>
 */
public class MCalendar extends X_C_Calendar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7721451326626542420L;


	/**
	 * 	Get MCalendar from Cache
	 *	@param ctx context
	 *	@param C_Calendar_ID id
	 *	@return MCalendar
	 */
	public static MCalendar get (Properties ctx, int C_Calendar_ID)
	{
		Integer key = new Integer (C_Calendar_ID);
		MCalendar retValue = (MCalendar) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MCalendar (ctx, C_Calendar_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Get Default Calendar for Client
	 *	@param ctx context
	 *	@param AD_Client_ID id
	 *	@return MCalendar
	 */
	public static MCalendar getDefault (Properties ctx, int AD_Client_ID)
	{
		MClientInfo info = MClientInfo.get(ctx, AD_Client_ID);
		return get (ctx, info.getC_Calendar_ID());
	}	//	getDefault
	
	/**
	 * 	Get Default Calendar for Client
	 *	@param ctx context
	 *	@return MCalendar
	 */
	public static MCalendar getDefault (Properties ctx)
	{
		return getDefault(ctx, Env.getAD_Client_ID(ctx));
	}	//	getDefault
	
	/**	Cache						*/
	private static CCache<Integer,MCalendar> s_cache
		= new CCache<Integer,MCalendar>("C_Calendar", 20);
	
	
	/*************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Calendar_ID id
	 *	@param trxName transaction
	 */
	public MCalendar (Properties ctx, int C_Calendar_ID, String trxName)
	{
		super(ctx, C_Calendar_ID, trxName);
	}	//	MCalendar

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCalendar (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCalendar

	/**
	 * 	Parent Constructor
	 *	@param client parent
	 */
	public MCalendar (MClient client)
	{
		super(client.getCtx(), 0, client.get_TrxName());
		setClientOrg(client);
		setName(client.getName() + " " + Msg.translate(client.getCtx(), "C_Calendar_ID"));
	}	//	MCalendar
	
	/**
	 * 	Create (current) Calendar Year
	 * 	@param locale locale
	 *	@return The Year
	 */
	public MYear createYear(Locale locale)
	{
		if (get_ID() == 0)
			return null;
		MYear year = new MYear (this);
		if (year.save())
			year.createStdPeriods(locale);
		//
		return year;
	}	//	createYear
	
	/**	Non Business Days			*/
	private HashMap<String, MNonBusinessDay> nonBusinessDays;
	private SimpleDateFormat format;
	
	/**
	 * Get Non Business Days
	 * @return
	 */
	private void loadNonBusinessDay() {
		if(nonBusinessDays != null) {
			return;
		}
		//	Get
		List<MNonBusinessDay> nonBusinessDaysList = new Query(getCtx(), I_C_NonBusinessDay.Table_Name, 
				I_C_NonBusinessDay.COLUMNNAME_C_Calendar_ID + " = ?", get_TrxName())
			.setParameters(getC_Calendar_ID())
			.setOnlyActiveRecords(true)
			.setOrderBy(I_C_NonBusinessDay.COLUMNNAME_Date1)
			.<MNonBusinessDay>list();
		//	To HashMap
		nonBusinessDays = new HashMap<String, MNonBusinessDay>();
		format = new SimpleDateFormat("yyyyMMdd");
		//	Add
		for(MNonBusinessDay nonBusinessDay : nonBusinessDaysList) {
			nonBusinessDays.put(format.format(nonBusinessDay.getDate1()) + "|" + nonBusinessDay.getAD_Org_ID(), nonBusinessDay);
		}
	}
	
	/**
	 * Verify if it is a not business day from Timestamp
	 * @param day
	 * @return boolean
	 */
	public boolean isNonBusinessDay(Timestamp day) {
		//	Validate null before
		if(day == null)
			return false;
		//	
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(day.getTime());
		//	REturn
		return isNonBusinessDay(date.getTime());
	}
	
	/**
	 * Verify if it is a not business day from Date by Org
	 * @param day
	 * @return boolean
	 */
	public boolean isNonBusinessDay(Date day, int orgId) {
		//	Validate null before
		if(day == null)
			return false;
		//	Load
		loadNonBusinessDay();
		//	
		String keyOrg = format.format(day) + "|" + orgId;
		String keyGlobal = format.format(day) + "|" + 0;
		//	
		MNonBusinessDay nonBusinessDay = nonBusinessDays.get(keyOrg);
		//	Validate
		if(nonBusinessDay == null
				&& orgId != 0) {
			nonBusinessDay = nonBusinessDays.get(keyGlobal);
		}
		//	Default return
		return nonBusinessDay != null;
	}
	
	/**
	 * Verify if it is a not business day from Date
	 * @param day
	 * @return boolean
	 */
	public boolean isNonBusinessDay(Date day) {
		return isNonBusinessDay(day, 0);
	}
}	//	MCalendar
