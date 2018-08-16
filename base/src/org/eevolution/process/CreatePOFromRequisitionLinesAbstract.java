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

package org.eevolution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Create PO from Requisition Lines)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CreatePOFromRequisitionLinesAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Requisition_Create_PO_From_Requisition";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create PO from Requisition Lines";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54117;
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Priority	*/
	public static final String PRIORITYRULE = "PriorityRule";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Partner Location	*/
	public static final String C_BPARTNER_LOCATION_ID = "C_BPartner_Location_ID";
	/**	Parameter Name for Date OrderedC_Order	*/
	public static final String DATEORDERED = "DateOrdered";
	/**	Parameter Name for Date Promised	*/
	public static final String DATEPROMISED = "DatePromised";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Name for Payment Term	*/
	public static final String C_PAYMENTTERM_ID = "C_PaymentTerm_ID";
	/**	Parameter Name for Price List	*/
	public static final String M_PRICELIST_ID = "M_PriceList_ID";
	/**	Parameter Name for Order Reference	*/
	public static final String POREFERENCE = "POReference";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Consolidate to one Document	*/
	public static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Priority	*/
	private String priorityRule;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Partner Location	*/
	private int bPartnerLocationId;
	/**	Parameter Value for Date OrderedC_Order	*/
	private Timestamp dateOrdered;
	/**	Parameter Value for Date Promised	*/
	private Timestamp datePromised;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;
	/**	Parameter Value for Payment Term	*/
	private int paymentTermId;
	/**	Parameter Value for Price List	*/
	private int priceListId;
	/**	Parameter Value for Order Reference	*/
	private String pOReference;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;

	@Override
	protected void prepare() {
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		priorityRule = getParameterAsString(PRIORITYRULE);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		bPartnerLocationId = getParameterAsInt(C_BPARTNER_LOCATION_ID);
		dateOrdered = getParameterAsTimestamp(DATEORDERED);
		datePromised = getParameterAsTimestamp(DATEPROMISED);
		salesRepId = getParameterAsInt(SALESREP_ID);
		paymentTermId = getParameterAsInt(C_PAYMENTTERM_ID);
		priceListId = getParameterAsInt(M_PRICELIST_ID);
		pOReference = getParameterAsString(POREFERENCE);
		docAction = getParameterAsString(DOCACTION);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriorityRule() {
		return priorityRule;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Partner Location	*/
	protected int getBPartnerLocationId() {
		return bPartnerLocationId;
	}

	/**	 Setter Parameter Value for Partner Location	*/
	protected void setBPartnerLocationId(int bPartnerLocationId) {
		this.bPartnerLocationId = bPartnerLocationId;
	}

	/**	 Getter Parameter Value for Date OrderedC_Order	*/
	protected Timestamp getDateOrdered() {
		return dateOrdered;
	}

	/**	 Setter Parameter Value for Date OrderedC_Order	*/
	protected void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	/**	 Getter Parameter Value for Date Promised	*/
	protected Timestamp getDatePromised() {
		return datePromised;
	}

	/**	 Setter Parameter Value for Date Promised	*/
	protected void setDatePromised(Timestamp datePromised) {
		this.datePromised = datePromised;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
	}

	/**	 Getter Parameter Value for Payment Term	*/
	protected int getPaymentTermId() {
		return paymentTermId;
	}

	/**	 Setter Parameter Value for Payment Term	*/
	protected void setPaymentTermId(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	/**	 Getter Parameter Value for Price List	*/
	protected int getPriceListId() {
		return priceListId;
	}

	/**	 Setter Parameter Value for Price List	*/
	protected void setPriceListId(int priceListId) {
		this.priceListId = priceListId;
	}

	/**	 Getter Parameter Value for Order Reference	*/
	protected String getPOReference() {
		return pOReference;
	}

	/**	 Setter Parameter Value for Order Reference	*/
	protected void setPOReference(String pOReference) {
		this.pOReference = pOReference;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Consolidate to one Document	*/
	protected boolean isConsolidateDocument() {
		return isConsolidateDocument;
	}

	/**	 Setter Parameter Value for Consolidate to one Document	*/
	protected void setConsolidateDocument(boolean isConsolidateDocument) {
		this.isConsolidateDocument = isConsolidateDocument;
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