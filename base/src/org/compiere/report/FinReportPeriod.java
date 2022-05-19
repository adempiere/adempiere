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
package org.compiere.report;

import java.sql.Timestamp;

import org.compiere.model.MPeriod;
import org.compiere.util.DB;

/**
 *  Financial Report Periods
 *
 *  @author Jorg Janke
 *  @version $Id: FinReportPeriod.java,v 1.3 2006/08/03 22:16:52 jjanke Exp $
 */
public class FinReportPeriod
{
	/**
	 *	Constructor
	 * 	@param periodId period
	 * 	@param name name
	 * 	@param startDate period start date
	 * 	@param endDate period end date
	 * 	@param yearStartDate year start date
	 *  @param periodType Period Type
	*/
	public FinReportPeriod (int periodId, String name, Timestamp startDate, Timestamp endDate, Timestamp yearStartDate, String periodType)
	{
		this.periodId = periodId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.yearStartDate = yearStartDate;
		this.periodType = periodType;
	}	//

	private int periodId;
	private String name;
	private Timestamp startDate;
	private Timestamp endDate;
	private Timestamp yearStartDate;

	private String periodType;


	public String getPeriodTypeWhere(String alias, boolean includeAdjustmentPeriod) {
		String periodTypeWhere = "";
		if (includeAdjustmentPeriod)
			periodTypeWhere = "IN ('S','A')";
		else
			periodTypeWhere = "IN ('S')";

		return " EXISTS (SELECT 1 FROM C_Period WHERE C_Period.C_Period_ID = "+alias+".C_Period_ID AND C_Period.PeriodType " +periodTypeWhere+") ";
	}

	/*
	 * 	Get Period Info
	 * 	@return BETWEEN start AND end
	 */
	public String getPeriodWhere ()
	{
		StringBuffer sql = new StringBuffer ("BETWEEN ");
		sql.append(DB.TO_DATE(startDate))
			.append(" AND ")
			.append(DB.TO_DATE(endDate));
		return sql.toString();
	}	//	getPeriodWhere

	/**
	 * 	Get Year Info
	 * 	@return BETWEEN start AND end
	 */
	public String getYearWhere ()
	{
		StringBuffer sql = new StringBuffer ("BETWEEN ");
		sql.append(DB.TO_DATE(yearStartDate))
			  .append(" AND ")
			  .append(DB.TO_DATE(endDate));
		return sql.toString();
	}	//	getPeriodWhere

	/**
	 * 	Get Total Info
	 * 	@return <= end
	 */
	public String getTotalWhere ()
	{
		StringBuffer sql = new StringBuffer ("<= ");
		sql.append(DB.TO_DATE(endDate));
		return sql.toString();
	}	//	getPeriodWhere

	/**
	 * 	Is date in period
	 * 	@param date date
	 * 	@return true if in period
	 */
	public boolean inPeriod (Timestamp date)
	{
		if (date == null)
			return false;
		if (date.before(startDate))
			return false;
		if (date.after(endDate))
			return false;
		return true;
	}	//	inPeriod

	/**
	 * 	Get Name
	 *	@return name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * 	Get C_Period_ID
	 *	@return period
	 */
	public int getC_Period_ID()
	{
		return periodId;
	}
	/**
	 * 	Get End Date
	 *	@return end date
	 */
	public Timestamp getEndDate()
	{
		return endDate;
	}
	/**
	 * 	Get Start Date
	 *	@return start date
	 */
	public Timestamp getStartDate()
	{
		return startDate;
	}
	/**
	 * 	Get Year Start Date
	 *	@return year start date
	 */
	public Timestamp getYearStartDate()
	{
		return yearStartDate;
	}

	/**
	 * Get natural balance dateacct filter
	 * @param alias table name or alias name
	 * @return is balance sheet a/c and <= end or BETWEEN start AND end 
	 */
	public String getNaturalWhere(String alias) {
		String yearWhere = getYearWhere();
		String totalWhere = getTotalWhere();
		String bs = " EXISTS (SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = " + alias + ".Account_ID AND AccountType NOT IN ('R', 'E'))";
		String full = totalWhere + " AND ( " + bs + " OR TRUNC(" + alias + ".DateAcct, 'DD') " + yearWhere + " ) ";
		
		return full;
	}

}	//	FinReportPeriod
