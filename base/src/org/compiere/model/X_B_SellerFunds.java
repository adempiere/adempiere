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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for B_SellerFunds
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_B_SellerFunds extends PO implements I_B_SellerFunds, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_B_SellerFunds (Properties ctx, int B_SellerFunds_ID, String trxName)
    {
      super (ctx, B_SellerFunds_ID, trxName);
      /** if (B_SellerFunds_ID == 0)
        {
			setAD_User_ID (0);
			setB_SellerFunds_ID (0);
			setCommittedAmt (Env.ZERO);
			setNonCommittedAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_B_SellerFunds (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_B_SellerFunds[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
    }

	/** Set Seller Funds.
		@param B_SellerFunds_ID 
		Seller Funds from Offers on Topics
	  */
	public void setB_SellerFunds_ID (int B_SellerFunds_ID)
	{
		if (B_SellerFunds_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_B_SellerFunds_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_B_SellerFunds_ID, Integer.valueOf(B_SellerFunds_ID));
	}

	/** Get Seller Funds.
		@return Seller Funds from Offers on Topics
	  */
	public int getB_SellerFunds_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_SellerFunds_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_Value (COLUMNNAME_C_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Committed Amount.
		@param CommittedAmt 
		The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt)
	{
		set_Value (COLUMNNAME_CommittedAmt, CommittedAmt);
	}

	/** Get Committed Amount.
		@return The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CommittedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Not Committed Aount.
		@param NonCommittedAmt 
		Amount not committed yet
	  */
	public void setNonCommittedAmt (BigDecimal NonCommittedAmt)
	{
		set_Value (COLUMNNAME_NonCommittedAmt, NonCommittedAmt);
	}

	/** Get Not Committed Aount.
		@return Amount not committed yet
	  */
	public BigDecimal getNonCommittedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NonCommittedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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