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

package org.spin.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Identify Payment)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class PaymentIdentifyAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Payment Identify";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Identify Payment";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54306;
	/**	Parameter Name for Transaction Type	*/
	public static final String TRXTYPE = "TrxType";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Order	*/
	public static final String C_ORDER_ID = "C_Order_ID";
	/**	Parameter Name for Invoice	*/
	public static final String C_INVOICE_ID = "C_Invoice_ID";
	/**	Parameter Name for Payment Related	*/
	public static final String RELATEDPAYMENT_ID = "RelatedPayment_ID";
	/**	Parameter Name for Transaction Date	*/
	public static final String DATETRX = "DateTrx";
	/**	Parameter Value for Transaction Type	*/
	private String trxType;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Order	*/
	private int orderId;
	/**	Parameter Value for Invoice	*/
	private int invoiceId;
	/**	Parameter Value for Payment Related	*/
	private int relatedPaymentId;
	/**	Parameter Value for Transaction Date	*/
	private Timestamp dateTrx;

	@Override
	protected void prepare() {
		trxType = getParameterAsString(TRXTYPE);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		orderId = getParameterAsInt(C_ORDER_ID);
		invoiceId = getParameterAsInt(C_INVOICE_ID);
		relatedPaymentId = getParameterAsInt(RELATEDPAYMENT_ID);
		dateTrx = getParameterAsTimestamp(DATETRX);
	}

	/**	 Getter Parameter Value for Transaction Type	*/
	protected String getTrxType() {
		return trxType;
	}

	/**	 Setter Parameter Value for Transaction Type	*/
	protected void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
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

	/**	 Getter Parameter Value for Payment Related	*/
	protected int getRelatedPaymentId() {
		return relatedPaymentId;
	}

	/**	 Setter Parameter Value for Payment Related	*/
	protected void setRelatedPaymentId(int relatedPaymentId) {
		this.relatedPaymentId = relatedPaymentId;
	}

	/**	 Getter Parameter Value for Transaction Date	*/
	protected Timestamp getDateTrx() {
		return dateTrx;
	}

	/**	 Setter Parameter Value for Transaction Date	*/
	protected void setDateTrx(Timestamp dateTrx) {
		this.dateTrx = dateTrx;
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