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

/** Generated Process for (Create Process Access from Windows)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public abstract class RoleCreateFromWindowProcessAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_RoleCreateFromWindowProcess";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Process Access from Windows";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54202;
	/**	Parameter Name for Read Write	*/
	public static final String ISREADWRITE = "IsReadWrite";
	/**	Parameter Name for Dependent Entities	*/
	public static final String ISDEPENDENTENTITIES = "IsDependentEntities";
	/**	Parameter Value for Read Write	*/
	private boolean isReadWrite;
	/**	Parameter Value for Dependent Entities	*/
	private boolean isDependentEntities;

	@Override
	protected void prepare() {
		isReadWrite = getParameterAsBoolean(ISREADWRITE);
		isDependentEntities = getParameterAsBoolean(ISDEPENDENTENTITIES);
	}

	/**	 Getter Parameter Value for Read Write	*/
	protected boolean isReadWrite() {
		return isReadWrite;
	}

	/**	 Setter Parameter Value for Read Write	*/
	protected void setIsReadWrite(boolean isReadWrite) {
		this.isReadWrite = isReadWrite;
	}

	/**	 Getter Parameter Value for Dependent Entities	*/
	protected boolean isDependentEntities() {
		return isDependentEntities;
	}

	/**	 Setter Parameter Value for Dependent Entities	*/
	protected void setIsDependentEntities(boolean isDependentEntities) {
		this.isDependentEntities = isDependentEntities;
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