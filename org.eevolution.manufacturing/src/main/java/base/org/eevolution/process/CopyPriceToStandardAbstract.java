/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
/** Generated Process for (Copy Price to Standard Cost)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class CopyPriceToStandardAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "PP_Copy Price to Standard Cost";
	/** Process Name 	*/
	private static final String NAME = "Copy Price to Standard Cost";
	/** Process Id 	*/
	private static final int ID = 53056;
 
	/**	Parameter Name for IsCostFrozen	*/
	public static final String IsCostFrozen = "IsCostFrozen";
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for M_CostType_ID	*/
	public static final String M_CostType_ID = "M_CostType_ID";
	/**	Parameter Name for M_CostElement_ID	*/
	public static final String M_CostElement_ID = "M_CostElement_ID";
	/**	Parameter Name for M_PriceList_Version_ID	*/
	public static final String M_PriceList_Version_ID = "M_PriceList_Version_ID";

	/**	Parameter Value for isstFrozen	*/
	private boolean isstFrozen;
	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for warehouseId	*/
	private int warehouseId;
	/**	Parameter Value for costTypeId	*/
	private int costTypeId;
	/**	Parameter Value for costElementId	*/
	private int costElementId;
	/**	Parameter Value for priceListVersionId	*/
	private int priceListVersionId;
 

	@Override
	protected void prepare()
	{
		isstFrozen = getParameterAsBoolean(IsCostFrozen);
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		organizationId = getParameterAsInt(AD_Org_ID);
		warehouseId = getParameterAsInt(M_Warehouse_ID);
		costTypeId = getParameterAsInt(M_CostType_ID);
		costElementId = getParameterAsInt(M_CostElement_ID);
		priceListVersionId = getParameterAsInt(M_PriceList_Version_ID);
	}

	/**	 Getter Parameter Value for isstFrozen	*/
	protected boolean isstFrozen() {
		return isstFrozen;
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for warehouseId	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Getter Parameter Value for costTypeId	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Getter Parameter Value for costElementId	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Getter Parameter Value for priceListVersionId	*/
	protected int getPriceListVersionId() {
		return priceListVersionId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}