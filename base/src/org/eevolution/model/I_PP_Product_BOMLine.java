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

/** Generated Interface for PP_Product_BOMLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_PP_Product_BOMLine 
{

    /** TableName=PP_Product_BOMLine */
    public static final String Table_Name = "PP_Product_BOMLine";

    /** AD_Table_ID=53019 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name PP_Product_BOMLine_ID */
    public static final String COLUMNNAME_PP_Product_BOMLine_ID = "PP_Product_BOMLine_ID";

	/** Set BOM Line.
	  * BOM Line
	  */
	public void setPP_Product_BOMLine_ID (int PP_Product_BOMLine_ID);

	/** Get BOM Line.
	  * BOM Line
	  */
	public int getPP_Product_BOMLine_ID();

    /** Column name PP_Product_BOM_ID */
    public static final String COLUMNNAME_PP_Product_BOM_ID = "PP_Product_BOM_ID";

	/** Set BOM & Formula.
	  * BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID);

	/** Get BOM & Formula.
	  * BOM & Formula
	  */
	public int getPP_Product_BOM_ID();

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws Exception;

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
