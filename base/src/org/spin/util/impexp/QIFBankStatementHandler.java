/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util.impexp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.impexp.BankStatementLoaderInterface;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatementLoader;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Class used for Load and parse data from QIF file
 * It class must be implemented for a Bank or type
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1700 ] Add Quicken Interchange Format support
 * @see https://github.com/adempiere/adempiere/issues/1700
 */
public abstract class QIFBankStatementHandler implements BankStatementLoaderInterface {
	/**	Controller for import	*/
	private MBankStatementLoader controller = null;
	/**	List of bank transactions	*/
	private List<QIFBankTransaction> transactionList = new ArrayList<QIFBankTransaction>();
	/**	Current Transaction	*/
	private QIFBankTransaction bankTransaction = null;
	/**	Bank Account	*/
	private MBankAccount bankAccount = null;
	/**	Last Error	*/
	private String lastError = null;
	
	/** Static Logger					*/
	private CLogger	log = CLogger.getCLogger (QIFBankStatementHandler.class);
	
	@Override
	public boolean init(MBankStatementLoader controller) {
		if (controller == null) {
			throw new AdempiereException("@C_BankStatementLoader_ID@ @NotFoud@");
		}
		this.controller = controller;
		bankAccount = MBankAccount.get(controller.getCtx(), controller.getC_BankAccount_ID());
		return controller.getLocalFileName() != null;
	}	//	init
	
	@Override
	public boolean isValid() {
		return controller.getLocalFileName() != null;
	}
	
	@Override
	public boolean loadLines() {
		try {
			FileInputStream stream = null;
			if (controller.getLocalFileName() != null) {
				stream = new FileInputStream(controller.getLocalFileName());
			}
			//	Try to open the file specified as part of the loader configuration
			else if (controller.getFileName() != null) {
				stream = new FileInputStream(controller.getFileName());
			}
			//	
			return readFile(new BufferedReader(new InputStreamReader(stream)));
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			throw new AdempiereException(e.getLocalizedMessage());
		}
	}	//	attachInput
	
	/**
	 * Read file and load data for list
	 * @param reader
	 */
	private boolean readFile(BufferedReader reader) {
		try {
			String line = null;
			bankTransaction = getBankTransactionInstance();
			while ((line = reader.readLine()) != null) {
				//	Add to List
				if(QIFBankTransaction.isEndLine(line)) {
					transactionList.add(bankTransaction);
					//	Save
					if(!controller.saveLine()) {
						log.severe("Bank Statement Loader Error: " + controller.getErrorMessage());
					}
					bankTransaction = getBankTransactionInstance();
					continue;
				}
				//	Set value for transaction
				bankTransaction.parseLine(line);
			}
			//	Close
			reader.close();
		} catch (Exception e) {
			throw new AdempiereException("@ReadError@: " + e.getLocalizedMessage());
		}
		//	Default return
		return transactionList.size() > 0;
	}
	
	/**
	 * Get a new instance of bank transaction
	 * it allow define distint format for each locale
	 * @return
	 */
	protected QIFBankTransaction getBankTransactionInstance() {
		return new GenericQIFBankTransaction();
	}
	
	@Override
	public String getLastErrorMessage() {
		return lastError;
	}

	@Override
	public String getLastErrorDescription() {
		return null;
	}
	
	@Override
	public Timestamp getDateLastRun() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	@Override
	public String getRoutingNo() {
		return bankAccount.getC_Bank().getRoutingNo();
	}
	
	@Override
	public String getBankAccountNo() {
		return bankAccount.getAccountNo();
	}
	
	@Override
	public String getStatementReference() {
		return null;
	}
	
	@Override
	public Timestamp getStatementDate() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	@Override
	public String getTrxID() {
		return bankTransaction.getBankTrxCheckNo();
	}
	
	@Override
	public String getReference() {
		return bankTransaction.getBankTrxCheckNo();
	}
	
	@Override
	public String getCheckNo() {
		return bankTransaction.getBankTrxCheckNo();
	}
	
	@Override
	public String getPayeeName() {
		return null;
	}
	
	@Override
	public String getPayeeAccountNo() {
		return bankTransaction.getBankTrxPayeeAccountNo();
	}
	
	@Override
	public Timestamp getStatementLineDate() {
		return bankTransaction.getBankTrxDate();
	}
	
	@Override
	public Timestamp getValutaDate() {
		return bankTransaction.getBankTrxDate();
	}
	
	@Override
	public String getTrxType() {
		return bankTransaction.getBankTrxCategory();
	}

	@Override
	public boolean getIsReversal() {
		return false;
	}
	
	@Override
	public String getCurrency() {
		return null;
	}
	
	@Override
	public BigDecimal getStmtAmt() {
		return bankTransaction.getBankTrxAmount();
	}
	
	@Override
	public BigDecimal getTrxAmt() {
		return bankTransaction.getBankTrxAmount();
	}
	
	@Override
	public BigDecimal getInterestAmt() {
		return Env.ZERO;
	}
	
	@Override
	public String getMemo() {
		return bankTransaction.getBankTrxMemo();
	}
	
	@Override
	public String getChargeName() {
		return bankTransaction.getBankTrxMemo();
	}
	
	@Override
	public BigDecimal getChargeAmt() {
		return bankTransaction.getBankTrxAmount();
	}
}
