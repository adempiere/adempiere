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



/** Generated Process for (Load Bank Statement)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class LoadBankStatementAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Load_BankStatement";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Load Bank Statement";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 247;
	/**	Parameter Name for Bank Statement Loader	*/
	public static final String C_BANKSTATEMENTLOADER_ID = "C_BankStatementLoader_ID";
	/**	Parameter Name for File Name	*/
	public static final String FILENAME = "FileName";
	/**	Parameter Value for Bank Statement Loader	*/
	private int bankStatementLoaderId;
	/**	Parameter Value for File Name	*/
	private String fileName;

	@Override
	protected void prepare() {
		bankStatementLoaderId = getParameterAsInt(C_BANKSTATEMENTLOADER_ID);
		fileName = getParameterAsString(FILENAME);
	}

	/**	 Getter Parameter Value for Bank Statement Loader	*/
	protected int getBankStatementLoaderId() {
		return bankStatementLoaderId;
	}

	/**	 Setter Parameter Value for Bank Statement Loader	*/
	protected void setBankStatementLoaderId(int bankStatementLoaderId) {
		this.bankStatementLoaderId = bankStatementLoaderId;
	}

	/**	 Getter Parameter Value for File Name	*/
	protected String getFileName() {
		return fileName;
	}

	/**	 Setter Parameter Value for File Name	*/
	protected void setFileName(String fileName) {
		this.fileName = fileName;
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