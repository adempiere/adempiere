/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Product_Costing
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_M_Product_Costing 
{

    /** TableName=M_Product_Costing */
    public static final String Table_Name = "M_Product_Costing";

    /** AD_Table_ID=327 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws Exception;

    /** Column name CostAverage */
    public static final String COLUMNNAME_CostAverage = "CostAverage";

	/** Set Average Cost.
	  * Weighted average costs
	  */
	public void setCostAverage (BigDecimal CostAverage);

	/** Get Average Cost.
	  * Weighted average costs
	  */
	public BigDecimal getCostAverage();

    /** Column name CostAverageCumAmt */
    public static final String COLUMNNAME_CostAverageCumAmt = "CostAverageCumAmt";

	/** Set Average Cost Amount Sum.
	  * Cumulative average cost amounts (internal)
	  */
	public void setCostAverageCumAmt (BigDecimal CostAverageCumAmt);

	/** Get Average Cost Amount Sum.
	  * Cumulative average cost amounts (internal)
	  */
	public BigDecimal getCostAverageCumAmt();

    /** Column name CostAverageCumQty */
    public static final String COLUMNNAME_CostAverageCumQty = "CostAverageCumQty";

	/** Set Average Cost Quantity Sum.
	  * Cumulative average cost quantities (internal)
	  */
	public void setCostAverageCumQty (BigDecimal CostAverageCumQty);

	/** Get Average Cost Quantity Sum.
	  * Cumulative average cost quantities (internal)
	  */
	public BigDecimal getCostAverageCumQty();

    /** Column name CostStandard */
    public static final String COLUMNNAME_CostStandard = "CostStandard";

	/** Set Standard Cost.
	  * Standard Costs
	  */
	public void setCostStandard (BigDecimal CostStandard);

	/** Get Standard Cost.
	  * Standard Costs
	  */
	public BigDecimal getCostStandard();

    /** Column name CostStandardCumAmt */
    public static final String COLUMNNAME_CostStandardCumAmt = "CostStandardCumAmt";

	/** Set Std Cost Amount Sum.
	  * Standard Cost Invoice Amount Sum (internal)
	  */
	public void setCostStandardCumAmt (BigDecimal CostStandardCumAmt);

	/** Get Std Cost Amount Sum.
	  * Standard Cost Invoice Amount Sum (internal)
	  */
	public BigDecimal getCostStandardCumAmt();

    /** Column name CostStandardCumQty */
    public static final String COLUMNNAME_CostStandardCumQty = "CostStandardCumQty";

	/** Set Std Cost Quantity Sum.
	  * Standard Cost Invoice Quantity Sum (internal)
	  */
	public void setCostStandardCumQty (BigDecimal CostStandardCumQty);

	/** Get Std Cost Quantity Sum.
	  * Standard Cost Invoice Quantity Sum (internal)
	  */
	public BigDecimal getCostStandardCumQty();

    /** Column name CostStandardPOAmt */
    public static final String COLUMNNAME_CostStandardPOAmt = "CostStandardPOAmt";

	/** Set Std PO Cost Amount Sum.
	  * Standard Cost Purchase Order Amount Sum (internal)
	  */
	public void setCostStandardPOAmt (BigDecimal CostStandardPOAmt);

	/** Get Std PO Cost Amount Sum.
	  * Standard Cost Purchase Order Amount Sum (internal)
	  */
	public BigDecimal getCostStandardPOAmt();

    /** Column name CostStandardPOQty */
    public static final String COLUMNNAME_CostStandardPOQty = "CostStandardPOQty";

	/** Set Std PO Cost Quantity Sum.
	  * Standard Cost Purchase Order Quantity Sum (internal)
	  */
	public void setCostStandardPOQty (BigDecimal CostStandardPOQty);

	/** Get Std PO Cost Quantity Sum.
	  * Standard Cost Purchase Order Quantity Sum (internal)
	  */
	public BigDecimal getCostStandardPOQty();

    /** Column name CurrentCostPrice */
    public static final String COLUMNNAME_CurrentCostPrice = "CurrentCostPrice";

	/** Set Current Cost Price.
	  * The currently used cost price
	  */
	public void setCurrentCostPrice (BigDecimal CurrentCostPrice);

	/** Get Current Cost Price.
	  * The currently used cost price
	  */
	public BigDecimal getCurrentCostPrice();

    /** Column name FutureCostPrice */
    public static final String COLUMNNAME_FutureCostPrice = "FutureCostPrice";

	/** Set Future Cost Price	  */
	public void setFutureCostPrice (BigDecimal FutureCostPrice);

	/** Get Future Cost Price	  */
	public BigDecimal getFutureCostPrice();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws Exception;

    /** Column name PriceLastInv */
    public static final String COLUMNNAME_PriceLastInv = "PriceLastInv";

	/** Set Last Invoice Price.
	  * Price of the last invoice for the product
	  */
	public void setPriceLastInv (BigDecimal PriceLastInv);

	/** Get Last Invoice Price.
	  * Price of the last invoice for the product
	  */
	public BigDecimal getPriceLastInv();

    /** Column name PriceLastPO */
    public static final String COLUMNNAME_PriceLastPO = "PriceLastPO";

	/** Set Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO);

	/** Get Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO();

    /** Column name TotalInvAmt */
    public static final String COLUMNNAME_TotalInvAmt = "TotalInvAmt";

	/** Set Total Invoice Amount.
	  * Cumulative total lifetime invoice amount
	  */
	public void setTotalInvAmt (BigDecimal TotalInvAmt);

	/** Get Total Invoice Amount.
	  * Cumulative total lifetime invoice amount
	  */
	public BigDecimal getTotalInvAmt();

    /** Column name TotalInvQty */
    public static final String COLUMNNAME_TotalInvQty = "TotalInvQty";

	/** Set Total Invoice Quantity.
	  * Cumulative total lifetime invoice quantity
	  */
	public void setTotalInvQty (BigDecimal TotalInvQty);

	/** Get Total Invoice Quantity.
	  * Cumulative total lifetime invoice quantity
	  */
	public BigDecimal getTotalInvQty();
}
