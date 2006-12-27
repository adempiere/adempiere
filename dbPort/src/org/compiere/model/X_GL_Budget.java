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
/** Generated Model for GL_Budget
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_GL_Budget extends PO
{
/** Standard Constructor
@param ctx context
@param GL_Budget_ID id
@param trxName transaction
*/
public X_GL_Budget (Properties ctx, int GL_Budget_ID, String trxName)
{
super (ctx, GL_Budget_ID, trxName);
/** if (GL_Budget_ID == 0)
{
setGL_Budget_ID (0);
setIsPrimary (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_Budget (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=271 */
public static final int Table_ID=MTable.getTable_ID("GL_Budget");

/** TableName=GL_Budget */
public static final String Table_Name="GL_Budget";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"GL_Budget");

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
StringBuffer sb = new StringBuffer ("X_GL_Budget[").append(get_ID()).append("]");
return sb.toString();
}

/** BudgetStatus AD_Reference_ID=178 */
public static final int BUDGETSTATUS_AD_Reference_ID=178;
/** Approved = A */
public static final String BUDGETSTATUS_Approved = "A";
/** Draft = D */
public static final String BUDGETSTATUS_Draft = "D";
/** Set Budget Status.
@param BudgetStatus Indicates the current status of this budget */
public void setBudgetStatus (String BudgetStatus)
{
if (BudgetStatus == null || BudgetStatus.equals("A") || BudgetStatus.equals("D"));
 else throw new IllegalArgumentException ("BudgetStatus Invalid value - " + BudgetStatus + " - Reference_ID=178 - A - D");
if (BudgetStatus != null && BudgetStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
BudgetStatus = BudgetStatus.substring(0,0);
}
set_Value ("BudgetStatus", BudgetStatus);
}
/** Get Budget Status.
@return Indicates the current status of this budget */
public String getBudgetStatus() 
{
return (String)get_Value("BudgetStatus");
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
/** Set Budget.
@param GL_Budget_ID General Ledger Budget */
public void setGL_Budget_ID (int GL_Budget_ID)
{
if (GL_Budget_ID < 1) throw new IllegalArgumentException ("GL_Budget_ID is mandatory.");
set_ValueNoCheck ("GL_Budget_ID", Integer.valueOf(GL_Budget_ID));
}
/** Get Budget.
@return General Ledger Budget */
public int getGL_Budget_ID() 
{
Integer ii = (Integer)get_Value("GL_Budget_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Primary.
@param IsPrimary Indicates if this is the primary budget */
public void setIsPrimary (boolean IsPrimary)
{
set_Value ("IsPrimary", Boolean.valueOf(IsPrimary));
}
/** Get Primary.
@return Indicates if this is the primary budget */
public boolean isPrimary() 
{
Object oo = get_Value("IsPrimary");
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
}
