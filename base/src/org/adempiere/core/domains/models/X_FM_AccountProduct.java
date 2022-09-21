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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_AccountProduct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_AccountProduct extends PO implements I_FM_AccountProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_AccountProduct (Properties ctx, int FM_AccountProduct_ID, String trxName)
    {
      super (ctx, FM_AccountProduct_ID, trxName);
      /** if (FM_AccountProduct_ID == 0)
        {
			setFM_Account_ID (0);
			setFM_AccountProduct_ID (0);
			setFM_Product_ID (0);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_FM_AccountProduct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_AccountProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_FM_Account getFM_Account() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Account)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Account.Table_Name)
			.getPO(getFM_Account_ID(), get_TrxName());	}

	/** Set Financial Account.
		@param FM_Account_ID Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID)
	{
		if (FM_Account_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, Integer.valueOf(FM_Account_ID));
	}

	/** Get Financial Account.
		@return Financial Account	  */
	public int getFM_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFM_Account_ID()));
    }

	/** Set Financial Account Product.
		@param FM_AccountProduct_ID Financial Account Product	  */
	public void setFM_AccountProduct_ID (int FM_AccountProduct_ID)
	{
		if (FM_AccountProduct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_AccountProduct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_AccountProduct_ID, Integer.valueOf(FM_AccountProduct_ID));
	}

	/** Get Financial Account Product.
		@return Financial Account Product	  */
	public int getFM_AccountProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_AccountProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Product getFM_Product() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Product)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Product.Table_Name)
			.getPO(getFM_Product_ID(), get_TrxName());	}

	/** Set Financial Product.
		@param FM_Product_ID Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID)
	{
		if (FM_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Product_ID, Integer.valueOf(FM_Product_ID));
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_ValueNoCheck (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}