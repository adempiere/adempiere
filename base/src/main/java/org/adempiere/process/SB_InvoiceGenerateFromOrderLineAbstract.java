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

/** Generated Process for (SB_InvoiceGenerateFromOrderLine)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class SB_InvoiceGenerateFromOrderLineAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SB_InvoiceGenerateFromOrderLine";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "SB_InvoiceGenerateFromOrderLine";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 90001;
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Consolidate to one Document	*/
	public static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Trx Organization	*/
	public static final String AD_ORGTRX_ID = "AD_OrgTrx_ID";
	/**	Parameter Name for Add Invoice Reference Line	*/
	public static final String ISADDINVOICEREFERENCELINE = "IsAddInvoiceReferenceLine";
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Trx Organization	*/
	private int orgTrxId;
	/**	Parameter Value for Add Invoice Reference Line	*/
	private boolean isAddInvoiceReferenceLine;

	@Override
	protected void prepare() {
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		docAction = getParameterAsString(DOCACTION);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		orgTrxId = getParameterAsInt(AD_ORGTRX_ID);
		isAddInvoiceReferenceLine = getParameterAsBoolean(ISADDINVOICEREFERENCELINE);
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
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

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Trx Organization	*/
	protected int getOrgTrxId() {
		return orgTrxId;
	}

	/**	 Setter Parameter Value for Trx Organization	*/
	protected void setOrgTrxId(int orgTrxId) {
		this.orgTrxId = orgTrxId;
	}

	/**	 Getter Parameter Value for Add Invoice Reference Line	*/
	protected boolean isAddInvoiceReferenceLine() {
		return isAddInvoiceReferenceLine;
	}

	/**	 Setter Parameter Value for Add Invoice Reference Line	*/
	protected void setIsAddInvoiceReferenceLine(boolean isAddInvoiceReferenceLine) {
		this.isAddInvoiceReferenceLine = isAddInvoiceReferenceLine;
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