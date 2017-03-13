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

import org.compiere.process.SvrProcess;
/** Generated Process for (Import Employee Data)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ImportEmployeeAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "Import_Employee";
	/** Process Name 	*/
	private static final String NAME = "Import Employee Data";
	/** Process Id 	*/
	private static final int ID = 53910;
 
	/**	Parameter Name for IsValidateOnly	*/
	public static final String IsValidateOnly = "IsValidateOnly";
	/**	Parameter Name for DeleteOldImported	*/
	public static final String DeleteOldImported = "DeleteOldImported";
	/**	Parameter Name for IsCreated	*/
	public static final String IsCreated = "IsCreated";
	/**	Parameter Name for C_BP_Group_ID	*/
	public static final String C_BP_Group_ID = "C_BP_Group_ID";

	/**	Parameter Value for isOnlyValidateData	*/
	private boolean isOnlyValidateData;
	/**	Parameter Value for isDeleteoldimportedrecords	*/
	private boolean isDeleteoldimportedrecords;
	/**	Parameter Value for isCreatedBusinessPartner	*/
	private boolean isCreatedBusinessPartner;
	/**	Parameter Value for businessPartnerGroupId	*/
	private int businessPartnerGroupId;
 

	@Override
	protected void prepare()
	{
		isOnlyValidateData = getParameterAsBoolean(IsValidateOnly);
		isDeleteoldimportedrecords = getParameterAsBoolean(DeleteOldImported);
		isCreatedBusinessPartner = getParameterAsBoolean(IsCreated);
		businessPartnerGroupId = getParameterAsInt(C_BP_Group_ID);
	}

	/**	 Getter Parameter Value for isOnlyValidateData	*/
	protected boolean isOnlyValidateData() {
		return isOnlyValidateData;
	}

	/**	 Getter Parameter Value for isDeleteoldimportedrecords	*/
	protected boolean isDeleteoldimportedrecords() {
		return isDeleteoldimportedrecords;
	}

	/**	 Getter Parameter Value for isCreatedBusinessPartner	*/
	protected boolean isCreatedBusinessPartner() {
		return isCreatedBusinessPartner;
	}

	/**	 Getter Parameter Value for businessPartnerGroupId	*/
	protected int getBusinessPartnerGroupId() {
		return businessPartnerGroupId;
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