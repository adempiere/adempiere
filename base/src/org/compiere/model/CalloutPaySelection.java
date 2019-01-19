/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Contributor(s): Teo Sarca
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.X_HR_Concept;
import org.eevolution.model.X_HR_Movement;
import org.eevolution.model.X_HR_Payroll;


/**
 *	Payment Selection Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutPaySelection.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  globalqss - integrate Teo Sarca bug fix 
 *    [ 1623598 ] Payment Selection Line problem when selecting invoice
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document, this process is changed to 
 *			document workflow of Payment Selection
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class CalloutPaySelection extends CalloutEngine
{
	/**
	 *	Payment Selection Line - Payment Amount.
	 *		- called from C_PaySelectionLine.PayAmt
	 *		- update DifferenceAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String payAmt (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		//	get invoice info
		//	FR [ 297 ]
		BigDecimal OpenAmt = (BigDecimal)mTab.getValue("OpenAmt");
		BigDecimal DiscountAmt = (BigDecimal)mTab.getValue("DiscountAmt");
		BigDecimal PayAmt = (BigDecimal)mTab.getValue("PayAmt");
		BigDecimal DifferenceAmt = OpenAmt.subtract(PayAmt).subtract(DiscountAmt);
		//	
		log.fine(" - OpenAmt=" + OpenAmt + " - PayAmt=" + PayAmt
			+ ", Discount=" + DiscountAmt + ", Difference=" + DifferenceAmt);
		//	Set Difference
		mTab.setValue("DifferenceAmt", DifferenceAmt);
		//	
		return "";
	}	//	PaySel_PayAmt
	
	/**
	 *	Payment Selection Line - Discount Amount.
	 *		- called from C_PaySelectionLine.DiscountAmt
	 *		- update PayAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String discountAmt(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		//	get invoice info
		//	FR [ 297 ]
		BigDecimal OpenAmt = (BigDecimal)mTab.getValue("OpenAmt");
		BigDecimal DiscountAmt = (BigDecimal)mTab.getValue("DiscountAmt");
		BigDecimal PayAmt = OpenAmt.subtract(DiscountAmt);
		BigDecimal DifferenceAmt = Env.ZERO;
		//	
		log.fine(" - OpenAmt=" + OpenAmt + " - PayAmt=" + PayAmt
			+ ", Discount=" + DiscountAmt + ", Difference=" + DifferenceAmt);
		//	Set Difference
		
		
		mTab.setValue("PayAmt", PayAmt);
		mTab.setValue("DifferenceAmt", DifferenceAmt);
		//	
		return "";
	}	//	PaySel_PayAmt
	
	/**
	 * Change amount based in currency, conversion type and conversion rate
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String conversion(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		//	Valid Column Name
		String colName = mField.getColumnName ();
		if (!colName.equals ("C_ConversionType_ID")
				&& !colName.equals ("C_Conversion_Rate_ID"))
			return "";
		//	Add support to order
		Integer C_Invoice_ID = (Integer)mTab.getValue("C_Invoice_ID");
		Integer C_Order_ID = (Integer)mTab.getValue("C_Order_ID");
		Integer C_PaySelection_ID = (Integer)mTab.getValue("C_PaySelection_ID");
		//	Valid Invoice
		if ((C_Invoice_ID == null
				|| C_Invoice_ID.intValue() == 0)
				&& 
			(C_Order_ID == null
				|| C_Order_ID.intValue() == 0))
			return "";
		//	Valid Order
		//	Get values
		BigDecimal AmtSource = (BigDecimal)mTab.getValue("AmtSource");
		BigDecimal OpenAmt = (BigDecimal)mTab.getValue("OpenAmt");
		BigDecimal DiscountAmt = (BigDecimal)mTab.getValue("DiscountAmt");
		BigDecimal PayAmt = (BigDecimal)mTab.getValue("PayAmt");
		BigDecimal DifferenceAmt = OpenAmt.subtract(PayAmt).subtract(DiscountAmt);
		//	
		log.fine(" - OpenAmt=" + OpenAmt + " - PayAmt=" + PayAmt
			+ ", Discount=" + DiscountAmt + ", Difference=" + DifferenceAmt);
		//	
		//	
		int AD_Client_ID = 0;
		int AD_Org_ID = 0;
		int C_ConversionType_ID = 0;
		int C_Conversion_Rate_ID = 0;
		Integer ii = (Integer)mTab.getValue ("C_ConversionType_ID");
		//	For Conversion Type
		if (ii != null)
			C_ConversionType_ID = ii.intValue();
		ii = (Integer)mTab.getValue ("C_Conversion_Rate_ID");
		//	For Conversion Rate
		if (ii != null)
			C_Conversion_Rate_ID = ii.intValue();
		//	Get Client info
		ii = (Integer) mTab.getValue("AD_Client_ID");
		if(ii != null)
			AD_Client_ID = ii.intValue();
		//	Get Org
		ii = (Integer) mTab.getValue("AD_Org_ID");
		if(ii != null)
			AD_Org_ID = ii.intValue();
		//	Get Currency Document
		int C_Currency_Document_ID = 0;
		//	For invoice
		if(C_Invoice_ID != null && C_Invoice_ID != 0) {
			MInvoice invoice = new MInvoice(ctx, C_Invoice_ID, null);
			C_Currency_Document_ID = invoice.getC_Currency_ID();
		} else if(C_Order_ID != 0) {
			MOrder order = new MOrder(ctx, C_Order_ID, null);
			C_Currency_Document_ID = order.getC_Currency_ID();
		}
		//	Get Currency Info
		MPaySelection selection = new MPaySelection(ctx, C_PaySelection_ID, null);
		int C_Currency_ID = selection.getC_Currency_ID();
		//	
		MCurrency currency = MCurrency.get (ctx, C_Currency_ID);
		Timestamp ConvDate = selection.getDateDoc();
		// Get Currency Rate
		BigDecimal CurrentCurrencyRate = Env.ONE;
		BigDecimal CurrencyRate = Env.ONE;
		//	Get previous conversion
		if(OpenAmt.doubleValue() != 0) {
			CurrentCurrencyRate = AmtSource.divide(OpenAmt, MathContext.DECIMAL128);
			//	
			DiscountAmt = DiscountAmt.multiply(CurrentCurrencyRate).setScale(
					currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
			PayAmt = PayAmt.multiply(CurrentCurrencyRate).setScale(
					currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
		}
		//	For Conversion Rate
		if(C_Conversion_Rate_ID != 0) {
			CurrencyRate = MConversionRate.getRate(ctx, C_Conversion_Rate_ID);	
		} else if (C_Currency_ID > 0 
				&& C_Currency_Document_ID > 0 
				&& C_Currency_ID != C_Currency_Document_ID) {
			log.fine ("InvCurrency=" + C_Currency_Document_ID + ", PayCurrency="
				+ C_Currency_ID + ", Date=" + ConvDate + ", Type="
				+ C_ConversionType_ID);
			//	Rate
			CurrencyRate = MConversionRate.getRate (C_Currency_Document_ID,
				C_Currency_ID, ConvDate, C_ConversionType_ID, AD_Client_ID,
				AD_Org_ID);
		}
		//	
		if (CurrencyRate == null || CurrencyRate.compareTo (Env.ZERO) == 0) {
			return "NoCurrencyConversion";
		}
		//
		log.fine ("Rate=" + CurrencyRate + ", InvoiceOpenAmt="
			+ AmtSource);
		//	Set Open Amount
		OpenAmt = AmtSource.multiply(CurrencyRate).setScale(
				currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
		// Currency Changed - convert all
		PayAmt = PayAmt.multiply(CurrencyRate).setScale(
				currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
		DiscountAmt = DiscountAmt.multiply (CurrencyRate).setScale(
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
		//	Set difference
		DifferenceAmt = OpenAmt.subtract(PayAmt).subtract(DiscountAmt);
		//	Set values
		mTab.setValue("OpenAmt", OpenAmt);
		mTab.setValue ("PayAmt", PayAmt);
		mTab.setValue ("DiscountAmt", DiscountAmt);
		mTab.setValue("DifferenceAmt", DifferenceAmt);
		//	Default
		return "";
	}

	/**
	 *	Payment Selection Line - Invoice.
	 *		- called from C_PaySelectionLine.C_Invoice_ID
	 *		- update PayAmt & DifferenceAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String invoice (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		//	get value
		int C_Invoice_ID = ((Integer)value).intValue();
		if (C_Invoice_ID == 0)
			return "";
		//	FR [ 297 ]
		//	Get currency instead bank account
		int C_Currency_ID = Env.getContextAsInt(ctx, WindowNo, "C_Currency_ID");
		Timestamp PayDate = Env.getContextAsDate(ctx, WindowNo, "PayDate");
		int C_ConversionType_ID = 0;
		int C_BPartner_ID = 0;
		/* ARHIPAC: TEO: BEGIN: END ------------------------------------------------------------------------------------------ */
		if (PayDate == null)
			PayDate = new Timestamp(System.currentTimeMillis());

		//	FR [ 297 ]
		BigDecimal AmtSource = Env.ZERO;
		BigDecimal OpenAmt = Env.ZERO;
		BigDecimal DiscountAmt = Env.ZERO;
		Boolean IsSOTrx = Boolean.FALSE;
		String sql = "SELECT invoiceOpen(i.C_Invoice_ID, 0) AmtSource, "
				+ "currencyConvert(invoiceOpen(i.C_Invoice_ID, 0), i.C_Currency_ID,"
				+ "?, i.DateInvoiced, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID),"
			+ " paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?), i.IsSOTrx, "
			//	Currency Type
			+ "i.C_ConversionType_ID, "
			+ "i.C_BPartner_ID "
			+ "FROM C_Invoice_v i "
			+ "WHERE i.C_Invoice_ID=?";	//	#1..2
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Currency_ID);
			pstmt.setTimestamp(2, PayDate);
			pstmt.setInt(3, C_Invoice_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				AmtSource = rs.getBigDecimal(1);
				OpenAmt = rs.getBigDecimal(2);
				DiscountAmt = rs.getBigDecimal(3);
				IsSOTrx = new Boolean ("Y".equals(rs.getString(4)));
				C_ConversionType_ID = rs.getInt(5);
				C_BPartner_ID = rs.getInt(6);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine(" - OpenAmt=" + OpenAmt + " (Invoice=" + C_Invoice_ID + ",Currency=" + C_Currency_ID + ")");
		mTab.setValue("AmtSource", AmtSource);
		mTab.setValue("OpenAmt", OpenAmt);
		mTab.setValue("PayAmt", OpenAmt.subtract(DiscountAmt));
		mTab.setValue("DiscountAmt", DiscountAmt);
		mTab.setValue("DifferenceAmt", Env.ZERO);
		mTab.setValue("IsSOTrx", IsSOTrx);
		//	Set Currency Type from invoice
		mTab.setValue("C_ConversionType_ID", C_ConversionType_ID);
		//	Set BP from Document
		mTab.setValue("C_BPartner_ID", C_BPartner_ID);
		return "";
	}	//	PaySel_Invoice
	
	
	/**
	 *	Payment Selection Line - Order.
	 *		- called from C_PaySelectionLine.C_Order_ID
	 *		- update PayAmt & DifferenceAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String order(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		//	get value
		int C_Order_ID = ((Integer)value).intValue();
		if (C_Order_ID == 0)
			return "";
		//	FR [ 297 ]
		//	Get currency instead bank account
		int C_Currency_ID = Env.getContextAsInt(ctx, WindowNo, "C_Currency_ID");
		Timestamp PayDate = Env.getContextAsDate(ctx, WindowNo, "PayDate");
		int C_ConversionType_ID = 0;
		int C_BPartner_ID = 0;
		/* ARHIPAC: TEO: BEGIN: END ------------------------------------------------------------------------------------------ */
		if (PayDate == null)
			PayDate = new Timestamp(System.currentTimeMillis());

		//	FR [ 297 ]
		BigDecimal AmtSource = Env.ZERO;
		BigDecimal OpenAmt = Env.ZERO;
		Boolean IsSOTrx = Boolean.FALSE;
		String sql = "SELECT o.GrandTotal, currencyConvert(o.GrandTotal, o.C_Currency_ID,"
				+ "?, o.DateOrdered, o.C_ConversionType_ID, o.AD_Client_ID, o.AD_Org_ID), o.IsSOTrx, "
			//	Currency Type
			+ "o.C_ConversionType_ID, "
			+ "o.C_BPartner_ID "
			+ "FROM C_Order o "
			+ "WHERE o.C_Order_ID = ?";	//	#1..2
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Currency_ID);
			pstmt.setInt(2, C_Order_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				AmtSource = rs.getBigDecimal(1);
				OpenAmt = rs.getBigDecimal(2);
				IsSOTrx = new Boolean ("Y".equals(rs.getString(3)));
				C_ConversionType_ID = rs.getInt(4);
				C_BPartner_ID = rs.getInt(5);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine(" - OpenAmt=" + OpenAmt + " (Order=" + C_Order_ID + ",Currency=" + C_Currency_ID + ")");
		mTab.setValue("AmtSource", AmtSource);
		mTab.setValue("OpenAmt", OpenAmt);
		mTab.setValue("PayAmt", OpenAmt);
		mTab.setValue("DiscountAmt", Env.ZERO);
		mTab.setValue("DifferenceAmt", Env.ZERO);
		mTab.setValue("IsSOTrx", IsSOTrx);
		//	Set Currency Type from invoice
		mTab.setValue("C_ConversionType_ID", C_ConversionType_ID);		
		//	Set BP from Document
		mTab.setValue("C_BPartner_ID", C_BPartner_ID);
		return "";
	}	//	PaySel_Order
	
	/**
	 * Get Amount from payroll movement
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String payrollMovement(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		//	get value
		int HR_Movement_ID = ((Integer)value).intValue();
		if (HR_Movement_ID == 0)
			return "";
		//	Get amount from movement
		X_HR_Movement movement = new X_HR_Movement(ctx, HR_Movement_ID, null);
		X_HR_Concept concept = new X_HR_Concept(ctx, movement.getHR_Concept_ID(), null);
		//	MHRConcept concept = MHRConcept.get(ctx, movement.getHR_Concept_ID());
		if(!concept.getColumnType().equals(X_HR_Concept.COLUMNTYPE_Amount))
			return "@HR_Concept_ID@ <> @Amount@";
		//	Valid payroll
		X_HR_Payroll payroll = new X_HR_Payroll(ctx, movement.getHR_Payroll_ID(), null);
		//	MHRPayroll payroll = MHRPayroll.get(ctx, movement.getHR_Payroll_ID());
		if(payroll.getC_Charge_ID() == 0)
			return "@C_Charge_ID@ @NotFound@";
		//	Get Amount
		mTab.setValue("PayAmt", movement.getAmount());
		mTab.setValue("C_Charge_ID", payroll.getC_Charge_ID());
		//	Set BP from Document
		mTab.setValue("C_BPartner_ID", movement.getC_BPartner_ID());
		return "";
	}
	
	/**
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return null or error message
	 * FR [ 297 ]
	 */
	public String bankAccount(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		//	Valid callout
		if (isCalloutActive() || value == null)
			return "";
		//	get value
		int C_BankAccount_ID = ((Integer)value).intValue();
		if (C_BankAccount_ID == 0)
			return "";
		//	
		MBankAccount account = MBankAccount.get(ctx, C_BankAccount_ID);
		//	Valid Account
		if(account == null)
			return "";
		//	Set currency
		mTab.setValue("C_Currency_ID", account.getC_Currency_ID());
		//	Ok
		return "";
	}
}	//	CalloutPaySelection
