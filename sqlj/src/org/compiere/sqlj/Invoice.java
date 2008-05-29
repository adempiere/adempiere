/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.sqlj;

import java.math.*;
import java.sql.*;


/**
 *	SQLJ Invoice related Functions
 *	
 *  @author Jorg Janke
 *  @version $Id: Invoice.java,v 1.3 2006/07/30 00:59:07 jjanke Exp $
 */
public class Invoice
{
	/**
	 * 	Open Invoice Amount.
	 * 	- incoiceOpen
	 *	@param p_C_Invoice_ID invoice
	 *	@param p_C_InvoicePaySchedule_ID payment schedule
	 *	@return open amount
	 *	@throws SQLException
	 */
	public static BigDecimal open (int p_C_Invoice_ID, int p_C_InvoicePaySchedule_ID)
		throws SQLException
	{
		//	Invoice info
		int C_Currency_ID = 0;
		int C_ConversionType_ID = 0;
		BigDecimal GrandTotal = null;
		BigDecimal MultiplierAP = null;
		BigDecimal MultiplierCM = null;
		//
		String sql = "SELECT MAX(C_Currency_ID),MAX(C_ConversionType_ID),"
			+ " SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier) "
			+ "FROM	C_Invoice_v "	//	corrected for CM / Split Payment
			+ "WHERE C_Invoice_ID=?";
		if (p_C_InvoicePaySchedule_ID != 0)
			sql += " AND C_InvoicePaySchedule_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Invoice_ID);
		if (p_C_InvoicePaySchedule_ID != 0)
			pstmt.setInt(2, p_C_InvoicePaySchedule_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			C_Currency_ID = rs.getInt(1);
			C_ConversionType_ID = rs.getInt(2);
			GrandTotal = rs.getBigDecimal(3);
			MultiplierAP = rs.getBigDecimal(4);
			MultiplierCM = rs.getBigDecimal(5);
		}
		rs.close();
		pstmt.close();
		//	No Invoice
		if (GrandTotal == null)
                    return null;                    
                

		BigDecimal paidAmt = allocatedAmt(p_C_Invoice_ID, C_Currency_ID, 
			C_ConversionType_ID, MultiplierAP);
        BigDecimal TotalOpenAmt = GrandTotal.subtract(paidAmt);
        
		/**
		 	GrandTotal	Paid	TotalOpen	Remaining	Due		x
		 	100			0		100			=0
		 1a						=50-0					50		x
		 1b									=0-50 =0	50
		 2a									=0-50 =0	50		
		 2b						=50-0					50 		x
		 --
		 	100			10		100			=10
		 1a						=50-10					50		x
		 1b									=10-50 =0	50
		 2a									=10-50 =0	50		
		 2b						=50-0					50 		x
		 --
		 	100			60		100			=60
		 1a						=50-60 =0					50		x
		 1b									=60-50 		50
		 2a									=60-50 =10	50		
		 2b						=50-10					50 		x
		 --
		**/
		
		//	Do we have a Payment Schedule ?
		if (p_C_InvoicePaySchedule_ID > 0)	//	if not valid = lists invoice amount
		{
			TotalOpenAmt = GrandTotal;
			BigDecimal remainingAmt = paidAmt;
			sql = "SELECT C_InvoicePaySchedule_ID, DueAmt "
				+ "FROM C_InvoicePaySchedule "
				+ "WHERE C_Invoice_ID=?"
				+ " AND IsValid='Y' "
				+ "ORDER BY DueDate";
			pstmt = Adempiere.prepareStatement(sql);
			pstmt.setInt(1, p_C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int C_InvoicePaySchedule_ID = rs.getInt(1); 
				BigDecimal DueAmt = rs.getBigDecimal(2);
				//
				if (C_InvoicePaySchedule_ID == p_C_InvoicePaySchedule_ID)
				{
					if (DueAmt.signum() > 0)	//	positive
					{
						if (DueAmt.compareTo(remainingAmt) < 0)		// paid more 
							TotalOpenAmt = Adempiere.ZERO;
						else
							TotalOpenAmt = DueAmt.multiply(MultiplierCM)
								.subtract(remainingAmt);
					}
					else
					{
						if (DueAmt.compareTo(remainingAmt) > 0)		// paid more 
							TotalOpenAmt = Adempiere.ZERO;
						else
							TotalOpenAmt = DueAmt.multiply(MultiplierCM)
								.add(remainingAmt);
					}
				}
				else
				{
					if (DueAmt.signum() > 0)	//	positive
					{
						remainingAmt = remainingAmt.subtract(DueAmt);
						if (remainingAmt.signum() < 0)
							remainingAmt = Adempiere.ZERO;
					}
					else
					{
						remainingAmt = remainingAmt.add(DueAmt);
						if (remainingAmt.signum() < 0)
							remainingAmt = Adempiere.ZERO;
					}
				}
			}
			rs.close();
			pstmt.close();
		}	//	Invoice Schedule
	
		//	Rounding
		TotalOpenAmt = Currency.round(TotalOpenAmt, C_Currency_ID, null);
		
		//	Ignore Penny if there is a payment
		if (paidAmt.signum() != 0)
		{
			double open = TotalOpenAmt.doubleValue();
			if (open >= -0.01 && open <= 0.01)
				TotalOpenAmt = Adempiere.ZERO;
		}
		//
		return TotalOpenAmt;
	}	//	open
	
    // Begin e-Evolution 15/03/2006        
	/**
	 * 	Open Invoice Amount.
	 * 	- incoiceOpen
	 *	@param p_C_Invoice_ID invoice
	 *	@param p_C_InvoicePaySchedule_ID payment schedule
	 *  @param DateAcct Date Account
	 *  @return open amount
	 *	@throws SQLException
	 */
	public static BigDecimal openToDate (int p_C_Invoice_ID, int p_C_InvoicePaySchedule_ID, Timestamp DateAcct)
		throws SQLException
	{
		//	Invoice info
		int C_Currency_ID = 0;
		int C_ConversionType_ID = 0;
		BigDecimal GrandTotal = null;
		BigDecimal MultiplierAP = null;
		BigDecimal MultiplierCM = null;
		//
		String sql = "SELECT MAX(C_Currency_ID),MAX(C_ConversionType_ID),"
			+ " SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier) "
			+ "FROM	C_Invoice_v "	//	corrected for CM / Split Payment
			+ "WHERE C_Invoice_ID=?"
                // Begin 
                        + " AND DateAcct <= ?";
                // End
		if (p_C_InvoicePaySchedule_ID != 0)
			sql += " AND C_InvoicePaySchedule_ID=?";
		PreparedStatement pstmt = Compiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Invoice_ID);
        pstmt.setTimestamp(2, DateAcct);
		if (p_C_InvoicePaySchedule_ID != 0)
        pstmt.setInt(3, p_C_InvoicePaySchedule_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			C_Currency_ID = rs.getInt(1);
			C_ConversionType_ID = rs.getInt(2);
			GrandTotal = rs.getBigDecimal(3);
			MultiplierAP = rs.getBigDecimal(4);
			MultiplierCM = rs.getBigDecimal(5);
		}
		rs.close();
		pstmt.close();
                pstmt = null;
		//	No Invoice
		if (GrandTotal == null)
			return null;

		BigDecimal paidAmt = allocatedAmtToDate(p_C_Invoice_ID, C_Currency_ID, 
			C_ConversionType_ID, MultiplierAP,DateAcct);
        BigDecimal TotalOpenAmt = GrandTotal.subtract(paidAmt);
        
		/**
		 	GrandTotal	Paid	TotalOpen	Remaining	Due		x
		 	100			0		100			=0
		 1a						=50-0					50		x
		 1b									=0-50 =0	50
		 2a									=0-50 =0	50		
		 2b						=50-0					50 		x
		 --
		 	100			10		100			=10
		 1a						=50-10					50		x
		 1b									=10-50 =0	50
		 2a									=10-50 =0	50		
		 2b						=50-0					50 		x
		 --
		 	100			60		100			=60
		 1a						=50-60 =0					50		x
		 1b									=60-50 		50
		 2a									=60-50 =10	50		
		 2b						=50-10					50 		x
		 --
		**/
		
		//	Do we have a Payment Schedule ?
		if (p_C_InvoicePaySchedule_ID > 0)	//	if not valid = lists invoice amount
		{
			TotalOpenAmt = GrandTotal;
			BigDecimal remainingAmt = paidAmt;
			sql = "SELECT C_InvoicePaySchedule_ID, DueAmt "
				+ "FROM C_InvoicePaySchedule "
				+ "WHERE C_Invoice_ID=?"
				+ " AND IsValid='Y' "
				+ "ORDER BY DueDate";
			pstmt = Compiere.prepareStatement(sql);
			pstmt.setInt(1, p_C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int C_InvoicePaySchedule_ID = rs.getInt(1); 
				BigDecimal DueAmt = rs.getBigDecimal(2);
				//
				if (C_InvoicePaySchedule_ID == p_C_InvoicePaySchedule_ID)
				{
					if (DueAmt.signum() > 0)	//	positive
					{
						if (DueAmt.compareTo(remainingAmt) < 0)		// paid more 
							TotalOpenAmt = Compiere.ZERO;
						else
							TotalOpenAmt = DueAmt.multiply(MultiplierCM)
								.subtract(remainingAmt);
					}
					else
					{
						if (DueAmt.compareTo(remainingAmt) > 0)		// paid more 
							TotalOpenAmt = Compiere.ZERO;
						else
							TotalOpenAmt = DueAmt.multiply(MultiplierCM)
								.add(remainingAmt);
					}
				}
				else
				{
					if (DueAmt.signum() > 0)	//	positive
					{
						remainingAmt = remainingAmt.subtract(DueAmt);
						if (remainingAmt.signum() < 0)
							remainingAmt = Compiere.ZERO;
					}
					else
					{
						remainingAmt = remainingAmt.add(DueAmt);
						if (remainingAmt.signum() < 0)
							remainingAmt = Compiere.ZERO;
					}
				}
			}
			rs.close();
			pstmt.close();
		}	//	Invoice Schedule
	
		//	Rounding
		TotalOpenAmt = Currency.round(TotalOpenAmt, C_Currency_ID, null);
		
		//	Ignore Penny if there is a payment
		if (paidAmt.signum() != 0)
		{
			double open = TotalOpenAmt.doubleValue();
			if (open >= -0.01 && open <= 0.01)
				TotalOpenAmt = Compiere.ZERO;
		}
		//
		return TotalOpenAmt;
	}	//	open
	// End e-Evolution 15/03/2006
	
	// Begin e-Evolution 15/03/2006
	/**
	 * 	Get Invoice paid(allocated) amount.
	 * 	- invoicePaid
	 *	@param p_C_Invoice_ID invoice
	 *	@param p_C_Currency_ID currency
	 *	@param p_MultiplierAP multiplier
	 *  @param DateAcct Date Account
	 *	@return paid amount
	 *	@throws SQLException
	 */

	public static BigDecimal paidToDate (int p_C_Invoice_ID, int p_C_Currency_ID, int p_MultiplierAP, Timestamp DateAcct)
		throws SQLException
	{
		//	Invalid Parameters
		if (p_C_Invoice_ID == 0 || p_C_Currency_ID == 0)
			return null;
		//	Parameters
		BigDecimal MultiplierAP = new BigDecimal((double)p_MultiplierAP);
		if (p_MultiplierAP == 0)
			MultiplierAP = Compiere.ONE;
		int C_ConversionType_ID = 0;
		
		//	Calculate Allocated Amount
		BigDecimal paymentAmt = allocatedAmtToDate(p_C_Invoice_ID, 
			p_C_Currency_ID, C_ConversionType_ID, MultiplierAP,DateAcct);
		return Currency.round(paymentAmt, p_C_Currency_ID, null);
	}	//	paid
	// End e-Evolution 15/03/2006
	
	/**
	 * 	Get Invoice paid(allocated) amount.
	 * 	- invoicePaid
	 *	@param p_C_Invoice_ID invoice
	 *	@param p_C_Currency_ID currency
	 *	@param p_MultiplierAP multiplier
	 *	@return paid amount
	 *	@throws SQLException
	 */
	public static BigDecimal paid (int p_C_Invoice_ID, int p_C_Currency_ID, int p_MultiplierAP)
		throws SQLException
	{
		//	Invalid Parameters
		if (p_C_Invoice_ID == 0 || p_C_Currency_ID == 0)
                    return null;                    
		//	Parameters
		BigDecimal MultiplierAP = new BigDecimal((double)p_MultiplierAP);
		if (p_MultiplierAP == 0)
			MultiplierAP = Adempiere.ONE;
		int C_ConversionType_ID = 0;
		
		//	Calculate Allocated Amount
		BigDecimal paymentAmt = allocatedAmt(p_C_Invoice_ID, 
			p_C_Currency_ID, C_ConversionType_ID, MultiplierAP);
		return Currency.round(paymentAmt, p_C_Currency_ID, null);
	}	//	paid

	
	/**
	 * 	Get Allocated Amt (not directly used)
	 *	@param C_Invoice_ID invoice
	 *	@param C_Currency_ID currency
	 *	@param C_ConversionType_ID conversion type
	 *	@param MultiplierAP multiplier
	 *	@return allocated amount
	 *	@throws SQLException
	 */
	public static BigDecimal allocatedAmt(int C_Invoice_ID, 
		int C_Currency_ID, int C_ConversionType_ID, BigDecimal MultiplierAP)
		throws SQLException
	{
		//	Calculate Allocated Amount
		BigDecimal paidAmt = Adempiere.ZERO;
		String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID,"
			+ " al.Amount, al.DiscountAmt, al.WriteOffAmt,"
			+ " a.C_Currency_ID, a.DateTrx "
			+ "FROM C_AllocationLine al"
			+ " INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
			+ "WHERE al.C_Invoice_ID=?"
			+ " AND a.IsActive='Y'";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, C_Invoice_ID);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next())
		{
			int AD_Client_ID = rs.getInt(1);
			int AD_Org_ID = rs.getInt(2);
			BigDecimal Amount = rs.getBigDecimal(3);
			BigDecimal DiscountAmt = rs.getBigDecimal(4);
			BigDecimal WriteOffAmt = rs.getBigDecimal(5);
			int C_CurrencyFrom_ID = rs.getInt(6);
			Timestamp DateTrx = rs.getTimestamp(7);
			//
			BigDecimal invAmt = Amount.add(DiscountAmt).add(WriteOffAmt);
			BigDecimal allocation = Currency.convert(invAmt.multiply(MultiplierAP),
				C_CurrencyFrom_ID, C_Currency_ID, DateTrx, C_ConversionType_ID, 
				AD_Client_ID, AD_Org_ID);
			if (allocation != null)
				paidAmt = paidAmt.add(allocation);
		}
		rs.close();
		pstmt.close();
		//
		return paidAmt;
	}	//	getAllocatedAmt
	
	// Begin e-Evolution ogi-cd 15/03/2006        
	/**
	 * 	Get Allocated Amt (not directly used)
	 *	@param C_Invoice_ID invoice
	 *	@param C_Currency_ID currency
	 *	@param C_ConversionType_ID conversion type
	 *	@param MultiplierAP multiplier
	 *  @param DateAcct Date Account
	 *	@return allocated amount
	 *	@throws SQLException
	 */
	public static BigDecimal allocatedAmtToDate(int C_Invoice_ID, 
		int C_Currency_ID, int C_ConversionType_ID, BigDecimal MultiplierAP, Timestamp DateAcct)
		throws SQLException
	{
		//	Calculate Allocated Amount
		BigDecimal paidAmt = Compiere.ZERO;
		String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID,"
			+ " al.Amount, al.DiscountAmt, al.WriteOffAmt,"
			+ " a.C_Currency_ID, a.DateTrx "
			+ "FROM C_AllocationLine al"
			+ " INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
			+ "WHERE al.C_Invoice_ID=?"
			+ " AND a.IsActive='Y' AND a.DateAcct <= ?";
		PreparedStatement pstmt = Compiere.prepareStatement(sql);
		pstmt.setInt(1, C_Invoice_ID);
                pstmt.setTimestamp(2, DateAcct);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next())
		{
			int AD_Client_ID = rs.getInt(1);
			int AD_Org_ID = rs.getInt(2);
			BigDecimal Amount = rs.getBigDecimal(3);
			BigDecimal DiscountAmt = rs.getBigDecimal(4);
			BigDecimal WriteOffAmt = rs.getBigDecimal(5);
			int C_CurrencyFrom_ID = rs.getInt(6);
			Timestamp DateTrx = rs.getTimestamp(7);
			//
			BigDecimal invAmt = Amount.add(DiscountAmt).add(WriteOffAmt);
			BigDecimal allocation = Currency.convert(invAmt.multiply(MultiplierAP),
				C_CurrencyFrom_ID, C_Currency_ID, DateTrx, C_ConversionType_ID, 
				AD_Client_ID, AD_Org_ID);
			if (allocation != null)
				paidAmt = paidAmt.add(allocation);
		}
		rs.close();
		pstmt.close();
                pstmt = null;
		//
		return paidAmt;
	}	//	getAllocatedAmt
	// End e-Evolution 15/03/2006

	
	/**
	 * 	Get Invoice discount.
	 * 	C_Invoice_Discount - invoiceDiscount
	 *	@param p_C_Invoice_ID invoice
	 *	@param p_PayDate pay date
	 *	@param p_C_InvoicePaySchedule_ID pay schedule
	 *	@return discount amount or null
	 *	@throws SQLException
	 */
	public static BigDecimal discount (int p_C_Invoice_ID, 
		Timestamp p_PayDate, int p_C_InvoicePaySchedule_ID)
		throws SQLException
	{
		//	Parameters
		if (p_C_Invoice_ID == 0)
		    return null;                    
		Timestamp PayDate = p_PayDate;
		if (PayDate == null)
			PayDate = new Timestamp (System.currentTimeMillis());
		PayDate = Adempiere.trunc(PayDate);
		
		//	Invoice Info
		boolean IsDiscountLineAmt = false;
		BigDecimal GrandTotal = null;
		BigDecimal TotalLines = null;
		int C_PaymentTerm_ID = 0;
		Timestamp DateInvoiced = null;
		boolean IsPayScheduleValid = false;
		int C_Currency_ID = 0;
		String sql = "SELECT ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines, "
			+ " i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid, i.C_Currency_ID "
			+ "FROM C_Invoice i"
			+ " INNER JOIN AD_ClientInfo ci ON (ci.AD_Client_ID=i.AD_Client_ID) "
			+ "WHERE i.C_Invoice_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Invoice_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			IsDiscountLineAmt = "Y".equals(rs.getString(1));
			GrandTotal = rs.getBigDecimal(2);
			TotalLines = rs.getBigDecimal(3);
			C_PaymentTerm_ID = rs.getInt(4);
			DateInvoiced = rs.getTimestamp(5);
			IsPayScheduleValid = "Y".equals(rs.getString(6));
			C_Currency_ID = rs.getInt(7);
		}
		rs.close();
		pstmt.close();
		//	Not found
		if (GrandTotal == null)
			//vpj-cd return null;
                    return Adempiere.ZERO;
		
		//	What Amount is the Discount Base?
		BigDecimal amount = GrandTotal;
		if (IsDiscountLineAmt)
			amount = TotalLines;
		
		//	Anything to discount?
		if (amount.signum() == 0)
			return Adempiere.ZERO;
		
		//	Valid Payment Schedule (has discount)
		if (IsPayScheduleValid && p_C_InvoicePaySchedule_ID > 0)
		{
			BigDecimal discount = Adempiere.ZERO;
			sql = "SELECT DiscountAmt "
				+ "FROM C_InvoicePaySchedule "
				+ "WHERE C_InvoicePaySchedule_ID=?"
				+ " AND TRUNC(DiscountDate) <= ?";
			pstmt = Adempiere.prepareStatement(sql);
			pstmt.setInt(1, p_C_InvoicePaySchedule_ID);
			pstmt.setTimestamp(2, PayDate);
			rs = pstmt.executeQuery();
			if (rs.next())
				discount = rs.getBigDecimal(1);
			rs.close();
			pstmt.close();
			//
			return discount;
		}

		//	return discount amount	
		return PaymentTerm.discount (amount, C_Currency_ID, 
			C_PaymentTerm_ID, DateInvoiced, PayDate);
	}	//	discount
	
	/**
	 * 	Test
	 *	@param args
	 *
	public static void main (String[] args)
	{
		
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Adempiere.s_type = Adempiere.TYPE_ORACLE;
			Adempiere.s_url = "jdbc:oracle:thin:@//dev1:1521/dev1.adempiere.org";
			Adempiere.s_uid = "adempiere";
			Adempiere.s_pwd = "adempiere";
		//	System.out.println(Invoice.open(1000000, 1000004));
		//	System.out.println(Invoice.open(1000000, 1000005));
		//	System.out.println(Invoice.open(1000001, 1000006));
		//	System.out.println(Invoice.open(1000001, 1000007));
			System.out.println(Invoice.paid(101, 100, 1));
			System.out.println(Invoice.paid(1000000, 100, 1));
			System.out.println(Invoice.paid(1000001, 100, 1));
			System.out.println(Invoice.paid(1000002, 100, 1));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}	//	main	/* */
	
}	//	Invoice
