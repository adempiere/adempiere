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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for DD_Requirement
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_DD_Requirement extends PO implements I_DD_Requirement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190501L;

    /** Standard Constructor */
    public X_DD_Requirement (Properties ctx, int DD_Requirement_ID, String trxName)
    {
      super (ctx, DD_Requirement_ID, trxName);
      /** if (DD_Requirement_ID == 0)
        {
			setDD_Requirement_ID (0);
			setDD_RequirementType_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DD_Requirement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Requirement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transport Requirement.
		@param DD_Requirement_ID Transport Requirement	  */
	public void setDD_Requirement_ID (int DD_Requirement_ID)
	{
		if (DD_Requirement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Requirement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Requirement_ID, Integer.valueOf(DD_Requirement_ID));
	}

	/** Get Transport Requirement.
		@return Transport Requirement	  */
	public int getDD_Requirement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Requirement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_RequirementType getDD_RequirementType() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_RequirementType)MTable.get(getCtx(), org.eevolution.model.I_DD_RequirementType.Table_Name)
			.getPO(getDD_RequirementType_ID(), get_TrxName());	}

	/** Set Transport Requirement Type.
		@param DD_RequirementType_ID Transport Requirement Type	  */
	public void setDD_RequirementType_ID (int DD_RequirementType_ID)
	{
		if (DD_RequirementType_ID < 1) 
			set_Value (COLUMNNAME_DD_RequirementType_ID, null);
		else 
			set_Value (COLUMNNAME_DD_RequirementType_ID, Integer.valueOf(DD_RequirementType_ID));
	}

	/** Get Transport Requirement Type.
		@return Transport Requirement Type	  */
	public int getDD_RequirementType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_RequirementType_ID);
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

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}