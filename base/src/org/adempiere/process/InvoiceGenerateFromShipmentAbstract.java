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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Generate Invoices from Shipments)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InvoiceGenerateFromShipmentAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_Invoice_Generate_from_Shipment";
	/** Process Name 	*/
	private static final String NAME = "Generate Invoices from Shipments";
	/** Process Id 	*/
	private static final int ID = 53345;
 
	/**	Parameter Name for DateInvoiced	*/
	public static final String DateInvoiced = "DateInvoiced";
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for M_InOut_ID	*/
	public static final String M_InOut_ID = "M_InOut_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for DocAction	*/
	public static final String DocAction = "DocAction";
	/**	Parameter Name for ConsolidateDocument	*/
	public static final String ConsolidateDocument = "ConsolidateDocument";
	/**	Parameter Name for AD_OrgTrx_ID	*/
	public static final String AD_OrgTrx_ID = "AD_OrgTrx_ID";
	/**	Parameter Name for IsAddInvoiceReferenceLine	*/
	public static final String IsAddInvoiceReferenceLine = "IsAddInvoiceReferenceLine";

	/**	Parameter Value for dateInvoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for shipmentReceiptId	*/
	private int shipmentReceiptId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for documentAction	*/
	private String documentAction;
	/**	Parameter Value for isConsolidatetooneDocument	*/
	private boolean isConsolidatetooneDocument;
	/**	Parameter Value for trxOrganizationId	*/
	private int trxOrganizationId;
	/**	Parameter Value for isAddInvoiceReferenceLine	*/
	private boolean isAddInvoiceReferenceLine;
 

	@Override
	protected void prepare()
	{
		dateInvoiced = getParameterAsTimestamp(DateInvoiced);
		organizationId = getParameterAsInt(AD_Org_ID);
		shipmentReceiptId = getParameterAsInt(M_InOut_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		documentAction = getParameterAsString(DocAction);
		isConsolidatetooneDocument = getParameterAsBoolean(ConsolidateDocument);
		trxOrganizationId = getParameterAsInt(AD_OrgTrx_ID);
		isAddInvoiceReferenceLine = getParameterAsBoolean(IsAddInvoiceReferenceLine);
	}

	/**	 Getter Parameter Value for dateInvoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for shipmentReceiptId	*/
	protected int getShipmentReceiptId() {
		return shipmentReceiptId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for documentAction	*/
	protected String getDocumentAction() {
		return documentAction;
	}

	/**	 Getter Parameter Value for isConsolidatetooneDocument	*/
	protected boolean isConsolidatetooneDocument() {
		return isConsolidatetooneDocument;
	}

	/**	 Getter Parameter Value for trxOrganizationId	*/
	protected int getTrxOrganizationId() {
		return trxOrganizationId;
	}

	/**	 Getter Parameter Value for isAddInvoiceReferenceLine	*/
	protected boolean isAddInvoiceReferenceLine() {
		return isAddInvoiceReferenceLine;
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