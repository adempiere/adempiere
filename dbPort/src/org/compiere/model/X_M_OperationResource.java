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
/** Generated Model for M_OperationResource
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_OperationResource extends PO
{
/** Standard Constructor
@param ctx context
@param M_OperationResource_ID id
@param trxName transaction
*/
public X_M_OperationResource (Properties ctx, int M_OperationResource_ID, String trxName)
{
super (ctx, M_OperationResource_ID, trxName);
/** if (M_OperationResource_ID == 0)
{
setM_OperationResource_ID (0);
setM_ProductOperation_ID (0);
setName (null);
setSetupTime (Env.ZERO);
setTeardownTime (Env.ZERO);
setUnitRuntime (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_OperationResource (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=797 */
public static final int Table_ID=797;

/** TableName=M_OperationResource */
public static final String Table_Name="M_OperationResource";

protected static KeyNamePair Model = new KeyNamePair(797,"M_OperationResource");

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
StringBuffer sb = new StringBuffer ("X_M_OperationResource[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID <= 0) set_Value ("A_Asset_ID", null);
 else 
set_Value ("A_Asset_ID", new Integer(A_Asset_ID));
}
/** Get Asset.
@return Asset used internally or by customers */
public int getA_Asset_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Position.
@param C_Job_ID Job Position */
public void setC_Job_ID (int C_Job_ID)
{
if (C_Job_ID <= 0) set_Value ("C_Job_ID", null);
 else 
set_Value ("C_Job_ID", new Integer(C_Job_ID));
}
/** Get Position.
@return Job Position */
public int getC_Job_ID() 
{
Integer ii = (Integer)get_Value("C_Job_ID");
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Operation Resource.
@param M_OperationResource_ID Product Operation Resource */
public void setM_OperationResource_ID (int M_OperationResource_ID)
{
if (M_OperationResource_ID < 1) throw new IllegalArgumentException ("M_OperationResource_ID is mandatory.");
set_ValueNoCheck ("M_OperationResource_ID", new Integer(M_OperationResource_ID));
}
/** Get Operation Resource.
@return Product Operation Resource */
public int getM_OperationResource_ID() 
{
Integer ii = (Integer)get_Value("M_OperationResource_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product Operation.
@param M_ProductOperation_ID Product Manufacturing Operation */
public void setM_ProductOperation_ID (int M_ProductOperation_ID)
{
if (M_ProductOperation_ID < 1) throw new IllegalArgumentException ("M_ProductOperation_ID is mandatory.");
set_ValueNoCheck ("M_ProductOperation_ID", new Integer(M_ProductOperation_ID));
}
/** Get Product Operation.
@return Product Manufacturing Operation */
public int getM_ProductOperation_ID() 
{
Integer ii = (Integer)get_Value("M_ProductOperation_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Setup Time.
@param SetupTime Setup time before starting Production */
public void setSetupTime (BigDecimal SetupTime)
{
if (SetupTime == null) throw new IllegalArgumentException ("SetupTime is mandatory.");
set_Value ("SetupTime", SetupTime);
}
/** Get Setup Time.
@return Setup time before starting Production */
public BigDecimal getSetupTime() 
{
BigDecimal bd = (BigDecimal)get_Value("SetupTime");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Teardown Time.
@param TeardownTime Time at the end of the operation */
public void setTeardownTime (BigDecimal TeardownTime)
{
if (TeardownTime == null) throw new IllegalArgumentException ("TeardownTime is mandatory.");
set_Value ("TeardownTime", TeardownTime);
}
/** Get Teardown Time.
@return Time at the end of the operation */
public BigDecimal getTeardownTime() 
{
BigDecimal bd = (BigDecimal)get_Value("TeardownTime");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Runtime per Unit.
@param UnitRuntime Time to produce one unit */
public void setUnitRuntime (BigDecimal UnitRuntime)
{
if (UnitRuntime == null) throw new IllegalArgumentException ("UnitRuntime is mandatory.");
set_Value ("UnitRuntime", UnitRuntime);
}
/** Get Runtime per Unit.
@return Time to produce one unit */
public BigDecimal getUnitRuntime() 
{
BigDecimal bd = (BigDecimal)get_Value("UnitRuntime");
if (bd == null) return Env.ZERO;
return bd;
}
}
