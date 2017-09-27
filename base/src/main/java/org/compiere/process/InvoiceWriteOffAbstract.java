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

import java.math.BigDecimal;
import java.sql.Timestamp;

/** Generated Process for (Receivables Write-Off)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InvoiceWriteOffAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Invoice_WriteOff";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Receivables Write-Off";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 171;
	/**	Parameter Name for Organization	*/
	private static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Business Partner 	*/
	private static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Business Partner Group	*/
	private static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Invoice	*/
	private static final String C_INVOICE_ID = "C_Invoice_ID";
	/**	Parameter Name for Collection Status	*/
	private static final String INVOICECOLLECTIONTYPE = "InvoiceCollectionType";
	/**	Parameter Name for Dunning Level	*/
	private static final String C_DUNNINGLEVEL_ID = "C_DunningLevel_ID";
	/**	Parameter Name for Maximum write-off per Invoice	*/
	private static final String MAXINVWRITEOFFAMT = "MaxInvWriteOffAmt";
	/**	Parameter Name for AP - AR	*/
	private static final String APAR = "APAR";
	/**	Parameter Name for Date Invoiced	*/
	private static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Simulation	*/
	private static final String ISSIMULATION = "IsSimulation";
	/**	Parameter Name for Create Payment	*/
	private static final String CREATEPAYMENT = "CreatePayment";
	/**	Parameter Name for Account Date	*/
	private static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Bank Account	*/
	private static final String C_BANKACCOUNT_ID = "C_BankAccount_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Invoice	*/
	private int invoiceId;
	/**	Parameter Value for Collection Status	*/
	private String invoiceCollectionType;
	/**	Parameter Value for Dunning Level	*/
	private int dunningLevelId;
	/**	Parameter Value for Maximum write-off per Invoice	*/
	private BigDecimal maxInvWriteOffAmt;
	/**	Parameter Value for AP - AR	*/
	private String aPAR;
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Date Invoiced(To)	*/
	private Timestamp dateInvoicedTo;
	/**	Parameter Value for Simulation	*/
	private boolean isSimulation;
	/**	Parameter Value for Create Payment	*/
	private boolean isCreatePayment;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Bank Account	*/
	private int bankAccountId;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		invoiceId = getParameterAsInt(C_INVOICE_ID);
		invoiceCollectionType = getParameterAsString(INVOICECOLLECTIONTYPE);
		dunningLevelId = getParameterAsInt(C_DUNNINGLEVEL_ID);
		maxInvWriteOffAmt = getParameterAsBigDecimal(MAXINVWRITEOFFAMT);
		aPAR = getParameterAsString(APAR);
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateInvoicedTo = getParameterToAsTimestamp(DATEINVOICED);
		isSimulation = getParameterAsBoolean(ISSIMULATION);
		isCreatePayment = getParameterAsBoolean(CREATEPAYMENT);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		bankAccountId = getParameterAsInt(C_BANKACCOUNT_ID);
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

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Invoice	*/
	protected int getInvoiceId() {
		return invoiceId;
	}

	/**	 Setter Parameter Value for Invoice	*/
	protected void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**	 Getter Parameter Value for Collection Status	*/
	protected String getInvoiceCollectionType() {
		return invoiceCollectionType;
	}

	/**	 Setter Parameter Value for Collection Status	*/
	protected void setInvoiceCollectionType(String invoiceCollectionType) {
		this.invoiceCollectionType = invoiceCollectionType;
	}

	/**	 Getter Parameter Value for Dunning Level	*/
	protected int getDunningLevelId() {
		return dunningLevelId;
	}

	/**	 Setter Parameter Value for Dunning Level	*/
	protected void setDunningLevelId(int dunningLevelId) {
		this.dunningLevelId = dunningLevelId;
	}

	/**	 Getter Parameter Value for Maximum write-off per Invoice	*/
	protected BigDecimal getMaxInvWriteOffAmt() {
		return maxInvWriteOffAmt;
	}

	/**	 Setter Parameter Value for Maximum write-off per Invoice	*/
	protected void setMaxInvWriteOffAmt(BigDecimal maxInvWriteOffAmt) {
		this.maxInvWriteOffAmt = maxInvWriteOffAmt;
	}

	/**	 Getter Parameter Value for AP - AR	*/
	protected String getAPAR() {
		return aPAR;
	}

	/**	 Setter Parameter Value for AP - AR	*/
	protected void setAPAR(String aPAR) {
		this.aPAR = aPAR;
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}

	/**	 Getter Parameter Value for Date Invoiced(To)	*/
	protected Timestamp getDateInvoicedTo() {
		return dateInvoicedTo;
	}

	/**	 Setter Parameter Value for Date Invoiced(To)	*/
	protected void setDateInvoicedTo(Timestamp dateInvoicedTo) {
		this.dateInvoicedTo = dateInvoicedTo;
	}

	/**	 Getter Parameter Value for Simulation	*/
	protected boolean isSimulation() {
		return isSimulation;
	}

	/**	 Setter Parameter Value for Simulation	*/
	protected void setIsSimulation(boolean isSimulation) {
		this.isSimulation = isSimulation;
	}

	/**	 Getter Parameter Value for Create Payment	*/
	protected boolean isCreatePayment() {
		return isCreatePayment;
	}

	/**	 Setter Parameter Value for Create Payment	*/
	protected void setCreatePayment(boolean isCreatePayment) {
		this.isCreatePayment = isCreatePayment;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Bank Account	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Setter Parameter Value for Bank Account	*/
	protected void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
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