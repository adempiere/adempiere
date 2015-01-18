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
package org.compiere.process;

import java.math.*;
import java.sql.*;
import java.util.logging.*;

import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Suspense account reconciliation report
 *  @author Paul Bowden (phib)
 */
public class FactReconcile extends SvrProcess
{
	private MElementValue account;
	private String type;
	private int ruleID;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		int accountID = 0;
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Rule_ID"))
				ruleID = para[i].getParameterAsInt();
			else if (name.equals("Account_ID"))
				accountID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
			if ( accountID > 0 )
				account = new MElementValue(getCtx(), accountID, get_TrxName());
		}
	}	//	prepare

	/**
	 * 	DoIt
	 *	@return Message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{

		String result;
		log.info("Reconcile Account: " + account.getName());
		
		String subselect = "null";
		
		MRule rule = MRule.get(getCtx(), ruleID);
		
		if ( rule == null || rule.is_new() || !rule.getRuleType().equals("Q") || ! rule.getEventType().equals("R") )
			return "Invalid rule for account reconciliation.";
		else
			subselect = rule.getScript();
		
		log.log(Level.FINE, "Rule subselect: " + subselect);
		
		
		/*  example matching rules:
		 * 
		 * 
		// ar/ap TRade (Receivables/Vendor Liability)
		if ( type.equals("TR") )
			subselect = " (CASE WHEN fa.AD_Table_ID = " + MInvoice.Table_ID +
			" THEN 'C_Invoice:' || fa.Record_ID " +
			" WHEN fa.AD_Table_ID = " + MAllocationHdr.Table_ID +
			" THEN (SELECT 'C_Invoice:' || al.C_Invoice_ID FROM C_AllocationLine al " +
			" WHERE al.C_AllocationHdr_ID = fa.Record_ID " +
			" AND al.C_AllocationLine_ID = fa.Line_ID ) END)";
		// Bank in Transit
		else if ( type.equals("BT"))
			subselect = " (CASE WHEN fa.AD_Table_ID = " + MPayment.Table_ID +
			" THEN 'C_Payment:' || fa.Record_ID " +
			" WHEN fa.AD_Table_ID = " + MBankStatement.Table_ID +
			" THEN (SELECT 'C_Payment:' || bsl.C_Payment_ID FROM C_BankStatementLine bsl " +
			" WHERE bsl.C_BankStatement_ID = fa.Record_ID " +
			" AND bsl.C_BankStatementLine_ID = fa.Line_ID ) END)";
		// Payment Clearing (unallocated cash/payment selection)
		else if ( type.equals("PC") )
			subselect = " (CASE WHEN fa.AD_Table_ID = " + MPayment.Table_ID +
			" THEN 'C_Payment:' || fa.Record_ID " +
			" WHEN fa.AD_Table_ID = " + MAllocationHdr.Table_ID +
			" THEN (SELECT 'C_Payment:' || al.C_Payment_ID FROM C_AllocationLine al " +
			" WHERE al.C_AllocationHdr_ID = fa.Record_ID " +
			" AND al.C_AllocationLine_ID = fa.Line_ID ) END)";
			
			*/
		
		String sql = "";
		
		log.info("AD_PInstance_ID= " + getAD_PInstance_ID());
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count;
		int unmatched;
		try
		{
			
			// add new facts into reconciliation table
			 sql = "INSERT into Fact_Reconciliation " +
				"(AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, " +
				"IsActive, Fact_Acct_ID) " + 
				"SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, " +
				"Updated, UpdatedBy, IsActive, " +
				"Fact_Acct_ID " +
				"FROM Fact_Acct f " +
				"WHERE Account_ID = ? " +
				"AND NOT EXISTS (SELECT 1 FROM Fact_Reconciliation r " +
				"WHERE r.Fact_Acct_ID = f.Fact_Acct_ID) ";
					
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, account.get_ID());
			count = pstmt.executeUpdate();
			log.log(Level.FINE, "Inserted " + count + " new facts into Fact_Reconciliation");
			
			// set the matchcode based on the rule found in AD_Rule
			// which is a sql fragment that returns a string based on the accounting fact
			 sql = "UPDATE Fact_Reconciliation " +
				"SET MatchCode = (" + subselect +
				" ) " + 
				"WHERE MatchCode is null " +
				"AND (SELECT f.Account_ID FROM Fact_Acct f " +
				"     WHERE f.Fact_Acct_ID = Fact_Reconciliation.Fact_Acct_ID ) = ? " +
				"AND ( " + subselect +
					" ) IS NOT NULL " ;
					
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, account.get_ID());
			count = pstmt.executeUpdate();
			
			log.log(Level.FINE, "Updated " + count + " match codes.");
			
			// remove any matchcodes that don't balance to zero
			sql = "UPDATE Fact_Reconciliation " +
			"SET MatchCode = null " +
			" WHERE  (SELECT f1.Account_ID FROM Fact_Acct f1 " +
			"         WHERE f1.Fact_Acct_ID=Fact_Reconciliation.Fact_Acct_ID) = ? " +
			" AND (SELECT SUM(f2.amtacctdr-f2.amtacctcr) FROM Fact_Reconciliation r " +
				"    INNER JOIN Fact_Acct f2 ON (f2.Fact_Acct_ID = r.Fact_Acct_ID) " +
				"       WHERE r.MatchCode=Fact_Reconciliation.MatchCode" +
				"       AND f2.Account_ID = ?) <> 0 " +
			" AND MatchCode IS NOT NULL";
				
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, account.get_ID());
		pstmt.setInt(2, account.get_ID());
		unmatched = pstmt.executeUpdate();
		
		log.log(Level.FINE, "Cleared match codes from " + unmatched + " unreconciled facts.");
			
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return "Matched " + (count-unmatched) + " facts";
	}	//	doIt

}	//	FactReconcile

