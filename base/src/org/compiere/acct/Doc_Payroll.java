/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRProcess;


/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              HR_Process (??)
 *  Document Types:     HR_Process
 *  </pre>
 *  @author Oscar Gomez Islas
 *  @version  $Id: Doc_Payroll.java,v 1.1 2007/01/20 00:40:02 ogomezi Exp $
 */
public class Doc_Payroll extends Doc
{
	public MHRProcess process = null;
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@parem trxName trx
	 */
	protected Doc_Payroll (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MHRProcess.class, rs, DOCTYPE_Payroll, trxName);
	}	//	Doc_Payroll

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		process = (MHRProcess)getPO();
		setDateDoc(getDateAcct());
		//	Contained Objects
		p_lines = loadLines(process);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails


	/**
	 *	Load Payroll Line
	 *	@param Payroll Process
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MHRProcess process)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MHRMovement[] lines = process.getLines(true, process.getHR_Process_ID());
		for (int i = 0; i < lines.length; i++)
		{
			MHRMovement line = lines[i];
			DocLine_Payroll docLine = new DocLine_Payroll (line, this);
			//
			log.fine(docLine.toString());
			list.add(docLine);
		}
		//	Return Array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines


	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line amounts from total - no rounding
	 *  @return positive amount, if total invoice is bigger than lines
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return Env.ZERO; 
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  @param as account schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		Fact fact = new Fact(this, as, Fact.POST_Actual);		
		String sql2= "SELECT m.HR_Concept_id,0,MAX(c.Name),SUM(ROUND(m.Amount,2)),MAX(c.AccountSign),MAX(CA.IsBalancing),e.ad_org_id,d.c_activity_id" // 1,2,3,4,5,6,7,8
					+"	 FROM HR_Movement m"
					+"	 INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.IsActive = 'Y')"
					+"	 INNER JOIN HR_Concept      c  ON (c.HR_Concept_ID=m.HR_Concept_ID AND c.IsActive = 'Y')"
					+"	 INNER JOIN C_BPartner      bp ON (bp.C_BPartner_ID = m.C_BPartner_ID)"
					+"	 INNER JOIN hr_employee	    e  ON (bp.c_bpartner_id=e.c_bpartner_id)"
					+"	 INNER JOIN hr_department   d  ON (d.hr_department_id=e.hr_department_id)"
					+"	 WHERE m.HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0) AND c.AccountSign != 'N' AND ca.IsBalancing != 'Y'"
					+"	 GROUP BY m.hr_concept_id,e.ad_org_id,d.c_activity_id"
					+"	 order by e.ad_org_id,d.c_activity_id";
		
		System.err.println("Consulta:  " + sql2);
		PreparedStatement pstmt2 = null;
		try
		{
			pstmt2 = DB.prepareStatement (sql2, process.get_TrxName());
			pstmt2.setInt (1, process.getHR_Process_ID());
			ResultSet rs2 = pstmt2.executeQuery ();	
			while (rs2.next ())
			{
				int AD_OrgTrx_ID=rs2.getInt(7);
				int C_Activity_ID=rs2.getInt(8);
				if(rs2.getString(5).equals("D")){					// --- Debit
					MAccount accountBPD = MAccount.get (getCtx(), getAccountBalancing(as.getC_AcctSchema_ID(),rs2.getInt(1),rs2.getInt(2),"D"));
					FactLine debit=fact.createLine(null, accountBPD,as.getC_Currency_ID(),rs2.getBigDecimal(4), null);
					debit.setAD_OrgTrx_ID(AD_OrgTrx_ID);
					debit.setC_Activity_ID(C_Activity_ID);
					debit.save();
					MAccount accountBPC = MAccount.get (getCtx(),this.getAccountBalancing(as.getC_AcctSchema_ID(),rs2.getInt(1),rs2.getInt(2),"C"));
					FactLine credit = fact.createLine(null,accountBPC ,as.getC_Currency_ID(),null,rs2.getBigDecimal(4));
					credit.setAD_OrgTrx_ID(AD_OrgTrx_ID);
					credit.setC_Activity_ID(C_Activity_ID);
					credit.save();
				}
				else if(rs2.getString(5).equals("C")){			// --- Credit
					MAccount accountBPC = MAccount.get (getCtx(),this.getAccountBalancing(as.getC_AcctSchema_ID(),rs2.getInt(1),rs2.getInt(2),"C"));
					FactLine credit=fact.createLine(null, accountBPC,as.getC_Currency_ID(), null, rs2.getBigDecimal(4));
					credit.setAD_OrgTrx_ID(AD_OrgTrx_ID);
					credit.setC_Activity_ID(C_Activity_ID);
					credit.save();
					MAccount accountBPD = MAccount.get (getCtx(),this.getAccountBalancing(as.getC_AcctSchema_ID(),rs2.getInt(1),rs2.getInt(2),"D"));
					FactLine debit=fact.createLine(null, accountBPD ,as.getC_Currency_ID(),rs2.getBigDecimal(4), null);
					debit.setAD_OrgTrx_ID(AD_OrgTrx_ID);
					debit.setC_Activity_ID(C_Activity_ID);
					debit.save();
				}
			}
			rs2.close ();
			pstmt2.close ();
			pstmt2 = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql2, e);
		}
	
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}
	
	
	private int getAccount (int AcctSchema_ID, int HR_Concept_ID, int C_BP_Group_ID, String AccountSign)
	{
		String field = AccountSign.equals("D") ? "HR_Expense_Acct" : "HR_Revenue_Acct";
		int Account_ID;
		String sqlAccount = "SELECT " +field+ " FROM HR_Concept_Acct WHERE "
		       +" HR_Concept_ID=" +HR_Concept_ID+ " AND C_AcctSchema_ID=" +AcctSchema_ID;
		Account_ID = DB.getSQLValue(null, sqlAccount);
		if (Account_ID < 0 ) 
			Account_ID = DB.getSQLValue(null, sqlAccount);
		
		MAccount account = MAccount.get (getCtx(), Account_ID);
		return Account_ID;
	}
	
	private int getAccountBalancing (int AcctSchema_ID, int HR_Concept_ID, int C_BP_Group_ID, String AccountSign)
	{
		String field = AccountSign.equals("D") ? "HR_Expense_Acct" : "HR_Revenue_Acct";
		int Account_ID;
		String sqlAccount = "SELECT " +field+ " FROM HR_Concept_Acct WHERE "
		       +" HR_Concept_ID=" +HR_Concept_ID+ " AND C_AcctSchema_ID=" +AcctSchema_ID;
		Account_ID = DB.getSQLValue(null, sqlAccount);		
		return Account_ID;
	}	
}   //  Doc_Payroll
