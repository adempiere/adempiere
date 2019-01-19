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



/** Generated Process for (Import migration from XML)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class MigrationFromXMLAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "AD_Migration import";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import migration from XML";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53175;
	/**	Parameter Name for File Path or Name	*/
	public static final String FILEPATHORNAME = "FilePathOrName";
	/**	Parameter Name for File Name	*/  // Parameter changed name in DB
	public static final String FILENAME = "FileName";
	/**	Parameter Name for Apply	*/
	public static final String APPLY = "Apply";
	/**	Parameter Name for Force	*/
	public static final String ISFORCE = "IsForce";
	/**	Parameter Value for File Path or Name	*/
	private String filePathOrName;
	/**	Parameter Value for Apply	*/
	private boolean isApply;
	/**	Parameter Value for Force	*/
	private boolean isForce;

	@Override
	protected void prepare() {
		filePathOrName = getParameterAsString(FILEPATHORNAME);
		if (filePathOrName == null)
			filePathOrName = getParameterAsString(FILENAME);
		isApply = getParameterAsBoolean(APPLY);
		isForce = getParameterAsBoolean(ISFORCE);
	}

	/**	 Getter Parameter Value for File Path or Name	*/
	protected String getFilePathOrName() {
		return filePathOrName;
	}

	/**	 Setter Parameter Value for File Path or Name	*/
	protected void setFilePathOrName(String filePathOrName) {
		this.filePathOrName = filePathOrName;
	}

	/**	 Getter Parameter Value for Apply	*/
	protected boolean isApply() {
		return isApply;
	}

	/**	 Setter Parameter Value for Apply	*/
	protected void setApply(boolean isApply) {
		this.isApply = isApply;
	}

	/**	 Getter Parameter Value for Force	*/
	protected boolean isForce() {
		return isForce;
	}

	/**	 Setter Parameter Value for Force	*/
	protected void setIsForce(boolean isForce) {
		this.isForce = isForce;
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