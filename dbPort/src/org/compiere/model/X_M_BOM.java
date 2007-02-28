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
/** Generated Model for M_BOM
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_BOM extends PO
{
/** Standard Constructor
@param ctx context
@param M_BOM_ID id
@param trxName transaction
*/
public X_M_BOM (Properties ctx, int M_BOM_ID, String trxName)
{
super (ctx, M_BOM_ID, trxName);
/** if (M_BOM_ID == 0)
{
setBOMType (null);	// A
setBOMUse (null);	// A
setM_BOM_ID (0);
setM_Product_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_BOM (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=798 */
public static final int Table_ID=MTable.getTable_ID("M_BOM");

/** TableName=M_BOM */
public static final String Table_Name="M_BOM";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_BOM");

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
StringBuffer sb = new StringBuffer ("X_M_BOM[").append(get_ID()).append("]");
return sb.toString();
}

/** BOMType AD_Reference_ID=347 */
public static final int BOMTYPE_AD_Reference_ID=347;
/** Current Active = A */
public static final String BOMTYPE_CurrentActive = "A";
/** Future = F */
public static final String BOMTYPE_Future = "F";
/** Maintenance = M */
public static final String BOMTYPE_Maintenance = "M";
/** Make-To-Order = O */
public static final String BOMTYPE_Make_To_Order = "O";
/** Previous = P */
public static final String BOMTYPE_Previous = "P";
/** Repair = R */
public static final String BOMTYPE_Repair = "R";
/** Previous, Spare = S */
public static final String BOMTYPE_PreviousSpare = "S";
/** Set BOM Type.
@param BOMType Type of BOM */
public void setBOMType (String BOMType)
{
if (BOMType == null) throw new IllegalArgumentException ("BOMType is mandatory");
if (BOMType.equals("A") || BOMType.equals("F") || BOMType.equals("M") || BOMType.equals("O") || BOMType.equals("P") || BOMType.equals("R") || BOMType.equals("S"));
 else throw new IllegalArgumentException ("BOMType Invalid value - " + BOMType + " - Reference_ID=347 - A - F - M - O - P - R - S");
if (BOMType.length() > 1)
{
log.warning("Length > 1 - truncated");
BOMType = BOMType.substring(0,0);
}
set_Value ("BOMType", BOMType);
}
/** Get BOM Type.
@return Type of BOM */
public String getBOMType() 
{
return (String)get_Value("BOMType");
}
/** Column name BOMType */
public static final String COLUMNNAME_BOMType = "BOMType";

/** BOMUse AD_Reference_ID=348 */
public static final int BOMUSE_AD_Reference_ID=348;
/** Master = A */
public static final String BOMUSE_Master = "A";
/** Engineering = E */
public static final String BOMUSE_Engineering = "E";
/** Manufacturing = M */
public static final String BOMUSE_Manufacturing = "M";
/** Planning = P */
public static final String BOMUSE_Planning = "P";
/** Set BOM Use.
@param BOMUse The use of the Bill of Material */
public void setBOMUse (String BOMUse)
{
if (BOMUse == null) throw new IllegalArgumentException ("BOMUse is mandatory");
if (BOMUse.equals("A") || BOMUse.equals("E") || BOMUse.equals("M") || BOMUse.equals("P"));
 else throw new IllegalArgumentException ("BOMUse Invalid value - " + BOMUse + " - Reference_ID=348 - A - E - M - P");
if (BOMUse.length() > 1)
{
log.warning("Length > 1 - truncated");
BOMUse = BOMUse.substring(0,0);
}
set_Value ("BOMUse", BOMUse);
}
/** Get BOM Use.
@return The use of the Bill of Material */
public String getBOMUse() 
{
return (String)get_Value("BOMUse");
}
/** Column name BOMUse */
public static final String COLUMNNAME_BOMUse = "BOMUse";
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set BOM.
@param M_BOM_ID Bill of Material */
public void setM_BOM_ID (int M_BOM_ID)
{
if (M_BOM_ID < 1) throw new IllegalArgumentException ("M_BOM_ID is mandatory.");
set_ValueNoCheck ("M_BOM_ID", Integer.valueOf(M_BOM_ID));
}
/** Get BOM.
@return Bill of Material */
public int getM_BOM_ID() 
{
Integer ii = (Integer)get_Value("M_BOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_BOM_ID */
public static final String COLUMNNAME_M_BOM_ID = "M_BOM_ID";
/** Set Change Notice.
@param M_ChangeNotice_ID Bill of Materials (Engineering) Change Notice (Version) */
public void setM_ChangeNotice_ID (int M_ChangeNotice_ID)
{
if (M_ChangeNotice_ID <= 0) set_Value ("M_ChangeNotice_ID", null);
 else 
set_Value ("M_ChangeNotice_ID", Integer.valueOf(M_ChangeNotice_ID));
}
/** Get Change Notice.
@return Bill of Materials (Engineering) Change Notice (Version) */
public int getM_ChangeNotice_ID() 
{
Integer ii = (Integer)get_Value("M_ChangeNotice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ChangeNotice_ID */
public static final String COLUMNNAME_M_ChangeNotice_ID = "M_ChangeNotice_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
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
}
