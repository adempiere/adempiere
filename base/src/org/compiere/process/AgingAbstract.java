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

package org.compiere.process;

import java.sql.Timestamp;

/** Generated Process for (Aging)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class AgingAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "RV_T_Aging";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Aging";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 238;
	/**	Parameter Name for Statement date	*/
	public static final String STATEMENTDATE = "StatementDate";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Sales Transaction	*/
	public static final String ISSOTRX = "IsSOTrx";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for List Invoices	*/
	public static final String ISLISTINVOICES = "IsListInvoices";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Value for Statement date	*/
	private Timestamp statementDate;
	/**	Parameter Value for Account Date	*/
	private boolean isDateAcct;
	/**	Parameter Value for Sales Transaction	*/
	private boolean isSOTrx;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for List Invoices	*/
	private boolean isListInvoices;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;

	@Override
	protected void prepare() {
		statementDate = getParameterAsTimestamp(STATEMENTDATE);
		isDateAcct = getParameterAsBoolean(DATEACCT);
		isSOTrx = getParameterAsBoolean(ISSOTRX);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		isListInvoices = getParameterAsBoolean(ISLISTINVOICES);
		salesRepId = getParameterAsInt(SALESREP_ID);
	}

	/**	 Getter Parameter Value for Statement date	*/
	protected Timestamp getStatementDate() {
		return statementDate;
	}

	/**	 Setter Parameter Value for Statement date	*/
	protected void setStatementDate(Timestamp statementDate) {
		this.statementDate = statementDate;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected boolean isDateAcct() {
		return isDateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(boolean isDateAcct) {
		this.isDateAcct = isDateAcct;
	}

	/**	 Getter Parameter Value for Sales Transaction	*/
	protected boolean isSOTrx() {
		return isSOTrx;
	}

	/**	 Setter Parameter Value for Sales Transaction	*/
	protected void setIsSOTrx(boolean isSOTrx) {
		this.isSOTrx = isSOTrx;
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

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for List Invoices	*/
	protected boolean isListInvoices() {
		return isListInvoices;
	}

	/**	 Setter Parameter Value for List Invoices	*/
	protected void setIsListInvoices(boolean isListInvoices) {
		this.isListInvoices = isListInvoices;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
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