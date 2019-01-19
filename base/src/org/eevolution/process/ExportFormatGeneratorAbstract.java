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

package org.eevolution.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Export Format Generator)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ExportFormatGeneratorAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "EXP_Format Generator";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Export Format Generator";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53085;
	/**	Parameter Name for Window	*/
	private static final String AD_WINDOW_ID = "AD_Window_ID";
	/**	Parameter Name for Includes only the Tabs that Insert records	*/
	private static final String ISINSERTRECORD = "IsInsertRecord";
	/**	Parameter Name for Includes only the mandatory columns	*/
	private static final String ISMANDATORY = "IsMandatory";
	/**	Parameter Value for Window	*/
	private int windowId;
	/**	Parameter Value for Includes only the Tabs that Insert records	*/
	private boolean isInsertRecord;
	/**	Parameter Value for Includes only the mandatory columns	*/
	private boolean isMandatory;

	@Override
	protected void prepare() {
		windowId = getParameterAsInt(AD_WINDOW_ID);
		isInsertRecord = getParameterAsBoolean(ISINSERTRECORD);
		isMandatory = getParameterAsBoolean(ISMANDATORY);
	}

	/**	 Getter Parameter Value for Window	*/
	protected int getWindowId() {
		return windowId;
	}

	/**	 Setter Parameter Value for Window	*/
	protected void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**	 Getter Parameter Value for Includes only the Tabs that Insert records	*/
	protected boolean isInsertRecord() {
		return isInsertRecord;
	}

	/**	 Setter Parameter Value for Includes only the Tabs that Insert records	*/
	protected void setIsInsertRecord(boolean isInsertRecord) {
		this.isInsertRecord = isInsertRecord;
	}

	/**	 Getter Parameter Value for Includes only the mandatory columns	*/
	protected boolean isMandatory() {
		return isMandatory;
	}

	/**	 Setter Parameter Value for Includes only the mandatory columns	*/
	protected void setIsMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
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