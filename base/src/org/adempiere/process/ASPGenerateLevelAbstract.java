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

package org.adempiere.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (ASP Generate Level From Menu)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class ASPGenerateLevelAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ASPGenerateLevelFromMenu";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "ASP Generate Level From Menu";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 1000001;
	/**	Parameter Name for ASP Module	*/
	public static final String ASP_MODULE_ID = "ASP_Module_ID";
	/**	Parameter Name for ASP Level	*/
	public static final String ASP_LEVEL_ID = "ASP_Level_ID";
	/**	Parameter Name for Generate Fields	*/
	public static final String ISGENERATEFIELDS = "IsGenerateFields";
	/**	Parameter Name for ASP Status	*/
	public static final String ASP_STATUS = "ASP_Status";
	/**	Parameter Value for ASP Module	*/
	private int moduleId;
	/**	Parameter Value for ASP Level	*/
	private int levelId;
	/**	Parameter Value for Generate Fields	*/
	private boolean isGenerateFields;
	/**	Parameter Value for ASP Status	*/
	private String status;

	@Override
	protected void prepare() {
		moduleId = getParameterAsInt(ASP_MODULE_ID);
		levelId = getParameterAsInt(ASP_LEVEL_ID);
		isGenerateFields = getParameterAsBoolean(ISGENERATEFIELDS);
		status = getParameterAsString(ASP_STATUS);
	}

	/**	 Getter Parameter Value for ASP Module	*/
	protected int getModuleId() {
		return moduleId;
	}

	/**	 Setter Parameter Value for ASP Module	*/
	protected void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	/**	 Getter Parameter Value for ASP Level	*/
	protected int getLevelId() {
		return levelId;
	}

	/**	 Setter Parameter Value for ASP Level	*/
	protected void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	/**	 Getter Parameter Value for Generate Fields	*/
	protected boolean isGenerateFields() {
		return isGenerateFields;
	}

	/**	 Setter Parameter Value for Generate Fields	*/
	protected void setIsGenerateFields(boolean isGenerateFields) {
		this.isGenerateFields = isGenerateFields;
	}

	/**	 Getter Parameter Value for ASP Status	*/
	protected String getStatus() {
		return status;
	}

	/**	 Setter Parameter Value for ASP Status	*/
	protected void setStatus(String status) {
		this.status = status;
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