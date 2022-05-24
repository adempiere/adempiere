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
package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_TransactionTypeAttribute
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_TransactionTypeAttribute extends PO implements I_FM_TransactionTypeAttribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_TransactionTypeAttribute (Properties ctx, int FM_TransactionTypeAttribute_ID, String trxName)
    {
      super (ctx, FM_TransactionTypeAttribute_ID, trxName);
      /** if (FM_TransactionTypeAttribute_ID == 0)
        {
			setFM_TransactionTypeAttribute_ID (0);
			setFM_TransactionType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FM_TransactionTypeAttribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_TransactionTypeAttribute[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_FunctionalSetting getFM_FunctionalSetting() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_FunctionalSetting)MTable.get(getCtx(), org.spin.investment.model.I_FM_FunctionalSetting.Table_Name)
			.getPO(getFM_FunctionalSetting_ID(), get_TrxName());	}

	/** Set Financial Functional Setting.
		@param FM_FunctionalSetting_ID Financial Functional Setting	  */
	public void setFM_FunctionalSetting_ID (int FM_FunctionalSetting_ID)
	{
		if (FM_FunctionalSetting_ID < 1) 
			set_Value (COLUMNNAME_FM_FunctionalSetting_ID, null);
		else 
			set_Value (COLUMNNAME_FM_FunctionalSetting_ID, Integer.valueOf(FM_FunctionalSetting_ID));
	}

	/** Get Financial Functional Setting.
		@return Financial Functional Setting	  */
	public int getFM_FunctionalSetting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_FunctionalSetting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_Product getFM_Product() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Product)MTable.get(getCtx(), org.spin.investment.model.I_FM_Product.Table_Name)
			.getPO(getFM_Product_ID(), get_TrxName());	}

	/** Set Financial Product.
		@param FM_Product_ID Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID)
	{
		if (FM_Product_ID < 1) 
			set_Value (COLUMNNAME_FM_Product_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Product_ID, Integer.valueOf(FM_Product_ID));
	}

	/** Get Financial Product.
		@return Financial Product	  */
	public int getFM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Transaction Type Attribute.
		@param FM_TransactionTypeAttribute_ID Financial Transaction Type Attribute	  */
	public void setFM_TransactionTypeAttribute_ID (int FM_TransactionTypeAttribute_ID)
	{
		if (FM_TransactionTypeAttribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionTypeAttribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionTypeAttribute_ID, Integer.valueOf(FM_TransactionTypeAttribute_ID));
	}

	/** Get Financial Transaction Type Attribute.
		@return Financial Transaction Type Attribute	  */
	public int getFM_TransactionTypeAttribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_TransactionTypeAttribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_TransactionType getFM_TransactionType() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_TransactionType)MTable.get(getCtx(), org.spin.investment.model.I_FM_TransactionType.Table_Name)
			.getPO(getFM_TransactionType_ID(), get_TrxName());	}

	/** Set Financial Transaction Type.
		@param FM_TransactionType_ID Financial Transaction Type	  */
	public void setFM_TransactionType_ID (int FM_TransactionType_ID)
	{
		if (FM_TransactionType_ID < 1) 
			set_Value (COLUMNNAME_FM_TransactionType_ID, null);
		else 
			set_Value (COLUMNNAME_FM_TransactionType_ID, Integer.valueOf(FM_TransactionType_ID));
	}

	/** Get Financial Transaction Type.
		@return Financial Transaction Type	  */
	public int getFM_TransactionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_TransactionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFM_TransactionType_ID()));
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