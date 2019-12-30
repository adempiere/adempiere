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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_WF_ProcessData
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_WF_ProcessData extends PO implements I_AD_WF_ProcessData, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_WF_ProcessData (Properties ctx, int AD_WF_ProcessData_ID, String trxName)
    {
      super (ctx, AD_WF_ProcessData_ID, trxName);
      /** if (AD_WF_ProcessData_ID == 0)
        {
			setAD_WF_ProcessData_ID (0);
			setAD_WF_Process_ID (0);
			setAttributeName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_WF_ProcessData (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_WF_ProcessData[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Workflow Process Data.
		@param AD_WF_ProcessData_ID 
		Workflow Process Context
	  */
	public void setAD_WF_ProcessData_ID (int AD_WF_ProcessData_ID)
	{
		if (AD_WF_ProcessData_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_WF_ProcessData_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_WF_ProcessData_ID, Integer.valueOf(AD_WF_ProcessData_ID));
	}

	/** Get Workflow Process Data.
		@return Workflow Process Context
	  */
	public int getAD_WF_ProcessData_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_ProcessData_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_WF_Process getAD_WF_Process() throws RuntimeException
    {
		return (org.compiere.model.I_AD_WF_Process)MTable.get(getCtx(), org.compiere.model.I_AD_WF_Process.Table_Name)
			.getPO(getAD_WF_Process_ID(), get_TrxName());	}

	/** Set Workflow Process.
		@param AD_WF_Process_ID 
		Actual Workflow Process Instance
	  */
	public void setAD_WF_Process_ID (int AD_WF_Process_ID)
	{
		if (AD_WF_Process_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_WF_Process_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_WF_Process_ID, Integer.valueOf(AD_WF_Process_ID));
	}

	/** Get Workflow Process.
		@return Actual Workflow Process Instance
	  */
	public int getAD_WF_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_Process_ID()));
    }

	/** Set Attribute Name.
		@param AttributeName 
		Name of the Attribute
	  */
	public void setAttributeName (String AttributeName)
	{
		set_Value (COLUMNNAME_AttributeName, AttributeName);
	}

	/** Get Attribute Name.
		@return Name of the Attribute
	  */
	public String getAttributeName () 
	{
		return (String)get_Value(COLUMNNAME_AttributeName);
	}

	/** Set Attribute Value.
		@param AttributeValue 
		Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue)
	{
		set_Value (COLUMNNAME_AttributeValue, AttributeValue);
	}

	/** Get Attribute Value.
		@return Value of the Attribute
	  */
	public String getAttributeValue () 
	{
		return (String)get_Value(COLUMNNAME_AttributeValue);
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