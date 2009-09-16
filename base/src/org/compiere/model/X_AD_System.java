/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_System
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a - $Id$ */
public class X_AD_System extends PO implements I_AD_System, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20090915L;

    /** Standard Constructor */
    public X_AD_System (Properties ctx, int AD_System_ID, String trxName)
    {
      super (ctx, AD_System_ID, trxName);
      /** if (AD_System_ID == 0)
        {
			setAD_System_ID (0);
// 0
			setInfo (null);
			setIsAllowStatistics (false);
			setIsAutoErrorReport (true);
// Y
			setIsFailOnBuildDiffer (false);
// N
			setIsFailOnMissingModelValidator (true);
// Y
			setName (null);
			setPassword (null);
			setReplicationType (null);
// L
			setSystemStatus (null);
// E
			setUserName (null);
			setVersion (null);
        } */
    }

    /** Load Constructor */
    public X_AD_System (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_System[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set System.
		@param AD_System_ID 
		System Definition
	  */
	public void setAD_System_ID (int AD_System_ID)
	{
		if (AD_System_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_System_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_System_ID, Integer.valueOf(AD_System_ID));
	}

	/** Get System.
		@return System Definition
	  */
	public int getAD_System_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_System_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Custom Prefix.
		@param CustomPrefix 
		Prefix for Custom entities
	  */
	public void setCustomPrefix (String CustomPrefix)
	{
		set_Value (COLUMNNAME_CustomPrefix, CustomPrefix);
	}

	/** Get Custom Prefix.
		@return Prefix for Custom entities
	  */
	public String getCustomPrefix () 
	{
		return (String)get_Value(COLUMNNAME_CustomPrefix);
	}

	/** Set DB Address.
		@param DBAddress 
		JDBC URL of the database server
	  */
	public void setDBAddress (String DBAddress)
	{
		set_Value (COLUMNNAME_DBAddress, DBAddress);
	}

	/** Get DB Address.
		@return JDBC URL of the database server
	  */
	public String getDBAddress () 
	{
		return (String)get_Value(COLUMNNAME_DBAddress);
	}

	/** Set Database Name.
		@param DBInstance 
		Database Name
	  */
	public void setDBInstance (String DBInstance)
	{
		set_Value (COLUMNNAME_DBInstance, DBInstance);
	}

	/** Get Database Name.
		@return Database Name
	  */
	public String getDBInstance () 
	{
		return (String)get_Value(COLUMNNAME_DBInstance);
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

	/** Set Encryption Class.
		@param EncryptionKey 
		Encryption Class used for securing data content
	  */
	public void setEncryptionKey (String EncryptionKey)
	{
		set_ValueNoCheck (COLUMNNAME_EncryptionKey, EncryptionKey);
	}

	/** Get Encryption Class.
		@return Encryption Class used for securing data content
	  */
	public String getEncryptionKey () 
	{
		return (String)get_Value(COLUMNNAME_EncryptionKey);
	}

	/** Set ID Range End.
		@param IDRangeEnd 
		End if the ID Range used
	  */
	public void setIDRangeEnd (BigDecimal IDRangeEnd)
	{
		set_Value (COLUMNNAME_IDRangeEnd, IDRangeEnd);
	}

	/** Get ID Range End.
		@return End if the ID Range used
	  */
	public BigDecimal getIDRangeEnd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IDRangeEnd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ID Range Start.
		@param IDRangeStart 
		Start of the ID Range used
	  */
	public void setIDRangeStart (BigDecimal IDRangeStart)
	{
		set_Value (COLUMNNAME_IDRangeStart, IDRangeStart);
	}

	/** Get ID Range Start.
		@return Start of the ID Range used
	  */
	public BigDecimal getIDRangeStart () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IDRangeStart);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Info.
		@param Info 
		Information
	  */
	public void setInfo (String Info)
	{
		set_ValueNoCheck (COLUMNNAME_Info, Info);
	}

	/** Get Info.
		@return Information
	  */
	public String getInfo () 
	{
		return (String)get_Value(COLUMNNAME_Info);
	}

	/** Set Maintain Statistics.
		@param IsAllowStatistics 
		Maintain general statistics
	  */
	public void setIsAllowStatistics (boolean IsAllowStatistics)
	{
		set_Value (COLUMNNAME_IsAllowStatistics, Boolean.valueOf(IsAllowStatistics));
	}

	/** Get Maintain Statistics.
		@return Maintain general statistics
	  */
	public boolean isAllowStatistics () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowStatistics);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Error Reporting.
		@param IsAutoErrorReport 
		Automatically report Errors
	  */
	public void setIsAutoErrorReport (boolean IsAutoErrorReport)
	{
		set_Value (COLUMNNAME_IsAutoErrorReport, Boolean.valueOf(IsAutoErrorReport));
	}

	/** Get Error Reporting.
		@return Automatically report Errors
	  */
	public boolean isAutoErrorReport () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutoErrorReport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Fail if Build Differ.
		@param IsFailOnBuildDiffer Fail if Build Differ	  */
	public void setIsFailOnBuildDiffer (boolean IsFailOnBuildDiffer)
	{
		set_Value (COLUMNNAME_IsFailOnBuildDiffer, Boolean.valueOf(IsFailOnBuildDiffer));
	}

	/** Get Fail if Build Differ.
		@return Fail if Build Differ	  */
	public boolean isFailOnBuildDiffer () 
	{
		Object oo = get_Value(COLUMNNAME_IsFailOnBuildDiffer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Fail on Missing Model Validator.
		@param IsFailOnMissingModelValidator Fail on Missing Model Validator	  */
	public void setIsFailOnMissingModelValidator (boolean IsFailOnMissingModelValidator)
	{
		set_Value (COLUMNNAME_IsFailOnMissingModelValidator, Boolean.valueOf(IsFailOnMissingModelValidator));
	}

	/** Get Fail on Missing Model Validator.
		@return Fail on Missing Model Validator	  */
	public boolean isFailOnMissingModelValidator () 
	{
		Object oo = get_Value(COLUMNNAME_IsFailOnMissingModelValidator);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Just Migrated.
		@param IsJustMigrated 
		Value set by Migration for post-Migation tasks.
	  */
	public void setIsJustMigrated (boolean IsJustMigrated)
	{
		set_Value (COLUMNNAME_IsJustMigrated, Boolean.valueOf(IsJustMigrated));
	}

	/** Get Just Migrated.
		@return Value set by Migration for post-Migation tasks.
	  */
	public boolean isJustMigrated () 
	{
		Object oo = get_Value(COLUMNNAME_IsJustMigrated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last Build Info.
		@param LastBuildInfo Last Build Info	  */
	public void setLastBuildInfo (String LastBuildInfo)
	{
		set_Value (COLUMNNAME_LastBuildInfo, LastBuildInfo);
	}

	/** Get Last Build Info.
		@return Last Build Info	  */
	public String getLastBuildInfo () 
	{
		return (String)get_Value(COLUMNNAME_LastBuildInfo);
	}

	/** Set LDAP Domain.
		@param LDAPDomain 
		Directory service domain name - e.g. adempiere.org
	  */
	public void setLDAPDomain (String LDAPDomain)
	{
		set_Value (COLUMNNAME_LDAPDomain, LDAPDomain);
	}

	/** Get LDAP Domain.
		@return Directory service domain name - e.g. adempiere.org
	  */
	public String getLDAPDomain () 
	{
		return (String)get_Value(COLUMNNAME_LDAPDomain);
	}

	/** Set LDAP URL.
		@param LDAPHost 
		Connection String to LDAP server starting with ldap://
	  */
	public void setLDAPHost (String LDAPHost)
	{
		set_Value (COLUMNNAME_LDAPHost, LDAPHost);
	}

	/** Get LDAP URL.
		@return Connection String to LDAP server starting with ldap://
	  */
	public String getLDAPHost () 
	{
		return (String)get_Value(COLUMNNAME_LDAPHost);
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

	/** Set Processors.
		@param NoProcessors 
		Number of Database Processors
	  */
	public void setNoProcessors (int NoProcessors)
	{
		set_Value (COLUMNNAME_NoProcessors, Integer.valueOf(NoProcessors));
	}

	/** Get Processors.
		@return Number of Database Processors
	  */
	public int getNoProcessors () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoProcessors);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Old Name.
		@param OldName Old Name	  */
	public void setOldName (String OldName)
	{
		set_ValueNoCheck (COLUMNNAME_OldName, OldName);
	}

	/** Get Old Name.
		@return Old Name	  */
	public String getOldName () 
	{
		return (String)get_Value(COLUMNNAME_OldName);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
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

	/** Set Profile.
		@param ProfileInfo 
		Information to help profiling the system for solving support issues
	  */
	public void setProfileInfo (String ProfileInfo)
	{
		set_ValueNoCheck (COLUMNNAME_ProfileInfo, ProfileInfo);
	}

	/** Get Profile.
		@return Information to help profiling the system for solving support issues
	  */
	public String getProfileInfo () 
	{
		return (String)get_Value(COLUMNNAME_ProfileInfo);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		set_ValueNoCheck (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

	/** ReplicationType AD_Reference_ID=126 */
	public static final int REPLICATIONTYPE_AD_Reference_ID=126;
	/** Local = L */
	public static final String REPLICATIONTYPE_Local = "L";
	/** Merge = M */
	public static final String REPLICATIONTYPE_Merge = "M";
	/** Reference = R */
	public static final String REPLICATIONTYPE_Reference = "R";
	/** Set Replication Type.
		@param ReplicationType 
		Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType)
	{

		set_Value (COLUMNNAME_ReplicationType, ReplicationType);
	}

	/** Get Replication Type.
		@return Type of Data Replication
	  */
	public String getReplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ReplicationType);
	}

	/** Set Statistics.
		@param StatisticsInfo 
		Information to help profiling the system for solving support issues
	  */
	public void setStatisticsInfo (String StatisticsInfo)
	{
		set_ValueNoCheck (COLUMNNAME_StatisticsInfo, StatisticsInfo);
	}

	/** Get Statistics.
		@return Information to help profiling the system for solving support issues
	  */
	public String getStatisticsInfo () 
	{
		return (String)get_Value(COLUMNNAME_StatisticsInfo);
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

	/** Set Support EMail.
		@param SupportEMail 
		EMail address to send support information and updates to
	  */
	public void setSupportEMail (String SupportEMail)
	{
		set_Value (COLUMNNAME_SupportEMail, SupportEMail);
	}

	/** Get Support EMail.
		@return EMail address to send support information and updates to
	  */
	public String getSupportEMail () 
	{
		return (String)get_Value(COLUMNNAME_SupportEMail);
	}

	/** Set Support Expires.
		@param SupportExpDate 
		Date when the Adempiere support expires
	  */
	public void setSupportExpDate (Timestamp SupportExpDate)
	{
		set_ValueNoCheck (COLUMNNAME_SupportExpDate, SupportExpDate);
	}

	/** Get Support Expires.
		@return Date when the Adempiere support expires
	  */
	public Timestamp getSupportExpDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_SupportExpDate);
	}

	/** Set Internal Users.
		@param SupportUnits 
		Number of Internal Users for Adempiere Support
	  */
	public void setSupportUnits (int SupportUnits)
	{
		set_ValueNoCheck (COLUMNNAME_SupportUnits, Integer.valueOf(SupportUnits));
	}

	/** Get Internal Users.
		@return Number of Internal Users for Adempiere Support
	  */
	public int getSupportUnits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SupportUnits);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SystemStatus AD_Reference_ID=374 */
	public static final int SYSTEMSTATUS_AD_Reference_ID=374;
	/** Evaluation = E */
	public static final String SYSTEMSTATUS_Evaluation = "E";
	/** Implementation = I */
	public static final String SYSTEMSTATUS_Implementation = "I";
	/** Production = P */
	public static final String SYSTEMSTATUS_Production = "P";
	/** Set System Status.
		@param SystemStatus 
		Status of the system - Support priority depends on system status
	  */
	public void setSystemStatus (String SystemStatus)
	{

		set_Value (COLUMNNAME_SystemStatus, SystemStatus);
	}

	/** Get System Status.
		@return Status of the system - Support priority depends on system status
	  */
	public String getSystemStatus () 
	{
		return (String)get_Value(COLUMNNAME_SystemStatus);
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (String Version)
	{
		set_ValueNoCheck (COLUMNNAME_Version, Version);
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public String getVersion () 
	{
		return (String)get_Value(COLUMNNAME_Version);
	}
}