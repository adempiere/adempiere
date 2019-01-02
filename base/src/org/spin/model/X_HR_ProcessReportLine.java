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

/** Generated Model for HR_ProcessReportLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_ProcessReportLine extends PO implements I_HR_ProcessReportLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_ProcessReportLine (Properties ctx, int HR_ProcessReportLine_ID, String trxName)
    {
      super (ctx, HR_ProcessReportLine_ID, trxName);
      /** if (HR_ProcessReportLine_ID == 0)
        {
			setHR_ProcessReportLine_ID (0);
			setHR_ProcessReport_ID (0);
			setIsAveraged (false);
// N
			setIsSummarized (false);
// N
        } */
    }

    /** Load Constructor */
    public X_HR_ProcessReportLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_ProcessReportLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getHR_Concept_ID(), get_TrxName());	}

	/** Set Global Payroll Concept.
		@param HR_Concept_ID 
		The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Global Payroll Concept.
		@return The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payroll Process Report Line.
		@param HR_ProcessReportLine_ID Payroll Process Report Line	  */
	public void setHR_ProcessReportLine_ID (int HR_ProcessReportLine_ID)
	{
		if (HR_ProcessReportLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportLine_ID, Integer.valueOf(HR_ProcessReportLine_ID));
	}

	/** Get Payroll Process Report Line.
		@return Payroll Process Report Line	  */
	public int getHR_ProcessReportLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ProcessReportLine_ID);
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

	/** Set Calculate Mean (µ).
		@param IsAveraged 
		Calculate Average of numeric content or length
	  */
	public void setIsAveraged (boolean IsAveraged)
	{
		set_Value (COLUMNNAME_IsAveraged, Boolean.valueOf(IsAveraged));
	}

	/** Get Calculate Mean (µ).
		@return Calculate Average of numeric content or length
	  */
	public boolean isAveraged () 
	{
		Object oo = get_Value(COLUMNNAME_IsAveraged);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Calculate Sum (S).
		@param IsSummarized 
		Calculate the Sum of numeric content or length
	  */
	public void setIsSummarized (boolean IsSummarized)
	{
		set_Value (COLUMNNAME_IsSummarized, Boolean.valueOf(IsSummarized));
	}

	/** Get Calculate Sum (S).
		@return Calculate the Sum of numeric content or length
	  */
	public boolean isSummarized () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummarized);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Text.
		@param PrintName 
		The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName)
	{
		set_Value (COLUMNNAME_PrintName, PrintName);
	}

	/** Get Print Text.
		@return The label text to be printed on a document or correspondence.
	  */
	public String getPrintName () 
	{
		return (String)get_Value(COLUMNNAME_PrintName);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
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