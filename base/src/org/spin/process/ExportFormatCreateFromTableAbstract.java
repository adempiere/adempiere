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

/** Generated Process for (Create From Table)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ExportFormatCreateFromTableAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "EXP_Format_CreateFromTable";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create From Table";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54376;
	/**	Parameter Name for Delete old/existing records	*/
	public static final String DELETEOLD = "DeleteOld";
	/**	Parameter Name for Version	*/
	public static final String VERSION = "Version";
	/**	Parameter Name for Suffix	*/
	public static final String SUFFIX = "Suffix";
	/**	Parameter Value for Delete old/existing records	*/
	private boolean isDeleteOld;
	/**	Parameter Value for Version	*/
	private String version;
	/**	Parameter Value for Suffix	*/
	private String suffix;

	@Override
	protected void prepare() {
		isDeleteOld = getParameterAsBoolean(DELETEOLD);
		version = getParameterAsString(VERSION);
		suffix = getParameterAsString(SUFFIX);
	}

	/**	 Getter Parameter Value for Delete old/existing records	*/
	protected boolean isDeleteOld() {
		return isDeleteOld;
	}

	/**	 Setter Parameter Value for Delete old/existing records	*/
	protected void setDeleteOld(boolean isDeleteOld) {
		this.isDeleteOld = isDeleteOld;
	}

	/**	 Getter Parameter Value for Version	*/
	protected String getVersion() {
		return version;
	}

	/**	 Setter Parameter Value for Version	*/
	protected void setVersion(String version) {
		this.version = version;
	}

	/**	 Getter Parameter Value for Suffix	*/
	protected String getSuffix() {
		return suffix;
	}

	/**	 Setter Parameter Value for Suffix	*/
	protected void setSuffix(String suffix) {
		this.suffix = suffix;
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