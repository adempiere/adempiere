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

package org.eevolution.manufacturing.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Manufacturing Order from Project)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ProjectGenerateManufacturingOrderAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Project_GenerateMO";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Manufacturing Order from Project";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54077;
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Project Phase	*/
	public static final String C_PROJECTPHASE_ID = "C_ProjectPhase_ID";
	/**	Parameter Name for Project Task	*/
	public static final String C_PROJECTTASK_ID = "C_ProjectTask_ID";
	/**	Parameter Name for Project Line	*/
	public static final String C_PROJECTLINE_ID = "C_ProjectLine_ID";
	/**	Parameter Name for BOM & Formula	*/
	public static final String PP_PRODUCT_BOM_ID = "PP_Product_BOM_ID";
	/**	Parameter Name for Workflow	*/
	public static final String AD_WORKFLOW_ID = "AD_Workflow_ID";
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Project Phase	*/
	private int projectPhaseId;
	/**	Parameter Value for Project Task	*/
	private int projectTaskId;
	/**	Parameter Value for Project Line	*/
	private int projectLineId;
	/**	Parameter Value for BOM & Formula	*/
	private int productBOMId;
	/**	Parameter Value for Workflow	*/
	private int workflowId;

	@Override
	protected void prepare() {
		projectId = getParameterAsInt(C_PROJECT_ID);
		projectPhaseId = getParameterAsInt(C_PROJECTPHASE_ID);
		projectTaskId = getParameterAsInt(C_PROJECTTASK_ID);
		projectLineId = getParameterAsInt(C_PROJECTLINE_ID);
		productBOMId = getParameterAsInt(PP_PRODUCT_BOM_ID);
		workflowId = getParameterAsInt(AD_WORKFLOW_ID);
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Project Phase	*/
	protected int getProjectPhaseId() {
		return projectPhaseId;
	}

	/**	 Setter Parameter Value for Project Phase	*/
	protected void setProjectPhaseId(int projectPhaseId) {
		this.projectPhaseId = projectPhaseId;
	}

	/**	 Getter Parameter Value for Project Task	*/
	protected int getProjectTaskId() {
		return projectTaskId;
	}

	/**	 Setter Parameter Value for Project Task	*/
	protected void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	/**	 Getter Parameter Value for Project Line	*/
	protected int getProjectLineId() {
		return projectLineId;
	}

	/**	 Setter Parameter Value for Project Line	*/
	protected void setProjectLineId(int projectLineId) {
		this.projectLineId = projectLineId;
	}

	/**	 Getter Parameter Value for BOM & Formula	*/
	protected int getProductBOMId() {
		return productBOMId;
	}

	/**	 Setter Parameter Value for BOM & Formula	*/
	protected void setProductBOMId(int productBOMId) {
		this.productBOMId = productBOMId;
	}

	/**	 Getter Parameter Value for Workflow	*/
	protected int getWorkflowId() {
		return workflowId;
	}

	/**	 Setter Parameter Value for Workflow	*/
	protected void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
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