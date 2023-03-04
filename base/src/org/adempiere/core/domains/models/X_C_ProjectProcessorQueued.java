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

/** Generated Model for C_ProjectProcessorQueued
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_C_ProjectProcessorQueued extends PO implements I_C_ProjectProcessorQueued, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_C_ProjectProcessorQueued (Properties ctx, int C_ProjectProcessorQueued_ID, String trxName)
    {
      super (ctx, C_ProjectProcessorQueued_ID, trxName);
      /** if (C_ProjectProcessorQueued_ID == 0)
        {
			setC_ProjectProcessorQueued_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectProcessorQueued (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectProcessorQueued[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.adempiere.core.domains.models.I_AD_UserMail getAD_UserMail() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_UserMail)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_UserMail.Table_Name)
			.getPO(getAD_UserMail_ID(), get_TrxName());	}

	/** Set User Mail.
		@param AD_UserMail_ID 
		Mail sent to the user
	  */
	public void setAD_UserMail_ID (int AD_UserMail_ID)
	{
		if (AD_UserMail_ID < 1) 
			set_Value (COLUMNNAME_AD_UserMail_ID, null);
		else 
			set_Value (COLUMNNAME_AD_UserMail_ID, Integer.valueOf(AD_UserMail_ID));
	}

	/** Get User Mail.
		@return Mail sent to the user
	  */
	public int getAD_UserMail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_UserMail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_ProjectProcessorLog getC_ProjectProcessorLog() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_ProjectProcessorLog)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_ProjectProcessorLog.Table_Name)
			.getPO(getC_ProjectProcessorLog_ID(), get_TrxName());	}

	/** Set Project Processor Log.
		@param C_ProjectProcessorLog_ID Project Processor Log	  */
	public void setC_ProjectProcessorLog_ID (int C_ProjectProcessorLog_ID)
	{
		if (C_ProjectProcessorLog_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectProcessorLog_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectProcessorLog_ID, Integer.valueOf(C_ProjectProcessorLog_ID));
	}

	/** Get Project Processor Log.
		@return Project Processor Log	  */
	public int getC_ProjectProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Processor Queued.
		@param C_ProjectProcessorQueued_ID Project Processor Queued	  */
	public void setC_ProjectProcessorQueued_ID (int C_ProjectProcessorQueued_ID)
	{
		if (C_ProjectProcessorQueued_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorQueued_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorQueued_ID, Integer.valueOf(C_ProjectProcessorQueued_ID));
	}

	/** Get Project Processor Queued.
		@return Project Processor Queued	  */
	public int getC_ProjectProcessorQueued_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessorQueued_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** NotificationType AD_Reference_ID=344 */
	public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
	/** EMail = E */
	public static final String NOTIFICATIONTYPE_EMail = "E";
	/** Notice = N */
	public static final String NOTIFICATIONTYPE_Notice = "N";
	/** None = X */
	public static final String NOTIFICATIONTYPE_None = "X";
	/** EMail+Notice = B */
	public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
	/** Social Media = S */
	public static final String NOTIFICATIONTYPE_SocialMedia = "S";
	/** Notice+Social Media = M */
	public static final String NOTIFICATIONTYPE_NoticePlusSocialMedia = "M";
	/** EMail+Social Media = F */
	public static final String NOTIFICATIONTYPE_EMailPlusSocialMedia = "F";
	/** All Possible Means = A */
	public static final String NOTIFICATIONTYPE_AllPossibleMeans = "A";
	/** Set Notification Type.
		@param NotificationType 
		Type of Notifications
	  */
	public void setNotificationType (String NotificationType)
	{

		set_Value (COLUMNNAME_NotificationType, NotificationType);
	}

	/** Get Notification Type.
		@return Type of Notifications
	  */
	public String getNotificationType () 
	{
		return (String)get_Value(COLUMNNAME_NotificationType);
	}

	/** Set Send EMail.
		@param SendEMail 
		Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail)
	{
		set_Value (COLUMNNAME_SendEMail, Boolean.valueOf(SendEMail));
	}

	/** Get Send EMail.
		@return Enable sending Document EMail
	  */
	public boolean isSendEMail () 
	{
		Object oo = get_Value(COLUMNNAME_SendEMail);
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