/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.pos.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;


/** Generated Process for (POS Withdrawal)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class GenerateWithdrawalAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_POS Withdrawal";
	/** Process Name 	*/
	private static final String NAME = "POS Withdrawal";
	/** Process Id 	*/
	private static final int ID = 53846;
 
	/**	Parameter Name for C_POS_ID	*/
	public static final String C_POS_ID = "C_POS_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for DocumentNo	*/
	public static final String DocumentNo = "DocumentNo";
	/**	Parameter Name for Description	*/
	public static final String Description = "Description";
	/**	Parameter Name for DateTrx	*/
	public static final String DateTrx = "DateTrx";
	/**	Parameter Name for DateAcct	*/
	public static final String DateAcct = "DateAcct";
	/**	Parameter Name for C_BankAccount_ID	*/
	public static final String C_BankAccount_ID = "C_BankAccount_ID";
	/**	Parameter Name for CashTransferBankAccount_ID	*/
	public static final String CashTransferBankAccount_ID = "CashTransferBankAccount_ID";
	/**	Parameter Name for C_DocType_ID	*/
	public static final String C_DocType_ID = "C_DocType_ID";
	/**	Parameter Name for Counter_C_DocType_ID	*/
	public static final String Counter_C_DocType_ID = "Counter_C_DocType_ID";
	/**	Parameter Name for C_Charge_ID	*/
	public static final String C_Charge_ID = "C_Charge_ID";
	/**	Parameter Name for C_ConversionType_ID	*/
	public static final String C_ConversionType_ID = "C_ConversionType_ID";
	/**	Parameter Name for C_Currency_ID	*/
	public static final String C_Currency_ID = "C_Currency_ID";

	/**	Parameter Value for pOSTerminalId	*/
	private int pOSTerminalId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for documentNo	*/
	private String documentNo;
	/**	Parameter Value for description	*/
	private String description;
	/**	Parameter Value for transactionDate	*/
	private Timestamp transactionDate;
	/**	Parameter Value for accountDate	*/
	private Timestamp accountDate;
	/**	Parameter Value for bankAccountId	*/
	private int bankAccountId;
	/**	Parameter Value for transferCashtrxtoId	*/
	private int transferCashtrxtoId;
	/**	Parameter Value for documentTypeId	*/
	private int documentTypeId;
	/**	Parameter Value for counterDocumentTypeId	*/
	private int counterDocumentTypeId;
	/**	Parameter Value for chargeId	*/
	private int chargeId;
	/**	Parameter Value for currencyTypeId	*/
	private int currencyTypeId;
	/**	Parameter Value for currencyId	*/
	private int currencyId;
 

	@Override
	protected void prepare()
	{
		pOSTerminalId = getParameterAsInt(C_POS_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		documentNo = getParameterAsString(DocumentNo);
		description = getParameterAsString(Description);
		transactionDate = getParameterAsTimestamp(DateTrx);
		accountDate = getParameterAsTimestamp(DateAcct);
		bankAccountId = getParameterAsInt(C_BankAccount_ID);
		transferCashtrxtoId = getParameterAsInt(CashTransferBankAccount_ID);
		documentTypeId = getParameterAsInt(C_DocType_ID);
		counterDocumentTypeId = getParameterAsInt(Counter_C_DocType_ID);
		chargeId = getParameterAsInt(C_Charge_ID);
		currencyTypeId = getParameterAsInt(C_ConversionType_ID);
		currencyId = getParameterAsInt(C_Currency_ID);
	}

	/**	 Getter Parameter Value for pOSTerminalId	*/
	protected int getPOSTerminalId() {
		return pOSTerminalId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for documentNo	*/
	protected String getDocumentNo() {
		return documentNo;
	}

	/**	 Getter Parameter Value for description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Getter Parameter Value for transactionDate	*/
	protected Timestamp getTransactionDate() {
		return transactionDate;
	}

	/**	 Getter Parameter Value for accountDate	*/
	protected Timestamp getAccountDate() {
		return accountDate;
	}

	/**	 Getter Parameter Value for bankAccountId	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Getter Parameter Value for transferCashtrxtoId	*/
	protected int getTransferCashtrxtoId() {
		return transferCashtrxtoId;
	}

	/**	 Getter Parameter Value for documentTypeId	*/
	protected int getDocumentTypeId() {
		return documentTypeId;
	}

	/**	 Getter Parameter Value for counterDocumentTypeId	*/
	protected int getCounterDocumentTypeId() {
		return counterDocumentTypeId;
	}

	/**	 Getter Parameter Value for chargeId	*/
	protected int getChargeId() {
		return chargeId;
	}

	/**	 Getter Parameter Value for currencyTypeId	*/
	protected int getCurrencyTypeId() {
		return currencyTypeId;
	}

	/**	 Getter Parameter Value for currencyId	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}