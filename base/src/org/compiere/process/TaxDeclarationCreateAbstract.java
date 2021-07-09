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

package org.compiere.process;



/** Generated Process for (Create Tax Declaration)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class TaxDeclarationCreateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_TaxDeclaration Create";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Tax Declaration";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 336;
	/**	Parameter Name for Delete old/existing records	*/
	public static final String DELETEOLD = "DeleteOld";
	/**	Parameter Name for UseDateAcct	*/
	public static final String USEDATEACCT = "UseDateAcct";
	/**	Parameter Name for OnlyPosted	*/
	public static final String ONLYPOSTED = "OnlyPosted";
	/**	Parameter Value for Delete old/existing records	*/
	private boolean isDeleteOld;
	/**	Parameter Value for UseDateAcct	*/
	private boolean isUseDateAcct;
	/**	Parameter Value for OnlyPosted	*/
	private boolean isOnlyPosted;

	@Override
	protected void prepare() {
		isDeleteOld = getParameterAsBoolean(DELETEOLD);
		isUseDateAcct = getParameterAsBoolean(USEDATEACCT);
		isOnlyPosted = getParameterAsBoolean(ONLYPOSTED);
	}

	/**	 Getter Parameter Value for Delete old/existing records	*/
	protected boolean isDeleteOld() {
		return isDeleteOld;
	}

	/**	 Setter Parameter Value for Delete old/existing records	*/
	protected void setDeleteOld(boolean isDeleteOld) {
		this.isDeleteOld = isDeleteOld;
	}

	/**	 Getter Parameter Value for UseDateAcct	*/
	protected boolean isUseDateAcct() {
		return isUseDateAcct;
	}

	/**	 Setter Parameter Value for UseDateAcct	*/
	protected void setUseDateAcct(boolean isUseDateAcct) {
		this.isUseDateAcct = isUseDateAcct;
	}

	/**	 Getter Parameter Value for OnlyPosted	*/
	protected boolean isOnlyPosted() {
		return isOnlyPosted;
	}

	/**	 Setter Parameter Value for OnlyPosted	*/
	protected void setOnlyPosted(boolean isOnlyPosted) {
		this.isOnlyPosted = isOnlyPosted;
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