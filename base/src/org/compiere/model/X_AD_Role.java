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
/** Generated Model for AD_Role
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Role extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Role_ID id
@param trxName transaction
*/
public X_AD_Role (Properties ctx, int AD_Role_ID, String trxName)
{
super (ctx, AD_Role_ID, trxName);
/** if (AD_Role_ID == 0)
{
setAD_Role_ID (0);
setConfirmQueryRecords (0);	// 0
setIsAccessAllOrgs (false);	// N
setIsCanApproveOwnDoc (false);
setIsCanExport (true);	// Y
setIsCanReport (true);	// Y
setIsChangeLog (false);	// N
setIsManual (false);
setIsPersonalAccess (false);	// N
setIsPersonalLock (false);	// N
setIsShowAcct (false);	// N
setIsUseUserOrgAccess (false);	// N
setMaxQueryRecords (0);	// 0
setName (null);
setOverwritePriceLimit (false);	// N
setPreferenceType (null);	// O
setUserLevel (null);	// O
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Role (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=156 */
public static final int Table_ID=MTable.getTable_ID("AD_Role");

/** TableName=AD_Role */
public static final String Table_Name="AD_Role";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Role");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
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
StringBuffer sb = new StringBuffer ("X_AD_Role[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID < 0) throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
set_ValueNoCheck ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Role_ID */
public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

/** AD_Tree_Menu_ID AD_Reference_ID=184 */
public static final int AD_TREE_MENU_ID_AD_Reference_ID=184;
/** Set Menu Tree.
@param AD_Tree_Menu_ID Tree of the menu */
public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID)
{
if (AD_Tree_Menu_ID <= 0) set_Value ("AD_Tree_Menu_ID", null);
 else 
set_Value ("AD_Tree_Menu_ID", Integer.valueOf(AD_Tree_Menu_ID));
}
/** Get Menu Tree.
@return Tree of the menu */
public int getAD_Tree_Menu_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Menu_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Menu_ID */
public static final String COLUMNNAME_AD_Tree_Menu_ID = "AD_Tree_Menu_ID";

/** AD_Tree_Org_ID AD_Reference_ID=184 */
public static final int AD_TREE_ORG_ID_AD_Reference_ID=184;
/** Set Organization Tree.
@param AD_Tree_Org_ID Tree to determine organizational hierarchy */
public void setAD_Tree_Org_ID (int AD_Tree_Org_ID)
{
if (AD_Tree_Org_ID <= 0) set_Value ("AD_Tree_Org_ID", null);
 else 
set_Value ("AD_Tree_Org_ID", Integer.valueOf(AD_Tree_Org_ID));
}
/** Get Organization Tree.
@return Tree to determine organizational hierarchy */
public int getAD_Tree_Org_ID() 
{
Integer ii = (Integer)get_Value("AD_Tree_Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tree_Org_ID */
public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";
/** Set Allow Info Account.
@param Allow_Info_Account Allow Info Account */
public void setAllow_Info_Account (boolean Allow_Info_Account)
{
set_Value ("Allow_Info_Account", Boolean.valueOf(Allow_Info_Account));
}
/** Get Allow Info Account.
@return Allow Info Account */
public boolean isAllow_Info_Account() 
{
Object oo = get_Value("Allow_Info_Account");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Account */
public static final String COLUMNNAME_Allow_Info_Account = "Allow_Info_Account";
/** Set Allow Info Asset.
@param Allow_Info_Asset Allow Info Asset */
public void setAllow_Info_Asset (boolean Allow_Info_Asset)
{
set_Value ("Allow_Info_Asset", Boolean.valueOf(Allow_Info_Asset));
}
/** Get Allow Info Asset.
@return Allow Info Asset */
public boolean isAllow_Info_Asset() 
{
Object oo = get_Value("Allow_Info_Asset");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Asset */
public static final String COLUMNNAME_Allow_Info_Asset = "Allow_Info_Asset";
/** Set Allow Info BPartner.
@param Allow_Info_BPartner Allow Info BPartner */
public void setAllow_Info_BPartner (boolean Allow_Info_BPartner)
{
set_Value ("Allow_Info_BPartner", Boolean.valueOf(Allow_Info_BPartner));
}
/** Get Allow Info BPartner.
@return Allow Info BPartner */
public boolean isAllow_Info_BPartner() 
{
Object oo = get_Value("Allow_Info_BPartner");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_BPartner */
public static final String COLUMNNAME_Allow_Info_BPartner = "Allow_Info_BPartner";
/** Set Allow Info CashJournal.
@param Allow_Info_CashJournal Allow Info CashJournal */
public void setAllow_Info_CashJournal (boolean Allow_Info_CashJournal)
{
set_Value ("Allow_Info_CashJournal", Boolean.valueOf(Allow_Info_CashJournal));
}
/** Get Allow Info CashJournal.
@return Allow Info CashJournal */
public boolean isAllow_Info_CashJournal() 
{
Object oo = get_Value("Allow_Info_CashJournal");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_CashJournal */
public static final String COLUMNNAME_Allow_Info_CashJournal = "Allow_Info_CashJournal";
/** Set Allow Info InOut.
@param Allow_Info_InOut Allow Info InOut */
public void setAllow_Info_InOut (boolean Allow_Info_InOut)
{
set_Value ("Allow_Info_InOut", Boolean.valueOf(Allow_Info_InOut));
}
/** Get Allow Info InOut.
@return Allow Info InOut */
public boolean isAllow_Info_InOut() 
{
Object oo = get_Value("Allow_Info_InOut");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_InOut */
public static final String COLUMNNAME_Allow_Info_InOut = "Allow_Info_InOut";
/** Set Allow Info Invoice.
@param Allow_Info_Invoice Allow Info Invoice */
public void setAllow_Info_Invoice (boolean Allow_Info_Invoice)
{
set_Value ("Allow_Info_Invoice", Boolean.valueOf(Allow_Info_Invoice));
}
/** Get Allow Info Invoice.
@return Allow Info Invoice */
public boolean isAllow_Info_Invoice() 
{
Object oo = get_Value("Allow_Info_Invoice");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Invoice */
public static final String COLUMNNAME_Allow_Info_Invoice = "Allow_Info_Invoice";
/** Set Allow Info Order.
@param Allow_Info_Order Allow Info Order */
public void setAllow_Info_Order (boolean Allow_Info_Order)
{
set_Value ("Allow_Info_Order", Boolean.valueOf(Allow_Info_Order));
}
/** Get Allow Info Order.
@return Allow Info Order */
public boolean isAllow_Info_Order() 
{
Object oo = get_Value("Allow_Info_Order");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Order */
public static final String COLUMNNAME_Allow_Info_Order = "Allow_Info_Order";
/** Set Allow Info Payment.
@param Allow_Info_Payment Allow Info Payment */
public void setAllow_Info_Payment (boolean Allow_Info_Payment)
{
set_Value ("Allow_Info_Payment", Boolean.valueOf(Allow_Info_Payment));
}
/** Get Allow Info Payment.
@return Allow Info Payment */
public boolean isAllow_Info_Payment() 
{
Object oo = get_Value("Allow_Info_Payment");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Payment */
public static final String COLUMNNAME_Allow_Info_Payment = "Allow_Info_Payment";
/** Set Allow Info Product.
@param Allow_Info_Product Allow Info Product */
public void setAllow_Info_Product (boolean Allow_Info_Product)
{
set_Value ("Allow_Info_Product", Boolean.valueOf(Allow_Info_Product));
}
/** Get Allow Info Product.
@return Allow Info Product */
public boolean isAllow_Info_Product() 
{
Object oo = get_Value("Allow_Info_Product");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Product */
public static final String COLUMNNAME_Allow_Info_Product = "Allow_Info_Product";
/** Set Allow Info Resource.
@param Allow_Info_Resource Allow Info Resource */
public void setAllow_Info_Resource (boolean Allow_Info_Resource)
{
set_Value ("Allow_Info_Resource", Boolean.valueOf(Allow_Info_Resource));
}
/** Get Allow Info Resource.
@return Allow Info Resource */
public boolean isAllow_Info_Resource() 
{
Object oo = get_Value("Allow_Info_Resource");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Resource */
public static final String COLUMNNAME_Allow_Info_Resource = "Allow_Info_Resource";
/** Set Allow Info Schedule.
@param Allow_Info_Schedule Allow Info Schedule */
public void setAllow_Info_Schedule (boolean Allow_Info_Schedule)
{
set_Value ("Allow_Info_Schedule", Boolean.valueOf(Allow_Info_Schedule));
}
/** Get Allow Info Schedule.
@return Allow Info Schedule */
public boolean isAllow_Info_Schedule() 
{
Object oo = get_Value("Allow_Info_Schedule");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Allow_Info_Schedule */
public static final String COLUMNNAME_Allow_Info_Schedule = "Allow_Info_Schedule";
/** Set Approval Amount.
@param AmtApproval The approval amount limit for this role */
public void setAmtApproval (BigDecimal AmtApproval)
{
set_Value ("AmtApproval", AmtApproval);
}
/** Get Approval Amount.
@return The approval amount limit for this role */
public BigDecimal getAmtApproval() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtApproval");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AmtApproval */
public static final String COLUMNNAME_AmtApproval = "AmtApproval";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Confirm Query Records.
@param ConfirmQueryRecords Require Confirmation if more records will be returned by the query (If not defined 500) */
public void setConfirmQueryRecords (int ConfirmQueryRecords)
{
set_Value ("ConfirmQueryRecords", Integer.valueOf(ConfirmQueryRecords));
}
/** Get Confirm Query Records.
@return Require Confirmation if more records will be returned by the query (If not defined 500) */
public int getConfirmQueryRecords() 
{
Integer ii = (Integer)get_Value("ConfirmQueryRecords");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ConfirmQueryRecords */
public static final String COLUMNNAME_ConfirmQueryRecords = "ConfirmQueryRecords";

/** ConnectionProfile AD_Reference_ID=364 */
public static final int CONNECTIONPROFILE_AD_Reference_ID=364;
/** LAN = L */
public static final String CONNECTIONPROFILE_LAN = "L";
/** Terminal Server = T */
public static final String CONNECTIONPROFILE_TerminalServer = "T";
/** VPN = V */
public static final String CONNECTIONPROFILE_VPN = "V";
/** WAN = W */
public static final String CONNECTIONPROFILE_WAN = "W";
/** Set Connection Profile.
@param ConnectionProfile How a Java Client connects to the server(s) */
public void setConnectionProfile (String ConnectionProfile)
{
if (ConnectionProfile == null || ConnectionProfile.equals("L") || ConnectionProfile.equals("T") || ConnectionProfile.equals("V") || ConnectionProfile.equals("W"));
 else throw new IllegalArgumentException ("ConnectionProfile Invalid value - " + ConnectionProfile + " - Reference_ID=364 - L - T - V - W");
if (ConnectionProfile != null && ConnectionProfile.length() > 1)
{
log.warning("Length > 1 - truncated");
ConnectionProfile = ConnectionProfile.substring(0,0);
}
set_Value ("ConnectionProfile", ConnectionProfile);
}
/** Get Connection Profile.
@return How a Java Client connects to the server(s) */
public String getConnectionProfile() 
{
return (String)get_Value("ConnectionProfile");
}
/** Column name ConnectionProfile */
public static final String COLUMNNAME_ConnectionProfile = "ConnectionProfile";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Access all Orgs.
@param IsAccessAllOrgs Access all Organizations (no org access control) of the client */
public void setIsAccessAllOrgs (boolean IsAccessAllOrgs)
{
set_Value ("IsAccessAllOrgs", Boolean.valueOf(IsAccessAllOrgs));
}
/** Get Access all Orgs.
@return Access all Organizations (no org access control) of the client */
public boolean isAccessAllOrgs() 
{
Object oo = get_Value("IsAccessAllOrgs");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAccessAllOrgs */
public static final String COLUMNNAME_IsAccessAllOrgs = "IsAccessAllOrgs";
/** Set Approve own Documents.
@param IsCanApproveOwnDoc Users with this role can approve their own documents */
public void setIsCanApproveOwnDoc (boolean IsCanApproveOwnDoc)
{
set_Value ("IsCanApproveOwnDoc", Boolean.valueOf(IsCanApproveOwnDoc));
}
/** Get Approve own Documents.
@return Users with this role can approve their own documents */
public boolean isCanApproveOwnDoc() 
{
Object oo = get_Value("IsCanApproveOwnDoc");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCanApproveOwnDoc */
public static final String COLUMNNAME_IsCanApproveOwnDoc = "IsCanApproveOwnDoc";
/** Set Can Export.
@param IsCanExport Users with this role can export data */
public void setIsCanExport (boolean IsCanExport)
{
set_Value ("IsCanExport", Boolean.valueOf(IsCanExport));
}
/** Get Can Export.
@return Users with this role can export data */
public boolean isCanExport() 
{
Object oo = get_Value("IsCanExport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCanExport */
public static final String COLUMNNAME_IsCanExport = "IsCanExport";
/** Set Can Report.
@param IsCanReport Users with this role can create reports */
public void setIsCanReport (boolean IsCanReport)
{
set_Value ("IsCanReport", Boolean.valueOf(IsCanReport));
}
/** Get Can Report.
@return Users with this role can create reports */
public boolean isCanReport() 
{
Object oo = get_Value("IsCanReport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCanReport */
public static final String COLUMNNAME_IsCanReport = "IsCanReport";
/** Set Maintain Change Log.
@param IsChangeLog Maintain a log of changes */
public void setIsChangeLog (boolean IsChangeLog)
{
set_Value ("IsChangeLog", Boolean.valueOf(IsChangeLog));
}
/** Get Maintain Change Log.
@return Maintain a log of changes */
public boolean isChangeLog() 
{
Object oo = get_Value("IsChangeLog");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsChangeLog */
public static final String COLUMNNAME_IsChangeLog = "IsChangeLog";
/** Set Manual.
@param IsManual This is a manual process */
public void setIsManual (boolean IsManual)
{
set_Value ("IsManual", Boolean.valueOf(IsManual));
}
/** Get Manual.
@return This is a manual process */
public boolean isManual() 
{
Object oo = get_Value("IsManual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsManual */
public static final String COLUMNNAME_IsManual = "IsManual";
/** Set Personal Access.
@param IsPersonalAccess Allow access to all personal records */
public void setIsPersonalAccess (boolean IsPersonalAccess)
{
set_Value ("IsPersonalAccess", Boolean.valueOf(IsPersonalAccess));
}
/** Get Personal Access.
@return Allow access to all personal records */
public boolean isPersonalAccess() 
{
Object oo = get_Value("IsPersonalAccess");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPersonalAccess */
public static final String COLUMNNAME_IsPersonalAccess = "IsPersonalAccess";
/** Set Personal Lock.
@param IsPersonalLock Allow users with role to lock access to personal records */
public void setIsPersonalLock (boolean IsPersonalLock)
{
set_Value ("IsPersonalLock", Boolean.valueOf(IsPersonalLock));
}
/** Get Personal Lock.
@return Allow users with role to lock access to personal records */
public boolean isPersonalLock() 
{
Object oo = get_Value("IsPersonalLock");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPersonalLock */
public static final String COLUMNNAME_IsPersonalLock = "IsPersonalLock";
/** Set Show Accounting.
@param IsShowAcct Users with this role can see accounting information */
public void setIsShowAcct (boolean IsShowAcct)
{
set_Value ("IsShowAcct", Boolean.valueOf(IsShowAcct));
}
/** Get Show Accounting.
@return Users with this role can see accounting information */
public boolean isShowAcct() 
{
Object oo = get_Value("IsShowAcct");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsShowAcct */
public static final String COLUMNNAME_IsShowAcct = "IsShowAcct";
/** Set Use User Org Access.
@param IsUseUserOrgAccess Use Org Access defined by user instead of Role Org Access */
public void setIsUseUserOrgAccess (boolean IsUseUserOrgAccess)
{
set_Value ("IsUseUserOrgAccess", Boolean.valueOf(IsUseUserOrgAccess));
}
/** Get Use User Org Access.
@return Use Org Access defined by user instead of Role Org Access */
public boolean isUseUserOrgAccess() 
{
Object oo = get_Value("IsUseUserOrgAccess");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsUseUserOrgAccess */
public static final String COLUMNNAME_IsUseUserOrgAccess = "IsUseUserOrgAccess";
/** Set Max Query Records.
@param MaxQueryRecords If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records */
public void setMaxQueryRecords (int MaxQueryRecords)
{
set_Value ("MaxQueryRecords", Integer.valueOf(MaxQueryRecords));
}
/** Get Max Query Records.
@return If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records */
public int getMaxQueryRecords() 
{
Integer ii = (Integer)get_Value("MaxQueryRecords");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MaxQueryRecords */
public static final String COLUMNNAME_MaxQueryRecords = "MaxQueryRecords";
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
/** Set Overwrite Price Limit.
@param OverwritePriceLimit Overwrite Price Limit if the Price List  enforces the Price Limit */
public void setOverwritePriceLimit (boolean OverwritePriceLimit)
{
set_Value ("OverwritePriceLimit", Boolean.valueOf(OverwritePriceLimit));
}
/** Get Overwrite Price Limit.
@return Overwrite Price Limit if the Price List  enforces the Price Limit */
public boolean isOverwritePriceLimit() 
{
Object oo = get_Value("OverwritePriceLimit");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwritePriceLimit */
public static final String COLUMNNAME_OverwritePriceLimit = "OverwritePriceLimit";

/** PreferenceType AD_Reference_ID=330 */
public static final int PREFERENCETYPE_AD_Reference_ID=330;
/** Client = C */
public static final String PREFERENCETYPE_Client = "C";
/** None = N */
public static final String PREFERENCETYPE_None = "N";
/** Organization = O */
public static final String PREFERENCETYPE_Organization = "O";
/** User = U */
public static final String PREFERENCETYPE_User = "U";
/** Set Preference Level.
@param PreferenceType Determines what preferences the user can set */
public void setPreferenceType (String PreferenceType)
{
if (PreferenceType == null) throw new IllegalArgumentException ("PreferenceType is mandatory");
if (PreferenceType.equals("C") || PreferenceType.equals("N") || PreferenceType.equals("O") || PreferenceType.equals("U"));
 else throw new IllegalArgumentException ("PreferenceType Invalid value - " + PreferenceType + " - Reference_ID=330 - C - N - O - U");
if (PreferenceType.length() > 1)
{
log.warning("Length > 1 - truncated");
PreferenceType = PreferenceType.substring(0,0);
}
set_Value ("PreferenceType", PreferenceType);
}
/** Get Preference Level.
@return Determines what preferences the user can set */
public String getPreferenceType() 
{
return (String)get_Value("PreferenceType");
}
/** Column name PreferenceType */
public static final String COLUMNNAME_PreferenceType = "PreferenceType";

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

/** UserLevel AD_Reference_ID=226 */
public static final int USERLEVEL_AD_Reference_ID=226;
/** Organization =   O */
public static final String USERLEVEL_Organization = "  O";
/** Client =  C  */
public static final String USERLEVEL_Client = " C ";
/** Client+Organization =  CO */
public static final String USERLEVEL_ClientPlusOrganization = " CO";
/** System = S   */
public static final String USERLEVEL_System = "S  ";
/** Set User Level.
@param UserLevel System Client Organization */
public void setUserLevel (String UserLevel)
{
if (UserLevel == null) throw new IllegalArgumentException ("UserLevel is mandatory");
if (UserLevel.equals("  O") || UserLevel.equals(" C ") || UserLevel.equals(" CO") || UserLevel.equals("S  "));
 else throw new IllegalArgumentException ("UserLevel Invalid value - " + UserLevel + " - Reference_ID=226 -   O -  C  -  CO - S  ");
if (UserLevel.length() > 3)
{
log.warning("Length > 3 - truncated");
UserLevel = UserLevel.substring(0,2);
}
set_Value ("UserLevel", UserLevel);
}
/** Get User Level.
@return System Client Organization */
public String getUserLevel() 
{
return (String)get_Value("UserLevel");
}
/** Column name UserLevel */
public static final String COLUMNNAME_UserLevel = "UserLevel";
}
