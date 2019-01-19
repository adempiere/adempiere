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

/** Generated Model for HR_ProcessReportTemplate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_ProcessReportTemplate extends PO implements I_HR_ProcessReportTemplate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_ProcessReportTemplate (Properties ctx, int HR_ProcessReportTemplate_ID, String trxName)
    {
      super (ctx, HR_ProcessReportTemplate_ID, trxName);
      /** if (HR_ProcessReportTemplate_ID == 0)
        {
			setAD_PrintFormat_ID (0);
			setHR_ProcessReportTemplate_ID (0);
			setHR_ProcessReport_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_ProcessReportTemplate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_ProcessReportTemplate[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFormat)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFormat.Table_Name)
			.getPO(getAD_PrintFormat_ID(), get_TrxName());	}

	/** Set Print Format.
		@param AD_PrintFormat_ID 
		Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
	{
		if (AD_PrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, Integer.valueOf(AD_PrintFormat_ID));
	}

	/** Get Print Format.
		@return Data Print Format
	  */
	public int getAD_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Payroll Process Report Template.
		@param HR_ProcessReportTemplate_ID Payroll Process Report Template	  */
	public void setHR_ProcessReportTemplate_ID (int HR_ProcessReportTemplate_ID)
	{
		if (HR_ProcessReportTemplate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportTemplate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportTemplate_ID, Integer.valueOf(HR_ProcessReportTemplate_ID));
	}

	/** Get Payroll Process Report Template.
		@return Payroll Process Report Template	  */
	public int getHR_ProcessReportTemplate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ProcessReportTemplate_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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