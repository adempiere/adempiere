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

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;


/** Generated Process for (POS Bank Statement Close)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class CloseStatementPOSAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_POS Bank Statement Close";
	/** Process Name 	*/
	private static final String NAME = "POS Bank Statement Close";
	/** Process Id 	*/
	private static final int ID = 53847;
 
	/**	Parameter Name for C_POS_ID	*/
	public static final String C_POS_ID = "C_POS_ID";
	/**	Parameter Name for C_BankAccount_ID	*/
	public static final String C_BankAccount_ID = "C_BankAccount_ID";
	/**	Parameter Name for DateTrx	*/
	public static final String DateTrx = "DateTrx";
	/**	Parameter Name for LineTotalAmt	*/
	public static final String LineTotalAmt = "LineTotalAmt";
	/**	Parameter Name for PaidAmt	*/
	public static final String PaidAmt = "PaidAmt";
	/**	Parameter Name for OpenAmt	*/
	public static final String OpenAmt = "OpenAmt";
	/**	Parameter Name for DifferenceAmt	*/
	public static final String DifferenceAmt = "DifferenceAmt";
	/**	Parameter Name for IsOverUnderPayment	*/
	public static final String IsOverUnderPayment = "IsOverUnderPayment";
	/**	Parameter Name for C_Charge_ID	*/
	public static final String C_Charge_ID = "C_Charge_ID";

	/**	Parameter Value for pOSTerminalId	*/
	private int pOSTerminalId;
	/**	Parameter Value for bankAccountId	*/
	private int bankAccountId;
	/**	Parameter Value for transactionDate	*/
	private Timestamp transactionDate;
	/**	Parameter Value for transactionDateTo	*/
	private Timestamp transactionDateTo;
	/**	Parameter Value for lineTotal	*/
	private BigDecimal lineTotal;
	/**	Parameter Value for paidAmount	*/
	private BigDecimal paidAmount;
	/**	Parameter Value for openAmount	*/
	private BigDecimal openAmount;
	/**	Parameter Value for difference	*/
	private BigDecimal difference;
	/**	Parameter Value for isOverUnderPayment	*/
	private boolean isOverUnderPayment;
	/**	Parameter Value for chargeId	*/
	private int chargeId;
 

	@Override
	protected void prepare()
	{
		pOSTerminalId = getParameterAsInt(C_POS_ID);
		bankAccountId = getParameterAsInt(C_BankAccount_ID);
		transactionDate = getParameterAsTimestamp(DateTrx);
		transactionDateTo = getParameterToAsTimestamp(DateTrx);
		lineTotal = getParameterAsBigDecimal(LineTotalAmt);
		paidAmount = getParameterAsBigDecimal(PaidAmt);
		openAmount = getParameterAsBigDecimal(OpenAmt);
		difference = getParameterAsBigDecimal(DifferenceAmt);
		isOverUnderPayment = getParameterAsBoolean(IsOverUnderPayment);
		chargeId = getParameterAsInt(C_Charge_ID);
	}

	/**	 Getter Parameter Value for pOSTerminalId	*/
	protected int getPOSTerminalId() {
		return pOSTerminalId;
	}

	/**	 Getter Parameter Value for bankAccountId	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Getter Parameter Value for transactionDate	*/
	protected Timestamp getTransactionDate() {
		return transactionDate;
	}

	/**	 Getter Parameter Value for transactionDateTo	*/
	protected Timestamp getTransactionDateTo() {
		return transactionDateTo;
	}

	/**	 Getter Parameter Value for lineTotal	*/
	protected BigDecimal getLineTotal() {
		return lineTotal;
	}

	/**	 Getter Parameter Value for paidAmount	*/
	protected BigDecimal getPaidAmount() {
		return paidAmount;
	}

	/**	 Getter Parameter Value for openAmount	*/
	protected BigDecimal getOpenAmount() {
		return openAmount;
	}

	/**	 Getter Parameter Value for difference	*/
	protected BigDecimal getDifference() {
		return difference;
	}

	/**	 Getter Parameter Value for isOverUnderPayment	*/
	protected boolean isOverUnderPayment() {
		return isOverUnderPayment;
	}

	/**	 Getter Parameter Value for chargeId	*/
	protected int getChargeId() {
		return chargeId;
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