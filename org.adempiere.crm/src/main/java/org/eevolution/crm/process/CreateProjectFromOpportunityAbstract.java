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

package org.eevolution.crm.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Create Project)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class CreateProjectFromOpportunityAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Opportunity Create Project";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Project";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54007;
	/**	Parameter Name for Project Type	*/
	public static final String C_PROJECTTYPE_ID = "C_ProjectType_ID";
	/**	Parameter Name for Project Category	*/
	public static final String C_PROJECTCATEGORY_ID = "C_ProjectCategory_ID";
	/**	Parameter Name for Project Status	*/
	public static final String C_PROJECTSTATUS_ID = "C_ProjectStatus_ID";
	/**	Parameter Name for Project Group	*/
	public static final String C_PROJECTGROUP_ID = "C_ProjectGroup_ID";
	/**	Parameter Name for Project Class	*/
	public static final String C_PROJECTCLASS_ID = "C_ProjectClass_ID";
	/**	Parameter Name for Contract Date	*/
	public static final String DATECONTRACT = "DateContract";
	/**	Parameter Name for Finish Date	*/
	public static final String DATEFINISH = "DateFinish";
	/**	Parameter Value for Project Type	*/
	private int projectTypeId;
	/**	Parameter Value for Project Category	*/
	private int projectCategoryId;
	/**	Parameter Value for Project Status	*/
	private int projectStatusId;
	/**	Parameter Value for Project Group	*/
	private int projectGroupId;
	/**	Parameter Value for Project Class	*/
	private int projectClassId;
	/**	Parameter Value for Contract Date	*/
	private Timestamp dateContract;
	/**	Parameter Value for Finish Date	*/
	private Timestamp dateFinish;

	@Override
	protected void prepare() {
		projectTypeId = getParameterAsInt(C_PROJECTTYPE_ID);
		projectCategoryId = getParameterAsInt(C_PROJECTCATEGORY_ID);
		projectStatusId = getParameterAsInt(C_PROJECTSTATUS_ID);
		projectGroupId = getParameterAsInt(C_PROJECTGROUP_ID);
		projectClassId = getParameterAsInt(C_PROJECTCLASS_ID);
		dateContract = getParameterAsTimestamp(DATECONTRACT);
		dateFinish = getParameterAsTimestamp(DATEFINISH);
	}

	/**	 Getter Parameter Value for Project Type	*/
	protected int getProjectTypeId() {
		return projectTypeId;
	}

	/**	 Setter Parameter Value for Project Type	*/
	protected void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	/**	 Getter Parameter Value for Project Category	*/
	protected int getProjectCategoryId() {
		return projectCategoryId;
	}

	/**	 Setter Parameter Value for Project Category	*/
	protected void setProjectCategoryId(int projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	/**	 Getter Parameter Value for Project Status	*/
	protected int getProjectStatusId() {
		return projectStatusId;
	}

	/**	 Setter Parameter Value for Project Status	*/
	protected void setProjectStatusId(int projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	/**	 Getter Parameter Value for Project Group	*/
	protected int getProjectGroupId() {
		return projectGroupId;
	}

	/**	 Setter Parameter Value for Project Group	*/
	protected void setProjectGroupId(int projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	/**	 Getter Parameter Value for Project Class	*/
	protected int getProjectClassId() {
		return projectClassId;
	}

	/**	 Setter Parameter Value for Project Class	*/
	protected void setProjectClassId(int projectClassId) {
		this.projectClassId = projectClassId;
	}

	/**	 Getter Parameter Value for Contract Date	*/
	protected Timestamp getDateContract() {
		return dateContract;
	}

	/**	 Setter Parameter Value for Contract Date	*/
	protected void setDateContract(Timestamp dateContract) {
		this.dateContract = dateContract;
	}

	/**	 Getter Parameter Value for Finish Date	*/
	protected Timestamp getDateFinish() {
		return dateFinish;
	}

	/**	 Setter Parameter Value for Finish Date	*/
	protected void setDateFinish(Timestamp dateFinish) {
		this.dateFinish = dateFinish;
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