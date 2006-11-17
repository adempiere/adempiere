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
/** Generated Model for C_ServiceLevel
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.703 */
public class X_C_ServiceLevel extends PO
{
/** Standard Constructor
@param ctx context
@param C_ServiceLevel_ID id
@param trxName transaction
*/
public X_C_ServiceLevel (Properties ctx, int C_ServiceLevel_ID, String trxName)
{
super (ctx, C_ServiceLevel_ID, trxName);
/** if (C_ServiceLevel_ID == 0)
{
setC_RevenueRecognition_Plan_ID (0);
setC_ServiceLevel_ID (0);
setM_Product_ID (0);
setServiceLevelInvoiced (Env.ZERO);
setServiceLevelProvided (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ServiceLevel (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=337 */
public static final int Table_ID=337;

/** TableName=C_ServiceLevel */
public static final String Table_Name="C_ServiceLevel";

protected static KeyNamePair Model = new KeyNamePair(337,"C_ServiceLevel");

protected BigDecimal accessLevel = new BigDecimal(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_ServiceLevel[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Revenue Recognition Plan.
@param C_RevenueRecognition_Plan_ID Plan for recognizing or recording revenue */
public void setC_RevenueRecognition_Plan_ID (int C_RevenueRecognition_Plan_ID)
{
if (C_RevenueRecognition_Plan_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_Plan_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_Plan_ID", new Integer(C_RevenueRecognition_Plan_ID));
}
/** Get Revenue Recognition Plan.
@return Plan for recognizing or recording revenue */
public int getC_RevenueRecognition_Plan_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_Plan_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Service Level.
@param C_ServiceLevel_ID Product Revenue Recognition Service Level  */
public void setC_ServiceLevel_ID (int C_ServiceLevel_ID)
{
if (C_ServiceLevel_ID < 1) throw new IllegalArgumentException ("C_ServiceLevel_ID is mandatory.");
set_ValueNoCheck ("C_ServiceLevel_ID", new Integer(C_ServiceLevel_ID));
}
/** Get Service Level.
@return Product Revenue Recognition Service Level  */
public int getC_ServiceLevel_ID() 
{
Integer ii = (Integer)get_Value("C_ServiceLevel_ID");
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getDescription());
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
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
/** Set Quantity Invoiced.
@param ServiceLevelInvoiced Quantity of product or service invoiced */
public void setServiceLevelInvoiced (BigDecimal ServiceLevelInvoiced)
{
if (ServiceLevelInvoiced == null) throw new IllegalArgumentException ("ServiceLevelInvoiced is mandatory.");
set_ValueNoCheck ("ServiceLevelInvoiced", ServiceLevelInvoiced);
}
/** Get Quantity Invoiced.
@return Quantity of product or service invoiced */
public BigDecimal getServiceLevelInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("ServiceLevelInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Quantity Provided.
@param ServiceLevelProvided Quantity of service or product provided */
public void setServiceLevelProvided (BigDecimal ServiceLevelProvided)
{
if (ServiceLevelProvided == null) throw new IllegalArgumentException ("ServiceLevelProvided is mandatory.");
set_ValueNoCheck ("ServiceLevelProvided", ServiceLevelProvided);
}
/** Get Quantity Provided.
@return Quantity of service or product provided */
public BigDecimal getServiceLevelProvided() 
{
BigDecimal bd = (BigDecimal)get_Value("ServiceLevelProvided");
if (bd == null) return Env.ZERO;
return bd;
}
}
