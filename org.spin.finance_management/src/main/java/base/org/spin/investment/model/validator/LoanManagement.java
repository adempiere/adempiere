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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.model.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPayment;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.spin.investment.model.I_FM_Account;
import org.spin.investment.model.I_FM_Agreement;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAccountProduct;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMAmortization;
import org.spin.investment.util.FinancialSetting;

/**
 * Loan Management Model Validator
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class LoanManagement implements ModelValidator {
    private static CLogger log = CLogger.getCLogger(LoanManagement.class);
    
    /** Client			*/
	private int		clientId = -1;
	
    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
		//	
    	if (client != null) {	
			clientId = client.getAD_Client_ID();
		}
		engine.addModelChange(MFMAgreement.Table_Name, this);
		engine.addModelChange(MInvoice.Table_Name, this);
		engine.addDocValidate(MFMAgreement.Table_Name, this);
		engine.addDocValidate(MPaySelection.Table_Name, this);
		engine.addDocValidate(MPayment.Table_Name, this);
		engine.addDocValidate(MInvoice.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() {
    	return clientId;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {
    	if (po.get_TableName().equals(I_FM_Agreement.Table_Name)) {
    		if(type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) {
    			log.fine("FM_Agreement = type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE");
    			MFMAgreement agreement = (MFMAgreement) po;
    			if(agreement.isProcessed()) {
    				return null;
    			}
    			//	Create Product
    			if(agreement.getFM_Product_ID() != 0) {
        			//	get all account
        			List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
        			MFMAccount account = null;
        			if (accounts.isEmpty()){
    	    			account = new MFMAccount(agreement);
    	    			account.saveEx();
        			} else {
        				account = accounts.get(0);
        			}
    				MFMAccountProduct accountProduct = new MFMAccountProduct(account);
    				accountProduct.setFM_Product_ID(agreement.getFM_Product_ID());
    				accountProduct.setValidFrom(agreement.getDateDoc());
    				accountProduct.saveEx();
    			}
    		} else if(type == TYPE_AFTER_DELETE) {
    			log.fine("FM_Agreement = type == TYPE_AFTER_DELETE");
    			MFMAgreement agreement = (MFMAgreement) po;
    			if(agreement.isProcessed()) {
    				return null;
    			}
    			//	get all account
    			List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
    			for(MFMAccount account : accounts) {
    				account.deleteEx(true);
    			}
    		}
    	} else if(po.get_TableName().equals(I_C_Invoice.Table_Name)) {
    		if(type == TYPE_AFTER_CHANGE) {
    			log.fine("C_Invoice = type == TYPE_AFTER_CHANGE");
    			MInvoice invoice = (MInvoice) po;
    			if(invoice.is_ValueChanged(I_C_Invoice.COLUMNNAME_IsPaid)) {
    				boolean isPaid = invoice.isPaid();
    				if(!invoice.getDocStatus().equals(MInvoice.DOCACTION_Complete)
    						&& !invoice.getDocStatus().equals(MInvoice.DOCACTION_Close)) {
    					isPaid = false;
    				}
    				for(MInvoiceLine line : invoice.getLines()) {
    					if(line.get_ValueAsInt("FM_Amortization_ID") != 0) {
    						MFMAmortization amortization = new MFMAmortization(invoice.getCtx(), line.get_ValueAsInt("FM_Amortization_ID"), invoice.get_TrxName());
    						amortization.setIsPaid(isPaid);
    						amortization.saveEx();
    					}
    				}
    			}
    		}
    	}
        return null;
    }

    /**
     * Document Validate for Standard Request Type
     * @param entity
     * @param timing see TIMING_ constants
     * @return
     */
    public String docValidate(PO entity, int timing) {
    	if (entity instanceof MPaySelection) {
			if (timing == TIMING_BEFORE_COMPLETE) {
				MPaySelection paymentSelection = (MPaySelection) entity;
				String sql = new String("SELECT ag.FM_Agreement_ID, ag.DocumentNo "
						+ "FROM FM_Agreement ag "
						+ "INNER JOIN FM_Account ac ON(ac.FM_Agreement_ID = ag.FM_Agreement_ID) "
						+ "INNER JOIN (SELECT pl.FM_Account_ID, SUM(pl.AmtSource) AmtSource "
						+ "FROM C_PaySelectionLine pl "
						+ "WHERE EXISTS(SELECT 1 FROM C_PaySelection ps "
						+ "				WHERE ps.C_PaySelection_ID = pl.C_PaySelection_ID "
						+ "				AND ps.DocStatus IN('CO')) "
						+ "				GROUP BY pl.FM_Account_ID) ps ON(ps.FM_Account_ID = ac.FM_Account_ID) "
						+ "WHERE ac.CapitalAmt = COALESCE(ps.AmtSource, 0) "
						+ "AND EXISTS(SELECT 1 FROM C_PaySelectionLine pl "
						+ "WHERE pl.FM_Account_ID = ac.FM_Account_ID "
						+ "AND pl.C_PaySelection_ID = ?)");
				//	Get From DB
				KeyNamePair[] loanArray = DB.getKeyNamePairs(paymentSelection.get_TrxName(), sql, false, paymentSelection.getC_PaySelection_ID());
				if(loanArray != null
						&& loanArray.length > 0) {
					StringBuffer msg = new StringBuffer();
					for(KeyNamePair loan : loanArray) {
						if(msg.length() > 0) {
							msg.append(",").append(Env.NL);
						}
						//	Add Message
						msg.append(loan.getName());
					}
					//	Get Message
					if(msg.length() > 0) {
						throw new AdempiereException("@Loan@ @Processed@ [" + msg.toString() + "]");
					}
				}
			}
		} else if (entity instanceof MPayment) {
			if (timing == TIMING_AFTER_COMPLETE
					|| timing == TIMING_AFTER_REVERSECORRECT
					|| timing == TIMING_AFTER_REVERSEACCRUAL
					|| timing == TIMING_AFTER_VOID) {
				MPayment payment = (MPayment) entity;
				if(timing == TIMING_AFTER_COMPLETE
						&& payment.getReversal_ID() != 0) {
					return null;
				}
				String whereClause = new String("EXISTS(SELECT 1 "
						+ "FROM C_PaySelectionLine pl "
						+ "INNER JOIN C_PaySelectionCheck pc ON(pc.C_PaySelectionCheck_ID = pl.C_PaySelectionCheck_ID) "
						+ "WHERE pl.FM_Account_ID = FM_Account.FM_Account_ID "
						+ "AND pc.C_Payment_ID = ?)");
				//	Get Financial Account from Payment
				List<MFMAccount> accountList = new Query(payment.getCtx(), I_FM_Account.Table_Name, whereClause, payment.get_TrxName())
					.setParameters(payment.getC_Payment_ID())
					.list();
				//	Mark it
				if(accountList != null
						&& accountList.size() > 0) {
					accountList.stream().forEach(account -> {
						account.set_ValueOfColumn("IsPaid", timing == TIMING_AFTER_COMPLETE);
						account.saveEx();
					});
				}
			}
		} else if (entity instanceof MFMAgreement) {
			MFMAgreement agreement = (MFMAgreement) entity;
			if (timing == TIMING_BEFORE_COMPLETE) {
				if(!agreement.isSOTrx()) {
					return null;
				}
				//	Get Loan on Dunning
				String sql = new String("SELECT la.DocumentNo, SUM(COALESCE(la.CurrentDunningAmt, 0)) AS CurrentDunningAmt "
						+ "FROM RV_FM_LoanAmortization la "
						+ "WHERE DocStatus IN('CO') "
						+ "AND C_BPartner_ID = ? "
						+ "AND la.IsSOTrx = 'Y' "
						+ "AND (la.IsPaid = 'N' OR la.IsInvoiced = 'N') "
						+ "GROUP BY la.DocumentNo "
						+ "HAVING(SUM(COALESCE(la.CurrentDunningAmt, 0)) > 0)");
				//	Query
				List<Object> params = new ArrayList<>();
				params.add(agreement.getC_BPartner_ID());
				ValueNamePair[] pairs = DB.getValueNamePairs(sql, false, params);
				if(pairs != null
						&& pairs.length > 0) {
					StringBuffer resultToShow = new StringBuffer(Msg.getMsg(agreement.getCtx(), "Loan.DunningBalanceOpen"));
					String loanMsg = Msg.getMsg(agreement.getCtx(), "Loan");
					for(ValueNamePair pair : pairs) {
						if(Util.isEmpty(pair.getName())) {
							continue;
						}
						resultToShow.append(Env.NL);
						//	
						BigDecimal dunningAmount = new BigDecimal(pair.getName());
						resultToShow.append("- " + loanMsg + ": " + pair.getID() 
											+ " = " + DisplayType.getNumberFormat(DisplayType.Amount).format(dunningAmount));
					}
					//	Set last result
					agreement.set_ValueOfColumn("LastResult", resultToShow.toString());
					agreement.saveEx();
				}
			} else if (timing == TIMING_AFTER_COMPLETE) {
				//	Get values from amortization
				List<MFMAccount> accountList = agreement.getAccounts();
				if(accountList != null
						&& !accountList.isEmpty()) {
					BigDecimal capitalAmt = Env.ZERO;
					BigDecimal interestAmt = Env.ZERO;
					BigDecimal taxAmt = Env.ZERO;
					for(MFMAccount account : accountList) {
						List<MFMAmortization> amortizationList = MFMAmortization
								.getFromAccount(account.getFM_Account_ID(), account.get_TrxName());
						//	Iterate
						for(MFMAmortization amortization : amortizationList) {
							//	Capital Amount
							if(amortization.getCapitalAmt() != null) {
								capitalAmt = capitalAmt.add(amortization.getCapitalAmt());
							}
							//	Interest Amount
							if(amortization.getInterestAmt() != null) {
								interestAmt = interestAmt.add(amortization.getInterestAmt());
							}
							//	Tax Amount
							if(amortization.getTaxAmt() != null) {
								taxAmt = taxAmt.add(amortization.getTaxAmt());
							}
						}
						//	Set Capital
						if(account.get_Value("CapitalAmt") == null
								|| ((BigDecimal) account.get_Value("CapitalAmt")).equals(Env.ZERO)) {
							account.set_ValueOfColumn("CapitalAmt", capitalAmt);
						}
						//	Set Interest
						if(account.get_Value("InterestAmt") == null
								|| ((BigDecimal) account.get_Value("InterestAmt")).equals(Env.ZERO)) {
							account.set_ValueOfColumn("InterestAmt", interestAmt);
						}
						//	Set Tax
						if(account.get_Value("TaxAmt") == null
								|| ((BigDecimal) account.get_Value("TaxAmt")).equals(Env.ZERO)) {
							account.set_ValueOfColumn("TaxAmt", taxAmt);
						}
						account.saveEx();	
					}
				}
			} else if(timing == TIMING_BEFORE_REVERSECORRECT
					|| timing == TIMING_BEFORE_REVERSEACCRUAL
					|| timing == TIMING_BEFORE_VOID
					|| timing == TIMING_BEFORE_REACTIVATE) {
				//	Get values from amortization
				List<MFMAccount> accountList = agreement.getAccounts();
				StringBuffer inClause = new StringBuffer();
				if(accountList != null
						&& !accountList.isEmpty()) {
					for(MFMAccount account : accountList) {
						if(inClause.length() > 0) {
							inClause.append(", ");
						}
						//	Add
						inClause.append(account.getFM_Account_ID());
					}
				}
				//	Validate and get result
				if(inClause.length() > 0) {
					//	For Payment Selection
					String sql = new String("SELECT ps.C_PaySelection_ID, ps.DocumentNo "
							+ "FROM C_PaySelection ps "
							+ "INNER JOIN C_PaySelectionLine pl ON(pl.C_PaySelection_ID = ps.C_PaySelection_ID) "
							+ "WHERE ps.DocStatus IN('CO', 'CL') "
							+ "AND pl.FM_Account_ID IN(" + inClause.toString() + ")");
					KeyNamePair[] paySelectionResult = DB.getKeyNamePairs(agreement.get_TrxName(), sql, false);
					//	For Payment
					sql = new String("SELECT p.C_Payment_ID, p.DocumentNo "
							+ "FROM C_PaySelectionLine pl "
							+ "INNER JOIN C_PaySelectionCheck pc ON(pc.C_PaySelectionCheck_ID = pl.C_PaySelectionCheck_ID) "
							+ "INNER JOIN C_Payment p ON(p.C_Payment_ID = pc.C_Payment_ID) "
							+ "WHERE p.DocStatus IN('CO', 'CL') "
							+ "AND EXISTS(SELECT 1 FROM C_PaySelection ps "
							+ "				WHERE ps.C_PaySelection_ID = pl.C_PaySelection_ID"
							+ "				AND ps.DocStatus IN('CO', 'CL')) "
							+ "AND pl.FM_Account_ID IN(" + inClause.toString() + ")");
					KeyNamePair[] paymentResult = DB.getKeyNamePairs(agreement.get_TrxName(), sql, false);
					//	For Invoice
					sql = new String("SELECT i.C_Invoice_ID, i.DocumentNo "
							+ "FROM C_Invoice i "
							+ "WHERE i.DocStatus IN('CO', 'CL') "
							+ "AND i.FM_Account_ID IN(" + inClause.toString() + ")");
					KeyNamePair[] invoiceResult = DB.getKeyNamePairs(agreement.get_TrxName(), sql, false);
					//	Show Result for Payment Selection
					StringBuffer resultToShow = new StringBuffer();
					if(paySelectionResult != null
							&& paySelectionResult.length > 0) {
						if(resultToShow.length() > 0) {
							resultToShow.append(Env.NL);
						}
						//	Add result
						resultToShow.append(Msg.getElement(agreement.getCtx(), "C_PaySelection_ID"));
						//	Get result
						for(KeyNamePair result : paySelectionResult) {
							if(resultToShow.length() > 0) {
								resultToShow.append(Env.NL);
							}
							//	Add result
							resultToShow.append(result.getName());
						}
					}
					//	Show Result for Payment
					if(paymentResult != null
							&& paymentResult.length > 0) {
						if(resultToShow.length() > 0) {
							resultToShow.append(Env.NL);
						}
						//	Add result
						resultToShow.append(Msg.getElement(agreement.getCtx(), "C_Payment_ID"));
						//	Get result
						for(KeyNamePair result : paymentResult) {
							if(resultToShow.length() > 0) {
								resultToShow.append(Env.NL);
							}
							//	Add result
							resultToShow.append(result.getName());
						}
					}
					//	Show Result for Invoice
					if(invoiceResult != null
							&& invoiceResult.length > 0) {
						if(resultToShow.length() > 0) {
							resultToShow.append(Env.NL);
						}
						//	Add result
						resultToShow.append(Msg.getElement(agreement.getCtx(), "C_Invoice_ID"));
						//	Get result
						for(KeyNamePair result : invoiceResult) {
							if(resultToShow.length() > 0) {
								resultToShow.append(Env.NL);
							}
							//	Add result
							resultToShow.append(result.getName());
						}
					}
					//	Return result if exists
					if(resultToShow.length() > 0) {
						return Msg.getMsg(agreement.getCtx(), "SQLErrorReferenced") + Env.NL + resultToShow.toString();
					}
				}
			}
		} else if(entity.get_TableName().equals(I_C_Invoice.Table_Name)) {
			MInvoice invoice = (MInvoice) entity;
			int financialAccountId = invoice.getFM_Account_ID();
        	if(financialAccountId <= 0) {
        		return null;
        	}
        	//	For Complete
        	if (timing == TIMING_BEFORE_COMPLETE) {
        		int periodNo = DB.getSQLValue(invoice.get_TrxName(), "SELECT MIN(a.PeriodNo) AS PeriodNo "
        				+ "FROM C_InvoiceLine il "
        				+ "INNER JOIN FM_Amortization a ON(a.FM_Amortization_ID = il.FM_Amortization_ID) "
        				+ "WHERE il.C_Invoice_ID = ?", invoice.getC_Invoice_ID());
        		//	Validate
        		String sql = new String("SELECT la.PeriodNo "
						+ "FROM RV_FM_LoanAmortization la "
						+ "WHERE FM_Account_ID = ? "
						+ "AND la.IsSOTrx = 'Y' "
						+ "AND (la.IsPaid = 'N' OR la.IsInvoiced = 'N') "
						+ "AND la.PeriodNo < ?");
        		//	Get
        		int previousPeriodNo = DB.getSQLValue(invoice.get_TrxName(), sql, financialAccountId, periodNo);
        		//	
        		if(previousPeriodNo > 0) {
        			return Msg.getMsg(invoice.getCtx(), "Loan.PreviousFeeUnpaid") + ": " + previousPeriodNo; 
        		}
        	} else if(timing == TIMING_AFTER_COMPLETE) {
        		boolean isInvoiced = true;
				if(!invoice.getDocStatus().equals(MInvoice.DOCSTATUS_Completed)
						&& !invoice.getDocStatus().equals(MInvoice.DOCSTATUS_Closed)
						&& !invoice.getDocStatus().equals(MInvoice.DOCSTATUS_InProgress)
						|| invoice.getReversal_ID() != 0) {
					isInvoiced = false;
				} else {
					//	For Credit Memo
					MDocType documentType = MDocType.get(invoice.getCtx(), invoice.getC_DocType_ID());
					if(documentType.getDocBaseType().endsWith("C")) {
						isInvoiced = false;
					}
				}
				//	Set All quotes
				for(MInvoiceLine line : invoice.getLines()) {
					if(line.getFM_Amortization_ID() != 0) {
						MFMAmortization amortization = new MFMAmortization(invoice.getCtx(), line.getFM_Amortization_ID(), invoice.get_TrxName());
						amortization.setIsInvoiced(isInvoiced);
						if(!isInvoiced) {
							amortization.setIsPaid(false);
						}
						amortization.saveEx();
					}
				}
        	}
        	//	Get Account
        	MFMAccount account = new MFMAccount(invoice.getCtx(), financialAccountId, invoice.get_TrxName());
        	//	Get Agreement
        	MFMAgreement agreement = (MFMAgreement) account.getFM_Agreement();
        	//	Get Financial Product
        	String processMsg = FinancialSetting.get().fireDocValidate(invoice, timing, agreement.getFM_Product_ID());
			if (processMsg != null) {
				return processMsg;
			}
    	}
        return null;
    }
}
