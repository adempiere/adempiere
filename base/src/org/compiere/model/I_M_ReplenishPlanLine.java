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

/** Generated Interface for M_ReplenishPlanLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_M_ReplenishPlanLine 
{

    /** TableName=M_ReplenishPlanLine */
    public static final String Table_Name = "M_ReplenishPlanLine";

    /** AD_Table_ID=53975 */
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

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

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

    /** Column name DateFinish */
    public static final String COLUMNNAME_DateFinish = "DateFinish";

	/** Set Finish Date.
	  * Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish);

	/** Get Finish Date.
	  * Finish or (planned) completion date
	  */
	public Timestamp getDateFinish();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

    /** Column name HasSupplyDemand */
    public static final String COLUMNNAME_HasSupplyDemand = "HasSupplyDemand";

	/** Set Has Supply/Demand.
	  * This product has supply or demand in the current Replenish Plan run.
	  */
	public void setHasSupplyDemand (boolean HasSupplyDemand);

	/** Get Has Supply/Demand.
	  * This product has supply or demand in the current Replenish Plan run.
	  */
	public boolean isHasSupplyDemand();

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

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

	public org.compiere.model.I_M_Production getM_Production() throws RuntimeException;

    /** Column name M_ReplenishPlanLine_ID */
    public static final String COLUMNNAME_M_ReplenishPlanLine_ID = "M_ReplenishPlanLine_ID";

	/** Set M_ReplenishPlanLine ID	  */
	public void setM_ReplenishPlanLine_ID (int M_ReplenishPlanLine_ID);

	/** Get M_ReplenishPlanLine ID	  */
	public int getM_ReplenishPlanLine_ID();

    /** Column name M_ReplenishPlan_ID */
    public static final String COLUMNNAME_M_ReplenishPlan_ID = "M_ReplenishPlan_ID";

	/** Set M_ReplenishPlan ID	  */
	public void setM_ReplenishPlan_ID (int M_ReplenishPlan_ID);

	/** Get M_ReplenishPlan ID	  */
	public int getM_ReplenishPlan_ID();

	public org.compiere.model.I_M_ReplenishPlan getM_ReplenishPlan() throws RuntimeException;

    /** Column name M_Requisition_ID */
    public static final String COLUMNNAME_M_Requisition_ID = "M_Requisition_ID";

	/** Set Requisition.
	  * Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID);

	/** Get Requisition.
	  * Material Requisition
	  */
	public int getM_Requisition_ID();

	public org.compiere.model.I_M_Requisition getM_Requisition() throws RuntimeException;

    /** Column name OrderInfo */
    public static final String COLUMNNAME_OrderInfo = "OrderInfo";

	/** Set Order Info	  */
	public void setOrderInfo (String OrderInfo);

	/** Get Order Info	  */
	public String getOrderInfo();

    /** Column name ProductName */
    public static final String COLUMNNAME_ProductName = "ProductName";

	/** Set Product Name.
	  * Name of the Product
	  */
	public void setProductName (String ProductName);

	/** Get Product Name.
	  * Name of the Product
	  */
	public String getProductName();

    /** Column name ProductionInfo */
    public static final String COLUMNNAME_ProductionInfo = "ProductionInfo";

	/** Set Production Info	  */
	public void setProductionInfo (String ProductionInfo);

	/** Get Production Info	  */
	public String getProductionInfo();

    /** Column name RecordType */
    public static final String COLUMNNAME_RecordType = "RecordType";

	/** Set RecordType	  */
	public void setRecordType (String RecordType);

	/** Get RecordType	  */
	public String getRecordType();

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

    /** Column name Week1 */
    public static final String COLUMNNAME_Week1 = "Week1";

	/** Set Week1	  */
	public void setWeek1 (BigDecimal Week1);

	/** Get Week1	  */
	public BigDecimal getWeek1();

    /** Column name Week10 */
    public static final String COLUMNNAME_Week10 = "Week10";

	/** Set Week10	  */
	public void setWeek10 (BigDecimal Week10);

	/** Get Week10	  */
	public BigDecimal getWeek10();

    /** Column name Week11 */
    public static final String COLUMNNAME_Week11 = "Week11";

	/** Set Week11	  */
	public void setWeek11 (BigDecimal Week11);

	/** Get Week11	  */
	public BigDecimal getWeek11();

    /** Column name Week12 */
    public static final String COLUMNNAME_Week12 = "Week12";

	/** Set Week12	  */
	public void setWeek12 (BigDecimal Week12);

	/** Get Week12	  */
	public BigDecimal getWeek12();

    /** Column name Week13 */
    public static final String COLUMNNAME_Week13 = "Week13";

	/** Set Week13	  */
	public void setWeek13 (BigDecimal Week13);

	/** Get Week13	  */
	public BigDecimal getWeek13();

    /** Column name Week14 */
    public static final String COLUMNNAME_Week14 = "Week14";

	/** Set Week14	  */
	public void setWeek14 (BigDecimal Week14);

	/** Get Week14	  */
	public BigDecimal getWeek14();

    /** Column name Week15 */
    public static final String COLUMNNAME_Week15 = "Week15";

	/** Set Week15	  */
	public void setWeek15 (BigDecimal Week15);

	/** Get Week15	  */
	public BigDecimal getWeek15();

    /** Column name Week16 */
    public static final String COLUMNNAME_Week16 = "Week16";

	/** Set Week16	  */
	public void setWeek16 (BigDecimal Week16);

	/** Get Week16	  */
	public BigDecimal getWeek16();

    /** Column name Week17 */
    public static final String COLUMNNAME_Week17 = "Week17";

	/** Set Week17	  */
	public void setWeek17 (BigDecimal Week17);

	/** Get Week17	  */
	public BigDecimal getWeek17();

    /** Column name Week18 */
    public static final String COLUMNNAME_Week18 = "Week18";

	/** Set Week18	  */
	public void setWeek18 (BigDecimal Week18);

	/** Get Week18	  */
	public BigDecimal getWeek18();

    /** Column name Week19 */
    public static final String COLUMNNAME_Week19 = "Week19";

	/** Set Week19	  */
	public void setWeek19 (BigDecimal Week19);

	/** Get Week19	  */
	public BigDecimal getWeek19();

    /** Column name Week2 */
    public static final String COLUMNNAME_Week2 = "Week2";

	/** Set Week2	  */
	public void setWeek2 (BigDecimal Week2);

	/** Get Week2	  */
	public BigDecimal getWeek2();

    /** Column name Week20 */
    public static final String COLUMNNAME_Week20 = "Week20";

	/** Set Week20	  */
	public void setWeek20 (BigDecimal Week20);

	/** Get Week20	  */
	public BigDecimal getWeek20();

    /** Column name Week21 */
    public static final String COLUMNNAME_Week21 = "Week21";

	/** Set Week21	  */
	public void setWeek21 (BigDecimal Week21);

	/** Get Week21	  */
	public BigDecimal getWeek21();

    /** Column name Week22 */
    public static final String COLUMNNAME_Week22 = "Week22";

	/** Set Week22	  */
	public void setWeek22 (BigDecimal Week22);

	/** Get Week22	  */
	public BigDecimal getWeek22();

    /** Column name Week23 */
    public static final String COLUMNNAME_Week23 = "Week23";

	/** Set Week23	  */
	public void setWeek23 (BigDecimal Week23);

	/** Get Week23	  */
	public BigDecimal getWeek23();

    /** Column name Week24 */
    public static final String COLUMNNAME_Week24 = "Week24";

	/** Set Week24	  */
	public void setWeek24 (BigDecimal Week24);

	/** Get Week24	  */
	public BigDecimal getWeek24();

    /** Column name Week3 */
    public static final String COLUMNNAME_Week3 = "Week3";

	/** Set Week3	  */
	public void setWeek3 (BigDecimal Week3);

	/** Get Week3	  */
	public BigDecimal getWeek3();

    /** Column name Week4 */
    public static final String COLUMNNAME_Week4 = "Week4";

	/** Set Week4	  */
	public void setWeek4 (BigDecimal Week4);

	/** Get Week4	  */
	public BigDecimal getWeek4();

    /** Column name Week5 */
    public static final String COLUMNNAME_Week5 = "Week5";

	/** Set Week5	  */
	public void setWeek5 (BigDecimal Week5);

	/** Get Week5	  */
	public BigDecimal getWeek5();

    /** Column name Week6 */
    public static final String COLUMNNAME_Week6 = "Week6";

	/** Set Week6	  */
	public void setWeek6 (BigDecimal Week6);

	/** Get Week6	  */
	public BigDecimal getWeek6();

    /** Column name Week7 */
    public static final String COLUMNNAME_Week7 = "Week7";

	/** Set Week7	  */
	public void setWeek7 (BigDecimal Week7);

	/** Get Week7	  */
	public BigDecimal getWeek7();

    /** Column name Week8 */
    public static final String COLUMNNAME_Week8 = "Week8";

	/** Set Week8	  */
	public void setWeek8 (BigDecimal Week8);

	/** Get Week8	  */
	public BigDecimal getWeek8();

    /** Column name Week9 */
    public static final String COLUMNNAME_Week9 = "Week9";

	/** Set Week9	  */
	public void setWeek9 (BigDecimal Week9);

	/** Get Week9	  */
	public BigDecimal getWeek9();
}
