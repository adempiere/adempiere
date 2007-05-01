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
/** Generated Model for C_RevenueRecognition_Run
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_RevenueRecognition_Run extends PO
{
/** Standard Constructor
@param ctx context
@param C_RevenueRecognition_Run_ID id
@param trxName transaction
*/
public X_C_RevenueRecognition_Run (Properties ctx, int C_RevenueRecognition_Run_ID, String trxName)
{
super (ctx, C_RevenueRecognition_Run_ID, trxName);
/** if (C_RevenueRecognition_Run_ID == 0)
{
setC_RevenueRecognition_Plan_ID (0);
setC_RevenueRecognition_Run_ID (0);
setGL_Journal_ID (0);
setRecognizedAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RevenueRecognition_Run (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=444 */
public static final int Table_ID=MTable.getTable_ID("C_RevenueRecognition_Run");

/** TableName=C_RevenueRecognition_Run */
public static final String Table_Name="C_RevenueRecognition_Run";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RevenueRecognition_Run");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
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
StringBuffer sb = new StringBuffer ("X_C_RevenueRecognition_Run[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Revenue Recognition Plan.
@param C_RevenueRecognition_Plan_ID Plan for recognizing or recording revenue */
public void setC_RevenueRecognition_Plan_ID (int C_RevenueRecognition_Plan_ID)
{
if (C_RevenueRecognition_Plan_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_Plan_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_Plan_ID", Integer.valueOf(C_RevenueRecognition_Plan_ID));
}
/** Get Revenue Recognition Plan.
@return Plan for recognizing or recording revenue */
public int getC_RevenueRecognition_Plan_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_Plan_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_RevenueRecognition_Plan_ID()));
}
/** Column name C_RevenueRecognition_Plan_ID */
public static final String COLUMNNAME_C_RevenueRecognition_Plan_ID = "C_RevenueRecognition_Plan_ID";
/** Set Revenue Recognition Run.
@param C_RevenueRecognition_Run_ID Revenue Recognition Run or Process */
public void setC_RevenueRecognition_Run_ID (int C_RevenueRecognition_Run_ID)
{
if (C_RevenueRecognition_Run_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_Run_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_Run_ID", Integer.valueOf(C_RevenueRecognition_Run_ID));
}
/** Get Revenue Recognition Run.
@return Revenue Recognition Run or Process */
public int getC_RevenueRecognition_Run_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_Run_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RevenueRecognition_Run_ID */
public static final String COLUMNNAME_C_RevenueRecognition_Run_ID = "C_RevenueRecognition_Run_ID";
/** Set Journal.
@param GL_Journal_ID General Ledger Journal */
public void setGL_Journal_ID (int GL_Journal_ID)
{
if (GL_Journal_ID < 1) throw new IllegalArgumentException ("GL_Journal_ID is mandatory.");
set_ValueNoCheck ("GL_Journal_ID", Integer.valueOf(GL_Journal_ID));
}
/** Get Journal.
@return General Ledger Journal */
public int getGL_Journal_ID() 
{
Integer ii = (Integer)get_Value("GL_Journal_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Journal_ID */
public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";
/** Set Recognized Amount.
@param RecognizedAmt Recognized Amount */
public void setRecognizedAmt (BigDecimal RecognizedAmt)
{
if (RecognizedAmt == null) throw new IllegalArgumentException ("RecognizedAmt is mandatory.");
set_ValueNoCheck ("RecognizedAmt", RecognizedAmt);
}
/** Get Recognized Amount.
@return Recognized Amount */
public BigDecimal getRecognizedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RecognizedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name RecognizedAmt */
public static final String COLUMNNAME_RecognizedAmt = "RecognizedAmt";
}
