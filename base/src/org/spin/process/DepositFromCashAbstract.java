/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

package org.spin.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Deposit From Cash Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class DepositFromCashAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBPDepositFromCash";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Deposit From Cash Process";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54014;
	/**	Parameter Name for Transaction Date	*/
	public static final String DATETRX = "DateTrx";
	/**	Parameter Name for Bank Account	*/
	public static final String C_BANKACCOUNT_ID = "C_BankAccount_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Document No	*/
	public static final String DOCUMENTNO = "DocumentNo";
	/**	Parameter Name for Split Deposits	*/
	public static final String ISSPLITDEPOSITS = "IsSplitDeposits";
	/**	Parameter Name for Charge	*/
	public static final String C_CHARGE_ID = "C_Charge_ID";
	/**	Parameter Name for Withdrawal Document Type	*/
	public static final String WITHDRAWALDOCUMENTTYPE_ID = "WithdrawalDocumentType_ID";
	/**	Parameter Name for Deposit Document Type	*/
	public static final String DEPOSITDOCUMENTTYPE_ID = "DepositDocumentType_ID";
	/**	Parameter Value for Transaction Date	*/
	private Timestamp dateTrx;
	/**	Parameter Value for Bank Account	*/
	private int bankAccountId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Document No	*/
	private String documentNo;
	/**	Parameter Value for Split Deposits	*/
	private boolean isSplitDeposits;
	/**	Parameter Value for Charge	*/
	private int chargeId;
	/**	Parameter Value for Withdrawal Document Type	*/
	private int withdrawalDocumentTypeId;
	/**	Parameter Value for Deposit Document Type	*/
	private int depositDocumentTypeId;

	@Override
	protected void prepare() {
		dateTrx = getParameterAsTimestamp(DATETRX);
		bankAccountId = getParameterAsInt(C_BANKACCOUNT_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		documentNo = getParameterAsString(DOCUMENTNO);
		isSplitDeposits = getParameterAsBoolean(ISSPLITDEPOSITS);
		chargeId = getParameterAsInt(C_CHARGE_ID);
		withdrawalDocumentTypeId = getParameterAsInt(WITHDRAWALDOCUMENTTYPE_ID);
		depositDocumentTypeId = getParameterAsInt(DEPOSITDOCUMENTTYPE_ID);
	}

	/**	 Getter Parameter Value for Transaction Date	*/
	protected Timestamp getDateTrx() {
		return dateTrx;
	}

	/**	 Setter Parameter Value for Transaction Date	*/
	protected void setDateTrx(Timestamp dateTrx) {
		this.dateTrx = dateTrx;
	}

	/**	 Getter Parameter Value for Bank Account	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Setter Parameter Value for Bank Account	*/
	protected void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Document No	*/
	protected String getDocumentNo() {
		return documentNo;
	}

	/**	 Setter Parameter Value for Document No	*/
	protected void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	/**	 Getter Parameter Value for Split Deposits	*/
	protected boolean isSplitDeposits() {
		return isSplitDeposits;
	}

	/**	 Setter Parameter Value for Split Deposits	*/
	protected void setIsSplitDeposits(boolean isSplitDeposits) {
		this.isSplitDeposits = isSplitDeposits;
	}

	/**	 Getter Parameter Value for Charge	*/
	protected int getChargeId() {
		return chargeId;
	}

	/**	 Setter Parameter Value for Charge	*/
	protected void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	/**	 Getter Parameter Value for Withdrawal Document Type	*/
	protected int getWithdrawalDocumentTypeId() {
		return withdrawalDocumentTypeId;
	}

	/**	 Setter Parameter Value for Withdrawal Document Type	*/
	protected void setWithdrawalDocumentTypeId(int withdrawalDocumentTypeId) {
		this.withdrawalDocumentTypeId = withdrawalDocumentTypeId;
	}

	/**	 Getter Parameter Value for Deposit Document Type	*/
	protected int getDepositDocumentTypeId() {
		return depositDocumentTypeId;
	}

	/**	 Setter Parameter Value for Deposit Document Type	*/
	protected void setDepositDocumentTypeId(int depositDocumentTypeId) {
		this.depositDocumentTypeId = depositDocumentTypeId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}