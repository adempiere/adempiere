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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.eevolution.model.I_HR_Movement;
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
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1030">
 * 		@see FR [ 1030 ] Posting Error on payroll process</a>
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
	public Doc_HRProcess (MAcctSchema[] ass, ResultSet rs, String trxName) {
		super(ass, MHRProcess.class, rs, DOCTYPE_Payroll, trxName);
	}	//	Doc_Payroll

	@Override
	protected String loadDocumentDetails () {
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
	private DocLine[] loadLines(MHRProcess process) {
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		//	Get Movements
		List<MHRMovement> movements = new Query(getCtx(), I_HR_Movement.Table_Name, 
				"HR_Movement.HR_Process_ID = ? "
				+ "AND HR_Movement.Amount <> 0 "
				+ "AND EXISTS(SELECT 1 FROM HR_Concept c "
				+ "					WHERE c.HR_Concept_ID = HR_Movement.HR_Concept_ID "
				+ "					AND c.AccountSign != 'N')", getTrxName())
			.setParameters(process.getHR_Process_ID())
			.setOrderBy("C_BPartner_ID")
			.list();
		//	
		for (MHRMovement line : movements) {
			DocLine_Payroll docLine = new DocLine_Payroll(line, this);
			//
			log.fine(docLine.toString());
			list.add(docLine);
		}
		//	Convert to array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	@Override
	public BigDecimal getBalance() {
		BigDecimal retValue = Env.ZERO;
		return retValue; 
	}   //  getBalance

	@Override
	public ArrayList<Fact> createFacts (MAcctSchema as) {
		int C_BPartner_ID = 0;
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		BigDecimal totalDebit = Env.ZERO;
		BigDecimal totalCredit = Env.ZERO;
		for (DocLine line : p_lines) {
			if (C_BPartner_ID == 0)
				C_BPartner_ID = line.getC_BPartner_ID();
			//Close every employee
			if (line.getC_BPartner_ID() != 0 && line.getC_BPartner_ID() != C_BPartner_ID && process.getHR_Payroll().isPostPerEmployee()) {
				closeBPartner(totalDebit, totalCredit, fact, as, C_BPartner_ID);
				C_BPartner_ID = line.getC_BPartner_ID();
				totalDebit = Env.ZERO;
				totalCredit = Env.ZERO;
			}
			DocLine_Payroll payrollDocLine = (DocLine_Payroll) line;
			//	Get Source Amount
			BigDecimal sumAmount = line.getAmtSource();
			// round amount according to currency
			sumAmount = sumAmount.setScale(as.getStdPrecision(), BigDecimal.ROUND_HALF_UP);
			MHRConcept concept = MHRConcept.getById(as.getCtx(), payrollDocLine.getHR_Concept_ID() , getTrxName());
			//	Get Concept Account
			X_HR_Concept_Acct conceptAcct = concept.getConceptAcct(
					Optional.ofNullable(payrollDocLine.getAccountSchemaId()),
					Optional.ofNullable(payrollDocLine.getPayrollId()),
					Optional.ofNullable(payrollDocLine.getPayrollId()));

			if(conceptAcct == null) {
				continue;
			}
			//	
			if (payrollDocLine.getAccountSign() != null && payrollDocLine.getAccountSign().length() > 0
					&& (MHRConcept.ACCOUNTSIGN_Debit.equals(payrollDocLine.getAccountSign())
					|| MHRConcept.ACCOUNTSIGN_Credit.equals(payrollDocLine.getAccountSign()))) {
				if (conceptAcct.isBalancing()) {
					MAccount accountBPD = MAccount.getValidCombination (getCtx(), conceptAcct.getHR_Expense_Acct() , getTrxName());
					FactLine debitLine = fact.createLine(line, accountBPD, getC_Currency_ID(),sumAmount, null);
					debitLine.setDescription(process.getName() + " " + concept.getValue() + " " + concept.getName());
					debitLine.saveEx();
					MAccount accountBPC = MAccount.getValidCombination (getCtx(), conceptAcct.getHR_Revenue_Acct() , getTrxName());
					FactLine creditLine = fact.createLine(line,accountBPC, getC_Currency_ID(),null,sumAmount);
					creditLine.setDescription(process.getName() + " " + concept.getValue() + " " + concept.getName());
					creditLine.saveEx();
				} else {
					if (MHRConcept.ACCOUNTSIGN_Debit.equals(payrollDocLine.getAccountSign())) {
						MAccount accountBPD = MAccount.getValidCombination (getCtx(), conceptAcct.getHR_Expense_Acct() , getTrxName());
						FactLine debitLine = fact.createLine(line, accountBPD, getC_Currency_ID(),sumAmount, null);
						debitLine.setDescription(process.getName() + " " + concept.getValue() + " " + concept.getName());
						debitLine.saveEx();
						totalDebit = totalDebit.add(sumAmount);
					} else if (MHRConcept.ACCOUNTSIGN_Credit.equals(payrollDocLine.getAccountSign())) {
						MAccount accountBPC = MAccount.getValidCombination (getCtx(), conceptAcct.getHR_Revenue_Acct(), getTrxName());
						FactLine creditLine = fact.createLine(line,accountBPC, getC_Currency_ID(),null,sumAmount);
						creditLine.setDescription(process.getName() + " " + concept.getValue() + " " + concept.getName());
						creditLine.saveEx();
						totalCredit = totalCredit.add(sumAmount);
					}
				}
			}
		}
		if (process.getHR_Payroll().isPostPerEmployee()) {
			closeBPartner(totalDebit, totalCredit, fact, as, C_BPartner_ID);
		} else {
			if (totalDebit.signum() != 0
					|| totalCredit.signum() != 0) {
				int C_Charge_ID = process.getHR_Payroll().getC_Charge_ID();
				if (C_Charge_ID > 0) {
					MAccount acct = MCharge.getAccount(C_Charge_ID, as, totalDebit.subtract(totalCredit));
					FactLine regTotal = null;
					if (totalDebit.abs().compareTo(totalCredit.abs()) > 0) {
						regTotal = fact.createLine(null, acct, getC_Currency_ID(), null, totalDebit.subtract(totalCredit));
					} else {
						regTotal = fact.createLine(null, acct, getC_Currency_ID(), totalCredit.abs().subtract(totalDebit.abs()), null);
					}
					regTotal.setAD_Org_ID(getAD_Org_ID());
				}
			}
		}
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}

    private void closeBPartner (BigDecimal totalDebit, BigDecimal totalCredit,
                                Fact fact, MAcctSchema as, int c_bpartner_id)
    {
        if(totalDebit.signum() != 0
                || totalCredit.signum() != 0)
        {
            int C_Charge_ID = process.getHR_Payroll().getC_Charge_ID();
            if (C_Charge_ID > 0) {
                MAccount acct = MCharge.getAccount(C_Charge_ID, as, totalDebit.subtract(totalCredit));
                FactLine regTotal = null;
                if(totalDebit.abs().compareTo(totalCredit.abs()) > 0 )
                    regTotal = fact.createLine(null, acct, getC_Currency_ID(), null, totalDebit.subtract(totalCredit));
                else
                    regTotal = fact.createLine(null, acct, getC_Currency_ID(), totalCredit.abs().subtract(totalDebit.abs()), null);
                if (regTotal != null)
                {
                    regTotal.setAD_Org_ID(getAD_Org_ID());
                    regTotal.setC_BPartner_ID(c_bpartner_id);
                    regTotal.saveEx();
                }
            }
        }
    }
}   //  Doc_Payroll
