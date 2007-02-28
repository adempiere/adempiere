/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for AD_ClientInfo
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_ClientInfo extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ClientInfo_ID id
@param trxName transaction
*/
public X_AD_ClientInfo (Properties ctx, int AD_ClientInfo_ID, String trxName)
{
super (ctx, AD_ClientInfo_ID, trxName);
/** if (AD_ClientInfo_ID == 0)
{
setIsDiscountLineAmt (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ClientInfo (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=227 */
public static final int Table_ID=MTable.getTable_ID("AD_ClientInfo");

/** TableName=AD_ClientInfo */
public static final String Table_Name="AD_ClientInfo";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ClientInfo");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_AD_ClientInfo[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Tree_Activity_ID AD_Reference_ID=184 */
public static final int AD_TREE_ACTIVITY_ID_AD_Reference_ID=184;
/** Set Activity Tree.
@param AD_Tree_Activity_ID Tree to determine activity hierarchy */
public void setAD_Tree_Activity_ID (int AD_Tree_Activity_ID)
{
if (AD_Tree_Activity_ID <= 0) set_ValueNoCheck ("AD_Tree_Activity_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Activity_ID", Integer.valueOf(AD_Tree_Activity_ID));
}
/** Get Activity Tree.
@return Tree to determine activity hierarchy */
public int getAD_Tree_Activity_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Activity_ID */
public static final String COLUMNNAME_AD_Tree_Activity_ID = "AD_Tree_Activity_ID";

/** AD_Tree_BPartner_ID AD_Reference_ID=184 */
public static final int AD_TREE_BPARTNER_ID_AD_Reference_ID=184;
/** Set BPartner Tree.
@param AD_Tree_BPartner_ID Tree to determine business partner hierarchy */
public void setAD_Tree_BPartner_ID (int AD_Tree_BPartner_ID)
{
if (AD_Tree_BPartner_ID <= 0) set_ValueNoCheck ("AD_Tree_BPartner_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_BPartner_ID", Integer.valueOf(AD_Tree_BPartner_ID));
}
/** Get BPartner Tree.
@return Tree to determine business partner hierarchy */
public int getAD_Tree_BPartner_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_BPartner_ID */
public static final String COLUMNNAME_AD_Tree_BPartner_ID = "AD_Tree_BPartner_ID";

/** AD_Tree_Campaign_ID AD_Reference_ID=184 */
public static final int AD_TREE_CAMPAIGN_ID_AD_Reference_ID=184;
/** Set Campaign Tree.
@param AD_Tree_Campaign_ID Tree to determine marketing campaign hierarchy */
public void setAD_Tree_Campaign_ID (int AD_Tree_Campaign_ID)
{
if (AD_Tree_Campaign_ID <= 0) set_ValueNoCheck ("AD_Tree_Campaign_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Campaign_ID", Integer.valueOf(AD_Tree_Campaign_ID));
}
/** Get Campaign Tree.
@return Tree to determine marketing campaign hierarchy */
public int getAD_Tree_Campaign_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Campaign_ID */
public static final String COLUMNNAME_AD_Tree_Campaign_ID = "AD_Tree_Campaign_ID";

/** AD_Tree_Menu_ID AD_Reference_ID=184 */
public static final int AD_TREE_MENU_ID_AD_Reference_ID=184;
/** Set Menu Tree.
@param AD_Tree_Menu_ID Tree of the menu */
public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID)
{
if (AD_Tree_Menu_ID <= 0) set_ValueNoCheck ("AD_Tree_Menu_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Menu_ID", Integer.valueOf(AD_Tree_Menu_ID));
}
/** Get Menu Tree.
@return Tree of the menu */
public int getAD_Tree_Menu_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Menu_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Menu_ID */
public static final String COLUMNNAME_AD_Tree_Menu_ID = "AD_Tree_Menu_ID";

/** AD_Tree_Org_ID AD_Reference_ID=184 */
public static final int AD_TREE_ORG_ID_AD_Reference_ID=184;
/** Set Organization Tree.
@param AD_Tree_Org_ID Tree to determine organizational hierarchy */
public void setAD_Tree_Org_ID (int AD_Tree_Org_ID)
{
if (AD_Tree_Org_ID <= 0) set_ValueNoCheck ("AD_Tree_Org_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Org_ID", Integer.valueOf(AD_Tree_Org_ID));
}
/** Get Organization Tree.
@return Tree to determine organizational hierarchy */
public int getAD_Tree_Org_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Org_ID */
public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";

/** AD_Tree_Product_ID AD_Reference_ID=184 */
public static final int AD_TREE_PRODUCT_ID_AD_Reference_ID=184;
/** Set Product Tree.
@param AD_Tree_Product_ID Tree to determine product hierarchy */
public void setAD_Tree_Product_ID (int AD_Tree_Product_ID)
{
if (AD_Tree_Product_ID <= 0) set_ValueNoCheck ("AD_Tree_Product_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Product_ID", Integer.valueOf(AD_Tree_Product_ID));
}
/** Get Product Tree.
@return Tree to determine product hierarchy */
public int getAD_Tree_Product_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Product_ID */
public static final String COLUMNNAME_AD_Tree_Product_ID = "AD_Tree_Product_ID";

/** AD_Tree_Project_ID AD_Reference_ID=184 */
public static final int AD_TREE_PROJECT_ID_AD_Reference_ID=184;
/** Set Project Tree.
@param AD_Tree_Project_ID Tree to determine project hierarchy */
public void setAD_Tree_Project_ID (int AD_Tree_Project_ID)
{
if (AD_Tree_Project_ID <= 0) set_ValueNoCheck ("AD_Tree_Project_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_Project_ID", Integer.valueOf(AD_Tree_Project_ID));
}
/** Get Project Tree.
@return Tree to determine project hierarchy */
public int getAD_Tree_Project_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Project_ID */
public static final String COLUMNNAME_AD_Tree_Project_ID = "AD_Tree_Project_ID";

/** AD_Tree_SalesRegion_ID AD_Reference_ID=184 */
public static final int AD_TREE_SALESREGION_ID_AD_Reference_ID=184;
/** Set Sales Region Tree.
@param AD_Tree_SalesRegion_ID Tree to determine sales regional hierarchy */
public void setAD_Tree_SalesRegion_ID (int AD_Tree_SalesRegion_ID)
{
if (AD_Tree_SalesRegion_ID <= 0) set_ValueNoCheck ("AD_Tree_SalesRegion_ID", null);
 else 
set_ValueNoCheck ("AD_Tree_SalesRegion_ID", Integer.valueOf(AD_Tree_SalesRegion_ID));
}
/** Get Sales Region Tree.
@return Tree to determine sales regional hierarchy */
public int getAD_Tree_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_SalesRegion_ID */
public static final String COLUMNNAME_AD_Tree_SalesRegion_ID = "AD_Tree_SalesRegion_ID";

/** C_AcctSchema1_ID AD_Reference_ID=136 */
public static final int C_ACCTSCHEMA1_ID_AD_Reference_ID=136;
/** Set Primary Accounting Schema.
@param C_AcctSchema1_ID Primary rules for accounting */
public void setC_AcctSchema1_ID (int C_AcctSchema1_ID)
{
if (C_AcctSchema1_ID <= 0) set_ValueNoCheck ("C_AcctSchema1_ID", null);
 else 
set_ValueNoCheck ("C_AcctSchema1_ID", Integer.valueOf(C_AcctSchema1_ID));
}
/** Get Primary Accounting Schema.
@return Primary rules for accounting */
public int getC_AcctSchema1_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_AcctSchema1_ID */
public static final String COLUMNNAME_C_AcctSchema1_ID = "C_AcctSchema1_ID";

/** C_BPartnerCashTrx_ID AD_Reference_ID=138 */
public static final int C_BPARTNERCASHTRX_ID_AD_Reference_ID=138;
/** Set Template B.Partner.
@param C_BPartnerCashTrx_ID Business Partner used for creating new Business Partners on the fly */
public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID)
{
if (C_BPartnerCashTrx_ID <= 0) set_Value ("C_BPartnerCashTrx_ID", null);
 else 
set_Value ("C_BPartnerCashTrx_ID", Integer.valueOf(C_BPartnerCashTrx_ID));
}
/** Get Template B.Partner.
@return Business Partner used for creating new Business Partners on the fly */
public int getC_BPartnerCashTrx_ID() 
{
Integer ii = (Integer)get_Value("C_BPartnerCashTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartnerCashTrx_ID */
public static final String COLUMNNAME_C_BPartnerCashTrx_ID = "C_BPartnerCashTrx_ID";
/** Set Calendar.
@param C_Calendar_ID Accounting Calendar Name */
public void setC_Calendar_ID (int C_Calendar_ID)
{
if (C_Calendar_ID <= 0) set_Value ("C_Calendar_ID", null);
 else 
set_Value ("C_Calendar_ID", Integer.valueOf(C_Calendar_ID));
}
/** Get Calendar.
@return Accounting Calendar Name */
public int getC_Calendar_ID() 
{
Integer ii = (Integer)get_Value("C_Calendar_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Calendar_ID */
public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

/** C_UOM_Length_ID AD_Reference_ID=114 */
public static final int C_UOM_LENGTH_ID_AD_Reference_ID=114;
/** Set UOM for Length.
@param C_UOM_Length_ID Standard Unit of Measure for Length */
public void setC_UOM_Length_ID (int C_UOM_Length_ID)
{
if (C_UOM_Length_ID <= 0) set_Value ("C_UOM_Length_ID", null);
 else 
set_Value ("C_UOM_Length_ID", Integer.valueOf(C_UOM_Length_ID));
}
/** Get UOM for Length.
@return Standard Unit of Measure for Length */
public int getC_UOM_Length_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_Length_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_Length_ID */
public static final String COLUMNNAME_C_UOM_Length_ID = "C_UOM_Length_ID";

/** C_UOM_Time_ID AD_Reference_ID=114 */
public static final int C_UOM_TIME_ID_AD_Reference_ID=114;
/** Set UOM for Time.
@param C_UOM_Time_ID Standard Unit of Measure for Time */
public void setC_UOM_Time_ID (int C_UOM_Time_ID)
{
if (C_UOM_Time_ID <= 0) set_Value ("C_UOM_Time_ID", null);
 else 
set_Value ("C_UOM_Time_ID", Integer.valueOf(C_UOM_Time_ID));
}
/** Get UOM for Time.
@return Standard Unit of Measure for Time */
public int getC_UOM_Time_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_Time_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_Time_ID */
public static final String COLUMNNAME_C_UOM_Time_ID = "C_UOM_Time_ID";

/** C_UOM_Volume_ID AD_Reference_ID=114 */
public static final int C_UOM_VOLUME_ID_AD_Reference_ID=114;
/** Set UOM for Volume.
@param C_UOM_Volume_ID Standard Unit of Measure for Volume */
public void setC_UOM_Volume_ID (int C_UOM_Volume_ID)
{
if (C_UOM_Volume_ID <= 0) set_Value ("C_UOM_Volume_ID", null);
 else 
set_Value ("C_UOM_Volume_ID", Integer.valueOf(C_UOM_Volume_ID));
}
/** Get UOM for Volume.
@return Standard Unit of Measure for Volume */
public int getC_UOM_Volume_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_Volume_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_Volume_ID */
public static final String COLUMNNAME_C_UOM_Volume_ID = "C_UOM_Volume_ID";

/** C_UOM_Weight_ID AD_Reference_ID=114 */
public static final int C_UOM_WEIGHT_ID_AD_Reference_ID=114;
/** Set UOM for Weight.
@param C_UOM_Weight_ID Standard Unit of Measure for Weight */
public void setC_UOM_Weight_ID (int C_UOM_Weight_ID)
{
if (C_UOM_Weight_ID <= 0) set_Value ("C_UOM_Weight_ID", null);
 else 
set_Value ("C_UOM_Weight_ID", Integer.valueOf(C_UOM_Weight_ID));
}
/** Get UOM for Weight.
@return Standard Unit of Measure for Weight */
public int getC_UOM_Weight_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_Weight_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_Weight_ID */
public static final String COLUMNNAME_C_UOM_Weight_ID = "C_UOM_Weight_ID";
/** Set Discount calculated from Line Amounts.
@param IsDiscountLineAmt Payment Discount calculation does not include Taxes and Charges */
public void setIsDiscountLineAmt (boolean IsDiscountLineAmt)
{
set_Value ("IsDiscountLineAmt", Boolean.valueOf(IsDiscountLineAmt));
}
/** Get Discount calculated from Line Amounts.
@return Payment Discount calculation does not include Taxes and Charges */
public boolean isDiscountLineAmt() 
{
Object oo = get_Value("IsDiscountLineAmt");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDiscountLineAmt */
public static final String COLUMNNAME_IsDiscountLineAmt = "IsDiscountLineAmt";
/** Set Days to keep Log.
@param KeepLogDays Number of days to keep the log entries */
public void setKeepLogDays (int KeepLogDays)
{
set_Value ("KeepLogDays", Integer.valueOf(KeepLogDays));
}
/** Get Days to keep Log.
@return Number of days to keep the log entries */
public int getKeepLogDays() 
{
Integer ii = (Integer)get_Value("KeepLogDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name KeepLogDays */
public static final String COLUMNNAME_KeepLogDays = "KeepLogDays";

/** M_ProductFreight_ID AD_Reference_ID=162 */
public static final int M_PRODUCTFREIGHT_ID_AD_Reference_ID=162;
/** Set Product for Freight.
@param M_ProductFreight_ID Product for Freight */
public void setM_ProductFreight_ID (int M_ProductFreight_ID)
{
if (M_ProductFreight_ID <= 0) set_Value ("M_ProductFreight_ID", null);
 else 
set_Value ("M_ProductFreight_ID", Integer.valueOf(M_ProductFreight_ID));
}
/** Get Product for Freight.
@return Product for Freight */
public int getM_ProductFreight_ID() 
{
Integer ii = (Integer)get_Value("M_ProductFreight_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductFreight_ID */
public static final String COLUMNNAME_M_ProductFreight_ID = "M_ProductFreight_ID";
}
