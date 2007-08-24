/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for AD_Role
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:27.421
     */
    public interface I_AD_Role 
{

    /** TableName=AD_Role */
    public static final String Table_Name = "AD_Role";

    /** AD_Table_ID=156 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

    /** Column name AD_Tree_Menu_ID */
    public static final String COLUMNNAME_AD_Tree_Menu_ID = "AD_Tree_Menu_ID";

	/** Set Menu Tree.
	  * Tree of the menu
	  */
	public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID);

	/** Get Menu Tree.
	  * Tree of the menu
	  */
	public int getAD_Tree_Menu_ID();

    /** Column name AD_Tree_Org_ID */
    public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";

	/** Set Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID);

	/** Get Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public int getAD_Tree_Org_ID();

    /** Column name Allow_Info_Account */
    public static final String COLUMNNAME_Allow_Info_Account = "Allow_Info_Account";

	/** Set Allow Info Account	  */
	public void setAllow_Info_Account (boolean Allow_Info_Account);

	/** Get Allow Info Account	  */
	public boolean isAllow_Info_Account();

    /** Column name Allow_Info_Asset */
    public static final String COLUMNNAME_Allow_Info_Asset = "Allow_Info_Asset";

	/** Set Allow Info Asset	  */
	public void setAllow_Info_Asset (boolean Allow_Info_Asset);

	/** Get Allow Info Asset	  */
	public boolean isAllow_Info_Asset();

    /** Column name Allow_Info_BPartner */
    public static final String COLUMNNAME_Allow_Info_BPartner = "Allow_Info_BPartner";

	/** Set Allow Info BPartner	  */
	public void setAllow_Info_BPartner (boolean Allow_Info_BPartner);

	/** Get Allow Info BPartner	  */
	public boolean isAllow_Info_BPartner();

    /** Column name Allow_Info_CashJournal */
    public static final String COLUMNNAME_Allow_Info_CashJournal = "Allow_Info_CashJournal";

	/** Set Allow Info CashJournal	  */
	public void setAllow_Info_CashJournal (boolean Allow_Info_CashJournal);

	/** Get Allow Info CashJournal	  */
	public boolean isAllow_Info_CashJournal();

    /** Column name Allow_Info_InOut */
    public static final String COLUMNNAME_Allow_Info_InOut = "Allow_Info_InOut";

	/** Set Allow Info InOut	  */
	public void setAllow_Info_InOut (boolean Allow_Info_InOut);

	/** Get Allow Info InOut	  */
	public boolean isAllow_Info_InOut();

    /** Column name Allow_Info_Invoice */
    public static final String COLUMNNAME_Allow_Info_Invoice = "Allow_Info_Invoice";

	/** Set Allow Info Invoice	  */
	public void setAllow_Info_Invoice (boolean Allow_Info_Invoice);

	/** Get Allow Info Invoice	  */
	public boolean isAllow_Info_Invoice();

    /** Column name Allow_Info_Order */
    public static final String COLUMNNAME_Allow_Info_Order = "Allow_Info_Order";

	/** Set Allow Info Order	  */
	public void setAllow_Info_Order (boolean Allow_Info_Order);

	/** Get Allow Info Order	  */
	public boolean isAllow_Info_Order();

    /** Column name Allow_Info_Payment */
    public static final String COLUMNNAME_Allow_Info_Payment = "Allow_Info_Payment";

	/** Set Allow Info Payment	  */
	public void setAllow_Info_Payment (boolean Allow_Info_Payment);

	/** Get Allow Info Payment	  */
	public boolean isAllow_Info_Payment();

    /** Column name Allow_Info_Product */
    public static final String COLUMNNAME_Allow_Info_Product = "Allow_Info_Product";

	/** Set Allow Info Product	  */
	public void setAllow_Info_Product (boolean Allow_Info_Product);

	/** Get Allow Info Product	  */
	public boolean isAllow_Info_Product();

    /** Column name Allow_Info_Resource */
    public static final String COLUMNNAME_Allow_Info_Resource = "Allow_Info_Resource";

	/** Set Allow Info Resource	  */
	public void setAllow_Info_Resource (boolean Allow_Info_Resource);

	/** Get Allow Info Resource	  */
	public boolean isAllow_Info_Resource();

    /** Column name Allow_Info_Schedule */
    public static final String COLUMNNAME_Allow_Info_Schedule = "Allow_Info_Schedule";

	/** Set Allow Info Schedule	  */
	public void setAllow_Info_Schedule (boolean Allow_Info_Schedule);

	/** Get Allow Info Schedule	  */
	public boolean isAllow_Info_Schedule();

    /** Column name AmtApproval */
    public static final String COLUMNNAME_AmtApproval = "AmtApproval";

	/** Set Approval Amount.
	  * The approval amount limit for this role
	  */
	public void setAmtApproval (BigDecimal AmtApproval);

	/** Get Approval Amount.
	  * The approval amount limit for this role
	  */
	public BigDecimal getAmtApproval();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getI_C_Currency() throws Exception;

    /** Column name ConfirmQueryRecords */
    public static final String COLUMNNAME_ConfirmQueryRecords = "ConfirmQueryRecords";

	/** Set Confirm Query Records.
	  * Require Confirmation if more records will be returned by the query (If not defined 500)
	  */
	public void setConfirmQueryRecords (int ConfirmQueryRecords);

	/** Get Confirm Query Records.
	  * Require Confirmation if more records will be returned by the query (If not defined 500)
	  */
	public int getConfirmQueryRecords();

    /** Column name ConnectionProfile */
    public static final String COLUMNNAME_ConnectionProfile = "ConnectionProfile";

	/** Set Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile);

	/** Get Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsAccessAllOrgs */
    public static final String COLUMNNAME_IsAccessAllOrgs = "IsAccessAllOrgs";

	/** Set Access all Orgs.
	  * Access all Organizations (no org access control) of the client
	  */
	public void setIsAccessAllOrgs (boolean IsAccessAllOrgs);

	/** Get Access all Orgs.
	  * Access all Organizations (no org access control) of the client
	  */
	public boolean isAccessAllOrgs();

    /** Column name IsCanApproveOwnDoc */
    public static final String COLUMNNAME_IsCanApproveOwnDoc = "IsCanApproveOwnDoc";

	/** Set Approve own Documents.
	  * Users with this role can approve their own documents
	  */
	public void setIsCanApproveOwnDoc (boolean IsCanApproveOwnDoc);

	/** Get Approve own Documents.
	  * Users with this role can approve their own documents
	  */
	public boolean isCanApproveOwnDoc();

    /** Column name IsCanExport */
    public static final String COLUMNNAME_IsCanExport = "IsCanExport";

	/** Set Can Export.
	  * Users with this role can export data
	  */
	public void setIsCanExport (boolean IsCanExport);

	/** Get Can Export.
	  * Users with this role can export data
	  */
	public boolean isCanExport();

    /** Column name IsCanReport */
    public static final String COLUMNNAME_IsCanReport = "IsCanReport";

	/** Set Can Report.
	  * Users with this role can create reports
	  */
	public void setIsCanReport (boolean IsCanReport);

	/** Get Can Report.
	  * Users with this role can create reports
	  */
	public boolean isCanReport();

    /** Column name IsChangeLog */
    public static final String COLUMNNAME_IsChangeLog = "IsChangeLog";

	/** Set Maintain Change Log.
	  * Maintain a log of changes
	  */
	public void setIsChangeLog (boolean IsChangeLog);

	/** Get Maintain Change Log.
	  * Maintain a log of changes
	  */
	public boolean isChangeLog();

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

    /** Column name IsPersonalAccess */
    public static final String COLUMNNAME_IsPersonalAccess = "IsPersonalAccess";

	/** Set Personal Access.
	  * Allow access to all personal records
	  */
	public void setIsPersonalAccess (boolean IsPersonalAccess);

	/** Get Personal Access.
	  * Allow access to all personal records
	  */
	public boolean isPersonalAccess();

    /** Column name IsPersonalLock */
    public static final String COLUMNNAME_IsPersonalLock = "IsPersonalLock";

	/** Set Personal Lock.
	  * Allow users with role to lock access to personal records
	  */
	public void setIsPersonalLock (boolean IsPersonalLock);

	/** Get Personal Lock.
	  * Allow users with role to lock access to personal records
	  */
	public boolean isPersonalLock();

    /** Column name IsShowAcct */
    public static final String COLUMNNAME_IsShowAcct = "IsShowAcct";

	/** Set Show Accounting.
	  * Users with this role can see accounting information
	  */
	public void setIsShowAcct (boolean IsShowAcct);

	/** Get Show Accounting.
	  * Users with this role can see accounting information
	  */
	public boolean isShowAcct();

    /** Column name IsUseUserOrgAccess */
    public static final String COLUMNNAME_IsUseUserOrgAccess = "IsUseUserOrgAccess";

	/** Set Use User Org Access.
	  * Use Org Access defined by user instead of Role Org Access
	  */
	public void setIsUseUserOrgAccess (boolean IsUseUserOrgAccess);

	/** Get Use User Org Access.
	  * Use Org Access defined by user instead of Role Org Access
	  */
	public boolean isUseUserOrgAccess();

    /** Column name MaxQueryRecords */
    public static final String COLUMNNAME_MaxQueryRecords = "MaxQueryRecords";

	/** Set Max Query Records.
	  * If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public void setMaxQueryRecords (int MaxQueryRecords);

	/** Get Max Query Records.
	  * If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public int getMaxQueryRecords();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name OverwritePriceLimit */
    public static final String COLUMNNAME_OverwritePriceLimit = "OverwritePriceLimit";

	/** Set Overwrite Price Limit.
	  * Overwrite Price Limit if the Price List  enforces the Price Limit
	  */
	public void setOverwritePriceLimit (boolean OverwritePriceLimit);

	/** Get Overwrite Price Limit.
	  * Overwrite Price Limit if the Price List  enforces the Price Limit
	  */
	public boolean isOverwritePriceLimit();

    /** Column name PreferenceType */
    public static final String COLUMNNAME_PreferenceType = "PreferenceType";

	/** Set Preference Level.
	  * Determines what preferences the user can set
	  */
	public void setPreferenceType (String PreferenceType);

	/** Get Preference Level.
	  * Determines what preferences the user can set
	  */
	public String getPreferenceType();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

    /** Column name UserLevel */
    public static final String COLUMNNAME_UserLevel = "UserLevel";

	/** Set User Level.
	  * System Client Organization
	  */
	public void setUserLevel (String UserLevel);

	/** Get User Level.
	  * System Client Organization
	  */
	public String getUserLevel();
}
