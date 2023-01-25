/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_NotificationUpdates
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_NotificationUpdates extends PO implements I_AD_NotificationUpdates, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_NotificationUpdates (Properties ctx, int AD_NotificationUpdates_ID, String trxName)
    {
      super (ctx, AD_NotificationUpdates_ID, trxName);
      /** if (AD_NotificationUpdates_ID == 0)
        {
			setAccountName (null);
			setAD_NotificationQueue_ID (0);
			setAD_NotificationUpdates_ID (0);
			setNotificationResponseCode (null);
        } */
    }

    /** Load Constructor */
    public X_AD_NotificationUpdates (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_NotificationUpdates[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account Name.
		@param AccountName Account Name	  */
	public void setAccountName (String AccountName)
	{
		set_Value (COLUMNNAME_AccountName, AccountName);
	}

	/** Get Account Name.
		@return Account Name	  */
	public String getAccountName () 
	{
		return (String)get_Value(COLUMNNAME_AccountName);
	}

	public org.adempiere.core.domains.models.I_AD_NotificationQueue getAD_NotificationQueue() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_NotificationQueue)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_NotificationQueue.Table_Name)
			.getPO(getAD_NotificationQueue_ID(), get_TrxName());	}

	/** Set Notification Queue.
		@param AD_NotificationQueue_ID 
		Notification Queue used for manage all system notification
	  */
	public void setAD_NotificationQueue_ID (int AD_NotificationQueue_ID)
	{
		if (AD_NotificationQueue_ID < 1) 
			set_Value (COLUMNNAME_AD_NotificationQueue_ID, null);
		else 
			set_Value (COLUMNNAME_AD_NotificationQueue_ID, Integer.valueOf(AD_NotificationQueue_ID));
	}

	/** Get Notification Queue.
		@return Notification Queue used for manage all system notification
	  */
	public int getAD_NotificationQueue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_NotificationQueue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_NotificationQueue_ID()));
    }

	public org.adempiere.core.domains.models.I_AD_NotificationRecipient getAD_NotificationRecipient() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_NotificationRecipient)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_NotificationRecipient.Table_Name)
			.getPO(getAD_NotificationRecipient_ID(), get_TrxName());	}

	/** Set Notification Recipient.
		@param AD_NotificationRecipient_ID 
		Notification Recipient allows save a contact list for send notification
	  */
	public void setAD_NotificationRecipient_ID (int AD_NotificationRecipient_ID)
	{
		if (AD_NotificationRecipient_ID < 1) 
			set_Value (COLUMNNAME_AD_NotificationRecipient_ID, null);
		else 
			set_Value (COLUMNNAME_AD_NotificationRecipient_ID, Integer.valueOf(AD_NotificationRecipient_ID));
	}

	/** Get Notification Recipient.
		@return Notification Recipient allows save a contact list for send notification
	  */
	public int getAD_NotificationRecipient_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_NotificationRecipient_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Notification Updates.
		@param AD_NotificationUpdates_ID 
		Notification Updates
	  */
	public void setAD_NotificationUpdates_ID (int AD_NotificationUpdates_ID)
	{
		if (AD_NotificationUpdates_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_NotificationUpdates_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_NotificationUpdates_ID, Integer.valueOf(AD_NotificationUpdates_ID));
	}

	/** Get Notification Updates.
		@return Notification Updates
	  */
	public int getAD_NotificationUpdates_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_NotificationUpdates_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Group Account ID.
		@param GroupAccountID 
		Account ID of group
	  */
	public void setGroupAccountID (String GroupAccountID)
	{
		set_Value (COLUMNNAME_GroupAccountID, GroupAccountID);
	}

	/** Get Group Account ID.
		@return Account ID of group
	  */
	public String getGroupAccountID () 
	{
		return (String)get_Value(COLUMNNAME_GroupAccountID);
	}

	/** Set Response Code.
		@param NotificationResponseCode 
		Response Code for a Notification
	  */
	public void setNotificationResponseCode (String NotificationResponseCode)
	{
		set_Value (COLUMNNAME_NotificationResponseCode, NotificationResponseCode);
	}

	/** Get Response Code.
		@return Response Code for a Notification
	  */
	public String getNotificationResponseCode () 
	{
		return (String)get_Value(COLUMNNAME_NotificationResponseCode);
	}

	/** Set Text Message.
		@param Text 
		This field allows define a text message with a text very long
	  */
	public void setText (String Text)
	{
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Text Message.
		@return This field allows define a text message with a text very long
	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
	}

	/** Set Account ID.
		@param UserAccountID Account ID	  */
	public void setUserAccountID (String UserAccountID)
	{
		set_Value (COLUMNNAME_UserAccountID, UserAccountID);
	}

	/** Get Account ID.
		@return Account ID	  */
	public String getUserAccountID () 
	{
		return (String)get_Value(COLUMNNAME_UserAccountID);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}