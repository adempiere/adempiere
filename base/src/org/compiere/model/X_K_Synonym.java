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
/** Generated Model for K_Synonym
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_K_Synonym extends PO
{
/** Standard Constructor
@param ctx context
@param K_Synonym_ID id
@param trxName transaction
*/
public X_K_Synonym (Properties ctx, int K_Synonym_ID, String trxName)
{
super (ctx, K_Synonym_ID, trxName);
/** if (K_Synonym_ID == 0)
{
setAD_Language (null);
setK_Synonym_ID (0);
setName (null);
setSynonymName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_Synonym (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=608 */
public static final int Table_ID=MTable.getTable_ID("K_Synonym");

/** TableName=K_Synonym */
public static final String Table_Name="K_Synonym";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_Synonym");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_K_Synonym[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Language AD_Reference_ID=106 */
public static final int AD_LANGUAGE_AD_Reference_ID=106;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_Value ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";
/** Set Knowledge Synonym.
@param K_Synonym_ID Knowlege Keyword Synonym */
public void setK_Synonym_ID (int K_Synonym_ID)
{
if (K_Synonym_ID < 1) throw new IllegalArgumentException ("K_Synonym_ID is mandatory.");
set_ValueNoCheck ("K_Synonym_ID", Integer.valueOf(K_Synonym_ID));
}
/** Get Knowledge Synonym.
@return Knowlege Keyword Synonym */
public int getK_Synonym_ID() 
{
Integer ii = (Integer)get_Value("K_Synonym_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name K_Synonym_ID */
public static final String COLUMNNAME_K_Synonym_ID = "K_Synonym_ID";
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
/** Set Synonym Name.
@param SynonymName The synonym for the name */
public void setSynonymName (String SynonymName)
{
if (SynonymName == null) throw new IllegalArgumentException ("SynonymName is mandatory.");
if (SynonymName.length() > 60)
{
log.warning("Length > 60 - truncated");
SynonymName = SynonymName.substring(0,59);
}
set_Value ("SynonymName", SynonymName);
}
/** Get Synonym Name.
@return The synonym for the name */
public String getSynonymName() 
{
return (String)get_Value("SynonymName");
}
/** Column name SynonymName */
public static final String COLUMNNAME_SynonymName = "SynonymName";
}
