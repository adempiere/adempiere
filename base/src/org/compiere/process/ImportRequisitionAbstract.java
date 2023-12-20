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

package org.compiere.process;



/** Generated Process for (Import Requisitions)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ImportRequisitionAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_Requisition";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Requisitions";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54568;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for User/Contact	*/
	public static final String AD_USER_ID = "AD_User_ID";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Consolidate Requisition	*/
	public static final String ISCONSOLIDATEREQUISITION = "IsConsolidateRequisition";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for User/Contact	*/
	private int userId;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Consolidate Requisition	*/
	private boolean isConsolidateRequisition;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		userId = getParameterAsInt(AD_USER_ID);
		docAction = getParameterAsString(DOCACTION);
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isConsolidateRequisition = getParameterAsBoolean(ISCONSOLIDATEREQUISITION);
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for User/Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for User/Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Delete old imported records	*/
	protected boolean isDeleteOldImported() {
		return isDeleteOldImported;
	}

	/**	 Setter Parameter Value for Delete old imported records	*/
	protected void setDeleteOldImported(boolean isDeleteOldImported) {
		this.isDeleteOldImported = isDeleteOldImported;
	}

	/**	 Getter Parameter Value for Consolidate Requisition	*/
	protected boolean isConsolidateRequisition() {
		return isConsolidateRequisition;
	}

	/**	 Setter Parameter Value for Consolidate Requisition	*/
	protected void setIsConsolidateRequisition(boolean isConsolidateRequisition) {
		this.isConsolidateRequisition = isConsolidateRequisition;
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