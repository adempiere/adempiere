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
/** Generated Model for C_RevenueRecognition
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_RevenueRecognition extends PO
{
/** Standard Constructor
@param ctx context
@param C_RevenueRecognition_ID id
@param trxName transaction
*/
public X_C_RevenueRecognition (Properties ctx, int C_RevenueRecognition_ID, String trxName)
{
super (ctx, C_RevenueRecognition_ID, trxName);
/** if (C_RevenueRecognition_ID == 0)
{
setC_RevenueRecognition_ID (0);
setIsTimeBased (false);
setName (null);
setRecognitionFrequency (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RevenueRecognition (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=336 */
public static final int Table_ID=MTable.getTable_ID("C_RevenueRecognition");

/** TableName=C_RevenueRecognition */
public static final String Table_Name="C_RevenueRecognition";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RevenueRecognition");

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
StringBuffer sb = new StringBuffer ("X_C_RevenueRecognition[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Revenue Recognition.
@param C_RevenueRecognition_ID Method for recording revenue */
public void setC_RevenueRecognition_ID (int C_RevenueRecognition_ID)
{
if (C_RevenueRecognition_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_ID", Integer.valueOf(C_RevenueRecognition_ID));
}
/** Get Revenue Recognition.
@return Method for recording revenue */
public int getC_RevenueRecognition_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_ID");
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
/** Set Time based.
@param IsTimeBased Time based Revenue Recognition rather than Service Level based */
public void setIsTimeBased (boolean IsTimeBased)
{
set_Value ("IsTimeBased", Boolean.valueOf(IsTimeBased));
}
/** Get Time based.
@return Time based Revenue Recognition rather than Service Level based */
public boolean isTimeBased() 
{
Object oo = get_Value("IsTimeBased");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Number of Months.
@param NoMonths Number of Months */
public void setNoMonths (int NoMonths)
{
set_Value ("NoMonths", Integer.valueOf(NoMonths));
}
/** Get Number of Months.
@return Number of Months */
public int getNoMonths() 
{
Integer ii = (Integer)get_Value("NoMonths");
if (ii == null) return 0;
return ii.intValue();
}

/** RecognitionFrequency AD_Reference_ID=196 */
public static final int RECOGNITIONFREQUENCY_AD_Reference_ID=196;
/** Month = M */
public static final String RECOGNITIONFREQUENCY_Month = "M";
/** Quarter = Q */
public static final String RECOGNITIONFREQUENCY_Quarter = "Q";
/** Year = Y */
public static final String RECOGNITIONFREQUENCY_Year = "Y";
/** Set Recognition frequency.
@param RecognitionFrequency Recognition frequency */
public void setRecognitionFrequency (String RecognitionFrequency)
{
if (RecognitionFrequency == null) throw new IllegalArgumentException ("RecognitionFrequency is mandatory");
if (RecognitionFrequency.equals("M") || RecognitionFrequency.equals("Q") || RecognitionFrequency.equals("Y"));
 else throw new IllegalArgumentException ("RecognitionFrequency Invalid value - " + RecognitionFrequency + " - Reference_ID=196 - M - Q - Y");
if (RecognitionFrequency.length() > 1)
{
log.warning("Length > 1 - truncated");
RecognitionFrequency = RecognitionFrequency.substring(0,0);
}
set_Value ("RecognitionFrequency", RecognitionFrequency);
}
/** Get Recognition frequency.
@return Recognition frequency */
public String getRecognitionFrequency() 
{
return (String)get_Value("RecognitionFrequency");
}
}
