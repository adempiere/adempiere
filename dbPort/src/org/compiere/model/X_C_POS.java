/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for C_POS
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_POS extends PO
{
/** Standard Constructor
@param ctx context
@param C_POS_ID id
@param trxName transaction
*/
public X_C_POS (Properties ctx, int C_POS_ID, String trxName)
{
super (ctx, C_POS_ID, trxName);
/** if (C_POS_ID == 0)
{
setC_CashBook_ID (0);
setC_POS_ID (0);
setIsModifyPrice (false);	// N
setM_PriceList_ID (0);
setM_Warehouse_ID (0);
setName (null);
setSalesRep_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_POS (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=748 */
public static final int Table_ID=748;

/** TableName=C_POS */
public static final String Table_Name="C_POS";

protected static KeyNamePair Model = new KeyNamePair(748,"C_POS");

protected BigDecimal accessLevel = new BigDecimal(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_C_POS[").append(get_ID()).append("]");
return sb.toString();
}

/** C_BPartnerCashTrx_ID AD_Reference_ID=173 */
public static final int C_BPARTNERCASHTRX_ID_AD_Reference_ID=173;
/** Set Template B.Partner.
@param C_BPartnerCashTrx_ID Business Partner used for creating new Business Partners on the fly */
public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID)
{
if (C_BPartnerCashTrx_ID <= 0) set_Value ("C_BPartnerCashTrx_ID", null);
 else 
set_Value ("C_BPartnerCashTrx_ID", new Integer(C_BPartnerCashTrx_ID));
}
/** Get Template B.Partner.
@return Business Partner used for creating new Business Partners on the fly */
public int getC_BPartnerCashTrx_ID() 
{
Integer ii = (Integer)get_Value("C_BPartnerCashTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Book.
@param C_CashBook_ID Cash Book for recording petty cash transactions */
public void setC_CashBook_ID (int C_CashBook_ID)
{
if (C_CashBook_ID < 1) throw new IllegalArgumentException ("C_CashBook_ID is mandatory.");
set_Value ("C_CashBook_ID", new Integer(C_CashBook_ID));
}
/** Get Cash Book.
@return Cash Book for recording petty cash transactions */
public int getC_CashBook_ID() 
{
Integer ii = (Integer)get_Value("C_CashBook_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set POS Key Layout.
@param C_POSKeyLayout_ID POS Function Key Layout */
public void setC_POSKeyLayout_ID (int C_POSKeyLayout_ID)
{
if (C_POSKeyLayout_ID <= 0) set_Value ("C_POSKeyLayout_ID", null);
 else 
set_Value ("C_POSKeyLayout_ID", new Integer(C_POSKeyLayout_ID));
}
/** Get POS Key Layout.
@return POS Function Key Layout */
public int getC_POSKeyLayout_ID() 
{
Integer ii = (Integer)get_Value("C_POSKeyLayout_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set POS Terminal.
@param C_POS_ID Point of Sales Terminal */
public void setC_POS_ID (int C_POS_ID)
{
if (C_POS_ID < 1) throw new IllegalArgumentException ("C_POS_ID is mandatory.");
set_ValueNoCheck ("C_POS_ID", new Integer(C_POS_ID));
}
/** Get POS Terminal.
@return Point of Sales Terminal */
public int getC_POS_ID() 
{
Integer ii = (Integer)get_Value("C_POS_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Modify Price.
@param IsModifyPrice Allow modifying the price */
public void setIsModifyPrice (boolean IsModifyPrice)
{
set_Value ("IsModifyPrice", new Boolean(IsModifyPrice));
}
/** Get Modify Price.
@return Allow modifying the price */
public boolean isModifyPrice() 
{
Object oo = get_Value("IsModifyPrice");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID < 1) throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
set_Value ("M_PriceList_ID", new Integer(M_PriceList_ID));
}
/** Get Price List.
@return Unique identifier of a Price List */
public int getM_PriceList_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Printer Name.
@param PrinterName Name of the Printer */
public void setPrinterName (String PrinterName)
{
if (PrinterName != null && PrinterName.length() > 60)
{
log.warning("Length > 60 - truncated");
PrinterName = PrinterName.substring(0,59);
}
set_Value ("PrinterName", PrinterName);
}
/** Get Printer Name.
@return Name of the Printer */
public String getPrinterName() 
{
return (String)get_Value("PrinterName");
}

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID < 1) throw new IllegalArgumentException ("SalesRep_ID is mandatory.");
set_Value ("SalesRep_ID", new Integer(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
