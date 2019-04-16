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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for R_MailText
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_R_MailText extends PO implements I_R_MailText, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190328L;

    /** Standard Constructor */
    public X_R_MailText (Properties ctx, int R_MailText_ID, String trxName)
    {
      super (ctx, R_MailText_ID, trxName);
      /** if (R_MailText_ID == 0)
        {
			setIsHtml (false);
			setMailText (null);
			setName (null);
			setR_MailText_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_MailText (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_R_MailText[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** EventType AD_Reference_ID=54119 */
	public static final int EVENTTYPE_AD_Reference_ID=54119;
	/** End User: New Request Auto-Response = EUNAR */
	public static final String EVENTTYPE_EndUserNewRequestAuto_Response = "EUNAR";
	/** End User: New Automatic Response Message = EUNRM */
	public static final String EVENTTYPE_EndUserNewAutomaticResponseMessage = "EUNRM";
	/** End User: New Activity Notice = EUNAN */
	public static final String EVENTTYPE_EndUserNewActivityNotice = "EUNAN";
	/** End User: New Request Notice = EUNRN */
	public static final String EVENTTYPE_EndUserNewRequestNotice = "EUNRN";
	/** End User: New Automatic Response Request = EUNRR */
	public static final String EVENTTYPE_EndUserNewAutomaticResponseRequest = "EUNRR";
	/** End User: Limit Override Notice = EULON */
	public static final String EVENTTYPE_EndUserLimitOverrideNotice = "EULON";
	/** End User: Response Template = EURTR */
	public static final String EVENTTYPE_EndUserResponseTemplate = "EURTR";
	/** Sales Rep: Internal Activity Alert = SRIAA */
	public static final String EVENTTYPE_SalesRepInternalActivityAlert = "SRIAA";
	/** Sales Rep: New Message Notice = SRNMN */
	public static final String EVENTTYPE_SalesRepNewMessageNotice = "SRNMN";
	/** Sales Rep: New Request Notice = SRNRN */
	public static final String EVENTTYPE_SalesRepNewRequestNotice = "SRNRN";
	/** Sales Rep: Due Request Alert = SRLRA */
	public static final String EVENTTYPE_SalesRepDueRequestAlert = "SRLRA";
	/** Automatic Task: Request Assignment Notice = SRRAN */
	public static final String EVENTTYPE_AutomaticTaskRequestAssignmentNotice = "SRRAN";
	/** Sales Rep: Alert when Transferring a Request = SRATR */
	public static final String EVENTTYPE_SalesRepAlertWhenTransferringARequest = "SRATR";
	/** Automatic Task: Default Template = ATDNT */
	public static final String EVENTTYPE_AutomaticTaskDefaultTemplate = "ATDNT";
	/** Automatic Task: Expired Task Alert = ATETA */
	public static final String EVENTTYPE_AutomaticTaskExpiredTaskAlert = "ATETA";
	/** Automatic Task: New Activity Alert = ATNAA */
	public static final String EVENTTYPE_AutomaticTaskNewActivityAlert = "ATNAA";
	/** Automatic Task: New Activity Notice = ATNAN */
	public static final String EVENTTYPE_AutomaticTaskNewActivityNotice = "ATNAN";
	/** Automatic Task: New Task Notice = ATNTN */
	public static final String EVENTTYPE_AutomaticTaskNewTaskNotice = "ATNTN";
	/** Automatic Task: Task Assignment Notice = ATTAN */
	public static final String EVENTTYPE_AutomaticTaskTaskAssignmentNotice = "ATTAN";
	/** Automatic Task: Task Transfer Notice = ATTTN */
	public static final String EVENTTYPE_AutomaticTaskTaskTransferNotice = "ATTTN";
	/** Automatic Task: Inactivity Alert = ATIAR */
	public static final String EVENTTYPE_AutomaticTaskInactivityAlert = "ATIAR";
	/** Set Event Type.
		@param EventType 
		Type of Event
	  */
	public void setEventType (String EventType)
	{

		set_ValueNoCheck (COLUMNNAME_EventType, EventType);
	}

	/** Get Event Type.
		@return Type of Event
	  */
	public String getEventType () 
	{
		return (String)get_Value(COLUMNNAME_EventType);
	}

	/** Set HTML.
		@param IsHtml 
		Text has HTML tags
	  */
	public void setIsHtml (boolean IsHtml)
	{
		set_Value (COLUMNNAME_IsHtml, Boolean.valueOf(IsHtml));
	}

	/** Get HTML.
		@return Text has HTML tags
	  */
	public boolean isHtml () 
	{
		Object oo = get_Value(COLUMNNAME_IsHtml);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Subject.
		@param MailHeader 
		Mail Header (Subject)
	  */
	public void setMailHeader (String MailHeader)
	{
		set_Value (COLUMNNAME_MailHeader, MailHeader);
	}

	/** Get Subject.
		@return Mail Header (Subject)
	  */
	public String getMailHeader () 
	{
		return (String)get_Value(COLUMNNAME_MailHeader);
	}

	/** Set Mail Text.
		@param MailText 
		Text used for Mail message
	  */
	public void setMailText (String MailText)
	{
		set_Value (COLUMNNAME_MailText, MailText);
	}

	/** Get Mail Text.
		@return Text used for Mail message
	  */
	public String getMailText () 
	{
		return (String)get_Value(COLUMNNAME_MailText);
	}

	/** Set Mail Text 2.
		@param MailText2 
		Optional second text part used for Mail message
	  */
	public void setMailText2 (String MailText2)
	{
		set_Value (COLUMNNAME_MailText2, MailText2);
	}

	/** Get Mail Text 2.
		@return Optional second text part used for Mail message
	  */
	public String getMailText2 () 
	{
		return (String)get_Value(COLUMNNAME_MailText2);
	}

	/** Set Mail Text 3.
		@param MailText3 
		Optional third text part used for Mail message
	  */
	public void setMailText3 (String MailText3)
	{
		set_Value (COLUMNNAME_MailText3, MailText3);
	}

	/** Get Mail Text 3.
		@return Optional third text part used for Mail message
	  */
	public String getMailText3 () 
	{
		return (String)get_Value(COLUMNNAME_MailText3);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	public org.spin.model.I_R_MailTemplate getR_MailTemplate() throws RuntimeException
    {
		return (org.spin.model.I_R_MailTemplate)MTable.get(getCtx(), org.spin.model.I_R_MailTemplate.Table_Name)
			.getPO(getR_MailTemplate_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailTemplate_ID 
		Request Mail Template by Event
	  */
	public void setR_MailTemplate_ID (int R_MailTemplate_ID)
	{
		if (R_MailTemplate_ID < 1) 
			set_Value (COLUMNNAME_R_MailTemplate_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailTemplate_ID, Integer.valueOf(R_MailTemplate_ID));
	}

	/** Get Mail Template.
		@return Request Mail Template by Event
	  */
	public int getR_MailTemplate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailTemplate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_MailText_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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