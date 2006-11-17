/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for CM_Container_Index
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-06-17 17:51:37.297 */
public class X_CM_Container_Index extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Container_Index_ID id
@param trxName transaction
*/
public X_CM_Container_Index (Properties ctx, int CM_Container_Index_ID, String trxName)
{
super (ctx, CM_Container_Index_ID, trxName);
/** if (CM_Container_Index_ID == 0)
{
setCM_CONTAINER_INDEX_ID (0);
setCM_Container_Element_ID (0);
setKeyword (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Container_Index (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=862 */
public static final int Table_ID=862;

/** TableName=CM_Container_Index */
public static final String Table_Name="CM_Container_Index";

protected static KeyNamePair Model = new KeyNamePair(862,"CM_Container_Index");

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
StringBuffer sb = new StringBuffer ("X_CM_Container_Index[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Container Keyword Index.
@param CM_CONTAINER_INDEX_ID Container keyword index contains the content in keywords */
public void setCM_CONTAINER_INDEX_ID (int CM_CONTAINER_INDEX_ID)
{
if (CM_CONTAINER_INDEX_ID < 1) throw new IllegalArgumentException ("CM_CONTAINER_INDEX_ID is mandatory.");
set_ValueNoCheck ("CM_CONTAINER_INDEX_ID", new Integer(CM_CONTAINER_INDEX_ID));
}
/** Get Container Keyword Index.
@return Container keyword index contains the content in keywords */
public int getCM_CONTAINER_INDEX_ID() 
{
Integer ii = (Integer)get_Value("CM_CONTAINER_INDEX_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Container Element.
@param CM_Container_Element_ID Container element i.e. Headline, Content, Footer etc. */
public void setCM_Container_Element_ID (int CM_Container_Element_ID)
{
if (CM_Container_Element_ID < 1) throw new IllegalArgumentException ("CM_Container_Element_ID is mandatory.");
set_Value ("CM_Container_Element_ID", new Integer(CM_Container_Element_ID));
}
/** Get Container Element.
@return Container element i.e. Headline, Content, Footer etc. */
public int getCM_Container_Element_ID() 
{
Integer ii = (Integer)get_Value("CM_Container_Element_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Keyword.
@param Keyword Case insensitive keyword */
public void setKeyword (String Keyword)
{
if (Keyword == null) throw new IllegalArgumentException ("Keyword is mandatory.");
if (Keyword.length() > 255)
{
log.warning("Length > 255 - truncated");
Keyword = Keyword.substring(0,254);
}
set_Value ("Keyword", Keyword);
}
/** Get Keyword.
@return Case insensitive keyword */
public String getKeyword() 
{
return (String)get_Value("Keyword");
}
}
