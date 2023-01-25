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

/** Generated Model for R_NoticeTemplateEvent
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_R_NoticeTemplateEvent extends PO implements I_R_NoticeTemplateEvent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_R_NoticeTemplateEvent (Properties ctx, int R_NoticeTemplateEvent_ID, String trxName)
    {
      super (ctx, R_NoticeTemplateEvent_ID, trxName);
      /** if (R_NoticeTemplateEvent_ID == 0)
        {
			setEventType (null);
			setR_MailText_ID (0);
			setR_NoticeTemplate_ID (0);
			setR_NoticeTemplateEvent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_NoticeTemplateEvent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_NoticeTemplateEvent[")
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

	public org.adempiere.core.domains.models.I_R_MailText getR_MailText() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_R_MailText)MTable.get(getCtx(), org.adempiere.core.domains.models.I_R_MailText.Table_Name)
			.getPO(getR_MailText_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
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

	public org.adempiere.core.domains.models.I_R_NoticeTemplate getR_NoticeTemplate() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_R_NoticeTemplate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_R_NoticeTemplate.Table_Name)
			.getPO(getR_NoticeTemplate_ID(), get_TrxName());	}

	/** Set Notice Template.
		@param R_NoticeTemplate_ID 
		Notice Template by Event
	  */
	public void setR_NoticeTemplate_ID (int R_NoticeTemplate_ID)
	{
		if (R_NoticeTemplate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplate_ID, Integer.valueOf(R_NoticeTemplate_ID));
	}

	/** Get Notice Template.
		@return Notice Template by Event
	  */
	public int getR_NoticeTemplate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_NoticeTemplate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Notice Template by Event.
		@param R_NoticeTemplateEvent_ID Notice Template by Event	  */
	public void setR_NoticeTemplateEvent_ID (int R_NoticeTemplateEvent_ID)
	{
		if (R_NoticeTemplateEvent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplateEvent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplateEvent_ID, Integer.valueOf(R_NoticeTemplateEvent_ID));
	}

	/** Get Notice Template by Event.
		@return Notice Template by Event	  */
	public int getR_NoticeTemplateEvent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_NoticeTemplateEvent_ID);
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