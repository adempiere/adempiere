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
package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MCalendar;
import org.compiere.model.Query;
import org.compiere.model.X_C_Period;
import org.compiere.model.X_C_Year;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Payroll for HRayroll Module
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRPayroll.java,v 1.0 2005/10/05 ogomezi
 *  
 *  @author Cristina Ghita, www.arhipac.ro
 */
public class MHRPayroll extends X_HR_Payroll
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407037967021019961L;
	/** Cache */
	private static CCache<Integer, MHRPayroll> s_cache = new CCache<Integer, MHRPayroll>(Table_Name, 10);
	/** Cache */
	private static CCache<String, MHRPayroll> s_cacheValue = new CCache<String, MHRPayroll>(Table_Name+"_Value", 10);
	
	/**
	 * Get Payroll by Value
	 * @param ctx
	 * @param value
	 * @return payroll
	 */
	public static MHRPayroll forValue(Properties ctx, String value)
	{
		if (Util.isEmpty(value, true))
		{
			return null;
		}
		
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		final String key = AD_Client_ID+"#"+value;
		MHRPayroll payroll = s_cacheValue.get(key);
		if (payroll != null)
		{
			return payroll;
		}
		
		final String whereClause = COLUMNNAME_Value+"=? AND AD_Client_ID IN (?,?)"; 
		payroll = new Query(ctx, Table_Name, whereClause, null)
							.setParameters(new Object[]{value, 0, AD_Client_ID})
							.setOnlyActiveRecords(true)
							.setOrderBy("AD_Client_ID DESC")
							.first();
		if (payroll != null)
		{
			s_cacheValue.put(key, payroll);
			s_cache.put(payroll.get_ID(), payroll);
		}
		return payroll;
	}
	
	/**
	 * Get Payroll by ID
	 * @param ctx
	 * @param HR_Payroll_ID
	 * @return payroll
	 */
	public static MHRPayroll get(Properties ctx, int HR_Payroll_ID)
	{
		if (HR_Payroll_ID <= 0)
			return null;
		//
		MHRPayroll payroll = s_cache.get(HR_Payroll_ID);
		if (payroll != null)
			return payroll;
		//
		payroll = new MHRPayroll(ctx, HR_Payroll_ID, null);
		if (payroll.get_ID() == HR_Payroll_ID)
		{
			s_cache.put(HR_Payroll_ID, payroll);
		}
		else
		{
			payroll = null;
		}
		return payroll;
	}
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Payroll_ID id
	 */
	public MHRPayroll (Properties ctx, int HR_Payroll_ID, String trxName)
	{
		super (ctx, HR_Payroll_ID, trxName);
		if (HR_Payroll_ID == 0)
		{
			setProcessing (false);	// N
		}		
	}	//	HRPayroll

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MHRPayroll (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Parent Constructor
	 *	@param  calendar
	 */
	public MHRPayroll (MCalendar calendar)
	{
		this (calendar.getCtx(), 0, calendar.get_TrxName());
		setClientOrg(calendar);
	}	//	HRPayroll

	/**
	 * Get period count
	 * @return period no
     */
	public int countPeriods()
	{
		StringBuilder where = new StringBuilder();
		where.append(MHRPeriod.COLUMNNAME_HR_Payroll_ID).append("=?");
		return new Query(getCtx() , MHRPeriod.Table_Name , where.toString() , get_TrxName())
				.setClient_ID()
				.setParameters(getHR_Payroll_ID())
				.count();
	}

	/**
	 * Get HR period based on a date
	 * @param date
	 * @return HR Period instance
     */
	public MHRPeriod getPeriodByDate(Timestamp date)
	{
		StringBuilder where = new StringBuilder();
		where.append(COLUMNNAME_HR_Payroll_ID).append("=? AND ");
		where.append("?").append(" BETWEEN ").append(MHRPeriod.COLUMNNAME_StartDate).append(" AND ").append(MHRPeriod.COLUMNNAME_EndDate);
		return new Query(getCtx() , MHRPeriod.Table_Name , where.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(getHR_Payroll_ID() , date)
				.firstOnly();
	}

	/**
	 * get period list based on range dates
	 * @param from
	 * @param to
	 * @return
	 */
	public List<MHRPeriod> getPeriods(Timestamp from , Timestamp to)
	{
		StringBuilder where = new StringBuilder();
		where.append(COLUMNNAME_HR_Payroll_ID).append("=? AND ");
		where.append(MHRPeriod.COLUMNNAME_StartDate).append(">=?  AND  ");
		where.append(MHRPeriod.COLUMNNAME_EndDate).append("<=?");
		return new Query(getCtx() , MHRPeriod.Table_Name , where.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(getHR_Payroll_ID() , from, to)
				.list();
	}

	/**
	 * get period list based on range dates
	 * method created to compatibility with groovy not support list only array
	 * @param from
	 * @param to
	 * @return
	 */
	public MHRPeriod[] getPeriodsAsArray(Timestamp from , Timestamp to)
	{
		List<MHRPeriod> periods = getPeriods(from, to);
		MHRPeriod[] arrayPeriods = new MHRPeriod[periods.size()];
		periods.toArray(arrayPeriods);
		return arrayPeriods;
	}

	/**
	 * Get HR period based on year and period no
	 * @param year
	 * @param periodNo
     * @return HR Period instance
     */
	public MHRPeriod getPeriodByPeriodNo(String year , int periodNo)
	{
		StringBuilder where = new StringBuilder();
		// SELECT  * FROM HR_Period WHERE HR_Payroll_ID = ? AND PeriodNo=? AND
		// EXISTS (SELECT 1 FROM C_Period period INNER JOIN C_Year year ON (period.C_Year_ID=year.C_Year_ID)
		// WHERE period.C_Period_ID=HR_Period.C_Period_ID AND year.FiscalYear = ?)
		where.append(COLUMNNAME_HR_Payroll_ID).append("=? AND ")
			 .append(MHRPeriod.COLUMNNAME_PeriodNo).append("=? AND ")
			  .append("EXISTS (SELECT 1 FROM ")
				.append(X_C_Period.Table_Name).append(" period ")
				.append(" INNER JOIN ").append(X_C_Year.Table_Name).append(" year ON (period.").append(X_C_Period.COLUMNNAME_C_Year_ID).append("=")
				.append("year.").append(X_C_Year.COLUMNNAME_C_Year_ID).append(") WHERE period.").append(X_C_Period.COLUMNNAME_C_Period_ID).append("=")
				.append(MHRPeriod.Table_Name).append(".").append(MHRPeriod.COLUMNNAME_C_Period_ID).append(" AND year.").append(X_C_Year.COLUMNNAME_FiscalYear).append("=?)");

		return new Query(getCtx() , MHRPeriod.Table_Name , where.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(getHR_Payroll_ID() , periodNo , year)
				.firstOnly();
	}
}	//	MPayroll
