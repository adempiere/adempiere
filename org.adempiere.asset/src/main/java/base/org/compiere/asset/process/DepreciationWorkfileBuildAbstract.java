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

import org.compiere.process.SvrProcess;

/** Generated Process for (Update depreciation)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class DepreciationWorkfileBuildAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "A_Depreciation_Workfile_Build";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Update depreciation";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53207;
	/**	Parameter Name for All Assets	*/
	public static final String ALLASSETS = "AllAssets";
	/**	Parameter Value for All Assets	*/
	private boolean isAllAssets;

	@Override
	protected void prepare() {
		isAllAssets = getParameterAsBoolean(ALLASSETS);
	}

	/**	 Getter Parameter Value for All Assets	*/
	protected boolean isAllAssets() {
		return isAllAssets;
	}

	/**	 Setter Parameter Value for All Assets	*/
	protected void setAllAssets(boolean isAllAssets) {
		this.isAllAssets = isAllAssets;
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