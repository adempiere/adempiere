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

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.process.SvrProcess;

/** Generated Process for (Issue to Project)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ProjectIssueAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Project_Issue";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Issue to Project";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 224;
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Shipment/Receipt	*/
	public static final String M_INOUT_ID = "M_InOut_ID";
	/**	Parameter Name for Expense Report	*/
	public static final String S_TIMEEXPENSE_ID = "S_TimeExpense_ID";
	/**	Parameter Name for Locator	*/
	public static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Project Line	*/
	public static final String C_PROJECTLINE_ID = "C_ProjectLine_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Attribute Set Instance	*/
	public static final String M_ATTRIBUTESETINSTANCE_ID = "M_AttributeSetInstance_ID";
	/**	Parameter Name for Movement Quantity	*/
	public static final String MOVEMENTQTY = "MovementQty";
	/**	Parameter Name for Movement Date	*/
	public static final String MOVEMENTDATE = "MovementDate";
	/**	Parameter Name for Description	*/
	public static final String DESCRIPTION = "Description";
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Shipment/Receipt	*/
	private int inOutId;
	/**	Parameter Value for Expense Report	*/
	private int timeExpenseId;
	/**	Parameter Value for Locator	*/
	private int locatorId;
	/**	Parameter Value for Project Line	*/
	private int projectLineId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Attribute Set Instance	*/
	private int attributeSetInstance;
	/**	Parameter Value for Movement Quantity	*/
	private BigDecimal movementQty;
	/**	Parameter Value for Movement Date	*/
	private Timestamp movementDate;
	/**	Parameter Value for Description	*/
	private String description;

	@Override
	protected void prepare() {
		projectId = getParameterAsInt(C_PROJECT_ID);
		inOutId = getParameterAsInt(M_INOUT_ID);
		timeExpenseId = getParameterAsInt(S_TIMEEXPENSE_ID);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		projectLineId = getParameterAsInt(C_PROJECTLINE_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		attributeSetInstance = getParameterAsInt(M_ATTRIBUTESETINSTANCE_ID);
		movementQty = getParameterAsBigDecimal(MOVEMENTQTY);
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
		description = getParameterAsString(DESCRIPTION);
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Shipment/Receipt	*/
	protected int getInOutId() {
		return inOutId;
	}

	/**	 Setter Parameter Value for Shipment/Receipt	*/
	protected void setInOutId(int inOutId) {
		this.inOutId = inOutId;
	}

	/**	 Getter Parameter Value for Expense Report	*/
	protected int getTimeExpenseId() {
		return timeExpenseId;
	}

	/**	 Setter Parameter Value for Expense Report	*/
	protected void setTimeExpenseId(int timeExpenseId) {
		this.timeExpenseId = timeExpenseId;
	}

	/**	 Getter Parameter Value for Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
	}

	/**	 Getter Parameter Value for Project Line	*/
	protected int getProjectLineId() {
		return projectLineId;
	}

	/**	 Setter Parameter Value for Project Line	*/
	protected void setProjectLineId(int projectLineId) {
		this.projectLineId = projectLineId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Attribute Set Instance	*/
	protected int getAttributeSetInstance() {
		return attributeSetInstance;
	}

	/**	 Setter Parameter Value for Attribute Set Instance	*/
	protected void setAttributeSetInstance(int attributeSetInstance) {
		this.attributeSetInstance = attributeSetInstance;
	}

	/**	 Getter Parameter Value for Movement Quantity	*/
	protected BigDecimal getMovementQty() {
		if (movementQty == null)
			movementQty = BigDecimal.ZERO;

		return movementQty;
	}

	/**	 Setter Parameter Value for Movement Quantity	*/
	protected void setMovementQty(BigDecimal movementQty) {
		this.movementQty = movementQty;
	}

	/**	 Getter Parameter Value for Movement Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Movement Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
	}

	/**	 Getter Parameter Value for Description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Setter Parameter Value for Description	*/
	protected void setDescription(String description) {
		this.description = description;
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