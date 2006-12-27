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
/** Generated Model for AD_TreeNodeCMT
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_TreeNodeCMT extends PO
{
/** Standard Constructor
@param ctx context
@param AD_TreeNodeCMT_ID id
@param trxName transaction
*/
public X_AD_TreeNodeCMT (Properties ctx, int AD_TreeNodeCMT_ID, String trxName)
{
super (ctx, AD_TreeNodeCMT_ID, trxName);
/** if (AD_TreeNodeCMT_ID == 0)
{
setAD_Tree_ID (0);
setNode_ID (0);
setParent_ID (0);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_TreeNodeCMT (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=848 */
public static final int Table_ID=MTable.getTable_ID("AD_TreeNodeCMT");

/** TableName=AD_TreeNodeCMT */
public static final String Table_Name="AD_TreeNodeCMT";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_TreeNodeCMT");

protected BigDecimal accessLevel = new BigDecimal(7);
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
StringBuffer sb = new StringBuffer ("X_AD_TreeNodeCMT[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Tree.
@param AD_Tree_ID Identifies a Tree */
public void setAD_Tree_ID (int AD_Tree_ID)
{
if (AD_Tree_ID < 1) throw new IllegalArgumentException ("AD_Tree_ID is mandatory.");
set_ValueNoCheck ("AD_Tree_ID", Integer.valueOf(AD_Tree_ID));
}
/** Get Tree.
@return Identifies a Tree */
public int getAD_Tree_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Node_ID.
@param Node_ID Node_ID */
public void setNode_ID (int Node_ID)
{
if (Node_ID < 0) throw new IllegalArgumentException ("Node_ID is mandatory.");
set_ValueNoCheck ("Node_ID", Integer.valueOf(Node_ID));
}
/** Get Node_ID.
@return Node_ID */
public int getNode_ID() 
{
Integer ii = (Integer)get_Value("Node_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Parent.
@param Parent_ID Parent of Entity */
public void setParent_ID (int Parent_ID)
{
if (Parent_ID < 1) throw new IllegalArgumentException ("Parent_ID is mandatory.");
set_Value ("Parent_ID", Integer.valueOf(Parent_ID));
}
/** Get Parent.
@return Parent of Entity */
public int getParent_ID() 
{
Integer ii = (Integer)get_Value("Parent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
}
