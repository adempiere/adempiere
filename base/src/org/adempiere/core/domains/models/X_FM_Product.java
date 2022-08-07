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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_Product extends PO implements I_FM_Product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_Product (Properties ctx, int FM_Product_ID, String trxName)
    {
      super (ctx, FM_Product_ID, trxName);
      /** if (FM_Product_ID == 0)
        {
			setFM_ProductCategory_ID (0);
			setFM_Product_ID (0);
			setM_Product_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FM_Product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_Product[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Charge)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Currency)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Rate getDunningInterest() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Rate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Rate.Table_Name)
			.getPO(getDunningInterest_ID(), get_TrxName());	}

	/** Set Dunning Interest.
		@param DunningInterest_ID Dunning Interest	  */
	public void setDunningInterest_ID (int DunningInterest_ID)
	{
		if (DunningInterest_ID < 1) 
			set_Value (COLUMNNAME_DunningInterest_ID, null);
		else 
			set_Value (COLUMNNAME_DunningInterest_ID, Integer.valueOf(DunningInterest_ID));
	}

	/** Get Dunning Interest.
		@return Dunning Interest	  */
	public int getDunningInterest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DunningInterest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Dunning getFM_Dunning() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Dunning)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Dunning.Table_Name)
			.getPO(getFM_Dunning_ID(), get_TrxName());	}

	/** Set Financial Dunning.
		@param FM_Dunning_ID Financial Dunning	  */
	public void setFM_Dunning_ID (int FM_Dunning_ID)
	{
		if (FM_Dunning_ID < 1) 
			set_Value (COLUMNNAME_FM_Dunning_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Dunning_ID, Integer.valueOf(FM_Dunning_ID));
	}

	/** Get Financial Dunning.
		@return Financial Dunning	  */
	public int getFM_Dunning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Dunning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_ProductCategory getFM_ProductCategory() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_ProductCategory)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_ProductCategory.Table_Name)
			.getPO(getFM_ProductCategory_ID(), get_TrxName());	}

	/** Set Financial Product Category.
		@param FM_ProductCategory_ID Financial Product Category	  */
	public void setFM_ProductCategory_ID (int FM_ProductCategory_ID)
	{
		if (FM_ProductCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_ProductCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_ProductCategory_ID, Integer.valueOf(FM_ProductCategory_ID));
	}

	/** Get Financial Product Category.
		@return Financial Product Category	  */
	public int getFM_ProductCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_ProductCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	public org.adempiere.core.domains.models.I_FM_Rate getFM_Rate() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Rate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Rate.Table_Name)
			.getPO(getFM_Rate_ID(), get_TrxName());	}

	/** Set Financial Rate.
		@param FM_Rate_ID Financial Rate	  */
	public void setFM_Rate_ID (int FM_Rate_ID)
	{
		if (FM_Rate_ID < 1) 
			set_Value (COLUMNNAME_FM_Rate_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Rate_ID, Integer.valueOf(FM_Rate_ID));
	}

	/** Get Financial Rate.
		@return Financial Rate	  */
	public int getFM_Rate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Rate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grace Days.
		@param GraceDays 
		Days after due date to send first dunning letter
	  */
	public void setGraceDays (int GraceDays)
	{
		set_Value (COLUMNNAME_GraceDays, Integer.valueOf(GraceDays));
	}

	/** Get Grace Days.
		@return Days after due date to send first dunning letter
	  */
	public int getGraceDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GraceDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed due date.
		@param IsDueFixed 
		Payment is due on a fixed date
	  */
	public void setIsDueFixed (boolean IsDueFixed)
	{
		set_ValueNoCheck (COLUMNNAME_IsDueFixed, Boolean.valueOf(IsDueFixed));
	}

	/** Get Fixed due date.
		@return Payment is due on a fixed date
	  */
	public boolean isDueFixed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDueFixed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Max Capital Amount.
		@param MaxCapitalAmt Max Capital Amount	  */
	public void setMaxCapitalAmt (BigDecimal MaxCapitalAmt)
	{
		set_Value (COLUMNNAME_MaxCapitalAmt, MaxCapitalAmt);
	}

	/** Get Max Capital Amount.
		@return Max Capital Amount	  */
	public BigDecimal getMaxCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxCapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max Fees Quantity.
		@param MaxFeesQty Max Fees Quantity	  */
	public void setMaxFeesQty (BigDecimal MaxFeesQty)
	{
		set_Value (COLUMNNAME_MaxFeesQty, MaxFeesQty);
	}

	/** Get Max Fees Quantity.
		@return Max Fees Quantity	  */
	public BigDecimal getMaxFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxFeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Capital Amount.
		@param MinCapitalAmt Min Capital Amount	  */
	public void setMinCapitalAmt (BigDecimal MinCapitalAmt)
	{
		set_Value (COLUMNNAME_MinCapitalAmt, MinCapitalAmt);
	}

	/** Get Min Capital Amount.
		@return Min Capital Amount	  */
	public BigDecimal getMinCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinCapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Fees Quantity.
		@param MinFeesQty Min Fees Quantity	  */
	public void setMinFeesQty (BigDecimal MinFeesQty)
	{
		set_Value (COLUMNNAME_MinFeesQty, MinFeesQty);
	}

	/** Get Min Fees Quantity.
		@return Min Fees Quantity	  */
	public BigDecimal getMinFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinFeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.adempiere.core.domains.models.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_M_Product)MTable.get(getCtx(), org.adempiere.core.domains.models.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** PaymentFrequency AD_Reference_ID=54019 */
	public static final int PAYMENTFREQUENCY_AD_Reference_ID=54019;
	/** Daily = D */
	public static final String PAYMENTFREQUENCY_Daily = "D";
	/** Monthly = M */
	public static final String PAYMENTFREQUENCY_Monthly = "M";
	/** Quarterly = Q */
	public static final String PAYMENTFREQUENCY_Quarterly = "Q";
	/** Semi-yearly = S */
	public static final String PAYMENTFREQUENCY_Semi_Yearly = "S";
	/** Yearly = Y */
	public static final String PAYMENTFREQUENCY_Yearly = "Y";
	/** Twice Monthly = T */
	public static final String PAYMENTFREQUENCY_TwiceMonthly = "T";
	/** Single Fee = F */
	public static final String PAYMENTFREQUENCY_SingleFee = "F";
	/** Weekly = W */
	public static final String PAYMENTFREQUENCY_Weekly = "W";
	/** Set Payment Frequency.
		@param PaymentFrequency 
		Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency)
	{

		set_Value (COLUMNNAME_PaymentFrequency, PaymentFrequency);
	}

	/** Get Payment Frequency.
		@return Payment Frequency
	  */
	public String getPaymentFrequency () 
	{
		return (String)get_Value(COLUMNNAME_PaymentFrequency);
	}

	public org.adempiere.core.domains.models.I_FM_Rate getPrepayFeeRate() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Rate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Rate.Table_Name)
			.getPO(getPrepayFeeRate_ID(), get_TrxName());	}

	/** Set Prepayment Fee Rate.
		@param PrepayFeeRate_ID 
		% penalty applied to loan interest when it is prepaid
	  */
	public void setPrepayFeeRate_ID (int PrepayFeeRate_ID)
	{
		if (PrepayFeeRate_ID < 1) 
			set_Value (COLUMNNAME_PrepayFeeRate_ID, null);
		else 
			set_Value (COLUMNNAME_PrepayFeeRate_ID, Integer.valueOf(PrepayFeeRate_ID));
	}

	/** Get Prepayment Fee Rate.
		@return % penalty applied to loan interest when it is prepaid
	  */
	public int getPrepayFeeRate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PrepayFeeRate_ID);
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
}