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

/** Generated Interface for RV_PP_Product_BOMLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.0
 */
public interface I_RV_PP_Product_BOMLine 
{

    /** TableName=RV_PP_Product_BOMLine */
    public static final String Table_Name = "RV_PP_Product_BOMLine";

    /** AD_Table_ID=53063 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

	public I_AD_PInstance getAD_PInstance() throws Exception;

    /** Column name ComponentType */
    public static final String COLUMNNAME_ComponentType = "ComponentType";

	/** Set ComponentType	  */
	public void setComponentType (String ComponentType);

	/** Get ComponentType	  */
	public String getComponentType();

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

    /** Column name Implotion */
    public static final String COLUMNNAME_Implotion = "Implotion";

	/** Set Implotion	  */
	public void setImplotion (boolean Implotion);

	/** Get Implotion	  */
	public boolean isImplotion();

    /** Column name IsCritical */
    public static final String COLUMNNAME_IsCritical = "IsCritical";

	/** Set IsCritical	  */
	public void setIsCritical (boolean IsCritical);

	/** Get IsCritical	  */
	public boolean isCritical();

    /** Column name IsQtyPercentage */
    public static final String COLUMNNAME_IsQtyPercentage = "IsQtyPercentage";

	/** Set IsQtyPercentage	  */
	public void setIsQtyPercentage (boolean IsQtyPercentage);

	/** Get IsQtyPercentage	  */
	public boolean isQtyPercentage();

    /** Column name IssueMethod */
    public static final String COLUMNNAME_IssueMethod = "IssueMethod";

	/** Set IssueMethod	  */
	public void setIssueMethod (String IssueMethod);

	/** Get IssueMethod	  */
	public String getIssueMethod();

    /** Column name LevelNo */
    public static final String COLUMNNAME_LevelNo = "LevelNo";

	/** Set Level no	  */
	public void setLevelNo (int LevelNo);

	/** Get Level no	  */
	public int getLevelNo();

    /** Column name Levels */
    public static final String COLUMNNAME_Levels = "Levels";

	/** Set Levels	  */
	public void setLevels (String Levels);

	/** Get Levels	  */
	public String getLevels();

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

    /** Column name PP_Product_BOM_ID */
    public static final String COLUMNNAME_PP_Product_BOM_ID = "PP_Product_BOM_ID";

	/** Set BOM & Formaula	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID);

	/** Get BOM & Formaula	  */
	public int getPP_Product_BOM_ID();

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws Exception;

    /** Column name PP_Product_BOMLine_ID */
    public static final String COLUMNNAME_PP_Product_BOMLine_ID = "PP_Product_BOMLine_ID";

	/** Set PP_Product_BOMLine_ID	  */
	public void setPP_Product_BOMLine_ID (int PP_Product_BOMLine_ID);

	/** Get PP_Product_BOMLine_ID	  */
	public int getPP_Product_BOMLine_ID();

	public org.eevolution.model.I_PP_Product_BOMLine getPP_Product_BOMLine() throws Exception;

    /** Column name QtyBatch */
    public static final String COLUMNNAME_QtyBatch = "QtyBatch";

	/** Set QtyBatch	  */
	public void setQtyBatch (BigDecimal QtyBatch);

	/** Get QtyBatch	  */
	public BigDecimal getQtyBatch();

    /** Column name QtyBOM */
    public static final String COLUMNNAME_QtyBOM = "QtyBOM";

	/** Set QtyBOM	  */
	public void setQtyBOM (BigDecimal QtyBOM);

	/** Get QtyBOM	  */
	public BigDecimal getQtyBOM();

    /** Column name Scrap */
    public static final String COLUMNNAME_Scrap = "Scrap";

	/** Set Scrap	  */
	public void setScrap (BigDecimal Scrap);

	/** Get Scrap	  */
	public BigDecimal getScrap();

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

    /** Column name TM_Product_ID */
    public static final String COLUMNNAME_TM_Product_ID = "TM_Product_ID";

	/** Set TM_Product_ID.
	  * Product, Service, Item
	  */
	public void setTM_Product_ID (int TM_Product_ID);

	/** Get TM_Product_ID.
	  * Product, Service, Item
	  */
	public int getTM_Product_ID();

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
