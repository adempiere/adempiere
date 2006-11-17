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
 *	SQLJ Account related Functions
 *	
 *  @author Jorg Janke
 *  @version $Id: Account.java,v 1.3 2006/07/30 00:59:07 jjanke Exp $
 */
public class Account
{

	/**
	 * 	Get Balance based on Account Sign and Type.
	 * 	Acct_Balance - acctBalance
	 *  If an account is specified and found
	 *  - If the account sign is Natural it sets it based on Account Type
	 *	@param p_Account_ID account
	 *	@param p_AmtDr debit
	 *	@param p_AmtCr credit
	 *	@return cr or dr balance
	 *	@throws SQLException
	 */
	public static BigDecimal balance (int p_Account_ID, BigDecimal p_AmtDr, BigDecimal p_AmtCr)
		throws SQLException
	{
		BigDecimal AmtDr = p_AmtDr;
		if (AmtDr == null)
			AmtDr = Adempiere.ZERO;
		BigDecimal AmtCr = p_AmtCr;
		if (AmtCr == null)
			AmtCr = Adempiere.ZERO;
		BigDecimal balance = AmtDr.subtract(AmtCr);
		//
		if (p_Account_ID != 0)
		{
			String sql = "SELECT AccountType, AccountSign "
				+ "FROM C_ElementValue "
				+ "WHERE C_ElementValue_ID=?";
			PreparedStatement pstmt = Adempiere.prepareStatement(sql);
			pstmt.setInt(1, p_Account_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				String AccountType = rs.getString(1);
				String AccountSign = rs.getString(2);
				//	Natural Account Sign -> D/C
				if (AccountSign.equals("N"))
				{
					if (AccountType.equals("A") || AccountType.equals("E"))
		                AccountSign = "D";
					else
		                AccountSign = "C";
				}
				//	Debit Balance
				if (AccountSign.equals("C"))
					balance = AmtCr.subtract(AmtDr);
			}
			rs.close();
			pstmt.close();
		}
		//
		return balance;
	}	//	balance
	
}	//	Account
