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

package org.spin.freight.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Sales Invoice from Freight Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class GenerateSalesInvoiceFromFreightOrderAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_GenerateSalesInvoiceFromFreightOrder";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Sales Invoice from Freight Order";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54427;
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Target Document Type	*/
	public static final String C_DOCTYPETARGET_ID = "C_DocTypeTarget_ID";
	/**	Parameter Name for Trx Organization	*/
	public static final String AD_ORGTRX_ID = "AD_OrgTrx_ID";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Target Document Type	*/
	private int docTypeTargetId;
	/**	Parameter Value for Trx Organization	*/
	private int orgTrxId;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;

	@Override
	protected void prepare() {
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		docAction = getParameterAsString(DOCACTION);
		docTypeTargetId = getParameterAsInt(C_DOCTYPETARGET_ID);
		orgTrxId = getParameterAsInt(AD_ORGTRX_ID);
		salesRepId = getParameterAsInt(SALESREP_ID);
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

	/**	 Getter Parameter Value for Target Document Type	*/
	protected int getDocTypeTargetId() {
		return docTypeTargetId;
	}

	/**	 Setter Parameter Value for Target Document Type	*/
	protected void setDocTypeTargetId(int docTypeTargetId) {
		this.docTypeTargetId = docTypeTargetId;
	}

	/**	 Getter Parameter Value for Trx Organization	*/
	protected int getOrgTrxId() {
		return orgTrxId;
	}

	/**	 Setter Parameter Value for Trx Organization	*/
	protected void setOrgTrxId(int orgTrxId) {
		this.orgTrxId = orgTrxId;
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