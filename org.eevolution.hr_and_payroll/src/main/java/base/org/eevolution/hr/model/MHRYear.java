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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Oscar Gómez  www.e-evolution.com                           *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MCalendar;
import org.compiere.model.MPeriod;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 *	MHRYear Year for a Payroll
 *	
 *  @author oscar.gomez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<li> Original contributor of Payroll Functionality
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *
 *	@author Cristina Ghita, www.arhipac.ro
 * 			<li> BUG [ 1932959 ]
 * 			@see https://sourceforge.net/tracker/index.php?func=detail&aid=1932959&group_id=176962&atid=934929
 * 	@author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * 			Add Time and Attendance Record integration
 */
public class MHRYear extends X_HR_Year
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7789699154024839462L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Payroll_ID id
	 */
	public MHRYear (Properties ctx, int HR_Year_ID, String trxName)
	{
		super (ctx, HR_Year_ID, trxName);
		if (HR_Year_ID == 0)
		{
			setProcessing (false);	// N
		}		
	}	//	HRYear

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MHRYear (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 */
	public MHRYear (MCalendar calendar)
	{
		this (calendar.getCtx(), 0, calendar.get_TrxName());
		setClientOrg(calendar);
	}
	
		
	/**
	 * 	CreatePeriods.
	 * 	Creates also Period
	 * 	@param  HR_Payroll_ID
	 *	@return true if created
	 */
	public boolean createPeriods()
	{
		int sumDays = 0;
		int calendarId = DB.getSQLValueEx(get_TrxName(), "SELECT C_Calendar_ID FROM C_Year WHERE C_Year_ID = ?", getC_Year_ID());
		if (calendarId <= 0)
			return false;
		
		Timestamp startDate = null;
		Timestamp endDate = null ;
		for (int period = 1; period <= getQty(); period ++) {
			//arhipac: Cristina Ghita It is need this condition for a good generation periods
			//in case of correspondence between period and month
			if ((12 == getQty())&& (28 == getNetDays() || 29 == getNetDays() || 30 == getNetDays() || 31 == getNetDays()))
			{
				if (period >1) {
					startDate = TimeUtil.addDays(endDate, 1);
				} else {
					startDate = TimeUtil.addDays(getStartDate(),0);	
				}
				endDate   = TimeUtil.getMonthLastDay(startDate);
				
			}	
			// fortnight payment
			else if ((24 == getQty())&& (15 == getNetDays() || 16 == getNetDays())) {
				if (period > 1) {
					startDate = TimeUtil.addDays(endDate, 1);
				} else {
					startDate = TimeUtil.addDays(getStartDate(),0);	
				}
				boolean par = (period % 2) == 0 ? true : false;
				if (!par) {
					endDate = TimeUtil.addDays(startDate, getNetDays()-1);
				} else {
					endDate   = TimeUtil.getMonthLastDay(startDate);				
				}
			} else {
				sumDays   =  period == 1 ? 0 : (period-1) * (getNetDays()) ;
				startDate = TimeUtil.addDays(getStartDate(),sumDays);
				endDate   = TimeUtil.addDays(startDate,getNetDays()-1);
			}
			int periodId = DB.getSQLValueEx(get_TrxName(),
					"SELECT C_Period_ID FROM C_Period p "
					+ " INNER JOIN C_Year y ON (p.C_Year_ID=y.C_Year_ID) "
					+ " WHERE "
					+ " ? BETWEEN p.startdate AND p.endDate"
					+ " AND y.C_Calendar_ID=?",
					endDate, calendarId);
			if(periodId <= 0)
				return false;

			String fromAndTo = DisplayType.getDateFormat(DisplayType.Date).format(startDate) + " " + Msg.translate(getCtx(), "To") + " " + DisplayType.getDateFormat(DisplayType.Date).format(endDate);
			StringBuffer message = new StringBuffer();
			//	Add optional payroll
			if(getHR_Payroll_ID() > 0) {
				MHRPayroll payroll = new MHRPayroll(getCtx(), getHR_Payroll_ID(), get_TrxName());
				message.append(Msg.translate(getCtx(), "HR_Payroll_ID")).append(" ").append(payroll.getName().trim()).append(" ");
			}
			message.append(Msg.translate(getCtx(), "HR_Period_ID")).append(" ").append(period).append(": ").append(Msg.translate(getCtx(), "From")).append(" ").append(fromAndTo);
			MPeriod standardPeriod = MPeriod.get(getCtx(), periodId);
			MHRPeriod payrollPeriod = new MHRPeriod(getCtx(), 0, get_TrxName());
			payrollPeriod.setAD_Org_ID(getAD_Org_ID());
			payrollPeriod.setHR_Year_ID(getHR_Year_ID());
			payrollPeriod.setHR_Payroll_ID(getHR_Payroll_ID());
			payrollPeriod.setName(fromAndTo);
			payrollPeriod.setDescription(message.toString());
			payrollPeriod.setPeriodNo(period);
			payrollPeriod.setC_Period_ID(periodId);
			payrollPeriod.setC_Year_ID(standardPeriod.getC_Year_ID());
			payrollPeriod.setStartDate(startDate);
			payrollPeriod.setEndDate(endDate);
			payrollPeriod.setDateAcct(endDate);
			payrollPeriod.setIsActive(true);
			payrollPeriod.saveEx();
		}
		return true;
	}	//	createPeriods
 
	

}	//	HRYear
