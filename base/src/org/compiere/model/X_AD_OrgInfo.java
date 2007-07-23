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
/** Generated Model for AD_OrgInfo
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_OrgInfo extends PO
{
/** Standard Constructor
@param ctx context
@param AD_OrgInfo_ID id
@param trxName transaction
*/
public X_AD_OrgInfo (Properties ctx, int AD_OrgInfo_ID, String trxName)
{
super (ctx, AD_OrgInfo_ID, trxName);
/** if (AD_OrgInfo_ID == 0)
{
setDUNS (null);
setTaxID (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_OrgInfo (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_OrgInfo */
public static final String Table_Name="AD_OrgInfo";

/** AD_Table_ID=228 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
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
StringBuffer sb = new StringBuffer ("X_AD_OrgInfo[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Organization Type.
@param AD_OrgType_ID Organization Type allows you to categorize your organizations */
public void setAD_OrgType_ID (int AD_OrgType_ID)
{
if (AD_OrgType_ID <= 0) set_Value ("AD_OrgType_ID", null);
 else 
set_Value ("AD_OrgType_ID", Integer.valueOf(AD_OrgType_ID));
}
/** Get Organization Type.
@return Organization Type allows you to categorize your organizations */
public int getAD_OrgType_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgType_ID */
public static final String COLUMNNAME_AD_OrgType_ID = "AD_OrgType_ID";
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID <= 0) set_Value ("C_Location_ID", null);
 else 
set_Value ("C_Location_ID", Integer.valueOf(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Location_ID */
public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";
/** Set D-U-N-S.
@param DUNS Dun & Bradstreet Number */
public void setDUNS (String DUNS)
{
if (DUNS == null) throw new IllegalArgumentException ("DUNS is mandatory.");
if (DUNS.length() > 11)
{
log.warning("Length > 11 - truncated");
DUNS = DUNS.substring(0,10);
}
set_Value ("DUNS", DUNS);
}
/** Get D-U-N-S.
@return Dun & Bradstreet Number */
public String getDUNS() 
{
return (String)get_Value("DUNS");
}
/** Column name DUNS */
public static final String COLUMNNAME_DUNS = "DUNS";
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID <= 0) set_Value ("M_Warehouse_ID", null);
 else 
set_Value ("M_Warehouse_ID", Integer.valueOf(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Warehouse_ID */
public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

/** Parent_Org_ID AD_Reference_ID=130 */
public static final int PARENT_ORG_ID_AD_Reference_ID=130;
/** Set Parent Organization.
@param Parent_Org_ID Parent (superior) Organization  */
public void setParent_Org_ID (int Parent_Org_ID)
{
if (Parent_Org_ID <= 0) set_Value ("Parent_Org_ID", null);
 else 
set_Value ("Parent_Org_ID", Integer.valueOf(Parent_Org_ID));
}
/** Get Parent Organization.
@return Parent (superior) Organization  */
public int getParent_Org_ID() 
{
Integer ii = (Integer)get_Value("Parent_Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Parent_Org_ID */
public static final String COLUMNNAME_Parent_Org_ID = "Parent_Org_ID";

/** Supervisor_ID AD_Reference_ID=286 */
public static final int SUPERVISOR_ID_AD_Reference_ID=286;
/** Set Supervisor.
@param Supervisor_ID Supervisor for this user/organization - used for escalation and approval */
public void setSupervisor_ID (int Supervisor_ID)
{
if (Supervisor_ID <= 0) set_Value ("Supervisor_ID", null);
 else 
set_Value ("Supervisor_ID", Integer.valueOf(Supervisor_ID));
}
/** Get Supervisor.
@return Supervisor for this user/organization - used for escalation and approval */
public int getSupervisor_ID() 
{
Integer ii = (Integer)get_Value("Supervisor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Supervisor_ID */
public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";
/** Set Tax ID.
@param TaxID Tax Identification */
public void setTaxID (String TaxID)
{
if (TaxID == null) throw new IllegalArgumentException ("TaxID is mandatory.");
if (TaxID.length() > 20)
{
log.warning("Length > 20 - truncated");
TaxID = TaxID.substring(0,19);
}
set_Value ("TaxID", TaxID);
}
/** Get Tax ID.
@return Tax Identification */
public String getTaxID() 
{
return (String)get_Value("TaxID");
}
/** Column name TaxID */
public static final String COLUMNNAME_TaxID = "TaxID";
}
