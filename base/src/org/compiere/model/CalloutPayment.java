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
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Payment Callouts. org.compiere.model.CalloutPayment.*
 * @author Jorg Janke
 * @version $Id: CalloutPayment.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *         <li>BF [ 1803316 ] CalloutPayment: use C_Order.Bill_BPartner_ID
 * @author j2garcia - GlobalQSS
 *         <li>BF [ 2021745 ] Cannot assign project to payment with charge
 */
public class CalloutPayment extends CalloutEngine
{

	/**
	 * Payment_Invoice. when Invoice selected - set C_Currency_ID -
	 * C_BPartner_ID - DiscountAmt = C_Invoice_Discount (ID, DateTrx) - PayAmt =
	 * invoiceOpen (ID) - Discount - WriteOffAmt = 0
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String invoice(Properties ctx, int WindowNo, GridTab mTab,
		GridField mField, Object value)
	{
		Integer C_Invoice_ID = (Integer)value;
		if (isCalloutActive () // assuming it is resetting value
			|| C_Invoice_ID == null || C_Invoice_ID.intValue () == 0)
			return "";
		mTab.setValue ("C_Order_ID", null);
		mTab.setValue ("C_Charge_ID", null);
		mTab.setValue ("IsPrepayment", Boolean.FALSE);
		//
		mTab.setValue ("DiscountAmt", Env.ZERO);
		mTab.setValue ("WriteOffAmt", Env.ZERO);
		// mTab.setValue ("IsOverUnderPayment", Boolean.FALSE);
		mTab.setValue ("OverUnderAmt", Env.ZERO);
		int C_InvoicePaySchedule_ID = 0;
		if (Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_Invoice_ID") == C_Invoice_ID.intValue ()
			&& Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID") != 0)
		{
			C_InvoicePaySchedule_ID = Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID");
		}
		// Payment Date
		Timestamp ts = (Timestamp)mTab.getValue ("DateTrx");
		if (ts == null)
			ts = new Timestamp (System.currentTimeMillis ());
		//
		String sql = "SELECT C_BPartner_ID,C_Currency_ID," // 1..2
			+ " invoiceOpen(C_Invoice_ID, ?)," // 3 #1
			+ " invoiceDiscount(C_Invoice_ID,?,?), IsSOTrx " // 4..5 #2/3
			+ "FROM C_Invoice WHERE C_Invoice_ID=?"; // #4
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, C_InvoicePaySchedule_ID);
			pstmt.setTimestamp (2, ts);
			pstmt.setInt (3, C_InvoicePaySchedule_ID);
			pstmt.setInt (4, C_Invoice_ID.intValue ());
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				mTab.setValue ("C_BPartner_ID", new Integer (rs.getInt (1)));
				int C_Currency_ID = rs.getInt (2); // Set Invoice Currency
				mTab.setValue ("C_Currency_ID", new Integer (C_Currency_ID));
				//
				BigDecimal InvoiceOpen = rs.getBigDecimal (3); // Set Invoice
				// OPen Amount
				if (InvoiceOpen == null)
					InvoiceOpen = Env.ZERO;
				BigDecimal DiscountAmt = rs.getBigDecimal (4); // Set Discount
				// Amt
				if (DiscountAmt == null)
					DiscountAmt = Env.ZERO;
				mTab.setValue ("PayAmt", InvoiceOpen.subtract (DiscountAmt));
				mTab.setValue ("DiscountAmt", DiscountAmt);
				// reset as dependent fields get reset
				Env.setContext (ctx, WindowNo, "C_Invoice_ID", C_Invoice_ID
					.toString ());
				mTab.setValue ("C_Invoice_ID", C_Invoice_ID);
			}
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, sql, e);
			return e.getLocalizedMessage ();
		}
		finally
		{
			DB.close (rs, pstmt);
		}
		return docType (ctx, WindowNo, mTab, mField, value);
	} // invoice

	/**
	 * Payment_Order. when Waiting Payment Order selected - set C_Currency_ID -
	 * C_BPartner_ID - DiscountAmt = C_Invoice_Discount (ID, DateTrx) - PayAmt =
	 * invoiceOpen (ID) - Discount - WriteOffAmt = 0
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String order(Properties ctx, int WindowNo, GridTab mTab,
		GridField mField, Object value)
	{
		Integer C_Order_ID = (Integer)value;
		if (isCalloutActive () // assuming it is resetting value
			|| C_Order_ID == null || C_Order_ID.intValue () == 0)
			return "";
		mTab.setValue ("C_Invoice_ID", null);
		mTab.setValue ("C_Charge_ID", null);
		mTab.setValue ("IsPrepayment", Boolean.TRUE);
		//
		mTab.setValue ("DiscountAmt", Env.ZERO);
		mTab.setValue ("WriteOffAmt", Env.ZERO);
		mTab.setValue ("IsOverUnderPayment", Boolean.FALSE);
		mTab.setValue ("OverUnderAmt", Env.ZERO);
		// Payment Date
		Timestamp ts = (Timestamp)mTab.getValue ("DateTrx");
		if (ts == null)
			ts = new Timestamp (System.currentTimeMillis ());
		//
		String sql = "SELECT COALESCE(Bill_BPartner_ID, C_BPartner_ID) as C_BPartner_ID "
			+ ", C_Currency_ID "
			+ ", GrandTotal "
			+ "FROM C_Order WHERE C_Order_ID=?"; // #1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, C_Order_ID.intValue ());
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				mTab.setValue ("C_BPartner_ID", new Integer (rs.getInt (1)));
				int C_Currency_ID = rs.getInt (2); // Set Order Currency
				mTab.setValue ("C_Currency_ID", new Integer (C_Currency_ID));
				//
				BigDecimal GrandTotal = rs.getBigDecimal (3); // Set Pay
				// Amount
				if (GrandTotal == null)
					GrandTotal = Env.ZERO;
				mTab.setValue ("PayAmt", GrandTotal);
			}
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, sql, e);
			return e.getLocalizedMessage ();
		}
		finally
		{
			DB.close (rs, pstmt);
		}
		return docType (ctx, WindowNo, mTab, mField, value);
	} // order

	// 2008/07/18 Globalqss [ 2021745 ]
	// Deleted project method

	/**
	 * Payment_Charge. - reset - C_BPartner_ID, Invoice, Order, Project,
	 * Discount, WriteOff
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String charge(Properties ctx, int WindowNo, GridTab mTab,
		GridField mField, Object value)
	{
		Integer C_Charge_ID = (Integer)value;
		if (isCalloutActive () // assuming it is resetting value
			|| C_Charge_ID == null || C_Charge_ID.intValue () == 0)
			return "";
		mTab.setValue ("C_Invoice_ID", null);
		mTab.setValue ("C_Order_ID", null);
		// 2008/07/18 Globalqss [ 2021745 ]
		// mTab.setValue ("C_Project_ID", null);
		mTab.setValue ("IsPrepayment", Boolean.FALSE);
		//
		mTab.setValue ("DiscountAmt", Env.ZERO);
		mTab.setValue ("WriteOffAmt", Env.ZERO);
		mTab.setValue ("IsOverUnderPayment", Boolean.FALSE);
		mTab.setValue ("OverUnderAmt", Env.ZERO);
		return "";
	} // charge

	/**
	 * Payment_Document Type. Verify that Document Type (AP/AR) and Invoice
	 * (SO/PO) are in sync
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @return null or error message
	 */
	public String docType(Properties ctx, int WindowNo, GridTab mTab,
		GridField mField, Object value)
	{
		int C_Invoice_ID = Env.getContextAsInt (ctx, WindowNo, "C_Invoice_ID");
		int C_Order_ID = Env.getContextAsInt (ctx, WindowNo, "C_Order_ID");
		int C_DocType_ID = Env.getContextAsInt (ctx, WindowNo, "C_DocType_ID");
		log.fine ("Payment_DocType - C_Invoice_ID=" + C_Invoice_ID
			+ ", C_DocType_ID=" + C_DocType_ID);
		MDocType dt = null;
		if (C_DocType_ID != 0)
		{
			dt = MDocType.get (ctx, C_DocType_ID);
			Env
				.setContext (ctx, WindowNo, "IsSOTrx", dt.isSOTrx () ? "Y"
					: "N");
		}
		// Invoice
		if (C_Invoice_ID != 0)
		{
			MInvoice inv = new MInvoice (ctx, C_Invoice_ID, null);
			if (dt != null)
			{
				if (inv.isSOTrx () != dt.isSOTrx ())
					return "PaymentDocTypeInvoiceInconsistent";
			}
		}
		// globalqss - Allow prepayment to Purchase Orders
		// Order Waiting Payment (can only be SO)
		// if (C_Order_ID != 0 && dt != null && !dt.isSOTrx())
		// return "PaymentDocTypeInvoiceInconsistent";
		// Order
		if (C_Order_ID != 0)
		{
			MOrder ord = new MOrder (ctx, C_Order_ID, null);
			if (dt != null)
			{
				if (ord.isSOTrx () != dt.isSOTrx ())
					return "PaymentDocTypeInvoiceInconsistent";
			}
		}
		return "";
	} // docType

	/**
	 * Payment_Amounts. Change of: - IsOverUnderPayment -> set OverUnderAmt to 0 -
	 * C_Currency_ID, C_ConvesionRate_ID -> convert all - PayAmt, DiscountAmt,
	 * WriteOffAmt, OverUnderAmt -> PayAmt make sure that add up to
	 * InvoiceOpenAmt
	 * @param ctx context
	 * @param WindowNo current Window No
	 * @param mTab Grid Tab
	 * @param mField Grid Field
	 * @param value New Value
	 * @param oldValue Old Value
	 * @return null or error message
	 */
	public String amounts(Properties ctx, int WindowNo, GridTab mTab,
		GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive ()) // assuming it is resetting value
			return "";
		int C_Invoice_ID = Env.getContextAsInt (ctx, WindowNo, "C_Invoice_ID");
		// New Payment
		if (Env.getContextAsInt (ctx, WindowNo, "C_Payment_ID") == 0
			&& Env.getContextAsInt (ctx, WindowNo, "C_BPartner_ID") == 0
			&& C_Invoice_ID == 0)
			return "";
		// Changed Column
		String colName = mField.getColumnName ();
		if (colName.equals ("IsOverUnderPayment") // Set Over/Under Amt to
			// Zero
			|| !"Y".equals (Env
				.getContext (ctx, WindowNo, "IsOverUnderPayment")))
			mTab.setValue ("OverUnderAmt", Env.ZERO);
		int C_InvoicePaySchedule_ID = 0;
		if (Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_Invoice_ID") == C_Invoice_ID
			&& Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID") != 0)
		{
			C_InvoicePaySchedule_ID = Env.getContextAsInt (ctx, WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID");
		}
		// Get Open Amount & Invoice Currency
		BigDecimal InvoiceOpenAmt = Env.ZERO;
		int C_Currency_Invoice_ID = 0;
		if (C_Invoice_ID != 0)
		{
			Timestamp ts = (Timestamp)mTab.getValue ("DateTrx");
			if (ts == null)
				ts = new Timestamp (System.currentTimeMillis ());
			String sql = "SELECT C_BPartner_ID,C_Currency_ID," // 1..2
				+ " invoiceOpen(C_Invoice_ID,?)," // 3 #1
				+ " invoiceDiscount(C_Invoice_ID,?,?), IsSOTrx " // 4..5 #2/3
				+ "FROM C_Invoice WHERE C_Invoice_ID=?"; // #4
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				pstmt.setInt (1, C_InvoicePaySchedule_ID);
				pstmt.setTimestamp (2, ts);
				pstmt.setInt (3, C_InvoicePaySchedule_ID);
				pstmt.setInt (4, C_Invoice_ID);
				rs = pstmt.executeQuery ();
				if (rs.next ())
				{
					C_Currency_Invoice_ID = rs.getInt (2);
					InvoiceOpenAmt = rs.getBigDecimal (3); // Set Invoice Open
					// Amount
					if (InvoiceOpenAmt == null)
						InvoiceOpenAmt = Env.ZERO;
				}
			}
			catch (SQLException e)
			{
				log.log (Level.SEVERE, sql, e);
				return e.getLocalizedMessage ();
			}
			finally
			{
				DB.close (rs, pstmt);
				rs = null;
				pstmt = null;
			}
		} // get Invoice Info
		log.fine ("Open=" + InvoiceOpenAmt + ", C_Invoice_ID=" + C_Invoice_ID
			+ ", C_Currency_ID=" + C_Currency_Invoice_ID);
		// Get Info from Tab
		BigDecimal PayAmt = (BigDecimal)mTab.getValue ("PayAmt");
		BigDecimal DiscountAmt = (BigDecimal)mTab.getValue ("DiscountAmt");
		BigDecimal WriteOffAmt = (BigDecimal)mTab.getValue ("WriteOffAmt");
		BigDecimal OverUnderAmt = (BigDecimal)mTab.getValue ("OverUnderAmt");
		log.fine ("Pay=" + PayAmt + ", Discount=" + DiscountAmt + ", WriteOff="
			+ WriteOffAmt + ", OverUnderAmt=" + OverUnderAmt);
		// Get Currency Info
		int C_Currency_ID = ((Integer)mTab.getValue ("C_Currency_ID"))
			.intValue ();
		MCurrency currency = MCurrency.get (ctx, C_Currency_ID);
		Timestamp ConvDate = (Timestamp)mTab.getValue ("DateTrx");
		int C_ConversionType_ID = 0;
		Integer ii = (Integer)mTab.getValue ("C_ConversionType_ID");
		if (ii != null)
			C_ConversionType_ID = ii.intValue ();
		int AD_Client_ID = Env.getContextAsInt (ctx, WindowNo, "AD_Client_ID");
		int AD_Org_ID = Env.getContextAsInt (ctx, WindowNo, "AD_Org_ID");
		// Get Currency Rate
		BigDecimal CurrencyRate = Env.ONE;
		if ((C_Currency_ID > 0 && C_Currency_Invoice_ID > 0 && C_Currency_ID != C_Currency_Invoice_ID)
			|| colName.equals ("C_Currency_ID")
			|| colName.equals ("C_ConversionType_ID"))
		{
			log.fine ("InvCurrency=" + C_Currency_Invoice_ID + ", PayCurrency="
				+ C_Currency_ID + ", Date=" + ConvDate + ", Type="
				+ C_ConversionType_ID);
			CurrencyRate = MConversionRate.getRate (C_Currency_Invoice_ID,
				C_Currency_ID, ConvDate, C_ConversionType_ID, AD_Client_ID,
				AD_Org_ID);
			if (CurrencyRate == null || CurrencyRate.compareTo (Env.ZERO) == 0)
			{
				// mTab.setValue("C_Currency_ID", new
				// Integer(C_Currency_Invoice_ID)); // does not work
				if (C_Currency_Invoice_ID == 0)
					return ""; // no error message when no invoice is selected
				return "NoCurrencyConversion";
			}
			//
			InvoiceOpenAmt = InvoiceOpenAmt.multiply (CurrencyRate).setScale (
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
			log.fine ("Rate=" + CurrencyRate + ", InvoiceOpenAmt="
				+ InvoiceOpenAmt);
		}
		// Currency Changed - convert all
		if (colName.equals ("C_Currency_ID")
			|| colName.equals ("C_ConversionType_ID"))
		{
			PayAmt = PayAmt.multiply (CurrencyRate).setScale (
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
			mTab.setValue ("PayAmt", PayAmt);
			DiscountAmt = DiscountAmt.multiply (CurrencyRate).setScale (
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
			mTab.setValue ("DiscountAmt", DiscountAmt);
			WriteOffAmt = WriteOffAmt.multiply (CurrencyRate).setScale (
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
			mTab.setValue ("WriteOffAmt", WriteOffAmt);
			OverUnderAmt = OverUnderAmt.multiply (CurrencyRate).setScale (
				currency.getStdPrecision (), BigDecimal.ROUND_HALF_UP);
			mTab.setValue ("OverUnderAmt", OverUnderAmt);
		}
		// No Invoice - Set Discount, Witeoff, Under/Over to 0
		else if (C_Invoice_ID == 0)
		{
			if (Env.ZERO.compareTo (DiscountAmt) != 0)
				mTab.setValue ("DiscountAmt", Env.ZERO);
			if (Env.ZERO.compareTo (WriteOffAmt) != 0)
				mTab.setValue ("WriteOffAmt", Env.ZERO);
			if (Env.ZERO.compareTo (OverUnderAmt) != 0)
				mTab.setValue ("OverUnderAmt", Env.ZERO);
		}
		// PayAmt - calculate write off
		// Added Lines By Goodwill (02-03-2006)
		// Reason: we must make the callout is called just when docstatus is
		// draft
		// Old Code : else if (colName.equals("PayAmt"))
		// New Code :
		else if (colName.equals ("PayAmt")
			&& mTab.get_ValueAsString ("DocStatus").equals ("DR")
			&& "Y"
				.equals (Env.getContext (ctx, WindowNo, "IsOverUnderPayment")))
		{
			OverUnderAmt = InvoiceOpenAmt.subtract (PayAmt).subtract (
				DiscountAmt).subtract (WriteOffAmt);
			mTab.setValue ("OverUnderAmt", OverUnderAmt);
		}
		else if (colName.equals ("PayAmt")
			&& mTab.get_ValueAsString ("DocStatus").equals ("DR"))
		{
			WriteOffAmt = InvoiceOpenAmt.subtract (PayAmt).subtract (
				DiscountAmt).subtract (OverUnderAmt);
			mTab.setValue ("WriteOffAmt", WriteOffAmt);
		}
		else if (colName.equals ("IsOverUnderPayment")
			&& mTab.get_ValueAsString ("DocStatus").equals ("DR"))
		{
			boolean overUnderPaymentActive = "Y".equals (Env.getContext (ctx,
				WindowNo, "IsOverUnderPayment"));
			if (overUnderPaymentActive)
			{
				OverUnderAmt = InvoiceOpenAmt.subtract (PayAmt).subtract (
					DiscountAmt);
				mTab.setValue ("WriteOffAmt", Env.ZERO);
				mTab.setValue ("OverUnderAmt", OverUnderAmt);
			}else{
				WriteOffAmt = InvoiceOpenAmt.subtract (PayAmt).subtract (
					DiscountAmt);
				mTab.setValue ("WriteOffAmt", WriteOffAmt);
				mTab.setValue ("OverUnderAmt", Env.ZERO);
			}
		}
		// Added Lines By Goodwill (02-03-2006)
		// Reason: we must make the callout is called just when docstatus is
		// draft
		// Old Code : else // calculate PayAmt
		// New Code :
		else if (mTab.get_ValueAsString ("DocStatus").equals ("DR")) // calculate
		// PayAmt
		// End By Goodwill
		{
			PayAmt = InvoiceOpenAmt.subtract (DiscountAmt).subtract (
				WriteOffAmt).subtract (OverUnderAmt);
			mTab.setValue ("PayAmt", PayAmt);
		}
		return "";
	} // amounts
} // CalloutPayment
