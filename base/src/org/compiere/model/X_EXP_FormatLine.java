/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXP_FormatLine
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_EXP_FormatLine extends PO implements I_EXP_FormatLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_EXP_FormatLine (Properties ctx, int EXP_FormatLine_ID, String trxName)
    {
      super (ctx, EXP_FormatLine_ID, trxName);
      /** if (EXP_FormatLine_ID == 0)
        {
			setAD_Column_ID (0);
			setEXP_FormatLine_ID (0);
			setName (null);
			setType (null);
// E
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXP_FormatLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXP_FormatLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException
    {
		return (I_AD_Column)MTable.get(getCtx(), I_AD_Column.Table_Name)
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

	public I_AD_Reference getAD_Reference() throws RuntimeException
    {
		return (I_AD_Reference)MTable.get(getCtx(), I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_ID(), get_TrxName());	}

	/** Set Reference.
		@param AD_Reference_ID 
		System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID)
	{
		throw new IllegalArgumentException ("AD_Reference_ID is virtual column");	}

	/** Get Reference.
		@return System Reference and Validation
	  */
	public int getAD_Reference_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Format.
		@param DateFormat 
		Date format used in the imput format
	  */
	public void setDateFormat (String DateFormat)
	{
		set_Value (COLUMNNAME_DateFormat, DateFormat);
	}

	/** Get Date Format.
		@return Date format used in the imput format
	  */
	public String getDateFormat () 
	{
		return (String)get_Value(COLUMNNAME_DateFormat);
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

	public org.compiere.model.I_EXP_Format getEXP_EmbeddedFormat() throws RuntimeException
    {
		return (org.compiere.model.I_EXP_Format)MTable.get(getCtx(), org.compiere.model.I_EXP_Format.Table_Name)
			.getPO(getEXP_EmbeddedFormat_ID(), get_TrxName());	}

	/** Set Embedded Format.
		@param EXP_EmbeddedFormat_ID Embedded Format	  */
	public void setEXP_EmbeddedFormat_ID (int EXP_EmbeddedFormat_ID)
	{
		if (EXP_EmbeddedFormat_ID < 1) 
			set_Value (COLUMNNAME_EXP_EmbeddedFormat_ID, null);
		else 
			set_Value (COLUMNNAME_EXP_EmbeddedFormat_ID, Integer.valueOf(EXP_EmbeddedFormat_ID));
	}

	/** Get Embedded Format.
		@return Embedded Format	  */
	public int getEXP_EmbeddedFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_EmbeddedFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_EXP_Format getEXP_Format() throws RuntimeException
    {
		return (org.compiere.model.I_EXP_Format)MTable.get(getCtx(), org.compiere.model.I_EXP_Format.Table_Name)
			.getPO(getEXP_Format_ID(), get_TrxName());	}

	/** Set Export Format.
		@param EXP_Format_ID Export Format	  */
	public void setEXP_Format_ID (int EXP_Format_ID)
	{
		if (EXP_Format_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXP_Format_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXP_Format_ID, Integer.valueOf(EXP_Format_ID));
	}

	/** Get Export Format.
		@return Export Format	  */
	public int getEXP_Format_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_Format_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Format Line.
		@param EXP_FormatLine_ID Format Line	  */
	public void setEXP_FormatLine_ID (int EXP_FormatLine_ID)
	{
		if (EXP_FormatLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXP_FormatLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXP_FormatLine_ID, Integer.valueOf(EXP_FormatLine_ID));
	}

	/** Get Format Line.
		@return Format Line	  */
	public int getEXP_FormatLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_FormatLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Is Part Unique Index.
		@param IsPartUniqueIndex Is Part Unique Index	  */
	public void setIsPartUniqueIndex (boolean IsPartUniqueIndex)
	{
		set_Value (COLUMNNAME_IsPartUniqueIndex, Boolean.valueOf(IsPartUniqueIndex));
	}

	/** Get Is Part Unique Index.
		@return Is Part Unique Index	  */
	public boolean isPartUniqueIndex () 
	{
		Object oo = get_Value(COLUMNNAME_IsPartUniqueIndex);
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

	/** Set Position.
		@param Position Position	  */
	public void setPosition (int Position)
	{
		set_Value (COLUMNNAME_Position, Integer.valueOf(Position));
	}

	/** Get Position.
		@return Position	  */
	public int getPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Position);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=53241 */
	public static final int TYPE_AD_Reference_ID=53241;
	/** XML Element = E */
	public static final String TYPE_XMLElement = "E";
	/** XML Attribute = A */
	public static final String TYPE_XMLAttribute = "A";
	/** Embedded EXP Format = M */
	public static final String TYPE_EmbeddedEXPFormat = "M";
	/** Referenced EXP Format = R */
	public static final String TYPE_ReferencedEXPFormat = "R";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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