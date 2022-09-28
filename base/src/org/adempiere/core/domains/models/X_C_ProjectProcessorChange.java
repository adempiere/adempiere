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
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for C_ProjectProcessorChange
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_C_ProjectProcessorChange extends PO implements I_C_ProjectProcessorChange, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_C_ProjectProcessorChange (Properties ctx, int C_ProjectProcessorChange_ID, String trxName)
    {
      super (ctx, C_ProjectProcessorChange_ID, trxName);
      /** if (C_ProjectProcessorChange_ID == 0)
        {
			setC_ProjectProcessorChange_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectProcessorChange (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectProcessorChange[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Column)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Table)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Processor Change ID.
		@param C_ProjectProcessorChange_ID Project Processor Change ID	  */
	public void setC_ProjectProcessorChange_ID (int C_ProjectProcessorChange_ID)
	{
		if (C_ProjectProcessorChange_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorChange_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorChange_ID, Integer.valueOf(C_ProjectProcessorChange_ID));
	}

	/** Get Project Processor Change ID.
		@return Project Processor Change ID	  */
	public int getC_ProjectProcessorChange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessorChange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_ProjectProcessorLog getC_ProjectProcessorLog() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_ProjectProcessorLog)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_ProjectProcessorLog.Table_Name)
			.getPO(getC_ProjectProcessorLog_ID(), get_TrxName());	}

	/** Set Project Processor Log.
		@param C_ProjectProcessorLog_ID Project Processor Log	  */
	public void setC_ProjectProcessorLog_ID (int C_ProjectProcessorLog_ID)
	{
		if (C_ProjectProcessorLog_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectProcessorLog_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectProcessorLog_ID, Integer.valueOf(C_ProjectProcessorLog_ID));
	}

	/** Get Project Processor Log.
		@return Project Processor Log	  */
	public int getC_ProjectProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set New Value.
		@param NewValue 
		New field value
	  */
	public void setNewValue (String NewValue)
	{
		set_Value (COLUMNNAME_NewValue, NewValue);
	}

	/** Get New Value.
		@return New field value
	  */
	public String getNewValue () 
	{
		return (String)get_Value(COLUMNNAME_NewValue);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
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