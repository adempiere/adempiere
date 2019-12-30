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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_CostDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_M_CostDetail 
{

    /** TableName=M_CostDetail */
    public static final String Table_Name = "M_CostDetail";

    /** AD_Table_ID=808 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Amt */
    public static final String COLUMNNAME_Amt = "Amt";

	/** Set Amount.
	  * Amount
	  */
	public void setAmt (BigDecimal Amt);

	/** Get Amount.
	  * Amount
	  */
	public BigDecimal getAmt();

    /** Column name AmtLL */
    public static final String COLUMNNAME_AmtLL = "AmtLL";

	/** Set Amount LL.
	  * Amount Lower Level Cost
	  */
	public void setAmtLL (BigDecimal AmtLL);

	/** Get Amount LL.
	  * Amount Lower Level Cost
	  */
	public BigDecimal getAmtLL();

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

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name C_InvoiceLine_ID */
    public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

	/** Set Invoice Line.
	  * Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID);

	/** Get Invoice Line.
	  * Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID();

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;

    /** Column name C_LandedCostAllocation_ID */
    public static final String COLUMNNAME_C_LandedCostAllocation_ID = "C_LandedCostAllocation_ID";

	/** Set Landed Cost Allocation.
	  * Allocation for Land Costs
	  */
	public void setC_LandedCostAllocation_ID (int C_LandedCostAllocation_ID);

	/** Get Landed Cost Allocation.
	  * Allocation for Land Costs
	  */
	public int getC_LandedCostAllocation_ID();

	public org.compiere.model.I_C_LandedCostAllocation getC_LandedCostAllocation() throws RuntimeException;

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

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

	public org.compiere.model.I_C_ProjectIssue getC_ProjectIssue() throws RuntimeException;

    /** Column name CostAdjustment */
    public static final String COLUMNNAME_CostAdjustment = "CostAdjustment";

	/** Set Cost Adjustment.
	  * Product Cost Adjustment
	  */
	public void setCostAdjustment (BigDecimal CostAdjustment);

	/** Get Cost Adjustment.
	  * Product Cost Adjustment
	  */
	public BigDecimal getCostAdjustment();

    /** Column name CostAdjustmentDate */
    public static final String COLUMNNAME_CostAdjustmentDate = "CostAdjustmentDate";

	/** Set Cost Adjustment Date.
	  * Product Cost Adjustment
	  */
	public void setCostAdjustmentDate (Timestamp CostAdjustmentDate);

	/** Get Cost Adjustment Date.
	  * Product Cost Adjustment
	  */
	public Timestamp getCostAdjustmentDate();

    /** Column name CostAdjustmentDateLL */
    public static final String COLUMNNAME_CostAdjustmentDateLL = "CostAdjustmentDateLL";

	/** Set Cost Adjustment Date.
	  * Date Product Cost Adjustment Lower Level
	  */
	public void setCostAdjustmentDateLL (Timestamp CostAdjustmentDateLL);

	/** Get Cost Adjustment Date.
	  * Date Product Cost Adjustment Lower Level
	  */
	public Timestamp getCostAdjustmentDateLL();

    /** Column name CostAdjustmentLL */
    public static final String COLUMNNAME_CostAdjustmentLL = "CostAdjustmentLL";

	/** Set Cost Adjustment LL.
	  * Product Cost Adjustment Lower Level
	  */
	public void setCostAdjustmentLL (BigDecimal CostAdjustmentLL);

	/** Get Cost Adjustment LL.
	  * Product Cost Adjustment Lower Level
	  */
	public BigDecimal getCostAdjustmentLL();

    /** Column name CostAmt */
    public static final String COLUMNNAME_CostAmt = "CostAmt";

	/** Set Cost Value.
	  * Value with Cost
	  */
	public void setCostAmt (BigDecimal CostAmt);

	/** Get Cost Value.
	  * Value with Cost
	  */
	public BigDecimal getCostAmt();

    /** Column name CostAmtLL */
    public static final String COLUMNNAME_CostAmtLL = "CostAmtLL";

	/** Set Cost Value LL.
	  * Value with Cost Lower Level
	  */
	public void setCostAmtLL (BigDecimal CostAmtLL);

	/** Get Cost Value LL.
	  * Value with Cost Lower Level
	  */
	public BigDecimal getCostAmtLL();

    /** Column name CostingMethod */
    public static final String COLUMNNAME_CostingMethod = "CostingMethod";

	/** Set Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public void setCostingMethod (String CostingMethod);

	/** Get Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public String getCostingMethod();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CumulatedAmt */
    public static final String COLUMNNAME_CumulatedAmt = "CumulatedAmt";

	/** Set Accumulated Amt.
	  * Total Amount
	  */
	public void setCumulatedAmt (BigDecimal CumulatedAmt);

	/** Get Accumulated Amt.
	  * Total Amount
	  */
	public BigDecimal getCumulatedAmt();

    /** Column name CumulatedAmtLL */
    public static final String COLUMNNAME_CumulatedAmtLL = "CumulatedAmtLL";

	/** Set Accumulated Amt LL.
	  * Total Amount
	  */
	public void setCumulatedAmtLL (BigDecimal CumulatedAmtLL);

	/** Get Accumulated Amt LL.
	  * Total Amount
	  */
	public BigDecimal getCumulatedAmtLL();

    /** Column name CumulatedQty */
    public static final String COLUMNNAME_CumulatedQty = "CumulatedQty";

	/** Set Accumulated Qty.
	  * Total Quantity
	  */
	public void setCumulatedQty (BigDecimal CumulatedQty);

	/** Get Accumulated Qty.
	  * Total Quantity
	  */
	public BigDecimal getCumulatedQty();

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

    /** Column name CurrentCostPriceLL */
    public static final String COLUMNNAME_CurrentCostPriceLL = "CurrentCostPriceLL";

	/** Set Current Cost Price LL.
	  * Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.
	  */
	public void setCurrentCostPriceLL (BigDecimal CurrentCostPriceLL);

	/** Get Current Cost Price LL.
	  * Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.
	  */
	public BigDecimal getCurrentCostPriceLL();

    /** Column name CurrentQty */
    public static final String COLUMNNAME_CurrentQty = "CurrentQty";

	/** Set Current Quantity.
	  * Current Quantity
	  */
	public void setCurrentQty (BigDecimal CurrentQty);

	/** Get Current Quantity.
	  * Current Quantity
	  */
	public BigDecimal getCurrentQty();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name DeltaAmt */
    public static final String COLUMNNAME_DeltaAmt = "DeltaAmt";

	/** Set Delta Amount.
	  * Difference Amount
	  */
	public void setDeltaAmt (BigDecimal DeltaAmt);

	/** Get Delta Amount.
	  * Difference Amount
	  */
	public BigDecimal getDeltaAmt();

    /** Column name DeltaQty */
    public static final String COLUMNNAME_DeltaQty = "DeltaQty";

	/** Set Delta Quantity.
	  * Quantity Difference
	  */
	public void setDeltaQty (BigDecimal DeltaQty);

	/** Get Delta Quantity.
	  * Quantity Difference
	  */
	public BigDecimal getDeltaQty();

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

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsReversal */
    public static final String COLUMNNAME_IsReversal = "IsReversal";

	/** Set Reversal.
	  * This is a reversing transaction
	  */
	public void setIsReversal (boolean IsReversal);

	/** Get Reversal.
	  * This is a reversing transaction
	  */
	public boolean isReversal();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

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

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_CostDetail_ID */
    public static final String COLUMNNAME_M_CostDetail_ID = "M_CostDetail_ID";

	/** Set Cost Detail.
	  * Cost Detail Information
	  */
	public void setM_CostDetail_ID (int M_CostDetail_ID);

	/** Get Cost Detail.
	  * Cost Detail Information
	  */
	public int getM_CostDetail_ID();

    /** Column name M_CostElement_ID */
    public static final String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";

	/** Set Cost Element.
	  * Product Cost Element
	  */
	public void setM_CostElement_ID (int M_CostElement_ID);

	/** Get Cost Element.
	  * Product Cost Element
	  */
	public int getM_CostElement_ID();

	public org.compiere.model.I_M_CostElement getM_CostElement() throws RuntimeException;

    /** Column name M_CostType_ID */
    public static final String COLUMNNAME_M_CostType_ID = "M_CostType_ID";

	/** Set Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public void setM_CostType_ID (int M_CostType_ID);

	/** Get Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public int getM_CostType_ID();

	public org.compiere.model.I_M_CostType getM_CostType() throws RuntimeException;

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

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException;

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

	public org.compiere.model.I_M_InventoryLine getM_InventoryLine() throws RuntimeException;

    /** Column name M_MatchInv_ID */
    public static final String COLUMNNAME_M_MatchInv_ID = "M_MatchInv_ID";

	/** Set Match Invoice.
	  * Match Shipment/Receipt to Invoice
	  */
	public void setM_MatchInv_ID (int M_MatchInv_ID);

	/** Get Match Invoice.
	  * Match Shipment/Receipt to Invoice
	  */
	public int getM_MatchInv_ID();

	public org.compiere.model.I_M_MatchInv getM_MatchInv() throws RuntimeException;

    /** Column name M_MatchPO_ID */
    public static final String COLUMNNAME_M_MatchPO_ID = "M_MatchPO_ID";

	/** Set Match PO.
	  * Match Purchase Order to Shipment/Receipt and Invoice
	  */
	public void setM_MatchPO_ID (int M_MatchPO_ID);

	/** Get Match PO.
	  * Match Purchase Order to Shipment/Receipt and Invoice
	  */
	public int getM_MatchPO_ID();

	public org.compiere.model.I_M_MatchPO getM_MatchPO() throws RuntimeException;

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

	public org.compiere.model.I_M_MovementLine getM_MovementLine() throws RuntimeException;

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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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

	public org.compiere.model.I_M_ProductionLine getM_ProductionLine() throws RuntimeException;

    /** Column name M_Transaction_ID */
    public static final String COLUMNNAME_M_Transaction_ID = "M_Transaction_ID";

	/** Set Inventory Transaction	  */
	public void setM_Transaction_ID (int M_Transaction_ID);

	/** Get Inventory Transaction	  */
	public int getM_Transaction_ID();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name PP_Cost_Collector_ID */
    public static final String COLUMNNAME_PP_Cost_Collector_ID = "PP_Cost_Collector_ID";

	/** Set Manufacturing Cost Collector	  */
	public void setPP_Cost_Collector_ID (int PP_Cost_Collector_ID);

	/** Get Manufacturing Cost Collector	  */
	public int getPP_Cost_Collector_ID();

	public org.eevolution.model.I_PP_Cost_Collector getPP_Cost_Collector() throws RuntimeException;

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
