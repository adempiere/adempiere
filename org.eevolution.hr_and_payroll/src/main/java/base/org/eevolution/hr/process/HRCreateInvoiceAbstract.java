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

package org.eevolution.hr.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Payroll Create Invoice)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class HRCreateInvoiceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "HR_Create Invoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Payroll Create Invoice";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53889;
	/**	Parameter Name for Payroll Process	*/
	private static final String HR_PROCESS_ID = "HR_Process_ID";
	/**	Parameter Name for Date Invoiced	*/
	private static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Value for Payroll Process	*/
	private int hRProcessId;
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;

	@Override
	protected void prepare() {
		hRProcessId = getParameterAsInt(HR_PROCESS_ID);
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
	}

	/**	 Getter Parameter Value for Payroll Process	*/
	protected int getHRProcessId() {
		return hRProcessId;
	}

	/**	 Setter Parameter Value for Payroll Process	*/
	protected void setHRProcessId(int hRProcessId) {
		this.hRProcessId = hRProcessId;
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
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