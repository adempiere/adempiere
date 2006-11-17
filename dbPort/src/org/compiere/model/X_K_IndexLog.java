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
/** Generated Model for K_IndexLog
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:00.484 */
public class X_K_IndexLog extends PO
{
/** Standard Constructor
@param ctx context
@param K_IndexLog_ID id
@param trxName transaction
*/
public X_K_IndexLog (Properties ctx, int K_IndexLog_ID, String trxName)
{
super (ctx, K_IndexLog_ID, trxName);
/** if (K_IndexLog_ID == 0)
{
setIndexQuery (null);
setIndexQueryResult (0);
setK_IndexLog_ID (0);
setQuerySource (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_IndexLog (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=899 */
public static final int Table_ID=899;

/** TableName=K_IndexLog */
public static final String Table_Name="K_IndexLog";

protected static KeyNamePair Model = new KeyNamePair(899,"K_IndexLog");

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
StringBuffer sb = new StringBuffer ("X_K_IndexLog[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Index Query.
@param IndexQuery Text Search Query  */
public void setIndexQuery (String IndexQuery)
{
if (IndexQuery == null) throw new IllegalArgumentException ("IndexQuery is mandatory.");
if (IndexQuery.length() > 255)
{
log.warning("Length > 255 - truncated");
IndexQuery = IndexQuery.substring(0,254);
}
set_ValueNoCheck ("IndexQuery", IndexQuery);
}
/** Get Index Query.
@return Text Search Query  */
public String getIndexQuery() 
{
return (String)get_Value("IndexQuery");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getIndexQuery());
}
/** Set Query Result.
@param IndexQueryResult Result of the text query */
public void setIndexQueryResult (int IndexQueryResult)
{
set_ValueNoCheck ("IndexQueryResult", new Integer(IndexQueryResult));
}
/** Get Query Result.
@return Result of the text query */
public int getIndexQueryResult() 
{
Integer ii = (Integer)get_Value("IndexQueryResult");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Index Log.
@param K_IndexLog_ID Text search log */
public void setK_IndexLog_ID (int K_IndexLog_ID)
{
if (K_IndexLog_ID < 1) throw new IllegalArgumentException ("K_IndexLog_ID is mandatory.");
set_ValueNoCheck ("K_IndexLog_ID", new Integer(K_IndexLog_ID));
}
/** Get Index Log.
@return Text search log */
public int getK_IndexLog_ID() 
{
Integer ii = (Integer)get_Value("K_IndexLog_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** QuerySource AD_Reference_ID=391 */
public static final int QUERYSOURCE_AD_Reference_ID=391;
/** Collaboration Management = C */
public static final String QUERYSOURCE_CollaborationManagement = "C";
/** HTML Client = H */
public static final String QUERYSOURCE_HTMLClient = "H";
/** Java Client = J */
public static final String QUERYSOURCE_JavaClient = "J";
/** Self Service = W */
public static final String QUERYSOURCE_SelfService = "W";
/** Set Query Source.
@param QuerySource Source of the Query */
public void setQuerySource (String QuerySource)
{
if (QuerySource == null) throw new IllegalArgumentException ("QuerySource is mandatory");
if (QuerySource.equals("C") || QuerySource.equals("H") || QuerySource.equals("J") || QuerySource.equals("W"));
 else throw new IllegalArgumentException ("QuerySource Invalid value - " + QuerySource + " - Reference_ID=391 - C - H - J - W");
if (QuerySource.length() > 1)
{
log.warning("Length > 1 - truncated");
QuerySource = QuerySource.substring(0,0);
}
set_Value ("QuerySource", QuerySource);
}
/** Get Query Source.
@return Source of the Query */
public String getQuerySource() 
{
return (String)get_Value("QuerySource");
}
}
