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

package org.adempiere.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Create Invoice for Business Partner)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class HR_CreateInvoiceBPartnerAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "HR_CreateInvoice_BusinessPartner";
	/** Process Name 	*/
	private static final String NAME = "Create Invoice for Business Partner";
	/** Process Id 	*/
	private static final int ID = 3000343;
 
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for C_DocType_ID	*/
	public static final String C_DocType_ID = "C_DocType_ID";
	/**	Parameter Name for DateInvoiced	*/
	public static final String DateInvoiced = "DateInvoiced";

	/**	Parameter Name for C_Charge_ID	*/
	public static final String C_Charge_ID = "C_Charge_ID";

	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for documentTypeId	*/
	private int chargeID;
	private int documentTypeId;
	/**	Parameter Value for dateInvoiced	*/
	private Timestamp dateInvoiced;
 

	@Override
	protected void prepare()
	{
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		documentTypeId = getParameterAsInt(C_DocType_ID);
		dateInvoiced = getParameterAsTimestamp(DateInvoiced);
		chargeID = getParameterAsInt(C_Charge_ID);
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for documentTypeId	*/
	protected int getDocumentTypeId() {
		return documentTypeId;
	}

	/**	 Getter Parameter Value for dateInvoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
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

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getChargeID() {
		return chargeID;
	}
}