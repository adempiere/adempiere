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

    /** Generated Interface for AD_ClientInfo
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:21.203
     */
    public interface I_AD_ClientInfo 
{

    /** TableName=AD_ClientInfo */
    public static final String Table_Name = "AD_ClientInfo";

    /** AD_Table_ID=227 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name AD_Tree_Activity_ID */
    public static final String COLUMNNAME_AD_Tree_Activity_ID = "AD_Tree_Activity_ID";

	/** Set Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public void setAD_Tree_Activity_ID (int AD_Tree_Activity_ID);

	/** Get Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public int getAD_Tree_Activity_ID();

    /** Column name AD_Tree_BPartner_ID */
    public static final String COLUMNNAME_AD_Tree_BPartner_ID = "AD_Tree_BPartner_ID";

	/** Set BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public void setAD_Tree_BPartner_ID (int AD_Tree_BPartner_ID);

	/** Get BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public int getAD_Tree_BPartner_ID();

    /** Column name AD_Tree_Campaign_ID */
    public static final String COLUMNNAME_AD_Tree_Campaign_ID = "AD_Tree_Campaign_ID";

	/** Set Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public void setAD_Tree_Campaign_ID (int AD_Tree_Campaign_ID);

	/** Get Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public int getAD_Tree_Campaign_ID();

    /** Column name AD_Tree_Menu_ID */
    public static final String COLUMNNAME_AD_Tree_Menu_ID = "AD_Tree_Menu_ID";

	/** Set Menu Tree.
	  * Tree of the menu
	  */
	public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID);

	/** Get Menu Tree.
	  * Tree of the menu
	  */
	public int getAD_Tree_Menu_ID();

    /** Column name AD_Tree_Org_ID */
    public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";

	/** Set Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID);

	/** Get Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public int getAD_Tree_Org_ID();

    /** Column name AD_Tree_Product_ID */
    public static final String COLUMNNAME_AD_Tree_Product_ID = "AD_Tree_Product_ID";

	/** Set Product Tree.
	  * Tree to determine product hierarchy
	  */
	public void setAD_Tree_Product_ID (int AD_Tree_Product_ID);

	/** Get Product Tree.
	  * Tree to determine product hierarchy
	  */
	public int getAD_Tree_Product_ID();

    /** Column name AD_Tree_Project_ID */
    public static final String COLUMNNAME_AD_Tree_Project_ID = "AD_Tree_Project_ID";

	/** Set Project Tree.
	  * Tree to determine project hierarchy
	  */
	public void setAD_Tree_Project_ID (int AD_Tree_Project_ID);

	/** Get Project Tree.
	  * Tree to determine project hierarchy
	  */
	public int getAD_Tree_Project_ID();

    /** Column name AD_Tree_SalesRegion_ID */
    public static final String COLUMNNAME_AD_Tree_SalesRegion_ID = "AD_Tree_SalesRegion_ID";

	/** Set Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public void setAD_Tree_SalesRegion_ID (int AD_Tree_SalesRegion_ID);

	/** Get Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public int getAD_Tree_SalesRegion_ID();

    /** Column name C_AcctSchema1_ID */
    public static final String COLUMNNAME_C_AcctSchema1_ID = "C_AcctSchema1_ID";

	/** Set Primary Accounting Schema.
	  * Primary rules for accounting
	  */
	public void setC_AcctSchema1_ID (int C_AcctSchema1_ID);

	/** Get Primary Accounting Schema.
	  * Primary rules for accounting
	  */
	public int getC_AcctSchema1_ID();

    /** Column name C_BPartnerCashTrx_ID */
    public static final String COLUMNNAME_C_BPartnerCashTrx_ID = "C_BPartnerCashTrx_ID";

	/** Set Template B.Partner.
	  * Business Partner used for creating new Business Partners on the fly
	  */
	public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID);

	/** Get Template B.Partner.
	  * Business Partner used for creating new Business Partners on the fly
	  */
	public int getC_BPartnerCashTrx_ID();

    /** Column name C_Calendar_ID */
    public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

	/** Set Calendar.
	  * Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID);

	/** Get Calendar.
	  * Accounting Calendar Name
	  */
	public int getC_Calendar_ID();

	public I_C_Calendar getI_C_Calendar() throws Exception;

    /** Column name C_UOM_Length_ID */
    public static final String COLUMNNAME_C_UOM_Length_ID = "C_UOM_Length_ID";

	/** Set UOM for Length.
	  * Standard Unit of Measure for Length
	  */
	public void setC_UOM_Length_ID (int C_UOM_Length_ID);

	/** Get UOM for Length.
	  * Standard Unit of Measure for Length
	  */
	public int getC_UOM_Length_ID();

    /** Column name C_UOM_Time_ID */
    public static final String COLUMNNAME_C_UOM_Time_ID = "C_UOM_Time_ID";

	/** Set UOM for Time.
	  * Standard Unit of Measure for Time
	  */
	public void setC_UOM_Time_ID (int C_UOM_Time_ID);

	/** Get UOM for Time.
	  * Standard Unit of Measure for Time
	  */
	public int getC_UOM_Time_ID();

    /** Column name C_UOM_Volume_ID */
    public static final String COLUMNNAME_C_UOM_Volume_ID = "C_UOM_Volume_ID";

	/** Set UOM for Volume.
	  * Standard Unit of Measure for Volume
	  */
	public void setC_UOM_Volume_ID (int C_UOM_Volume_ID);

	/** Get UOM for Volume.
	  * Standard Unit of Measure for Volume
	  */
	public int getC_UOM_Volume_ID();

    /** Column name C_UOM_Weight_ID */
    public static final String COLUMNNAME_C_UOM_Weight_ID = "C_UOM_Weight_ID";

	/** Set UOM for Weight.
	  * Standard Unit of Measure for Weight
	  */
	public void setC_UOM_Weight_ID (int C_UOM_Weight_ID);

	/** Get UOM for Weight.
	  * Standard Unit of Measure for Weight
	  */
	public int getC_UOM_Weight_ID();

    /** Column name IsDiscountLineAmt */
    public static final String COLUMNNAME_IsDiscountLineAmt = "IsDiscountLineAmt";

	/** Set Discount calculated from Line Amounts.
	  * Payment Discount calculation does not include Taxes and Charges
	  */
	public void setIsDiscountLineAmt (boolean IsDiscountLineAmt);

	/** Get Discount calculated from Line Amounts.
	  * Payment Discount calculation does not include Taxes and Charges
	  */
	public boolean isDiscountLineAmt();

    /** Column name KeepLogDays */
    public static final String COLUMNNAME_KeepLogDays = "KeepLogDays";

	/** Set Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays);

	/** Get Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public int getKeepLogDays();

    /** Column name M_ProductFreight_ID */
    public static final String COLUMNNAME_M_ProductFreight_ID = "M_ProductFreight_ID";

	/** Set Product for Freight	  */
	public void setM_ProductFreight_ID (int M_ProductFreight_ID);

	/** Get Product for Freight	  */
	public int getM_ProductFreight_ID();
}
