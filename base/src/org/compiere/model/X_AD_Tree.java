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
/** Generated Model for AD_Tree
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Tree extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Tree_ID id
@param trxName transaction
*/
public X_AD_Tree (Properties ctx, int AD_Tree_ID, String trxName)
{
super (ctx, AD_Tree_ID, trxName);
/** if (AD_Tree_ID == 0)
{
setAD_Tree_ID (0);
setIsAllNodes (false);
setIsDefault (false);	// N
setName (null);
setTreeType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Tree (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=288 */
public static final int Table_ID=MTable.getTable_ID("AD_Tree");

/** TableName=AD_Tree */
public static final String Table_Name="AD_Tree";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Tree");

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
StringBuffer sb = new StringBuffer ("X_AD_Tree[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Tree.
@param AD_Tree_ID Identifies a Tree */
public void setAD_Tree_ID (int AD_Tree_ID)
{
if (AD_Tree_ID < 1) throw new IllegalArgumentException ("AD_Tree_ID is mandatory.");
set_ValueNoCheck ("AD_Tree_ID", Integer.valueOf(AD_Tree_ID));
}
/** Get Tree.
@return Identifies a Tree */
public int getAD_Tree_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_ID */
public static final String COLUMNNAME_AD_Tree_ID = "AD_Tree_ID";
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
/** Set All Nodes.
@param IsAllNodes All Nodes are included (Complete Tree) */
public void setIsAllNodes (boolean IsAllNodes)
{
set_Value ("IsAllNodes", Boolean.valueOf(IsAllNodes));
}
/** Get All Nodes.
@return All Nodes are included (Complete Tree) */
public boolean isAllNodes() 
{
Object oo = get_Value("IsAllNodes");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAllNodes */
public static final String COLUMNNAME_IsAllNodes = "IsAllNodes";
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";

/** TreeType AD_Reference_ID=120 */
public static final int TREETYPE_AD_Reference_ID=120;
/** Activity = AY */
public static final String TREETYPE_Activity = "AY";
/** BoM = BB */
public static final String TREETYPE_BoM = "BB";
/** BPartner = BP */
public static final String TREETYPE_BPartner = "BP";
/** CM Container = CC */
public static final String TREETYPE_CMContainer = "CC";
/** CM Media = CM */
public static final String TREETYPE_CMMedia = "CM";
/** CM Container Stage = CS */
public static final String TREETYPE_CMContainerStage = "CS";
/** CM Template = CT */
public static final String TREETYPE_CMTemplate = "CT";
/** Element Value = EV */
public static final String TREETYPE_ElementValue = "EV";
/** Campaign = MC */
public static final String TREETYPE_Campaign = "MC";
/** Menu = MM */
public static final String TREETYPE_Menu = "MM";
/** Organization = OO */
public static final String TREETYPE_Organization = "OO";
/** Product Category = PC */
public static final String TREETYPE_ProductCategory = "PC";
/** Project = PJ */
public static final String TREETYPE_Project = "PJ";
/** Product = PR */
public static final String TREETYPE_Product = "PR";
/** Sales Region = SR */
public static final String TREETYPE_SalesRegion = "SR";
/** User 1 = U1 */
public static final String TREETYPE_User1 = "U1";
/** User 2 = U2 */
public static final String TREETYPE_User2 = "U2";
/** User 3 = U3 */
public static final String TREETYPE_User3 = "U3";
/** User 4 = U4 */
public static final String TREETYPE_User4 = "U4";
/** Set Type | Area.
@param TreeType Element this tree is built on (i.e Product, Business Partner) */
public void setTreeType (String TreeType)
{
if (TreeType == null) throw new IllegalArgumentException ("TreeType is mandatory");
if (TreeType.equals("AY") || TreeType.equals("BB") || TreeType.equals("BP") || TreeType.equals("CC") || TreeType.equals("CM") || TreeType.equals("CS") || TreeType.equals("CT") || TreeType.equals("EV") || TreeType.equals("MC") || TreeType.equals("MM") || TreeType.equals("OO") || TreeType.equals("PC") || TreeType.equals("PJ") || TreeType.equals("PR") || TreeType.equals("SR") || TreeType.equals("U1") || TreeType.equals("U2") || TreeType.equals("U3") || TreeType.equals("U4"));
 else throw new IllegalArgumentException ("TreeType Invalid value - " + TreeType + " - Reference_ID=120 - AY - BB - BP - CC - CM - CS - CT - EV - MC - MM - OO - PC - PJ - PR - SR - U1 - U2 - U3 - U4");
if (TreeType.length() > 2)
{
log.warning("Length > 2 - truncated");
TreeType = TreeType.substring(0,1);
}
set_ValueNoCheck ("TreeType", TreeType);
}
/** Get Type | Area.
@return Element this tree is built on (i.e Product, Business Partner) */
public String getTreeType() 
{
return (String)get_Value("TreeType");
}
/** Column name TreeType */
public static final String COLUMNNAME_TreeType = "TreeType";
}
