/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.compiere.request.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Request EMail Processor)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class RequestEMailProcessorAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Request EMail Processor";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Request EMail Processor";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 50012;
	/**	Parameter Name for EMail Configuration	*/
	public static final String AD_EMAILCONFIG_ID = "AD_EMailConfig_ID";
	/**	Parameter Name for Request User	*/
	public static final String REQUESTUSER = "RequestUser";
	/**	Parameter Name for Request User Password	*/
	public static final String REQUESTUSERPW = "RequestUserPW";
	/**	Parameter Name for Request Folder	*/
	public static final String REQUESTFOLDER = "RequestFolder";
	/**	Parameter Name for Inbox Folder	*/
	public static final String INBOXFOLDER = "InboxFolder";
	/**	Parameter Name for Error Folder	*/
	public static final String ERRORFOLDER = "ErrorFolder";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for User/Contact	*/
	public static final String AD_USER_ID = "AD_User_ID";
	/**	Parameter Name for Role	*/
	public static final String AD_ROLE_ID = "AD_Role_ID";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Name for Request Type	*/
	public static final String R_REQUESTTYPE_ID = "R_RequestType_ID";
	/**	Parameter Name for Priority	*/
	public static final String PRIORITYRULE = "PriorityRule";
	/**	Parameter Name for Confidentiality	*/
	public static final String CONFIDENTIALTYPE = "ConfidentialType";
	/**	Parameter Value for EMail Configuration	*/
	private int eMailConfigId;
	/**	Parameter Value for Request User	*/
	private String requestUser;
	/**	Parameter Value for Request User Password	*/
	private String requestUserPW;
	/**	Parameter Value for Request Folder	*/
	private String requestFolder;
	/**	Parameter Value for Inbox Folder	*/
	private String inboxFolder;
	/**	Parameter Value for Error Folder	*/
	private String errorFolder;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for User/Contact	*/
	private int userId;
	/**	Parameter Value for Role	*/
	private int roleId;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;
	/**	Parameter Value for Request Type	*/
	private int requestTypeId;
	/**	Parameter Value for Priority	*/
	private String priorityRule;
	/**	Parameter Value for Confidentiality	*/
	private String confidentialType;

	@Override
	protected void prepare() {
		eMailConfigId = getParameterAsInt(AD_EMAILCONFIG_ID);
		requestUser = getParameterAsString(REQUESTUSER);
		requestUserPW = getParameterAsString(REQUESTUSERPW);
		requestFolder = getParameterAsString(REQUESTFOLDER);
		inboxFolder = getParameterAsString(INBOXFOLDER);
		errorFolder = getParameterAsString(ERRORFOLDER);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		userId = getParameterAsInt(AD_USER_ID);
		roleId = getParameterAsInt(AD_ROLE_ID);
		salesRepId = getParameterAsInt(SALESREP_ID);
		requestTypeId = getParameterAsInt(R_REQUESTTYPE_ID);
		priorityRule = getParameterAsString(PRIORITYRULE);
		confidentialType = getParameterAsString(CONFIDENTIALTYPE);
	}

	/**	 Getter Parameter Value for EMail Configuration	*/
	protected int getEMailConfigId() {
		return eMailConfigId;
	}

	/**	 Setter Parameter Value for EMail Configuration	*/
	protected void setEMailConfigId(int eMailConfigId) {
		this.eMailConfigId = eMailConfigId;
	}

	/**	 Getter Parameter Value for Request User	*/
	protected String getRequestUser() {
		return requestUser;
	}

	/**	 Setter Parameter Value for Request User	*/
	protected void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	/**	 Getter Parameter Value for Request User Password	*/
	protected String getRequestUserPW() {
		return requestUserPW;
	}

	/**	 Setter Parameter Value for Request User Password	*/
	protected void setRequestUserPW(String requestUserPW) {
		this.requestUserPW = requestUserPW;
	}

	/**	 Getter Parameter Value for Request Folder	*/
	protected String getRequestFolder() {
		return requestFolder;
	}

	/**	 Setter Parameter Value for Request Folder	*/
	protected void setRequestFolder(String requestFolder) {
		this.requestFolder = requestFolder;
	}

	/**	 Getter Parameter Value for Inbox Folder	*/
	protected String getInboxFolder() {
		return inboxFolder;
	}

	/**	 Setter Parameter Value for Inbox Folder	*/
	protected void setInboxFolder(String inboxFolder) {
		this.inboxFolder = inboxFolder;
	}

	/**	 Getter Parameter Value for Error Folder	*/
	protected String getErrorFolder() {
		return errorFolder;
	}

	/**	 Setter Parameter Value for Error Folder	*/
	protected void setErrorFolder(String errorFolder) {
		this.errorFolder = errorFolder;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for User/Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for User/Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
	}

	/**	 Getter Parameter Value for Role	*/
	protected int getRoleId() {
		return roleId;
	}

	/**	 Setter Parameter Value for Role	*/
	protected void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
	}

	/**	 Getter Parameter Value for Request Type	*/
	protected int getRequestTypeId() {
		return requestTypeId;
	}

	/**	 Setter Parameter Value for Request Type	*/
	protected void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriorityRule() {
		return priorityRule;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}

	/**	 Getter Parameter Value for Confidentiality	*/
	protected String getConfidentialType() {
		return confidentialType;
	}

	/**	 Setter Parameter Value for Confidentiality	*/
	protected void setConfidentialType(String confidentialType) {
		this.confidentialType = confidentialType;
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