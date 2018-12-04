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



/** Generated Process for (Copy Role)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CopyRoleAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "CopyRole";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Copy Role";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 50010;
	/**	Parameter Name for Role From	*/
	public static final String AD_ROLE_ID = "AD_Role_ID";
	/**	Parameter Name for Role To	*/
	public static final String AD_ROLE_TO_ID = "AD_Role_To_ID";
	/**	Parameter Name for Client	*/
	public static final String AD_CLIENT_ID = "AD_Client_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Value for Role From	*/
	private int roleId;
	/**	Parameter Value for Role To	*/
	private int roleToId;
	/**	Parameter Value for Client	*/
	private int clientId;
	/**	Parameter Value for Organization	*/
	private int orgId;

	@Override
	protected void prepare() {
		roleId = getParameterAsInt(AD_ROLE_ID);
		roleToId = getParameterAsInt(AD_ROLE_TO_ID);
		clientId = getParameterAsInt(AD_CLIENT_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
	}

	/**	 Getter Parameter Value for Role From	*/
	protected int getRoleId() {
		return roleId;
	}

	/**	 Setter Parameter Value for Role From	*/
	protected void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**	 Getter Parameter Value for Role To	*/
	protected int getRoleToId() {
		return roleToId;
	}

	/**	 Setter Parameter Value for Role To	*/
	protected void setRoleToId(int roleToId) {
		this.roleToId = roleToId;
	}

	/**	 Getter Parameter Value for Client	*/
	protected int getClientId() {
		return clientId;
	}

	/**	 Setter Parameter Value for Client	*/
	protected void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
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