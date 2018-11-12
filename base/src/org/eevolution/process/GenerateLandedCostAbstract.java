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

package org.eevolution.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Landed Cost Generate to based on Receipts)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateLandedCostAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_LandedCost Receipts";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Landed Cost Generate to based on Receipts";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53690;
	/**	Parameter Name for Cost Element	*/
	public static final String M_COSTELEMENT_ID = "M_CostElement_ID";
	/**	Parameter Name for Cost Distribution	*/
	public static final String LANDEDCOSTDISTRIBUTION = "LandedCostDistribution";
	/**	Parameter Name for Create by Product	*/
	public static final String ISCREATEBYPRODUCT = "IsCreateByProduct";
	/**	Parameter Name for LandedCostType ID	*/
	public static final String C_LANDEDCOSTTYPE_ID = "C_LandedCostType_ID";
	/**	Parameter Value for Cost Element	*/
	private int costElementId;
	/**	Parameter Value for Cost Distribution	*/
	private String landedCostDistribution;
	/**	Parameter Value for Create by Product	*/
	private boolean isCreateByProduct;
	/**	Parameter Value for LandedCostType ID	*/
	private int landedCostTypeId;

	@Override
	protected void prepare() {
		costElementId = getParameterAsInt(M_COSTELEMENT_ID);
		landedCostDistribution = getParameterAsString(LANDEDCOSTDISTRIBUTION);
		isCreateByProduct = getParameterAsBoolean(ISCREATEBYPRODUCT);
		landedCostTypeId = getParameterAsInt(C_LANDEDCOSTTYPE_ID);
	}

	/**	 Getter Parameter Value for Cost Element	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Setter Parameter Value for Cost Element	*/
	protected void setCostElementId(int costElementId) {
		this.costElementId = costElementId;
	}

	/**	 Getter Parameter Value for Cost Distribution	*/
	protected String getLandedCostDistribution() {
		return landedCostDistribution;
	}

	/**	 Setter Parameter Value for Cost Distribution	*/
	protected void setLandedCostDistribution(String landedCostDistribution) {
		this.landedCostDistribution = landedCostDistribution;
	}

	/**	 Getter Parameter Value for Create by Product	*/
	protected boolean isCreateByProduct() {
		return isCreateByProduct;
	}

	/**	 Setter Parameter Value for Create by Product	*/
	protected void setIsCreateByProduct(boolean isCreateByProduct) {
		this.isCreateByProduct = isCreateByProduct;
	}

	/**	 Getter Parameter Value for LandedCostType ID	*/
	protected int getLandedCostTypeId() {
		return landedCostTypeId;
	}

	/**	 Setter Parameter Value for LandedCostType ID	*/
	protected void setLandedCostTypeId(int landedCostTypeId) {
		this.landedCostTypeId = landedCostTypeId;
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