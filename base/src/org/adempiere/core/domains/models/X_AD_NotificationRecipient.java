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

/** Generated Model for AD_NotificationRecipient
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_NotificationRecipient extends PO implements I_AD_NotificationRecipient, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_NotificationRecipient (Properties ctx, int AD_NotificationRecipient_ID, String trxName)
    {
      super (ctx, AD_NotificationRecipient_ID, trxName);
      /** if (AD_NotificationRecipient_ID == 0)
        {
			setAD_NotificationQueue_ID (0);
			setAD_NotificationRecipient_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_NotificationRecipient (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_NotificationRecipient[")
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getAccountName());
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
			set_ValueNoCheck (COLUMNNAME_AD_NotificationQueue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_NotificationQueue_ID, Integer.valueOf(AD_NotificationQueue_ID));
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

	/** Set Notification Recipient.
		@param AD_NotificationRecipient_ID 
		Notification Recipient allows save a contact list for send notification
	  */
	public void setAD_NotificationRecipient_ID (int AD_NotificationRecipient_ID)
	{
		if (AD_NotificationRecipient_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_NotificationRecipient_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_NotificationRecipient_ID, Integer.valueOf(AD_NotificationRecipient_ID));
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

	public org.adempiere.core.domains.models.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_User)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Error Msg.
		@param ErrorMsg Error Msg	  */
	public void setErrorMsg (String ErrorMsg)
	{
		set_Value (COLUMNNAME_ErrorMsg, ErrorMsg);
	}

	/** Get Error Msg.
		@return Error Msg	  */
	public String getErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_ErrorMsg);
	}

	/** MessageType AD_Reference_ID=54280 */
	public static final int MESSAGETYPE_AD_Reference_ID=54280;
	/** Standard = STD */
	public static final String MESSAGETYPE_Standard = "STD";
	/** Confirmation = CFM */
	public static final String MESSAGETYPE_Confirmation = "CFM";
	/** Survey = SRV */
	public static final String MESSAGETYPE_Survey = "SRV";
	/** Promotional = PRM */
	public static final String MESSAGETYPE_Promotional = "PRM";
	/** Workflow Approval = WAP */
	public static final String MESSAGETYPE_WorkflowApproval = "WAP";
	/** Set Message Type.
		@param MessageType 
		Message Type for notification
	  */
	public void setMessageType (String MessageType)
	{

		set_Value (COLUMNNAME_MessageType, MessageType);
	}

	/** Get Message Type.
		@return Message Type for notification
	  */
	public String getMessageType () 
	{
		return (String)get_Value(COLUMNNAME_MessageType);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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