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

import java.sql.Timestamp;
/** Generated Process for (Payment Selection Generate (From Invoice))
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class PSCreateFromInvoiceAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "SBP_PaySelectionGenerateFromInvoice";
	/** Process Name 	*/
	private static final String NAME = "Payment Selection Generate (From Invoice)";
	/** Process Id 	*/
	private static final int ID = 53890;
 
	/**	Parameter Name for C_BankAccount_ID	*/
	public static final String C_BankAccount_ID = "C_BankAccount_ID";
	/**	Parameter Name for C_DocTypeTarget_ID	*/
	public static final String C_DocTypeTarget_ID = "C_DocTypeTarget_ID";
	/**	Parameter Name for DateDoc	*/
	public static final String DateDoc = "DateDoc";
	/**	Parameter Name for PayDate	*/
	public static final String PayDate = "PayDate";

	/**	Parameter Value for bankAccountId	*/
	private int bankAccountId;
	/**	Parameter Value for targetDocumentTypeId	*/
	private int targetDocumentTypeId;
	/**	Parameter Value for documentDate	*/
	private Timestamp documentDate;
	/**	Parameter Value for paymentdate	*/
	private Timestamp paymentdate;
 

	@Override
	protected void prepare()
	{
		bankAccountId = getParameterAsInt(C_BankAccount_ID);
		targetDocumentTypeId = getParameterAsInt(C_DocTypeTarget_ID);
		documentDate = getParameterAsTimestamp(DateDoc);
		paymentdate = getParameterAsTimestamp(PayDate);
	}

	/**	 Getter Parameter Value for bankAccountId	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Getter Parameter Value for targetDocumentTypeId	*/
	protected int getTargetDocumentTypeId() {
		return targetDocumentTypeId;
	}

	/**	 Getter Parameter Value for documentDate	*/
	protected Timestamp getDocumentDate() {
		return documentDate;
	}

	/**	 Getter Parameter Value for paymentdate	*/
	protected Timestamp getPaymentdate() {
		return paymentdate;
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