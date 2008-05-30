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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_Order_BOMLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_PP_Order_BOMLine 
{

    /** TableName=PP_Order_BOMLine */
    public static final String Table_Name = "PP_Order_BOMLine";

    /** AD_Table_ID=53025 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name Assay */
    public static final String COLUMNNAME_Assay = "Assay";

	/** Set Quantity Assay.
	  * Indicated the Quantity Assay to use into Quality Order
	  */
	public void setAssay (BigDecimal Assay);

	/** Get Quantity Assay.
	  * Indicated the Quantity Assay to use into Quality Order
	  */
	public BigDecimal getAssay();

    /** Column name BackflushGroup */
    public static final String COLUMNNAME_BackflushGroup = "BackflushGroup";

	/** Set Backflush Group.
	  * The Grouping Components to the Backflush
	  */
	public void setBackflushGroup (String BackflushGroup);

	/** Get Backflush Group.
	  * The Grouping Components to the Backflush
	  */
	public String getBackflushGroup();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws Exception;

    /** Column name ComponentType */
    public static final String COLUMNNAME_ComponentType = "ComponentType";

	/** Set Component Type.
	  * Component Type for a Bill of Material or Formula
	  */
	public void setComponentType (String ComponentType);

	/** Get Component Type.
	  * Component Type for a Bill of Material or Formula
	  */
	public String getComponentType();

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

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

    /** Column name Feature */
    public static final String COLUMNNAME_Feature = "Feature";

	/** Set Feature.
	  * Indicated the Feature for Product Configure
	  */
	public void setFeature (String Feature);

	/** Get Feature.
	  * Indicated the Feature for Product Configure
	  */
	public String getFeature();

    /** Column name Forecast */
    public static final String COLUMNNAME_Forecast = "Forecast";

	/** Set Forecast.
	  * Indicated the % of participation this component into a of the BOM Planning
	  */
	public void setForecast (BigDecimal Forecast);

	/** Get Forecast.
	  * Indicated the % of participation this component into a of the BOM Planning
	  */
	public BigDecimal getForecast();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsCritical */
    public static final String COLUMNNAME_IsCritical = "IsCritical";

	/** Set Is Critical Component.
	  * Indicate that a Manufacturing Order can not begin without have this component
	  */
	public void setIsCritical (boolean IsCritical);

	/** Get Is Critical Component.
	  * Indicate that a Manufacturing Order can not begin without have this component
	  */
	public boolean isCritical();

    /** Column name IsQtyPercentage */
    public static final String COLUMNNAME_IsQtyPercentage = "IsQtyPercentage";

	/** Set Is Qty Percentage.
	  * Indicate that this component is based in % Quantity
	  */
	public void setIsQtyPercentage (boolean IsQtyPercentage);

	/** Get Is Qty Percentage.
	  * Indicate that this component is based in % Quantity
	  */
	public boolean isQtyPercentage();

    /** Column name IssueMethod */
    public static final String COLUMNNAME_IssueMethod = "IssueMethod";

	/** Set Issue Method.
	  * There are two methods for issue the components to Manufacturing Order
	  */
	public void setIssueMethod (String IssueMethod);

	/** Get Issue Method.
	  * There are two methods for issue the components to Manufacturing Order
	  */
	public String getIssueMethod();

    /** Column name LeadTimeOffset */
    public static final String COLUMNNAME_LeadTimeOffset = "LeadTimeOffset";

	/** Set Lead Time Offset.
	  * Optional Lead Time offest before starting production
	  */
	public void setLeadTimeOffset (int LeadTimeOffset);

	/** Get Lead Time Offset.
	  * Optional Lead Time offest before starting production
	  */
	public int getLeadTimeOffset();

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

    /** Column name M_ChangeNotice_ID */
    public static final String COLUMNNAME_M_ChangeNotice_ID = "M_ChangeNotice_ID";

	/** Set Change Notice.
	  * Bill of Materials (Engineering) Change Notice (Version)
	  */
	public void setM_ChangeNotice_ID (int M_ChangeNotice_ID);

	/** Get Change Notice.
	  * Bill of Materials (Engineering) Change Notice (Version)
	  */
	public int getM_ChangeNotice_ID();

	public I_M_ChangeNotice getM_ChangeNotice() throws Exception;

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

	public I_M_Warehouse getM_Warehouse() throws Exception;

    /** Column name PP_Order_BOMLine_ID */
    public static final String COLUMNNAME_PP_Order_BOMLine_ID = "PP_Order_BOMLine_ID";

	/** Set PP_Order_BOMLine_ID	  */
	public void setPP_Order_BOMLine_ID (int PP_Order_BOMLine_ID);

	/** Get PP_Order_BOMLine_ID	  */
	public int getPP_Order_BOMLine_ID();

    /** Column name PP_Order_BOM_ID */
    public static final String COLUMNNAME_PP_Order_BOM_ID = "PP_Order_BOM_ID";

	/** Set PP_Order_BOM_ID	  */
	public void setPP_Order_BOM_ID (int PP_Order_BOM_ID);

	/** Get PP_Order_BOM_ID	  */
	public int getPP_Order_BOM_ID();

	public org.eevolution.model.I_PP_Order_BOM getPP_Order_BOM() throws Exception;

    /** Column name PP_Order_ID */
    public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

	/** Set PP_Order_ID	  */
	public void setPP_Order_ID (int PP_Order_ID);

	/** Get PP_Order_ID	  */
	public int getPP_Order_ID();

	public org.eevolution.model.I_PP_Order getPP_Order() throws Exception;

    /** Column name QtyBOM */
    public static final String COLUMNNAME_QtyBOM = "QtyBOM";

	/** Set Quantity.
	  * Indicate the Quantity  use in this BOM
	  */
	public void setQtyBOM (BigDecimal QtyBOM);

	/** Get Quantity.
	  * Indicate the Quantity  use in this BOM
	  */
	public BigDecimal getQtyBOM();

    /** Column name QtyBatch */
    public static final String COLUMNNAME_QtyBatch = "QtyBatch";

	/** Set Quantity %.
	  * Indicate the Quantity % use in this Formula
	  */
	public void setQtyBatch (BigDecimal QtyBatch);

	/** Get Quantity %.
	  * Indicate the Quantity % use in this Formula
	  */
	public BigDecimal getQtyBatch();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyPost */
    public static final String COLUMNNAME_QtyPost = "QtyPost";

	/** Set QtyPost	  */
	public void setQtyPost (BigDecimal QtyPost);

	/** Get QtyPost	  */
	public BigDecimal getQtyPost();

    /** Column name QtyReject */
    public static final String COLUMNNAME_QtyReject = "QtyReject";

	/** Set QtyReject	  */
	public void setQtyReject (BigDecimal QtyReject);

	/** Get QtyReject	  */
	public BigDecimal getQtyReject();

    /** Column name QtyRequiered */
    public static final String COLUMNNAME_QtyRequiered = "QtyRequiered";

	/** Set QtyRequiered	  */
	public void setQtyRequiered (BigDecimal QtyRequiered);

	/** Get QtyRequiered	  */
	public BigDecimal getQtyRequiered();

    /** Column name QtyReserved */
    public static final String COLUMNNAME_QtyReserved = "QtyReserved";

	/** Set Reserved Quantity.
	  * Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved);

	/** Get Reserved Quantity.
	  * Reserved Quantity
	  */
	public BigDecimal getQtyReserved();

    /** Column name QtyScrap */
    public static final String COLUMNNAME_QtyScrap = "QtyScrap";

	/** Set QtyScrap	  */
	public void setQtyScrap (BigDecimal QtyScrap);

	/** Get QtyScrap	  */
	public BigDecimal getQtyScrap();

    /** Column name Scrap */
    public static final String COLUMNNAME_Scrap = "Scrap";

	/** Set Scrap.
	  * Indicate the Scrap Quantity that is generate in a manufacturing process
	  */
	public void setScrap (BigDecimal Scrap);

	/** Get Scrap.
	  * Indicate the Scrap Quantity that is generate in a manufacturing process
	  */
	public BigDecimal getScrap();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
