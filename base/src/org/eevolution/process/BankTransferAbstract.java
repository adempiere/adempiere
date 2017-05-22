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

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Bank Transfer)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class BankTransferAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_BankStatement BankTransfer";
	/** Process Name 	*/
	private static final String NAME = "Bank Transfer";
	/** Process Id 	*/
	private static final int ID = 53153;
 
	/**	Parameter Name for From_C_BankAccount_ID	*/
	public static final String From_C_BankAccount_ID = "From_C_BankAccount_ID";
	/**	Parameter Name for To_C_BankAccount_ID	*/
	public static final String To_C_BankAccount_ID = "To_C_BankAccount_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for C_Currency_ID	*/
	public static final String C_Currency_ID = "C_Currency_ID";
	/**	Parameter Name for C_ConversionType_ID	*/
	public static final String C_ConversionType_ID = "C_ConversionType_ID";
	/**	Parameter Name for C_Charge_ID	*/
	public static final String C_Charge_ID = "C_Charge_ID";
	/**	Parameter Name for DocumentNo	*/
	public static final String DocumentNo = "DocumentNo";
	/**	Parameter Name for DocumentNoTo	*/
	public static final String DocumentNoTo = "DocumentNoTo";
	/**	Parameter Name for Amount	*/
	public static final String Amount = "Amount";
	/**	Parameter Name for Description	*/
	public static final String Description = "Description";
	/**	Parameter Name for StatementDate	*/
	public static final String StatementDate = "StatementDate";
	/**	Parameter Name for DateAcct	*/
	public static final String DateAcct = "DateAcct";
	/**	Parameter Name for IsAutoReconciled	*/
	public static final String IsAutoReconciled = "IsAutoReconciled";

	/**	Parameter Value for bankAccountFromId	*/
	private int bankAccountFromId;
	/**	Parameter Value for bankAccountToId	*/
	private int bankAccountToId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for currencyId	*/
	private int currencyId;
	/**	Parameter Value for currencyTypeId	*/
	private int currencyTypeId;
	/**	Parameter Value for chargeId	*/
	private int chargeId;
	/**	Parameter Value for documentNo	*/
	private String documentNo;
	/**	Parameter Value for documentNoTo	*/
	private String documentNoTo;
	/**	Parameter Value for amount	*/
	private BigDecimal amount;
	/**	Parameter Value for description	*/
	private String description;
	/**	Parameter Value for statementdate	*/
	private Timestamp statementdate;
	/**	Parameter Value for accountDate	*/
	private Timestamp accountDate;
	/**	Parameter Value for isReconcileAutomatically	*/
	private boolean isReconcileAutomatically;
 

	@Override
	protected void prepare()
	{
		bankAccountFromId = getParameterAsInt(From_C_BankAccount_ID);
		bankAccountToId = getParameterAsInt(To_C_BankAccount_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		currencyId = getParameterAsInt(C_Currency_ID);
		currencyTypeId = getParameterAsInt(C_ConversionType_ID);
		chargeId = getParameterAsInt(C_Charge_ID);
		documentNo = getParameterAsString(DocumentNo);
		documentNoTo = getParameterAsString(DocumentNoTo);
		amount = getParameterAsBigDecimal(Amount);
		description = getParameterAsString(Description);
		statementdate = getParameterAsTimestamp(StatementDate);
		accountDate = getParameterAsTimestamp(DateAcct);
		isReconcileAutomatically = getParameterAsBoolean(IsAutoReconciled);
	}

	/**	 Getter Parameter Value for bankAccountFromId	*/
	protected int getBankAccountFromId() {
		return bankAccountFromId;
	}

	/**	 Getter Parameter Value for bankAccountToId	*/
	protected int getBankAccountToId() {
		return bankAccountToId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for currencyId	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Getter Parameter Value for currencyTypeId	*/
	protected int getCurrencyTypeId() {
		return currencyTypeId;
	}

	/**	 Getter Parameter Value for chargeId	*/
	protected int getChargeId() {
		return chargeId;
	}

	/**	 Getter Parameter Value for documentNo	*/
	protected String getDocumentNo() {
		return documentNo;
	}

	/**	 Getter Parameter Value for documentNoTo	*/
	protected String getDocumentNoTo() {
		return documentNoTo;
	}

	/**	 Getter Parameter Value for amount	*/
	protected BigDecimal getAmount() {
		return amount;
	}

	/**	 Getter Parameter Value for description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Getter Parameter Value for statementdate	*/
	protected Timestamp getStatementdate() {
		return statementdate;
	}

	/**	 Getter Parameter Value for accountDate	*/
	protected Timestamp getAccountDate() {
		return accountDate;
	}

	/**	 Getter Parameter Value for isReconcileAutomatically	*/
	protected boolean isReconcileAutomatically() {
		return isReconcileAutomatically;
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