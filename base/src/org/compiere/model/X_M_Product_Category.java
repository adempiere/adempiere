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
/** Generated Model for M_Product_Category
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_Product_Category extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_Category_ID id
@param trxName transaction
*/
public X_M_Product_Category (Properties ctx, int M_Product_Category_ID, String trxName)
{
super (ctx, M_Product_Category_ID, trxName);
/** if (M_Product_Category_ID == 0)
{
setIsDefault (false);
setIsSelfService (true);	// Y
setMMPolicy (null);	// F
setM_Product_Category_ID (0);
setName (null);
setPlannedMargin (Env.ZERO);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product_Category (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=209 */
public static final int Table_ID=MTable.getTable_ID("M_Product_Category");

/** TableName=M_Product_Category */
public static final String Table_Name="M_Product_Category";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product_Category");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_M_Product_Category[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Color.
@param AD_PrintColor_ID Color used for printing and display */
public void setAD_PrintColor_ID (int AD_PrintColor_ID)
{
if (AD_PrintColor_ID <= 0) set_Value ("AD_PrintColor_ID", null);
 else 
set_Value ("AD_PrintColor_ID", Integer.valueOf(AD_PrintColor_ID));
}
/** Get Print Color.
@return Color used for printing and display */
public int getAD_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor_ID */
public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";
/** Set Asset Group.
@param A_Asset_Group_ID Group of Assets */
public void setA_Asset_Group_ID (int A_Asset_Group_ID)
{
if (A_Asset_Group_ID <= 0) set_Value ("A_Asset_Group_ID", null);
 else 
set_Value ("A_Asset_Group_ID", Integer.valueOf(A_Asset_Group_ID));
}
/** Get Asset Group.
@return Group of Assets */
public int getA_Asset_Group_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name A_Asset_Group_ID */
public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSelfService */
public static final String COLUMNNAME_IsSelfService = "IsSelfService";

/** MMPolicy AD_Reference_ID=335 */
public static final int MMPOLICY_AD_Reference_ID=335;
/** FiFo = F */
public static final String MMPOLICY_FiFo = "F";
/** LiFo = L */
public static final String MMPOLICY_LiFo = "L";
/** Set Material Policy.
@param MMPolicy Material Movement Policy */
public void setMMPolicy (String MMPolicy)
{
if (MMPolicy == null) throw new IllegalArgumentException ("MMPolicy is mandatory");
if (MMPolicy.equals("F") || MMPolicy.equals("L"));
 else throw new IllegalArgumentException ("MMPolicy Invalid value - " + MMPolicy + " - Reference_ID=335 - F - L");
if (MMPolicy.length() > 1)
{
log.warning("Length > 1 - truncated");
MMPolicy = MMPolicy.substring(0,0);
}
set_Value ("MMPolicy", MMPolicy);
}
/** Get Material Policy.
@return Material Movement Policy */
public String getMMPolicy() 
{
return (String)get_Value("MMPolicy");
}
/** Column name MMPolicy */
public static final String COLUMNNAME_MMPolicy = "MMPolicy";
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID < 1) throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
set_ValueNoCheck ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_Category_ID */
public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

/** M_Product_Category_Parent_ID AD_Reference_ID=163 */
public static final int M_PRODUCT_CATEGORY_PARENT_ID_AD_Reference_ID=163;
/** Set Parent Product Category.
@param M_Product_Category_Parent_ID Parent Product Category */
public void setM_Product_Category_Parent_ID (int M_Product_Category_Parent_ID)
{
if (M_Product_Category_Parent_ID <= 0) set_Value ("M_Product_Category_Parent_ID", null);
 else 
set_Value ("M_Product_Category_Parent_ID", Integer.valueOf(M_Product_Category_Parent_ID));
}
/** Get Parent Product Category.
@return Parent Product Category */
public int getM_Product_Category_Parent_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_Parent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_Category_Parent_ID */
public static final String COLUMNNAME_M_Product_Category_Parent_ID = "M_Product_Category_Parent_ID";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Planned Margin %.
@param PlannedMargin Project's planned margin as a percentage */
public void setPlannedMargin (BigDecimal PlannedMargin)
{
if (PlannedMargin == null) throw new IllegalArgumentException ("PlannedMargin is mandatory.");
set_Value ("PlannedMargin", PlannedMargin);
}
/** Get Planned Margin %.
@return Project's planned margin as a percentage */
public BigDecimal getPlannedMargin() 
{
BigDecimal bd = (BigDecimal)get_Value("PlannedMargin");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PlannedMargin */
public static final String COLUMNNAME_PlannedMargin = "PlannedMargin";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
}
