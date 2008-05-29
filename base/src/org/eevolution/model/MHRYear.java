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
package org.eevolution.model;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	MHRYear Year for a Payroll
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRYear.java,v 1.0 2007/04/25 ogomezi
 */
public class MHRYear extends X_HR_Year
{
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
	public boolean createPeriods(int HR_Payroll_ID,int HR_Year_ID, int C_Year_ID)
	{

		Timestamp StartDate = new Timestamp (System.currentTimeMillis());
		Timestamp EndDate   = new Timestamp (System.currentTimeMillis());
		int C_Period_ID     = 0;
		int sumDays         = 0;
		int C_Calendar_ID   = DB.getSQLValue("C_Calendar", "SELECT C_Calendar_ID FROM C_Year WHERE C_Year_ID = "+C_Year_ID);
		if (C_Calendar_ID == 0)
			return false;
		
		MHRYear hr_year = new MHRYear(Env.getCtx(),HR_Year_ID,get_TrxName());
		
		for (int period = 1; period <= getQty(); period++)
		{
			sumDays     =  period != 1 ? (period-1) * (getNetDays()) : 0;
			StartDate   = TimeUtil.addDays(getStartDate(),sumDays);
			EndDate     = TimeUtil.addDays(StartDate,getNetDays()-1);
			C_Period_ID = DB.getSQLValue("C_Period","SELECT C_Period_ID FROM C_Period p "
							+ " INNER JOIN C_Year y ON (p.C_Year_ID=y.C_Year_ID) "
							+ " WHERE " + DB.TO_DATE(EndDate)
					        + " BETWEEN p.startdate AND p.endDate AND y.C_Calendar_ID=" + C_Calendar_ID);
			if(C_Period_ID == 0)
				return false;
			
			MPeriod m_period = new MPeriod(getCtx(), C_Period_ID , get_TrxName());
			MHRPayroll payroll = new MHRPayroll(Env.getCtx(),HR_Payroll_ID,get_TrxName());
			X_HR_Period HR_Period = new X_HR_Period(Env.getCtx(),0,get_TrxName());
			HR_Period.setAD_Org_ID(hr_year.getAD_Org_ID());
			HR_Period.setHR_Year_ID(hr_year.getHR_Year_ID());
			HR_Period.setHR_Payroll_ID(HR_Payroll_ID);
			HR_Period.setName(StartDate.toString().substring(0, 10)+" "+Msg.translate(getCtx(), "To")+" "+EndDate.toString().substring(0, 10) );
			HR_Period.setDescription(Msg.translate(getCtx(), "HR_Payroll_ID")+" "+payroll.getName().trim()+" "+Msg.translate(getCtx(), "From")+ " "+period+" " +Msg.translate(getCtx(), "To")+" "+ StartDate.toString().substring(0, 10)+" al "+EndDate.toString().substring(0, 10));
			HR_Period.setPeriodNo(period);
			HR_Period.setC_Period_ID(C_Period_ID);
			HR_Period.setC_Year_ID(m_period.getC_Year_ID());
			HR_Period.setStartDate(StartDate);
			HR_Period.setEndDate(EndDate);
			HR_Period.setDateAcct(EndDate);
			HR_Period.setIsActive(true);
			//
			if ( !HR_Period.save() )	//	Creates Period Control
				return false;
		}
		return true;
	}	//	createPeriods
}	//	HRYear
