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

package org.eevolution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
/** Generated Process for (Payroll Create Invoice)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class HRCreateInvoiceAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "HR_Create Invoice";
	/** Process Name 	*/
	private static final String NAME = "Payroll Create Invoice";
	/** Process Id 	*/
	private static final int ID = 53889;
 
	/**	Parameter Name for HR_Process_ID	*/
	public static final String HR_Process_ID = "HR_Process_ID";
	/**	Parameter Name for C_DocType_ID	*/
	public static final String C_DocType_ID = "C_DocType_ID";
	/**	Parameter Name for DateInvoiced	*/
	public static final String DateInvoiced = "DateInvoiced";

	/**	Parameter Value for payrollProcessId	*/
	private int payrollProcessId;
	/**	Parameter Value for documentTypeId	*/
	private int documentTypeId;
	/**	Parameter Value for dateInvoiced	*/
	private Timestamp dateInvoiced;
 

	@Override
	protected void prepare()
	{
		payrollProcessId = getParameterAsInt(HR_Process_ID);
		documentTypeId = getParameterAsInt(C_DocType_ID);
		dateInvoiced = getParameterAsTimestamp(DateInvoiced);
	}

	/**	 Getter Parameter Value for payrollProcessId	*/
	protected int getPayrollProcessId() {
		return payrollProcessId;
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
}