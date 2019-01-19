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
package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_ProjectProcessor
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_ProjectProcessor extends PO implements I_C_ProjectProcessor, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_ProjectProcessor (Properties ctx, int C_ProjectProcessor_ID, String trxName)
    {
      super (ctx, C_ProjectProcessor_ID, trxName);
      /** if (C_ProjectProcessor_ID == 0)
        {
			setC_ProjectProcessor_ID (0);
			setFrequency (0);
// 1
			setFrequencyType (null);
			setInactivityAlertDays (0);
// 0
			setKeepLogDays (0);
// 7
			setName (null);
			setOverdueAlertDays (0);
// 0
			setOverdueAssignDays (0);
// 0
			setRemindDays (0);
// 0
			setSupervisor_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectProcessor (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectProcessor[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Project Processor.
		@param C_ProjectProcessor_ID 
		Processor for Project
	  */
	public void setC_ProjectProcessor_ID (int C_ProjectProcessor_ID)
	{
		if (C_ProjectProcessor_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessor_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessor_ID, Integer.valueOf(C_ProjectProcessor_ID));
	}

	/** Get Project Processor.
		@return Processor for Project
	  */
	public int getC_ProjectProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_C_ProjectTaskCategory getC_ProjectTaskCategory() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectTaskCategory)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectTaskCategory.Table_Name)
			.getPO(getC_ProjectTaskCategory_ID(), get_TrxName());	}

	/** Set Project Task Category.
		@param C_ProjectTaskCategory_ID 
		Set Category for project task
	  */
	public void setC_ProjectTaskCategory_ID (int C_ProjectTaskCategory_ID)
	{
		if (C_ProjectTaskCategory_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectTaskCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectTaskCategory_ID, Integer.valueOf(C_ProjectTaskCategory_ID));
	}

	/** Get Project Task Category.
		@return Set Category for project task
	  */
	public int getC_ProjectTaskCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectTaskCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ProjectType getC_ProjectType() throws RuntimeException
    {
		return (org.compiere.model.I_C_ProjectType)MTable.get(getCtx(), org.compiere.model.I_C_ProjectType.Table_Name)
			.getPO(getC_ProjectType_ID(), get_TrxName());	}

	/** Set Project Type.
		@param C_ProjectType_ID 
		Type of the project
	  */
	public void setC_ProjectType_ID (int C_ProjectType_ID)
	{
		if (C_ProjectType_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectType_ID, Integer.valueOf(C_ProjectType_ID));
	}

	/** Get Project Type.
		@return Type of the project
	  */
	public int getC_ProjectType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date last run.
		@param DateLastRun 
		Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		set_Value (COLUMNNAME_DateLastRun, DateLastRun);
	}

	/** Get Date last run.
		@return Date the process was last run.
	  */
	public Timestamp getDateLastRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastRun);
	}

	/** Set Date next run.
		@param DateNextRun 
		Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun)
	{
		set_Value (COLUMNNAME_DateNextRun, DateNextRun);
	}

	/** Get Date next run.
		@return Date the process will run next
	  */
	public Timestamp getDateNextRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNextRun);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (int Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Integer.valueOf(Frequency));
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public int getFrequency () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Frequency);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FrequencyType AD_Reference_ID=221 */
	public static final int FREQUENCYTYPE_AD_Reference_ID=221;
	/** Minute = M */
	public static final String FREQUENCYTYPE_Minute = "M";
	/** Hour = H */
	public static final String FREQUENCYTYPE_Hour = "H";
	/** Day = D */
	public static final String FREQUENCYTYPE_Day = "D";
	/** Set Frequency Type.
		@param FrequencyType 
		Frequency of event
	  */
	public void setFrequencyType (String FrequencyType)
	{

		set_Value (COLUMNNAME_FrequencyType, FrequencyType);
	}

	/** Get Frequency Type.
		@return Frequency of event
	  */
	public String getFrequencyType () 
	{
		return (String)get_Value(COLUMNNAME_FrequencyType);
	}

	/** Set Inactivity Alert Days.
		@param InactivityAlertDays 
		Send Alert when there is no activity after days (0= no alert)
	  */
	public void setInactivityAlertDays (int InactivityAlertDays)
	{
		set_Value (COLUMNNAME_InactivityAlertDays, Integer.valueOf(InactivityAlertDays));
	}

	/** Get Inactivity Alert Days.
		@return Send Alert when there is no activity after days (0= no alert)
	  */
	public int getInactivityAlertDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InactivityAlertDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days to keep Log.
		@param KeepLogDays 
		Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays)
	{
		set_Value (COLUMNNAME_KeepLogDays, Integer.valueOf(KeepLogDays));
	}

	/** Get Days to keep Log.
		@return Number of days to keep the log entries
	  */
	public int getKeepLogDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_KeepLogDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Alert after Days Due.
		@param OverdueAlertDays 
		Send email alert after number of days due (0=no alerts)
	  */
	public void setOverdueAlertDays (int OverdueAlertDays)
	{
		set_Value (COLUMNNAME_OverdueAlertDays, Integer.valueOf(OverdueAlertDays));
	}

	/** Get Alert after Days Due.
		@return Send email alert after number of days due (0=no alerts)
	  */
	public int getOverdueAlertDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OverdueAlertDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Escalate after Days Due.
		@param OverdueAssignDays 
		Escalation to superior after number of due days (0 = no)
	  */
	public void setOverdueAssignDays (int OverdueAssignDays)
	{
		set_Value (COLUMNNAME_OverdueAssignDays, Integer.valueOf(OverdueAssignDays));
	}

	/** Get Escalate after Days Due.
		@return Escalation to superior after number of due days (0 = no)
	  */
	public int getOverdueAssignDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OverdueAssignDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Reminder Days.
		@param RemindDays 
		Days between sending Reminder Emails for a due or inactive Document
	  */
	public void setRemindDays (int RemindDays)
	{
		set_Value (COLUMNNAME_RemindDays, Integer.valueOf(RemindDays));
	}

	/** Get Reminder Days.
		@return Days between sending Reminder Emails for a due or inactive Document
	  */
	public int getRemindDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RemindDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSupervisor_ID(), get_TrxName());	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_Value (COLUMNNAME_Supervisor_ID, null);
		else 
			set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
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