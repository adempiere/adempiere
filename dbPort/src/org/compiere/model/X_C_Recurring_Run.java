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
/** Generated Model for C_Recurring_Run
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_Recurring_Run extends PO
{
/** Standard Constructor
@param ctx context
@param C_Recurring_Run_ID id
@param trxName transaction
*/
public X_C_Recurring_Run (Properties ctx, int C_Recurring_Run_ID, String trxName)
{
super (ctx, C_Recurring_Run_ID, trxName);
/** if (C_Recurring_Run_ID == 0)
{
setC_Recurring_ID (0);
setC_Recurring_Run_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Recurring_Run (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=573 */
public static final int Table_ID=573;

/** TableName=C_Recurring_Run */
public static final String Table_Name="C_Recurring_Run";

protected static KeyNamePair Model = new KeyNamePair(573,"C_Recurring_Run");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_C_Recurring_Run[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_ValueNoCheck ("C_Invoice_ID", null);
 else 
set_ValueNoCheck ("C_Invoice_ID", new Integer(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_ValueNoCheck ("C_Order_ID", null);
 else 
set_ValueNoCheck ("C_Order_ID", new Integer(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_ValueNoCheck ("C_Payment_ID", null);
 else 
set_ValueNoCheck ("C_Payment_ID", new Integer(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_ValueNoCheck ("C_Project_ID", null);
 else 
set_ValueNoCheck ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Recurring.
@param C_Recurring_ID Recurring Document */
public void setC_Recurring_ID (int C_Recurring_ID)
{
if (C_Recurring_ID < 1) throw new IllegalArgumentException ("C_Recurring_ID is mandatory.");
set_ValueNoCheck ("C_Recurring_ID", new Integer(C_Recurring_ID));
}
/** Get Recurring.
@return Recurring Document */
public int getC_Recurring_ID() 
{
Integer ii = (Integer)get_Value("C_Recurring_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Recurring Run.
@param C_Recurring_Run_ID Recurring Document Run */
public void setC_Recurring_Run_ID (int C_Recurring_Run_ID)
{
if (C_Recurring_Run_ID < 1) throw new IllegalArgumentException ("C_Recurring_Run_ID is mandatory.");
set_ValueNoCheck ("C_Recurring_Run_ID", new Integer(C_Recurring_Run_ID));
}
/** Get Recurring Run.
@return Recurring Document Run */
public int getC_Recurring_Run_ID() 
{
Integer ii = (Integer)get_Value("C_Recurring_Run_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Date.
@param DateDoc Date of the Document */
public void setDateDoc (Timestamp DateDoc)
{
set_Value ("DateDoc", DateDoc);
}
/** Get Document Date.
@return Date of the Document */
public Timestamp getDateDoc() 
{
return (Timestamp)get_Value("DateDoc");
}
/** Set Journal Batch.
@param GL_JournalBatch_ID General Ledger Journal Batch */
public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
{
if (GL_JournalBatch_ID <= 0) set_ValueNoCheck ("GL_JournalBatch_ID", null);
 else 
set_ValueNoCheck ("GL_JournalBatch_ID", new Integer(GL_JournalBatch_ID));
}
/** Get Journal Batch.
@return General Ledger Journal Batch */
public int getGL_JournalBatch_ID() 
{
Integer ii = (Integer)get_Value("GL_JournalBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
