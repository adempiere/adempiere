/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for PA_GoalRestriction
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_PA_GoalRestriction extends PO
{
/** Standard Constructor
@param ctx context
@param PA_GoalRestriction_ID id
@param trxName transaction
*/
public X_PA_GoalRestriction (Properties ctx, int PA_GoalRestriction_ID, String trxName)
{
super (ctx, PA_GoalRestriction_ID, trxName);
/** if (PA_GoalRestriction_ID == 0)
{
setGoalRestrictionType (null);
setName (null);
setPA_GoalRestriction_ID (0);
setPA_Goal_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_GoalRestriction (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=832 */
public static final int Table_ID=MTable.getTable_ID("PA_GoalRestriction");

/** TableName=PA_GoalRestriction */
public static final String Table_Name="PA_GoalRestriction";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_GoalRestriction");

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
StringBuffer sb = new StringBuffer ("X_PA_GoalRestriction[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner Group.
@param C_BP_Group_ID Business Partner Group */
public void setC_BP_Group_ID (int C_BP_Group_ID)
{
if (C_BP_Group_ID <= 0) set_Value ("C_BP_Group_ID", null);
 else 
set_Value ("C_BP_Group_ID", Integer.valueOf(C_BP_Group_ID));
}
/** Get Business Partner Group.
@return Business Partner Group */
public int getC_BP_Group_ID() 
{
Integer ii = (Integer)get_Value("C_BP_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** GoalRestrictionType AD_Reference_ID=368 */
public static final int GOALRESTRICTIONTYPE_AD_Reference_ID=368;
/** Business Partner = B */
public static final String GOALRESTRICTIONTYPE_BusinessPartner = "B";
/** Product Category = C */
public static final String GOALRESTRICTIONTYPE_ProductCategory = "C";
/** Bus.Partner Group = G */
public static final String GOALRESTRICTIONTYPE_BusPartnerGroup = "G";
/** Organization = O */
public static final String GOALRESTRICTIONTYPE_Organization = "O";
/** Product = P */
public static final String GOALRESTRICTIONTYPE_Product = "P";
/** Set Restriction Type.
@param GoalRestrictionType Goal Restriction Type */
public void setGoalRestrictionType (String GoalRestrictionType)
{
if (GoalRestrictionType == null) throw new IllegalArgumentException ("GoalRestrictionType is mandatory");
if (GoalRestrictionType.equals("B") || GoalRestrictionType.equals("C") || GoalRestrictionType.equals("G") || GoalRestrictionType.equals("O") || GoalRestrictionType.equals("P"));
 else throw new IllegalArgumentException ("GoalRestrictionType Invalid value - " + GoalRestrictionType + " - Reference_ID=368 - B - C - G - O - P");
if (GoalRestrictionType.length() > 1)
{
log.warning("Length > 1 - truncated");
GoalRestrictionType = GoalRestrictionType.substring(0,0);
}
set_Value ("GoalRestrictionType", GoalRestrictionType);
}
/** Get Restriction Type.
@return Goal Restriction Type */
public String getGoalRestrictionType() 
{
return (String)get_Value("GoalRestrictionType");
}
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID <= 0) set_Value ("M_Product_Category_ID", null);
 else 
set_Value ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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

/** Org_ID AD_Reference_ID=322 */
public static final int ORG_ID_AD_Reference_ID=322;
/** Set Organization.
@param Org_ID Organizational entity within client */
public void setOrg_ID (int Org_ID)
{
if (Org_ID <= 0) set_Value ("Org_ID", null);
 else 
set_Value ("Org_ID", Integer.valueOf(Org_ID));
}
/** Get Organization.
@return Organizational entity within client */
public int getOrg_ID() 
{
Integer ii = (Integer)get_Value("Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Goal Restriction.
@param PA_GoalRestriction_ID Performance Goal Restriction */
public void setPA_GoalRestriction_ID (int PA_GoalRestriction_ID)
{
if (PA_GoalRestriction_ID < 1) throw new IllegalArgumentException ("PA_GoalRestriction_ID is mandatory.");
set_ValueNoCheck ("PA_GoalRestriction_ID", Integer.valueOf(PA_GoalRestriction_ID));
}
/** Get Goal Restriction.
@return Performance Goal Restriction */
public int getPA_GoalRestriction_ID() 
{
Integer ii = (Integer)get_Value("PA_GoalRestriction_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Goal.
@param PA_Goal_ID Performance Goal */
public void setPA_Goal_ID (int PA_Goal_ID)
{
if (PA_Goal_ID < 1) throw new IllegalArgumentException ("PA_Goal_ID is mandatory.");
set_Value ("PA_Goal_ID", Integer.valueOf(PA_Goal_ID));
}
/** Get Goal.
@return Performance Goal */
public int getPA_Goal_ID() 
{
Integer ii = (Integer)get_Value("PA_Goal_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
