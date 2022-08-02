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

package org.adempiere.production.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Replenish Plan Generate Report)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateReplenishPlanReportAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ReplenishPlan_Generate_Report";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Replenish Plan Generate Report";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53778;
	/**	Parameter Name for Replenish Plan Run	*/
	public static final String M_REPLENISHPLAN_ID = "M_ReplenishPlan_ID";
	/**	Parameter Name for Product Category	*/
	public static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Has Supply/Demand	*/
	public static final String HASSUPPLYDEMAND = "HasSupplyDemand";
	/**	Parameter Value for Replenish Plan Run	*/
	private int replenishPlanId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Has Supply/Demand	*/
	private boolean isHasSupplyDemand;

	@Override
	protected void prepare() {
		replenishPlanId = getParameterAsInt(M_REPLENISHPLAN_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		isHasSupplyDemand = getParameterAsBoolean(HASSUPPLYDEMAND);
	}

	/**	 Getter Parameter Value for Replenish Plan Run	*/
	protected int getReplenishPlanId() {
		return replenishPlanId;
	}

	/**	 Setter Parameter Value for Replenish Plan Run	*/
	protected void setReplenishPlanId(int replenishPlanId) {
		this.replenishPlanId = replenishPlanId;
	}

	/**	 Getter Parameter Value for Product Category	*/
	protected int getProductCategoryId() {
		return productCategoryId;
	}

	/**	 Setter Parameter Value for Product Category	*/
	protected void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Has Supply/Demand	*/
	protected boolean isHasSupplyDemand() {
		return isHasSupplyDemand;
	}

	/**	 Setter Parameter Value for Has Supply/Demand	*/
	protected void setIsHasSupplyDemand(boolean isHasSupplyDemand) {
		this.isHasSupplyDemand = isHasSupplyDemand;
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