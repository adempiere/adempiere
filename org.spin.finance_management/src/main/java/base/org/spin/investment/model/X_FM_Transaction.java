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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_Transaction
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_Transaction extends PO implements I_FM_Transaction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_Transaction (Properties ctx, int FM_Transaction_ID, String trxName)
    {
      super (ctx, FM_Transaction_ID, trxName);
      /** if (FM_Transaction_ID == 0)
        {
			setAmount (Env.ZERO);
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
			setFM_Account_ID (0);
			setFM_Batch_ID (0);
			setFM_Transaction_ID (0);
			setFM_TransactionType_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_FM_Transaction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_Transaction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
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

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
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

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	public org.eevolution.model.I_DD_Freight getDD_Freight() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_Freight)MTable.get(getCtx(), org.eevolution.model.I_DD_Freight.Table_Name)
			.getPO(getDD_Freight_ID(), get_TrxName());	}

	/** Set Order Freight.
		@param DD_Freight_ID Order Freight	  */
	public void setDD_Freight_ID (int DD_Freight_ID)
	{
		if (DD_Freight_ID < 1) 
			set_Value (COLUMNNAME_DD_Freight_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Freight_ID, Integer.valueOf(DD_Freight_ID));
	}

	/** Get Order Freight.
		@return Order Freight	  */
	public int getDD_Freight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Freight_ID);
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

	public org.spin.investment.model.I_FM_Account getFM_Account() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Account)MTable.get(getCtx(), org.spin.investment.model.I_FM_Account.Table_Name)
			.getPO(getFM_Account_ID(), get_TrxName());	}

	/** Set Financial Account.
		@param FM_Account_ID Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID)
	{
		if (FM_Account_ID < 1) 
			set_Value (COLUMNNAME_FM_Account_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Account_ID, Integer.valueOf(FM_Account_ID));
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

	public org.spin.investment.model.I_FM_Amortization getFM_Amortization() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Amortization)MTable.get(getCtx(), org.spin.investment.model.I_FM_Amortization.Table_Name)
			.getPO(getFM_Amortization_ID(), get_TrxName());	}

	/** Set Loan Amortization.
		@param FM_Amortization_ID Loan Amortization	  */
	public void setFM_Amortization_ID (int FM_Amortization_ID)
	{
		if (FM_Amortization_ID < 1) 
			set_Value (COLUMNNAME_FM_Amortization_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Amortization_ID, Integer.valueOf(FM_Amortization_ID));
	}

	/** Get Loan Amortization.
		@return Loan Amortization	  */
	public int getFM_Amortization_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Amortization_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_Batch getFM_Batch() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Batch)MTable.get(getCtx(), org.spin.investment.model.I_FM_Batch.Table_Name)
			.getPO(getFM_Batch_ID(), get_TrxName());	}

	/** Set Financial Transaction Batch.
		@param FM_Batch_ID Financial Transaction Batch	  */
	public void setFM_Batch_ID (int FM_Batch_ID)
	{
		if (FM_Batch_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Batch_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Batch_ID, Integer.valueOf(FM_Batch_ID));
	}

	/** Get Financial Transaction Batch.
		@return Financial Transaction Batch	  */
	public int getFM_Batch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Batch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Transaction.
		@param FM_Transaction_ID Financial Transaction	  */
	public void setFM_Transaction_ID (int FM_Transaction_ID)
	{
		if (FM_Transaction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Transaction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Transaction_ID, Integer.valueOf(FM_Transaction_ID));
	}

	/** Get Financial Transaction.
		@return Financial Transaction	  */
	public int getFM_Transaction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Transaction_ID);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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