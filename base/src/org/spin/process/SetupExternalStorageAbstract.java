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

package org.spin.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Setup External Storage for Files)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class SetupExternalStorageAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SetupExternalStorage";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Setup External Storage for Files";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54240;
	/**	Parameter Name for App Support	*/
	public static final String AD_APPSUPPORT_ID = "AD_AppSupport_ID";
	/**	Parameter Name for Application Registration	*/
	public static final String AD_APPREGISTRATION_ID = "AD_AppRegistration_ID";
	/**	Parameter Value for App Support	*/
	private int appSupportId;
	/**	Parameter Value for Application Registration	*/
	private int appRegistrationId;

	@Override
	protected void prepare() {
		appSupportId = getParameterAsInt(AD_APPSUPPORT_ID);
		appRegistrationId = getParameterAsInt(AD_APPREGISTRATION_ID);
	}

	/**	 Getter Parameter Value for App Support	*/
	protected int getAppSupportId() {
		return appSupportId;
	}

	/**	 Setter Parameter Value for App Support	*/
	protected void setAppSupportId(int appSupportId) {
		this.appSupportId = appSupportId;
	}

	/**	 Getter Parameter Value for Application Registration	*/
	protected int getAppRegistrationId() {
		return appRegistrationId;
	}

	/**	 Setter Parameter Value for Application Registration	*/
	protected void setAppRegistrationId(int appRegistrationId) {
		this.appRegistrationId = appRegistrationId;
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