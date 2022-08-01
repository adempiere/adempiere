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

import org.compiere.process.SvrProcess;

/** Generated Process for (Import Employee Data)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ImportEmployeeAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_Employee";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Employee Data";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53910;
 
	/**	Parameter Name for Delete old imported records	*/
	private static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Only Validate Data	*/
	private static final String ISVALIDATEONLY = "IsValidateOnly";
	/**	Parameter Name for Is Created Business Partner	*/
	private static final String ISCREATED = "IsCreated";
	/**	Parameter Name for Business Partner Group	*/
	private static final String C_BP_GROUP_ID = "C_BP_Group_ID";

	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Only Validate Data	*/
	private boolean isValidateOnly;
	/**	Parameter Value for Is Created Business Partner	*/
	private boolean isCreated;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
 

	@Override
	protected void prepare() {
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isValidateOnly = getParameterAsBoolean(ISVALIDATEONLY);
		isCreated = getParameterAsBoolean(ISCREATED);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
	}

	/**	 Getter Parameter Value for Delete old imported records	*/
	protected boolean isDeleteOldImported() {
		return isDeleteOldImported;
	}

	/**	 Setter Parameter Value for Delete old imported records	*/
	protected void setDeleteOldImported(boolean isDeleteOldImported) {
		this.isDeleteOldImported = isDeleteOldImported;
	}

	/**	 Getter Parameter Value for Only Validate Data	*/
	protected boolean isValidateOnly() {
		return isValidateOnly;
	}

	/**	 Setter Parameter Value for Only Validate Data	*/
	protected void setIsValidateOnly(boolean isValidateOnly) {
		this.isValidateOnly = isValidateOnly;
	}

	/**	 Getter Parameter Value for Is Created Business Partner	*/
	protected boolean isCreated() {
		return isCreated;
	}

	/**	 Setter Parameter Value for Is Created Business Partner	*/
	protected void setIsCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
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