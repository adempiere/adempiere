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
/** Generated Model for AD_PrintGraph
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_PrintGraph extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintGraph_ID id
@param trxName transaction
*/
public X_AD_PrintGraph (Properties ctx, int AD_PrintGraph_ID, String trxName)
{
super (ctx, AD_PrintGraph_ID, trxName);
/** if (AD_PrintGraph_ID == 0)
{
setAD_PrintFormat_ID (0);	// 0
setAD_PrintGraph_ID (0);
setData_PrintFormatItem_ID (0);
setDescription_PrintFormatItem_ID (0);
setGraphType (null);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintGraph (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=521 */
public static final int Table_ID=MTable.getTable_ID("AD_PrintGraph");

/** TableName=AD_PrintGraph */
public static final String Table_Name="AD_PrintGraph";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_PrintGraph");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_AD_PrintGraph[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Format.
@param AD_PrintFormat_ID Data Print Format */
public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
{
if (AD_PrintFormat_ID < 1) throw new IllegalArgumentException ("AD_PrintFormat_ID is mandatory.");
set_Value ("AD_PrintFormat_ID", Integer.valueOf(AD_PrintFormat_ID));
}
/** Get Print Format.
@return Data Print Format */
public int getAD_PrintFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintFormat_ID */
public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";
/** Set Graph.
@param AD_PrintGraph_ID Graph included in Reports */
public void setAD_PrintGraph_ID (int AD_PrintGraph_ID)
{
if (AD_PrintGraph_ID < 1) throw new IllegalArgumentException ("AD_PrintGraph_ID is mandatory.");
set_ValueNoCheck ("AD_PrintGraph_ID", Integer.valueOf(AD_PrintGraph_ID));
}
/** Get Graph.
@return Graph included in Reports */
public int getAD_PrintGraph_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintGraph_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintGraph_ID */
public static final String COLUMNNAME_AD_PrintGraph_ID = "AD_PrintGraph_ID";

/** Data1_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DATA1_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Data Column 2.
@param Data1_PrintFormatItem_ID Data Column for Line Charts */
public void setData1_PrintFormatItem_ID (int Data1_PrintFormatItem_ID)
{
if (Data1_PrintFormatItem_ID <= 0) set_Value ("Data1_PrintFormatItem_ID", null);
 else 
set_Value ("Data1_PrintFormatItem_ID", Integer.valueOf(Data1_PrintFormatItem_ID));
}
/** Get Data Column 2.
@return Data Column for Line Charts */
public int getData1_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Data1_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Data1_PrintFormatItem_ID */
public static final String COLUMNNAME_Data1_PrintFormatItem_ID = "Data1_PrintFormatItem_ID";

/** Data2_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DATA2_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Data Column 3.
@param Data2_PrintFormatItem_ID Data Column for Line Charts */
public void setData2_PrintFormatItem_ID (int Data2_PrintFormatItem_ID)
{
if (Data2_PrintFormatItem_ID <= 0) set_Value ("Data2_PrintFormatItem_ID", null);
 else 
set_Value ("Data2_PrintFormatItem_ID", Integer.valueOf(Data2_PrintFormatItem_ID));
}
/** Get Data Column 3.
@return Data Column for Line Charts */
public int getData2_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Data2_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Data2_PrintFormatItem_ID */
public static final String COLUMNNAME_Data2_PrintFormatItem_ID = "Data2_PrintFormatItem_ID";

/** Data3_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DATA3_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Data Column 4.
@param Data3_PrintFormatItem_ID Data Column for Line Charts */
public void setData3_PrintFormatItem_ID (int Data3_PrintFormatItem_ID)
{
if (Data3_PrintFormatItem_ID <= 0) set_Value ("Data3_PrintFormatItem_ID", null);
 else 
set_Value ("Data3_PrintFormatItem_ID", Integer.valueOf(Data3_PrintFormatItem_ID));
}
/** Get Data Column 4.
@return Data Column for Line Charts */
public int getData3_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Data3_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Data3_PrintFormatItem_ID */
public static final String COLUMNNAME_Data3_PrintFormatItem_ID = "Data3_PrintFormatItem_ID";

/** Data4_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DATA4_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Data Column 5.
@param Data4_PrintFormatItem_ID Data Column for Line Charts */
public void setData4_PrintFormatItem_ID (int Data4_PrintFormatItem_ID)
{
if (Data4_PrintFormatItem_ID <= 0) set_Value ("Data4_PrintFormatItem_ID", null);
 else 
set_Value ("Data4_PrintFormatItem_ID", Integer.valueOf(Data4_PrintFormatItem_ID));
}
/** Get Data Column 5.
@return Data Column for Line Charts */
public int getData4_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Data4_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Data4_PrintFormatItem_ID */
public static final String COLUMNNAME_Data4_PrintFormatItem_ID = "Data4_PrintFormatItem_ID";

/** Data_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DATA_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Data Column.
@param Data_PrintFormatItem_ID Data Column for Pie and Line Charts */
public void setData_PrintFormatItem_ID (int Data_PrintFormatItem_ID)
{
if (Data_PrintFormatItem_ID < 1) throw new IllegalArgumentException ("Data_PrintFormatItem_ID is mandatory.");
set_Value ("Data_PrintFormatItem_ID", Integer.valueOf(Data_PrintFormatItem_ID));
}
/** Get Data Column.
@return Data Column for Pie and Line Charts */
public int getData_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Data_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Data_PrintFormatItem_ID */
public static final String COLUMNNAME_Data_PrintFormatItem_ID = "Data_PrintFormatItem_ID";
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

/** Description_PrintFormatItem_ID AD_Reference_ID=264 */
public static final int DESCRIPTION_PRINTFORMATITEM_ID_AD_Reference_ID=264;
/** Set Description Column.
@param Description_PrintFormatItem_ID Description Column for Pie/Line/Bar Charts */
public void setDescription_PrintFormatItem_ID (int Description_PrintFormatItem_ID)
{
if (Description_PrintFormatItem_ID < 1) throw new IllegalArgumentException ("Description_PrintFormatItem_ID is mandatory.");
set_Value ("Description_PrintFormatItem_ID", Integer.valueOf(Description_PrintFormatItem_ID));
}
/** Get Description Column.
@return Description Column for Pie/Line/Bar Charts */
public int getDescription_PrintFormatItem_ID() 
{
Integer ii = (Integer)get_Value("Description_PrintFormatItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Description_PrintFormatItem_ID */
public static final String COLUMNNAME_Description_PrintFormatItem_ID = "Description_PrintFormatItem_ID";

/** GraphType AD_Reference_ID=265 */
public static final int GRAPHTYPE_AD_Reference_ID=265;
/** Bar Chart = B */
public static final String GRAPHTYPE_BarChart = "B";
/** Line Chart = L */
public static final String GRAPHTYPE_LineChart = "L";
/** Pie Chart = P */
public static final String GRAPHTYPE_PieChart = "P";
/** Set Graph Type.
@param GraphType Type of graph to be painted */
public void setGraphType (String GraphType)
{
if (GraphType == null) throw new IllegalArgumentException ("GraphType is mandatory");
if (GraphType.equals("B") || GraphType.equals("L") || GraphType.equals("P"));
 else throw new IllegalArgumentException ("GraphType Invalid value - " + GraphType + " - Reference_ID=265 - B - L - P");
if (GraphType.length() > 1)
{
log.warning("Length > 1 - truncated");
GraphType = GraphType.substring(0,0);
}
set_Value ("GraphType", GraphType);
}
/** Get Graph Type.
@return Type of graph to be painted */
public String getGraphType() 
{
return (String)get_Value("GraphType");
}
/** Column name GraphType */
public static final String COLUMNNAME_GraphType = "GraphType";
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
}
