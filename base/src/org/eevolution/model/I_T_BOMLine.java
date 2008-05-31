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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for T_BOMLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_T_BOMLine 
{

    /** TableName=T_BOMLine */
    public static final String Table_Name = "T_BOMLine";

    /** AD_Table_ID=53045 */
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

    /** Column name Implosion */
    public static final String COLUMNNAME_Implosion = "Implosion";

	/** Set Implosion.
	  * Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.
	  */
	public void setImplosion (boolean Implosion);

	/** Get Implosion.
	  * Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.
	  */
	public boolean isImplosion();

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

	public org.eevolution.model.I_PP_Product_BOMLine getPP_Product_BOMLine() throws Exception;

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

    /** Column name Sel_Product_ID */
    public static final String COLUMNNAME_Sel_Product_ID = "Sel_Product_ID";

	/** Set Sel_Product_ID	  */
	public void setSel_Product_ID (int Sel_Product_ID);

	/** Get Sel_Product_ID	  */
	public int getSel_Product_ID();

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

    /** Column name T_BOMLine_ID */
    public static final String COLUMNNAME_T_BOMLine_ID = "T_BOMLine_ID";

	/** Set T_BOMLine_ID	  */
	public void setT_BOMLine_ID (int T_BOMLine_ID);

	/** Get T_BOMLine_ID	  */
	public int getT_BOMLine_ID();
}
