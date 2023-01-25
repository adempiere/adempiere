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

package org.compiere.asset.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Import Fixed Assets)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ImportFixedAssetAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_FixedAsset";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Fixed Assets";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53215;
	/**	Parameter Name for Asset Group	*/
	public static final String A_ASSET_GROUP_ID = "A_Asset_Group_ID";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Only Validate Data	*/
	public static final String ISVALIDATEONLY = "IsValidateOnly";
	/**	Parameter Value for Asset Group	*/
	private int assetGroupId;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Only Validate Data	*/
	private boolean isValidateOnly;

	@Override
	protected void prepare() {
		assetGroupId = getParameterAsInt(A_ASSET_GROUP_ID);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isValidateOnly = getParameterAsBoolean(ISVALIDATEONLY);
	}

	/**	 Getter Parameter Value for Asset Group	*/
	protected int getAssetGroupId() {
		return assetGroupId;
	}

	/**	 Setter Parameter Value for Asset Group	*/
	protected void setAssetGroupId(int assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
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