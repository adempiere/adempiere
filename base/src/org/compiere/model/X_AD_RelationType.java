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

/** Generated Model for AD_RelationType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_RelationType extends PO implements I_AD_RelationType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_RelationType (Properties ctx, int AD_RelationType_ID, String trxName)
    {
      super (ctx, AD_RelationType_ID, trxName);
      /** if (AD_RelationType_ID == 0)
        {
			setAD_RelationType_ID (0);
			setIsDirected (false);
// N
			setName (null);
			setType (null);
// I
        } */
    }

    /** Load Constructor */
    public X_AD_RelationType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_RelationType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Reference getAD_Reference_Source() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_Source_ID(), get_TrxName());	}

	/** Set Source Reference.
		@param AD_Reference_Source_ID Source Reference	  */
	public void setAD_Reference_Source_ID (int AD_Reference_Source_ID)
	{
		if (AD_Reference_Source_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_Source_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_Source_ID, Integer.valueOf(AD_Reference_Source_ID));
	}

	/** Get Source Reference.
		@return Source Reference	  */
	public int getAD_Reference_Source_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_Source_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Reference getAD_Reference_Target() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_Target_ID(), get_TrxName());	}

	/** Set Target Reference.
		@param AD_Reference_Target_ID Target Reference	  */
	public void setAD_Reference_Target_ID (int AD_Reference_Target_ID)
	{
		if (AD_Reference_Target_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_Target_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_Target_ID, Integer.valueOf(AD_Reference_Target_ID));
	}

	/** Get Target Reference.
		@return Target Reference	  */
	public int getAD_Reference_Target_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_Target_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Relation Type.
		@param AD_RelationType_ID Relation Type	  */
	public void setAD_RelationType_ID (int AD_RelationType_ID)
	{
		if (AD_RelationType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_RelationType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_RelationType_ID, Integer.valueOf(AD_RelationType_ID));
	}

	/** Get Relation Type.
		@return Relation Type	  */
	public int getAD_RelationType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_RelationType_ID);
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

	/** Set Directed.
		@param IsDirected 
		Tells whether one "sees" the other end of the relation from each end or just from the source
	  */
	public void setIsDirected (boolean IsDirected)
	{
		set_Value (COLUMNNAME_IsDirected, Boolean.valueOf(IsDirected));
	}

	/** Get Directed.
		@return Tells whether one "sees" the other end of the relation from each end or just from the source
	  */
	public boolean isDirected () 
	{
		Object oo = get_Value(COLUMNNAME_IsDirected);
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

	/** Role_Source AD_Reference_ID=53331 */
	public static final int ROLE_SOURCE_AD_Reference_ID=53331;
	/** Order = Order */
	public static final String ROLE_SOURCE_Order = "Order";
	/** Invoice = Invoice */
	public static final String ROLE_SOURCE_Invoice = "Invoice";
	/** Invoice Open = InvoiceOpen */
	public static final String ROLE_SOURCE_InvoiceOpen = "InvoiceOpen";
	/** Unallocated Payments = UnallocatedPayments */
	public static final String ROLE_SOURCE_UnallocatedPayments = "UnallocatedPayments";
	/** Sales Invoice Open = SalesInvoiceOpen */
	public static final String ROLE_SOURCE_SalesInvoiceOpen = "SalesInvoiceOpen";
	/** Vendor Invoice Open = VendorInvoiceOpen */
	public static final String ROLE_SOURCE_VendorInvoiceOpen = "VendorInvoiceOpen";
	/** Sales Invoice Allocated = SalesInvoiceAllocated */
	public static final String ROLE_SOURCE_SalesInvoiceAllocated = "SalesInvoiceAllocated";
	/** Vendor Invoice Allocated = VendorInvoiceAllocated */
	public static final String ROLE_SOURCE_VendorInvoiceAllocated = "VendorInvoiceAllocated";
	/** Payment Allocated = PaymentAllocated */
	public static final String ROLE_SOURCE_PaymentAllocated = "PaymentAllocated";
	/** Invoice Matched = InvoiceMatched */
	public static final String ROLE_SOURCE_InvoiceMatched = "InvoiceMatched";
	/** Order Matched = OrderMatched */
	public static final String ROLE_SOURCE_OrderMatched = "OrderMatched";
	/** Receipt Matched = ReceiptMatched */
	public static final String ROLE_SOURCE_ReceiptMatched = "ReceiptMatched";
	/** View Matched Receipt Line = ViewMatchedReceiptLine */
	public static final String ROLE_SOURCE_ViewMatchedReceiptLine = "ViewMatchedReceiptLine";
	/** View Matched Order Line = ViewMatchedOrdertLine */
	public static final String ROLE_SOURCE_ViewMatchedOrderLine = "ViewMatchedOrdertLine";
	/** Deposit from Cash = DepositFromCash */
	public static final String ROLE_SOURCE_DepositFromCash = "DepositFromCash";
	/** Cash from Deposit = CashFromDeposit */
	public static final String ROLE_SOURCE_CashFromDeposit = "CashFromDeposit";
	/** Payments from Loan = PaymentsFromLoan */
	public static final String ROLE_SOURCE_PaymentsFromLoan = "PaymentsFromLoan";
	/** Loan of Payment = LoanOfPayment */
	public static final String ROLE_SOURCE_LoanOfPayment = "LoanOfPayment";
	/** Payment Selection from Loan = PaySelectionFromLoan */
	public static final String ROLE_SOURCE_PaymentSelectionFromLoan = "PaySelectionFromLoan";
	/** Loan of Payment Selection = LoanOfPaySelection */
	public static final String ROLE_SOURCE_LoanOfPaymentSelection = "LoanOfPaySelection";
	/** Invoice Customer From Loan = InvoiceCustomerFromLoan */
	public static final String ROLE_SOURCE_InvoiceCustomerFromLoan = "InvoiceCustomerFromLoan";
	/** Loan of Invoice Customer = LoanOfInvoiceCustomer */
	public static final String ROLE_SOURCE_LoanOfInvoiceCustomer = "LoanOfInvoiceCustomer";
	/** Batch Transaction from Agreement = BatchFromAgreement */
	public static final String ROLE_SOURCE_BatchTransactionFromAgreement = "BatchFromAgreement";
	/** Agreement from Batch Transaction = AgreementFromBatch */
	public static final String ROLE_SOURCE_AgreementFromBatchTransaction = "AgreementFromBatch";
	/** Batch Transaction from Invoice (Customer) = BatchFromInvoiceCustomer */
	public static final String ROLE_SOURCE_BatchTransactionFromInvoiceCustomer = "BatchFromInvoiceCustomer";
	/** Invoice (Customer) from Batch Transaction = InvoiceCustomerFromBatch */
	public static final String ROLE_SOURCE_InvoiceCustomerFromBatchTransaction = "InvoiceCustomerFromBatch";
	/** Identified Payment = IdentifiedPayment */
	public static final String ROLE_SOURCE_IdentifiedPayment = "IdentifiedPayment";
	/** Unidentified Payment = UnidentifiedPayment */
	public static final String ROLE_SOURCE_UnidentifiedPayment = "UnidentifiedPayment";
	/** Set Source Role.
		@param Role_Source 
		If set, this role will be used as label for the zoom destination instead of the destinations's window name
	  */
	public void setRole_Source (String Role_Source)
	{

		set_Value (COLUMNNAME_Role_Source, Role_Source);
	}

	/** Get Source Role.
		@return If set, this role will be used as label for the zoom destination instead of the destinations's window name
	  */
	public String getRole_Source () 
	{
		return (String)get_Value(COLUMNNAME_Role_Source);
	}

	/** Role_Target AD_Reference_ID=53331 */
	public static final int ROLE_TARGET_AD_Reference_ID=53331;
	/** Order = Order */
	public static final String ROLE_TARGET_Order = "Order";
	/** Invoice = Invoice */
	public static final String ROLE_TARGET_Invoice = "Invoice";
	/** Invoice Open = InvoiceOpen */
	public static final String ROLE_TARGET_InvoiceOpen = "InvoiceOpen";
	/** Unallocated Payments = UnallocatedPayments */
	public static final String ROLE_TARGET_UnallocatedPayments = "UnallocatedPayments";
	/** Sales Invoice Open = SalesInvoiceOpen */
	public static final String ROLE_TARGET_SalesInvoiceOpen = "SalesInvoiceOpen";
	/** Vendor Invoice Open = VendorInvoiceOpen */
	public static final String ROLE_TARGET_VendorInvoiceOpen = "VendorInvoiceOpen";
	/** Sales Invoice Allocated = SalesInvoiceAllocated */
	public static final String ROLE_TARGET_SalesInvoiceAllocated = "SalesInvoiceAllocated";
	/** Vendor Invoice Allocated = VendorInvoiceAllocated */
	public static final String ROLE_TARGET_VendorInvoiceAllocated = "VendorInvoiceAllocated";
	/** Payment Allocated = PaymentAllocated */
	public static final String ROLE_TARGET_PaymentAllocated = "PaymentAllocated";
	/** Invoice Matched = InvoiceMatched */
	public static final String ROLE_TARGET_InvoiceMatched = "InvoiceMatched";
	/** Order Matched = OrderMatched */
	public static final String ROLE_TARGET_OrderMatched = "OrderMatched";
	/** Receipt Matched = ReceiptMatched */
	public static final String ROLE_TARGET_ReceiptMatched = "ReceiptMatched";
	/** View Matched Receipt Line = ViewMatchedReceiptLine */
	public static final String ROLE_TARGET_ViewMatchedReceiptLine = "ViewMatchedReceiptLine";
	/** View Matched Order Line = ViewMatchedOrdertLine */
	public static final String ROLE_TARGET_ViewMatchedOrderLine = "ViewMatchedOrdertLine";
	/** Deposit from Cash = DepositFromCash */
	public static final String ROLE_TARGET_DepositFromCash = "DepositFromCash";
	/** Cash from Deposit = CashFromDeposit */
	public static final String ROLE_TARGET_CashFromDeposit = "CashFromDeposit";
	/** Payments from Loan = PaymentsFromLoan */
	public static final String ROLE_TARGET_PaymentsFromLoan = "PaymentsFromLoan";
	/** Loan of Payment = LoanOfPayment */
	public static final String ROLE_TARGET_LoanOfPayment = "LoanOfPayment";
	/** Payment Selection from Loan = PaySelectionFromLoan */
	public static final String ROLE_TARGET_PaymentSelectionFromLoan = "PaySelectionFromLoan";
	/** Loan of Payment Selection = LoanOfPaySelection */
	public static final String ROLE_TARGET_LoanOfPaymentSelection = "LoanOfPaySelection";
	/** Invoice Customer From Loan = InvoiceCustomerFromLoan */
	public static final String ROLE_TARGET_InvoiceCustomerFromLoan = "InvoiceCustomerFromLoan";
	/** Loan of Invoice Customer = LoanOfInvoiceCustomer */
	public static final String ROLE_TARGET_LoanOfInvoiceCustomer = "LoanOfInvoiceCustomer";
	/** Batch Transaction from Agreement = BatchFromAgreement */
	public static final String ROLE_TARGET_BatchTransactionFromAgreement = "BatchFromAgreement";
	/** Agreement from Batch Transaction = AgreementFromBatch */
	public static final String ROLE_TARGET_AgreementFromBatchTransaction = "AgreementFromBatch";
	/** Batch Transaction from Invoice (Customer) = BatchFromInvoiceCustomer */
	public static final String ROLE_TARGET_BatchTransactionFromInvoiceCustomer = "BatchFromInvoiceCustomer";
	/** Invoice (Customer) from Batch Transaction = InvoiceCustomerFromBatch */
	public static final String ROLE_TARGET_InvoiceCustomerFromBatchTransaction = "InvoiceCustomerFromBatch";
	/** Identified Payment = IdentifiedPayment */
	public static final String ROLE_TARGET_IdentifiedPayment = "IdentifiedPayment";
	/** Unidentified Payment = UnidentifiedPayment */
	public static final String ROLE_TARGET_UnidentifiedPayment = "UnidentifiedPayment";
	/** Set Target Role.
		@param Role_Target 
		If set, this role will be used as label for the zoom destination instead of the destinations's window name
	  */
	public void setRole_Target (String Role_Target)
	{

		set_Value (COLUMNNAME_Role_Target, Role_Target);
	}

	/** Get Target Role.
		@return If set, this role will be used as label for the zoom destination instead of the destinations's window name
	  */
	public String getRole_Target () 
	{
		return (String)get_Value(COLUMNNAME_Role_Target);
	}

	/** Type AD_Reference_ID=53332 */
	public static final int TYPE_AD_Reference_ID=53332;
	/** Implicit = I */
	public static final String TYPE_Implicit = "I";
	/** Explicit = E */
	public static final String TYPE_Explicit = "E";
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