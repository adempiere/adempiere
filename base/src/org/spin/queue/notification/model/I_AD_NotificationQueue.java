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
package org.spin.queue.notification.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_NotificationQueue
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_AD_NotificationQueue 
{

    /** TableName=AD_NotificationQueue */
    public static final String Table_Name = "AD_NotificationQueue";

    /** AD_Table_ID=54845 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_AppRegistration_ID */
    public static final String COLUMNNAME_AD_AppRegistration_ID = "AD_AppRegistration_ID";

	/** Set Application Registration.
	  * External Application Registration
	  */
	public void setAD_AppRegistration_ID (int AD_AppRegistration_ID);

	/** Get Application Registration.
	  * External Application Registration
	  */
	public int getAD_AppRegistration_ID();

	public org.spin.model.I_AD_AppRegistration getAD_AppRegistration() throws RuntimeException;

    /** Column name AD_AppSupport_ID */
    public static final String COLUMNNAME_AD_AppSupport_ID = "AD_AppSupport_ID";

	/** Set App Support.
	  * App Support for External Connection
	  */
	public void setAD_AppSupport_ID (int AD_AppSupport_ID);

	/** Get App Support.
	  * App Support for External Connection
	  */
	public int getAD_AppSupport_ID();

	public org.spin.model.I_AD_AppSupport getAD_AppSupport() throws RuntimeException;

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

    /** Column name AD_Queue_ID */
    public static final String COLUMNNAME_AD_Queue_ID = "AD_Queue_ID";

	/** Set System Queue	  */
	public void setAD_Queue_ID (int AD_Queue_ID);

	/** Get System Queue	  */
	public int getAD_Queue_ID();

	public org.spin.queue.model.I_AD_Queue getAD_Queue() throws RuntimeException;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name ApplicationType */
    public static final String COLUMNNAME_ApplicationType = "ApplicationType";

	/** Set Application Type.
	  * Application Type, used for identify a Application Type like Message Queue
	  */
	public void setApplicationType (String ApplicationType);

	/** Get Application Type.
	  * Application Type, used for identify a Application Type like Message Queue
	  */
	public String getApplicationType();

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

    /** Column name MessageType */
    public static final String COLUMNNAME_MessageType = "MessageType";

	/** Set Message Type.
	  * Message Type for notification
	  */
	public void setMessageType (String MessageType);

	/** Get Message Type.
	  * Message Type for notification
	  */
	public String getMessageType();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ResponseHandler */
    public static final String COLUMNNAME_ResponseHandler = "ResponseHandler";

	/** Set Response Handler.
	  * Response Handler for a notification
	  */
	public void setResponseHandler (String ResponseHandler);

	/** Get Response Handler.
	  * Response Handler for a notification
	  */
	public String getResponseHandler();

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
