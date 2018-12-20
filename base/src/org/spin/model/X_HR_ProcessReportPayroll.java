/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_ProcessReportPayroll
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_ProcessReportPayroll extends PO implements I_HR_ProcessReportPayroll, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_ProcessReportPayroll (Properties ctx, int HR_ProcessReportPayroll_ID, String trxName)
    {
      super (ctx, HR_ProcessReportPayroll_ID, trxName);
      /** if (HR_ProcessReportPayroll_ID == 0)
        {
			setHR_Payroll_ID (0);
			setHR_ProcessReportPayroll_ID (0);
			setHR_ProcessReport_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_ProcessReportPayroll (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_ProcessReportPayroll[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_HR_Payroll getHR_Payroll() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Payroll)MTable.get(getCtx(), org.eevolution.model.I_HR_Payroll.Table_Name)
			.getPO(getHR_Payroll_ID(), get_TrxName());	}

	/** Set Payroll.
		@param HR_Payroll_ID Payroll	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID)
	{
		if (HR_Payroll_ID < 1) 
			set_Value (COLUMNNAME_HR_Payroll_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Payroll_ID, Integer.valueOf(HR_Payroll_ID));
	}

	/** Get Payroll.
		@return Payroll	  */
	public int getHR_Payroll_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Payroll_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process Report of Payroll.
		@param HR_ProcessReportPayroll_ID Process Report of Payroll	  */
	public void setHR_ProcessReportPayroll_ID (int HR_ProcessReportPayroll_ID)
	{
		if (HR_ProcessReportPayroll_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportPayroll_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportPayroll_ID, Integer.valueOf(HR_ProcessReportPayroll_ID));
	}

	/** Get Process Report of Payroll.
		@return Process Report of Payroll	  */
	public int getHR_ProcessReportPayroll_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ProcessReportPayroll_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.model.I_HR_ProcessReport getHR_ProcessReport() throws RuntimeException
    {
		return (org.spin.model.I_HR_ProcessReport)MTable.get(getCtx(), org.spin.model.I_HR_ProcessReport.Table_Name)
			.getPO(getHR_ProcessReport_ID(), get_TrxName());	}

	/** Set Payroll Process Report.
		@param HR_ProcessReport_ID Payroll Process Report	  */
	public void setHR_ProcessReport_ID (int HR_ProcessReport_ID)
	{
		if (HR_ProcessReport_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReport_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReport_ID, Integer.valueOf(HR_ProcessReport_ID));
	}

	/** Get Payroll Process Report.
		@return Payroll Process Report	  */
	public int getHR_ProcessReport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ProcessReport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}