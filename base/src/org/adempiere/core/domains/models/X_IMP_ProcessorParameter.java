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

/** Generated Model for IMP_ProcessorParameter
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_IMP_ProcessorParameter extends PO implements I_IMP_ProcessorParameter, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_IMP_ProcessorParameter (Properties ctx, int IMP_ProcessorParameter_ID, String trxName)
    {
      super (ctx, IMP_ProcessorParameter_ID, trxName);
      /** if (IMP_ProcessorParameter_ID == 0)
        {
			setIMP_ProcessorParameter_ID (0);
			setIMP_Processor_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_IMP_ProcessorParameter (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IMP_ProcessorParameter[")
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Import Processor Parameter.
		@param IMP_ProcessorParameter_ID Import Processor Parameter	  */
	public void setIMP_ProcessorParameter_ID (int IMP_ProcessorParameter_ID)
	{
		if (IMP_ProcessorParameter_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IMP_ProcessorParameter_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IMP_ProcessorParameter_ID, Integer.valueOf(IMP_ProcessorParameter_ID));
	}

	/** Get Import Processor Parameter.
		@return Import Processor Parameter	  */
	public int getIMP_ProcessorParameter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IMP_ProcessorParameter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_IMP_Processor getIMP_Processor() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_IMP_Processor)MTable.get(getCtx(), org.adempiere.core.domains.models.I_IMP_Processor.Table_Name)
			.getPO(getIMP_Processor_ID(), get_TrxName());	}

	/** Set Import Processor.
		@param IMP_Processor_ID Import Processor	  */
	public void setIMP_Processor_ID (int IMP_Processor_ID)
	{
		if (IMP_Processor_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IMP_Processor_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IMP_Processor_ID, Integer.valueOf(IMP_Processor_ID));
	}

	/** Get Import Processor.
		@return Import Processor	  */
	public int getIMP_Processor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IMP_Processor_ID);
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

	/** Set Parameter Value.
		@param ParameterValue Parameter Value	  */
	public void setParameterValue (String ParameterValue)
	{
		set_Value (COLUMNNAME_ParameterValue, ParameterValue);
	}

	/** Get Parameter Value.
		@return Parameter Value	  */
	public String getParameterValue () 
	{
		return (String)get_Value(COLUMNNAME_ParameterValue);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}