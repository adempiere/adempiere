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

package org.eevolution.manufacturing.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (ImportInventoryMovement)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ImportInventoryMoveAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_InventoryMovement";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "ImportInventoryMovement";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53216;
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Import only if No Errors	*/
	public static final String ISIMPORTONLYNOERRORS = "IsImportOnlyNoErrors";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Import only if No Errors	*/
	private boolean isImportOnlyNoErrors;
	/**	Parameter Value for Document Action	*/
	private String docAction;

	@Override
	protected void prepare() {
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isImportOnlyNoErrors = getParameterAsBoolean(ISIMPORTONLYNOERRORS);
		docAction = getParameterAsString(DOCACTION);
	}

	/**	 Getter Parameter Value for Delete old imported records	*/
	protected boolean isDeleteOldImported() {
		return isDeleteOldImported;
	}

	/**	 Setter Parameter Value for Delete old imported records	*/
	protected void setDeleteOldImported(boolean isDeleteOldImported) {
		this.isDeleteOldImported = isDeleteOldImported;
	}

	/**	 Getter Parameter Value for Import only if No Errors	*/
	protected boolean isImportOnlyNoErrors() {
		return isImportOnlyNoErrors;
	}

	/**	 Setter Parameter Value for Import only if No Errors	*/
	protected void setIsImportOnlyNoErrors(boolean isImportOnlyNoErrors) {
		this.isImportOnlyNoErrors = isImportOnlyNoErrors;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
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