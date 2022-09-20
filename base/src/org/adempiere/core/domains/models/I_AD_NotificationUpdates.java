/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_NotificationUpdates
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_AD_NotificationUpdates 
{

    /** TableName=AD_NotificationUpdates */
    public static final String Table_Name = "AD_NotificationUpdates";

    /** AD_Table_ID=54884 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AccountName */
    public static final String COLUMNNAME_AccountName = "AccountName";

	/** Set Account Name	  */
	public void setAccountName (String AccountName);

	/** Get Account Name	  */
	public String getAccountName();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_NotificationQueue_ID */
    public static final String COLUMNNAME_AD_NotificationQueue_ID = "AD_NotificationQueue_ID";

	/** Set Notification Queue.
	  * Notification Queue used for manage all system notification
	  */
	public void setAD_NotificationQueue_ID (int AD_NotificationQueue_ID);

	/** Get Notification Queue.
	  * Notification Queue used for manage all system notification
	  */
	public int getAD_NotificationQueue_ID();

	public org.adempiere.core.domains.models.I_AD_NotificationQueue getAD_NotificationQueue() throws RuntimeException;

    /** Column name AD_NotificationRecipient_ID */
    public static final String COLUMNNAME_AD_NotificationRecipient_ID = "AD_NotificationRecipient_ID";

	/** Set Notification Recipient.
	  * Notification Recipient allows save a contact list for send notification
	  */
	public void setAD_NotificationRecipient_ID (int AD_NotificationRecipient_ID);

	/** Get Notification Recipient.
	  * Notification Recipient allows save a contact list for send notification
	  */
	public int getAD_NotificationRecipient_ID();

	public org.adempiere.core.domains.models.I_AD_NotificationRecipient getAD_NotificationRecipient() throws RuntimeException;

    /** Column name AD_NotificationUpdates_ID */
    public static final String COLUMNNAME_AD_NotificationUpdates_ID = "AD_NotificationUpdates_ID";

	/** Set Notification Updates.
	  * Notification Updates
	  */
	public void setAD_NotificationUpdates_ID (int AD_NotificationUpdates_ID);

	/** Get Notification Updates.
	  * Notification Updates
	  */
	public int getAD_NotificationUpdates_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name GroupAccountID */
    public static final String COLUMNNAME_GroupAccountID = "GroupAccountID";

	/** Set Group Account ID.
	  * Account ID of group
	  */
	public void setGroupAccountID (String GroupAccountID);

	/** Get Group Account ID.
	  * Account ID of group
	  */
	public String getGroupAccountID();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name NotificationResponseCode */
    public static final String COLUMNNAME_NotificationResponseCode = "NotificationResponseCode";

	/** Set Response Code.
	  * Response Code for a Notification
	  */
	public void setNotificationResponseCode (String NotificationResponseCode);

	/** Get Response Code.
	  * Response Code for a Notification
	  */
	public String getNotificationResponseCode();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Description	  */
	public void setText (String Text);

	/** Get Description	  */
	public String getText();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UserAccountID */
    public static final String COLUMNNAME_UserAccountID = "UserAccountID";

	/** Set Account ID	  */
	public void setUserAccountID (String UserAccountID);

	/** Get Account ID	  */
	public String getUserAccountID();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();
}
