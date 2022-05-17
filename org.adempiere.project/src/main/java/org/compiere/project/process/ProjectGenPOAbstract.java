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

package org.compiere.project.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Generate PO from Project)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ProjectGenPOAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Project_GeneratePO";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate PO from Project";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 225;
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Project Line	*/
	public static final String C_PROJECTLINE_ID = "C_ProjectLine_ID";
	/**	Parameter Name for Vendor	*/
	public static final String VENDOR_ID = "Vendor_ID";
	/**	Parameter Name for Consolidate to one Document	*/
	public static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Project Line	*/
	private int projectLineId;
	/**	Parameter Value for Vendor	*/
	private int vendorId;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;

	@Override
	protected void prepare() {
		projectId = getParameterAsInt(C_PROJECT_ID);
		projectLineId = getParameterAsInt(C_PROJECTLINE_ID);
		vendorId = getParameterAsInt(VENDOR_ID);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Project Line	*/
	protected int getProjectLineId() {
		return projectLineId;
	}

	/**	 Setter Parameter Value for Project Line	*/
	protected void setProjectLineId(int projectLineId) {
		this.projectLineId = projectLineId;
	}

	/**	 Getter Parameter Value for Vendor	*/
	protected int getVendorId() {
		return vendorId;
	}

	/**	 Setter Parameter Value for Vendor	*/
	protected void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	/**	 Getter Parameter Value for Consolidate to one Document	*/
	protected boolean isConsolidateDocument() {
		return isConsolidateDocument;
	}

	/**	 Setter Parameter Value for Consolidate to one Document	*/
	protected void setConsolidateDocument(boolean isConsolidateDocument) {
		this.isConsolidateDocument = isConsolidateDocument;
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