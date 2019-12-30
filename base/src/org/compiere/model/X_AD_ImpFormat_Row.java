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

/** Generated Model for AD_ImpFormat_Row
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_ImpFormat_Row extends PO implements I_AD_ImpFormat_Row, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_ImpFormat_Row (Properties ctx, int AD_ImpFormat_Row_ID, String trxName)
    {
      super (ctx, AD_ImpFormat_Row_ID, trxName);
      /** if (AD_ImpFormat_Row_ID == 0)
        {
			setAD_Column_ID (0);
			setAD_ImpFormat_ID (0);
			setAD_ImpFormat_Row_ID (0);
			setDataType (null);
			setDecimalPoint (null);
// .
			setDivideBy100 (false);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpFormat_Row WHERE AD_ImpFormat_ID=@AD_ImpFormat_ID@
        } */
    }

    /** Load Constructor */
    public X_AD_ImpFormat_Row (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ImpFormat_Row[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
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

	public org.compiere.model.I_AD_ImpFormat getAD_ImpFormat() throws RuntimeException
    {
		return (org.compiere.model.I_AD_ImpFormat)MTable.get(getCtx(), org.compiere.model.I_AD_ImpFormat.Table_Name)
			.getPO(getAD_ImpFormat_ID(), get_TrxName());	}

	/** Set Import Format.
		@param AD_ImpFormat_ID Import Format	  */
	public void setAD_ImpFormat_ID (int AD_ImpFormat_ID)
	{
		if (AD_ImpFormat_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpFormat_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpFormat_ID, Integer.valueOf(AD_ImpFormat_ID));
	}

	/** Get Import Format.
		@return Import Format	  */
	public int getAD_ImpFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Format Field.
		@param AD_ImpFormat_Row_ID Format Field	  */
	public void setAD_ImpFormat_Row_ID (int AD_ImpFormat_Row_ID)
	{
		if (AD_ImpFormat_Row_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ImpFormat_Row_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ImpFormat_Row_ID, Integer.valueOf(AD_ImpFormat_Row_ID));
	}

	/** Get Format Field.
		@return Format Field	  */
	public int getAD_ImpFormat_Row_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpFormat_Row_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Callout.
		@param Callout 
		Fully qualified class names and method - separated by semicolons
	  */
	public void setCallout (String Callout)
	{
		set_Value (COLUMNNAME_Callout, Callout);
	}

	/** Get Callout.
		@return Fully qualified class names and method - separated by semicolons
	  */
	public String getCallout () 
	{
		return (String)get_Value(COLUMNNAME_Callout);
	}

	/** Set Constant Value.
		@param ConstantValue 
		Constant value
	  */
	public void setConstantValue (String ConstantValue)
	{
		set_Value (COLUMNNAME_ConstantValue, ConstantValue);
	}

	/** Get Constant Value.
		@return Constant value
	  */
	public String getConstantValue () 
	{
		return (String)get_Value(COLUMNNAME_ConstantValue);
	}

	/** Set Data Format.
		@param DataFormat 
		Format String in Java Notation, e.g. ddMMyy
	  */
	public void setDataFormat (String DataFormat)
	{
		set_Value (COLUMNNAME_DataFormat, DataFormat);
	}

	/** Get Data Format.
		@return Format String in Java Notation, e.g. ddMMyy
	  */
	public String getDataFormat () 
	{
		return (String)get_Value(COLUMNNAME_DataFormat);
	}

	/** DataType AD_Reference_ID=210 */
	public static final int DATATYPE_AD_Reference_ID=210;
	/** String = S */
	public static final String DATATYPE_String = "S";
	/** Number = N */
	public static final String DATATYPE_Number = "N";
	/** Date = D */
	public static final String DATATYPE_Date = "D";
	/** Constant = C */
	public static final String DATATYPE_Constant = "C";
	/** Set Data Type.
		@param DataType 
		Type of data
	  */
	public void setDataType (String DataType)
	{

		set_Value (COLUMNNAME_DataType, DataType);
	}

	/** Get Data Type.
		@return Type of data
	  */
	public String getDataType () 
	{
		return (String)get_Value(COLUMNNAME_DataType);
	}

	/** Set Decimal Point.
		@param DecimalPoint 
		Decimal Point in the data file - if any
	  */
	public void setDecimalPoint (String DecimalPoint)
	{
		set_Value (COLUMNNAME_DecimalPoint, DecimalPoint);
	}

	/** Get Decimal Point.
		@return Decimal Point in the data file - if any
	  */
	public String getDecimalPoint () 
	{
		return (String)get_Value(COLUMNNAME_DecimalPoint);
	}

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
	}

	/** Set Divide by 100.
		@param DivideBy100 
		Divide number by 100 to get correct amount
	  */
	public void setDivideBy100 (boolean DivideBy100)
	{
		set_Value (COLUMNNAME_DivideBy100, Boolean.valueOf(DivideBy100));
	}

	/** Get Divide by 100.
		@return Divide number by 100 to get correct amount
	  */
	public boolean isDivideBy100 () 
	{
		Object oo = get_Value(COLUMNNAME_DivideBy100);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set End No.
		@param EndNo End No	  */
	public void setEndNo (int EndNo)
	{
		set_Value (COLUMNNAME_EndNo, Integer.valueOf(EndNo));
	}

	/** Get End No.
		@return End No	  */
	public int getEndNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EndNo);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Script.
		@param Script 
		Dynamic Java Language Script to calculate result
	  */
	public void setScript (String Script)
	{
		set_Value (COLUMNNAME_Script, Script);
	}

	/** Get Script.
		@return Dynamic Java Language Script to calculate result
	  */
	public String getScript () 
	{
		return (String)get_Value(COLUMNNAME_Script);
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

	/** Set Start No.
		@param StartNo 
		Starting number/position
	  */
	public void setStartNo (int StartNo)
	{
		set_Value (COLUMNNAME_StartNo, Integer.valueOf(StartNo));
	}

	/** Get Start No.
		@return Starting number/position
	  */
	public int getStartNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNo);
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