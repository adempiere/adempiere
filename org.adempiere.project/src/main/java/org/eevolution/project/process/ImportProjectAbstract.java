/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.project.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Import Project)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ImportProjectAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_Project";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Project";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54028;
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Import only if No Errors	*/
	public static final String ISIMPORTONLYNOERRORS = "IsImportOnlyNoErrors";
	/**	Parameter Name for Only Validate Data	*/
	public static final String ISVALIDATEONLY = "IsValidateOnly";
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Import only if No Errors	*/
	private boolean isImportOnlyNoErrors;
	/**	Parameter Value for Only Validate Data	*/
	private boolean isValidateOnly;

	@Override
	protected void prepare() {
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isImportOnlyNoErrors = getParameterAsBoolean(ISIMPORTONLYNOERRORS);
		isValidateOnly = getParameterAsBoolean(ISVALIDATEONLY);
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