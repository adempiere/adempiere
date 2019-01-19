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
 *  @version Release 3.9.0
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
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Collection Status	*/
	public static final String INVOICECOLLECTIONTYPE = "InvoiceCollectionType";
	/**	Parameter Name for Sales Transaction	*/
	public static final String ISSOTRX = "IsSOTrx";
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Days due	*/
	public static final String DAYSDUE = "DaysDue";
	/**	Parameter Name for Date To	*/
	public static final String DATETO = "DateTo";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Collection Status	*/
	private String invoiceCollectionType;
	/**	Parameter Value for Sales Transaction	*/
	private boolean isSOTrx;
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Date Invoiced(To)	*/
	private Timestamp dateInvoicedTo;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Account Date(To)	*/
	private Timestamp dateAcctTo;
	/**	Parameter Value for Days due	*/
	private int daysDue;
	/**	Parameter Value for Days due(To)	*/
	private int daysDueTo;
	/**	Parameter Value for Date To	*/
	private Timestamp dateTo;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		invoiceCollectionType = getParameterAsString(INVOICECOLLECTIONTYPE);
		isSOTrx = getParameterAsBoolean(ISSOTRX);
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateInvoicedTo = getParameterToAsTimestamp(DATEINVOICED);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		dateAcctTo = getParameterToAsTimestamp(DATEACCT);
		daysDue = getParameterAsInt(DAYSDUE);
		daysDueTo = getParameterToAsInt(DAYSDUE);
		dateTo = getParameterAsTimestamp(DATETO);
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

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Collection Status	*/
	protected String getInvoiceCollectionType() {
		return invoiceCollectionType;
	}

	/**	 Setter Parameter Value for Collection Status	*/
	protected void setInvoiceCollectionType(String invoiceCollectionType) {
		this.invoiceCollectionType = invoiceCollectionType;
	}

	/**	 Getter Parameter Value for Sales Transaction	*/
	protected boolean isSOTrx() {
		return isSOTrx;
	}

	/**	 Setter Parameter Value for Sales Transaction	*/
	protected void setIsSOTrx(boolean isSOTrx) {
		this.isSOTrx = isSOTrx;
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