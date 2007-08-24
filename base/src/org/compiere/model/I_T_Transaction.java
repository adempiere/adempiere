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

    /** Generated Interface for T_Transaction
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:56.218
     */
    public interface I_T_Transaction 
{

    /** TableName=T_Transaction */
    public static final String Table_Name = "T_Transaction";

    /** AD_Table_ID=758 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

    /** Column name C_ProjectIssue_ID */
    public static final String COLUMNNAME_C_ProjectIssue_ID = "C_ProjectIssue_ID";

	/** Set Project Issue.
	  * Project Issues (Material, Labor)
	  */
	public void setC_ProjectIssue_ID (int C_ProjectIssue_ID);

	/** Get Project Issue.
	  * Project Issues (Material, Labor)
	  */
	public int getC_ProjectIssue_ID();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

    /** Column name M_InOut_ID */
    public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

	/** Set Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID);

	/** Get Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getM_InOut_ID();

    /** Column name M_InventoryLine_ID */
    public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";

	/** Set Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID);

	/** Get Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID();

    /** Column name M_Inventory_ID */
    public static final String COLUMNNAME_M_Inventory_ID = "M_Inventory_ID";

	/** Set Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID);

	/** Get Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

    /** Column name M_MovementLine_ID */
    public static final String COLUMNNAME_M_MovementLine_ID = "M_MovementLine_ID";

	/** Set Move Line.
	  * Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID);

	/** Get Move Line.
	  * Inventory Move document Line
	  */
	public int getM_MovementLine_ID();

    /** Column name M_Movement_ID */
    public static final String COLUMNNAME_M_Movement_ID = "M_Movement_ID";

	/** Set Inventory Move.
	  * Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID);

	/** Get Inventory Move.
	  * Movement of Inventory
	  */
	public int getM_Movement_ID();

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

    /** Column name M_ProductionLine_ID */
    public static final String COLUMNNAME_M_ProductionLine_ID = "M_ProductionLine_ID";

	/** Set Production Line.
	  * Document Line representing a production
	  */
	public void setM_ProductionLine_ID (int M_ProductionLine_ID);

	/** Get Production Line.
	  * Document Line representing a production
	  */
	public int getM_ProductionLine_ID();

    /** Column name M_Production_ID */
    public static final String COLUMNNAME_M_Production_ID = "M_Production_ID";

	/** Set Production.
	  * Plan for producing a product
	  */
	public void setM_Production_ID (int M_Production_ID);

	/** Get Production.
	  * Plan for producing a product
	  */
	public int getM_Production_ID();

    /** Column name M_Transaction_ID */
    public static final String COLUMNNAME_M_Transaction_ID = "M_Transaction_ID";

	/** Set Inventory Transaction	  */
	public void setM_Transaction_ID (int M_Transaction_ID);

	/** Get Inventory Transaction	  */
	public int getM_Transaction_ID();

    /** Column name MovementDate */
    public static final String COLUMNNAME_MovementDate = "MovementDate";

	/** Set Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate);

	/** Get Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate();

    /** Column name MovementQty */
    public static final String COLUMNNAME_MovementQty = "MovementQty";

	/** Set Movement Quantity.
	  * Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty);

	/** Get Movement Quantity.
	  * Quantity of a product moved.
	  */
	public BigDecimal getMovementQty();

    /** Column name MovementType */
    public static final String COLUMNNAME_MovementType = "MovementType";

	/** Set Movement Type.
	  * Method of moving the inventory
	  */
	public void setMovementType (String MovementType);

	/** Get Movement Type.
	  * Method of moving the inventory
	  */
	public String getMovementType();

    /** Column name Search_InOut_ID */
    public static final String COLUMNNAME_Search_InOut_ID = "Search_InOut_ID";

	/** Set Search Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setSearch_InOut_ID (int Search_InOut_ID);

	/** Get Search Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getSearch_InOut_ID();

    /** Column name Search_Invoice_ID */
    public static final String COLUMNNAME_Search_Invoice_ID = "Search_Invoice_ID";

	/** Set Search Invoice.
	  * Search Invoice Identifier
	  */
	public void setSearch_Invoice_ID (int Search_Invoice_ID);

	/** Get Search Invoice.
	  * Search Invoice Identifier
	  */
	public int getSearch_Invoice_ID();

    /** Column name Search_Order_ID */
    public static final String COLUMNNAME_Search_Order_ID = "Search_Order_ID";

	/** Set Search Order.
	  * Order Identifier
	  */
	public void setSearch_Order_ID (int Search_Order_ID);

	/** Get Search Order.
	  * Order Identifier
	  */
	public int getSearch_Order_ID();
}
