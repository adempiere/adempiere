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
package org.compiere.sqlj;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 *	SQLJ Payment related Functions
 *	
 *  @author Jorg Janke
 *  @version $Id: Payment.java,v 1.2 2006/07/30 00:59:07 jjanke Exp $
 */
public class Payment
{
	/**
	 * 	Get allocated Payment amount.
	 * 	- paymentAllocated
	 *	@param p_C_Payment_ID payment
	 *	@param p_C_Currency_ID currency
	 *	@return allocated amount
	 *	@throws SQLException
	 */
	public static BigDecimal allocated (int p_C_Payment_ID, int p_C_Currency_ID)
		throws SQLException
	{
		BigDecimal PayAmt = null;
		int C_Charge_ID = 0;
		//
		String sql = "SELECT PayAmt, C_Charge_ID "
			+ "FROM C_Payment_v "	//	corrected for AP/AR
			+ "WHERE C_Payment_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Payment_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			PayAmt = rs.getBigDecimal(1);
			C_Charge_ID = rs.getInt(2);
		}
		rs.close();
		pstmt.close();

		if (C_Charge_ID > 0)
			return PayAmt;

		int C_ConversionType_ID = 0;
		
		//	Calculate Allocated Amount
		BigDecimal allocatedAmt = getAllocatedAmt(p_C_Payment_ID, 
			p_C_Currency_ID, C_ConversionType_ID);
		
		//	Round
		return Currency.round(allocatedAmt, p_C_Currency_ID, null);
	}	//	allocated

	/**
	 * 	Get available Payment amount in payment currency
	 *	@param p_C_Payment_ID payment
	 *	@return available amt
	 *	@throws SQLException
	 */
	public static BigDecimal available (int p_C_Payment_ID)
		throws SQLException
	{
		if (p_C_Payment_ID == 0)
			return null;
		//
		int C_Currency_ID = 0;
		int C_ConversionType_ID = 0;
		BigDecimal PayAmt = null;
		int C_Charge_ID = 0;
		//
		String sql = "SELECT C_Currency_ID, C_ConversionType_ID, PayAmt, C_Charge_ID "
			+ "FROM C_Payment_v "	//	corrected for AP/AR
			+ "WHERE C_Payment_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Payment_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			C_Currency_ID = rs.getInt(1);
			C_ConversionType_ID = rs.getInt(2);
			PayAmt = rs.getBigDecimal(3);
			C_Charge_ID = rs.getInt(4);
		}
		rs.close();
		pstmt.close();
		//	Not found
		if (PayAmt == null)
			return null;
		//	Charge - nothing available
		if (C_Charge_ID != 0)
			return Adempiere.ZERO;
		
		//	Calculate Allocated Amount
		BigDecimal allocatedAmt = getAllocatedAmt(p_C_Payment_ID, 
			C_Currency_ID, C_ConversionType_ID);
		
		BigDecimal available = PayAmt.subtract(allocatedAmt); 
		
		//	Round
		return Currency.round(available, C_Currency_ID, null);
	}	//	available

	/**
	 * 	Get Allocated Amt
	 *	@param p_C_Payment_ID payment
	 *	@param p_C_Currency_ID currency
	 *	@param p_C_ConversionType_ID conversion type
	 *	@return allocated amount in currency
	 *	@throws SQLException
	 */
	static BigDecimal getAllocatedAmt(int p_C_Payment_ID, 
		int p_C_Currency_ID, int p_C_ConversionType_ID)
		throws SQLException
	{
		//	Calculate Allocated Amount
		BigDecimal allocatedAmt = Adempiere.ZERO;
		String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx "
			+ "FROM C_AllocationLine al "
			+ " INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
			+ "WHERE al.C_Payment_ID=?"
			+ " AND a.IsActive='Y'";
		//	AND al.C_Invoice_ID IS NOT NULL;
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_C_Payment_ID);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next())
		{
			int AD_Client_ID = rs.getInt(1);
			int AD_Org_ID = rs.getInt(2);
			BigDecimal amount = rs.getBigDecimal(3);
			int C_CurrencyFrom_ID  = rs.getInt(4);
			Timestamp DateTrx = rs.getTimestamp(5);
			//
			BigDecimal allocation = Currency.convert(amount, //.multiply(MultiplierAP),
				C_CurrencyFrom_ID, p_C_Currency_ID, DateTrx,p_C_ConversionType_ID, 
				AD_Client_ID, AD_Org_ID); 
			if (allocation != null)
				allocatedAmt = allocatedAmt.add(allocation);
		}
		rs.close();
		pstmt.close();
		//
		return allocatedAmt;
	}	//	getAllocatedAmt
	
}	//	Payment
