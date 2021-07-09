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

package org.spin.report;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Open Items To Date)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class OpenItemToDateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "OpenItemToDate";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Open Items To Date";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53998;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Sales Transaction	*/
	public static final String ISSOTRX = "IsSOTrx";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Payment Term	*/
	public static final String C_PAYMENTTERM_ID = "C_PaymentTerm_ID";
	/**	Parameter Name for Collection Status	*/
	public static final String INVOICECOLLECTIONTYPE = "InvoiceCollectionType";
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Due Date	*/
	public static final String DUEDATE = "DueDate";
	/**	Parameter Name for Days due	*/
	public static final String DAYSDUE = "DaysDue";
	/**	Parameter Name for Date To	*/
	public static final String DATETO = "DateTo";
	/**	Parameter Name for Order	*/
	public static final String C_ORDER_ID = "C_Order_ID";
	/**	Parameter Name for Invoice	*/
	public static final String C_INVOICE_ID = "C_Invoice_ID";
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Campaign	*/
	public static final String C_CAMPAIGN_ID = "C_Campaign_ID";
	/**	Parameter Name for Activity	*/
	public static final String C_ACTIVITY_ID = "C_Activity_ID";
	/**	Parameter Name for Sales Group	*/
	public static final String C_BP_SALESGROUP_ID = "C_BP_SalesGroup_ID";
	/**	Parameter Name for Account Type	*/
	public static final String C_BP_ACCOUNTTYPE_ID = "C_BP_AccountType_ID";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Price List	*/
	public static final String M_PRICELIST_ID = "M_PriceList_ID";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Name for Segment	*/
	public static final String C_BP_SEGMENT_ID = "C_BP_Segment_ID";
	/**	Parameter Name for Industry Type	*/
	public static final String C_BP_INDUSTRYTYPE_ID = "C_BP_IndustryType_ID";
	/**	Parameter Name for Sales Region	*/
	public static final String C_SALESREGION_ID = "C_SalesRegion_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Sales Transaction	*/
	private boolean isSOTrx;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Payment Term	*/
	private int paymentTermId;
	/**	Parameter Value for Collection Status	*/
	private String invoiceCollectionType;
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Date Invoiced(To)	*/
	private Timestamp dateInvoicedTo;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Account Date(To)	*/
	private Timestamp dateAcctTo;
	/**	Parameter Value for Due Date	*/
	private Timestamp dueDate;
	/**	Parameter Value for Days due	*/
	private int daysDue;
	/**	Parameter Value for Days due(To)	*/
	private int daysDueTo;
	/**	Parameter Value for Date To	*/
	private Timestamp dateTo;
	/**	Parameter Value for Order	*/
	private int orderId;
	/**	Parameter Value for Invoice	*/
	private int invoiceId;
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Campaign	*/
	private int campaignId;
	/**	Parameter Value for Activity	*/
	private int activityId;
	/**	Parameter Value for Sales Group	*/
	private int bPSalesGroupId;
	/**	Parameter Value for Account Type	*/
	private int bPAccountTypeId;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Price List	*/
	private int priceListId;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;
	/**	Parameter Value for Segment	*/
	private int bPSegmentId;
	/**	Parameter Value for Industry Type	*/
	private int bPIndustryTypeId;
	/**	Parameter Value for Sales Region	*/
	private int salesRegionId;
	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		isSOTrx = getParameterAsBoolean(ISSOTRX);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		paymentTermId = getParameterAsInt(C_PAYMENTTERM_ID);
		invoiceCollectionType = getParameterAsString(INVOICECOLLECTIONTYPE);
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateInvoicedTo = getParameterToAsTimestamp(DATEINVOICED);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		dateAcctTo = getParameterToAsTimestamp(DATEACCT);
		dueDate = getParameterAsTimestamp(DUEDATE);
		daysDue = getParameterAsInt(DAYSDUE);
		daysDueTo = getParameterToAsInt(DAYSDUE);
		dateTo = getParameterAsTimestamp(DATETO);
		orderId = getParameterAsInt(C_ORDER_ID);
		invoiceId = getParameterAsInt(C_INVOICE_ID);
		projectId = getParameterAsInt(C_PROJECT_ID);
		campaignId = getParameterAsInt(C_CAMPAIGN_ID);
		activityId = getParameterAsInt(C_ACTIVITY_ID);
		bPSalesGroupId = getParameterAsInt(C_BP_SALESGROUP_ID);
		bPAccountTypeId = getParameterAsInt(C_BP_ACCOUNTTYPE_ID);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		priceListId = getParameterAsInt(M_PRICELIST_ID);
		salesRepId = getParameterAsInt(SALESREP_ID);
		bPSegmentId = getParameterAsInt(C_BP_SEGMENT_ID);
		bPIndustryTypeId = getParameterAsInt(C_BP_INDUSTRYTYPE_ID);
		salesRegionId = getParameterAsInt(C_SALESREGION_ID);
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
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

	/**	 Getter Parameter Value for Sales Transaction	*/
	protected boolean isSOTrx() {
		return isSOTrx;
	}

	/**	 Setter Parameter Value for Sales Transaction	*/
	protected void setIsSOTrx(boolean isSOTrx) {
		this.isSOTrx = isSOTrx;
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Payment Term	*/
	protected int getPaymentTermId() {
		return paymentTermId;
	}

	/**	 Setter Parameter Value for Payment Term	*/
	protected void setPaymentTermId(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	/**	 Getter Parameter Value for Collection Status	*/
	protected String getInvoiceCollectionType() {
		return invoiceCollectionType;
	}

	/**	 Setter Parameter Value for Collection Status	*/
	protected void setInvoiceCollectionType(String invoiceCollectionType) {
		this.invoiceCollectionType = invoiceCollectionType;
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

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Account Date(To)	*/
	protected Timestamp getDateAcctTo() {
		return dateAcctTo;
	}

	/**	 Setter Parameter Value for Account Date(To)	*/
	protected void setDateAcctTo(Timestamp dateAcctTo) {
		this.dateAcctTo = dateAcctTo;
	}

	/**	 Getter Parameter Value for Due Date	*/
	protected Timestamp getDueDate() {
		return dueDate;
	}

	/**	 Setter Parameter Value for Due Date	*/
	protected void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	/**	 Getter Parameter Value for Days due	*/
	protected int getDaysDue() {
		return daysDue;
	}

	/**	 Setter Parameter Value for Days due	*/
	protected void setDaysDue(int daysDue) {
		this.daysDue = daysDue;
	}

	/**	 Getter Parameter Value for Days due(To)	*/
	protected int getDaysDueTo() {
		return daysDueTo;
	}

	/**	 Setter Parameter Value for Days due(To)	*/
	protected void setDaysDueTo(int daysDueTo) {
		this.daysDueTo = daysDueTo;
	}

	/**	 Getter Parameter Value for Date To	*/
	protected Timestamp getDateTo() {
		return dateTo;
	}

	/**	 Setter Parameter Value for Date To	*/
	protected void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	/**	 Getter Parameter Value for Order	*/
	protected int getOrderId() {
		return orderId;
	}

	/**	 Setter Parameter Value for Order	*/
	protected void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**	 Getter Parameter Value for Invoice	*/
	protected int getInvoiceId() {
		return invoiceId;
	}

	/**	 Setter Parameter Value for Invoice	*/
	protected void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Campaign	*/
	protected int getCampaignId() {
		return campaignId;
	}

	/**	 Setter Parameter Value for Campaign	*/
	protected void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	/**	 Getter Parameter Value for Activity	*/
	protected int getActivityId() {
		return activityId;
	}

	/**	 Setter Parameter Value for Activity	*/
	protected void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/**	 Getter Parameter Value for Sales Group	*/
	protected int getBPSalesGroupId() {
		return bPSalesGroupId;
	}

	/**	 Setter Parameter Value for Sales Group	*/
	protected void setBPSalesGroupId(int bPSalesGroupId) {
		this.bPSalesGroupId = bPSalesGroupId;
	}

	/**	 Getter Parameter Value for Account Type	*/
	protected int getBPAccountTypeId() {
		return bPAccountTypeId;
	}

	/**	 Setter Parameter Value for Account Type	*/
	protected void setBPAccountTypeId(int bPAccountTypeId) {
		this.bPAccountTypeId = bPAccountTypeId;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Price List	*/
	protected int getPriceListId() {
		return priceListId;
	}

	/**	 Setter Parameter Value for Price List	*/
	protected void setPriceListId(int priceListId) {
		this.priceListId = priceListId;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
	}

	/**	 Getter Parameter Value for Segment	*/
	protected int getBPSegmentId() {
		return bPSegmentId;
	}

	/**	 Setter Parameter Value for Segment	*/
	protected void setBPSegmentId(int bPSegmentId) {
		this.bPSegmentId = bPSegmentId;
	}

	/**	 Getter Parameter Value for Industry Type	*/
	protected int getBPIndustryTypeId() {
		return bPIndustryTypeId;
	}

	/**	 Setter Parameter Value for Industry Type	*/
	protected void setBPIndustryTypeId(int bPIndustryTypeId) {
		this.bPIndustryTypeId = bPIndustryTypeId;
	}
	
	/**	 Getter Parameter Value for Sales Region	*/
	protected int getSalesRegionId() {
		return salesRegionId;
	}

	/**	 Setter Parameter Value for Sales Region	*/
	protected void setSalesRegionId(int salesRegionId) {
		this.salesRegionId = salesRegionId;
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