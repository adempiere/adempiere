/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_CommissionType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_C_CommissionType extends PO implements I_C_CommissionType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_C_CommissionType (Properties ctx, int C_CommissionType_ID, String trxName)
    {
      super (ctx, C_CommissionType_ID, trxName);
      /** if (C_CommissionType_ID == 0)
        {
			setAD_View_ID (0);
			setAmountColumn_ID (0);
			setC_CommissionType_ID (0);
			setCurrencyColumn_ID (0);
			setDateDocColumn_ID (0);
			setName (null);
			setQuantityColumn_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_CommissionType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_CommissionType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_Rule getAD_Rule() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Rule)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Rule.Table_Name)
			.getPO(getAD_Rule_ID(), get_TrxName());	}

	/** Set Rule.
		@param AD_Rule_ID Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1) 
			set_Value (COLUMNNAME_AD_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
	}

	/** Get Rule.
		@return Rule	  */
	public int getAD_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_View getAD_View() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_View)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_View.Table_Name)
			.getPO(getAD_View_ID(), get_TrxName());	}

	/** Set View.
		@param AD_View_ID 
		View allows you to create dynamic views of information from the dictionary application
	  */
	public void setAD_View_ID (int AD_View_ID)
	{
		if (AD_View_ID < 1) 
			set_Value (COLUMNNAME_AD_View_ID, null);
		else 
			set_Value (COLUMNNAME_AD_View_ID, Integer.valueOf(AD_View_ID));
	}

	/** Get View.
		@return View allows you to create dynamic views of information from the dictionary application
	  */
	public int getAD_View_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_View_Column getAmountColumn() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_View_Column.Table_Name)
			.getPO(getAmountColumn_ID(), get_TrxName());	}

	/** Set Amount Column.
		@param AmountColumn_ID 
		Amount Column for get from View
	  */
	public void setAmountColumn_ID (int AmountColumn_ID)
	{
		if (AmountColumn_ID < 1) 
			set_Value (COLUMNNAME_AmountColumn_ID, null);
		else 
			set_Value (COLUMNNAME_AmountColumn_ID, Integer.valueOf(AmountColumn_ID));
	}

	/** Get Amount Column.
		@return Amount Column for get from View
	  */
	public int getAmountColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AmountColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Commission Type.
		@param C_CommissionType_ID 
		Defined for custom query on commission
	  */
	public void setC_CommissionType_ID (int C_CommissionType_ID)
	{
		if (C_CommissionType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_CommissionType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_CommissionType_ID, Integer.valueOf(C_CommissionType_ID));
	}

	/** Get Commission Type.
		@return Defined for custom query on commission
	  */
	public int getC_CommissionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CommissionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_View_Column getCurrencyColumn() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_View_Column.Table_Name)
			.getPO(getCurrencyColumn_ID(), get_TrxName());	}

	/** Set Currency Column.
		@param CurrencyColumn_ID 
		Currency Column for get from View
	  */
	public void setCurrencyColumn_ID (int CurrencyColumn_ID)
	{
		if (CurrencyColumn_ID < 1) 
			set_Value (COLUMNNAME_CurrencyColumn_ID, null);
		else 
			set_Value (COLUMNNAME_CurrencyColumn_ID, Integer.valueOf(CurrencyColumn_ID));
	}

	/** Get Currency Column.
		@return Currency Column for get from View
	  */
	public int getCurrencyColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrencyColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_View_Column getDateDocColumn() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_View_Column.Table_Name)
			.getPO(getDateDocColumn_ID(), get_TrxName());	}

	/** Set Date Doc Column.
		@param DateDocColumn_ID 
		Date Doc Column for get from View
	  */
	public void setDateDocColumn_ID (int DateDocColumn_ID)
	{
		if (DateDocColumn_ID < 1) 
			set_Value (COLUMNNAME_DateDocColumn_ID, null);
		else 
			set_Value (COLUMNNAME_DateDocColumn_ID, Integer.valueOf(DateDocColumn_ID));
	}

	/** Get Date Doc Column.
		@return Date Doc Column for get from View
	  */
	public int getDateDocColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DateDocColumn_ID);
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

	public org.adempiere.core.domains.models.I_AD_View_Column getQuantityColumn() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_View_Column.Table_Name)
			.getPO(getQuantityColumn_ID(), get_TrxName());	}

	/** Set Quantity Column.
		@param QuantityColumn_ID 
		Quantity Column for get from View
	  */
	public void setQuantityColumn_ID (int QuantityColumn_ID)
	{
		if (QuantityColumn_ID < 1) 
			set_Value (COLUMNNAME_QuantityColumn_ID, null);
		else 
			set_Value (COLUMNNAME_QuantityColumn_ID, Integer.valueOf(QuantityColumn_ID));
	}

	/** Get Quantity Column.
		@return Quantity Column for get from View
	  */
	public int getQuantityColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuantityColumn_ID);
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

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}