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

/** Generated Model for R_StandardRequest
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_R_StandardRequest extends PO implements I_R_StandardRequest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_R_StandardRequest (Properties ctx, int R_StandardRequest_ID, String trxName)
    {
      super (ctx, R_StandardRequest_ID, trxName);
      /** if (R_StandardRequest_ID == 0)
        {
			setConfidentialTypeEntry (null);
// A
			setDueType (null);
// 7
			setPriority (null);
// 5
			setR_Category_ID (0);
			setR_RequestType_ID (0);
			setR_StandardRequest_ID (0);
			setSummary (null);
        } */
    }

    /** Load Constructor */
    public X_R_StandardRequest (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_R_StandardRequest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Role)MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_Value (COLUMNNAME_AD_Role_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ConfidentialTypeEntry AD_Reference_ID=340 */
	public static final int CONFIDENTIALTYPEENTRY_AD_Reference_ID=340;
	/** Public Information = A */
	public static final String CONFIDENTIALTYPEENTRY_PublicInformation = "A";
	/** Partner Confidential = C */
	public static final String CONFIDENTIALTYPEENTRY_PartnerConfidential = "C";
	/** Internal = I */
	public static final String CONFIDENTIALTYPEENTRY_Internal = "I";
	/** Private Information = P */
	public static final String CONFIDENTIALTYPEENTRY_PrivateInformation = "P";
	/** Set Entry Confidentiality.
		@param ConfidentialTypeEntry 
		Confidentiality of the individual entry
	  */
	public void setConfidentialTypeEntry (String ConfidentialTypeEntry)
	{

		set_Value (COLUMNNAME_ConfidentialTypeEntry, ConfidentialTypeEntry);
	}

	/** Get Entry Confidentiality.
		@return Confidentiality of the individual entry
	  */
	public String getConfidentialTypeEntry () 
	{
		return (String)get_Value(COLUMNNAME_ConfidentialTypeEntry);
	}

	/** DueType AD_Reference_ID=222 */
	public static final int DUETYPE_AD_Reference_ID=222;
	/** Overdue = 3 */
	public static final String DUETYPE_Overdue = "3";
	/** Due = 5 */
	public static final String DUETYPE_Due = "5";
	/** Scheduled = 7 */
	public static final String DUETYPE_Scheduled = "7";
	/** Set Due type.
		@param DueType 
		Status of the next action for this Request
	  */
	public void setDueType (String DueType)
	{

		set_Value (COLUMNNAME_DueType, DueType);
	}

	/** Get Due type.
		@return Status of the next action for this Request
	  */
	public String getDueType () 
	{
		return (String)get_Value(COLUMNNAME_DueType);
	}

	/** Set Duration.
		@param Duration 
		Normal Duration in Duration Unit
	  */
	public void setDuration (int Duration)
	{
		set_Value (COLUMNNAME_Duration, Integer.valueOf(Duration));
	}

	/** Get Duration.
		@return Normal Duration in Duration Unit
	  */
	public int getDuration () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duration);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DurationUnit AD_Reference_ID=299 */
	public static final int DURATIONUNIT_AD_Reference_ID=299;
	/** Year = Y */
	public static final String DURATIONUNIT_Year = "Y";
	/** Month = M */
	public static final String DURATIONUNIT_Month = "M";
	/** Day = D */
	public static final String DURATIONUNIT_Day = "D";
	/** hour = h */
	public static final String DURATIONUNIT_Hour = "h";
	/** minute = m */
	public static final String DURATIONUNIT_Minute = "m";
	/** second = s */
	public static final String DURATIONUNIT_Second = "s";
	/** Set Duration Unit.
		@param DurationUnit 
		Unit of Duration
	  */
	public void setDurationUnit (String DurationUnit)
	{

		set_Value (COLUMNNAME_DurationUnit, DurationUnit);
	}

	/** Get Duration Unit.
		@return Unit of Duration
	  */
	public String getDurationUnit () 
	{
		return (String)get_Value(COLUMNNAME_DurationUnit);
	}

	/** Priority AD_Reference_ID=154 */
	public static final int PRIORITY_AD_Reference_ID=154;
	/** High = 3 */
	public static final String PRIORITY_High = "3";
	/** Medium = 5 */
	public static final String PRIORITY_Medium = "5";
	/** Low = 7 */
	public static final String PRIORITY_Low = "7";
	/** Urgent = 1 */
	public static final String PRIORITY_Urgent = "1";
	/** Minor = 9 */
	public static final String PRIORITY_Minor = "9";
	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority)
	{

		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority () 
	{
		return (String)get_Value(COLUMNNAME_Priority);
	}

	public org.compiere.model.I_R_Category getR_Category() throws RuntimeException
    {
		return (org.compiere.model.I_R_Category)MTable.get(getCtx(), org.compiere.model.I_R_Category.Table_Name)
			.getPO(getR_Category_ID(), get_TrxName());	}

	/** Set Category.
		@param R_Category_ID 
		Request Category
	  */
	public void setR_Category_ID (int R_Category_ID)
	{
		if (R_Category_ID < 1) 
			set_Value (COLUMNNAME_R_Category_ID, null);
		else 
			set_Value (COLUMNNAME_R_Category_ID, Integer.valueOf(R_Category_ID));
	}

	/** Get Category.
		@return Request Category
	  */
	public int getR_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_Group getR_Group() throws RuntimeException
    {
		return (org.compiere.model.I_R_Group)MTable.get(getCtx(), org.compiere.model.I_R_Group.Table_Name)
			.getPO(getR_Group_ID(), get_TrxName());	}

	/** Set Group.
		@param R_Group_ID 
		Request Group
	  */
	public void setR_Group_ID (int R_Group_ID)
	{
		if (R_Group_ID < 1) 
			set_Value (COLUMNNAME_R_Group_ID, null);
		else 
			set_Value (COLUMNNAME_R_Group_ID, Integer.valueOf(R_Group_ID));
	}

	/** Get Group.
		@return Request Group
	  */
	public int getR_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_StandardRequest getR_RequestRelated() throws RuntimeException
    {
		return (org.compiere.model.I_R_StandardRequest)MTable.get(getCtx(), org.compiere.model.I_R_StandardRequest.Table_Name)
			.getPO(getR_RequestRelated_ID(), get_TrxName());	}

	/** Set Related Request.
		@param R_RequestRelated_ID 
		Related Request (Master Issue, ..)
	  */
	public void setR_RequestRelated_ID (int R_RequestRelated_ID)
	{
		if (R_RequestRelated_ID < 1) 
			set_Value (COLUMNNAME_R_RequestRelated_ID, null);
		else 
			set_Value (COLUMNNAME_R_RequestRelated_ID, Integer.valueOf(R_RequestRelated_ID));
	}

	/** Get Related Request.
		@return Related Request (Master Issue, ..)
	  */
	public int getR_RequestRelated_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestRelated_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_RequestType getR_RequestType() throws RuntimeException
    {
		return (org.compiere.model.I_R_RequestType)MTable.get(getCtx(), org.compiere.model.I_R_RequestType.Table_Name)
			.getPO(getR_RequestType_ID(), get_TrxName());	}

	/** Set Request Type.
		@param R_RequestType_ID 
		Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID)
	{
		if (R_RequestType_ID < 1) 
			set_Value (COLUMNNAME_R_RequestType_ID, null);
		else 
			set_Value (COLUMNNAME_R_RequestType_ID, Integer.valueOf(R_RequestType_ID));
	}

	/** Get Request Type.
		@return Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Standard Request Type.
		@param R_StandardRequestType_ID 
		Standard Request Type
	  */
	public void setR_StandardRequestType_ID (int R_StandardRequestType_ID)
	{
		if (R_StandardRequestType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_StandardRequestType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_StandardRequestType_ID, Integer.valueOf(R_StandardRequestType_ID));
	}

	/** Get Standard Request Type.
		@return Standard Request Type
	  */
	public int getR_StandardRequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_StandardRequestType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Standard Request.
		@param R_StandardRequest_ID 
		Standard Request
	  */
	public void setR_StandardRequest_ID (int R_StandardRequest_ID)
	{
		if (R_StandardRequest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_StandardRequest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_StandardRequest_ID, Integer.valueOf(R_StandardRequest_ID));
	}

	/** Get Standard Request.
		@return Standard Request
	  */
	public int getR_StandardRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_StandardRequest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_Status getR_Status() throws RuntimeException
    {
		return (org.compiere.model.I_R_Status)MTable.get(getCtx(), org.compiere.model.I_R_Status.Table_Name)
			.getPO(getR_Status_ID(), get_TrxName());	}

	/** Set Status.
		@param R_Status_ID 
		Request Status
	  */
	public void setR_Status_ID (int R_Status_ID)
	{
		if (R_Status_ID < 1) 
			set_Value (COLUMNNAME_R_Status_ID, null);
		else 
			set_Value (COLUMNNAME_R_Status_ID, Integer.valueOf(R_Status_ID));
	}

	/** Get Status.
		@return Request Status
	  */
	public int getR_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Status_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
    }

	/** Set Summary.
		@param Summary 
		Textual summary of this request
	  */
	public void setSummary (String Summary)
	{
		set_Value (COLUMNNAME_Summary, Summary);
	}

	/** Get Summary.
		@return Textual summary of this request
	  */
	public String getSummary () 
	{
		return (String)get_Value(COLUMNNAME_Summary);
	}

	/** TaskStatus AD_Reference_ID=366 */
	public static final int TASKSTATUS_AD_Reference_ID=366;
	/**  0% Not Started = 0 */
	public static final String TASKSTATUS_0NotStarted = "0";
	/** 100% Complete = D */
	public static final String TASKSTATUS_100Complete = "D";
	/**  20% Started = 2 */
	public static final String TASKSTATUS_20Started = "2";
	/**  80% Nearly Done = 8 */
	public static final String TASKSTATUS_80NearlyDone = "8";
	/**  40% Busy = 4 */
	public static final String TASKSTATUS_40Busy = "4";
	/**  60% Good Progress = 6 */
	public static final String TASKSTATUS_60GoodProgress = "6";
	/**  90% Finishing = 9 */
	public static final String TASKSTATUS_90Finishing = "9";
	/**  95% Almost Done = A */
	public static final String TASKSTATUS_95AlmostDone = "A";
	/**  99% Cleaning up = C */
	public static final String TASKSTATUS_99CleaningUp = "C";
	/** Set Task Status.
		@param TaskStatus 
		Status of the Task
	  */
	public void setTaskStatus (String TaskStatus)
	{

		set_Value (COLUMNNAME_TaskStatus, TaskStatus);
	}

	/** Get Task Status.
		@return Status of the Task
	  */
	public String getTaskStatus () 
	{
		return (String)get_Value(COLUMNNAME_TaskStatus);
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