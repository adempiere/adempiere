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

/** Generated Process for (Credit Leave Manual Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class LeaveCreditManualAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "HR_LeaveAssign Credit Process";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Credit Leave Manual Process";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53538;
	/**	Parameter Name for Number of Leaves Allocated	*/
	public static final String NOOFLEAVESALLOCATED = "NoOfLeavesAllocated";
	/**	Parameter Name for Leave Reason	*/
	public static final String HR_LEAVEREASON_ID = "HR_LeaveReason_ID";
	/**	Parameter Name for Valid from	*/
	public static final String VALIDFROM = "ValidFrom";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Value for Number of Leaves Allocated	*/
	private int noOfLeavesAllocated;
	/**	Parameter Value for Leave Reason	*/
	private int leaveReasonId;
	/**	Parameter Value for Valid from	*/
	private Timestamp validFrom;
	/**	Parameter Value for Document Action	*/
	private String docAction;

	@Override
	protected void prepare() {
		noOfLeavesAllocated = getParameterAsInt(NOOFLEAVESALLOCATED);
		leaveReasonId = getParameterAsInt(HR_LEAVEREASON_ID);
		validFrom = getParameterAsTimestamp(VALIDFROM);
		docAction = getParameterAsString(DOCACTION);
	}

	/**	 Getter Parameter Value for Number of Leaves Allocated	*/
	protected int getNoOfLeavesAllocated() {
		return noOfLeavesAllocated;
	}

	/**	 Setter Parameter Value for Number of Leaves Allocated	*/
	protected void setNoOfLeavesAllocated(int noOfLeavesAllocated) {
		this.noOfLeavesAllocated = noOfLeavesAllocated;
	}

	/**	 Getter Parameter Value for Leave Reason	*/
	protected int getLeaveReasonId() {
		return leaveReasonId;
	}

	/**	 Setter Parameter Value for Leave Reason	*/
	protected void setLeaveReasonId(int leaveReasonId) {
		this.leaveReasonId = leaveReasonId;
	}

	/**	 Getter Parameter Value for Valid from	*/
	protected Timestamp getValidFrom() {
		return validFrom;
	}

	/**	 Setter Parameter Value for Valid from	*/
	protected void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
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