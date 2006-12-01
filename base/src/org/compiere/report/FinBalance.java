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
package org.compiere.report;

import java.math.*;
import java.util.*;

import org.compiere.model.*;
import org.compiere.process.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *  Financial Balance Maintenance Engine
 *
 *  @author Jorg Janke
 *  @version $Id: FinBalance.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class FinBalance extends SvrProcess
{
	/**
	 * 	Financial Report Constructor
	 */
	public FinBalance()
	{
		super();
		log.info(" ");
	}	//	FinBalance

	/**	Logger							*/
	protected static CLogger		s_log = CLogger.getCLogger (FinBalance.class);

	private int			p_C_AcctSchema_ID = 0;
	private boolean		p_IsRecreate = false;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		//	Parameter
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("IsRecreate"))
				p_IsRecreate = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message to be translated
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		log.fine("C_AcctSchema_ID=" + p_C_AcctSchema_ID
			+ ", IsRecreate=" + p_IsRecreate);
		if (p_C_AcctSchema_ID != 0)
			updateBalance(p_C_AcctSchema_ID, p_IsRecreate);
		else
			updateBalanceClient(getCtx(), getAD_Client_ID(), p_IsRecreate);
		return "";
	}	//	doIt

	/**
	 * 	Delete Balances
	 * 	@param C_AcctSchema_ID	accounting schema 0 for all
	 *  @return Message to be translated
	 */
	public static String deleteBalance (int C_AcctSchema_ID)
	{
		StringBuffer sql = new StringBuffer ("DELETE FROM Fact_Acct_Balance WHERE ");
		if (C_AcctSchema_ID != 0)
			sql.append ("C_AcctSchema_ID=").append (C_AcctSchema_ID);
		//
		int no = DB.executeUpdate(sql.toString(), null);	//	out of trx
		String msg = "@Deleted@=" + no;
		s_log.fine("C_AcctSchema_ID=" + C_AcctSchema_ID + " #=" + no);
		//
		return msg;
	}	//	deleteBalance

	/**
	 * 	Update / Create Balances.
	 * 	Called from FinReport, FactAcctReset (indirect)
	 * 	@param C_AcctSchema_ID	accounting schema
	 * 	@param deleteFirst delete (all) balances first
	 *  @return Message to be translated
	 */
	public static String updateBalance (int C_AcctSchema_ID, boolean deleteFirst)
	{
		s_log.info("C_AcctSchema_ID=" + C_AcctSchema_ID + " - DeleteFirst=" + deleteFirst);
		long start = System.currentTimeMillis();

		if (deleteFirst)
		{
			int no = DB.executeUpdate("DELETE Fact_Acct_Balance WHERE C_AcctSchema_ID=?", 
				C_AcctSchema_ID, null);
			s_log.fine("Deleted=" + no);
		}


		//	Update existing
		String sql = "UPDATE Fact_Acct_Balance ab "
			+ "SET (AmtAcctDr, AmtAcctCr, Qty)= "
			+ "(SELECT COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0) "
			+ "FROM Fact_Acct a "
			+ "WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID"
			+ " AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)"
			+ " AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType"
			+ " AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)"
			+ " AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)"
			+ " AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)"
			+ " AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)"
			+ " AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) "
			+ "GROUP BY AD_Client_ID,AD_Org_ID, C_AcctSchema_ID, TRUNC(DateAcct),"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID, C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID) "
			+ "WHERE C_AcctSchema_ID=" + C_AcctSchema_ID
			+ " AND EXISTS (SELECT 1 FROM Fact_Acct a "
				+ "WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID"
				+ " AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)"
				+ " AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType"
				+ " AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)"
				+ " AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)"
				+ "	AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)"
				+ " AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)"
				+ " AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) "
				+ "GROUP BY AD_Client_ID,AD_Org_ID,"
				+ " C_AcctSchema_ID, TRUNC(DateAcct),"
				+ " Account_ID, PostingType,"
				+ " M_Product_ID, C_BPartner_ID,"
				+ " C_Project_ID, AD_OrgTrx_ID,"
				+ " C_SalesRegion_ID, C_Activity_ID,"
				+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,"
				+ " User1_ID, User2_ID, GL_Budget_ID)";
		if (!deleteFirst)
		{
			int no = DB.executeUpdate(sql, null);
			s_log.fine("Updates=" + no);
		}

		/** Insert		**/
		sql = "INSERT INTO Fact_Acct_Balance "
			+ "(AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, DateAcct,"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ "	C_Project_ID, AD_OrgTrx_ID,	C_SalesRegion_ID,C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
			+ " AmtAcctDr, AmtAcctCr, Qty) "
		//
			+ "SELECT AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, TRUNC(DateAcct),"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID,C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
			+ " COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0) "
			+ "FROM Fact_Acct a "
			+ "WHERE C_AcctSchema_ID=" + C_AcctSchema_ID;
			if (!deleteFirst)
				sql += " AND NOT EXISTS (SELECT 1 "
					+ "FROM Fact_Acct_Balance x "
					+ "WHERE a.AD_Client_ID=x.AD_Client_ID AND a.AD_Org_ID=x.AD_Org_ID"
					+ " AND a.C_AcctSchema_ID=x.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(x.DateAcct)"
					+ " AND a.Account_ID=x.Account_ID AND a.PostingType=x.PostingType"
					+ " AND COALESCE(a.M_Product_ID,0)=COALESCE(x.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(x.C_BPartner_ID,0)"
					+ " AND COALESCE(a.C_Project_ID,0)=COALESCE(x.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(x.AD_OrgTrx_ID,0)"
					+ " AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(x.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(x.C_Activity_ID,0)"
					+ " AND COALESCE(a.C_Campaign_ID,0)=COALESCE(x.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(x.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(x.C_LocFrom_ID,0)"
					+ " AND COALESCE(a.User1_ID,0)=COALESCE(x.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(x.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(x.GL_Budget_ID,0) )";
		sql += " GROUP BY AD_Client_ID,AD_Org_ID, C_AcctSchema_ID, TRUNC(DateAcct),"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID, C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID";
		int no = DB.executeUpdate(sql, null);
		s_log.fine("Inserts=" + no);

		start = System.currentTimeMillis() - start;
		s_log.info((start/1000) + " sec");
		return "";
	}	//	updateBalance
	
	/**
	 * 	Update Balance of Client
	 *	@param ctx context
	 *	@param AD_Client_ID client
	 *	@param deleteFirst delete first
	 *	@return error or ""
	 */
	public static String updateBalanceClient (Properties ctx, int AD_Client_ID, boolean deleteFirst)
	{
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, AD_Client_ID);
		for (int i = 0; i < ass.length; i++)
			updateBalance(ass[i].getC_AcctSchema_ID(), deleteFirst);
		return "";
	}	//	updateBalanceClient


	/**************************************************************************
	 * 	Test
	 * 	@param args ignored
	 */
	public static void main(String[] args)
	{
		FinBalance finBalance1 = new FinBalance();
	}	//	main

}	//	FinBalance
