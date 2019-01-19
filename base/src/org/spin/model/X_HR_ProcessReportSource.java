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

/** Generated Model for HR_ProcessReportSource
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_ProcessReportSource extends PO implements I_HR_ProcessReportSource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_ProcessReportSource (Properties ctx, int HR_ProcessReportSource_ID, String trxName)
    {
      super (ctx, HR_ProcessReportSource_ID, trxName);
      /** if (HR_ProcessReportSource_ID == 0)
        {
			setHR_Concept_ID (0);
			setHR_ProcessReportLine_ID (0);
			setHR_ProcessReportSource_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_ProcessReportSource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_ProcessReportSource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** ColumnType AD_Reference_ID=53243 */
	public static final int COLUMNTYPE_AD_Reference_ID=53243;
	/** Amount = A */
	public static final String COLUMNTYPE_Amount = "A";
	/** Date = D */
	public static final String COLUMNTYPE_Date = "D";
	/** Quantity = Q */
	public static final String COLUMNTYPE_Quantity = "Q";
	/** Text = T */
	public static final String COLUMNTYPE_Text = "T";
	/** Set Column Type.
		@param ColumnType Column Type	  */
	public void setColumnType (String ColumnType)
	{

		set_Value (COLUMNNAME_ColumnType, ColumnType);
	}

	/** Get Column Type.
		@return Column Type	  */
	public String getColumnType () 
	{
		return (String)get_Value(COLUMNNAME_ColumnType);
	}

	/** Set Format Pattern.
		@param FormatPattern 
		The pattern used to format a number or date.
	  */
	public void setFormatPattern (String FormatPattern)
	{
		set_Value (COLUMNNAME_FormatPattern, FormatPattern);
	}

	/** Get Format Pattern.
		@return The pattern used to format a number or date.
	  */
	public String getFormatPattern () 
	{
		return (String)get_Value(COLUMNNAME_FormatPattern);
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

	public org.spin.model.I_HR_ProcessReportLine getHR_ProcessReportLine() throws RuntimeException
    {
		return (org.spin.model.I_HR_ProcessReportLine)MTable.get(getCtx(), org.spin.model.I_HR_ProcessReportLine.Table_Name)
			.getPO(getHR_ProcessReportLine_ID(), get_TrxName());	}

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

	/** Set Payroll Process Report Source.
		@param HR_ProcessReportSource_ID Payroll Process Report Source	  */
	public void setHR_ProcessReportSource_ID (int HR_ProcessReportSource_ID)
	{
		if (HR_ProcessReportSource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportSource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ProcessReportSource_ID, Integer.valueOf(HR_ProcessReportSource_ID));
	}

	/** Get Payroll Process Report Source.
		@return Payroll Process Report Source	  */
	public int getHR_ProcessReportSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ProcessReportSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prefix.
		@param Prefix 
		Prefix before the sequence number
	  */
	public void setPrefix (String Prefix)
	{
		set_Value (COLUMNNAME_Prefix, Prefix);
	}

	/** Get Prefix.
		@return Prefix before the sequence number
	  */
	public String getPrefix () 
	{
		return (String)get_Value(COLUMNNAME_Prefix);
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

	/** Set Suffix.
		@param Suffix 
		Suffix after the number
	  */
	public void setSuffix (String Suffix)
	{
		set_Value (COLUMNNAME_Suffix, Suffix);
	}

	/** Get Suffix.
		@return Suffix after the number
	  */
	public String getSuffix () 
	{
		return (String)get_Value(COLUMNNAME_Suffix);
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