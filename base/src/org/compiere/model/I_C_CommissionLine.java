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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for C_CommissionLine
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:39.218
     */
    public interface I_C_CommissionLine 
{

    /** TableName=C_CommissionLine */
    public static final String Table_Name = "C_CommissionLine";

    /** AD_Table_ID=431 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

    /** Column name AmtMultiplier */
    public static final String COLUMNNAME_AmtMultiplier = "AmtMultiplier";

	/** Set Multiplier Amount.
	  * Multiplier Amount for generating commissions
	  */
	public void setAmtMultiplier (BigDecimal AmtMultiplier);

	/** Get Multiplier Amount.
	  * Multiplier Amount for generating commissions
	  */
	public BigDecimal getAmtMultiplier();

    /** Column name AmtSubtract */
    public static final String COLUMNNAME_AmtSubtract = "AmtSubtract";

	/** Set Subtract Amount.
	  * Subtract Amount for generating commissions
	  */
	public void setAmtSubtract (BigDecimal AmtSubtract);

	/** Get Subtract Amount.
	  * Subtract Amount for generating commissions
	  */
	public BigDecimal getAmtSubtract();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getI_C_BP_Group() throws Exception;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

    /** Column name C_CommissionLine_ID */
    public static final String COLUMNNAME_C_CommissionLine_ID = "C_CommissionLine_ID";

	/** Set Commission Line.
	  * Commission Line
	  */
	public void setC_CommissionLine_ID (int C_CommissionLine_ID);

	/** Get Commission Line.
	  * Commission Line
	  */
	public int getC_CommissionLine_ID();

    /** Column name C_Commission_ID */
    public static final String COLUMNNAME_C_Commission_ID = "C_Commission_ID";

	/** Set Commission.
	  * Commission
	  */
	public void setC_Commission_ID (int C_Commission_ID);

	/** Get Commission.
	  * Commission
	  */
	public int getC_Commission_ID();

	public I_C_Commission getI_C_Commission() throws Exception;

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public I_C_SalesRegion getI_C_SalesRegion() throws Exception;

    /** Column name CommissionOrders */
    public static final String COLUMNNAME_CommissionOrders = "CommissionOrders";

	/** Set Commission only specified Orders.
	  * Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public void setCommissionOrders (boolean CommissionOrders);

	/** Get Commission only specified Orders.
	  * Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public boolean isCommissionOrders();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsPositiveOnly */
    public static final String COLUMNNAME_IsPositiveOnly = "IsPositiveOnly";

	/** Set Positive only.
	  * Do not generate negative commissions
	  */
	public void setIsPositiveOnly (boolean IsPositiveOnly);

	/** Get Positive only.
	  * Do not generate negative commissions
	  */
	public boolean isPositiveOnly();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getI_M_Product_Category() throws Exception;

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

    /** Column name Org_ID */
    public static final String COLUMNNAME_Org_ID = "Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getOrg_ID();

    /** Column name QtyMultiplier */
    public static final String COLUMNNAME_QtyMultiplier = "QtyMultiplier";

	/** Set Multiplier Quantity.
	  * Value to multiply quantities by for generating commissions.
	  */
	public void setQtyMultiplier (BigDecimal QtyMultiplier);

	/** Get Multiplier Quantity.
	  * Value to multiply quantities by for generating commissions.
	  */
	public BigDecimal getQtyMultiplier();

    /** Column name QtySubtract */
    public static final String COLUMNNAME_QtySubtract = "QtySubtract";

	/** Set Subtract Quantity.
	  * Quantity to subtract when generating commissions
	  */
	public void setQtySubtract (BigDecimal QtySubtract);

	/** Get Subtract Quantity.
	  * Quantity to subtract when generating commissions
	  */
	public BigDecimal getQtySubtract();
}
