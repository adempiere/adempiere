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

package org.spin.tar.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Create Repeated Leave for Employee)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class CreateRepeatedLeaveAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "CreateRepeatedLeave";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Repeated Leave for Employee";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54259;
	/**	Parameter Name for Leave Type	*/
	public static final String HR_LEAVETYPE_ID = "HR_LeaveType_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Leave Reason	*/
	public static final String HR_LEAVEREASON_ID = "HR_LeaveReason_ID";
	/**	Parameter Value for Leave Type	*/
	private int leaveTypeId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Leave Reason	*/
	private int leaveReasonId;

	@Override
	protected void prepare() {
		leaveTypeId = getParameterAsInt(HR_LEAVETYPE_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		leaveReasonId = getParameterAsInt(HR_LEAVEREASON_ID);
	}

	/**	 Getter Parameter Value for Leave Type	*/
	protected int getLeaveTypeId() {
		return leaveTypeId;
	}

	/**	 Setter Parameter Value for Leave Type	*/
	protected void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Leave Reason	*/
	protected int getLeaveReasonId() {
		return leaveReasonId;
	}

	/**	 Setter Parameter Value for Leave Reason	*/
	protected void setLeaveReasonId(int leaveReasonId) {
		this.leaveReasonId = leaveReasonId;
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