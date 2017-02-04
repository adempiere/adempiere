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

package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
/** Generated Process for (Receivables Write-Off)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InvoiceWriteOffAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_Invoice_WriteOff";
	/** Process Name 	*/
	private static final String NAME = "Receivables Write-Off";
	/** Process Id 	*/
	private static final int ID = 171;
 
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for C_BP_Group_ID	*/
	public static final String C_BP_Group_ID = "C_BP_Group_ID";
	/**	Parameter Name for C_Invoice_ID	*/
	public static final String C_Invoice_ID = "C_Invoice_ID";
	/**	Parameter Name for InvoiceCollectionType	*/
	public static final String InvoiceCollectionType = "InvoiceCollectionType";
	/**	Parameter Name for C_DunningLevel_ID	*/
	public static final String C_DunningLevel_ID = "C_DunningLevel_ID";
	/**	Parameter Name for MaxInvWriteOffAmt	*/
	public static final String MaxInvWriteOffAmt = "MaxInvWriteOffAmt";
	/**	Parameter Name for APAR	*/
	public static final String APAR = "APAR";
	/**	Parameter Name for DateInvoiced	*/
	public static final String DateInvoiced = "DateInvoiced";
	/**	Parameter Name for IsSimulation	*/
	public static final String IsSimulation = "IsSimulation";
	/**	Parameter Name for CreatePayment	*/
	public static final String CreatePayment = "CreatePayment";
	/**	Parameter Name for DateAcct	*/
	public static final String DateAcct = "DateAcct";
	/**	Parameter Name for C_BankAccount_ID	*/
	public static final String C_BankAccount_ID = "C_BankAccount_ID";

	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for businessPartnerGroupId	*/
	private int businessPartnerGroupId;
	/**	Parameter Value for invoiceId	*/
	private int invoiceId;
	/**	Parameter Value for collectionStatus	*/
	private String collectionStatus;
	/**	Parameter Value for dunningLevelId	*/
	private int dunningLevelId;
	/**	Parameter Value for maximumwrite-offperInvoice	*/
	private BigDecimal maximumInvoiceWriteOffperInvoice;
	/**	Parameter Value for aP-AR	*/
	private String accountPayableAndReceivable;
	/**	Parameter Value for dateInvoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for dateInvoicedTo	*/
	private Timestamp dateInvoicedTo;
	/**	Parameter Value for ismulation	*/
	private boolean isSimulation;
	/**	Parameter Value for iseatePayment	*/
	private boolean isCreatePayment;
	/**	Parameter Value for accountDate	*/
	private Timestamp accountDate;
	/**	Parameter Value for bankAccountId	*/
	private int bankAccountId;
 

	@Override
	protected void prepare()
	{
		organizationId = getParameterAsInt(AD_Org_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		businessPartnerGroupId = getParameterAsInt(C_BP_Group_ID);
		invoiceId = getParameterAsInt(C_Invoice_ID);
		collectionStatus = getParameterAsString(InvoiceCollectionType);
		dunningLevelId = getParameterAsInt(C_DunningLevel_ID);
		maximumInvoiceWriteOffperInvoice = getParameterAsBigDecimal(MaxInvWriteOffAmt);
		accountPayableAndReceivable = getParameterAsString(APAR);
		dateInvoiced = getParameterAsTimestamp(DateInvoiced);
		dateInvoicedTo = getParameterToAsTimestamp(DateInvoiced);
		isSimulation = getParameterAsBoolean(IsSimulation);
		isCreatePayment = getParameterAsBoolean(CreatePayment);
		accountDate = getParameterAsTimestamp(DateAcct);
		bankAccountId = getParameterAsInt(C_BankAccount_ID);
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for businessPartnerGroupId	*/
	protected int getBusinessPartnerGroupId() {
		return businessPartnerGroupId;
	}

	/**	 Getter Parameter Value for invoiceId	*/
	protected int getInvoiceId() {
		return invoiceId;
	}

	/**	 Getter Parameter Value for collectionStatus	*/
	protected String getCollectionStatus() {
		return collectionStatus;
	}

	/**	 Getter Parameter Value for dunningLevelId	*/
	protected int getDunningLevelId() {
		return dunningLevelId;
	}

	/**	 Getter Parameter Value for maximumwrite-offperInvoice	*/
	protected BigDecimal getMaximumWriteOffperInvoice() {
		return maximumInvoiceWriteOffperInvoice;
	}

	/**	 Getter Parameter Value for aP-AR	*/
	protected String getAccountPayableAndReceivable() {
		return accountPayableAndReceivable;
	}

	/**	 Getter Parameter Value for dateInvoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Getter Parameter Value for dateInvoicedTo	*/
	protected Timestamp getDateInvoicedTo() {
		return dateInvoicedTo;
	}

	/**	 Getter Parameter Value for ismulation	*/
	protected boolean isSimulation() {
		return isSimulation;
	}

	/**	 Getter Parameter Value for iseatePayment	*/
	protected boolean isCreatePayment() {
		return isCreatePayment;
	}

	/**	 Getter Parameter Value for accountDate	*/
	protected Timestamp getAccountDate() {
		return accountDate;
	}

	/**	 Getter Parameter Value for bankAccountId	*/
	protected int getBankAccountId() {
		return bankAccountId;
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