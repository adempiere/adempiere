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
import org.compiere.model.MCharge;
import org.compiere.model.MElementValue;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRProcess;
import org.eevolution.model.X_HR_Concept_Acct;


/**
 *  Post Payroll Documents.
 *  <pre>
 *  Table:              HR_Process (??)
 *  Document Types:     HR_Process
 *  </pre>
 *  @author Oscar Gomez Islas
 *  @author victor.perez@e-evolution.com,www.e-evolution.com
 *  @version  $Id: Doc_Payroll.java,v 1.1 2007/01/20 00:40:02 ogomezi Exp $
 *  @author Cristina Ghita, www.arhipac.ro
 */
public class   Doc_HRProcess extends Doc
{
	public MHRProcess process = null;
	
	/** Process Payroll **/
	public static final String	DOCTYPE_Payroll			= "HRP";
	/**
	 *  Constructor
	 * 	@param ass accounting schema
	 * 	@param rs record
	 * 	@parem trxName trx
	 */
	public Doc_HRProcess (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MHRProcess.class, rs, DOCTYPE_Payroll, trxName);
	}	//	Doc_Payroll

	@Override
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
		MHRMovement[] lines = process.getLines(true);
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

	@Override
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue; 
	}   //  getBalance

	@Override
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		Fact fact = new Fact(this, as, Fact.POST_Actual);		
		final String sql= "SELECT m.HR_Concept_id, MAX(c.Name) As Name, SUM(m.Amount) As Amount, MAX(c.AccountSign) As AccountSign, " + // 1,2,3,4
		" MAX(CA.IsBalancing) As IsBalancing, e.AD_Org_ID As AD_Org_ID, m.C_Activity_ID, bp.C_BPartner_ID, m.User1_ID, m.User2_ID, m.User3_ID, m.User4_ID " // 5,6,7
		+ " FROM HR_Movement m"
		+ " INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.AD_Client_ID = m.AD_Client_ID AND ca.IsActive = 'Y')"
		+ " INNER JOIN HR_Concept      c  ON (c.HR_Concept_ID=m.HR_Concept_ID AND c.IsActive = 'Y')"
		+ " INNER JOIN C_BPartner      bp ON (bp.C_BPartner_ID = m.C_BPartner_ID)"
		+ " INNER JOIN HR_Employee	 e  ON (bp.C_BPartner_ID=e.C_BPartner_ID)"
		+ " INNER JOIN HR_Department   d  ON (d.HR_Department_ID=e.HR_Department_ID)"
		+ " WHERE m.HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0) AND c.AccountSign != 'N'"
		+ " GROUP BY m.HR_Concept_ID,e.AD_Org_ID,m.C_Activity_ID , bp.C_BPartner_ID , m.User1_ID, m.User2_ID, m.User3_ID, m.User4_ID  "
		+ " ORDER BY e.AD_Org_ID,m.C_Activity_ID,bp.C_BPartner_ID, m.User1_ID, m.User2_ID, m.User3_ID, m.User4_ID ";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try
		{
			BigDecimal totalDebit  = Env.ZERO;
			BigDecimal totalCredit = Env.ZERO; 
			pstmt = DB.prepareStatement (sql, getTrxName());
			pstmt.setInt (1, process.getHR_Process_ID());
			rs = pstmt.executeQuery ();	
			while (rs.next())
			{
				int HR_Concept_ID = rs.getInt("HR_Concept_ID");
				BigDecimal sumAmount = rs.getBigDecimal("Amount");
				// round amount according to currency
				sumAmount = sumAmount.setScale(as.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
				String AccountSign = rs.getString("AccountSign");
				boolean isBalancing = "Y".equals(rs.getString("IsBalancing"));
				int AD_OrgTrx_ID=rs.getInt("AD_Org_ID");
				int C_Activity_ID=rs.getInt("C_Activity_ID");
				int C_BPartner_ID=rs.getInt("C_BPartner_ID");
				int user1Id = rs.getInt("User1_ID");
				int user2Id = rs.getInt("User3_ID");
				int user3Id = rs.getInt("User4_ID");
				int user4Id = rs.getInt("User5_ID");
				MHRMovement movement =  new MHRMovement(getCtx() , 0 , getTrxName());
				movement.setC_BPartner_ID(C_BPartner_ID);
				movement.setHR_Concept_ID(HR_Concept_ID);
				movement.setAccountSign(AccountSign);
				movement.setAD_OrgTrx_ID(AD_OrgTrx_ID);
				movement.setC_Activity_ID(C_Activity_ID);
				movement.setAmount(sumAmount);
				movement.setUser1_ID(user1Id);
				movement.setUser2_ID(user2Id);
				movement.setUser3_ID(user3Id);
				movement.setUser4_ID(user4Id);
				DocLine_Payroll docLine = new DocLine_Payroll(movement, this);
				//
				if (AccountSign != null && AccountSign.length() > 0 
				&& (MHRConcept.ACCOUNTSIGN_Debit.equals(AccountSign) 
				|| MHRConcept.ACCOUNTSIGN_Credit.equals(AccountSign))) 
				{
					if (isBalancing)
					{
						MAccount accountBPD = MAccount.get (getCtx(), getAccountBalancing(as.getC_AcctSchema_ID(),HR_Concept_ID,MHRConcept.ACCOUNTSIGN_Debit));
						FactLine debit=fact.createLine(docLine, accountBPD,as.getC_Currency_ID(),sumAmount, null);
						debit.saveEx();
						MAccount accountBPC = MAccount.get (getCtx(),this.getAccountBalancing(as.getC_AcctSchema_ID(),HR_Concept_ID, MHRConcept.ACCOUNTSIGN_Credit));
						FactLine credit = fact.createLine(docLine,accountBPC ,as.getC_Currency_ID(),null,sumAmount);
						credit.saveEx();
					}
					else
					{
						if (MHRConcept.ACCOUNTSIGN_Debit.equals(AccountSign))
						{
							MAccount accountBPD = MAccount.get (getCtx(), getAccountBalancing(as.getC_AcctSchema_ID(),HR_Concept_ID,MHRConcept.ACCOUNTSIGN_Debit));
							FactLine debit=fact.createLine(docLine, accountBPD,as.getC_Currency_ID(),sumAmount, null);
							debit.saveEx();
							totalDebit = totalDebit.add(sumAmount);
						}
						else if (MHRConcept.ACCOUNTSIGN_Credit.equals(AccountSign))
						{
							MAccount accountBPC = MAccount.get (getCtx(),this.getAccountBalancing(as.getC_AcctSchema_ID(),HR_Concept_ID,MHRConcept.ACCOUNTSIGN_Credit));
							FactLine credit = fact.createLine(docLine,accountBPC ,as.getC_Currency_ID(),null,sumAmount);
							credit.saveEx();
							totalCredit = totalCredit.add(sumAmount);
						}
					}
				}
				movement = null;
			}
			if(totalDebit.signum() != 0 
			|| totalCredit.signum() != 0)
			{					
				int C_Charge_ID = process.getHR_Payroll().getC_Charge_ID();
				if (C_Charge_ID > 0) {
					MAccount acct = MCharge.getAccount(C_Charge_ID, as, totalDebit.subtract(totalCredit));
					FactLine regTotal = null;
					if(totalDebit.abs().compareTo(totalCredit.abs()) > 0 )
						regTotal = fact.createLine(null, acct ,as.getC_Currency_ID(), null, totalDebit.subtract(totalCredit));
					else
						regTotal = fact.createLine(null, acct ,as.getC_Currency_ID(), totalCredit.abs().subtract(totalDebit.abs()), null);
					regTotal.setAD_Org_ID(getAD_Org_ID());
					regTotal.saveEx();
				}
			}

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
			p_Error = e.getLocalizedMessage();
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}

	/**
	 * get account balancing
	 * @param AcctSchema_ID
	 * @param HR_Concept_ID
	 * @param AccountSign Debit or Credit only
	 * @return Account ID 
	 */
	private int getAccountBalancing (int AcctSchema_ID, int HR_Concept_ID, String AccountSign)
	{
		String field;
		if (MElementValue.ACCOUNTSIGN_Debit.equals(AccountSign))
		{
			field = X_HR_Concept_Acct.COLUMNNAME_HR_Expense_Acct;
		}
		else if (MElementValue.ACCOUNTSIGN_Credit.equals(AccountSign))
		{
			field = X_HR_Concept_Acct.COLUMNNAME_HR_Revenue_Acct;
		}
		else
		{
			throw new IllegalArgumentException("Invalid value for AccountSign="+AccountSign);
		}
		final String sqlAccount = "SELECT "+field+" FROM HR_Concept_Acct"
							+ " WHERE HR_Concept_ID=? AND C_AcctSchema_ID=?";
		int Account_ID = DB.getSQLValueEx(getTrxName(), sqlAccount, HR_Concept_ID, AcctSchema_ID);		
		return Account_ID;
	}

}   //  Doc_Payroll
