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
/** Generated Model for AD_Sequence_No
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_Sequence_No extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Sequence_No_ID id
@param trxName transaction
*/
public X_AD_Sequence_No (Properties ctx, int AD_Sequence_No_ID, String trxName)
{
super (ctx, AD_Sequence_No_ID, trxName);
/** if (AD_Sequence_No_ID == 0)
{
setAD_Sequence_ID (0);
setCalendarYear (null);
setCurrentNext (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Sequence_No (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=122 */
public static final int Table_ID=122;

/** TableName=AD_Sequence_No */
public static final String Table_Name="AD_Sequence_No";

protected static KeyNamePair Model = new KeyNamePair(122,"AD_Sequence_No");

protected BigDecimal accessLevel = new BigDecimal(6);
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
StringBuffer sb = new StringBuffer ("X_AD_Sequence_No[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Sequence.
@param AD_Sequence_ID Document Sequence */
public void setAD_Sequence_ID (int AD_Sequence_ID)
{
if (AD_Sequence_ID < 1) throw new IllegalArgumentException ("AD_Sequence_ID is mandatory.");
set_ValueNoCheck ("AD_Sequence_ID", new Integer(AD_Sequence_ID));
}
/** Get Sequence.
@return Document Sequence */
public int getAD_Sequence_ID() 
{
Integer ii = (Integer)get_Value("AD_Sequence_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Year.
@param CalendarYear Calendar Year */
public void setCalendarYear (String CalendarYear)
{
if (CalendarYear == null) throw new IllegalArgumentException ("CalendarYear is mandatory.");
if (CalendarYear.length() > 4)
{
log.warning("Length > 4 - truncated");
CalendarYear = CalendarYear.substring(0,3);
}
set_ValueNoCheck ("CalendarYear", CalendarYear);
}
/** Get Year.
@return Calendar Year */
public String getCalendarYear() 
{
return (String)get_Value("CalendarYear");
}
/** Set Current Next.
@param CurrentNext The next number to be used */
public void setCurrentNext (int CurrentNext)
{
set_Value ("CurrentNext", new Integer(CurrentNext));
}
/** Get Current Next.
@return The next number to be used */
public int getCurrentNext() 
{
Integer ii = (Integer)get_Value("CurrentNext");
if (ii == null) return 0;
return ii.intValue();
}
}
