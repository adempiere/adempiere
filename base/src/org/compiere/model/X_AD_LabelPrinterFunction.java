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

/** Generated Model for AD_LabelPrinterFunction
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_LabelPrinterFunction extends PO implements I_AD_LabelPrinterFunction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_LabelPrinterFunction (Properties ctx, int AD_LabelPrinterFunction_ID, String trxName)
    {
      super (ctx, AD_LabelPrinterFunction_ID, trxName);
      /** if (AD_LabelPrinterFunction_ID == 0)
        {
			setAD_LabelPrinterFunction_ID (0);
			setAD_LabelPrinter_ID (0);
			setIsXYPosition (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_LabelPrinterFunction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_LabelPrinterFunction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Label printer Function.
		@param AD_LabelPrinterFunction_ID 
		Function of Label Printer
	  */
	public void setAD_LabelPrinterFunction_ID (int AD_LabelPrinterFunction_ID)
	{
		if (AD_LabelPrinterFunction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_LabelPrinterFunction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_LabelPrinterFunction_ID, Integer.valueOf(AD_LabelPrinterFunction_ID));
	}

	/** Get Label printer Function.
		@return Function of Label Printer
	  */
	public int getAD_LabelPrinterFunction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_LabelPrinterFunction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_LabelPrinter getAD_LabelPrinter() throws RuntimeException
    {
		return (org.compiere.model.I_AD_LabelPrinter)MTable.get(getCtx(), org.compiere.model.I_AD_LabelPrinter.Table_Name)
			.getPO(getAD_LabelPrinter_ID(), get_TrxName());	}

	/** Set Label printer.
		@param AD_LabelPrinter_ID 
		Label Printer Definition
	  */
	public void setAD_LabelPrinter_ID (int AD_LabelPrinter_ID)
	{
		if (AD_LabelPrinter_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_LabelPrinter_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_LabelPrinter_ID, Integer.valueOf(AD_LabelPrinter_ID));
	}

	/** Get Label printer.
		@return Label Printer Definition
	  */
	public int getAD_LabelPrinter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_LabelPrinter_ID);
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

	/** Set Function Prefix.
		@param FunctionPrefix 
		Data sent before the function
	  */
	public void setFunctionPrefix (String FunctionPrefix)
	{
		set_Value (COLUMNNAME_FunctionPrefix, FunctionPrefix);
	}

	/** Get Function Prefix.
		@return Data sent before the function
	  */
	public String getFunctionPrefix () 
	{
		return (String)get_Value(COLUMNNAME_FunctionPrefix);
	}

	/** Set Function Suffix.
		@param FunctionSuffix 
		Data sent after the function
	  */
	public void setFunctionSuffix (String FunctionSuffix)
	{
		set_Value (COLUMNNAME_FunctionSuffix, FunctionSuffix);
	}

	/** Get Function Suffix.
		@return Data sent after the function
	  */
	public String getFunctionSuffix () 
	{
		return (String)get_Value(COLUMNNAME_FunctionSuffix);
	}

	/** Set XY Position.
		@param IsXYPosition 
		The Function is XY position
	  */
	public void setIsXYPosition (boolean IsXYPosition)
	{
		set_Value (COLUMNNAME_IsXYPosition, Boolean.valueOf(IsXYPosition));
	}

	/** Get XY Position.
		@return The Function is XY position
	  */
	public boolean isXYPosition () 
	{
		Object oo = get_Value(COLUMNNAME_IsXYPosition);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set XY Separator.
		@param XYSeparator 
		The separator between the X and Y function.
	  */
	public void setXYSeparator (String XYSeparator)
	{
		set_Value (COLUMNNAME_XYSeparator, XYSeparator);
	}

	/** Get XY Separator.
		@return The separator between the X and Y function.
	  */
	public String getXYSeparator () 
	{
		return (String)get_Value(COLUMNNAME_XYSeparator);
	}
}