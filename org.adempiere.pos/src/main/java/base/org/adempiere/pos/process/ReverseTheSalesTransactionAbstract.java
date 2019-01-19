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

package org.adempiere.pos.process;

import org.compiere.process.SvrProcess;


/** Generated Process for (Reverse The Sales Transaction)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class ReverseTheSalesTransactionAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_POS ReverseTheSalesTransaction";
	/** Process Name 	*/
	private static final String NAME = "Reverse The Sales Transaction";
	/** Process Id 	*/
	private static final int ID = 53824;
 
	/**	Parameter Name for C_Order_ID	*/
	public static final String C_Order_ID = "C_Order_ID";
	/**	Parameter Name for Bill_BPartner_ID	*/
	public static final String Bill_BPartner_ID = "Bill_BPartner_ID";
	/**	Parameter Name for IsCancelled	*/
	public static final String IsCancelled = "IsCancelled";
	/**	Parameter Name for IsShipConfirm	*/
	public static final String IsShipConfirm = "IsShipConfirm";

	/**	Parameter Value for orderId	*/
	private int orderId;
	/**	Parameter Value for invoicePartnerId	*/
	private int invoicePartnerId;
	/**	Parameter Value for isCancelled	*/
	private boolean isCancelled;
	/**	Parameter Value for isShipReceiptConfirmation	*/
	private boolean isShipReceiptConfirmation;
 

	@Override
	protected void prepare()
	{
		orderId = getParameterAsInt(C_Order_ID);
		invoicePartnerId = getParameterAsInt(Bill_BPartner_ID);
		isCancelled = getParameterAsBoolean(IsCancelled);
		isShipReceiptConfirmation = getParameterAsBoolean(IsShipConfirm);
	}

	/**	 Getter Parameter Value for orderId	*/
	protected int getOrderId() {
		return orderId;
	}

	/**	 Getter Parameter Value for invoicePartnerId	*/
	protected int getInvoicePartnerId() {
		return invoicePartnerId;
	}

	/**	 Getter Parameter Value for isCancelled	*/
	protected boolean isCancelled() {
		return isCancelled;
	}

	/**	 Getter Parameter Value for isShipReceiptConfirmation	*/
	protected boolean isShipReceiptConfirmation() {
		return isShipReceiptConfirmation;
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