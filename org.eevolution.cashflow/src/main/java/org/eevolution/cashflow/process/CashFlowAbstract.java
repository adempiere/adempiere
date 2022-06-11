/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.eevolution.cashflow.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Review CashFlow)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class CashFlowAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "RV_CashFlow";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Review CashFlow";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53165;
	/**	Parameter Name for Due Date	*/
	public static final String DUEDATE = "DueDate";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Include Cash Flow (manual)
					records
					*/
	public static final String ISINCLUDECASHFLOWS = "IsIncludeCashFlows";
	/**	Parameter Name for Include Orders	*/
	public static final String ISINCLUDEORDERS = "IsIncludeOrders";
	/**	Parameter Name for Include Invoices	*/
	public static final String ISINCLUDEINVOICES = "IsIncludeInvoices";
	/**	Parameter Name for Include Bank Balances	*/
	public static final String ISINCLUDEBANKBALANCES = "IsIncludeBankBalances";
	/**	Parameter Value for Due Date	*/
	private Timestamp dueDate;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Include Cash Flow (manual)
					records
					*/
	private boolean isIncludeCashFlows;
	/**	Parameter Value for Include Orders	*/
	private boolean isIncludeOrders;
	/**	Parameter Value for Include Invoices	*/
	private boolean isIncludeInvoices;
	/**	Parameter Value for Include Bank Balances	*/
	private boolean isIncludeBankBalances;

	@Override
	protected void prepare() {
		dueDate = getParameterAsTimestamp(DUEDATE);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		isIncludeCashFlows = getParameterAsBoolean(ISINCLUDECASHFLOWS);
		isIncludeOrders = getParameterAsBoolean(ISINCLUDEORDERS);
		isIncludeInvoices = getParameterAsBoolean(ISINCLUDEINVOICES);
		isIncludeBankBalances = getParameterAsBoolean(ISINCLUDEBANKBALANCES);
	}

	/**	 Getter Parameter Value for Due Date	*/
	protected Timestamp getDueDate() {
		return dueDate;
	}

	/**	 Setter Parameter Value for Due Date	*/
	protected void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Include Cash Flow (manual)
					records
					*/
	protected boolean isIncludeCashFlows() {
		return isIncludeCashFlows;
	}

	/**	 Setter Parameter Value for Include Cash Flow (manual)
					records
					*/
	protected void setIsIncludeCashFlows(boolean isIncludeCashFlows) {
		this.isIncludeCashFlows = isIncludeCashFlows;
	}

	/**	 Getter Parameter Value for Include Orders	*/
	protected boolean isIncludeOrders() {
		return isIncludeOrders;
	}

	/**	 Setter Parameter Value for Include Orders	*/
	protected void setIsIncludeOrders(boolean isIncludeOrders) {
		this.isIncludeOrders = isIncludeOrders;
	}

	/**	 Getter Parameter Value for Include Invoices	*/
	protected boolean isIncludeInvoices() {
		return isIncludeInvoices;
	}

	/**	 Setter Parameter Value for Include Invoices	*/
	protected void setIsIncludeInvoices(boolean isIncludeInvoices) {
		this.isIncludeInvoices = isIncludeInvoices;
	}

	/**	 Getter Parameter Value for Include Bank Balances	*/
	protected boolean isIncludeBankBalances() {
		return isIncludeBankBalances;
	}

	/**	 Setter Parameter Value for Include Bank Balances	*/
	protected void setIsIncludeBankBalances(boolean isIncludeBankBalances) {
		this.isIncludeBankBalances = isIncludeBankBalances;
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