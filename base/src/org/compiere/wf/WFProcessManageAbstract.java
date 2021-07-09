/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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

package org.compiere.wf;

import org.compiere.process.SvrProcess;

/** Generated Process for (Manage Process)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class WFProcessManageAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "AD_WF_Process_Manage";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Manage Process";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 279;
	/**	Parameter Name for Abort Process	*/
	public static final String ISABORT = "IsAbort";
	/**	Parameter Name for New User/Contact	*/
	public static final String AD_USER_ID = "AD_User_ID";
	/**	Parameter Name for New Workflow Responsible	*/
	public static final String AD_WF_RESPONSIBLE_ID = "AD_WF_Responsible_ID";
	/**	Parameter Value for Abort Process	*/
	private boolean isAbort;
	/**	Parameter Value for New User/Contact	*/
	private int userId;
	/**	Parameter Value for New Workflow Responsible	*/
	private int wFResponsibleId;

	@Override
	protected void prepare() {
		isAbort = getParameterAsBoolean(ISABORT);
		userId = getParameterAsInt(AD_USER_ID);
		wFResponsibleId = getParameterAsInt(AD_WF_RESPONSIBLE_ID);
	}

	/**	 Getter Parameter Value for Abort Process	*/
	protected boolean isAbort() {
		return isAbort;
	}

	/**	 Setter Parameter Value for Abort Process	*/
	protected void setIsAbort(boolean isAbort) {
		this.isAbort = isAbort;
	}

	/**	 Getter Parameter Value for New User/Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for New User/Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
	}

	/**	 Getter Parameter Value for New Workflow Responsible	*/
	protected int getWFResponsibleId() {
		return wFResponsibleId;
	}

	/**	 Setter Parameter Value for New Workflow Responsible	*/
	protected void setWFResponsibleId(int wFResponsibleId) {
		this.wFResponsibleId = wFResponsibleId;
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