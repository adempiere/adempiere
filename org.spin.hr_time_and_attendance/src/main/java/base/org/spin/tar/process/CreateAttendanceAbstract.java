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

package org.spin.tar.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Create Attendance Record)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CreateAttendanceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_CreateAttendance";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Attendance Record";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54135;
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Document Action	*/
	public static final String DOCACTION = "DocAction";
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Create Default Attendance	*/
	public static final String ISCREATEDEFAULT = "IsCreateDefault";
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Document Action	*/
	private String docAction;
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Create Default Attendance	*/
	private String isCreateDefault;

	@Override
	protected void prepare() {
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		docAction = getParameterAsString(DOCACTION);
		projectId = getParameterAsInt(C_PROJECT_ID);
		isCreateDefault = getParameterAsString(ISCREATEDEFAULT);
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Document Action	*/
	protected String getDocAction() {
		return docAction;
	}

	/**	 Setter Parameter Value for Document Action	*/
	protected void setDocAction(String docAction) {
		this.docAction = docAction;
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Create Default Attendance	*/
	protected String getIsCreateDefault() {
		return isCreateDefault;
	}

	/**	 Setter Parameter Value for Create Default Attendance	*/
	protected void setIsCreateDefault(String isCreateDefault) {
		this.isCreateDefault = isCreateDefault;
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