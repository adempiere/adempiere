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

package org.spin.investment.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Import Financial Loan)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ImportLoanAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import Loan";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Financial Loan";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54073;
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Only Validate Data	*/
	public static final String ISVALIDATEONLY = "IsValidateOnly";
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Only Validate Data	*/
	private boolean isValidateOnly;

	@Override
	protected void prepare() {
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
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