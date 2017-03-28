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
/** Generated Process for (Landed Cost Generate to based on Receipts)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateLandedCostAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "C_LandedCost Receipts";
	/** Process Name 	*/
	private static final String NAME = "Landed Cost Generate to based on Receipts";
	/** Process Id 	*/
	private static final int ID = 53690;
 
	/**	Parameter Name for M_CostElement_ID	*/
	public static final String M_CostElement_ID = "M_CostElement_ID";
	/**	Parameter Name for LandedCostDistribution	*/
	public static final String LandedCostDistribution = "LandedCostDistribution";
	/**	Parameter Name for IsCreateByProduct	*/
	public static final String IsCreateByProduct = "IsCreateByProduct";

	/**	Parameter Value for costElementId	*/
	private int costElementId;
	/**	Parameter Value for costDistribution	*/
	private String costDistribution;
	/**	Parameter Value for isCreatebyProduct	*/
	private boolean isCreatebyProduct;
 

	@Override
	protected void prepare()
	{
		costElementId = getParameterAsInt(M_CostElement_ID);
		costDistribution = getParameterAsString(LandedCostDistribution);
		isCreatebyProduct = getParameterAsBoolean(IsCreateByProduct);
	}

	/**	 Getter Parameter Value for costElementId	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Getter Parameter Value for costDistribution	*/
	protected String getCostDistribution() {
		return costDistribution;
	}

	/**	 Getter Parameter Value for isCreatebyProduct	*/
	protected boolean isCreatebyProduct() {
		return isCreatebyProduct;
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